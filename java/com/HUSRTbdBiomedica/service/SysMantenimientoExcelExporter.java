package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;


public class SysMantenimientoExcelExporter {

private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
	private List<SystemMantenimiento> listsysmttos;
	
	public SysMantenimientoExcelExporter(List<SystemMantenimiento> listsysmttos) {
		this.listsysmttos = listsysmttos;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Servicios");
	}
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");  
		
		cell = row.createCell(1);
		cell.setCellValue("FECHA");
		
		cell = row.createCell(2);
		cell.setCellValue("NOMBRE");
		
		cell = row.createCell(3);
		cell.setCellValue("MARCA");
		
		cell = row.createCell(4);
		cell.setCellValue("MODELO");
		
		cell = row.createCell(5);
		cell.setCellValue("SERIE");
		
		cell = row.createCell(6);
		cell.setCellValue("PLACA INVENTARIO");
		
		cell = row.createCell(7);
		cell.setCellValue("SERVICIO Y UBICACIÓN ANTERIOR");
		
		cell = row.createCell(8);
		cell.setCellValue("SERVICIO");
		
		cell = row.createCell(9);
		cell.setCellValue("UBICACION");
		
		cell = row.createCell(10);
		cell.setCellValue("UBICACION ESPECIFICA");
		
		cell = row.createCell(11);
		cell.setCellValue("QUIEN REALIZA");
		
		cell = row.createCell(12);
		cell.setCellValue("QUIEN RECIBE");

		cell = row.createCell(13);
		cell.setCellValue("HORA");
		
		cell = row.createCell(14);
		cell.setCellValue("REPORTE");
	
		cell = row.createCell(15);
		cell.setCellValue("OBSERVACIONES");
	}
	private void writeDataRows() {
		int rowCount = 1;
		for(SystemMantenimiento mtto:listsysmttos) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(mtto.getId_Sysmtto());
			
			cell = row.createCell(1);
			cell.setCellValue(mtto.getFecha());
			
			cell = row.createCell(2);
			cell.setCellValue(mtto.getEquipo().getNombre_equipo());
			
			cell = row.createCell(3);
			cell.setCellValue(mtto.getEquipo().getMarca());
			
			cell = row.createCell(4);
			cell.setCellValue(mtto.getEquipo().getModelo());
			
			cell = row.createCell(5);
			cell.setCellValue(mtto.getEquipo().getSerie());
			
			cell = row.createCell(6);
			cell.setCellValue(mtto.getEquipo().getPlaca_inventario());
			
			cell = row.createCell(7);
			cell.setCellValue(mtto.getObservacioness());
			
			cell = row.createCell(8);
			cell.setCellValue(mtto.getEquipo().getServicio().getNombre_servicio());
			
			cell = row.createCell(9);
			cell.setCellValue(mtto.getEquipo().getUbicacion());
			
			cell = row.createCell(10);
			cell.setCellValue(mtto.getEquipo().getUbicacion_especifica());
			
			cell = row.createCell(11);
			cell.setCellValue(mtto.getAutor_realizado());
			
			cell = row.createCell(12);
			cell.setCellValue(mtto.getAutor_recibido());
			
			cell = row.createCell(13);
			cell.setCellValue(mtto.getHora_terminacion());
			
			cell = row.createCell(14);
			cell.setCellValue(mtto.getNumero_reporte());
			
			cell = row.createCell(15);
			cell.setCellValue(mtto.getObservacionesh());
		
			
		}
		
	}
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
}
