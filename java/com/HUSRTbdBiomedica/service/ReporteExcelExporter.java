package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.HUSRTbdBiomedica.app.entity.Reporte;

public class ReporteExcelExporter {
	
	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
	private List<Reporte> lisreporte;

	public ReporteExcelExporter(List<Reporte> lisreporte) {
		this.lisreporte = lisreporte;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Reportes");
	}
	
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");  
		
		cell = row.createCell(1);
		cell.setCellValue("NUMERO DE REPORTE");
		
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
		cell.setCellValue("SERVICIO");
		
		cell = row.createCell(8);
		cell.setCellValue("UBICACION");
		
		cell = row.createCell(9);
		cell.setCellValue("FECHA");
		
		cell = row.createCell(10);
		cell.setCellValue("HORA DE LLAMADO");
		
		cell = row.createCell(11);
		cell.setCellValue("HORA DE INICIO");
		
		cell = row.createCell(12);
		cell.setCellValue("HORA DE TERMINACIÓN");
		
		cell = row.createCell(13);
		cell.setCellValue("TOTAL DE HORAS");

		cell = row.createCell(14);
		cell.setCellValue("TIPO DE MANTENIMIENTO");
		
		cell = row.createCell(15);
		cell.setCellValue("FALLA");
		
		cell = row.createCell(16);
		cell.setCellValue("TRABAJO REALIZADO");
		
		cell = row.createCell(17);
		cell.setCellValue("REPUESTO CAMBIADO");
		
		
		cell = row.createCell(18);
		cell.setCellValue("COMPROBANTE EGRESO");
		
		cell = row.createCell(19);
		cell.setCellValue("REALIZADO POR");
		
		cell = row.createCell(20);
		cell.setCellValue("RECIBIDO POR");
		
		cell = row.createCell(21);
		cell.setCellValue("OBSERVACIONES");
		
		cell = row.createCell(22);
		cell.setCellValue("COMODATO");
		
		cell = row.createCell(23);
		cell.setCellValue("TIEMPO FUERA DE SERVICIO");
		
		cell = row.createCell(24);
		cell.setCellValue("MTTO PROPIO");

	}
	private void writeDataRows() {
		int rowCount = 1;
		for(Reporte reporte:lisreporte) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(reporte.getId_Reporte());
			
			cell = row.createCell(1);
			cell.setCellValue(reporte.getNumero_reporte());
			
			cell = row.createCell(2);
			cell.setCellValue(reporte.getNombre_equipo());
			
			cell = row.createCell(3);
			cell.setCellValue(reporte.getMarca());
			
			cell = row.createCell(4);
			cell.setCellValue(reporte.getModelo());
			
			cell = row.createCell(5);
			cell.setCellValue(reporte.getSerie());
			
			cell = row.createCell(6);
			cell.setCellValue(reporte.getPlaca_inventario());
			
			cell = row.createCell(7);
			cell.setCellValue(reporte.getServicio());
			
			cell = row.createCell(8);
			cell.setCellValue(reporte.getUbicacion());
			
			cell = row.createCell(9);
			cell.setCellValue(reporte.getFecha());
			
			cell = row.createCell(10);
			cell.setCellValue(reporte.getHora_llamado());
			
			cell = row.createCell(11);
			cell.setCellValue(reporte.getHora_inicio());
			
			cell = row.createCell(12);
			cell.setCellValue(reporte.getHora_terminacion());
			
			cell = row.createCell(13);
			cell.setCellValue(reporte.getTotal_horas());
			
			cell = row.createCell(14);
			cell.setCellValue(reporte.getTipo_mantenimiento());
			
			cell = row.createCell(15);
			cell.setCellValue(reporte.getTipo_falla());
			
			cell = row.createCell(16);
			cell.setCellValue(reporte.getTrabajo_realizado());
			
			cell = row.createCell(17);
			cell.setCellValue(reporte.getRepuesto_cambiado());
			
			cell = row.createCell(18);
			cell.setCellValue(reporte.getComprobante_ingreso());
			
			cell = row.createCell(19);
			cell.setCellValue(reporte.getAutor_realizado());
			
			cell = row.createCell(20);
			cell.setCellValue(reporte.getAutor_recibido());
			
			cell = row.createCell(21);
			cell.setCellValue(reporte.getObservaciones());
			
			cell = row.createCell(22);
			cell.setCellValue(reporte.isComodato());
			
			cell = row.createCell(23);
			cell.setCellValue(reporte.getTiempo_fuera_servicio());
			
			
			cell = row.createCell(24);
			cell.setCellValue(reporte.isMtto_propio());
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
