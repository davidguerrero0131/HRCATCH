package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;

public class Hoja_vidaExcelExporter {
	
	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;

	private List<Hoja_vida> listhojasvida;

	public Hoja_vidaExcelExporter(List<Hoja_vida> listhojasvida) {
		this.listhojasvida = listhojasvida;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Hojas_vida");
	}
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);		
		cell.setCellValue("ID");
		
		cell = row.createCell(1);
		cell.setCellValue("SERIE");
		
		cell = row.createCell(2);
		cell.setCellValue("NOMBRE");
		
		cell = row.createCell(3);
		cell.setCellValue("MARCA");
		
		cell = row.createCell(4);
		cell.setCellValue("MODELO");
		
		cell = row.createCell(5);
		cell.setCellValue("ANO FABRICACION");
		
		cell = row.createCell(6);
		cell.setCellValue("PLACA INVENTARIO");
		
		cell = row.createCell(7);
		cell.setCellValue("DEPARTAMENTO");
		
		cell = row.createCell(8);
		cell.setCellValue("MUNICIPIO");
		
		cell = row.createCell(9);
		cell.setCellValue("DIRECCION");
		
		cell = row.createCell(10);
		cell.setCellValue("TELEFONO");
		
		cell = row.createCell(11);
		cell.setCellValue("UBICACION");
		
		cell = row.createCell(12);
		cell.setCellValue("CODIGO INTERNACIONAL");
		
		cell = row.createCell(13);
		cell.setCellValue("SERVICIO");
		
		cell = row.createCell(14);
		cell.setCellValue("E-MAIL");
		
		cell = row.createCell(15);
		cell.setCellValue("CONTRATO");
		
		cell = row.createCell(16);
		cell.setCellValue("COMPRA DIRECTA");
		
		cell = row.createCell(17);
		cell.setCellValue("CONVENIO");
		
		cell = row.createCell(18);
		cell.setCellValue("DONADO");
		
		cell = row.createCell(19);
		cell.setCellValue("ASIGNADO POR EL MINISTERIO");
		
		cell = row.createCell(20);
		cell.setCellValue("ASIGNADO POR LA GOBERNACION");
		
		cell = row.createCell(21);
		cell.setCellValue("COMODATO");
		
		cell = row.createCell(22);
		cell.setCellValue("FECHA DE COMPRA");
		
		cell = row.createCell(23);
		cell.setCellValue("FECHA DE INSTALACIÓN");
		
		cell = row.createCell(24);
		cell.setCellValue("FECHA DE INICIO OPERACIÓN");
		
		cell = row.createCell(25);
		cell.setCellValue("FECHA VENCIMIENTO GARANTÍA");
		
		cell = row.createCell(26);
		cell.setCellValue("COSTO DE COMPRA");
		
		cell = row.createCell(27);
		cell.setCellValue("REGISTRO INVIMA");
		
		cell = row.createCell(28);
		cell.setCellValue("FABRICANTE");
		
		cell = row.createCell(29);
		cell.setCellValue("PAIS");
		
		cell = row.createCell(30);
		cell.setCellValue("PROVEEDOR");
		
		cell = row.createCell(31);
		cell.setCellValue("TELEFONO PROVEEDOR");
		
		cell = row.createCell(32);
		cell.setCellValue("CORREO PROVEEDOR");
		
		cell = row.createCell(33);
		cell.setCellValue("CIUDAD PROVEEDOR");
		
		cell = row.createCell(34);
		cell.setCellValue("REPRESENTANTE");
		
		cell = row.createCell(35);
		cell.setCellValue("TELEFONO REPRESENTANTE");
		
		cell = row.createCell(36);
		cell.setCellValue("VOLTAJE MAXIMO OPERACION");
		
		cell = row.createCell(37);
		cell.setCellValue("VOLTAJE MINIMO OPERACION");
		
		cell = row.createCell(38);
		cell.setCellValue("CORRIENTE MAXIMA OPERACION");
		
		cell = row.createCell(39);
		cell.setCellValue("CORRIENTE MINIMA OPERACION");
		
		cell = row.createCell(40);
		cell.setCellValue("POTENCIA CONSUMIDA");
		
		cell = row.createCell(41);
		cell.setCellValue("FRECUENCIA");
		
		cell = row.createCell(42);
		cell.setCellValue("PRESION");
		
		cell = row.createCell(43);
		cell.setCellValue("VELOCIDAD");
		
		cell = row.createCell(44);
		cell.setCellValue("TEMPERATURA");
		
		cell = row.createCell(45);
		cell.setCellValue("PESO");
		
		cell = row.createCell(46);
		cell.setCellValue("CAPACIDAD");
		
		cell = row.createCell(47);
		cell.setCellValue("OTROS");
		
		cell = row.createCell(48);
		cell.setCellValue("ELECTRICIDAD");
		
		cell = row.createCell(49);
		cell.setCellValue("ENERGIA SOLAR");
		
		cell = row.createCell(50);
		cell.setCellValue("AGUA");
		
		cell = row.createCell(51);
		cell.setCellValue("GAS");
		
		cell = row.createCell(52);
		cell.setCellValue("VAPOR AGUA");
		
		cell = row.createCell(53);
		cell.setCellValue("DERIVADOS DEL PETROLEO");
		
		cell = row.createCell(54);
		cell.setCellValue("OTRAS FUENTES");
		
		cell = row.createCell(55);
		cell.setCellValue("FIJO");
		
		cell = row.createCell(56);
		cell.setCellValue("PORTATIL");
		
		cell = row.createCell(57);
		cell.setCellValue("MEDICO");
		
		cell = row.createCell(58);
		cell.setCellValue("BASICO");
		
		cell = row.createCell(59);
		cell.setCellValue("APOYO");
		
		cell = row.createCell(60);
		cell.setCellValue("I");
		
		cell = row.createCell(61);
		cell.setCellValue("IIA");
		
		cell = row.createCell(62);
		cell.setCellValue("IIB");
		
		cell = row.createCell(63);
		cell.setCellValue("III");
		
		cell = row.createCell(64);
		cell.setCellValue("ELECTRICO");
		
		cell = row.createCell(65);
		cell.setCellValue("ELECTRONICO");
		
		cell = row.createCell(66);
		cell.setCellValue("MECANICO");
		
		cell = row.createCell(67);
		cell.setCellValue("ELECTROMECANICO");
		
		cell = row.createCell(68);
		cell.setCellValue("HIDRAULICO");
		
		cell = row.createCell(69);
		cell.setCellValue("NEUMATICO");
		
		cell = row.createCell(70);
		cell.setCellValue("VAPOR");
		
		cell = row.createCell(71);
		cell.setCellValue("SOLAR");
		
		cell = row.createCell(72);
		cell.setCellValue("DIAGNOSTICO");
		
		cell = row.createCell(73);
		cell.setCellValue("TRATAMIENTO Y MANTENIMIENTO DE LA VIDA");
		
		cell = row.createCell(74);
		cell.setCellValue("REHABILITACION");
		
		cell = row.createCell(75);
		cell.setCellValue("PREVENCION");
		
		cell = row.createCell(76);
		cell.setCellValue("ANALISIS DE LABORATORIO");
		
		cell = row.createCell(77);
		cell.setCellValue("TRIMESTRAL");
		
		cell = row.createCell(78);
		cell.setCellValue("CUATRIMESTRAL");
		
		cell = row.createCell(79);
		cell.setCellValue("SEMESTRAL");
		
		cell = row.createCell(80);
		cell.setCellValue("ANUAL");
		
		cell = row.createCell(81);
		cell.setCellValue("PROPIO");
		
		cell = row.createCell(82);
		cell.setCellValue("CONTRATADO");
		
		cell = row.createCell(83);
		cell.setCellValue("COMODATO");
		
		cell = row.createCell(84);
		cell.setCellValue("GARANTIA");
		
		cell = row.createCell(85);
		cell.setCellValue("HOSPITAL");
		
		cell = row.createCell(86);
		cell.setCellValue("PROVEEDOR");
		
		cell = row.createCell(87);
		cell.setCellValue("OTRO");
		
		cell = row.createCell(88);
		cell.setCellValue("OPERACIONAL-USUARIO");
		
		cell = row.createCell(89);
		cell.setCellValue("TECNICO");
		
		cell = row.createCell(90);
		cell.setCellValue("SI REQUIERE C/V");
		
		cell = row.createCell(91);
		cell.setCellValue("NO REQUIERE C/V");
		
		cell = row.createCell(92);
		cell.setCellValue("C/V SEMESTRAL");
		
		cell = row.createCell(93);
		cell.setCellValue("C/V ANUAL");
		
		cell = row.createCell(94);
		cell.setCellValue("ACCESORIOS 1");
		
		cell = row.createCell(95);
		cell.setCellValue("ACCESORIOS 2");
		
		cell = row.createCell(96);
		cell.setCellValue("ACCESORIOS 3");
		
		cell = row.createCell(97);
		cell.setCellValue("ACCESORIOS 4");
		
		
	}
	private void writeDataRows() {
		int rowCount = 1;
		for(Hoja_vida hoja_vida:listhojasvida) {
			if(hoja_vida.getEquipo()!=null) {
				Row row = sheet.createRow(rowCount++);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(hoja_vida.getId_Hoja_vida());
				
				cell = row.createCell(1);
				cell.setCellValue(hoja_vida.getEquipo().getSerie());
				
				cell = row.createCell(2);
				cell.setCellValue(hoja_vida.getEquipo().getNombre_Equipo());
				
				cell = row.createCell(3);
				cell.setCellValue(hoja_vida.getEquipo().getMarca());
				
				cell = row.createCell(4);
				cell.setCellValue(hoja_vida.getEquipo().getModelo());
				
				cell = row.createCell(5);
				cell.setCellValue(hoja_vida.getAno_fabricacion());
				
				cell = row.createCell(6);
				cell.setCellValue(hoja_vida.getEquipo().getPlaca_inventario());
				
				cell = row.createCell(7);
				cell.setCellValue(hoja_vida.getDepartamento());
				
				cell = row.createCell(8);
				cell.setCellValue(hoja_vida.getMunicipio());

				cell = row.createCell(9);
				cell.setCellValue(hoja_vida.getDireccion());
				
				cell = row.createCell(10);
				cell.setCellValue(hoja_vida.getTelefonoinstitucion());
				
				cell = row.createCell(11);
				cell.setCellValue(hoja_vida.getEquipo().getUbicacion());
				
				cell = row.createCell(12);
				cell.setCellValue(hoja_vida.getCodinternacional());
				
				cell = row.createCell(13);
				cell.setCellValue(hoja_vida.getEquipo().getServicios());
				
				cell = row.createCell(14);
				cell.setCellValue(hoja_vida.getEmailinstitucion());
				
				cell = row.createCell(15);
				cell.setCellValue(hoja_vida.getContrato());
				
				cell = row.createCell(16);
				cell.setCellValue(hoja_vida.isCompraddirecta());
				
				cell = row.createCell(17);
				cell.setCellValue(hoja_vida.isConvenio());
				
				cell = row.createCell(18);
				cell.setCellValue(hoja_vida.isDonado());
				
				cell = row.createCell(19);
				cell.setCellValue(hoja_vida.isAsignadoporministerio());
				
				cell = row.createCell(20);
				cell.setCellValue(hoja_vida.isAsignadoporgobernacion());
				
				cell = row.createCell(21);
				cell.setCellValue(hoja_vida.isComodato());
				
				cell = row.createCell(22);
				cell.setCellValue(hoja_vida.getFecha_compra());
				
				cell = row.createCell(23);
				cell.setCellValue(hoja_vida.getFecha_instalacion());
				
				cell = row.createCell(24);
				cell.setCellValue(hoja_vida.getFecha_iniciooperacion());
				
				cell = row.createCell(25);
				cell.setCellValue(hoja_vida.getFecha_vencimientogarantia());
				
				cell = row.createCell(26);
				cell.setCellValue(hoja_vida.getCosto_compra());
				
				cell = row.createCell(27);
				cell.setCellValue(hoja_vida.getRegistro_invima());
				
				cell = row.createCell(28);
				cell.setCellValue(hoja_vida.getFabricante());
				
				cell = row.createCell(29);
				cell.setCellValue(hoja_vida.getPaisfabricante());
				
				cell = row.createCell(30);
				cell.setCellValue(hoja_vida.getProveedor());
				
				cell = row.createCell(31);
				cell.setCellValue(hoja_vida.getTelefonoproveedor());
				
				cell = row.createCell(32);
				cell.setCellValue(hoja_vida.getCorreoproveedor());
				
				cell = row.createCell(33);
				cell.setCellValue(hoja_vida.getCiudadproveedor());
				
				cell = row.createCell(34);
				cell.setCellValue(hoja_vida.getRepresentante());
				
				cell = row.createCell(35);
				cell.setCellValue(hoja_vida.getTelefonorepresentante());
				
				cell = row.createCell(36);
				cell.setCellValue(hoja_vida.getVmaxoperacion());
				
				cell = row.createCell(37);
				cell.setCellValue(hoja_vida.getVminoperacion());
				
				cell = row.createCell(38);
				cell.setCellValue(hoja_vida.getImaxoperacion());
				
				cell = row.createCell(39);
				cell.setCellValue(hoja_vida.getIminoperacion());
				
				cell = row.createCell(40);
				cell.setCellValue(hoja_vida.getWconsumida());
				
				cell = row.createCell(41);
				cell.setCellValue(hoja_vida.getFrecuencia());
				
				cell = row.createCell(42);
				cell.setCellValue(hoja_vida.getPresion());
				
				cell = row.createCell(43);
				cell.setCellValue(hoja_vida.getVelocidad());
				
				cell = row.createCell(44);
				cell.setCellValue(hoja_vida.getTemperatura());
				
				cell = row.createCell(45);
				cell.setCellValue(hoja_vida.getPeso());
				
				cell = row.createCell(46);
				cell.setCellValue(hoja_vida.getCapacidad());
				
				cell = row.createCell(47);
				cell.setCellValue(hoja_vida.getOtrosdatostecnicos());
				
				cell = row.createCell(48);
				cell.setCellValue(hoja_vida.isFuenteaelectricidad());
				
				cell = row.createCell(49);
				cell.setCellValue(hoja_vida.isFuenteaenergiasolar());
				
				cell = row.createCell(50);
				cell.setCellValue(hoja_vida.isFuenteaagua());
				
				cell = row.createCell(51);
				cell.setCellValue(hoja_vida.isFuenteagas());
				
				cell = row.createCell(52);
				cell.setCellValue(hoja_vida.isFuenteavaporagua());
				
				cell = row.createCell(53);
				cell.setCellValue(hoja_vida.isFuenteaderivadospetroleo());
				
				cell = row.createCell(54);
				cell.setCellValue(hoja_vida.isFuenteaotros());
				
				cell = row.createCell(55);
				cell.setCellValue(hoja_vida.isEquipotipofijo());
				
				cell = row.createCell(56);
				cell.setCellValue(hoja_vida.isEquipotipoportatil());
				
				cell = row.createCell(57);
				cell.setCellValue(hoja_vida.isUsomedico());

				cell = row.createCell(58);
				cell.setCellValue(hoja_vida.isUsobasico());
				
				cell = row.createCell(59);
				cell.setCellValue(hoja_vida.isUsoapoyo());
				
				cell = row.createCell(60);
				cell.setCellValue(hoja_vida.isRiesgoi());
				
				cell = row.createCell(61);
				cell.setCellValue(hoja_vida.isRiesgoiia());
				
				cell = row.createCell(62);
				cell.setCellValue(hoja_vida.isRiesgoiib());
				
				cell = row.createCell(63);
				cell.setCellValue(hoja_vida.isRiesgoiii());
				
				cell = row.createCell(64);
				cell.setCellValue(hoja_vida.isClaseelectrico());
				
				cell = row.createCell(65);
				cell.setCellValue(hoja_vida.isClaseelectronico());
				
				cell = row.createCell(66);
				cell.setCellValue(hoja_vida.isClasemecanico());
				
				cell = row.createCell(67);
				cell.setCellValue(hoja_vida.isClaseelectromecanico());
				
				cell = row.createCell(68);
				cell.setCellValue(hoja_vida.isClasehidraulico());
				
				cell = row.createCell(69);
				cell.setCellValue(hoja_vida.isClaseneumatico());
				
				cell = row.createCell(70);
				cell.setCellValue(hoja_vida.isClasevapor());
				
				cell = row.createCell(71);
				cell.setCellValue(hoja_vida.isClasesolar());
				
				cell = row.createCell(72);
				cell.setCellValue(hoja_vida.isBiomedicdiagnostico());
				
				cell = row.createCell(73);
				cell.setCellValue(hoja_vida.isBiomedictratamiento());
				
				cell = row.createCell(74);
				cell.setCellValue(hoja_vida.isBiomedicrehabilitacion());
				
				cell = row.createCell(75);
				cell.setCellValue(hoja_vida.isBiomedicprevencion());
				
				cell = row.createCell(76);
				cell.setCellValue(hoja_vida.isBiomedicanalisis());
				
				if(hoja_vida.getEquipo().getPeriodicidad()==1) {
					cell = row.createCell(77);
					cell.setCellValue(0);
					
					cell = row.createCell(78);
					cell.setCellValue(0);
					
					cell = row.createCell(79);
					cell.setCellValue(0);
					
					cell = row.createCell(80);
					cell.setCellValue(1);
				}
				else if(hoja_vida.getEquipo().getPeriodicidad()==2){
					cell = row.createCell(77);
					cell.setCellValue(0);
					
					cell = row.createCell(78);
					cell.setCellValue(0);
					
					cell = row.createCell(79);
					cell.setCellValue(1);
					
					cell = row.createCell(80);
					cell.setCellValue(0);
					
				}
				else if(hoja_vida.getEquipo().getPeriodicidad()==3) {
					cell = row.createCell(77);
					cell.setCellValue(1);
					
					cell = row.createCell(78);
					cell.setCellValue(0);
					
					cell = row.createCell(79);
					cell.setCellValue(0);
					
					cell = row.createCell(80);
					cell.setCellValue(0);
				}
				else if(hoja_vida.getEquipo().getPeriodicidad()==4) {
					cell = row.createCell(77);
					cell.setCellValue(0);
					
					cell = row.createCell(78);
					cell.setCellValue(1);
					
					cell = row.createCell(79);
					cell.setCellValue(0);
					
					cell = row.createCell(80);
					cell.setCellValue(0);
				}
				cell = row.createCell(81);
				cell.setCellValue(hoja_vida.isMapropio());
				
				cell = row.createCell(82);
				cell.setCellValue(hoja_vida.isMacontratado());
				
				cell = row.createCell(83);
				cell.setCellValue(hoja_vida.isMacomodato());
				
				cell = row.createCell(84);
				cell.setCellValue(hoja_vida.isMagarantia());
				
				cell = row.createCell(85);
				cell.setCellValue(hoja_vida.isProphospital());
				
				cell = row.createCell(86);
				cell.setCellValue(hoja_vida.isPropproveedor());
				
				cell = row.createCell(87);
				cell.setCellValue(hoja_vida.isPropotro());
				
				cell = row.createCell(88);
				cell.setCellValue(hoja_vida.isManual_operacion());
				
				cell = row.createCell(89);
				cell.setCellValue(hoja_vida.isManual_tecnico());
				
				cell = row.createCell(90);
				cell.setCellValue(hoja_vida.isRequierecalibracion());
				
				cell = row.createCell(91);
				cell.setCellValue(hoja_vida.isNorequierecalibracion());
				
				cell = row.createCell(92);
				cell.setCellValue(hoja_vida.isPcalsemestral());
				
				cell = row.createCell(93);
				cell.setCellValue(hoja_vida.isPcalanual());
				
				cell = row.createCell(94);
				cell.setCellValue(hoja_vida.getAccesorio1());
				
				cell = row.createCell(95);
				cell.setCellValue(hoja_vida.getAccesorio2());
				
				cell = row.createCell(96);
				cell.setCellValue(hoja_vida.getAccesorio3());
				
				cell = row.createCell(97);
				cell.setCellValue(hoja_vida.getAccesorio4());
				
				
			}
			
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
