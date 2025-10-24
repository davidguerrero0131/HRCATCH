package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




import com.HUSRTbdBiomedica.app.entity.Equipo;

public class EquipoExcelExporter {

	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
	private List<Equipo> listequipos;
	
	public EquipoExcelExporter(List<Equipo> listequipos) {
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
		cell.setCellValue("PERIODICIDAD");
		
		cell = row.createCell(10);
		cell.setCellValue("DIAS MANTENIMIENTO");
		
		cell = row.createCell(11);
		cell.setCellValue("MESES MANTENIMIENTO");
		
		cell = row.createCell(12);
		cell.setCellValue("ANO MANTENIMIENTO");
		
		cell = row.createCell(13);
		cell.setCellValue("ENERO MANTENIMIENTO");

		cell = row.createCell(14);
		cell.setCellValue("FEBRERO MANTENIMIENTO");
		
		cell = row.createCell(15);
		cell.setCellValue("MARZO MANTENIMIENTO");
		
		cell = row.createCell(16);
		cell.setCellValue("ABRIL MANTENIMIENTO");
		
		cell = row.createCell(17);
		cell.setCellValue("MAYO MANTENIMIENTO");
		
		
		cell = row.createCell(18);
		cell.setCellValue("JUNIO MANTENIMIENTO");
		
		cell = row.createCell(19);
		cell.setCellValue("JULIO MANTENIMIENTO");
		
		cell = row.createCell(20);
		cell.setCellValue("AGOSTO MANTENIMIENTO");
		
		cell = row.createCell(21);
		cell.setCellValue("SEPTIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(22);
		cell.setCellValue("OCTUBRE MANTENIMIENTO");
		
		cell = row.createCell(23);
		cell.setCellValue("NOVIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(24);
		cell.setCellValue("DICIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(25);
		cell.setCellValue("ANO INGRESO");
		
		cell = row.createCell(26);
		cell.setCellValue("ACTIVO");
	}
	
	private void writeDataRows() {
		int rowCount = 1;
		for(Equipo equipo:listequipos) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getId_Equipo());
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getNombre_Equipo());
			
			cell = row.createCell(2);
			cell.setCellValue(equipo.getMarca());
			
			cell = row.createCell(3);
			cell.setCellValue(equipo.getModelo());
			
			cell = row.createCell(4);
			cell.setCellValue(equipo.getSerie());
			
			cell = row.createCell(5);
			cell.setCellValue(equipo.getPlaca_inventario());
			
			cell = row.createCell(6);
			cell.setCellValue(equipo.getServicios());
			
			cell = row.createCell(7);
			cell.setCellValue(equipo.getUbicacion());
			
			cell = row.createCell(8);
			cell.setCellValue(equipo.getUbicacion_especifica());
			
			cell = row.createCell(9);
			cell.setCellValue(equipo.getPeriodicidad());
			
			cell = row.createCell(10);
			cell.setCellValue(equipo.getDias_mantenimiento());
			
			cell = row.createCell(11);
			cell.setCellValue(equipo.getMeses_mantenimiento());
			
			cell = row.createCell(12);
			cell.setCellValue(equipo.getAno_mantenimiento());
			
			cell = row.createCell(13);
			cell.setCellValue(equipo.getEnero_mantenimiento());
			
			cell = row.createCell(14);
			cell.setCellValue(equipo.getFebrero_mantenimiento());
			
			cell = row.createCell(15);
			cell.setCellValue(equipo.getMarzo_mantenimiento());
			
			cell = row.createCell(16);
			cell.setCellValue(equipo.getAbril_mantenimiento());
			
			cell = row.createCell(17);
			cell.setCellValue(equipo.getMayo_mantenimiento());
			
			cell = row.createCell(18);
			cell.setCellValue(equipo.getJunio_mantenimiento());
			
			cell = row.createCell(19);
			cell.setCellValue(equipo.getJulio_mantenimiento());
			
			cell = row.createCell(20);
			cell.setCellValue(equipo.getAgosto_mantenimiento());
			
			cell = row.createCell(21);
			cell.setCellValue(equipo.getSeptiembre_mantenimiento());
			
			cell = row.createCell(22);
			cell.setCellValue(equipo.getOctubre_mantenimiento());
			
			cell = row.createCell(23);
			cell.setCellValue(equipo.getNoviembre_mantenimiento());
			
			cell = row.createCell(24);
			cell.setCellValue(equipo.getDiciembre_mantenimiento());
			
			cell = row.createCell(25);
			cell.setCellValue(equipo.getAno_ingreso());
			
			cell = row.createCell(26);
			cell.setCellValue(equipo.isActivo());
			
		
			
		}
		
	}
	
	private void writeHeaderRow2() {
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
		cell.setCellValue("PERIODICIDAD");
		
		cell = row.createCell(10);
		cell.setCellValue("DIAS MANTENIMIENTO");
		
		cell = row.createCell(11);
		cell.setCellValue("MESES MANTENIMIENTO");
		
		cell = row.createCell(12);
		cell.setCellValue("ANO MANTENIMIENTO");
		
		cell = row.createCell(13);
		cell.setCellValue("ENERO MANTENIMIENTO");

		cell = row.createCell(14);
		cell.setCellValue("FEBRERO MANTENIMIENTO");
		
		cell = row.createCell(15);
		cell.setCellValue("MARZO MANTENIMIENTO");
		
		cell = row.createCell(16);
		cell.setCellValue("ABRIL MANTENIMIENTO");
		
		cell = row.createCell(17);
		cell.setCellValue("MAYO MANTENIMIENTO");
		
		
		cell = row.createCell(18);
		cell.setCellValue("JUNIO MANTENIMIENTO");
		
		cell = row.createCell(19);
		cell.setCellValue("JULIO MANTENIMIENTO");
		
		cell = row.createCell(20);
		cell.setCellValue("AGOSTO MANTENIMIENTO");
		
		cell = row.createCell(21);
		cell.setCellValue("SEPTIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(22);
		cell.setCellValue("OCTUBRE MANTENIMIENTO");
		
		cell = row.createCell(23);
		cell.setCellValue("NOVIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(24);
		cell.setCellValue("DICIEMBRE MANTENIMIENTO");
		
		cell = row.createCell(25);
		cell.setCellValue("ANO INGRESO");
		
		cell = row.createCell(26);
		cell.setCellValue("ACTIVO");
		
		cell = row.createCell(27);
		cell.setCellValue("CODIGO");
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
		
		for(Equipo equipo:listequipos) {
			
			
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getNombre_Equipo()+"\n "+equipo.getMarca()+"\n "+equipo.getModelo()+"\n "+equipo.getSerie());
			cell.setCellStyle(wrapStyle);
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getServicios());
			
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
			ArrayList<String> dias = new ArrayList<String>(Arrays.asList(equipo.getDias_mantenimiento().split(",")));
			String days = "";
			for(int d = 0;d<dias.size();d++) {
				days = days.concat(dias.get(d)+"\n ");
			}
			cell = row.createCell(4);
			cell.setCellValue(days);
			cell.setCellStyle(wrapStyle);
			ArrayList<String> meses = new ArrayList<String>(Arrays.asList(equipo.getMeses_mantenimiento().split(",")));
			String months = "";
			for(int m = 0;m<meses.size();m++) {
				months = months.concat(meses.get(m)+"\n ");
			}
			cell = row.createCell(5);
			cell.setCellValue(months);
			cell.setCellStyle(wrapStyle);
			
			cell = row.createCell(6);
			cell.setCellValue(equipo.getAno_mantenimiento());
			
			cell = row.createCell(7);
			if(equipo.getResponsable()==null) {
				cell.setCellValue("");
			}
			else {
				cell.setCellValue(equipo.getResponsable().getNombre_responsable());
			}
			
			
			cell = row.createCell(8);
			cell.setCellValue("ACTIVIDAD");
			
			
		}
		
	}
	private void writeDataRows2() {
		int rowCount = 1;
		for(Equipo equipo:listequipos) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(equipo.getId_Equipo());
			
			cell = row.createCell(1);
			cell.setCellValue(equipo.getNombre_Equipo());
			
			cell = row.createCell(2);
			cell.setCellValue(equipo.getMarca());
			
			cell = row.createCell(3);
			cell.setCellValue(equipo.getModelo());
			
			cell = row.createCell(4);
			cell.setCellValue(equipo.getSerie());
			
			cell = row.createCell(5);
			cell.setCellValue(equipo.getPlaca_inventario());
			
			cell = row.createCell(6);
			if(equipo.getServicio()!=null) {
				cell.setCellValue(equipo.getServicio().getNombre_servicio());
			}
			else {
				cell.setCellValue(equipo.getServicios());
			}
			
			
			cell = row.createCell(7);
			cell.setCellValue(equipo.getUbicacion());
			
			cell = row.createCell(8);
			cell.setCellValue(equipo.getUbicacion_especifica());
			
			cell = row.createCell(9);
			cell.setCellValue(equipo.getPeriodicidad());
			
			cell = row.createCell(10);
			cell.setCellValue(equipo.getDias_mantenimiento());
			
			cell = row.createCell(11);
			cell.setCellValue(equipo.getMeses_mantenimiento());
			
			cell = row.createCell(12);
			cell.setCellValue(equipo.getAno_mantenimiento());
			
			cell = row.createCell(13);
			cell.setCellValue(equipo.getEnero_mantenimiento());
			
			cell = row.createCell(14);
			cell.setCellValue(equipo.getFebrero_mantenimiento());
			
			cell = row.createCell(15);
			cell.setCellValue(equipo.getMarzo_mantenimiento());
			
			cell = row.createCell(16);
			cell.setCellValue(equipo.getAbril_mantenimiento());
			
			cell = row.createCell(17);
			cell.setCellValue(equipo.getMayo_mantenimiento());
			
			cell = row.createCell(18);
			cell.setCellValue(equipo.getJunio_mantenimiento());
			
			cell = row.createCell(19);
			cell.setCellValue(equipo.getJulio_mantenimiento());
			
			cell = row.createCell(20);
			cell.setCellValue(equipo.getAgosto_mantenimiento());
			
			cell = row.createCell(21);
			cell.setCellValue(equipo.getSeptiembre_mantenimiento());
			
			cell = row.createCell(22);
			cell.setCellValue(equipo.getOctubre_mantenimiento());
			
			cell = row.createCell(23);
			cell.setCellValue(equipo.getNoviembre_mantenimiento());
			
			cell = row.createCell(24);
			cell.setCellValue(equipo.getDiciembre_mantenimiento());
			
			cell = row.createCell(25);
			cell.setCellValue(equipo.getAno_ingreso());
			
			cell = row.createCell(26);
			cell.setCellValue(equipo.isActivo());
			
			cell = row.createCell(27);
			cell.setCellValue(equipo.getCodigo());
			
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
	public void exportPlan(HttpServletResponse response) throws IOException{
		writeHeaderRowPlan();
		writeDataRowsPlan();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
