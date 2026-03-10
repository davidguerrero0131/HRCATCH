package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.HUSRTbdBiomedica.app.entity.SystemEquipo;

public class SysEquipoExcelExporter {

	
	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
	private List<SystemEquipo> listequipos;
	
	public SysEquipoExcelExporter(List<SystemEquipo> listequipos) {
		this.listequipos = listequipos;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Equipos");
	}
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");  
		
		cell = row.createCell(1);
		cell.setCellValue("NOMBRE");
		
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
		cell.setCellValue("UBICACION ESPECIFICA");
		
		cell = row.createCell(10);
		cell.setCellValue("PERIODICIDAD");
		
		cell = row.createCell(11);
		cell.setCellValue("DIAS MANTENIMIENTO");

		cell = row.createCell(12);
		cell.setCellValue("ANO INGRESO");
		
		cell = row.createCell(13);
		cell.setCellValue("ACTIVO");
		
		cell = row.createCell(14);
		cell.setCellValue("RESPONSABLE");
	}
	
	private void writeDataRows() {
		int rowCount = 1;
		for(SystemEquipo equipo:listequipos) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getId_Sysequipo());
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getTipo_equipo().getNombre_Tipo_equipo());
			
			cell = row.createCell(2);
			cell.setCellValue(equipo.getNombre_equipo());
			
			cell = row.createCell(3);
			cell.setCellValue(equipo.getMarca());
			
			cell = row.createCell(4);
			cell.setCellValue(equipo.getModelo());
			
			cell = row.createCell(5);
			cell.setCellValue(equipo.getSerie());
			
			cell = row.createCell(6);
			cell.setCellValue(equipo.getPlaca_inventario());
			
			cell = row.createCell(7);
			cell.setCellValue(equipo.getServicio().getNombre_servicio());
			
			cell = row.createCell(8);
			cell.setCellValue(equipo.getUbicacion());
			
			cell = row.createCell(9);
			cell.setCellValue(equipo.getUbicacion_especifica());
			
			cell = row.createCell(10);
			cell.setCellValue(equipo.getPeriodicidad());
			
			cell = row.createCell(11);
			cell.setCellValue(equipo.getDias_mantenimiento());
			
			cell = row.createCell(12);
			cell.setCellValue(equipo.getAno_ingreso());
			
			cell = row.createCell(13);
			cell.setCellValue(equipo.isActivo());
			
			cell = row.createCell(14);
			cell.setCellValue(equipo.getUsuario().getNombre() + ' ' + equipo.getUsuario().getApellido());
		
			
		}
		
	}
	
	private void writeHeaderRow2() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");  
		
		cell = row.createCell(1);
		cell.setCellValue("NOMBRE");
		
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
		cell.setCellValue("UBICACION ESPECIFICA");
		
		cell = row.createCell(10);
		cell.setCellValue("PERIODICIDAD");
		
		cell = row.createCell(11);
		cell.setCellValue("DIAS MANTENIMIENTO");

		cell = row.createCell(12);
		cell.setCellValue("ANO INGRESO");
		
		cell = row.createCell(13);
		cell.setCellValue("ACTIVO");
		
		cell = row.createCell(14);
		cell.setCellValue("CODIGO");
	}
	
	private void writeDataRows2() {
		int rowCount = 1;
		for(SystemEquipo equipo:listequipos) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getId_Sysequipo());
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getTipo_equipo().getNombre_Tipo_equipo());
			
			cell = row.createCell(2);
			cell.setCellValue(equipo.getNombre_equipo());
			
			cell = row.createCell(3);
			cell.setCellValue(equipo.getMarca());
			
			cell = row.createCell(4);
			cell.setCellValue(equipo.getModelo());
			
			cell = row.createCell(5);
			cell.setCellValue(equipo.getSerie());
			
			cell = row.createCell(6);
			cell.setCellValue(equipo.getPlaca_inventario());
			
			cell = row.createCell(7);
			cell.setCellValue(equipo.getServicio().getNombre_servicio());
			
			cell = row.createCell(8);
			cell.setCellValue(equipo.getUbicacion());
			
			cell = row.createCell(9);
			cell.setCellValue(equipo.getUbicacion_especifica());
			
			cell = row.createCell(10);
			cell.setCellValue(equipo.getPeriodicidad());
			
			cell = row.createCell(11);
			cell.setCellValue(equipo.getDias_mantenimiento());
			
			cell = row.createCell(12);
			cell.setCellValue(equipo.getAno_ingreso());
			
			cell = row.createCell(13);
			cell.setCellValue(equipo.isActivo());
			
			cell = row.createCell(14);
			cell.setCellValue(equipo.getCodigo());
			
		}
		
	}

	private void writeHeaderRowPlan() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("NOMBRE DEL EQUIPO");  
		
		cell = row.createCell(1);
		cell.setCellValue("LOCALIZACIÓN");
		
		cell = row.createCell(2);
		cell.setCellValue("N° DE INVENTARIO");
		
		cell = row.createCell(3);
		cell.setCellValue("PERIODICIDAD");
		
		cell = row.createCell(4);
		cell.setCellValue("DIA");
		
		cell = row.createCell(5);
		cell.setCellValue("MES");
		
		cell = row.createCell(6);
		cell.setCellValue("AÑO");
		
		cell = row.createCell(7);
		cell.setCellValue("RESPONSABLE(S)");
		
		cell = row.createCell(8);
		cell.setCellValue("ACTIVIDADES");
	}
	private void writeDataRowsPlan() {
		int rowCount = 1;
		CellStyle wrapStyle = workbook.createCellStyle();
		wrapStyle.setWrapText(true);
		
		for(SystemEquipo equipo:listequipos) {
			
			
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getTipo_equipo().getNombre_Tipo_equipo()+"\n "+equipo.getMarca()+"\n "+equipo.getModelo()+"\n "+equipo.getSerie());
			cell.setCellStyle(wrapStyle);
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getServicio().getNombre_servicio());
			
			cell = row.createCell(2);
			cell.setCellValue(equipo.getPlaca_inventario());
			
			String pdad = "";
			int p = equipo.getPeriodicidad();
			if(p==1) {
				pdad = "ANUAL";
			}
			else if(p==2) {
				pdad = "SEMESTRAL";
			}
			else if(p==3) {
				pdad = "TRIMESTRAL";
			}
			else if(p==4) {
				pdad = "CUATRIMESTRAL";
			}
			cell = row.createCell(3);
			cell.setCellValue(pdad);
			ArrayList<String> dias = new ArrayList<String>(Arrays.asList(equipo.getDias_mantenimiento().split("-")));
			System.out.println(dias);
			System.out.println(dias.size());
			
			ArrayList<String> splitf1 =new ArrayList<String>();
			ArrayList<String> splitf2 =new ArrayList<String>();
			LocalDate fday = LocalDate.now();	
			LocalDate lday = LocalDate.now();
			
			if(dias.size()>1) {
				splitf1 =new ArrayList<String>(Arrays.asList(dias.get(0).trim().split("/")));				
				splitf2 =new ArrayList<String>(Arrays.asList(dias.get(1).trim().split("/")));
				fday = LocalDate.of(Integer.valueOf(splitf1.get(2).replace(",", "")), Integer.valueOf(splitf1.get(1)), Integer.valueOf(splitf1.get(0)));
				lday = LocalDate.of(Integer.valueOf(splitf2.get(2).replace(",", "")), Integer.valueOf(splitf2.get(1)), Integer.valueOf(splitf2.get(0)));
				
			}
			else {
				
			}
			System.out.println(fday);
			System.out.println(lday);
			System.out.println(equipo.getId_Sysequipo());
			int mes = fday.getMonthValue();
			String month = "";
			if(mes == 1) {
				month = "ENERO";
			}
			else if(mes == 2) {
				month = "FEBRERO";
			}
			else if(mes == 3) {
				month = "MARZO";		
			}
			else if(mes == 4) {
				month = "ABRIL";
			}
			else if(mes == 5) {
				month = "MAYO";
			}
			else if(mes == 6) {
				month = "JUNIO";
			}
			else if(mes == 7) {
				month = "JULIO";
			}
			else if(mes == 8) {
				month = "AGOSTO";
			}
			else if(mes == 9) {
				month = "SEPTIEMBRE";
			}
			else if(mes == 10) {
				month = "OCTUBRE";
			}
			else if(mes == 11) {
				month = "NOVIEMBRE";
			}
			else if(mes == 12) {
				month = "DICIEMBRE";
			}
			
			cell = row.createCell(4);
			if(dias.size()>0) {
				cell.setCellValue(String.valueOf(fday.getDayOfMonth())+'-'+String.valueOf(lday.getDayOfMonth()));				
			}
			else {
				cell.setCellValue("");	
			}
			cell.setCellStyle(wrapStyle);
			
			cell = row.createCell(5);
			cell.setCellValue(month);
			cell.setCellStyle(wrapStyle);
			
			cell = row.createCell(6);
			cell.setCellValue(fday.getYear());
			
			cell = row.createCell(7);
			if(equipo.getMtto()!=1) {
				cell.setCellValue("EMPTY");
				
			}
			else {			
				cell.setCellValue("INGENIERÍA DE SISTEMAS");
			}
			
			cell = row.createCell(8);
			cell.setCellValue(equipo.getTipo_equipo().getActividad());
			
			
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
	public void exportadmin(HttpServletResponse response) throws IOException {
		writeHeaderRow2();
		writeDataRows2();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
	public void exportsysplan(HttpServletResponse response) throws IOException {
		writeHeaderRowPlan();
		writeDataRowsPlan();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
}
