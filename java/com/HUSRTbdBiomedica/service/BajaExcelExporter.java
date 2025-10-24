package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.HUSRTbdBiomedica.app.entity.Baja;

public class BajaExcelExporter {
	
	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
	private List<Baja> listbajas;

	public BajaExcelExporter(List<Baja> listbajas) {
		this.listbajas = listbajas;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Bajas");
	}
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");  
		
		cell = row.createCell(1);
		cell.setCellValue("NOMBRE");
		
		cell = row.createCell(2);
		cell.setCellValue("MARCA");
		
		cell = row.createCell(3);
		cell.setCellValue("MODELO");
		
		cell = row.createCell(4);
		cell.setCellValue("SERIE");
		
		cell = row.createCell(5);
		cell.setCellValue("PLACA INVENTARIO");
		
		cell = row.createCell(6);
		cell.setCellValue("SERVICIO");
		
		cell = row.createCell(7);
		cell.setCellValue("UBICACION");
		
		cell = row.createCell(8);
		cell.setCellValue("UBICACION ESPECIFICA");
		
		cell = row.createCell(9);
		cell.setCellValue("ANO DE INGRESO");
		
		cell = row.createCell(10);
		cell.setCellValue("CAUSA DE LA BAJA");
	}
	private void writeDataRows() {
		int rowCount = 1;
		for(Baja baja:listbajas) {
Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(baja.getId_Baja());
			
			cell = row.createCell(1);
			cell.setCellValue(baja.getNombre_Baja());
			
			cell = row.createCell(2);
			cell.setCellValue(baja.getMarca_baja());
			
			cell = row.createCell(3);
			cell.setCellValue(baja.getModelo_baja());
			
			cell = row.createCell(4);
			cell.setCellValue(baja.getSerie_baja());
			
			cell = row.createCell(5);
			cell.setCellValue(baja.getPlaca_inventario_baja());
			
			cell = row.createCell(6);
			cell.setCellValue(baja.getServicio_baja());
			
			cell = row.createCell(7);
			cell.setCellValue(baja.getUbicacion_baja());
			
			cell = row.createCell(8);
			cell.setCellValue(baja.getUbicacion_especifica_baja());
			
			cell = row.createCell(9);
			cell.setCellValue(baja.getAno_ingreso_baja());
			
			cell = row.createCell(10);
			cell.setCellValue(baja.getCausa());
			
			
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
