package com.HUSRTbdBiomedica.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Backup;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenarator {

	private String image = "./src/main/resources/static/images/Escudo_husrt.png";

	public ByteArrayOutputStream getPDFMSVDil(Reporte reporte, Equipo equipo, MantenimientoMSV mantenimientoMSV,
			Document document) throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirÃ¡ el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();
		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteTituloHospital1 = new Font();
		fuenteTituloHospital1.setSize(10);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);
		;

		Chunk rutinatitle = new Chunk("RUTINA DE MANTENIMIENTO PREVENTIVO");
		rutinatitle.setFont(fuenteTituloHospital);
		Chunk mintitle = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL TUNJA");
		mintitle.setFont(fuenteTituloHospital);
		Chunk niveltitle = new Chunk("III NIVEL DE ATENCIÓN ");
		niveltitle.setFont(fuenteTituloHospital);
		Chunk versionrutine = new Chunk("Versión: 02");
		versionrutine.setFont(negrita);
		Chunk codigorutine = new Chunk("Código: IB-F-26");
		codigorutine.setFont(negrita);

		Chunk daterutine = new Chunk("Fecha: 28/03/2022");
		daterutine.setFont(negrita);

		PdfPTable tabla1rutina = new PdfPTable(5);

		PdfPCell cellrutine = new PdfPCell(new Phrase(rutinatitle));
		cellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellrutine.setColspan(3);

		PdfPCell cellcode = new PdfPCell(new Phrase(codigorutine));
		cellcode.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellcode.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell cellmin = new PdfPCell(new Phrase(mintitle));
		cellmin.setColspan(3);
		cellmin.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellmin.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celllogo = new PdfPCell(logo);
		celllogo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celllogo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celllogo.setMinimumHeight(40);

		PdfPCell cellversion = new PdfPCell(new Phrase(versionrutine));
		cellversion.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellversion.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell cellnivel = new PdfPCell(new Phrase(niveltitle));
		cellnivel.setColspan(3);
		cellnivel.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellnivel.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell datecellrutine = new PdfPCell(new Phrase(daterutine));
		datecellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datecellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla1rutina.addCell(cellcode);
		tabla1rutina.addCell(cellmin);
		tabla1rutina.addCell(celllogo);
		tabla1rutina.addCell(cellversion);
		tabla1rutina.addCell(cellrutine);
		tabla1rutina.addCell(datecellrutine);

		tabla1rutina.setSpacingAfter(50);

		Chunk reportstyle = new Chunk("REPORTE No.");
		reportstyle.setFont(fuenteTituloHospital);

		Chunk numreportestyle = new Chunk(reporte.getNumero_reporte());
		numreportestyle.setFont(fontred);

		Chunk datestyle = new Chunk("FECHA: ");
		datestyle.setFont(fuenteTituloHospital);

		Chunk datertastyle = new Chunk(reporte.getFecha() + "");
		datertastyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteTituloHospital);

		Chunk equiportastyle = new Chunk(equipo.getNombre_Equipo());
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteTituloHospital);

		Chunk marcartastyle = new Chunk(equipo.getMarca());
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteTituloHospital);

		Chunk modelortastyle = new Chunk(equipo.getModelo());
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteTituloHospital);

		Chunk seriertastyle = new Chunk(equipo.getSerie());
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteTituloHospital);

		Chunk placartastyle = new Chunk(equipo.getPlaca_inventario());
		placartastyle.setFont(rta);

		String freq = new String();
		Chunk freqstyle = new Chunk("PERIODICIDAD: ");
		freqstyle.setFont(fuenteTituloHospital);

		Chunk freqrtastyle = new Chunk(equipo.getPeriodicidad() + "");
		freqrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteTituloHospital);

		Chunk servicertastyle = new Chunk(equipo.getServicio().getNombre_servicio());
		servicertastyle.setFont(rta);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteTituloHospital);

		Chunk ubrtastyle = new Chunk(equipo.getUbicacion());
		ubrtastyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(10);
		PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
		reportenm.setColspan(2);
		PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
		numreporte.setColspan(3);
		PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
		celdadatename.setColspan(2);
		PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
		celdadate.setColspan(3);

		PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
		equiponame.setColspan(2);
		PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
		equiporta.setColspan(3);
		PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
		marcaname.setColspan(2);
		PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
		marcarta.setColspan(3);

		PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
		modeloname.setColspan(2);
		PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
		modelorta.setColspan(3);
		PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
		seriename.setColspan(2);
		PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
		serierta.setColspan(3);

		PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
		placaname.setColspan(2);
		PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
		placarta.setColspan(3);
		PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
		pname.setColspan(2);
		PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
		prta.setColspan(3);

		PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
		servicioname.setColspan(2);
		PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
		serviciorta.setColspan(3);
		PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
		ubicacionname.setColspan(2);
		PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
		ubicacionrta.setColspan(3);

		tabla1rutina.setSpacingAfter(10);

		tabla2.addCell(reportenm);
		tabla2.addCell(numreporte);
		tabla2.addCell(celdadatename);
		tabla2.addCell(celdadate);

		tabla2.addCell(equiponame);
		tabla2.addCell(equiporta);
		tabla2.addCell(marcaname);
		tabla2.addCell(marcarta);

		tabla2.addCell(modeloname);
		tabla2.addCell(modelorta);
		tabla2.addCell(seriename);
		tabla2.addCell(serierta);

		tabla2.addCell(placaname);
		tabla2.addCell(placarta);
		tabla2.addCell(pname);
		tabla2.addCell(prta);

		tabla2.addCell(servicioname);
		tabla2.addCell(serviciorta);
		tabla2.addCell(ubicacionname);
		tabla2.addCell(ubicacionrta);

		tabla2.setSpacingAfter(10);

		// ________________________________________________________________

		PdfPTable tablaSimulador = new PdfPTable(6);

		Chunk cellTitle = new Chunk("DATOS DE SIMULADOR");

		cellTitle.setFont(fuenteTituloHospital);

		PdfPCell cellTableTitle = new PdfPCell(new Phrase(cellTitle));
		cellTableTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTableTitle.setColspan(6);

		Chunk textCellEqipo = new Chunk("Equipo:");
		textCellEqipo.setFont(fuenteTituloHospital);

		PdfPCell cellEquipo = new PdfPCell(new Phrase(textCellEqipo));
		cellEquipo.setMinimumHeight(30);
		cellEquipo.setColspan(1);

		PdfPCell cellEqipoR = new PdfPCell(new Phrase(new Chunk("SIMULADOR PACIENTE ")));
		cellEqipoR.setMinimumHeight(30);
		cellEqipoR.setColspan(1);

		Chunk textCellMarca = new Chunk("Marca:");
		textCellMarca.setFont(fuenteTituloHospital);

		PdfPCell cellMarca = new PdfPCell(new Phrase(textCellMarca));
		cellMarca.setMinimumHeight(30);
		cellMarca.setColspan(1);

		PdfPCell cellMarcaR = new PdfPCell(new Phrase(new Chunk("FLUKE")));
		cellMarcaR.setMinimumHeight(30);
		cellMarcaR.setColspan(1);

		Chunk textCellModelo = new Chunk("Modelo:");
		textCellModelo.setFont(fuenteTituloHospital);

		PdfPCell cellModelo = new PdfPCell(new Phrase(textCellModelo));
		cellModelo.setMinimumHeight(30);
		cellModelo.setColspan(1);

		PdfPCell cellModeloR = new PdfPCell(new Phrase(new Chunk("PROSIM 8")));
		cellModeloR.setMinimumHeight(30);
		cellModeloR.setColspan(1);

		Chunk textCellSerie = new Chunk("Serie:");
		textCellSerie.setFont(fuenteTituloHospital);

		PdfPCell cellSerie = new PdfPCell(new Phrase(textCellSerie));
		cellSerie.setMinimumHeight(30);
		cellSerie.setColspan(1);

		PdfPCell cellSerieR = new PdfPCell(new Phrase(new Chunk("209115")));
		cellSerieR.setMinimumHeight(30);
		cellSerieR.setColspan(1);

		Chunk textCellCertificado = new Chunk("Certificado de calibración:");
		textCellCertificado.setFont(fuenteTituloHospital);

		PdfPCell cellCertificado = new PdfPCell(new Phrase(textCellCertificado));
		cellCertificado.setMinimumHeight(30);
		cellCertificado.setColspan(1);

		PdfPCell cellCertificadoR = new PdfPCell(new Phrase(new Chunk("CG6609-23")));
		cellCertificadoR.setMinimumHeight(30);
		cellCertificadoR.setColspan(1);

		Chunk textCellFechaCal = new Chunk("Fecha de calibración:");
		textCellFechaCal.setFont(fuenteTituloHospital);

		PdfPCell cellFechaCal = new PdfPCell(new Phrase(textCellFechaCal));
		cellFechaCal.setMinimumHeight(30);
		cellFechaCal.setColspan(1);

		PdfPCell cellFechaCalR = new PdfPCell(new Phrase(new Chunk("15/09/2023")));
		cellFechaCalR.setMinimumHeight(30);
		cellFechaCalR.setColspan(1);

		tablaSimulador.addCell(cellTableTitle);

		tablaSimulador.addCell(cellEquipo);
		tablaSimulador.addCell(cellEqipoR);
		tablaSimulador.addCell(cellMarca);
		tablaSimulador.addCell(cellMarcaR);
		tablaSimulador.addCell(cellModelo);
		tablaSimulador.addCell(cellModeloR);

		tablaSimulador.addCell(cellSerie);
		tablaSimulador.addCell(cellSerieR);
		tablaSimulador.addCell(cellCertificado);
		tablaSimulador.addCell(cellCertificadoR);
		tablaSimulador.addCell(cellFechaCal);
		tablaSimulador.addCell(cellFechaCalR);

		tablaSimulador.setSpacingAfter(10);

		// ________________________________________________________________

		PdfPTable tablaDatosSimPaciente = new PdfPTable(8);

		Chunk TitleSimPaciente = new Chunk("DATOS DE SIMULACION PACIENTE");
		TitleSimPaciente.setFont(fuenteTituloHospital);

		PdfPCell cellTitleSimPaciente = new PdfPCell(new Phrase(TitleSimPaciente));
		cellTitleSimPaciente.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTitleSimPaciente.setColspan(8);

		PdfPCell cellVacia = new PdfPCell();
		cellVacia.setMinimumHeight(10);
		cellVacia.setColspan(1);

		Chunk textCellSPO2 = new Chunk("SPO2(%)");
		textCellSPO2.setFont(fuenteTituloHospital);

		PdfPCell cellSPO2 = new PdfPCell(new Phrase(textCellSPO2));
		cellSPO2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSPO2.setMinimumHeight(10);
		cellSPO2.setColspan(3);

		Chunk textCellECG = new Chunk("ECG(LPM)");
		textCellECG.setFont(fuenteTituloHospital);

		PdfPCell cellECG = new PdfPCell(new Phrase(textCellECG));
		cellECG.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellECG.setMinimumHeight(10);
		cellECG.setColspan(4);

		Chunk textCellValorColocado = new Chunk("Valor colocado");
		textCellValorColocado.setFont(fuenteTituloHospital);

		PdfPCell cellValorColocado = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado.setMinimumHeight(15);
		cellValorColocado.setColspan(1);

		Chunk textCell65 = new Chunk("65");
		textCell65.setFont(fuenteTituloHospital);

		PdfPCell cell65 = new PdfPCell(new Phrase(textCell65));
		cell65.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell65.setMinimumHeight(15);
		cell65.setColspan(1);

		Chunk textCell75 = new Chunk("75");
		textCell75.setFont(fuenteTituloHospital);

		PdfPCell cell75 = new PdfPCell(new Phrase(textCell75));
		cell75.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell75.setMinimumHeight(15);
		cell75.setColspan(1);

		Chunk textCell100 = new Chunk("100");
		textCell100.setFont(fuenteTituloHospital);

		PdfPCell cell100 = new PdfPCell(new Phrase(textCell100));
		cell100.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell100.setMinimumHeight(15);
		cell100.setColspan(1);

		Chunk textCell45 = new Chunk("45");
		textCell45.setFont(fuenteTituloHospital);

		PdfPCell cell45 = new PdfPCell(new Phrase(textCell45));
		cell45.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell45.setMinimumHeight(15);
		cell45.setColspan(1);

		Chunk textCell60 = new Chunk("60");
		textCell60.setFont(fuenteTituloHospital);

		PdfPCell cell60 = new PdfPCell(new Phrase(textCell60));
		cell60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell60.setMinimumHeight(15);
		cell60.setColspan(1);

		Chunk textCell120 = new Chunk("120");
		textCell120.setFont(fuenteTituloHospital);

		PdfPCell cell120 = new PdfPCell(new Phrase(textCell120));
		cell120.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell120.setMinimumHeight(15);
		cell120.setColspan(1);

		Chunk textCell180 = new Chunk("180");
		textCell180.setFont(fuenteTituloHospital);

		PdfPCell cell180 = new PdfPCell(new Phrase(textCell180));
		cell180.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell180.setMinimumHeight(15);
		cell180.setColspan(1);

		Chunk textCellValorMedido = new Chunk("Valor medido");
		textCellValorMedido.setFont(fuenteTituloHospital);

		PdfPCell cellValorMedido = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido.setMinimumHeight(15);
		cellValorMedido.setColspan(1);

		Chunk textCell65R = new Chunk(mantenimientoMSV.getV65medidoSPO2() + "");
		textCell65R.setFont(fuenteTituloHospital1);

		PdfPCell cell65R = new PdfPCell(new Phrase(textCell65R));
		cell65R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell65R.setMinimumHeight(15);
		cell65R.setColspan(1);

		Chunk textCell75R = new Chunk(mantenimientoMSV.getV75medidoSPO2() + "");
		textCell75R.setFont(fuenteTituloHospital1);

		PdfPCell cell75R = new PdfPCell(new Phrase(textCell75R));
		cell75R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell75R.setMinimumHeight(15);
		cell75R.setColspan(1);

		Chunk textCell100R = new Chunk(mantenimientoMSV.getV100medidoSPO2() + "");
		textCell100R.setFont(fuenteTituloHospital1);

		PdfPCell cell100R = new PdfPCell(new Phrase(textCell100R));
		cell100R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell100R.setMinimumHeight(15);
		cell100R.setColspan(1);

		Chunk textCell45R = new Chunk(mantenimientoMSV.getV45medidoECG() + "");
		textCell45R.setFont(fuenteTituloHospital1);

		PdfPCell cell45R = new PdfPCell(new Phrase(textCell45R));
		cell45R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell45R.setMinimumHeight(15);
		cell45R.setColspan(1);

		Chunk textCell60R = new Chunk(mantenimientoMSV.getV60medidoECG() + "");
		textCell60R.setFont(fuenteTituloHospital1);

		PdfPCell cell60R = new PdfPCell(new Phrase(textCell60R));
		cell60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell60R.setMinimumHeight(15);
		cell60R.setColspan(1);

		Chunk textCell120R = new Chunk(mantenimientoMSV.getV120medidoECG() + "");
		textCell120R.setFont(fuenteTituloHospital1);

		PdfPCell cell120R = new PdfPCell(new Phrase(textCell120R));
		cell120R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell120R.setMinimumHeight(15);
		cell120R.setColspan(1);

		Chunk textCell180R = new Chunk(mantenimientoMSV.getV180medidoECG() + "");
		textCell180R.setFont(fuenteTituloHospital1);

		PdfPCell cell180R = new PdfPCell(new Phrase(textCell180R));
		cell180R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell180R.setMinimumHeight(15);
		cell180R.setColspan(1);

		tablaDatosSimPaciente.addCell(cellTitleSimPaciente);
		tablaDatosSimPaciente.addCell(cellVacia);
		tablaDatosSimPaciente.addCell(cellSPO2);
		tablaDatosSimPaciente.addCell(cellECG);

		tablaDatosSimPaciente.addCell(cellValorColocado);
		tablaDatosSimPaciente.addCell(cell65);
		tablaDatosSimPaciente.addCell(cell75);
		tablaDatosSimPaciente.addCell(cell100);
		tablaDatosSimPaciente.addCell(cell45);
		tablaDatosSimPaciente.addCell(cell60);
		tablaDatosSimPaciente.addCell(cell120);
		tablaDatosSimPaciente.addCell(cell180);

		tablaDatosSimPaciente.addCell(cellValorMedido);
		tablaDatosSimPaciente.addCell(cell65R);
		tablaDatosSimPaciente.addCell(cell75R);
		tablaDatosSimPaciente.addCell(cell100R);
		tablaDatosSimPaciente.addCell(cell45R);
		tablaDatosSimPaciente.addCell(cell60R);
		tablaDatosSimPaciente.addCell(cell120R);
		tablaDatosSimPaciente.addCell(cell180R);

		tablaDatosSimPaciente.setSpacingAfter(10);

		// ___________________________________________________________________

		PdfPTable tablaNIBP = new PdfPTable(8);

		Chunk tablaDatosNIBP = new Chunk("NIBP (mmHg)");

		tablaDatosNIBP.setFont(fuenteTituloHospital);

		PdfPCell cellTitleNIBC = new PdfPCell(new Phrase(tablaDatosNIBP));
		cellTitleNIBC.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTitleNIBC.setColspan(8);

		PdfPCell cellVacia2 = new PdfPCell();
		cellVacia2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellVacia2.setMinimumHeight(15);
		cellVacia2.setColspan(1);

		Chunk textCellSistolica = new Chunk("SISTOLICA");
		textCellSistolica.setFont(fuenteTituloHospital);

		PdfPCell cellSistolica = new PdfPCell(new Phrase(textCellSistolica));
		cellSistolica.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSistolica.setMinimumHeight(15);
		cellSistolica.setColspan(1);

		Chunk textCellDiastolica = new Chunk("DIASTOLICA");
		textCellDiastolica.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica = new PdfPCell(new Phrase(textCellDiastolica));
		cellDiastolica.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica.setMinimumHeight(15);
		cellDiastolica.setColspan(1);

		Chunk textCellMedia = new Chunk("MEDIA");
		textCellMedia.setFont(fuenteTituloHospital);

		PdfPCell cellMedia = new PdfPCell(new Phrase(textCellMedia));
		cellMedia.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia.setMinimumHeight(15);
		cellMedia.setColspan(1);

		PdfPCell cellvacia3 = new PdfPCell();
		cellvacia3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellvacia3.setMinimumHeight(15);
		cellvacia3.setColspan(1);

		PdfPCell cellSistolica2 = new PdfPCell(new Phrase(textCellSistolica));
		cellSistolica2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSistolica2.setMinimumHeight(15);
		cellSistolica2.setColspan(1);

		PdfPCell cellDiastolica2 = new PdfPCell(new Phrase(textCellDiastolica));
		cellDiastolica2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica2.setMinimumHeight(15);
		cellDiastolica2.setColspan(1);

		PdfPCell cellMedia2 = new PdfPCell(new Phrase(textCellMedia));
		cellMedia2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia2.setMinimumHeight(15);
		cellMedia2.setColspan(1);

		PdfPCell cellValorColocado2 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado2.setMinimumHeight(15);
		cellValorColocado2.setColspan(1);

		Chunk textCellSistolica120 = new Chunk("120");
		textCellSistolica120.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica120 = new PdfPCell(new Phrase(textCellSistolica120));
		cellSisitolica120.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica120.setMinimumHeight(15);
		cellSisitolica120.setColspan(1);

		Chunk textCellDiastolica80 = new Chunk("80");
		textCellDiastolica80.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica80 = new PdfPCell(new Phrase(textCellDiastolica80));
		cellDiastolica80.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica80.setMinimumHeight(15);
		cellDiastolica80.setColspan(1);

		Chunk textCellMedia92 = new Chunk("92");
		textCellMedia92.setFont(fuenteTituloHospital);

		PdfPCell cellMedia92 = new PdfPCell(new Phrase(textCellMedia92));
		cellMedia92.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia92.setMinimumHeight(15);
		cellMedia92.setColspan(1);

		PdfPCell cellValorColocado3 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado3.setMinimumHeight(15);
		cellValorColocado3.setColspan(1);

		Chunk textCellSistolica200 = new Chunk("200");
		textCellSistolica200.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica200 = new PdfPCell(new Phrase(textCellSistolica200));
		cellSisitolica200.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica200.setMinimumHeight(15);
		cellSisitolica200.setColspan(1);

		Chunk textCellDiastolica150 = new Chunk("150");
		textCellDiastolica150.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica150 = new PdfPCell(new Phrase(textCellDiastolica150));
		cellDiastolica150.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica150.setMinimumHeight(15);
		cellDiastolica150.setColspan(1);

		Chunk textCellMedia167 = new Chunk("167");
		textCellMedia167.setFont(fuenteTituloHospital);

		PdfPCell cellMedia167 = new PdfPCell(new Phrase(textCellMedia167));
		cellMedia167.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia167.setMinimumHeight(15);
		cellMedia167.setColspan(1);

		PdfPCell cellValorMedido2 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido2.setMinimumHeight(15);
		cellValorMedido2.setColspan(1);

		Chunk textCellSistolica120R = new Chunk(mantenimientoMSV.getV120medidoSistolica() + "");
		textCellSistolica120R.setFont(fuenteTituloHospital1);

		PdfPCell cellSisitolica120R = new PdfPCell(new Phrase(textCellSistolica120R));
		cellSisitolica120R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica120R.setMinimumHeight(15);
		cellSisitolica120R.setColspan(1);

		Chunk textCellDiastolica80R = new Chunk(mantenimientoMSV.getV80medidoDiastolica() + "");
		textCellDiastolica80R.setFont(fuenteTituloHospital1);

		PdfPCell cellDiastolica80R = new PdfPCell(new Phrase(textCellDiastolica80R));
		cellDiastolica80R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica80R.setMinimumHeight(15);
		cellDiastolica80R.setColspan(1);

		Chunk textCellMedia92R = new Chunk(mantenimientoMSV.getV92medidoMedia() + "");
		textCellMedia92R.setFont(fuenteTituloHospital1);

		PdfPCell cellMedia92R = new PdfPCell(new Phrase(textCellMedia92R));
		cellMedia92R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia92R.setMinimumHeight(15);
		cellMedia92R.setColspan(1);

		PdfPCell cellValorMedido3 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido3.setMinimumHeight(15);
		cellValorMedido3.setColspan(1);

		Chunk textCellSistolica200R = new Chunk(mantenimientoMSV.getV200medidoSistolica() + "");
		textCellSistolica200R.setFont(fuenteTituloHospital1);

		PdfPCell cellSisitolica200R = new PdfPCell(new Phrase(textCellSistolica200R));
		cellSisitolica200R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica200R.setMinimumHeight(15);
		cellSisitolica200R.setColspan(1);

		Chunk textCellDiastolica150R = new Chunk(mantenimientoMSV.getV150medidoDiastolica() + "");
		textCellDiastolica150R.setFont(fuenteTituloHospital1);

		PdfPCell cellDiastolica150R = new PdfPCell(new Phrase(textCellDiastolica150R));
		cellDiastolica150R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica150R.setMinimumHeight(15);
		cellDiastolica150R.setColspan(1);

		Chunk textCellMedia167R = new Chunk(mantenimientoMSV.getV167medidoMedia() + "");
		textCellMedia167R.setFont(fuenteTituloHospital1);

		PdfPCell cellMedia167R = new PdfPCell(new Phrase(textCellMedia167R));
		cellMedia167R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia167R.setMinimumHeight(15);
		cellMedia167R.setColspan(1);

		PdfPCell cellValorColocado4 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado4.setMinimumHeight(15);
		cellValorColocado4.setColspan(1);

		Chunk textCellSistolica60 = new Chunk("60");
		textCellSistolica60.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica60 = new PdfPCell(new Phrase(textCellSistolica60));
		cellSisitolica60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica60.setMinimumHeight(15);
		cellSisitolica60.setColspan(1);

		Chunk textCellDiastolica30 = new Chunk("30");
		textCellDiastolica30.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica30 = new PdfPCell(new Phrase(textCellDiastolica30));
		cellDiastolica30.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica30.setMinimumHeight(15);
		cellDiastolica30.setColspan(1);

		Chunk textCellMedia40 = new Chunk("40");
		textCellMedia40.setFont(fuenteTituloHospital);

		PdfPCell cellMedia40 = new PdfPCell(new Phrase(textCellMedia40));
		cellMedia40.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia40.setMinimumHeight(15);
		cellMedia40.setColspan(1);

		PdfPCell cellValorColocado5 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado5.setMinimumHeight(15);
		cellValorColocado5.setColspan(1);

		Chunk textCellSistolica80 = new Chunk("80");
		textCellSistolica80.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica80 = new PdfPCell(new Phrase(textCellSistolica80));
		cellSisitolica80.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica80.setMinimumHeight(15);
		cellSisitolica80.setColspan(1);

		Chunk textCellDiastolica50 = new Chunk("50");
		textCellDiastolica50.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica50 = new PdfPCell(new Phrase(textCellDiastolica50));
		cellDiastolica50.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica50.setMinimumHeight(15);
		cellDiastolica50.setColspan(1);

		Chunk textCellMedia60 = new Chunk("60");
		textCellMedia60.setFont(fuenteTituloHospital);

		PdfPCell cellMedia60 = new PdfPCell(new Phrase(textCellMedia60));
		cellMedia60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia60.setMinimumHeight(15);
		cellMedia60.setColspan(1);

		PdfPCell cellValorMedido4 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido4.setMinimumHeight(15);
		cellValorMedido4.setColspan(1);

		Chunk textCellSistolica60R = new Chunk(mantenimientoMSV.getV60medidoSistolica() + "");
		textCellSistolica60R.setFont(fuenteTituloHospital1);

		PdfPCell cellSisitolica60R = new PdfPCell(new Phrase(textCellSistolica60R));
		cellSisitolica60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica60R.setMinimumHeight(15);
		cellSisitolica60R.setColspan(1);

		Chunk textCellDiastolica30R = new Chunk(mantenimientoMSV.getV30medidoDiastolica() + "");
		textCellDiastolica30R.setFont(fuenteTituloHospital1);

		PdfPCell cellDiastolica30R = new PdfPCell(new Phrase(textCellDiastolica30R));
		cellDiastolica30R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica30R.setMinimumHeight(15);
		cellDiastolica30R.setColspan(1);

		Chunk textCellMedia40R = new Chunk(mantenimientoMSV.getV40medidoMedia() + "");
		textCellMedia40R.setFont(fuenteTituloHospital1);

		PdfPCell cellMedia40R = new PdfPCell(new Phrase(textCellMedia40R));
		cellMedia40R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia40R.setMinimumHeight(15);
		cellMedia40R.setColspan(1);

		PdfPCell cellValorMedido5 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido5.setMinimumHeight(15);
		cellValorMedido5.setColspan(1);

		Chunk textCellSistolica80R = new Chunk(mantenimientoMSV.getV80medidoSistolica() + "");
		textCellSistolica80R.setFont(fuenteTituloHospital1);

		PdfPCell cellSisitolica80R = new PdfPCell(new Phrase(textCellSistolica80R));
		cellSisitolica80R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica80R.setMinimumHeight(15);
		cellSisitolica80R.setColspan(1);

		Chunk textCellDiastolica50R = new Chunk(mantenimientoMSV.getV50medidoDiastolica() + "");
		textCellDiastolica50R.setFont(fuenteTituloHospital1);

		PdfPCell cellDiastolica50R = new PdfPCell(new Phrase(textCellDiastolica50R));
		cellDiastolica50R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica50R.setMinimumHeight(15);
		cellDiastolica50R.setColspan(1);

		Chunk textCellMedia60R = new Chunk(mantenimientoMSV.getV60medidoMedia() + "");
		textCellMedia60R.setFont(fuenteTituloHospital1);

		PdfPCell cellMedia60R = new PdfPCell(new Phrase(textCellMedia60R));
		cellMedia60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia60R.setMinimumHeight(15);
		cellMedia60R.setColspan(1);

		tablaNIBP.addCell(cellTitleNIBC);
		tablaNIBP.addCell(cellVacia2);
		tablaNIBP.addCell(cellSistolica);
		tablaNIBP.addCell(cellDiastolica);
		tablaNIBP.addCell(cellMedia);
		tablaNIBP.addCell(cellvacia3);
		tablaNIBP.addCell(cellSistolica2);
		tablaNIBP.addCell(cellDiastolica2);
		tablaNIBP.addCell(cellMedia2);
		tablaNIBP.addCell(cellValorColocado2);
		tablaNIBP.addCell(cellSisitolica120);
		tablaNIBP.addCell(cellDiastolica80);
		tablaNIBP.addCell(cellMedia92);
		tablaNIBP.addCell(cellValorColocado3);
		tablaNIBP.addCell(cellSisitolica200);
		tablaNIBP.addCell(cellDiastolica150);
		tablaNIBP.addCell(cellMedia167);
		tablaNIBP.addCell(cellValorMedido2);
		tablaNIBP.addCell(cellSisitolica120R);
		tablaNIBP.addCell(cellDiastolica80R);
		tablaNIBP.addCell(cellMedia92R);
		tablaNIBP.addCell(cellValorMedido3);
		tablaNIBP.addCell(cellSisitolica200R);
		tablaNIBP.addCell(cellDiastolica150R);
		tablaNIBP.addCell(cellMedia167R);

		tablaNIBP.addCell(cellValorColocado4);
		tablaNIBP.addCell(cellSisitolica60);
		tablaNIBP.addCell(cellDiastolica30);
		tablaNIBP.addCell(cellMedia40);
		tablaNIBP.addCell(cellValorColocado5);
		tablaNIBP.addCell(cellSisitolica80);
		tablaNIBP.addCell(cellDiastolica50);
		tablaNIBP.addCell(cellMedia60);
		tablaNIBP.addCell(cellValorMedido4);
		tablaNIBP.addCell(cellSisitolica60R);
		tablaNIBP.addCell(cellDiastolica30R);
		tablaNIBP.addCell(cellMedia40R);
		tablaNIBP.addCell(cellValorMedido5);
		tablaNIBP.addCell(cellSisitolica80R);
		tablaNIBP.addCell(cellDiastolica50R);
		tablaNIBP.addCell(cellMedia60R);

		tablaNIBP.setSpacingAfter(10);

		// --------------------------------------------------------------------

		PdfPTable tabla4rutina = new PdfPTable(8);

		Chunk materialname = new Chunk("MATERIAL CONSUMIBLE");
		materialname.setFont(fuenteTituloHospital);

		Chunk toolsname = new Chunk("HERRAMIENTAS Y EQUIPOS");
		toolsname.setFont(fuenteTituloHospital);

		PdfPCell materialnamecell = new PdfPCell(new Phrase(materialname));
		materialnamecell.setColspan(4);

		PdfPCell toolnamecell = new PdfPCell(new Phrase(toolsname));
		toolnamecell.setColspan(4);

		PdfPCell materialcell = new PdfPCell();
		materialcell.setMinimumHeight(60);
		materialcell.setColspan(4);

		PdfPCell toolcell = new PdfPCell();
		toolcell.setMinimumHeight(60);
		toolcell.setColspan(4);

		tabla4rutina.addCell(materialnamecell);
		tabla4rutina.addCell(toolnamecell);

		tabla4rutina.addCell(materialcell);
		tabla4rutina.addCell(toolcell);

		tabla4rutina.setSpacingAfter(10);

		// ___________________________________________________________________

		PdfPTable tabla5rutina = new PdfPTable(1);
		Chunk obtitlestyle = new Chunk("OBSERVACIONES");
		obtitlestyle.setFont(fuenteTituloHospital);

		PdfPCell Obser = new PdfPCell(new Phrase(obtitlestyle));
		Obser.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla5rutina.addCell(Obser);

		Chunk obrtastyle = new Chunk();
		obrtastyle.setFont(rta);

		Obser = new PdfPCell(new Phrase(obrtastyle));
		Obser.setMinimumHeight(60);
		tabla5rutina.addCell(Obser);

		tabla5rutina.setSpacingAfter(10);

		PdfPTable tabla6rutina = new PdfPTable(2);

		Phrase tiempoejec = new Phrase();
		Chunk tiempotext = new Chunk("TIEMPO DE EJECUCIÓN (APROX. ");
		tiempotext.setFont(fuenteTituloHospital);
		tiempoejec.add(tiempotext);
		tiempotext = new Chunk();
		tiempotext.setFont(fuenteTituloHospital);
		tiempoejec.add(tiempotext);

		Obser = new PdfPCell(tiempoejec);
		tabla6rutina.addCell(Obser);
		tiempotext = new Chunk();
		Obser = new PdfPCell(new Phrase(tiempotext));
		tabla6rutina.addCell(Obser);

		Chunk realizadostyle = new Chunk("FIRMA DE QUIEN REALIZA");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk recibidostyle = new Chunk("FIRMA DE QUIEN RECIBE");
		recibidostyle.setFont(fuenteTituloHospital);

		PdfPCell realizado = new PdfPCell();
		realizado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		realizado.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		realizado.addElement(new Chunk("_____________________"));
		realizado.addElement(realizadostyle);

		PdfPCell recibido = new PdfPCell();
		recibido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		recibido.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		recibido.addElement(new Chunk("_____________________"));
		recibido.addElement(recibidostyle);

		realizado.setMinimumHeight(50);

		tabla6rutina.addCell(realizado);
		tabla6rutina.addCell(recibido);

		document.add(tabla1rutina);
		document.add(tabla2);
		document.add(tablaSimulador);
		document.add(tablaDatosSimPaciente);
		document.add(tablaNIBP);
		document.add(tabla4rutina);
		document.add(tabla5rutina);
		document.add(tabla6rutina);

		// contentByte.endText();
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getPDFMSV() throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirÃ¡ el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();
		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);
		;

		Chunk rutinatitle = new Chunk("RUTINA DE MANTENIMIENTO PREVENTIVO");
		rutinatitle.setFont(fuenteTituloHospital);
		Chunk mintitle = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL TUNJA");
		mintitle.setFont(fuenteTituloHospital);
		Chunk niveltitle = new Chunk("III NIVEL DE ATENCIÓN ");
		niveltitle.setFont(fuenteTituloHospital);
		Chunk versionrutine = new Chunk("Versión: 02");
		versionrutine.setFont(negrita);
		Chunk codigorutine = new Chunk("Código: IB-F-26");
		codigorutine.setFont(negrita);

		Chunk daterutine = new Chunk("Fecha: 28/03/2022");
		daterutine.setFont(negrita);

		PdfPTable tabla1rutina = new PdfPTable(5);

		PdfPCell cellrutine = new PdfPCell(new Phrase(rutinatitle));
		cellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellrutine.setColspan(3);

		PdfPCell cellcode = new PdfPCell(new Phrase(codigorutine));
		cellcode.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellcode.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell cellmin = new PdfPCell(new Phrase(mintitle));
		cellmin.setColspan(3);
		cellmin.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellmin.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celllogo = new PdfPCell(logo);
		celllogo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celllogo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celllogo.setMinimumHeight(40);

		PdfPCell cellversion = new PdfPCell(new Phrase(versionrutine));
		cellversion.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellversion.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell cellnivel = new PdfPCell(new Phrase(niveltitle));
		cellnivel.setColspan(3);
		cellnivel.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellnivel.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell datecellrutine = new PdfPCell(new Phrase(daterutine));
		datecellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datecellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla1rutina.addCell(cellcode);
		tabla1rutina.addCell(cellmin);
		tabla1rutina.addCell(celllogo);
		tabla1rutina.addCell(cellversion);
		tabla1rutina.addCell(cellrutine);
		tabla1rutina.addCell(datecellrutine);

		tabla1rutina.setSpacingAfter(50);

		Chunk reportstyle = new Chunk("REPORTE No.");
		reportstyle.setFont(fuenteTituloHospital);

		Chunk numreportestyle = new Chunk();
		numreportestyle.setFont(fontred);

		Chunk datestyle = new Chunk("FECHA: ");
		datestyle.setFont(fuenteTituloHospital);

		Chunk datertastyle = new Chunk();
		datertastyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteTituloHospital);

		Chunk equiportastyle = new Chunk();
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteTituloHospital);

		Chunk marcartastyle = new Chunk();
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteTituloHospital);

		Chunk modelortastyle = new Chunk();
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteTituloHospital);

		Chunk seriertastyle = new Chunk();
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteTituloHospital);

		Chunk placartastyle = new Chunk();
		placartastyle.setFont(rta);

		String freq = new String();
		Chunk freqstyle = new Chunk("PERIODICIDAD: ");
		freqstyle.setFont(fuenteTituloHospital);

		Chunk freqrtastyle = new Chunk(freq);
		freqrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteTituloHospital);

		Chunk servicertastyle = new Chunk();
		servicertastyle.setFont(rta);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteTituloHospital);

		Chunk ubrtastyle = new Chunk();
		ubrtastyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(10);
		PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
		reportenm.setColspan(2);
		PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
		numreporte.setColspan(3);
		PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
		celdadatename.setColspan(2);
		PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
		celdadate.setColspan(3);

		PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
		equiponame.setColspan(2);
		PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
		equiporta.setColspan(3);
		PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
		marcaname.setColspan(2);
		PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
		marcarta.setColspan(3);

		PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
		modeloname.setColspan(2);
		PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
		modelorta.setColspan(3);
		PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
		seriename.setColspan(2);
		PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
		serierta.setColspan(3);

		PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
		placaname.setColspan(2);
		PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
		placarta.setColspan(3);
		PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
		pname.setColspan(2);
		PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
		prta.setColspan(3);

		PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
		servicioname.setColspan(2);
		PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
		serviciorta.setColspan(3);
		PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
		ubicacionname.setColspan(2);
		PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
		ubicacionrta.setColspan(3);

		tabla1rutina.setSpacingAfter(10);

		tabla2.addCell(reportenm);
		tabla2.addCell(numreporte);
		tabla2.addCell(celdadatename);
		tabla2.addCell(celdadate);

		tabla2.addCell(equiponame);
		tabla2.addCell(equiporta);
		tabla2.addCell(marcaname);
		tabla2.addCell(marcarta);

		tabla2.addCell(modeloname);
		tabla2.addCell(modelorta);
		tabla2.addCell(seriename);
		tabla2.addCell(serierta);

		tabla2.addCell(placaname);
		tabla2.addCell(placarta);
		tabla2.addCell(pname);
		tabla2.addCell(prta);

		tabla2.addCell(servicioname);
		tabla2.addCell(serviciorta);
		tabla2.addCell(ubicacionname);
		tabla2.addCell(ubicacionrta);

		tabla2.setSpacingAfter(10);

		// ________________________________________________________________

		PdfPTable tablaSimulador = new PdfPTable(6);

		Chunk cellTitle = new Chunk("DATOS DE SIMULADOR");

		cellTitle.setFont(fuenteTituloHospital);

		PdfPCell cellTableTitle = new PdfPCell(new Phrase(cellTitle));
		cellTableTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTableTitle.setColspan(6);

		Chunk textCellEqipo = new Chunk("Equipo:");
		textCellEqipo.setFont(fuenteTituloHospital);

		PdfPCell cellEquipo = new PdfPCell(new Phrase(textCellEqipo));
		cellEquipo.setMinimumHeight(30);
		cellEquipo.setColspan(1);

		PdfPCell cellEqipoR = new PdfPCell(new Phrase(new Chunk("SIMULADOR PACIENTE ")));
		cellEqipoR.setMinimumHeight(30);
		cellEqipoR.setColspan(1);

		Chunk textCellMarca = new Chunk("Marca:");
		textCellMarca.setFont(fuenteTituloHospital);

		PdfPCell cellMarca = new PdfPCell(new Phrase(textCellMarca));
		cellMarca.setMinimumHeight(30);
		cellMarca.setColspan(1);

		PdfPCell cellMarcaR = new PdfPCell(new Phrase(new Chunk("FLUKE")));
		cellMarcaR.setMinimumHeight(30);
		cellMarcaR.setColspan(1);

		Chunk textCellModelo = new Chunk("Modelo:");
		textCellModelo.setFont(fuenteTituloHospital);

		PdfPCell cellModelo = new PdfPCell(new Phrase(textCellModelo));
		cellModelo.setMinimumHeight(30);
		cellModelo.setColspan(1);

		PdfPCell cellModeloR = new PdfPCell(new Phrase(new Chunk("PROSIM 8")));
		cellModeloR.setMinimumHeight(30);
		cellModeloR.setColspan(1);

		Chunk textCellSerie = new Chunk("Serie:");
		textCellSerie.setFont(fuenteTituloHospital);

		PdfPCell cellSerie = new PdfPCell(new Phrase(textCellSerie));
		cellSerie.setMinimumHeight(30);
		cellSerie.setColspan(1);

		PdfPCell cellSerieR = new PdfPCell(new Phrase(new Chunk("209115")));
		cellSerieR.setMinimumHeight(30);
		cellSerieR.setColspan(1);

		Chunk textCellCertificado = new Chunk("Certificado de calibración:");
		textCellCertificado.setFont(fuenteTituloHospital);

		PdfPCell cellCertificado = new PdfPCell(new Phrase(textCellCertificado));
		cellCertificado.setMinimumHeight(30);
		cellCertificado.setColspan(1);

		PdfPCell cellCertificadoR = new PdfPCell(new Phrase(new Chunk("CG6609-23")));
		cellCertificadoR.setMinimumHeight(30);
		cellCertificadoR.setColspan(1);

		Chunk textCellFechaCal = new Chunk("Fecha de calibración:");
		textCellFechaCal.setFont(fuenteTituloHospital);

		PdfPCell cellFechaCal = new PdfPCell(new Phrase(textCellFechaCal));
		cellFechaCal.setMinimumHeight(30);
		cellFechaCal.setColspan(1);

		PdfPCell cellFechaCalR = new PdfPCell(new Phrase(new Chunk("15/09/2023")));
		cellFechaCalR.setMinimumHeight(30);
		cellFechaCalR.setColspan(1);

		tablaSimulador.addCell(cellTableTitle);

		tablaSimulador.addCell(cellEquipo);
		tablaSimulador.addCell(cellEqipoR);
		tablaSimulador.addCell(cellMarca);
		tablaSimulador.addCell(cellMarcaR);
		tablaSimulador.addCell(cellModelo);
		tablaSimulador.addCell(cellModeloR);

		tablaSimulador.addCell(cellSerie);
		tablaSimulador.addCell(cellSerieR);
		tablaSimulador.addCell(cellCertificado);
		tablaSimulador.addCell(cellCertificadoR);
		tablaSimulador.addCell(cellFechaCal);
		tablaSimulador.addCell(cellFechaCalR);

		tablaSimulador.setSpacingAfter(10);

		// ________________________________________________________________

		PdfPTable tablaDatosSimPaciente = new PdfPTable(8);

		Chunk TitleSimPaciente = new Chunk("DATOS DE SIMULACION PACIENTE");
		TitleSimPaciente.setFont(fuenteTituloHospital);

		PdfPCell cellTitleSimPaciente = new PdfPCell(new Phrase(TitleSimPaciente));
		cellTitleSimPaciente.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTitleSimPaciente.setColspan(8);

		PdfPCell cellVacia = new PdfPCell();
		cellVacia.setMinimumHeight(10);
		cellVacia.setColspan(1);

		Chunk textCellSPO2 = new Chunk("SPO2(%)");
		textCellSPO2.setFont(fuenteTituloHospital);

		PdfPCell cellSPO2 = new PdfPCell(new Phrase(textCellSPO2));
		cellSPO2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSPO2.setMinimumHeight(10);
		cellSPO2.setColspan(3);

		Chunk textCellECG = new Chunk("ECG(LPM)");
		textCellECG.setFont(fuenteTituloHospital);

		PdfPCell cellECG = new PdfPCell(new Phrase(textCellECG));
		cellECG.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellECG.setMinimumHeight(10);
		cellECG.setColspan(4);

		Chunk textCellValorColocado = new Chunk("Valor colocado");
		textCellValorColocado.setFont(fuenteTituloHospital);

		PdfPCell cellValorColocado = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado.setMinimumHeight(15);
		cellValorColocado.setColspan(1);

		Chunk textCell65 = new Chunk("65");
		textCell65.setFont(fuenteTituloHospital);

		PdfPCell cell65 = new PdfPCell(new Phrase(textCell65));
		cell65.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell65.setMinimumHeight(15);
		cell65.setColspan(1);

		Chunk textCell75 = new Chunk("75");
		textCell75.setFont(fuenteTituloHospital);

		PdfPCell cell75 = new PdfPCell(new Phrase(textCell75));
		cell75.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell75.setMinimumHeight(15);
		cell75.setColspan(1);

		Chunk textCell100 = new Chunk("100");
		textCell100.setFont(fuenteTituloHospital);

		PdfPCell cell100 = new PdfPCell(new Phrase(textCell100));
		cell100.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell100.setMinimumHeight(15);
		cell100.setColspan(1);

		Chunk textCell45 = new Chunk("45");
		textCell45.setFont(fuenteTituloHospital);

		PdfPCell cell45 = new PdfPCell(new Phrase(textCell45));
		cell45.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell45.setMinimumHeight(15);
		cell45.setColspan(1);

		Chunk textCell60 = new Chunk("60");
		textCell60.setFont(fuenteTituloHospital);

		PdfPCell cell60 = new PdfPCell(new Phrase(textCell60));
		cell60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell60.setMinimumHeight(15);
		cell60.setColspan(1);

		Chunk textCell120 = new Chunk("120");
		textCell120.setFont(fuenteTituloHospital);

		PdfPCell cell120 = new PdfPCell(new Phrase(textCell120));
		cell120.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell120.setMinimumHeight(15);
		cell120.setColspan(1);

		Chunk textCell180 = new Chunk("180");
		textCell180.setFont(fuenteTituloHospital);

		PdfPCell cell180 = new PdfPCell(new Phrase(textCell180));
		cell180.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell180.setMinimumHeight(15);
		cell180.setColspan(1);

		Chunk textCellValorMedido = new Chunk("Valor medido");
		textCellValorMedido.setFont(fuenteTituloHospital);

		PdfPCell cellValorMedido = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido.setMinimumHeight(15);
		cellValorMedido.setColspan(1);

		Chunk textCell65R = new Chunk("");

		PdfPCell cell65R = new PdfPCell(new Phrase(textCell65R));
		cell65R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell65R.setMinimumHeight(15);
		cell65R.setColspan(1);

		Chunk textCell75R = new Chunk("");

		PdfPCell cell75R = new PdfPCell(new Phrase(textCell75R));
		cell75R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell75R.setMinimumHeight(15);
		cell75R.setColspan(1);

		Chunk textCell100R = new Chunk("");

		PdfPCell cell100R = new PdfPCell(new Phrase(textCell100R));
		cell100R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell100R.setMinimumHeight(15);
		cell100R.setColspan(1);

		Chunk textCell45R = new Chunk("");

		PdfPCell cell45R = new PdfPCell(new Phrase(textCell45R));
		cell45R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell45R.setMinimumHeight(15);
		cell45R.setColspan(1);

		Chunk textCell60R = new Chunk("");

		PdfPCell cell60R = new PdfPCell(new Phrase(textCell60R));
		cell60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell60R.setMinimumHeight(15);
		cell60R.setColspan(1);

		Chunk textCell120R = new Chunk("");

		PdfPCell cell120R = new PdfPCell(new Phrase(textCell120R));
		cell120R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell120R.setMinimumHeight(15);
		cell120R.setColspan(1);

		Chunk textCell180R = new Chunk("");

		PdfPCell cell180R = new PdfPCell(new Phrase(textCell180R));
		cell180R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell180R.setMinimumHeight(15);
		cell180R.setColspan(1);

		tablaDatosSimPaciente.addCell(cellTitleSimPaciente);
		tablaDatosSimPaciente.addCell(cellVacia);
		tablaDatosSimPaciente.addCell(cellSPO2);
		tablaDatosSimPaciente.addCell(cellECG);

		tablaDatosSimPaciente.addCell(cellValorColocado);
		tablaDatosSimPaciente.addCell(cell65);
		tablaDatosSimPaciente.addCell(cell75);
		tablaDatosSimPaciente.addCell(cell100);
		tablaDatosSimPaciente.addCell(cell45);
		tablaDatosSimPaciente.addCell(cell60);
		tablaDatosSimPaciente.addCell(cell120);
		tablaDatosSimPaciente.addCell(cell180);

		tablaDatosSimPaciente.addCell(cellValorMedido);
		tablaDatosSimPaciente.addCell(cell65R);
		tablaDatosSimPaciente.addCell(cell75R);
		tablaDatosSimPaciente.addCell(cell100R);
		tablaDatosSimPaciente.addCell(cell45R);
		tablaDatosSimPaciente.addCell(cell60R);
		tablaDatosSimPaciente.addCell(cell120R);
		tablaDatosSimPaciente.addCell(cell180R);

		tablaDatosSimPaciente.setSpacingAfter(10);

		// ___________________________________________________________________

		PdfPTable tablaNIBP = new PdfPTable(8);

		Chunk tablaDatosNIBP = new Chunk("NIBP (mmHg)");

		tablaDatosNIBP.setFont(fuenteTituloHospital);

		PdfPCell cellTitleNIBC = new PdfPCell(new Phrase(tablaDatosNIBP));
		cellTitleNIBC.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellTitleNIBC.setColspan(8);

		PdfPCell cellVacia2 = new PdfPCell();
		cellVacia2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellVacia2.setMinimumHeight(15);
		cellVacia2.setColspan(1);

		Chunk textCellSistolica = new Chunk("SISTOLICA");
		textCellSistolica.setFont(fuenteTituloHospital);

		PdfPCell cellSistolica = new PdfPCell(new Phrase(textCellSistolica));
		cellSistolica.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSistolica.setMinimumHeight(15);
		cellSistolica.setColspan(1);

		Chunk textCellDiastolica = new Chunk("DIASTOLICA");
		textCellDiastolica.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica = new PdfPCell(new Phrase(textCellDiastolica));
		cellDiastolica.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica.setMinimumHeight(15);
		cellDiastolica.setColspan(1);

		Chunk textCellMedia = new Chunk("MEDIA");
		textCellMedia.setFont(fuenteTituloHospital);

		PdfPCell cellMedia = new PdfPCell(new Phrase(textCellMedia));
		cellMedia.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia.setMinimumHeight(15);
		cellMedia.setColspan(1);

		PdfPCell cellvacia3 = new PdfPCell();
		cellvacia3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellvacia3.setMinimumHeight(15);
		cellvacia3.setColspan(1);

		PdfPCell cellSistolica2 = new PdfPCell(new Phrase(textCellSistolica));
		cellSistolica2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSistolica2.setMinimumHeight(15);
		cellSistolica2.setColspan(1);

		PdfPCell cellDiastolica2 = new PdfPCell(new Phrase(textCellDiastolica));
		cellDiastolica2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica2.setMinimumHeight(15);
		cellDiastolica2.setColspan(1);

		PdfPCell cellMedia2 = new PdfPCell(new Phrase(textCellMedia));
		cellMedia2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia2.setMinimumHeight(15);
		cellMedia2.setColspan(1);

		PdfPCell cellValorColocado2 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado2.setMinimumHeight(15);
		cellValorColocado2.setColspan(1);

		Chunk textCellSistolica120 = new Chunk("120");
		textCellSistolica120.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica120 = new PdfPCell(new Phrase(textCellSistolica120));
		cellSisitolica120.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica120.setMinimumHeight(15);
		cellSisitolica120.setColspan(1);

		Chunk textCellDiastolica80 = new Chunk("80");
		textCellDiastolica80.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica80 = new PdfPCell(new Phrase(textCellDiastolica80));
		cellDiastolica80.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica80.setMinimumHeight(15);
		cellDiastolica80.setColspan(1);

		Chunk textCellMedia92 = new Chunk("92");
		textCellMedia92.setFont(fuenteTituloHospital);

		PdfPCell cellMedia92 = new PdfPCell(new Phrase(textCellMedia92));
		cellMedia92.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia92.setMinimumHeight(15);
		cellMedia92.setColspan(1);

		PdfPCell cellValorColocado3 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado3.setMinimumHeight(15);
		cellValorColocado3.setColspan(1);

		Chunk textCellSistolica200 = new Chunk("200");
		textCellSistolica200.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica200 = new PdfPCell(new Phrase(textCellSistolica200));
		cellSisitolica200.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica200.setMinimumHeight(15);
		cellSisitolica200.setColspan(1);

		Chunk textCellDiastolica150 = new Chunk("150");
		textCellDiastolica150.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica150 = new PdfPCell(new Phrase(textCellDiastolica150));
		cellDiastolica150.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica150.setMinimumHeight(15);
		cellDiastolica150.setColspan(1);

		Chunk textCellMedia167 = new Chunk("167");
		textCellMedia167.setFont(fuenteTituloHospital);

		PdfPCell cellMedia167 = new PdfPCell(new Phrase(textCellMedia167));
		cellMedia167.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia167.setMinimumHeight(15);
		cellMedia167.setColspan(1);

		PdfPCell cellValorMedido2 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido2.setMinimumHeight(15);
		cellValorMedido2.setColspan(1);

		Chunk textCellSistolica120R = new Chunk("");
		textCellSistolica120R.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica120R = new PdfPCell(new Phrase(textCellSistolica120R));
		cellSisitolica120R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica120R.setMinimumHeight(15);
		cellSisitolica120R.setColspan(1);

		Chunk textCellDiastolica80R = new Chunk("");
		textCellDiastolica80R.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica80R = new PdfPCell(new Phrase(textCellDiastolica80R));
		cellDiastolica80R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica80R.setMinimumHeight(15);
		cellDiastolica80R.setColspan(1);

		Chunk textCellMedia92R = new Chunk("");
		textCellMedia92R.setFont(fuenteTituloHospital);

		PdfPCell cellMedia92R = new PdfPCell(new Phrase(textCellMedia92R));
		cellMedia92R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia92R.setMinimumHeight(15);
		cellMedia92R.setColspan(1);

		PdfPCell cellValorMedido3 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido3.setMinimumHeight(15);
		cellValorMedido3.setColspan(1);

		Chunk textCellSistolica200R = new Chunk("");
		textCellSistolica200R.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica200R = new PdfPCell(new Phrase(textCellSistolica200R));
		cellSisitolica200R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica200R.setMinimumHeight(15);
		cellSisitolica200R.setColspan(1);

		Chunk textCellDiastolica150R = new Chunk("");
		textCellDiastolica150R.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica150R = new PdfPCell(new Phrase(textCellDiastolica150R));
		cellDiastolica150R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica150R.setMinimumHeight(15);
		cellDiastolica150R.setColspan(1);

		Chunk textCellMedia167R = new Chunk("");
		textCellMedia167R.setFont(fuenteTituloHospital);

		PdfPCell cellMedia167R = new PdfPCell(new Phrase(textCellMedia167R));
		cellMedia167R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia167R.setMinimumHeight(15);
		cellMedia167R.setColspan(1);

		PdfPCell cellValorColocado4 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado4.setMinimumHeight(15);
		cellValorColocado4.setColspan(1);

		Chunk textCellSistolica60 = new Chunk("60");
		textCellSistolica60.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica60 = new PdfPCell(new Phrase(textCellSistolica60));
		cellSisitolica60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica60.setMinimumHeight(15);
		cellSisitolica60.setColspan(1);

		Chunk textCellDiastolica30 = new Chunk("30");
		textCellDiastolica30.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica30 = new PdfPCell(new Phrase(textCellDiastolica30));
		cellDiastolica30.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica30.setMinimumHeight(15);
		cellDiastolica30.setColspan(1);

		Chunk textCellMedia40 = new Chunk("40");
		textCellMedia40.setFont(fuenteTituloHospital);

		PdfPCell cellMedia40 = new PdfPCell(new Phrase(textCellMedia40));
		cellMedia40.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia40.setMinimumHeight(15);
		cellMedia40.setColspan(1);

		PdfPCell cellValorColocado5 = new PdfPCell(new Phrase(textCellValorColocado));
		cellValorColocado5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorColocado5.setMinimumHeight(15);
		cellValorColocado5.setColspan(1);

		Chunk textCellSistolica80 = new Chunk("80");
		textCellSistolica80.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica80 = new PdfPCell(new Phrase(textCellSistolica80));
		cellSisitolica80.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica80.setMinimumHeight(15);
		cellSisitolica80.setColspan(1);

		Chunk textCellDiastolica50 = new Chunk("50");
		textCellDiastolica50.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica50 = new PdfPCell(new Phrase(textCellDiastolica50));
		cellDiastolica50.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica50.setMinimumHeight(15);
		cellDiastolica50.setColspan(1);

		Chunk textCellMedia60 = new Chunk("60");
		textCellMedia60.setFont(fuenteTituloHospital);

		PdfPCell cellMedia60 = new PdfPCell(new Phrase(textCellMedia60));
		cellMedia60.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia60.setMinimumHeight(15);
		cellMedia60.setColspan(1);

		PdfPCell cellValorMedido4 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido4.setMinimumHeight(15);
		cellValorMedido4.setColspan(1);

		Chunk textCellSistolica60R = new Chunk("");
		textCellSistolica60R.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica60R = new PdfPCell(new Phrase(textCellSistolica60R));
		cellSisitolica60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica60R.setMinimumHeight(15);
		cellSisitolica60R.setColspan(1);

		Chunk textCellDiastolica30R = new Chunk("");
		textCellDiastolica30R.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica30R = new PdfPCell(new Phrase(textCellDiastolica30R));
		cellDiastolica30R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica30R.setMinimumHeight(15);
		cellDiastolica30R.setColspan(1);

		Chunk textCellMedia40R = new Chunk("");
		textCellMedia40R.setFont(fuenteTituloHospital);

		PdfPCell cellMedia40R = new PdfPCell(new Phrase(textCellMedia40R));
		cellMedia40R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia40R.setMinimumHeight(15);
		cellMedia40R.setColspan(1);

		PdfPCell cellValorMedido5 = new PdfPCell(new Phrase(textCellValorMedido));
		cellValorMedido5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellValorMedido5.setMinimumHeight(15);
		cellValorMedido5.setColspan(1);

		Chunk textCellSistolica80R = new Chunk("");
		textCellSistolica80R.setFont(fuenteTituloHospital);

		PdfPCell cellSisitolica80R = new PdfPCell(new Phrase(textCellSistolica80R));
		cellSisitolica80R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellSisitolica80R.setMinimumHeight(15);
		cellSisitolica80R.setColspan(1);

		Chunk textCellDiastolica50R = new Chunk("");
		textCellDiastolica50R.setFont(fuenteTituloHospital);

		PdfPCell cellDiastolica50R = new PdfPCell(new Phrase(textCellDiastolica50R));
		cellDiastolica50R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellDiastolica50R.setMinimumHeight(15);
		cellDiastolica50R.setColspan(1);

		Chunk textCellMedia60R = new Chunk("");
		textCellMedia60R.setFont(fuenteTituloHospital);

		PdfPCell cellMedia60R = new PdfPCell(new Phrase(textCellMedia60R));
		cellMedia60R.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cellMedia60R.setMinimumHeight(15);
		cellMedia60R.setColspan(1);

		tablaNIBP.addCell(cellTitleNIBC);
		tablaNIBP.addCell(cellVacia2);
		tablaNIBP.addCell(cellSistolica);
		tablaNIBP.addCell(cellDiastolica);
		tablaNIBP.addCell(cellMedia);
		tablaNIBP.addCell(cellvacia3);
		tablaNIBP.addCell(cellSistolica2);
		tablaNIBP.addCell(cellDiastolica2);
		tablaNIBP.addCell(cellMedia2);
		tablaNIBP.addCell(cellValorColocado2);
		tablaNIBP.addCell(cellSisitolica120);
		tablaNIBP.addCell(cellDiastolica80);
		tablaNIBP.addCell(cellMedia92);
		tablaNIBP.addCell(cellValorColocado3);
		tablaNIBP.addCell(cellSisitolica200);
		tablaNIBP.addCell(cellDiastolica150);
		tablaNIBP.addCell(cellMedia167);
		tablaNIBP.addCell(cellValorMedido2);
		tablaNIBP.addCell(cellSisitolica120R);
		tablaNIBP.addCell(cellDiastolica80R);
		tablaNIBP.addCell(cellMedia92R);
		tablaNIBP.addCell(cellValorMedido3);
		tablaNIBP.addCell(cellSisitolica200R);
		tablaNIBP.addCell(cellDiastolica150R);
		tablaNIBP.addCell(cellMedia167R);

		tablaNIBP.addCell(cellValorColocado4);
		tablaNIBP.addCell(cellSisitolica60);
		tablaNIBP.addCell(cellDiastolica30);
		tablaNIBP.addCell(cellMedia40);
		tablaNIBP.addCell(cellValorColocado5);
		tablaNIBP.addCell(cellSisitolica80);
		tablaNIBP.addCell(cellDiastolica50);
		tablaNIBP.addCell(cellMedia60);
		tablaNIBP.addCell(cellValorMedido4);
		tablaNIBP.addCell(cellSisitolica60R);
		tablaNIBP.addCell(cellDiastolica30R);
		tablaNIBP.addCell(cellMedia40R);
		tablaNIBP.addCell(cellValorMedido5);
		tablaNIBP.addCell(cellSisitolica80R);
		tablaNIBP.addCell(cellDiastolica50R);
		tablaNIBP.addCell(cellMedia60R);

		tablaNIBP.setSpacingAfter(10);

		// --------------------------------------------------------------------

		PdfPTable tabla4rutina = new PdfPTable(8);

		Chunk materialname = new Chunk("MATERIAL CONSUMIBLE");
		materialname.setFont(fuenteTituloHospital);

		Chunk toolsname = new Chunk("HERRAMIENTAS Y EQUIPOS");
		toolsname.setFont(fuenteTituloHospital);

		PdfPCell materialnamecell = new PdfPCell(new Phrase(materialname));
		materialnamecell.setColspan(4);

		PdfPCell toolnamecell = new PdfPCell(new Phrase(toolsname));
		toolnamecell.setColspan(4);

		PdfPCell materialcell = new PdfPCell();
		materialcell.setMinimumHeight(60);
		materialcell.setColspan(4);

		PdfPCell toolcell = new PdfPCell();
		toolcell.setMinimumHeight(60);
		toolcell.setColspan(4);

		tabla4rutina.addCell(materialnamecell);
		tabla4rutina.addCell(toolnamecell);

		tabla4rutina.addCell(materialcell);
		tabla4rutina.addCell(toolcell);

		tabla4rutina.setSpacingAfter(10);

		// ___________________________________________________________________

		PdfPTable tabla5rutina = new PdfPTable(1);
		Chunk obtitlestyle = new Chunk("OBSERVACIONES");
		obtitlestyle.setFont(fuenteTituloHospital);

		PdfPCell Obser = new PdfPCell(new Phrase(obtitlestyle));
		Obser.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla5rutina.addCell(Obser);

		Chunk obrtastyle = new Chunk();
		obrtastyle.setFont(rta);

		Obser = new PdfPCell(new Phrase(obrtastyle));
		Obser.setMinimumHeight(60);
		tabla5rutina.addCell(Obser);

		tabla5rutina.setSpacingAfter(10);

		PdfPTable tabla6rutina = new PdfPTable(2);

		Phrase tiempoejec = new Phrase();
		Chunk tiempotext = new Chunk("TIEMPO DE EJECUCIÓN (APROX. ");
		tiempotext.setFont(fuenteTituloHospital);
		tiempoejec.add(tiempotext);
		tiempotext = new Chunk();
		tiempotext.setFont(fuenteTituloHospital);
		tiempoejec.add(tiempotext);

		Obser = new PdfPCell(tiempoejec);
		tabla6rutina.addCell(Obser);
		tiempotext = new Chunk();
		Obser = new PdfPCell(new Phrase(tiempotext));
		tabla6rutina.addCell(Obser);

		Chunk realizadostyle = new Chunk("FIRMA DE QUIEN REALIZA");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk recibidostyle = new Chunk("FIRMA DE QUIEN RECIBE");
		recibidostyle.setFont(fuenteTituloHospital);

		PdfPCell realizado = new PdfPCell();
		realizado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		realizado.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		realizado.addElement(new Chunk("_____________________"));
		realizado.addElement(realizadostyle);

		PdfPCell recibido = new PdfPCell();
		recibido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		recibido.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		recibido.addElement(new Chunk("_____________________"));
		recibido.addElement(recibidostyle);

		realizado.setMinimumHeight(50);

		tabla6rutina.addCell(realizado);
		tabla6rutina.addCell(recibido);

		document.add(tabla1rutina);
		document.add(tabla2);
		document.add(tablaSimulador);
		document.add(tablaDatosSimPaciente);
		document.add(tablaNIBP);
		document.add(tabla4rutina);
		document.add(tabla5rutina);
		document.add(tabla6rutina);

		// contentByte.endText();
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getPDF(Reporte reporte, Mantenimiento_preventivo mtto,
			MantenimientoMSV mantenimientoMSV, List<Protocolo_preventivo> protocolos)
			throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();
		PdfContentByte contentByte = writer.getDirectContent();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();
		String codigo = "CÓDIGO IB-F-24";
		String Version = "VERSION: 02";
		String fechaformato = "Fecha:25/08/2022";
		String hospital = "E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA";
		String Encabezado = "REPORTE DE MANTENIMIENTO DIGITAL HRCATCH";
		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);
		;

		Chunk titulo1 = new Chunk(hospital);
		Chunk titulo2 = new Chunk(Encabezado);

		Chunk code = new Chunk(codigo);
		Chunk vs = new Chunk(Version);
		Chunk date = new Chunk(fechaformato);
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		Chunk firma = new Chunk("__________________ FIRMA");
		firma.setFont(fuenteTituloHospital);
		Chunk reportstyle = new Chunk("REPORTE No.");
		reportstyle.setFont(fuenteTituloHospital);

		Chunk numreportestyle = new Chunk(String.valueOf(reporte.getNumero_reporte()));
		numreportestyle.setFont(fontred);

		Chunk datestyle = new Chunk("FECHA: ");
		datestyle.setFont(fuenteTituloHospital);

		Chunk datertastyle = new Chunk(String.valueOf(reporte.getFecha()));
		datertastyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteTituloHospital);

		Chunk equiportastyle = new Chunk(String.valueOf(reporte.getNombre_equipo()));
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteTituloHospital);

		Chunk marcartastyle = new Chunk(reporte.getMarca());
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteTituloHospital);

		Chunk modelortastyle = new Chunk(reporte.getModelo());
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteTituloHospital);

		Chunk seriertastyle = new Chunk(reporte.getSerie());
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteTituloHospital);

		Chunk placartastyle = new Chunk(reporte.getPlaca_inventario());
		placartastyle.setFont(rta);

		int Periodicidad = reporte.getEquipo().getPeriodicidad();
		String freq = new String();
		if (Periodicidad == 1) {
			freq = "ANUAL";
		} else if (Periodicidad == 2) {
			freq = "SEMESTRAL";
		} else if (Periodicidad == 3) {
			freq = "TRIMESTRAL";
		} else {
			freq = "CUATRIMESTRAL";
		}

		Chunk freqstyle = new Chunk("PERIODICIDAD: ");
		freqstyle.setFont(fuenteTituloHospital);

		Chunk freqrtastyle = new Chunk(freq);
		freqrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteTituloHospital);

		Chunk servicertastyle = new Chunk(reporte.getServicio());
		servicertastyle.setFont(rta);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteTituloHospital);

		Chunk ubrtastyle = new Chunk(reporte.getUbicacion());
		ubrtastyle.setFont(rta);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda3);
		tabla.addCell(celda4);
		tabla.addCell(celda5);

		PdfPTable tabla2 = new PdfPTable(10);
		PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
		reportenm.setColspan(2);
		PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
		numreporte.setColspan(3);
		PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
		celdadatename.setColspan(2);
		PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
		celdadate.setColspan(3);

		PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
		equiponame.setColspan(2);
		PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
		equiporta.setColspan(3);
		PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
		marcaname.setColspan(2);
		PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
		marcarta.setColspan(3);

		PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
		modeloname.setColspan(2);
		PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
		modelorta.setColspan(3);
		PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
		seriename.setColspan(2);
		PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
		serierta.setColspan(3);

		PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
		placaname.setColspan(2);
		PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
		placarta.setColspan(3);
		PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
		pname.setColspan(2);
		PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
		prta.setColspan(3);

		PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
		servicioname.setColspan(2);
		PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
		serviciorta.setColspan(3);
		PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
		ubicacionname.setColspan(2);
		PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
		ubicacionrta.setColspan(3);

		tabla.setSpacingAfter(10);

		tabla2.addCell(reportenm);
		tabla2.addCell(numreporte);
		tabla2.addCell(celdadatename);
		tabla2.addCell(celdadate);

		tabla2.addCell(equiponame);
		tabla2.addCell(equiporta);
		tabla2.addCell(marcaname);
		tabla2.addCell(marcarta);

		tabla2.addCell(modeloname);
		tabla2.addCell(modelorta);
		tabla2.addCell(seriename);
		tabla2.addCell(serierta);

		tabla2.addCell(placaname);
		tabla2.addCell(placarta);
		tabla2.addCell(pname);
		tabla2.addCell(prta);

		tabla2.addCell(servicioname);
		tabla2.addCell(serviciorta);
		tabla2.addCell(ubicacionname);
		tabla2.addCell(ubicacionrta);

		tabla2.setSpacingAfter(10);

		PdfPTable tabla3 = new PdfPTable(7);

		String pd = new String();
		String pv = new String();
		String cr = new String();
		String ot = new String();

		int tipommtto = reporte.getTipo_mantenimiento();
		if (tipommtto == 1) {
			pd = "PREDICTIVO: ";
			pv = "PREVENTIVO: ";
			cr = "CORRECTIVO: ";
			ot = "OTRO: X";
		} else if (tipommtto == 2) {
			pd = "PREDICTIVO: ";
			pv = "PREVENTIVO: ";
			cr = "CORRECTIVO: X";
			ot = "OTRO: ";
		} else if (tipommtto == 3) {
			pd = "PREDICTIVO: ";
			pv = "PREVENTIVO: X";
			cr = "CORRECTIVO: ";
			ot = "OTRO: ";
		} else {
			pd = "PREDICTIVO: X";
			pv = "PREVENTIVO: ";
			cr = "CORRECTIVO: ";
			ot = "OTRO: ";
		}
		Chunk typemttostyle = new Chunk("TIPO DE MANTENIMIENTO");
		typemttostyle.setFont(fuenteTituloHospital);

		Chunk predstyle = new Chunk(pd);
		predstyle.setFont(fuenteTituloHospital);

		Chunk prevstyle = new Chunk(pv);
		prevstyle.setFont(fuenteTituloHospital);

		Chunk corstyle = new Chunk(cr);
		corstyle.setFont(fuenteTituloHospital);

		Chunk otrstyle = new Chunk(ot);
		otrstyle.setFont(fuenteTituloHospital);

		Chunk typefstyle = new Chunk("TIPO DE FALLA");
		typefstyle.setFont(fuenteTituloHospital);

		PdfPCell tipomname = new PdfPCell(new Phrase(typemttostyle));
		tipomname.setColspan(7);
		tipomname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell predictivo = new PdfPCell(new Phrase(predstyle));
		predictivo.setColspan(2);
		PdfPCell preventivo = new PdfPCell(new Phrase(prevstyle));
		preventivo.setColspan(2);
		PdfPCell correctivo = new PdfPCell(new Phrase(corstyle));
		correctivo.setColspan(2);
		correctivo.setMinimumHeight(20);
		PdfPCell otro = new PdfPCell(new Phrase(otrstyle));

		PdfPCell tipofname = new PdfPCell(new Phrase(typefstyle));
		tipofname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tipofname.setColspan(7);
		String tipofalla = reporte.getTipo_falla();
		String desgaste = new String();
		String opi = new String();
		String caex = new String();
		String ac = new String();
		String desc = new String();
		String sfall = new String();
		String otf = new String();

		if (tipofalla.contains("1")) {
			desgaste = "1.DESGASTE: X";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: ";
		} else if (tipofalla.contains("2")) {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: X";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: ";
		} else if (tipofalla.contains("3")) {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: X";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: ";
		} else if (tipofalla.contains("4")) {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: X";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: ";
		} else if (tipofalla.contains("5")) {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: X";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: ";
		} else if (tipofalla.contains("6")) {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: X";
			otf = "7.OTRO: ";
		} else {
			desgaste = "1.DESGASTE: ";
			opi = "2.OPERACIÓN INDEBIDA: ";
			caex = "3.CAUSA EXTERNA: ";
			ac = "4.ACCESORIOS: ";
			desc = "5.DESCONOCIDO: ";
			sfall = "6.SIN FALLAS: ";
			otf = "7.OTRO: X";
		}

		Chunk desestilo = new Chunk(desgaste);
		desestilo.setFont(writers);

		Chunk opstyle = new Chunk(opi);
		opstyle.setFont(writers);

		Chunk cexstyle = new Chunk(caex);
		cexstyle.setFont(writers);

		Chunk accstyle = new Chunk(ac);
		accstyle.setFont(writers);

		Chunk desstyle = new Chunk(desc);
		desstyle.setFont(negrita);

		Chunk sfstyle = new Chunk(sfall);
		sfstyle.setFont(writers);

		Chunk ofstyle = new Chunk(otf);
		ofstyle.setFont(writers);
		// Asignamos la variable ByteArrayOutputStream bos donde se escribirá el
		// documento
		PdfPCell f1 = new PdfPCell(new Phrase(desestilo));

		PdfPCell f2 = new PdfPCell(new Phrase(opstyle));

		PdfPCell f3 = new PdfPCell(new Phrase(cexstyle));

		PdfPCell f4 = new PdfPCell(new Phrase(accstyle));
		PdfPCell f5 = new PdfPCell(new Phrase(desstyle));
		PdfPCell f6 = new PdfPCell(new Phrase(sfstyle));
		PdfPCell f7 = new PdfPCell(new Phrase(ofstyle));

		Chunk motstyle = new Chunk("MOTIVO DEL LLAMADO: ");
		motstyle.setFont(fuenteTituloHospital);
		Chunk motrtastyle = new Chunk(reporte.getMotivo());
		motrtastyle.setFont(rta);

		Paragraph motivo = new Paragraph();
		motivo.add(motstyle);
		motivo.add(motrtastyle);

		Chunk ttitlestyle = new Chunk("TRABAJO REALIZADO: ");
		ttitlestyle.setFont(fuenteTituloHospital);

		Chunk trtastyle = new Chunk(reporte.getTrabajo_realizado());
		trtastyle.setFont(rta);

		Paragraph trealizado = new Paragraph();
		trealizado.add(ttitlestyle);
		trealizado.add(trtastyle);

		trealizado.setLeading(5.0f, 1.0f);

		PdfPCell llamado = new PdfPCell(motivo);
		llamado.setColspan(7);
		llamado.setMinimumHeight(50);

		PdfPCell trabajo = new PdfPCell(trealizado);
		trabajo.setColspan(7);
		trabajo.setMinimumHeight(210);
		tabla3.addCell(tipomname);
		tabla3.addCell(predictivo);
		tabla3.addCell(preventivo);
		tabla3.addCell(correctivo);
		tabla3.addCell(otro);

		tabla3.addCell(tipofname);
		tabla3.addCell(f1);
		tabla3.addCell(f2);
		tabla3.addCell(f3);
		tabla3.addCell(f4);
		tabla3.addCell(f5);
		tabla3.addCell(f6);
		tabla3.addCell(f7);
		tabla3.addCell(llamado);
		tabla3.addCell(trabajo);
		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(7);
		Chunk repstyle = new Chunk("REPUESTOS UTILIZADOS");
		repstyle.setFont(fuenteTituloHospital);

		Chunk descrstyle = new Chunk("DESCRIPCIÓN");
		descrstyle.setFont(fuenteTituloHospital);

		Chunk cantstyle = new Chunk("CANTIDAD");
		cantstyle.setFont(fuenteTituloHospital);

		Chunk cegstyle = new Chunk("C. EGRESO");
		cegstyle.setFont(fuenteTituloHospital);

		PdfPCell repuestosname = new PdfPCell(new Phrase(repstyle));
		repuestosname.setColspan(7);
		repuestosname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell descres = new PdfPCell(new Phrase(descrstyle));
		descres.setColspan(4);
		descres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell canres = new PdfPCell(new Phrase(cantstyle));
		canres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		PdfPCell cegres = new PdfPCell(new Phrase(cegstyle));
		cegres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cegres.setColspan(2);

		Chunk rtarptostyle = new Chunk(reporte.getRepuesto_cambiado());
		rtarptostyle.setFont(rta);

		Chunk cegresostyle = new Chunk(reporte.getComprobante_ingreso());
		cegresostyle.setFont(rta);

		PdfPCell description = new PdfPCell(new Phrase(rtarptostyle));
		description.setColspan(4);
		description.setMinimumHeight(70);

		PdfPCell amount = new PdfPCell(new Phrase(""));

		PdfPCell egress = new PdfPCell(new Phrase(cegresostyle));
		egress.setColspan(2);

		tabla4.addCell(repuestosname);
		tabla4.addCell(descres);
		tabla4.addCell(canres);
		tabla4.addCell(cegres);

		tabla4.addCell(description);
		tabla4.addCell(amount);
		tabla4.addCell(egress);
		tabla4.setSpacingAfter(10);

		PdfPTable tabla5 = new PdfPTable(1);

		Chunk obtitlestyle = new Chunk("OBSERVACIONES: ");
		obtitlestyle.setFont(fuenteTituloHospital);

		Chunk obrtastyle = new Chunk(reporte.getObservaciones());
		obrtastyle.setFont(rta);

		Paragraph obs = new Paragraph();
		obs.add(obtitlestyle);
		obs.add(obrtastyle);

		PdfPCell Obser = new PdfPCell(obs);
		Obser.setMinimumHeight(55);

		tabla5.addCell(Obser);
		tabla5.setSpacingAfter(10);

		PdfPTable tabla6 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("REALIZADO POR: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk(reporte.getAutor_realizado());
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("RECIBIDO POR: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk(reporte.getAutor_recibido());
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla6.addCell(realizado);
		tabla6.addCell(recibido);
		tabla6.addCell(nombrerea);
		tabla6.addCell(firmrea);
		tabla6.addCell(nombrereci);
		tabla6.addCell(firmreci);

		document.add(tabla);

		document.add(tabla2);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.add(tabla6);
		document.newPage();

		if (mtto != null) {
			Time timer = mtto.getTiempo_realizacion();
			LocalTime tiempor = timer.toLocalTime();
			int hora = tiempor.getHour();
			int minuto = tiempor.getMinute();
			int minutos = hora * 60 + minuto;
			ArrayList<String> materiales = new ArrayList<String>(
					Arrays.asList(reporte.getEquipo().getTipo_equipo().getMaterial_consumible().split(",")));
			ArrayList<String> herramientas = new ArrayList<String>(
					Arrays.asList(reporte.getEquipo().getTipo_equipo().getHerramienta().split(",")));
			ArrayList<String> repuestos = new ArrayList<String>(
					Arrays.asList(reporte.getEquipo().getTipo_equipo().getRepuestos_minimos().split(",")));

			Chunk rutinatitle = new Chunk("RUTINA DE MANTENIMIENTO PREVENTIVO");
			rutinatitle.setFont(fuenteTituloHospital);
			Chunk mintitle = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL TUNJA");
			mintitle.setFont(fuenteTituloHospital);
			Chunk niveltitle = new Chunk("III NIVEL DE ATENCIÓN ");
			niveltitle.setFont(fuenteTituloHospital);
			Chunk versionrutine = new Chunk("Versión: 02");
			versionrutine.setFont(negrita);
			Chunk codigorutine = new Chunk("Código: IB-F-26");
			codigorutine.setFont(negrita);

			Chunk daterutine = new Chunk("Fecha: 28/03/2022");
			daterutine.setFont(negrita);

			PdfPTable tabla1rutina = new PdfPTable(5);

			PdfPCell cellrutine = new PdfPCell(new Phrase(rutinatitle));
			cellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cellrutine.setColspan(3);

			PdfPCell cellcode = new PdfPCell(new Phrase(codigorutine));
			cellcode.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cellcode.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			PdfPCell cellmin = new PdfPCell(new Phrase(mintitle));
			cellmin.setColspan(3);
			cellmin.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cellmin.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			PdfPCell celllogo = new PdfPCell(logo);
			celllogo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			celllogo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			celllogo.setMinimumHeight(40);

			PdfPCell cellversion = new PdfPCell(new Phrase(versionrutine));
			cellversion.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cellversion.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			PdfPCell cellnivel = new PdfPCell(new Phrase(niveltitle));
			cellnivel.setColspan(3);
			cellnivel.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cellnivel.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			PdfPCell datecellrutine = new PdfPCell(new Phrase(daterutine));
			datecellrutine.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			datecellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			tabla1rutina.addCell(cellcode);
			tabla1rutina.addCell(cellmin);
			tabla1rutina.addCell(celllogo);
			tabla1rutina.addCell(cellversion);
			tabla1rutina.addCell(cellrutine);
			tabla1rutina.addCell(datecellrutine);

			tabla1rutina.setSpacingAfter(10);

			PdfPTable tabla3rutina = new PdfPTable(7);

			Chunk protocoloname = new Chunk("PROTOCOLO ESTABLECIDO POR EL FABRICANTE");
			protocoloname.setFont(fuenteTituloHospital);

			Chunk cumplename = new Chunk("CUMPLE");
			cumplename.setFont(fuenteTituloHospital);

			Chunk nocumplename = new Chunk("NO CUMPLE");
			nocumplename.setFont(fuenteTituloHospital);

			PdfPCell pasoprotocolo = new PdfPCell(new Phrase(protocoloname));
			pasoprotocolo.setColspan(5);

			PdfPCell checkprotocolo = new PdfPCell(new Phrase(cumplename));
			PdfPCell uncheckprotocolo = new PdfPCell(new Phrase(nocumplename));

			tabla3rutina.addCell(pasoprotocolo);
			tabla3rutina.addCell(checkprotocolo);
			tabla3rutina.addCell(uncheckprotocolo);

			PdfPCell pasorta = new PdfPCell();
			PdfPCell checkrta = new PdfPCell();
			PdfPCell uncheckrta = new PdfPCell();

			Chunk pasostyle = new Chunk("");
			boolean checkpaso = false;
			for (int paso = 0; paso < protocolos.size(); paso++) {
				pasostyle = new Chunk(protocolos.get(paso).getPaso());
				pasostyle.setFont(rta);
				pasorta = new PdfPCell(new Phrase(pasostyle));
				pasorta.setColspan(5);
				checkpaso = protocolos.get(paso).isCumplimiento();
				if (checkpaso) {
					checkrta = new PdfPCell(new Phrase("X"));
					checkrta.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					uncheckrta = new PdfPCell(new Phrase(""));
				} else {
					checkrta = new PdfPCell(new Phrase(""));

					uncheckrta = new PdfPCell(new Phrase("X"));
					uncheckrta.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				}

				tabla3rutina.addCell(pasorta);
				tabla3rutina.addCell(checkrta);
				tabla3rutina.addCell(uncheckrta);

			}
			tabla3rutina.setSpacingAfter(10);

			PdfPTable tabla4rutina = new PdfPTable(8);

			Chunk materialname = new Chunk("MATERIAL CONSUMIBLE");
			materialname.setFont(fuenteTituloHospital);

			Chunk toolsname = new Chunk("HERRAMIENTAS Y EQUIPOS");
			toolsname.setFont(fuenteTituloHospital);

			Chunk rptosname = new Chunk("REPUESTOS MÍNIMOS");
			rptosname.setFont(fuenteTituloHospital);

			PdfPCell materialnamecell = new PdfPCell(new Phrase(materialname));
			materialnamecell.setColspan(3);

			PdfPCell toolnamecell = new PdfPCell(new Phrase(toolsname));
			toolnamecell.setColspan(3);

			PdfPCell rptonamecell = new PdfPCell(new Phrase(rptosname));
			rptonamecell.setColspan(2);

			Chunk materialstyle = new Chunk("");
			materialstyle.setFont(rta);

			PdfPCell materialcell = new PdfPCell();
			materialcell.setColspan(3);

			for (int paso = 0; paso < materiales.size(); paso++) {
				materialstyle = new Chunk(materiales.get(paso));
				materialstyle.setFont(rta);
				materialcell.addElement(materialstyle);

			}
			PdfPCell toolcell = new PdfPCell();
			toolcell.setColspan(3);

			for (int paso = 0; paso < herramientas.size(); paso++) {
				materialstyle = new Chunk(herramientas.get(paso));
				materialstyle.setFont(rta);
				toolcell.addElement(materialstyle);

			}
			PdfPCell rptocell = new PdfPCell();
			rptocell.setColspan(2);
			for (int paso = 0; paso < repuestos.size(); paso++) {
				materialstyle = new Chunk(repuestos.get(paso));
				materialstyle.setFont(rta);
				rptocell.addElement(materialstyle);

			}

			tabla4rutina.addCell(materialnamecell);
			tabla4rutina.addCell(toolnamecell);
			tabla4rutina.addCell(rptonamecell);
			tabla4rutina.addCell(materialcell);
			tabla4rutina.addCell(toolcell);
			tabla4rutina.addCell(rptocell);
			tabla4rutina.setSpacingAfter(10);

			PdfPTable tabla5rutina = new PdfPTable(1);
			obtitlestyle = new Chunk("OBSERVACIONES");
			obtitlestyle.setFont(fuenteTituloHospital);

			Obser = new PdfPCell(new Phrase(obtitlestyle));
			Obser.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla5rutina.addCell(Obser);

			obrtastyle = new Chunk(reporte.getObservaciones());
			obrtastyle.setFont(rta);

			Obser = new PdfPCell(new Phrase(obrtastyle));
			Obser.setMinimumHeight(60);
			tabla5rutina.addCell(Obser);

			tabla5rutina.setSpacingAfter(10);

			PdfPTable tabla6rutina = new PdfPTable(2);

			Phrase tiempoejec = new Phrase();
			Chunk tiempotext = new Chunk("TIEMPO DE EJECUCIÓN (APROX. ");
			tiempotext.setFont(fuenteTituloHospital);
			tiempoejec.add(tiempotext);
			tiempotext = new Chunk(
					String.valueOf(reporte.getEquipo().getTipo_equipo().getTiempo_minutos()) + " MINUTOS) :");
			tiempotext.setFont(fuenteTituloHospital);
			tiempoejec.add(tiempotext);

			Obser = new PdfPCell(tiempoejec);
			tabla6rutina.addCell(Obser);
			tiempotext = new Chunk(String.valueOf(minutos));
			Obser = new PdfPCell(new Phrase(tiempotext));
			tabla6rutina.addCell(Obser);

			realizadostyle = new Chunk("FIRMA DE QUIEN REALIZA");
			realizadostyle.setFont(fuenteTituloHospital);

			recibidostyle = new Chunk("FIRMA DE QUIEN RECIBE");
			recibidostyle.setFont(fuenteTituloHospital);

			realizado = new PdfPCell();
			realizado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			realizado.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
			realizado.addElement(new Chunk("_____________________"));
			realizado.addElement(realizadostyle);

			recibido = new PdfPCell();
			recibido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			recibido.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
			recibido.addElement(new Chunk("_____________________"));
			recibido.addElement(recibidostyle);

			realizado.setMinimumHeight(50);

			tabla6rutina.addCell(realizado);
			tabla6rutina.addCell(recibido);

			document.add(tabla1rutina);
			document.add(tabla2);
			document.add(tabla3rutina);
			document.add(tabla4rutina);
			document.add(tabla5rutina);
			document.add(tabla6rutina);
		}

		document.newPage();

		if (mtto != null) {
			if (mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 9 && mantenimientoMSV != null && reporte.getTipo_mantenimiento() == 3) {

				getPDFMSVDil(reporte, mtto.getEquipo(), mantenimientoMSV, document);
			}
		}
		// contentByte.endText();
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalPDF() throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();
		String codigo = "CÓDIGO IB-F-24";
		String Version = "VERSION: 02";
		String fechaformato = "Fecha:25/08/2022";
		String hospital = "E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA";
		String Encabezado = "REPORTE DE MANTENIMIENTO DIGITAL HRCATCH";
		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);
		;

		Chunk titulo1 = new Chunk(hospital);
		Chunk titulo2 = new Chunk(Encabezado);

		Chunk code = new Chunk(codigo);
		Chunk vs = new Chunk(Version);
		Chunk date = new Chunk(fechaformato);
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		Chunk firma = new Chunk("__________________ FIRMA");
		firma.setFont(fuenteTituloHospital);
		Chunk reportstyle = new Chunk("REPORTE No.");
		reportstyle.setFont(fuenteTituloHospital);

		Chunk numreportestyle = new Chunk("");
		numreportestyle.setFont(fontred);

		Chunk datestyle = new Chunk("FECHA: ");
		datestyle.setFont(fuenteTituloHospital);

		Chunk datertastyle = new Chunk("");
		datertastyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteTituloHospital);

		Chunk equiportastyle = new Chunk("");
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteTituloHospital);

		Chunk marcartastyle = new Chunk("");
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteTituloHospital);

		Chunk modelortastyle = new Chunk("");
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteTituloHospital);

		Chunk seriertastyle = new Chunk("");
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteTituloHospital);

		Chunk placartastyle = new Chunk("");
		placartastyle.setFont(rta);

		Chunk freqstyle = new Chunk("PERIODICIDAD: ");
		freqstyle.setFont(fuenteTituloHospital);

		Chunk freqrtastyle = new Chunk("");
		freqrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteTituloHospital);

		Chunk servicertastyle = new Chunk("");
		servicertastyle.setFont(rta);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteTituloHospital);

		Chunk ubrtastyle = new Chunk("");
		ubrtastyle.setFont(rta);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda3);
		tabla.addCell(celda4);
		tabla.addCell(celda5);

		PdfPTable tabla2 = new PdfPTable(10);
		PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
		reportenm.setColspan(2);
		PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
		numreporte.setColspan(3);
		PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
		celdadatename.setColspan(2);
		PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
		celdadate.setColspan(3);

		PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
		equiponame.setColspan(2);
		PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
		equiporta.setColspan(3);
		PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
		marcaname.setColspan(2);
		PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
		marcarta.setColspan(3);

		PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
		modeloname.setColspan(2);
		PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
		modelorta.setColspan(3);
		PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
		seriename.setColspan(2);
		PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
		serierta.setColspan(3);

		PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
		placaname.setColspan(2);
		PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
		placarta.setColspan(3);
		PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
		pname.setColspan(2);
		PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
		prta.setColspan(3);

		PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
		servicioname.setColspan(2);
		PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
		serviciorta.setColspan(3);
		PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
		ubicacionname.setColspan(2);
		PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
		ubicacionrta.setColspan(3);

		tabla.setSpacingAfter(10);

		tabla2.addCell(reportenm);
		tabla2.addCell(numreporte);
		tabla2.addCell(celdadatename);
		tabla2.addCell(celdadate);

		tabla2.addCell(equiponame);
		tabla2.addCell(equiporta);
		tabla2.addCell(marcaname);
		tabla2.addCell(marcarta);

		tabla2.addCell(modeloname);
		tabla2.addCell(modelorta);
		tabla2.addCell(seriename);
		tabla2.addCell(serierta);

		tabla2.addCell(placaname);
		tabla2.addCell(placarta);
		tabla2.addCell(pname);
		tabla2.addCell(prta);

		tabla2.addCell(servicioname);
		tabla2.addCell(serviciorta);
		tabla2.addCell(ubicacionname);
		tabla2.addCell(ubicacionrta);

		tabla2.setSpacingAfter(10);

		PdfPTable tabla3 = new PdfPTable(7);

		String pd = new String();
		String pv = new String();
		String cr = new String();
		String ot = new String();
		pd = "PREDICTIVO: ";
		pv = "PREVENTIVO: ";
		cr = "CORRECTIVO: ";
		ot = "OTRO: ";
		Chunk typemttostyle = new Chunk("TIPO DE MANTENIMIENTO");
		typemttostyle.setFont(fuenteTituloHospital);

		Chunk predstyle = new Chunk(pd);
		predstyle.setFont(fuenteTituloHospital);

		Chunk prevstyle = new Chunk(pv);
		prevstyle.setFont(fuenteTituloHospital);

		Chunk corstyle = new Chunk(cr);
		corstyle.setFont(fuenteTituloHospital);

		Chunk otrstyle = new Chunk(ot);
		otrstyle.setFont(fuenteTituloHospital);

		Chunk typefstyle = new Chunk("TIPO DE FALLA");
		typefstyle.setFont(fuenteTituloHospital);

		PdfPCell tipomname = new PdfPCell(new Phrase(typemttostyle));
		tipomname.setColspan(7);
		tipomname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell predictivo = new PdfPCell(new Phrase(predstyle));
		predictivo.setColspan(2);
		PdfPCell preventivo = new PdfPCell(new Phrase(prevstyle));
		preventivo.setColspan(2);
		PdfPCell correctivo = new PdfPCell(new Phrase(corstyle));
		correctivo.setColspan(2);
		correctivo.setMinimumHeight(20);
		PdfPCell otro = new PdfPCell(new Phrase(otrstyle));

		PdfPCell tipofname = new PdfPCell(new Phrase(typefstyle));
		tipofname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tipofname.setColspan(7);

		String desgaste = new String();
		String opi = new String();
		String caex = new String();
		String ac = new String();
		String desc = new String();
		String sfall = new String();
		String otf = new String();
		desgaste = "1.DESGASTE: ";
		opi = "2.OPERACIÓN INDEBIDA: ";
		caex = "3.CAUSA EXTERNA: ";
		ac = "4.ACCESORIOS: ";
		desc = "5.DESCONOCIDO: ";
		sfall = "6.SIN FALLAS: ";
		otf = "7.OTRO: ";

		Chunk desestilo = new Chunk(desgaste);
		desestilo.setFont(writers);

		Chunk opstyle = new Chunk(opi);
		opstyle.setFont(writers);

		Chunk cexstyle = new Chunk(caex);
		cexstyle.setFont(writers);

		Chunk accstyle = new Chunk(ac);
		accstyle.setFont(writers);

		Chunk desstyle = new Chunk(desc);
		desstyle.setFont(negrita);

		Chunk sfstyle = new Chunk(sfall);
		sfstyle.setFont(writers);

		Chunk ofstyle = new Chunk(otf);
		ofstyle.setFont(writers);
		// Asignamos la variable ByteArrayOutputStream bos donde se escribirá el
		// documento
		PdfPCell f1 = new PdfPCell(new Phrase(desestilo));

		PdfPCell f2 = new PdfPCell(new Phrase(opstyle));

		PdfPCell f3 = new PdfPCell(new Phrase(cexstyle));

		PdfPCell f4 = new PdfPCell(new Phrase(accstyle));
		PdfPCell f5 = new PdfPCell(new Phrase(desstyle));
		PdfPCell f6 = new PdfPCell(new Phrase(sfstyle));
		PdfPCell f7 = new PdfPCell(new Phrase(ofstyle));

		Chunk motstyle = new Chunk("MOTIVO DEL LLAMADO: ");
		motstyle.setFont(fuenteTituloHospital);
		Chunk motrtastyle = new Chunk("");
		motrtastyle.setFont(fuenteTituloHospital);

		Paragraph motivo = new Paragraph();
		motivo.add(motstyle);
		motivo.add(motrtastyle);

		Chunk ttitlestyle = new Chunk("TRABAJO REALIZADO: ");
		ttitlestyle.setFont(fuenteTituloHospital);

		Chunk trtastyle = new Chunk("");
		trtastyle.setFont(rta);

		Paragraph trealizado = new Paragraph();
		trealizado.add(ttitlestyle);
		trealizado.add(trtastyle);

		trealizado.setLeading(5.0f, 1.0f);

		PdfPCell llamado = new PdfPCell(motivo);
		llamado.setColspan(7);
		llamado.setMinimumHeight(50);

		PdfPCell trabajo = new PdfPCell(trealizado);
		trabajo.setColspan(7);
		trabajo.setMinimumHeight(210);
		tabla3.addCell(tipomname);
		tabla3.addCell(predictivo);
		tabla3.addCell(preventivo);
		tabla3.addCell(correctivo);
		tabla3.addCell(otro);

		tabla3.addCell(tipofname);
		tabla3.addCell(f1);
		tabla3.addCell(f2);
		tabla3.addCell(f3);
		tabla3.addCell(f4);
		tabla3.addCell(f5);
		tabla3.addCell(f6);
		tabla3.addCell(f7);
		tabla3.addCell(llamado);
		tabla3.addCell(trabajo);
		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(7);
		Chunk repstyle = new Chunk("REPUESTOS UTILIZADOS");
		repstyle.setFont(fuenteTituloHospital);

		Chunk descrstyle = new Chunk("DESCRIPCIÓN");
		descrstyle.setFont(fuenteTituloHospital);

		Chunk cantstyle = new Chunk("CANTIDAD");
		cantstyle.setFont(fuenteTituloHospital);

		Chunk cegstyle = new Chunk("C. EGRESO");
		cegstyle.setFont(fuenteTituloHospital);

		PdfPCell repuestosname = new PdfPCell(new Phrase(repstyle));
		repuestosname.setColspan(7);
		repuestosname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell descres = new PdfPCell(new Phrase(descrstyle));
		descres.setColspan(4);
		descres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell canres = new PdfPCell(new Phrase(cantstyle));
		canres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		PdfPCell cegres = new PdfPCell(new Phrase(cegstyle));
		cegres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cegres.setColspan(2);

		Chunk rtarptostyle = new Chunk("");
		rtarptostyle.setFont(rta);

		Chunk cegresostyle = new Chunk("");
		cegresostyle.setFont(rta);

		PdfPCell description = new PdfPCell(new Phrase(rtarptostyle));
		description.setColspan(4);
		description.setMinimumHeight(70);

		PdfPCell amount = new PdfPCell(new Phrase(""));

		PdfPCell egress = new PdfPCell(new Phrase(cegresostyle));
		egress.setColspan(2);

		tabla4.addCell(repuestosname);
		tabla4.addCell(descres);
		tabla4.addCell(canres);
		tabla4.addCell(cegres);

		tabla4.addCell(description);
		tabla4.addCell(amount);
		tabla4.addCell(egress);
		tabla4.setSpacingAfter(10);

		PdfPTable tabla5 = new PdfPTable(1);

		Chunk obtitlestyle = new Chunk("OBSERVACIONES: ");
		obtitlestyle.setFont(fuenteTituloHospital);

		Chunk obrtastyle = new Chunk("");
		obrtastyle.setFont(rta);

		Paragraph obs = new Paragraph();
		obs.add(obtitlestyle);
		obs.add(obrtastyle);

		PdfPCell Obser = new PdfPCell(obs);
		Obser.setMinimumHeight(55);

		tabla5.addCell(Obser);
		tabla5.setSpacingAfter(10);

		PdfPTable tabla6 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("REALIZADO POR: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk("");
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("RECIBIDO POR: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk("");
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla6.addCell(realizado);
		tabla6.addCell(recibido);
		tabla6.addCell(nombrerea);
		tabla6.addCell(firmrea);
		tabla6.addCell(nombrereci);
		tabla6.addCell(firmreci);

		document.add(tabla);

		document.add(tabla2);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.add(tabla6);

		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getHVPDF(Hoja_vida hoja_vida) throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("HOJA DE VIDA DIGITAL EQUIPO BIOMEDICO HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO IB-F-25");
		Chunk vs = new Chunk("VERSION: 02");
		Chunk date = new Chunk("Fecha:21/09/2022");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk idenstyle = new Chunk("IDENTIFICACIÓN");
		idenstyle.setFont(fuenteTituloHospital);

		Chunk depstyle = new Chunk("DEPARTAMENTO:");
		depstyle.setFont(fuenteEnunciados);

		Chunk deprtastyle = new Chunk(hoja_vida.getDepartamento());
		deprtastyle.setFont(rta);

		Chunk munstyle = new Chunk("MUNICIPIO:");
		munstyle.setFont(fuenteEnunciados);

		Chunk munrtastyle = new Chunk(hoja_vida.getMunicipio());
		munrtastyle.setFont(rta);

		Chunk addresstyle = new Chunk("DIRECCIÓN:");
		addresstyle.setFont(fuenteEnunciados);

		Chunk addresrtastyle = new Chunk(hoja_vida.getDireccion());
		addresrtastyle.setFont(rta);

		Chunk telestyle = new Chunk("TELÉFONO:");
		telestyle.setFont(fuenteEnunciados);

		Chunk telertastyle = new Chunk(hoja_vida.getTelefonoinstitucion());
		telertastyle.setFont(rta);

		Chunk emailstyle = new Chunk("E-MAIL:");
		emailstyle.setFont(fuenteEnunciados);

		Chunk emailrtastyle = new Chunk(hoja_vida.getEmailinstitucion());
		emailrtastyle.setFont(correo);

		Chunk codstyle = new Chunk("COD. INTERNACIONAL:");
		codstyle.setFont(fuenteEnunciados);

		Chunk codrtastyle = new Chunk(hoja_vida.getCodinternacional());
		codrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteEnunciados);

		Chunk servicertastyle = new Chunk(hoja_vida.getEquipo().getServicios());
		servicertastyle.setFont(fuenteEnunciados);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteEnunciados);

		Chunk ubrtastyle = new Chunk(hoja_vida.getEquipo().getUbicacion());
		ubrtastyle.setFont(fuenteEnunciados);

		String pathroute = "./src/main/resources/static" + hoja_vida.getFoto();

		Image photodevice = Image.getInstance(pathroute);
		photodevice.setAlignment(Image.ALIGN_CENTER);
		photodevice.scaleAbsolute(80, 70);

		PdfPTable tabladhos = new PdfPTable(5);

		PdfPCell idencell = new PdfPCell(new Phrase(idenstyle));
		idencell.setColspan(6);
		idencell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell fotocell = new PdfPCell(photodevice);

		fotocell.setRowspan(4);
		fotocell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		fotocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		fotocell.setMinimumHeight(80);

		tabladhos.addCell(idencell);

		tabladhos.addCell(new PdfPCell(new Phrase(depstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(deprtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munrtastyle)));
		tabladhos.addCell(fotocell);
		tabladhos.addCell(new PdfPCell(new Phrase(addresstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(addresrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telestyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telertastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(emailstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(emailrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codrtastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(serviceestyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(servicertastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubrtastyle)));

		tabladhos.setSpacingAfter(10);

		Chunk dataeqstyle = new Chunk("DATOS DEL EQUIPO");
		dataeqstyle.setFont(fuenteTituloHospital);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(rta);

		Chunk equiportastyle = new Chunk(hoja_vida.getEquipo().getNombre_Equipo());
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(rta);

		Chunk marcartastyle = new Chunk(hoja_vida.getEquipo().getMarca());
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(rta);

		Chunk modelortastyle = new Chunk(hoja_vida.getEquipo().getModelo());
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(rta);

		Chunk seriertastyle = new Chunk(hoja_vida.getEquipo().getSerie());
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(rta);

		Chunk placartastyle = new Chunk(hoja_vida.getEquipo().getPlaca_inventario());
		placartastyle.setFont(rta);

		Chunk anofstyle = new Chunk("AÑO DE FABRICACIÓN:");
		anofstyle.setFont(rtasmall);
		Chunk anofrtastyle = new Chunk("");
		if (hoja_vida.getAno_fabricacion() != 0) {
			anofrtastyle = new Chunk(String.valueOf(hoja_vida.getAno_fabricacion()));

		} else {
			anofrtastyle = new Chunk("NE");

		}
		anofrtastyle.setFont(rta);

		Chunk invimastyle = new Chunk("REGISTRO INVIMA:");
		invimastyle.setFont(rta);

		Chunk invimartastyle = new Chunk(hoja_vida.getRegistro_invima());
		invimartastyle.setFont(rta);

		Chunk fadqstyle = new Chunk("FORMA DE ADQUISICIÓN");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk xrtastyle = new Chunk("X");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk compradstyle = new Chunk("COMPRA DIRECTA:");
		compradstyle.setFont(rta);

		Chunk convstyle = new Chunk("CONVENIO:");
		convstyle.setFont(rta);

		Chunk donadostyle = new Chunk("DONADO:");
		donadostyle.setFont(rta);

		Chunk asignminstyle = new Chunk("ASIGNADO POR EL MINISTERIO:");
		asignminstyle.setFont(rta);

		Chunk asigngobstyle = new Chunk("ASIGNADO POR LA GOBERNACIÓN:");
		asigngobstyle.setFont(rtasmall);

		Chunk comodatostyle = new Chunk("COMODATO:");
		comodatostyle.setFont(rta);

		Chunk dcomprastyle = new Chunk("DATOS DE LA COMPRA");
		dcomprastyle.setFont(fuenteTituloHospital);

		LocalDate fechadefault = LocalDate.of(1111, 11, 11);
		Date fechadef = Date.valueOf(fechadefault);

		Chunk fcomprastyle = new Chunk("FECHA DE COMPRA:");
		fcomprastyle.setFont(rta);

		Chunk fcomprartastyle = new Chunk(String.valueOf(hoja_vida.getFecha_compra()));
		fcomprartastyle.setFont(rta);
		if (hoja_vida.getFecha_compra() == null || hoja_vida.getFecha_compra().equals(fechadef)) {
			fcomprartastyle = new Chunk("ND");
			fcomprartastyle.setFont(rta);
		} else {
			fcomprartastyle = new Chunk(String.valueOf(hoja_vida.getFecha_compra()));
			fcomprartastyle.setFont(rta);
		}

		Chunk finstallstyle = new Chunk("FECHA DE INSTALACIÓN:");
		finstallstyle.setFont(rta);

		Chunk finstallrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_instalacion()));
		finstallrtastyle.setFont(rta);
		if (hoja_vida.getFecha_instalacion() == null || hoja_vida.getFecha_instalacion().equals(fechadef)) {
			finstallrtastyle = new Chunk("ND");
			finstallrtastyle.setFont(rta);
		} else {
			finstallrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_instalacion()));
			finstallrtastyle.setFont(rta);
		}

		Chunk fstartstyle = new Chunk("FECHA DE INICIO DE OPERACIÓN:");
		fstartstyle.setFont(rta);

		Chunk fstartrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_iniciooperacion()));
		fstartrtastyle.setFont(rta);
		if (hoja_vida.getFecha_iniciooperacion() == null || hoja_vida.getFecha_iniciooperacion().equals(fechadef)) {
			fstartrtastyle = new Chunk("ND");
			fstartrtastyle.setFont(rta);
		} else {
			fstartrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_iniciooperacion()));
			fstartrtastyle.setFont(rta);
		}

		Chunk fvctistyle = new Chunk("FECHA VENCIMIENTO GARANTÍA:");
		fvctistyle.setFont(rta);

		Chunk fvctirtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_vencimientogarantia()));
		fvctirtastyle.setFont(rta);
		if (hoja_vida.getFecha_vencimientogarantia() == null
				|| hoja_vida.getFecha_vencimientogarantia().equals(fechadef)) {
			fvctirtastyle = new Chunk("ND");
			fvctirtastyle.setFont(rta);
		} else {
			fvctirtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_vencimientogarantia()));
			fvctirtastyle.setFont(rta);
		}

		Chunk fabstyle = new Chunk("FABRICANTE:");
		fabstyle.setFont(rta);

		Chunk fabrtastyle = new Chunk(hoja_vida.getFabricante());
		fabrtastyle.setFont(rta);

		Chunk paisstyle = new Chunk("PAIS:");
		paisstyle.setFont(rta);

		Chunk paisrtastyle = new Chunk(hoja_vida.getPaisfabricante());
		paisrtastyle.setFont(rta);

		Chunk fcostostyle = new Chunk("COSTO EN PESOS:");
		fcostostyle.setFont(rta);

		Chunk fcostortastyle = new Chunk(hoja_vida.getCosto_compra());
		fcostortastyle.setFont(rta);

		Chunk provstyle = new Chunk("PROVEEDOR:");
		provstyle.setFont(rta);

		Chunk provrtastyle = new Chunk(hoja_vida.getProveedor());
		provrtastyle.setFont(rta);

		Chunk tefpstyle = new Chunk("TELÉFONO PROVEEDOR:");
		tefpstyle.setFont(rta);

		Chunk tefprtastyle = new Chunk(hoja_vida.getTelefonoproveedor());
		tefprtastyle.setFont(rta);

		Chunk correostyle = new Chunk("CORREO:");
		correostyle.setFont(rta);

		Chunk correortastyle = new Chunk(hoja_vida.getCorreoproveedor());
		correortastyle.setFont(rta);

		Chunk ciudadstyle = new Chunk("CIUDAD:");
		ciudadstyle.setFont(rta);

		Chunk ciudadrtastyle = new Chunk(hoja_vida.getCiudadproveedor());
		ciudadrtastyle.setFont(rta);

		Chunk represtyle = new Chunk("REPRESENTANTE:");
		represtyle.setFont(rtaultrasmall);

		Chunk reprertastyle = new Chunk(hoja_vida.getRepresentante());
		reprertastyle.setFont(rta);

		Chunk tefrepstyle = new Chunk("TELÉFONO REPRESENTANTE:");
		tefrepstyle.setFont(rtaultrasmall);

		Chunk tefreprtastyle = new Chunk(hoja_vida.getTelefonorepresentante());
		tefreprtastyle.setFont(rta);

		Chunk contractstyle = new Chunk("CONTRATO:");
		contractstyle.setFont(rta);

		Chunk contractrtastyle = new Chunk(hoja_vida.getContrato());
		contractrtastyle.setFont(rta);

		PdfPTable tablaeqcom = new PdfPTable(16);
		PdfPCell datoequipo = new PdfPCell(new Phrase(dataeqstyle));
		datoequipo.setColspan(5);
		PdfPCell datoadq = new PdfPCell(new Phrase(fadqstyle));
		datoadq.setColspan(3);
		PdfPCell datocompra = new PdfPCell(new Phrase(dcomprastyle));
		datocompra.setColspan(8);

		tablaeqcom.addCell(datoequipo);
		tablaeqcom.addCell(datoadq);
		tablaeqcom.addCell(datocompra);
		// first row
		datoequipo = new PdfPCell(new Phrase(equipostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(equiportastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(compradstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isCompraddirecta()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcomprastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcomprartastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(provstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(provrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// second row
		datoequipo = new PdfPCell(new Phrase(marcastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(marcartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(convstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isConvenio()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(finstallstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(finstallrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefpstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefprtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// third row
		datoequipo = new PdfPCell(new Phrase(modelostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(modelortastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(donadostyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isDonado()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fstartstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fstartrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(correostyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(correortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fourth row
		datoequipo = new PdfPCell(new Phrase(seriestyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(seriertastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asignminstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isAsignadoporministerio()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fvctistyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fvctirtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(ciudadstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(ciudadrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fifth row
		datoequipo = new PdfPCell(new Phrase(placastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(placartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asigngobstyle));
		datoadq.setRowspan(2);
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isAsignadoporgobernacion()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			datoadq.setRowspan(2);

		} else {
			datoadq = new PdfPCell(new Phrase(""));
			datoadq.setRowspan(2);
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fabstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fabrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(represtyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(reprertastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// sixth row
		datoequipo = new PdfPCell(new Phrase(anofstyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(anofrtastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);
		PdfPCell celdanula = new PdfPCell();
		celdanula.setBorder(Rectangle.NO_BORDER);
		tablaeqcom.addCell(celdanula);

		datocompra = new PdfPCell(new Phrase(paisstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(paisrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefrepstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefreprtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// seven row
		datoequipo = new PdfPCell(new Phrase(invimastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(invimartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(comodatostyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isComodato()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcostostyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcostortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		tablaeqcom.setSpacingAfter(10);

		// table data

		Chunk tecnicstyle = new Chunk("REGISTRO TÉCNICO");
		tecnicstyle.setFont(fuenteEnunciados);

		Chunk vacrstyle = new Chunk("V");
		vacrstyle.setFont(rta);

		Chunk iacrstyle = new Chunk("A");
		iacrstyle.setFont(rta);

		Chunk pacrstyle = new Chunk("PSI");
		pacrstyle.setFont(rta);

		Chunk wacrstyle = new Chunk("W");
		wacrstyle.setFont(rta);

		Chunk fzacrstyle = new Chunk("Hz");
		fzacrstyle.setFont(rta);

		Chunk rpmacrstyle = new Chunk("RPM");
		rpmacrstyle.setFont(rta);

		Chunk tempacrstyle = new Chunk("°C");
		tempacrstyle.setFont(rta);

		Chunk weightacrstyle = new Chunk("Kg");
		weightacrstyle.setFont(rta);

		Chunk capacacrstyle = new Chunk("L");
		capacacrstyle.setFont(rta);

		Chunk vmaxstyle = new Chunk("VOLTAJE MÁXIMO:");
		vmaxstyle.setFont(rta);

		Chunk vmaxrtastyle = new Chunk(hoja_vida.getVmaxoperacion());
		vmaxrtastyle.setFont(rta);

		Chunk presionstyle = new Chunk("PRESIÓN:");
		presionstyle.setFont(rta);

		Chunk presionrtastyle = new Chunk(hoja_vida.getPresion());
		presionrtastyle.setFont(rta);

		Chunk vminstyle = new Chunk("VOLTAJE MÍNIMO:");
		vminstyle.setFont(rta);

		Chunk vminrtastyle = new Chunk(hoja_vida.getVminoperacion());
		vminrtastyle.setFont(rta);

		Chunk velstyle = new Chunk("VELOCIDAD:");
		velstyle.setFont(rta);

		Chunk velrtastyle = new Chunk(hoja_vida.getVelocidad());
		velrtastyle.setFont(rta);

		Chunk imaxstyle = new Chunk("CORRIENTE MÁXIMA:");
		imaxstyle.setFont(rta);

		Chunk imaxrtastyle = new Chunk(hoja_vida.getImaxoperacion());
		imaxrtastyle.setFont(rta);

		Chunk tempstyle = new Chunk("TEMPERATURA:");
		tempstyle.setFont(rta);

		Chunk temprtastyle = new Chunk(hoja_vida.getTemperatura());
		temprtastyle.setFont(rta);

		Chunk iminstyle = new Chunk("CORRIENTE MÍNIMA:");
		iminstyle.setFont(rta);

		Chunk iminrtastyle = new Chunk(hoja_vida.getIminoperacion());
		iminrtastyle.setFont(rta);

		Chunk weightstyle = new Chunk("PESO:");
		weightstyle.setFont(rta);

		Chunk weightrtastyle = new Chunk(hoja_vida.getPeso());
		weightrtastyle.setFont(rta);

		Chunk powerstyle = new Chunk("POTENCIA CONSUMIDA:");
		powerstyle.setFont(rta);

		Chunk powerrtastyle = new Chunk(hoja_vida.getWconsumida());
		powerrtastyle.setFont(rta);

		Chunk capacitystyle = new Chunk("CAPACIDAD:");
		capacitystyle.setFont(rta);

		Chunk capacityrtastyle = new Chunk(hoja_vida.getCapacidad());
		capacityrtastyle.setFont(rta);

		Chunk frecuencystyle = new Chunk("FRECUENCIA:");
		frecuencystyle.setFont(rta);

		Chunk frecuencyrtastyle = new Chunk(hoja_vida.getFrecuencia());
		frecuencyrtastyle.setFont(rta);

		Chunk otrotecstyle = new Chunk("OTROS:");
		otrotecstyle.setFont(rta);

		Chunk otrotecrtastyle = new Chunk(hoja_vida.getOtrosdatostecnicos());
		otrotecrtastyle.setFont(rta);

		Chunk portstyle = new Chunk("EQUIPO PORTATIL:");
		portstyle.setFont(writers);

		Chunk portrtastyle = new Chunk("");
		if (hoja_vida.isEquipotipoportatil()) {
			portrtastyle = new Chunk("SI");
			portrtastyle.setFont(rta);
		} else {
			portrtastyle = new Chunk("NO");
			portrtastyle.setFont(rta);
		}

		Chunk fijostyle = new Chunk("EQUIPO FIJO:");
		fijostyle.setFont(writers);

		Chunk fijortastyle = new Chunk("");
		if (hoja_vida.isEquipotipofijo()) {
			fijortastyle = new Chunk("SI");
			fijortastyle.setFont(rta);
		} else {
			fijortastyle = new Chunk("NO");
			fijortastyle.setFont(rta);
		}

		Chunk sourcestyle = new Chunk("FUENTE DE ALIMENTACIÓN:");
		sourcestyle.setFont(fuenteEnunciados);

		Chunk elecsourcestyle = new Chunk("ELECTRICIDAD:");
		elecsourcestyle.setFont(rta);

		Chunk solarsourcestyle = new Chunk("ENERGIA SOLAR:");
		solarsourcestyle.setFont(rta);

		Chunk watersourcestyle = new Chunk("AGUA:");
		watersourcestyle.setFont(rta);

		Chunk gassourcestyle = new Chunk("GAS:");
		gassourcestyle.setFont(rta);

		Chunk vaporsourcestyle = new Chunk("VAPOR DE AGUA:");
		vaporsourcestyle.setFont(rta);

		Chunk psourcestyle = new Chunk("DERIVADOS DEL PETROLEO:");
		psourcestyle.setFont(rta);

		Chunk osourcestyle = new Chunk("OTROS:");
		osourcestyle.setFont(rta);

		Chunk apoyostyle = new Chunk("REGISTRO DE APOYO TÉCNICO:");
		apoyostyle.setFont(fuenteEnunciados);

		Chunk manualstyle = new Chunk("MANUALES:");
		manualstyle.setFont(writers);

		Chunk opuserstyle = new Chunk("OPERACIONAL-USUARIO:");
		opuserstyle.setFont(rta);

		Chunk tecmanualstyle = new Chunk("TÉCNICO:");
		tecmanualstyle.setFont(rta);

		Chunk usostyle = new Chunk("USO:");
		usostyle.setFont(writers);

		Chunk usomedstyle = new Chunk("MÉDICO:");
		usomedstyle.setFont(rta);

		Chunk usobacstyle = new Chunk("BÁSICO:");
		usobacstyle.setFont(rta);

		Chunk usoapostyle = new Chunk("APOYO:");
		usoapostyle.setFont(rta);

		PdfPTable tablatecnica = new PdfPTable(14);

		// firstrowtec
		PdfPCell regtecstyle = new PdfPCell(new Phrase(tecnicstyle));
		regtecstyle.setColspan(8);
		tablatecnica.addCell(regtecstyle);
		PdfPCell sourcealstyle = new PdfPCell(new Phrase(sourcestyle));
		sourcealstyle.setColspan(3);
		tablatecnica.addCell(sourcealstyle);
		PdfPCell apoyoteccell = new PdfPCell(new Phrase(apoyostyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// secondrowtec
		regtecstyle = new PdfPCell(new Phrase(vmaxstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vmaxrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(velstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(velrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(pacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(elecsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);

		if (hoja_vida.isFuenteaelectricidad()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(sourcealstyle);

		apoyoteccell = new PdfPCell(new Phrase(manualstyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// thirdrowtec
		regtecstyle = new PdfPCell(new Phrase(vminstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vminrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(presionstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(presionrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(rpmacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(solarsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);

		if (hoja_vida.isFuenteaenergiasolar()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(opuserstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);

		if (hoja_vida.isManual_operacion()) {
			apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
			apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			apoyoteccell = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(apoyoteccell);

		// fourthrowtec
		regtecstyle = new PdfPCell(new Phrase(imaxstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(imaxrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(tempstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(temprtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(tempacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(watersourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		if (hoja_vida.isFuenteaagua()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(tecmanualstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);

		if (hoja_vida.isManual_tecnico()) {
			apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
			apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			apoyoteccell = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(apoyoteccell);

		// fifthyrowtec
		regtecstyle = new PdfPCell(new Phrase(iminstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iminrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(gassourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		if (hoja_vida.isFuenteagas()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usostyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// sixrowtec
		regtecstyle = new PdfPCell(new Phrase(powerstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(powerrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(wacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacitystyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacityrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(vaporsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		if (hoja_vida.isFuenteavaporagua()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usomedstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);

		if (hoja_vida.isUsomedico()) {
			apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
			apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			apoyoteccell = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(apoyoteccell);

		// sevenrowtec
		regtecstyle = new PdfPCell(new Phrase(frecuencystyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(frecuencyrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(fzacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(otrotecstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(otrotecrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(""));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(psourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		if (hoja_vida.isFuenteaderivadospetroleo()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usobacstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);

		if (hoja_vida.isUsobasico()) {
			apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
			apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			apoyoteccell = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(apoyoteccell);

		// eightyrowtec
		regtecstyle = new PdfPCell(new Phrase(portstyle));
		regtecstyle.setColspan(3);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(portrtastyle));
		tablatecnica.addCell(regtecstyle);

		regtecstyle = new PdfPCell(new Phrase(fijostyle));
		regtecstyle.setColspan(3);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(fijortastyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(osourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		if (hoja_vida.isFuenteaotros()) {
			sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
			sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			sourcealstyle = new PdfPCell(new Phrase(""));
		}

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usoapostyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);

		if (hoja_vida.isUsoapoyo()) {
			apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
			apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			apoyoteccell = new PdfPCell(new Phrase(""));
		}
		tablatecnica.addCell(apoyoteccell);

		tablatecnica.setSpacingAfter(10);
		// table class
		Chunk riesgotstyle = new Chunk("RIESGO:");
		riesgotstyle.setFont(fuenteEnunciados);

		Chunk riesgoistyle = new Chunk("I:");
		riesgoistyle.setFont(rta);

		Chunk riesgoiiastyle = new Chunk("IIA:");
		riesgoiiastyle.setFont(rta);

		Chunk riesgoiibstyle = new Chunk("IIB:");
		riesgoiibstyle.setFont(rta);

		Chunk riesgoiiistyle = new Chunk("III:");
		riesgoiiistyle.setFont(rta);

		Chunk tecpredstyle = new Chunk("CLASE DE TECNOLOGÍA PREDOMINANTE:");
		tecpredstyle.setFont(fuenteEnunciados);

		Chunk celecstyle = new Chunk("ELÉCTRICO:");
		celecstyle.setFont(rta);

		Chunk celectronicstyle = new Chunk("ELECTRÓNICO:");
		celectronicstyle.setFont(rta);

		Chunk cmecstyle = new Chunk("MECÁNICO:");
		cmecstyle.setFont(rta);

		Chunk celectromecstyle = new Chunk("ELECTROMECÁNICO:");
		celectromecstyle.setFont(rtasmall);

		Chunk chidstyle = new Chunk("HIDRAÚLICO:");
		chidstyle.setFont(rta);

		Chunk cneustyle = new Chunk("NEUMÁTICO:");
		cneustyle.setFont(rta);

		Chunk cvapstyle = new Chunk("VAPOR:");
		cvapstyle.setFont(rta);

		Chunk csolstyle = new Chunk("SOLAR:");
		csolstyle.setFont(rta);

		Chunk periodstyle = new Chunk("PERIODICIDAD DEL MANTENIMIENTO:");
		periodstyle.setFont(fuenteEnunciados);

		Chunk ptristyle = new Chunk("TRIMESTRAL:");
		ptristyle.setFont(rta);

		Chunk pcuatristyle = new Chunk("CUATRIMESTRAL:");
		pcuatristyle.setFont(rta);

		Chunk psemstyle = new Chunk("SEMESTRAL:");
		psemstyle.setFont(rta);

		Chunk panualstyle = new Chunk("ANUAL:");
		panualstyle.setFont(rta);

		Chunk responmttostyle = new Chunk("MANTENIMIENTO ACTUAL:");
		responmttostyle.setFont(fuenteEnunciados);

		Chunk mttopropstyle = new Chunk("PROPIO:");
		mttopropstyle.setFont(rta);

		Chunk mttocontstyle = new Chunk("CONTRATADO:");
		mttocontstyle.setFont(rta);

		Chunk mttocomostyle = new Chunk("COMODATO:");
		mttocomostyle.setFont(rta);

		Chunk mttogarstyle = new Chunk("GARANTÍA:");
		mttogarstyle.setFont(rta);

		PdfPTable tablaclass = new PdfPTable(18);

		// firstrowclass
		PdfPCell tecnopredcell = new PdfPCell(new Phrase(tecpredstyle));
		tecnopredcell.setColspan(8);
		PdfPCell riesgocell = new PdfPCell(new Phrase(riesgotstyle));
		riesgocell.setColspan(2);
		PdfPCell freqmttocell = new PdfPCell(new Phrase(periodstyle));
		freqmttocell.setColspan(4);
		PdfPCell responmttocell = new PdfPCell(new Phrase(responmttostyle));
		responmttocell.setColspan(4);
		tablaclass.addCell(tecnopredcell);
		tablaclass.addCell(riesgocell);
		tablaclass.addCell(freqmttocell);
		tablaclass.addCell(responmttocell);
		// secondrowclass
		tecnopredcell = new PdfPCell(new Phrase(celecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClaseelectrico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(celectronicstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClaseelectronico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoistyle));
		tablaclass.addCell(riesgocell);
		if (hoja_vida.isRiesgoi()) {
			riesgocell = new PdfPCell(new Phrase(xrtastyle));
			riesgocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			riesgocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(ptristyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		if (hoja_vida.getEquipo().getPeriodicidad() == 3) {
			freqmttocell = new PdfPCell(new Phrase(xrtastyle));
			freqmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			freqmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttopropstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		if (hoja_vida.isMapropio()) {
			responmttocell = new PdfPCell(new Phrase(xrtastyle));
			responmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			responmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(responmttocell);

		// thirdrowclass
		tecnopredcell = new PdfPCell(new Phrase(cmecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClasemecanico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(celectromecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClaseelectromecanico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiiastyle));
		tablaclass.addCell(riesgocell);
		if (hoja_vida.isRiesgoiia()) {
			riesgocell = new PdfPCell(new Phrase(xrtastyle));
			riesgocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			riesgocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(pcuatristyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		if (hoja_vida.getEquipo().getPeriodicidad() == 4) {
			freqmttocell = new PdfPCell(new Phrase(xrtastyle));
			freqmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			freqmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttocontstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		if (hoja_vida.isMacontratado()) {
			responmttocell = new PdfPCell(new Phrase(xrtastyle));
			responmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			responmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(responmttocell);
		// fourthrowclass
		tecnopredcell = new PdfPCell(new Phrase(chidstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClasehidraulico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(cneustyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClaseneumatico()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiibstyle));
		tablaclass.addCell(riesgocell);
		if (hoja_vida.isRiesgoiib()) {
			riesgocell = new PdfPCell(new Phrase(xrtastyle));
			riesgocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			riesgocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(psemstyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		if (hoja_vida.getEquipo().getPeriodicidad() == 2) {
			freqmttocell = new PdfPCell(new Phrase(xrtastyle));
			freqmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			freqmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttocomostyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		if (hoja_vida.isComodato()) {
			responmttocell = new PdfPCell(new Phrase(xrtastyle));
			responmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			responmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(responmttocell);
		// fifthyrowclass
		tecnopredcell = new PdfPCell(new Phrase(cvapstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClasevapor()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(csolstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		if (hoja_vida.isClasesolar()) {
			tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
			tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			tecnopredcell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiiistyle));
		tablaclass.addCell(riesgocell);
		if (hoja_vida.isRiesgoiii()) {
			riesgocell = new PdfPCell(new Phrase(xrtastyle));
			riesgocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			riesgocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(panualstyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		if (hoja_vida.getEquipo().getPeriodicidad() == 1) {
			freqmttocell = new PdfPCell(new Phrase(xrtastyle));
			freqmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			freqmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttogarstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		if (hoja_vida.isMagarantia()) {
			responmttocell = new PdfPCell(new Phrase(xrtastyle));
			responmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			responmttocell = new PdfPCell(new Phrase(""));
		}
		tablaclass.addCell(responmttocell);

		// tableaccessories
		Chunk accstyle = new Chunk("ACCESORIOS DEL EQUIPO:");
		accstyle.setFont(fuenteEnunciados);

		Chunk acc1style = new Chunk(hoja_vida.getAccesorio1());
		acc1style.setFont(rta);

		Chunk acc2style = new Chunk(hoja_vida.getAccesorio2());
		acc2style.setFont(rta);

		Chunk acc3style = new Chunk(hoja_vida.getAccesorio3());
		acc3style.setFont(rta);

		Chunk acc4style = new Chunk(hoja_vida.getAccesorio4());
		acc4style.setFont(rta);

		Chunk calovalstyle = new Chunk("REQUIERE CALIBRACIÓN Y/O VALIDACIÓN:");
		calovalstyle.setFont(fuenteEnunciados);

		Chunk calsistyle = new Chunk("SI:");
		calsistyle.setFont(rta);

		Chunk calnostyle = new Chunk("NO:");
		calnostyle.setFont(rta);

		Chunk pvalcalstyle = new Chunk("PERIODICIDAD");
		pvalcalstyle.setFont(fuenteEnunciados);

		tablaclass.setSpacingAfter(10);

		PdfPTable tablefinal = new PdfPTable(16);

		PdfPTable tableacc = new PdfPTable(1);
		PdfPCell accesory = new PdfPCell(new Phrase(accstyle));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc1style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc2style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc3style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc4style));
		tableacc.addCell(accesory);

		PdfPCell accell = new PdfPCell(tableacc);
		accell.setColspan(6);
		accell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		accell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPTable tablecalval = new PdfPTable(4);
		PdfPCell valcalcell = new PdfPCell(tablecalval);
		valcalcell.setColspan(3);
		valcalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		valcalcell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell calvalcell = new PdfPCell(new Phrase(calovalstyle));
		calvalcell.setColspan(4);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(calsistyle));
		tablecalval.addCell(calvalcell);
		if (hoja_vida.isRequierecalibracion()) {
			calvalcell = new PdfPCell(new Phrase(xrtastyle));
			calvalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			calvalcell = new PdfPCell(new Phrase(""));
		}
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(calnostyle));
		tablecalval.addCell(calvalcell);
		if (hoja_vida.isNorequierecalibracion()) {
			calvalcell = new PdfPCell(new Phrase(xrtastyle));
			calvalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			calvalcell = new PdfPCell(new Phrase(""));
		}
		tablecalval.addCell(calvalcell);

		calvalcell = new PdfPCell(new Phrase(pvalcalstyle));
		calvalcell.setColspan(4);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(psemstyle));
		calvalcell.setColspan(3);
		tablecalval.addCell(calvalcell);
		if (hoja_vida.isPcalsemestral()) {
			calvalcell = new PdfPCell(new Phrase(xrtastyle));
			calvalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			calvalcell = new PdfPCell(new Phrase(""));
		}
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(panualstyle));
		calvalcell.setColspan(3);
		tablecalval.addCell(calvalcell);
		if (hoja_vida.isPcalanual()) {
			calvalcell = new PdfPCell(new Phrase(xrtastyle));
			calvalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			calvalcell = new PdfPCell(new Phrase(""));
		}
		tablecalval.addCell(calvalcell);

		Chunk propstyle = new Chunk("PROPIEDAD DEL EQUIPO:");
		propstyle.setFont(fuenteEnunciados);

		Chunk prohospitalstyle = new Chunk("HOSPITAL:");
		prohospitalstyle.setFont(rta);

		Chunk proproveedorstyle = new Chunk("PROVEEDOR:");
		proproveedorstyle.setFont(rtasmall);

		Chunk protrostyle = new Chunk("OTRO:");
		protrostyle.setFont(rtasmall);

		Chunk biomedicstyle = new Chunk("CLASIFICACIÓN BIOMÉDICA");
		biomedicstyle.setFont(fuenteEnunciados);

		Chunk diagstyle = new Chunk("DIAGNÓSTICO:");
		diagstyle.setFont(rta);

		Chunk rehabstyle = new Chunk("REHABILITACIÓN:");
		rehabstyle.setFont(rta);

		Chunk preventstyle = new Chunk("PREVENCIÓN:");
		preventstyle.setFont(rta);

		Chunk analystyle = new Chunk("ANÁLISIS DE LABORATORIO:");
		analystyle.setFont(rta);

		Chunk treatstyle = new Chunk("TRATAMIENTO Y MANTENIMIENTO A LA VIDA:");
		treatstyle.setFont(rta);

		PdfPTable tableclassbio = new PdfPTable(8);
		PdfPCell classbiocell = new PdfPCell(new Phrase(biomedicstyle));
		classbiocell.setColspan(8);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(diagstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		if (hoja_vida.isBiomedicdiagnostico()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(rehabstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		if (hoja_vida.isBiomedicrehabilitacion()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(preventstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		if (hoja_vida.isBiomedicprevencion()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(analystyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		if (hoja_vida.isBiomedicanalisis()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(treatstyle));
		classbiocell.setColspan(7);
		tableclassbio.addCell(classbiocell);

		if (hoja_vida.isBiomedictratamiento()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(propstyle));
		classbiocell.setColspan(8);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(prohospitalstyle));
		classbiocell.setColspan(2);
		tableclassbio.addCell(classbiocell);
		if (hoja_vida.isProphospital()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(proproveedorstyle));
		classbiocell.setColspan(2);
		tableclassbio.addCell(classbiocell);
		if (hoja_vida.isPropproveedor()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(protrostyle));
		tableclassbio.addCell(classbiocell);
		if (hoja_vida.isPropotro()) {
			classbiocell = new PdfPCell(new Phrase(xrtastyle));
			classbiocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		else {
			classbiocell = new PdfPCell(new Phrase(""));
		}
		tableclassbio.addCell(classbiocell);

		PdfPCell biomedicell = new PdfPCell(tableclassbio);
		biomedicell.setColspan(7);

		PdfPTable tableobs = new PdfPTable(8);

		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(fuenteEnunciados);
		PdfPCell obs = new PdfPCell(new Phrase(obsstyle));

		obs.setColspan(2);
		obs.setMinimumHeight(20);
		tableobs.addCell(obs);
		Chunk obssrta = new Chunk(hoja_vida.getObservaciones());
		obssrta.setFont(rta);
		obs = new PdfPCell(new Phrase(obssrta));
		obs.setColspan(6);
		tableobs.addCell(obs);

		Chunk espnull = new Chunk("ND: NO DISPONIBLE NR: NO REGISTRA NE: NO ESPECIFICA NA: NO APLICA");
		espnull.setFont(rta);
		obs = new PdfPCell(new Phrase(espnull));
		obs.setColspan(8);
		obs.setBorder(Rectangle.NO_BORDER);
		tableobs.addCell(obs);

		tablefinal.addCell(accell);
		tablefinal.addCell(valcalcell);
		tablefinal.addCell(biomedicell);

		tablefinal.setSpacingAfter(10);

		document.add(tabla);
		document.add(tabladhos);
		document.add(tablaeqcom);
		document.add(tablatecnica);
		document.add(tablaclass);
		document.add(tablefinal);
		document.add(tableobs);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalHVPDF() throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("HOJA DE VIDA DIGITAL EQUIPO BIOMEDICO HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO IB-F-25");
		Chunk vs = new Chunk("VERSION: 02");
		Chunk date = new Chunk("Fecha:21/09/2022");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk idenstyle = new Chunk("IDENTIFICACIÓN");
		idenstyle.setFont(fuenteTituloHospital);

		Chunk depstyle = new Chunk("DEPARTAMENTO:");
		depstyle.setFont(fuenteEnunciados);

		Chunk deprtastyle = new Chunk("");
		deprtastyle.setFont(rta);

		Chunk munstyle = new Chunk("MUNICIPIO:");
		munstyle.setFont(fuenteEnunciados);

		Chunk munrtastyle = new Chunk("");
		munrtastyle.setFont(rta);

		Chunk addresstyle = new Chunk("DIRECCIÓN:");
		addresstyle.setFont(fuenteEnunciados);

		Chunk addresrtastyle = new Chunk("");
		addresrtastyle.setFont(rta);

		Chunk telestyle = new Chunk("TELÉFONO:");
		telestyle.setFont(fuenteEnunciados);

		Chunk telertastyle = new Chunk("");
		telertastyle.setFont(rta);

		Chunk emailstyle = new Chunk("E-MAIL:");
		emailstyle.setFont(fuenteEnunciados);

		Chunk emailrtastyle = new Chunk("");
		emailrtastyle.setFont(correo);

		Chunk codstyle = new Chunk("COD. INTERNACIONAL:");
		codstyle.setFont(fuenteEnunciados);

		Chunk codrtastyle = new Chunk("");
		codrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteEnunciados);

		Chunk servicertastyle = new Chunk("");
		servicertastyle.setFont(fuenteEnunciados);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteEnunciados);

		Chunk ubrtastyle = new Chunk("");
		ubrtastyle.setFont(fuenteEnunciados);

		PdfPTable tabladhos = new PdfPTable(5);

		PdfPCell idencell = new PdfPCell(new Phrase(idenstyle));
		idencell.setColspan(6);
		idencell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell fotocell = new PdfPCell(new Phrase(""));

		fotocell.setRowspan(4);
		fotocell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		fotocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		fotocell.setMinimumHeight(80);

		tabladhos.addCell(idencell);

		tabladhos.addCell(new PdfPCell(new Phrase(depstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(deprtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munrtastyle)));
		tabladhos.addCell(fotocell);
		tabladhos.addCell(new PdfPCell(new Phrase(addresstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(addresrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telestyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telertastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(emailstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(emailrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codrtastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(serviceestyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(servicertastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubrtastyle)));

		tabladhos.setSpacingAfter(10);

		Chunk dataeqstyle = new Chunk("DATOS DEL EQUIPO");
		dataeqstyle.setFont(fuenteTituloHospital);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(rta);

		Chunk equiportastyle = new Chunk("");
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(rta);

		Chunk marcartastyle = new Chunk("");
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(rta);

		Chunk modelortastyle = new Chunk("");
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(rta);

		Chunk seriertastyle = new Chunk("");
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(rta);

		Chunk placartastyle = new Chunk("");
		placartastyle.setFont(rta);

		Chunk anofstyle = new Chunk("AÑO DE FABRICACIÓN:");
		anofstyle.setFont(rtasmall);

		Chunk anofrtastyle = new Chunk("");
		anofrtastyle.setFont(rta);

		Chunk invimastyle = new Chunk("REGISTRO INVIMA:");
		invimastyle.setFont(rta);

		Chunk invimartastyle = new Chunk("");
		invimartastyle.setFont(rta);

		Chunk fadqstyle = new Chunk("FORMA DE ADQUISICIÓN");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk xrtastyle = new Chunk("X");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk compradstyle = new Chunk("COMPRA DIRECTA:");
		compradstyle.setFont(rta);

		Chunk convstyle = new Chunk("CONVENIO:");
		convstyle.setFont(rta);

		Chunk donadostyle = new Chunk("DONADO:");
		donadostyle.setFont(rta);

		Chunk asignminstyle = new Chunk("ASIGNADO POR EL MINISTERIO:");
		asignminstyle.setFont(rta);

		Chunk asigngobstyle = new Chunk("ASIGNADO POR LA GOBERNACIÓN:");
		asigngobstyle.setFont(rtasmall);

		Chunk comodatostyle = new Chunk("COMODATO:");
		comodatostyle.setFont(rta);

		Chunk dcomprastyle = new Chunk("DATOS DE LA COMPRA");
		dcomprastyle.setFont(fuenteTituloHospital);

		Chunk fcomprastyle = new Chunk("FECHA DE COMPRA:");
		fcomprastyle.setFont(rta);

		Chunk fcomprartastyle = new Chunk("");
		fcomprartastyle.setFont(rta);

		Chunk finstallstyle = new Chunk("FECHA DE INSTALACIÓN:");
		finstallstyle.setFont(rta);

		Chunk finstallrtastyle = new Chunk("");
		finstallrtastyle.setFont(rta);

		Chunk fstartstyle = new Chunk("FECHA DE INICIO DE OPERACIÓN:");
		fstartstyle.setFont(rta);

		Chunk fstartrtastyle = new Chunk("");
		fstartrtastyle.setFont(rta);

		Chunk fvctistyle = new Chunk("FECHA VENCIMIENTO GARANTÍA:");
		fvctistyle.setFont(rta);

		Chunk fvctirtastyle = new Chunk("");
		fvctirtastyle.setFont(rta);

		Chunk fabstyle = new Chunk("FABRICANTE:");
		fabstyle.setFont(rta);

		Chunk fabrtastyle = new Chunk("");
		fabrtastyle.setFont(rta);

		Chunk paisstyle = new Chunk("PAIS:");
		paisstyle.setFont(rta);

		Chunk paisrtastyle = new Chunk("");
		paisrtastyle.setFont(rta);

		Chunk fcostostyle = new Chunk("COSTO EN PESOS:");
		fcostostyle.setFont(rta);

		Chunk fcostortastyle = new Chunk("");
		fcostortastyle.setFont(rta);

		Chunk provstyle = new Chunk("PROVEEDOR:");
		provstyle.setFont(rta);

		Chunk provrtastyle = new Chunk("");
		provrtastyle.setFont(rta);

		Chunk tefpstyle = new Chunk("TELÉFONO PROVEEDOR:");
		tefpstyle.setFont(rta);

		Chunk tefprtastyle = new Chunk("");
		tefprtastyle.setFont(rta);

		Chunk correostyle = new Chunk("CORREO:");
		correostyle.setFont(rta);

		Chunk correortastyle = new Chunk("");
		correortastyle.setFont(rta);

		Chunk ciudadstyle = new Chunk("CIUDAD:");
		ciudadstyle.setFont(rta);

		Chunk ciudadrtastyle = new Chunk("");
		ciudadrtastyle.setFont(rta);

		Chunk represtyle = new Chunk("REPRESENTANTE:");
		represtyle.setFont(rtaultrasmall);

		Chunk reprertastyle = new Chunk("");
		reprertastyle.setFont(rta);

		Chunk tefrepstyle = new Chunk("TELÉFONO REPRESENTANTE:");
		tefrepstyle.setFont(rtaultrasmall);

		Chunk tefreprtastyle = new Chunk("");
		tefreprtastyle.setFont(rta);

		Chunk contractstyle = new Chunk("CONTRATO:");
		contractstyle.setFont(rta);

		Chunk contractrtastyle = new Chunk("");
		contractrtastyle.setFont(rta);

		PdfPTable tablaeqcom = new PdfPTable(16);
		PdfPCell datoequipo = new PdfPCell(new Phrase(dataeqstyle));
		datoequipo.setColspan(5);
		PdfPCell datoadq = new PdfPCell(new Phrase(fadqstyle));
		datoadq.setColspan(3);
		PdfPCell datocompra = new PdfPCell(new Phrase(dcomprastyle));
		datocompra.setColspan(8);

		tablaeqcom.addCell(datoequipo);
		tablaeqcom.addCell(datoadq);
		tablaeqcom.addCell(datocompra);
		// first row
		datoequipo = new PdfPCell(new Phrase(equipostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(equiportastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(compradstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcomprastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcomprartastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(provstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(provrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// second row
		datoequipo = new PdfPCell(new Phrase(marcastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(marcartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(convstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(finstallstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(finstallrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefpstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefprtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// third row
		datoequipo = new PdfPCell(new Phrase(modelostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(modelortastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(donadostyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fstartstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fstartrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(correostyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(correortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fourth row
		datoequipo = new PdfPCell(new Phrase(seriestyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(seriertastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asignminstyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fvctistyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fvctirtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(ciudadstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(ciudadrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fifth row
		datoequipo = new PdfPCell(new Phrase(placastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(placartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asigngobstyle));
		datoadq.setRowspan(2);
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));
		datoadq.setRowspan(2);

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fabstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fabrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(represtyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(reprertastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// sixth row
		datoequipo = new PdfPCell(new Phrase(anofstyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(anofrtastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);
		PdfPCell celdanula = new PdfPCell();
		celdanula.setBorder(Rectangle.NO_BORDER);
		tablaeqcom.addCell(celdanula);

		datocompra = new PdfPCell(new Phrase(paisstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(paisrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefrepstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(tefreprtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// seven row
		datoequipo = new PdfPCell(new Phrase(invimastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(invimartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(comodatostyle));
		datoadq.setColspan(2);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcostostyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcostortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractstyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		tablaeqcom.setSpacingAfter(10);

		// table data

		Chunk tecnicstyle = new Chunk("REGISTRO TÉCNICO");
		tecnicstyle.setFont(fuenteEnunciados);

		Chunk vacrstyle = new Chunk("V");
		vacrstyle.setFont(rta);

		Chunk iacrstyle = new Chunk("A");
		iacrstyle.setFont(rta);

		Chunk pacrstyle = new Chunk("PSI");
		pacrstyle.setFont(rta);

		Chunk wacrstyle = new Chunk("W");
		wacrstyle.setFont(rta);

		Chunk fzacrstyle = new Chunk("Hz");
		fzacrstyle.setFont(rta);

		Chunk rpmacrstyle = new Chunk("RPM");
		rpmacrstyle.setFont(rta);

		Chunk tempacrstyle = new Chunk("°C");
		tempacrstyle.setFont(rta);

		Chunk weightacrstyle = new Chunk("Kg");
		weightacrstyle.setFont(rta);

		Chunk capacacrstyle = new Chunk("L");
		capacacrstyle.setFont(rta);

		Chunk vmaxstyle = new Chunk("VOLTAJE MÁXIMO:");
		vmaxstyle.setFont(rta);

		Chunk vmaxrtastyle = new Chunk("");
		vmaxrtastyle.setFont(rta);

		Chunk presionstyle = new Chunk("PRESIÓN:");
		presionstyle.setFont(rta);

		Chunk presionrtastyle = new Chunk("");
		presionrtastyle.setFont(rta);

		Chunk vminstyle = new Chunk("VOLTAJE MÍNIMO:");
		vminstyle.setFont(rta);

		Chunk vminrtastyle = new Chunk("");
		vminrtastyle.setFont(rta);

		Chunk velstyle = new Chunk("VELOCIDAD:");
		velstyle.setFont(rta);

		Chunk velrtastyle = new Chunk("");
		velrtastyle.setFont(rta);

		Chunk imaxstyle = new Chunk("CORRIENTE MÁXIMA:");
		imaxstyle.setFont(rta);

		Chunk imaxrtastyle = new Chunk("");
		imaxrtastyle.setFont(rta);

		Chunk tempstyle = new Chunk("TEMPERATURA:");
		tempstyle.setFont(rta);

		Chunk temprtastyle = new Chunk("");
		temprtastyle.setFont(rta);

		Chunk iminstyle = new Chunk("CORRIENTE MÍNIMA:");
		iminstyle.setFont(rta);

		Chunk iminrtastyle = new Chunk("");
		iminrtastyle.setFont(rta);

		Chunk weightstyle = new Chunk("PESO:");
		weightstyle.setFont(rta);

		Chunk weightrtastyle = new Chunk("");
		weightrtastyle.setFont(rta);

		Chunk powerstyle = new Chunk("POTENCIA CONSUMIDA:");
		powerstyle.setFont(rta);

		Chunk powerrtastyle = new Chunk("");
		powerrtastyle.setFont(rta);

		Chunk capacitystyle = new Chunk("CAPACIDAD:");
		capacitystyle.setFont(rta);

		Chunk capacityrtastyle = new Chunk("");
		capacityrtastyle.setFont(rta);

		Chunk frecuencystyle = new Chunk("FRECUENCIA:");
		frecuencystyle.setFont(rta);

		Chunk frecuencyrtastyle = new Chunk("");
		frecuencyrtastyle.setFont(rta);

		Chunk otrotecstyle = new Chunk("OTROS:");
		otrotecstyle.setFont(rta);

		Chunk otrotecrtastyle = new Chunk("");
		otrotecrtastyle.setFont(rta);

		Chunk portstyle = new Chunk("EQUIPO PORTATIL:");
		portstyle.setFont(writers);

		Chunk portrtastyle = new Chunk("");
		portrtastyle = new Chunk("");
		portrtastyle.setFont(rta);

		Chunk fijostyle = new Chunk("EQUIPO FIJO:");
		fijostyle.setFont(writers);

		Chunk fijortastyle = new Chunk("");
		fijortastyle = new Chunk("");
		fijortastyle.setFont(rta);

		Chunk sourcestyle = new Chunk("FUENTE DE ALIMENTACIÓN:");
		sourcestyle.setFont(fuenteEnunciados);

		Chunk elecsourcestyle = new Chunk("ELECTRICIDAD:");
		elecsourcestyle.setFont(rta);

		Chunk solarsourcestyle = new Chunk("ENERGIA SOLAR:");
		solarsourcestyle.setFont(rta);

		Chunk watersourcestyle = new Chunk("AGUA:");
		watersourcestyle.setFont(rta);

		Chunk gassourcestyle = new Chunk("GAS:");
		gassourcestyle.setFont(rta);

		Chunk vaporsourcestyle = new Chunk("VAPOR DE AGUA:");
		vaporsourcestyle.setFont(rta);

		Chunk psourcestyle = new Chunk("DERIVADOS DEL PETROLEO:");
		psourcestyle.setFont(rta);

		Chunk osourcestyle = new Chunk("OTROS:");
		osourcestyle.setFont(rta);

		Chunk apoyostyle = new Chunk("REGISTRO DE APOYO TÉCNICO:");
		apoyostyle.setFont(fuenteEnunciados);

		Chunk manualstyle = new Chunk("MANUALES:");
		manualstyle.setFont(writers);

		Chunk opuserstyle = new Chunk("OPERACIONAL-USUARIO:");
		opuserstyle.setFont(rta);

		Chunk tecmanualstyle = new Chunk("TÉCNICO:");
		tecmanualstyle.setFont(rta);

		Chunk usostyle = new Chunk("USO:");
		usostyle.setFont(writers);

		Chunk usomedstyle = new Chunk("MÉDICO:");
		usomedstyle.setFont(rta);

		Chunk usobacstyle = new Chunk("BÁSICO:");
		usobacstyle.setFont(rta);

		Chunk usoapostyle = new Chunk("APOYO:");
		usoapostyle.setFont(rta);

		PdfPTable tablatecnica = new PdfPTable(14);

		// firstrowtec
		PdfPCell regtecstyle = new PdfPCell(new Phrase(tecnicstyle));
		regtecstyle.setColspan(8);
		tablatecnica.addCell(regtecstyle);
		PdfPCell sourcealstyle = new PdfPCell(new Phrase(sourcestyle));
		sourcealstyle.setColspan(3);
		tablatecnica.addCell(sourcealstyle);
		PdfPCell apoyoteccell = new PdfPCell(new Phrase(apoyostyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// secondrowtec
		regtecstyle = new PdfPCell(new Phrase(vmaxstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vmaxrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(velstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(velrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(pacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(elecsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);

		apoyoteccell = new PdfPCell(new Phrase(manualstyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// thirdrowtec
		regtecstyle = new PdfPCell(new Phrase(vminstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vminrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(vacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(presionstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(presionrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(rpmacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(solarsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(opuserstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);
		apoyoteccell = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(apoyoteccell);

		// fourthrowtec
		regtecstyle = new PdfPCell(new Phrase(imaxstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(imaxrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(tempstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(temprtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(tempacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(watersourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(tecmanualstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);
		apoyoteccell = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(apoyoteccell);

		// fifthyrowtec
		regtecstyle = new PdfPCell(new Phrase(iminstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iminrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(iacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(weightacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(gassourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usostyle));
		apoyoteccell.setColspan(3);
		tablatecnica.addCell(apoyoteccell);

		// sixrowtec
		regtecstyle = new PdfPCell(new Phrase(powerstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(powerrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(wacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacitystyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacityrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(capacacrstyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(vaporsourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usomedstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);
		apoyoteccell = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(apoyoteccell);

		// sevenrowtec
		regtecstyle = new PdfPCell(new Phrase(frecuencystyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(frecuencyrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(fzacrstyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(otrotecstyle));
		regtecstyle.setColspan(2);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(otrotecrtastyle));
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(""));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(psourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usobacstyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);
		apoyoteccell = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(apoyoteccell);

		// eightyrowtec
		regtecstyle = new PdfPCell(new Phrase(portstyle));
		regtecstyle.setColspan(3);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(portrtastyle));
		tablatecnica.addCell(regtecstyle);

		regtecstyle = new PdfPCell(new Phrase(fijostyle));
		regtecstyle.setColspan(3);
		tablatecnica.addCell(regtecstyle);
		regtecstyle = new PdfPCell(new Phrase(fijortastyle));
		tablatecnica.addCell(regtecstyle);

		sourcealstyle = new PdfPCell(new Phrase(osourcestyle));
		sourcealstyle.setColspan(2);
		tablatecnica.addCell(sourcealstyle);
		sourcealstyle = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(sourcealstyle);
		apoyoteccell = new PdfPCell(new Phrase(usoapostyle));
		apoyoteccell.setColspan(2);
		tablatecnica.addCell(apoyoteccell);
		apoyoteccell = new PdfPCell(new Phrase(""));

		tablatecnica.addCell(apoyoteccell);

		tablatecnica.setSpacingAfter(10);
		// table class
		Chunk riesgotstyle = new Chunk("RIESGO:");
		riesgotstyle.setFont(fuenteEnunciados);

		Chunk riesgoistyle = new Chunk("I:");
		riesgoistyle.setFont(rta);

		Chunk riesgoiiastyle = new Chunk("IIA:");
		riesgoiiastyle.setFont(rta);

		Chunk riesgoiibstyle = new Chunk("IIB:");
		riesgoiibstyle.setFont(rta);

		Chunk riesgoiiistyle = new Chunk("III:");
		riesgoiiistyle.setFont(rta);

		Chunk tecpredstyle = new Chunk("CLASE DE TECNOLOGÍA PREDOMINANTE:");
		tecpredstyle.setFont(fuenteEnunciados);

		Chunk celecstyle = new Chunk("ELÉCTRICO:");
		celecstyle.setFont(rta);

		Chunk celectronicstyle = new Chunk("ELECTRÓNICO:");
		celectronicstyle.setFont(rta);

		Chunk cmecstyle = new Chunk("MECÁNICO:");
		cmecstyle.setFont(rta);

		Chunk celectromecstyle = new Chunk("ELECTROMECÁNICO:");
		celectromecstyle.setFont(rtasmall);

		Chunk chidstyle = new Chunk("HIDRAÚLICO:");
		chidstyle.setFont(rta);

		Chunk cneustyle = new Chunk("NEUMÁTICO:");
		cneustyle.setFont(rta);

		Chunk cvapstyle = new Chunk("VAPOR:");
		cvapstyle.setFont(rta);

		Chunk csolstyle = new Chunk("SOLAR:");
		csolstyle.setFont(rta);

		Chunk periodstyle = new Chunk("PERIODICIDAD DEL MANTENIMIENTO:");
		periodstyle.setFont(fuenteEnunciados);

		Chunk ptristyle = new Chunk("TRIMESTRAL:");
		ptristyle.setFont(rta);

		Chunk pcuatristyle = new Chunk("CUATRIMESTRAL:");
		pcuatristyle.setFont(rta);

		Chunk psemstyle = new Chunk("SEMESTRAL:");
		psemstyle.setFont(rta);

		Chunk panualstyle = new Chunk("ANUAL:");
		panualstyle.setFont(rta);

		Chunk responmttostyle = new Chunk("MANTENIMIENTO ACTUAL:");
		responmttostyle.setFont(fuenteEnunciados);

		Chunk mttopropstyle = new Chunk("PROPIO:");
		mttopropstyle.setFont(rta);

		Chunk mttocontstyle = new Chunk("CONTRATADO:");
		mttocontstyle.setFont(rta);

		Chunk mttocomostyle = new Chunk("COMODATO:");
		mttocomostyle.setFont(rta);

		Chunk mttogarstyle = new Chunk("GARANTÍA:");
		mttogarstyle.setFont(rta);

		PdfPTable tablaclass = new PdfPTable(18);

		// firstrowclass
		PdfPCell tecnopredcell = new PdfPCell(new Phrase(tecpredstyle));
		tecnopredcell.setColspan(8);
		PdfPCell riesgocell = new PdfPCell(new Phrase(riesgotstyle));
		riesgocell.setColspan(2);
		PdfPCell freqmttocell = new PdfPCell(new Phrase(periodstyle));
		freqmttocell.setColspan(4);
		PdfPCell responmttocell = new PdfPCell(new Phrase(responmttostyle));
		responmttocell.setColspan(4);
		tablaclass.addCell(tecnopredcell);
		tablaclass.addCell(riesgocell);
		tablaclass.addCell(freqmttocell);
		tablaclass.addCell(responmttocell);
		// secondrowclass
		tecnopredcell = new PdfPCell(new Phrase(celecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(celectronicstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoistyle));
		tablaclass.addCell(riesgocell);
		riesgocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(ptristyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		freqmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttopropstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		responmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(responmttocell);

		// thirdrowclass
		tecnopredcell = new PdfPCell(new Phrase(cmecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(celectromecstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiiastyle));
		tablaclass.addCell(riesgocell);
		riesgocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(pcuatristyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		freqmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttocontstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);

		responmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(responmttocell);
		// fourthrowclass
		tecnopredcell = new PdfPCell(new Phrase(chidstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(cneustyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiibstyle));
		tablaclass.addCell(riesgocell);
		riesgocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(psemstyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		freqmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttocomostyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		responmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(responmttocell);
		// fifthyrowclass
		tecnopredcell = new PdfPCell(new Phrase(cvapstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(csolstyle));
		tecnopredcell.setColspan(3);
		tablaclass.addCell(tecnopredcell);
		tecnopredcell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(tecnopredcell);

		riesgocell = new PdfPCell(new Phrase(riesgoiiistyle));
		tablaclass.addCell(riesgocell);
		riesgocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(riesgocell);

		freqmttocell = new PdfPCell(new Phrase(panualstyle));
		freqmttocell.setColspan(3);
		tablaclass.addCell(freqmttocell);
		freqmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(freqmttocell);

		responmttocell = new PdfPCell(new Phrase(mttogarstyle));
		responmttocell.setColspan(3);
		tablaclass.addCell(responmttocell);
		responmttocell = new PdfPCell(new Phrase(""));

		tablaclass.addCell(responmttocell);

		// tableaccessories
		Chunk accstyle = new Chunk("ACCESORIOS DEL EQUIPO:");
		accstyle.setFont(fuenteEnunciados);

		Chunk acc1style = new Chunk("");
		acc1style.setFont(rta);

		Chunk acc2style = new Chunk("");
		acc2style.setFont(rta);

		Chunk acc3style = new Chunk("");
		acc3style.setFont(rta);

		Chunk acc4style = new Chunk("");
		acc4style.setFont(rta);

		Chunk calovalstyle = new Chunk("REQUIERE CALIBRACIÓN Y/O VALIDACIÓN:");
		calovalstyle.setFont(fuenteEnunciados);

		Chunk calsistyle = new Chunk("SI:");
		calsistyle.setFont(rta);

		Chunk calnostyle = new Chunk("NO:");
		calnostyle.setFont(rta);

		Chunk pvalcalstyle = new Chunk("PERIODICIDAD");
		pvalcalstyle.setFont(fuenteEnunciados);

		tablaclass.setSpacingAfter(10);

		PdfPTable tablefinal = new PdfPTable(16);

		PdfPTable tableacc = new PdfPTable(1);
		PdfPCell accesory = new PdfPCell(new Phrase(accstyle));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc1style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc2style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc3style));
		tableacc.addCell(accesory);
		accesory = new PdfPCell(new Phrase(acc4style));
		tableacc.addCell(accesory);

		PdfPCell accell = new PdfPCell(tableacc);
		accell.setColspan(6);
		accell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		accell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPTable tablecalval = new PdfPTable(4);
		PdfPCell valcalcell = new PdfPCell(tablecalval);
		valcalcell.setColspan(3);
		valcalcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		valcalcell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell calvalcell = new PdfPCell(new Phrase(calovalstyle));
		calvalcell.setColspan(4);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(calsistyle));
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(""));

		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(calnostyle));
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(""));

		tablecalval.addCell(calvalcell);

		calvalcell = new PdfPCell(new Phrase(pvalcalstyle));
		calvalcell.setColspan(4);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(psemstyle));
		calvalcell.setColspan(3);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(""));

		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(panualstyle));
		calvalcell.setColspan(3);
		tablecalval.addCell(calvalcell);
		calvalcell = new PdfPCell(new Phrase(""));

		tablecalval.addCell(calvalcell);

		Chunk propstyle = new Chunk("PROPIEDAD DEL EQUIPO:");
		propstyle.setFont(fuenteEnunciados);

		Chunk prohospitalstyle = new Chunk("HOSPITAL:");
		prohospitalstyle.setFont(rta);

		Chunk proproveedorstyle = new Chunk("PROVEEDOR:");
		proproveedorstyle.setFont(rtasmall);

		Chunk protrostyle = new Chunk("OTRO:");
		protrostyle.setFont(rtasmall);

		Chunk biomedicstyle = new Chunk("CLASIFICACIÓN BIOMÉDICA");
		biomedicstyle.setFont(fuenteEnunciados);

		Chunk diagstyle = new Chunk("DIAGNÓSTICO:");
		diagstyle.setFont(rta);

		Chunk rehabstyle = new Chunk("REHABILITACIÓN:");
		rehabstyle.setFont(rta);

		Chunk preventstyle = new Chunk("PREVENCIÓN:");
		preventstyle.setFont(rta);

		Chunk analystyle = new Chunk("ANÁLISIS DE LABORATORIO:");
		analystyle.setFont(rta);

		Chunk treatstyle = new Chunk("TRATAMIENTO Y MANTENIMIENTO A LA VIDA:");
		treatstyle.setFont(rta);

		PdfPTable tableclassbio = new PdfPTable(8);
		PdfPCell classbiocell = new PdfPCell(new Phrase(biomedicstyle));
		classbiocell.setColspan(8);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(diagstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(rehabstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(preventstyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);
		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(analystyle));
		classbiocell.setColspan(3);
		tableclassbio.addCell(classbiocell);
		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(treatstyle));
		classbiocell.setColspan(7);
		tableclassbio.addCell(classbiocell);
		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(propstyle));
		classbiocell.setColspan(8);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(prohospitalstyle));
		classbiocell.setColspan(2);
		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(proproveedorstyle));
		classbiocell.setColspan(2);
		tableclassbio.addCell(classbiocell);
		classbiocell = new PdfPCell(new Phrase(""));

		tableclassbio.addCell(classbiocell);

		classbiocell = new PdfPCell(new Phrase(protrostyle));
		tableclassbio.addCell(classbiocell);
		classbiocell = new PdfPCell(new Phrase(""));
		tableclassbio.addCell(classbiocell);

		PdfPCell biomedicell = new PdfPCell(tableclassbio);
		biomedicell.setColspan(7);

		PdfPTable tableobs = new PdfPTable(8);

		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(fuenteEnunciados);
		PdfPCell obs = new PdfPCell(new Phrase(obsstyle));
		obs.setColspan(2);
		obs.setMinimumHeight(20);
		tableobs.addCell(obs);

		obs = new PdfPCell(new Phrase(""));
		obs.setColspan(6);
		tableobs.addCell(obs);

		Chunk espnull = new Chunk("ND: NO DISPONIBLE NR: NO REGISTRA NE: NO ESPECIFICA NA: NO APLICA");
		espnull.setFont(rta);
		obs = new PdfPCell(new Phrase(espnull));
		obs.setColspan(8);
		obs.setBorder(Rectangle.NO_BORDER);
		tableobs.addCell(obs);

		tablefinal.addCell(accell);
		tablefinal.addCell(valcalcell);
		tablefinal.addCell(biomedicell);

		tablefinal.setSpacingAfter(10);

		document.add(tabla);
		document.add(tabladhos);
		document.add(tablaeqcom);
		document.add(tablatecnica);
		document.add(tablaclass);
		document.add(tablefinal);
		document.add(tableobs);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getMSPHsysPDF(SystemMantenimiento mtto, List<Protocolo_preventivo> protos,
			List<Protocolo_preventivo> protoh) throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE HARDWARE HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-04");
		Chunk vs = new Chunk("VERSION: 06");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		Chunk procesorta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		procesorta.setFont(rta);

		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		Chunk marca = new Chunk("MARCA");
		marca.setFont(rta);
		Chunk marcarta = new Chunk(mtto.getEquipo().getMarca());
		marcarta.setFont(rta);

		Chunk modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		Chunk modelorta = new Chunk(mtto.getEquipo().getModelo());
		modelorta.setFont(rta);

		Chunk serie = new Chunk("SERIE");
		serie.setFont(rta);
		Chunk serierta = new Chunk(mtto.getEquipo().getSerie());
		serierta.setFont(rta);

		Chunk inv = new Chunk("INVENTARIO");
		inv.setFont(rta);
		Chunk invrta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		invrta.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(procesorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(fecharta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(marcarta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(modelorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(serierta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(invrta));
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		PdfPTable tablatipo = new PdfPTable(12);
		Chunk tipoequipo = new Chunk("TIPO DE EQUIPO");
		tipoequipo.setFont(fuenteEnunciados);
		PdfPCell celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(12);
		celltipo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tablatipo.addCell(celltipo);
		Chunk xselect = new Chunk("X");
		xselect.setFont(rta);

		tipoequipo = new Chunk("PC:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(123L)) {
			celltipo = new PdfPCell(new Phrase(xselect));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}

		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("IMPRESORA:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(121L)) {
			celltipo = new PdfPCell(new Phrase(xselect));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}
		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("OTRO:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (!mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(123L)
				&& !mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(121L)) {
			Font rta2 = new Font();
			rta2.setStyle(Font.BOLD);
			rta2.setSize(6);
			Chunk xselect2 = new Chunk(mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo());
			xselect2.setFont(rta2);
			celltipo = new PdfPCell(new Phrase(xselect2));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}
		tablatipo.addCell(celltipo);
		tablatipo.setSpacingAfter(10);

		Chunk actividades = new Chunk("LISTA DE ACTIVIDADES A REALIZAR");
		actividades.setFont(fuenteEnunciados);

		Chunk first = new Chunk("Desconectar cables y periféricos de los equipos.");
		first.setFont(rta);

		PdfPTable tabla3 = new PdfPTable(12);

		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		Chunk number = new Chunk("1");
		ArrayList<String> strnum = new ArrayList<String>(Arrays.asList(mtto.getRutinah().split(",")));

		for (int p = 0; p < protoh.size(); p++) {
			number = new Chunk(String.valueOf(p));
			number.setFont(rta);
			cellact = new PdfPCell(new Phrase(number));
			cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			tabla3.addCell(cellact);
			first = new Chunk(protoh.get(p).getPaso());
			first.setFont(rta);
			cellact = new PdfPCell(new Phrase(first));
			cellact.setColspan(10);
			tabla3.addCell(cellact);
			if (strnum.contains(String.valueOf(p))) {
				cellact = new PdfPCell(new Phrase(xselect));
			} else {
				cellact = new PdfPCell(new Phrase(" "));
			}
			tabla3.addCell(cellact);
		}

		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		Chunk obsrta = new Chunk(mtto.getObservacionesh());
		obsrta.setFont(rta);
		cellobs = new PdfPCell(new Phrase(obsrta));
		cellobs.setMinimumHeight(300);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk(mtto.getAutor_realizado());
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk(mtto.getAutor_recibido());
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tablatipo);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.newPage();

		titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE SOFTWARE HRCATCH");
		titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		code = new Chunk("CÓDIGO S-F-05");
		vs = new Chunk("VERSION: 05");
		date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		tabla = new PdfPTable(5);

		celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		procesorta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		procesorta.setFont(rta);

		fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		marca = new Chunk("MARCA");
		marca.setFont(rta);
		marcarta = new Chunk(mtto.getEquipo().getMarca());
		marcarta.setFont(rta);

		serie = new Chunk("SERIE");
		serie.setFont(rta);
		serierta = new Chunk(mtto.getEquipo().getSerie());
		serierta.setFont(rta);

		inv = new Chunk("INVENTARIO");
		inv.setFont(rta);
		invrta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		invrta.setFont(rta);

		tabla2 = new PdfPTable(6);

		cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(procesorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(fecharta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(marcarta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(modelorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(serierta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(invrta));
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		actividades = new Chunk("ACTIVIDADES A REALIZAR PARA MANTENIMIENTO PREVENTIVO DE SOFTWARE");
		actividades.setFont(fuenteEnunciados);

		Chunk second = new Chunk("Instalación de sistema operativo.");
		second.setFont(rta);

		tabla3 = new PdfPTable(12);

		cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		number = new Chunk("1");
		ArrayList<String> strnums = new ArrayList<String>(Arrays.asList(mtto.getRutinas().split(",")));

		for (int p = 0; p < protos.size(); p++) {
			number = new Chunk(String.valueOf(p));
			number.setFont(rta);
			cellact = new PdfPCell(new Phrase(number));
			cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			tabla3.addCell(cellact);
			first = new Chunk(protos.get(p).getPaso());
			first.setFont(rta);
			cellact = new PdfPCell(new Phrase(first));
			cellact.setColspan(10);
			tabla3.addCell(cellact);
			if (strnums.contains(String.valueOf(p))) {
				cellact = new PdfPCell(new Phrase(xselect));
			} else {
				cellact = new PdfPCell(new Phrase(" "));
			}
			tabla3.addCell(cellact);
		}

		tabla3.setSpacingAfter(10);

		tabla4 = new PdfPTable(1);
		observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		obsrta = new Chunk(mtto.getObservacioness());
		obsrta.setFont(rta);
		cellobs = new PdfPCell(new Phrase(obsrta));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		tabla5 = new PdfPTable(4);
		realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		realizadortastyle = new Chunk(mtto.getAutor_realizado());
		realizadortastyle.setFont(rta);

		recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		recibidortastyle = new Chunk(mtto.getAutor_recibido());
		recibidortastyle.setFont(rta);

		cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		nombrerea = new PdfPCell(new Phrase(cedula));

		firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		nombrereci = new PdfPCell(new Phrase(cedula));

		firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);

		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalMPSsysPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE SOFTWARE HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-05");
		Chunk vs = new Chunk("VERSION: 05");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		Chunk marca = new Chunk("MARCA");
		marca.setFont(rta);
		Chunk modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		Chunk serie = new Chunk("SERIE");
		serie.setFont(rta);
		Chunk inv = new Chunk("INVENTARIO");
		inv.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(" "));
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		Chunk actividades = new Chunk("ACTIVIDADES A REALIZAR PARA MANTENIMIENTO PREVENTIVO DE SOFTWARE");
		actividades.setFont(fuenteEnunciados);

		Chunk first = new Chunk("Copia de seguridad de información en medios extraibles.");
		first.setFont(rta);
		Chunk second = new Chunk("Instalación de sistema operativo.");
		second.setFont(rta);
		Chunk third = new Chunk("Instalación de actualizaciones de sistema operativo.");
		third.setFont(rta);
		Chunk fourth = new Chunk("Verificación y corrección de nombre de usuario de dominio y equipo.");
		fourth.setFont(rta);
		Chunk fifthy = new Chunk("Instalar y/o actualizar los antivirus y complementos de seguridad.");
		fifthy.setFont(rta);
		Chunk six = new Chunk("Instalación de software y aplicaciones ofimáticas con respectiva licencia.");
		six.setFont(rta);
		Chunk seven = new Chunk("Instalación de aplicativo para control de inventario.");
		seven.setFont(rta);
		Chunk eight = new Chunk(
				"Configuración software antivirus de manera que se actualice periódicamente para detección y control automático de amenazas.");
		eight.setFont(rta);
		Chunk nine = new Chunk("Instalación o actualización de navegadores de internet y complementos.");
		nine.setFont(rta);
		Chunk ten = new Chunk("Dar acceso a servidor remoto, plataformas y carpetas compartidas necesarias.");
		ten.setFont(rta);
		Chunk eleven = new Chunk("Instalación y configuración de dispositivos necesarios(impresora, escaner, otros).");
		eleven.setFont(rta);
		Chunk twelve = new Chunk("Configuración y pruebas de funcionamiento de correo corporativo.");
		twelve.setFont(rta);
		Chunk thirdtn = new Chunk("Restauración y verificación de copia de seguridad de información extraída.");
		thirdtn.setFont(rta);
		Chunk fourtn = new Chunk("Realizar test de componentes(disco, memoria, pantalla, board) para descartar daños.");
		fourtn.setFont(rta);
		Chunk fivetn = new Chunk(
				"Realizar limpieza y desfragmentación de registro, limpieza de archivos temporales y papelera de reciclaje.");
		fivetn.setFont(rta);
		Chunk sixtn = new Chunk(
				"Corregir errores y/o configuraciones del software que causen problemas, y si persiste el inconveniente, programar mantenimiento correctivo.");
		sixtn.setFont(rta);
		Chunk seventn = new Chunk("Eliminar archivos de música y otros no permitidos.");
		seventn.setFont(rta);
		Chunk eightn = new Chunk("Desinstalar programas que no estén licenciados y/o que causen errores.");
		eightn.setFont(rta);
		Chunk ninetn = new Chunk("Escanear en busca de virus, software malicioso y eliminar las amenazas encontradas.");
		ninetn.setFont(rta);
		Chunk twenty = new Chunk("Desfragmentar disco duro.");
		twenty.setFont(rta);
		Chunk twone = new Chunk("Realizar pruebas de funcionamiento.");
		twone.setFont(rta);
		Chunk twtwo = new Chunk(
				"Registrar la observación si se encuentra software y/o archivos no autorizados o no licenciados y otros hallazgos que deban ser registrados.");
		twtwo.setFont(rta);
		Chunk twothree = new Chunk("Diligenciamiento de formato S-F-05 version 05.");
		twothree.setFont(rta);
		PdfPTable tabla3 = new PdfPTable(12);

		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		Chunk number = new Chunk("1");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(first));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 2
		number = new Chunk("2");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(second));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 3
		number = new Chunk("3");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(third));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 4
		number = new Chunk("4");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fourth));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 5
		number = new Chunk("5");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fifthy));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 6
		number = new Chunk("6");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(six));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 7
		number = new Chunk("7");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(seven));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 8
		number = new Chunk("8");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(eight));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 9
		number = new Chunk("9");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(nine));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 10
		number = new Chunk("10");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(ten));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 11
		number = new Chunk("11");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(eleven));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 12
		number = new Chunk("12");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(twelve));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 13
		number = new Chunk("13");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(thirdtn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 14
		number = new Chunk("14");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fourtn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 15
		number = new Chunk("15");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fivetn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 16
		number = new Chunk("16");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(sixtn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 17
		number = new Chunk("17");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(seventn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 18
		number = new Chunk("18");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(eightn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 19
		number = new Chunk("19");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(ninetn));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 20
		number = new Chunk("20");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(twenty));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 21
		number = new Chunk("21");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(twone));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 22
		number = new Chunk("22");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(twtwo));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 23
		number = new Chunk("23");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(twothree));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		cellobs = new PdfPCell(new Phrase(" "));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk("");
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk("");
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);

		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalMPHsysPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE HARDWARE HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-04");
		Chunk vs = new Chunk("VERSION: 06");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		Chunk marca = new Chunk("MARCA");
		marca.setFont(rta);
		Chunk modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		Chunk serie = new Chunk("SERIE");
		serie.setFont(rta);
		Chunk inv = new Chunk("INVENTARIO");
		inv.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(5);

		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(5);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(" "));
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		PdfPTable tablatipo = new PdfPTable(12);
		Chunk tipoequipo = new Chunk("TIPO DE EQUIPO");
		tipoequipo.setFont(fuenteEnunciados);
		PdfPCell celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(12);
		celltipo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("PC:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		celltipo = new PdfPCell(new Phrase(""));
		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("IMPRESORA:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		celltipo = new PdfPCell(new Phrase(""));
		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("OTRO:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		celltipo = new PdfPCell(new Phrase(""));
		tablatipo.addCell(celltipo);
		tablatipo.setSpacingAfter(10);

		Chunk actividades = new Chunk("LISTA DE ACTIVIDADES A REALIZAR");
		actividades.setFont(fuenteEnunciados);

		Chunk first = new Chunk("Desconectar cables y periféricos de los equipos.");
		first.setFont(rta);
		Chunk second = new Chunk("Trasladar el equipo a un área de aire libre");
		second.setFont(rta);
		Chunk third = new Chunk("Utilizar elementos de protección");
		third.setFont(rta);
		Chunk fourth = new Chunk("Limpieza externa de equipo y periféricos con uso de limpiadores");
		fourth.setFont(rta);
		Chunk fifthy = new Chunk("Limpieza interna de equipo");
		fifthy.setFont(rta);
		Chunk six = new Chunk("Cambio componentes internos o externos por desgaste o daño");
		six.setFont(rta);
		Chunk seven = new Chunk("Pruebas de funcionamiento de equipo");
		seven.setFont(rta);
		Chunk eight = new Chunk("Diligenciamiento de formato S-F-04 version 6");
		eight.setFont(rta);

		PdfPTable tabla3 = new PdfPTable(12);

		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		Chunk number = new Chunk("1");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(first));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 2
		number = new Chunk("2");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(second));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 3
		number = new Chunk("3");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(third));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 4
		number = new Chunk("4");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fourth));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 5
		number = new Chunk("5");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fifthy));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 6
		number = new Chunk("6");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(six));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 7
		number = new Chunk("7");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(seven));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 8
		number = new Chunk("8");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(eight));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		cellobs = new PdfPCell(new Phrase(" "));
		cellobs.setMinimumHeight(300);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk("");
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk("");
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tablatipo);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getMPHsysPDF(SystemMantenimiento mtto, List<Protocolo_preventivo> protos,
			List<Protocolo_preventivo> protoh) throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE HARDWARE HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-04");
		Chunk vs = new Chunk("VERSION: 06");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		Chunk procesorta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		procesorta.setFont(rta);

		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		Chunk marca = new Chunk("MARCA");
		marca.setFont(rta);
		Chunk marcarta = new Chunk(mtto.getEquipo().getMarca());
		marcarta.setFont(rta);

		Chunk modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		Chunk modelorta = new Chunk(mtto.getEquipo().getModelo());
		modelorta.setFont(rta);

		Chunk serie = new Chunk("SERIE");
		serie.setFont(rta);
		Chunk serierta = new Chunk(mtto.getEquipo().getSerie());
		serierta.setFont(rta);

		Chunk inv = new Chunk("INVENTARIO");
		inv.setFont(rta);
		Chunk invrta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		invrta.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(procesorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(fecharta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(marcarta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(modelorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(serierta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(invrta));
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);
		PdfPTable tablatipo = new PdfPTable(12);
		Chunk tipoequipo = new Chunk("TIPO DE EQUIPO");
		tipoequipo.setFont(fuenteEnunciados);
		PdfPCell celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(12);
		celltipo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tablatipo.addCell(celltipo);
		Chunk xselect = new Chunk("X");
		xselect.setFont(rta);

		tipoequipo = new Chunk("PC:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(123L)) {
			celltipo = new PdfPCell(new Phrase(xselect));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}

		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("IMPRESORA:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(121L)) {
			celltipo = new PdfPCell(new Phrase(xselect));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}
		tablatipo.addCell(celltipo);

		tipoequipo = new Chunk("OTRO:");
		tipoequipo.setFont(rta);
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		celltipo.setColspan(3);
		tablatipo.addCell(celltipo);
		if (!mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(123L)
				&& !mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo().equals(121L)) {
			celltipo = new PdfPCell(new Phrase(xselect));
		} else {
			celltipo = new PdfPCell(new Phrase(""));
		}
		tablatipo.addCell(celltipo);
		tablatipo.setSpacingAfter(10);

		Chunk actividades = new Chunk("LISTA DE ACTIVIDADES A REALIZAR");
		actividades.setFont(fuenteEnunciados);

		Chunk first = new Chunk("Desconectar cables y periféricos de los equipos.");
		first.setFont(rta);

		PdfPTable tabla3 = new PdfPTable(12);

		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		Chunk number = new Chunk("1");
		ArrayList<String> strnum = new ArrayList<String>(Arrays.asList(mtto.getRutinah().split(",")));

		for (int p = 0; p < protoh.size(); p++) {
			number = new Chunk(String.valueOf(p));
			number.setFont(rta);
			cellact = new PdfPCell(new Phrase(number));
			cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			tabla3.addCell(cellact);
			first = new Chunk(protoh.get(p).getPaso());
			first.setFont(rta);
			cellact = new PdfPCell(new Phrase(first));
			cellact.setColspan(10);
			tabla3.addCell(cellact);
			if (strnum.contains(String.valueOf(p))) {
				cellact = new PdfPCell(new Phrase(xselect));
			} else {
				cellact = new PdfPCell(new Phrase(" "));
			}
			tabla3.addCell(cellact);
		}

		tabla3.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		Chunk obsrta = new Chunk(mtto.getObservacionesh());
		obsrta.setFont(rta);
		cellobs = new PdfPCell(new Phrase(obsrta));
		cellobs.setMinimumHeight(300);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk(mtto.getAutor_realizado());
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk(mtto.getAutor_recibido());
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tablatipo);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalEntregaPortatilsysPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("ENTREGA DE EQUIPOS PORTATILES HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-01");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:06/10/2022");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA DESTINO:");

		espnull.setFont(rta);
		Chunk space = new Chunk("                         ");
		space.setFont(fuentesubrayada);
		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);

		Chunk realizadostyle = new Chunk("QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);
		inv1 = new PdfPCell(new Phrase(realizadostyle));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(4);
		tablainv1.addCell(inv1);

		tablainv1.setSpacingAfter(10);
		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteEnunciados);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteEnunciados);

		Chunk portastyle = new Chunk("PORTÁTIL");
		portastyle.setFont(fuenteEnunciados);

		Chunk accestyle = new Chunk("ACCESORIOS");
		accestyle.setFont(rta);

		Chunk softstyle = new Chunk("SOFTWARE");
		softstyle.setFont(rta);

		Chunk escstyle = new Chunk("CARACTERÍSTICAS DEL PORTÁTIL");
		escstyle.setFont(rta);

		Chunk procstyle = new Chunk("PROCESADOR");
		procstyle.setFont(rta);

		Chunk ramstyle = new Chunk("MEMORÍA RAM");
		ramstyle.setFont(rta);

		Chunk hdstyle = new Chunk("DISCO DURO");
		hdstyle.setFont(rta);

		Chunk outstyle = new Chunk("EQUIPO QUE SE RETIRA");
		outstyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(5);

		PdfPCell dataequipo = new PdfPCell(new Phrase(equipostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastyle));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(portastyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(accestyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(50);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(softstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(50);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(escstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		dataequipo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		dataequipo.setColspan(5);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(procstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(ramstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(hdstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(outstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);

		tabla2.setSpacingAfter(10);

		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(rta);

		Chunk recibe = new Chunk("NOMBRE DE QUIEN RECIBE:");
		recibe.setFont(rta);

		Chunk firm = new Chunk("FIRMA:");
		firm.setFont(rta);

		Chunk cedula = new Chunk("CEDULA:");
		cedula.setFont(rta);

		Chunk cargo = new Chunk("CARGO:");
		cargo.setFont(rta);

		PdfPTable tablainv2 = new PdfPTable(7);
		PdfPCell inv2 = new PdfPCell(new Phrase(obsstyle));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(5);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(recibe));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(firm));
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cargo));
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(3);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cedula));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabla2);
		document.add(tablainv2);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getEntregaPortatilsysPDF(SystemMantenimiento mtto, SystemHoja_vida hv,
			List<SystemRepuestos> repuestos, SystemEquipo equipo) throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("ENTREGA DE EQUIPOS PORTATILES HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-01");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:06/10/2022");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA O PROCESO:");

		espnull.setFont(rta);
		Chunk espnullrta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		espnullrta.setFont(rta);

		Chunk space = new Chunk("                         ");
		space.setFont(fuentesubrayada);
		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		Chunk realizadostyle = new Chunk("NOMBRE DE QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);
		Chunk realizadostylerta = new Chunk(mtto.getAutor_realizado());
		realizadostylerta.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(espnullrta));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecharta));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);
		inv1 = new PdfPCell(new Phrase(realizadostyle));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(realizadostylerta));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(4);
		tablainv1.addCell(inv1);

		tablainv1.setSpacingAfter(10);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk equipostylerta = new Chunk(mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo());
		equipostylerta.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk marcastylerta = new Chunk(mtto.getEquipo().getMarca());
		marcastylerta.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteEnunciados);

		Chunk modelostylerta = new Chunk(mtto.getEquipo().getModelo());
		modelostylerta.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk seriestylerta = new Chunk(mtto.getEquipo().getSerie());
		seriestylerta.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteEnunciados);

		Chunk placastylerta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		placastylerta.setFont(rta);

		Chunk ubactualstyle = new Chunk("UBICACIÓN ACTUAL:");
		ubactualstyle.setFont(fuenteEnunciados);

		Chunk ubactualstylerta = new Chunk(mtto.getEquipo().getUbicacion());
		ubactualstylerta.setFont(rta);

		Chunk accestyle = new Chunk("ACCESORIOS");
		accestyle.setFont(rta);

		Chunk softstyle = new Chunk("SOFTWARE");
		softstyle.setFont(rta);

		Chunk escstyle = new Chunk("CARACTERÍSTICAS DEL PORTÁTIL");
		escstyle.setFont(rta);

		Chunk procstyle = new Chunk("PROCESADOR");
		procstyle.setFont(rta);

		Chunk ramstyle = new Chunk("MEMORÍA RAM");
		ramstyle.setFont(rta);

		Chunk hdstyle = new Chunk("DISCO DURO");
		hdstyle.setFont(rta);

		Chunk outstyle = new Chunk("EQUIPO QUE SE RETIRA");
		outstyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(5);

		PdfPCell dataequipo = new PdfPCell(new Phrase(equipostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastyle));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(equipostylerta));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastylerta));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostylerta));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestylerta));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastylerta));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(accestyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		Chunk access = new Chunk("");
		Phrase accessph = new Phrase();
		SystemRepuestos repuesto = new SystemRepuestos();
		for (int i = 0; i < repuestos.size(); i++) {
			repuesto = repuestos.get(i);
			access = new Chunk(repuesto.getNombre_repuesto() + ' ' + repuesto.getMarca() + ' ' + repuesto.getModelo()
					+ ' ' + repuesto.getSerie());
			access.setFont(rta);
			accessph.add(access);
		}
		dataequipo = new PdfPCell(new Phrase(accessph));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(50);
		tabla2.addCell(dataequipo);

		if (hv == null) {
			dataequipo = new PdfPCell(new Phrase(softstyle));
			dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			dataequipo.setMinimumHeight(50);
			tabla2.addCell(dataequipo);
		} else {
			Chunk softstylerta = new Chunk(hv.getSistema_operativo());
			softstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(softstyle));
			dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			dataequipo.setMinimumHeight(50);
			tabla2.addCell(dataequipo);
		}

		dataequipo = new PdfPCell(new Phrase(escstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		dataequipo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		dataequipo.setColspan(5);
		tabla2.addCell(dataequipo);

		if (hv == null) {
			dataequipo = new PdfPCell(new Phrase(procstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			dataequipo = new PdfPCell(new Phrase(ramstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			dataequipo = new PdfPCell(new Phrase(hdstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);
		} else {

			Chunk procstylerta = new Chunk(hv.getProcesador());
			procstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(procstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(procstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			Chunk ramstylerta = new Chunk(hv.getRam());
			ramstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(ramstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(ramstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			Chunk hdstylerta = new Chunk(hv.getDisco_duro());
			hdstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(hdstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(hdstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);
		}
		if (equipo == null) {
			dataequipo = new PdfPCell(new Phrase(outstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		} else {
			dataequipo = new PdfPCell(new Phrase(outstyle));
			tabla2.addCell(dataequipo);
			Chunk outequipo = new Chunk(equipo.getMarca());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getModelo());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getSerie());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getPlaca_inventario());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
		}

		tabla2.setSpacingAfter(10);

		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(rta);

		Chunk recibe = new Chunk("NOMBRE DE QUIEN RECIBE:");
		recibe.setFont(rta);

		Chunk reciberta = new Chunk(mtto.getAutor_recibido());
		reciberta.setFont(rta);

		Chunk firm = new Chunk("FIRMA:");
		firm.setFont(rta);

		Chunk cedula = new Chunk("CEDULA:");
		cedula.setFont(rta);

		Chunk cargo = new Chunk("CARGO:");
		cargo.setFont(rta);
		Chunk obsstylerta = new Chunk(mtto.getObservacionesh());
		obsstylerta.setFont(rta);

		PdfPTable tablainv2 = new PdfPTable(7);
		PdfPCell inv2 = new PdfPCell(new Phrase(obsstyle));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(obsstylerta));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv2.setColspan(5);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(recibe));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(reciberta));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(firm));
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cargo));
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(3);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cedula));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabla2);
		document.add(tablainv2);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getEntregaSysPDF(SystemMantenimiento mtto, SystemHoja_vida hv, SystemEquipo equipo)
			throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("ENTREGA DE EQUIPOS HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-03");
		Chunk vs = new Chunk("VERSION: 05");
		Chunk date = new Chunk("Fecha:08/03/2024");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA O PROCESO:");

		espnull.setFont(rta);
		Chunk espnullrta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		espnullrta.setFont(rta);

		Chunk space = new Chunk("                         ");
		space.setFont(fuentesubrayada);
		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		Chunk realizadostyle = new Chunk("NOMBRE DE QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);
		Chunk realizadostylerta = new Chunk(mtto.getAutor_realizado());
		realizadostylerta.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(espnullrta));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecharta));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);
		inv1 = new PdfPCell(new Phrase(realizadostyle));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(realizadostylerta));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(4);
		tablainv1.addCell(inv1);

		tablainv1.setSpacingAfter(10);
		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk equipostylerta = new Chunk("TODO EN UNO");
		equipostylerta.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk marcastylerta = new Chunk(mtto.getEquipo().getMarca());
		marcastylerta.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteEnunciados);

		Chunk modelostylerta = new Chunk(mtto.getEquipo().getModelo());
		modelostylerta.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk seriestylerta = new Chunk(mtto.getEquipo().getSerie());
		seriestylerta.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteEnunciados);

		Chunk placastylerta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		placastylerta.setFont(rta);

		Chunk ubactualstyle = new Chunk("UBICACIÓN ACTUAL:");
		ubactualstyle.setFont(fuenteEnunciados);

		Chunk ubactualstylerta = new Chunk(mtto.getEquipo().getUbicacion());
		ubactualstylerta.setFont(rta);

		Chunk allonestyle = new Chunk("TODO EN UNO");
		allonestyle.setFont(rta);
		
		Chunk pCPestyle = new Chunk("PORTATIL");
		pCPestyle.setFont(rta);
		
		Chunk tabletstyle = new Chunk("TABLET");
		tabletstyle.setFont(rta);

		Chunk procstyle = new Chunk("PROCESADOR");
		procstyle.setFont(rta);

		Chunk ramstyle = new Chunk("RAM");
		ramstyle.setFont(rta);

		Chunk hdstyle = new Chunk("DISCO DURO");
		hdstyle.setFont(rta);

		Chunk softstyle = new Chunk("SOFTWARE");
		softstyle.setFont(rta);

		Chunk escstyle = new Chunk("ESCANER");
		escstyle.setFont(rta);

		Chunk printstyle = new Chunk("IMPRESORA");
		printstyle.setFont(rta);

		Chunk outstyle = new Chunk("EQUIPO QUE SE RETIRA");
		outstyle.setFont(rta);

		Chunk ubpaststyle = new Chunk("UBICACIÓN ANTERIOR:");
		ubpaststyle.setFont(fuenteEnunciados);

		Chunk ubpaststylerta = new Chunk(mtto.getObservacioness());
		ubpaststylerta.setFont(rta);

		Chunk obsstyle = new Chunk("OBSERVACIONES");
		obsstyle.setFont(rta);

		Chunk obsstylerta = new Chunk(mtto.getObservacionesh());
		obsstylerta.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(5);

		PdfPCell dataequipo = new PdfPCell(new Phrase(equipostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastyle));
		tabla2.addCell(dataequipo);
		
		if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 123){
			
			dataequipo = new PdfPCell(new Phrase(equipostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(marcastylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(modelostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(seriestylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(placastylerta));
			tabla2.addCell(dataequipo);
		}else {
			dataequipo = new PdfPCell(new Phrase(allonestyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		}
		
		if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 122){
			
			dataequipo = new PdfPCell(new Phrase(pCPestyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(marcastylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(modelostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(seriestylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(placastylerta));
			tabla2.addCell(dataequipo);
		}else {
			dataequipo = new PdfPCell(new Phrase(pCPestyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		}
		
		if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 124){
			
			dataequipo = new PdfPCell(new Phrase(escstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(marcastylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(modelostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(seriestylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(placastylerta));
			tabla2.addCell(dataequipo);
		}else {
			dataequipo = new PdfPCell(new Phrase(escstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		}
		
		if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 121){
			
			dataequipo = new PdfPCell(new Phrase(printstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(marcastylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(modelostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(seriestylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(placastylerta));
			tabla2.addCell(dataequipo);
		}else {
			dataequipo = new PdfPCell(new Phrase(printstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		}
		
		if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 127){
			
			dataequipo = new PdfPCell(new Phrase(tabletstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(marcastylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(modelostylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(seriestylerta));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(placastylerta));
			tabla2.addCell(dataequipo);
		}else {
			dataequipo = new PdfPCell(new Phrase(tabletstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		}


		if (hv == null) {
			dataequipo = new PdfPCell(new Phrase(procstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			dataequipo = new PdfPCell(new Phrase(ramstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			dataequipo = new PdfPCell(new Phrase(hdstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			dataequipo = new PdfPCell(new Phrase(softstyle));
			dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			dataequipo.setColspan(4);
			dataequipo.setMinimumHeight(30);
			tabla2.addCell(dataequipo);
		} else {
			Chunk procstylerta = new Chunk(hv.getProcesador());
			procstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(procstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(procstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			Chunk ramstylerta = new Chunk(hv.getRam());
			ramstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(ramstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(ramstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			Chunk hdstylerta = new Chunk(hv.getDisco_duro());
			hdstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(hdstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(hdstylerta));
			dataequipo.setColspan(4);
			tabla2.addCell(dataequipo);

			Chunk softstylerta = new Chunk(hv.getSistema_operativo());
			softstylerta.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(softstyle));
			dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(softstylerta));
			dataequipo.setColspan(4);
			dataequipo.setMinimumHeight(30);
			tabla2.addCell(dataequipo);
		}



		if (equipo == null) {
			dataequipo = new PdfPCell(new Phrase(outstyle));
			tabla2.addCell(dataequipo);
			dataequipo = new PdfPCell(new Phrase(" "));
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
			tabla2.addCell(dataequipo);
		} else {
			dataequipo = new PdfPCell(new Phrase(outstyle));
			tabla2.addCell(dataequipo);
			Chunk outequipo = new Chunk(equipo.getMarca());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getModelo());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getSerie());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
			outequipo = new Chunk(equipo.getPlaca_inventario());
			outequipo.setFont(rta);
			dataequipo = new PdfPCell(new Phrase(outequipo));
			tabla2.addCell(dataequipo);
		}

		dataequipo = new PdfPCell(new Phrase(ubpaststyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(ubpaststylerta));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(ubactualstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(ubactualstylerta));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(obsstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(obsstylerta));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(50);
		tabla2.addCell(dataequipo);

		tabla2.setSpacingAfter(10);

		Chunk recibe = new Chunk("NOMBRE DE QUIEN RECIBE:");
		recibe.setFont(rta);

		Chunk reciberta = new Chunk(mtto.getAutor_recibido());
		reciberta.setFont(rta);

		Chunk firm = new Chunk("FIRMA:");
		firm.setFont(rta);

		Chunk cedula = new Chunk("CEDULA:");
		cedula.setFont(rta);

		Chunk cargo = new Chunk("CARGO:");
		cargo.setFont(rta);

		PdfPTable tablainv2 = new PdfPTable(7);
		PdfPCell inv2 = new PdfPCell(new Phrase(recibe));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(reciberta));
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		inv2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(firm));
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cargo));
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(3);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cedula));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabla2);
		document.add(tablainv2);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalEntregasysPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("ENTREGA DE EQUIPOS HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-03");
		Chunk vs = new Chunk("VERSION: 05");
		Chunk date = new Chunk("Fecha:08/03/2024");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA O PROCESO:");

		espnull.setFont(rta);
		Chunk space = new Chunk("                         ");
		space.setFont(fuentesubrayada);
		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);

		Chunk realizadostyle = new Chunk("NOMBRE DE QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);
		inv1 = new PdfPCell(new Phrase(realizadostyle));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(4);
		tablainv1.addCell(inv1);

		tablainv1.setSpacingAfter(10);
		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(fuenteEnunciados);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(fuenteEnunciados);

		Chunk ubactualstyle = new Chunk("UBICACIÓN ACTUAL:");
		ubactualstyle.setFont(fuenteEnunciados);

		Chunk allonestyle = new Chunk("TODO EN UNO");
		allonestyle.setFont(rta);

		Chunk pCPstyle = new Chunk("PORTATIL");
		pCPstyle.setFont(rta);
		
		Chunk tabletstyle = new Chunk("TABLET");
		tabletstyle.setFont(rta);
		
		Chunk procstyle = new Chunk("PROCESADOR");
		procstyle.setFont(rta);

		Chunk ramstyle = new Chunk("RAM");
		ramstyle.setFont(rta);

		Chunk hdstyle = new Chunk("DISCO DURO");
		hdstyle.setFont(rta);

		Chunk softstyle = new Chunk("SOFTWARE");
		softstyle.setFont(rta);

		Chunk escstyle = new Chunk("ESCANER");
		escstyle.setFont(rta);

		Chunk printstyle = new Chunk("IMPRESORA");
		printstyle.setFont(rta);

		Chunk outstyle = new Chunk("EQUIPO QUE SE RETIRA");
		outstyle.setFont(rta);

		Chunk ubpaststyle = new Chunk("UBICACIÓN ANTERIOR:");
		ubpaststyle.setFont(fuenteEnunciados);

		Chunk obsstyle = new Chunk("OBSERVACIONES");
		obsstyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(5);

		PdfPCell dataequipo = new PdfPCell(new Phrase(equipostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastyle));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(allonestyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		
		dataequipo = new PdfPCell(new Phrase(pCPstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		
		dataequipo = new PdfPCell(new Phrase(escstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(printstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		
		dataequipo = new PdfPCell(new Phrase(tabletstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		
		dataequipo = new PdfPCell(new Phrase(procstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(ramstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(hdstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(softstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(30);
		tabla2.addCell(dataequipo);


		dataequipo = new PdfPCell(new Phrase(outstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(ubpaststyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(ubactualstyle));
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(obsstyle));
		dataequipo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(" "));
		dataequipo.setColspan(4);
		dataequipo.setMinimumHeight(50);
		tabla2.addCell(dataequipo);

		tabla2.setSpacingAfter(10);

		Chunk recibe = new Chunk("NOMBRE DE QUIEN RECIBE:");
		recibe.setFont(rta);

		Chunk firm = new Chunk("FIRMA:");
		firm.setFont(rta);

		Chunk cedula = new Chunk("CEDULA:");
		cedula.setFont(rta);

		Chunk cargo = new Chunk("CARGO:");
		cargo.setFont(rta);

		PdfPTable tablainv2 = new PdfPTable(7);
		PdfPCell inv2 = new PdfPCell(new Phrase(recibe));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(firm));
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cargo));
		inv2.setBorder(Rectangle.NO_BORDER);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(3);
		tablainv2.addCell(inv2);

		inv2 = new PdfPCell(new Phrase(cedula));
		inv2.setBorder(Rectangle.NO_BORDER);
		inv2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tablainv2.addCell(inv2);
		inv2 = new PdfPCell(new Phrase(""));
		inv2.setBorder(Rectangle.BOTTOM);
		inv2.setColspan(2);
		tablainv2.addCell(inv2);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabla2);
		document.add(tablainv2);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalDanosPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("REPORTE DE DAÑO DIGITAL DE DOTACION HOSPITALARIA");

		Chunk code = new Chunk("CÓDIGO. F-54");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:29/10/2021");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);

		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);

		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(fuenteEnunciados);

		Chunk realizadostyle = new Chunk("NOMBRE DE QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk modelostyle = new Chunk("MODELO: ");
		modelostyle.setFont(fuenteEnunciados);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk placastyle = new Chunk("INVENTARIO: ");
		placastyle.setFont(fuenteEnunciados);

		Chunk reportantestyle = new Chunk("DATOS DEL REPORTANTE");
		reportantestyle.setFont(fuenteTituloHospital);

		Chunk nombrestyle = new Chunk("NOMBRE: ");
		nombrestyle.setFont(rta);

		Chunk cedulastyle = new Chunk("CEDULA: ");
		cedulastyle.setFont(rta);

		Chunk phonestyle = new Chunk("TELEFONO: ");
		phonestyle.setFont(rta);

		Chunk cargostyle = new Chunk("CARGO: ");
		cargostyle.setFont(rta);

		Chunk servicestyle = new Chunk("SERVICIO ");
		servicestyle.setFont(rta);

		Chunk descripstyle = new Chunk("DESCRIPCIÓN DE LO SUCEDIDO");
		descripstyle.setFont(fuenteTituloHospital);

		Chunk subdestyle = new Chunk("El informe Debe contener Modo, Tiempo, Lugar.");
		subdestyle.setFont(rta);

		Chunk estadostyle = new Chunk("ESTADO DEL EQUIPO");
		estadostyle.setFont(fuenteTituloHospital);

		Chunk buenostyle = new Chunk("Bueno");
		buenostyle.setFont(fuenteEnunciados);

		Chunk regularstyle = new Chunk("Regular");
		regularstyle.setFont(fuenteEnunciados);

		Chunk malostyle = new Chunk("Malo");
		malostyle.setFont(fuenteEnunciados);

		Chunk coorstyle = new Chunk("V°B COORDINADOR DEL SERVICIO");
		coorstyle.setFont(fuenteTituloHospital);

		Chunk namecorstyle = new Chunk("Nombre");
		namecorstyle.setFont(fuenteEnunciados);

		Chunk cargocorstyle = new Chunk("Cargo");
		cargocorstyle.setFont(fuenteEnunciados);

		Chunk firmacorstyle = new Chunk("Firma");
		firmacorstyle.setFont(fuenteEnunciados);

		Chunk recibidostyle = new Chunk(
				"RECIBIDO (Espacio exlusivo de Mantenimiento, Biomédica, TIC, Esterilización, Almacén)");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk fecharecstyle = new Chunk("Fecha de recepción");
		fecharecstyle.setFont(rta);

		Chunk dependenciastyle = new Chunk("Dependencia");
		dependenciastyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		PdfPCell dataequipo = new PdfPCell(new Phrase(fecha));
		dataequipo.setColspan(6);
		tabla2.addCell(dataequipo);

		dataequipo = new PdfPCell(new Phrase(equipostyle));
		dataequipo.setColspan(3);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(marcastyle));
		dataequipo.setColspan(3);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(modelostyle));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(seriestyle));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);
		dataequipo = new PdfPCell(new Phrase(placastyle));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);

		tabla2.setSpacingAfter(10);

		PdfPTable tabla3 = new PdfPTable(4);
		PdfPCell datareport = new PdfPCell(new Phrase(reportantestyle));
		datareport.setColspan(4);
		datareport.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(nombrestyle));
		datareport.setColspan(2);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(cedulastyle));
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(phonestyle));
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(cargostyle));
		datareport.setColspan(2);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(servicestyle));
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(""));
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(descripstyle));
		datareport.setColspan(4);
		datareport.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(""));
		datareport.setColspan(4);
		datareport.setMinimumHeight(400);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(subdestyle));
		datareport.setColspan(4);
		tabla3.addCell(datareport);

		tabla3.setSpacingAfter(10);

		Chunk estadofisico = new Chunk("FISICO");
		estadofisico.setFont(fuenteEnunciados);

		Chunk estadofuncional = new Chunk("FUNCIONAL");
		estadofuncional.setFont(fuenteEnunciados);

		PdfPTable table4 = new PdfPTable(6);
		PdfPCell estadocell = new PdfPCell(new Phrase(estadostyle));
		estadocell.setColspan(6);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(estadofisico));
		estadocell.setColspan(3);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(estadofuncional));
		estadocell.setColspan(3);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(buenostyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(regularstyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(malostyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(buenostyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(regularstyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(malostyle));
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		table4.setSpacingAfter(10);

		PdfPTable tabla5 = new PdfPTable(6);
		PdfPCell datacor = new PdfPCell(new Phrase(coorstyle));
		datacor.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacor.setColspan(6);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(namecorstyle));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(cargocorstyle));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(firmacorstyle));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(recibidostyle));
		datacor.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacor.setColspan(6);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(fecharecstyle));
		datacor.setColspan(3);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(dependenciastyle));
		datacor.setColspan(3);
		tabla5.addCell(datacor);

		document.add(tabla);

		document.add(tabla2);

		document.add(tabla3);

		document.add(table4);
		document.add(tabla5);

		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getDanosPDF(SystemMantenimiento mtto) throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font fuentesubrayada = new Font();
		fuentesubrayada.setStyle(Font.UNDERLINE);
		fuentesubrayada.setSize(8);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("REPORTE DE DAÑO DIGITAL DE DOTACION HOSPITALARIA");

		Chunk code = new Chunk("CÓDIGO. F-54");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:29/10/2021");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);

		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);

		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(fuenteEnunciados);

		Chunk realizadostyle = new Chunk("NOMBRE DE QUIEN ENTREGA: ");
		realizadostyle.setFont(rta);

		Chunk equipostyle = new Chunk("EQUIPO: ");
		equipostyle.setFont(fuenteEnunciados);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(fuenteEnunciados);

		Chunk modelostyle = new Chunk("MODELO: ");
		modelostyle.setFont(fuenteEnunciados);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(fuenteEnunciados);

		Chunk placastyle = new Chunk("INVENTARIO: ");
		placastyle.setFont(fuenteEnunciados);

		Chunk reportantestyle = new Chunk("DATOS DEL REPORTANTE");
		reportantestyle.setFont(fuenteTituloHospital);

		Chunk nombrestyle = new Chunk("NOMBRE: ");
		nombrestyle.setFont(rta);

		Chunk cedulastyle = new Chunk("CEDULA: ");
		cedulastyle.setFont(rta);

		Chunk phonestyle = new Chunk("TELEFONO: ");
		phonestyle.setFont(rta);

		Chunk cargostyle = new Chunk("CARGO: ");
		cargostyle.setFont(rta);

		Chunk servicestyle = new Chunk("SERVICIO ");
		servicestyle.setFont(rta);

		Chunk descripstyle = new Chunk("DESCRIPCIÓN DE LO SUCEDIDO");
		descripstyle.setFont(fuenteTituloHospital);

		Chunk subdestyle = new Chunk("El informe Debe contener Modo, Tiempo, Lugar.");
		subdestyle.setFont(rta);

		Chunk estadostyle = new Chunk("ESTADO DEL EQUIPO");
		estadostyle.setFont(fuenteTituloHospital);

		Chunk coorstyle = new Chunk("V°B COORDINADOR DEL SERVICIO");
		coorstyle.setFont(fuenteTituloHospital);

		Chunk namecorstyle = new Chunk("Nombre ");
		namecorstyle.setFont(fuenteEnunciados);

		Chunk cargocorstyle = new Chunk("Cargo ");
		cargocorstyle.setFont(fuenteEnunciados);

		Chunk firmacorstyle = new Chunk("Firma ");
		firmacorstyle.setFont(fuenteEnunciados);

		Chunk recibidostyle = new Chunk(
				"RECIBIDO (Espacio exlusivo de Mantenimiento, Biomédica, TIC, Esterilización, Almacén)");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk fecharecstyle = new Chunk("Fecha de recepción ");
		fecharecstyle.setFont(rta);

		Chunk dependenciastyle = new Chunk("Dependencia ");
		dependenciastyle.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		Phrase complete = new Phrase();
		complete.add(fecha);
		Chunk datertastyle = new Chunk(" " + String.valueOf(mtto.getFecha()));
		datertastyle.setFont(rta);
		complete.add(datertastyle);
		PdfPCell dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(6);
		tabla2.addCell(dataequipo);

		complete = new Phrase();
		complete.add(equipostyle);
		Chunk datarta = new Chunk(mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo());
		datarta.setFont(rta);
		complete.add(datarta);
		dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(3);
		tabla2.addCell(dataequipo);

		complete = new Phrase();
		complete.add(marcastyle);
		datarta = new Chunk(mtto.getEquipo().getMarca());
		datarta.setFont(rta);
		complete.add(datarta);
		dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(3);
		tabla2.addCell(dataequipo);

		complete = new Phrase();
		complete.add(modelostyle);
		datarta = new Chunk(mtto.getEquipo().getModelo());
		datarta.setFont(rta);
		complete.add(datarta);
		dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);

		complete = new Phrase();
		complete.add(seriestyle);
		datarta = new Chunk(mtto.getEquipo().getSerie());
		datarta.setFont(rta);
		complete.add(datarta);
		dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);

		complete = new Phrase();
		complete.add(placastyle);
		datarta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		datarta.setFont(rta);
		complete.add(datarta);
		dataequipo = new PdfPCell(new Phrase(complete));
		dataequipo.setColspan(2);
		tabla2.addCell(dataequipo);

		tabla2.setSpacingAfter(10);

		PdfPTable tabla3 = new PdfPTable(4);
		PdfPCell datareport = new PdfPCell(new Phrase(reportantestyle));
		datareport.setColspan(4);
		datareport.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla3.addCell(datareport);

		ArrayList<String> datosdano = new ArrayList<String>(Arrays.asList(mtto.getRutasoftware().split(";")));

		complete = new Phrase();
		complete.add(nombrestyle);
		datarta = new Chunk(mtto.getAutor_realizado());
		datarta.setFont(rta);
		complete.add(datarta);
		datareport = new PdfPCell(complete);
		datareport.setColspan(2);
		tabla3.addCell(datareport);

		complete = new Phrase();
		complete.add(cedulastyle);
		datarta = new Chunk(datosdano.get(0));
		datarta.setFont(rta);
		complete.add(datarta);
		datareport = new PdfPCell(complete);
		tabla3.addCell(datareport);

		complete = new Phrase();
		complete.add(phonestyle);
		datarta = new Chunk(datosdano.get(1));
		datarta.setFont(rta);
		complete.add(datarta);
		datareport = new PdfPCell(new Phrase(complete));
		tabla3.addCell(datareport);

		complete = new Phrase();
		complete.add(cargostyle);
		datarta = new Chunk(datosdano.get(2));
		datarta.setFont(rta);
		complete.add(datarta);
		datareport = new PdfPCell(complete);
		datareport.setColspan(2);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(servicestyle));
		tabla3.addCell(datareport);

		Chunk servicerta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		servicerta.setFont(rta);
		datareport = new PdfPCell(new Phrase(servicerta));
		tabla3.addCell(datareport);

		Chunk buenostyle = new Chunk("Bueno");
		buenostyle.setFont(fuenteEnunciados);

		Chunk regularstyle = new Chunk("Regular");
		regularstyle.setFont(fuenteEnunciados);

		Chunk malostyle = new Chunk("Malo");
		malostyle.setFont(fuenteEnunciados);

		Chunk nbuenostyle = new Chunk("Bueno X");
		nbuenostyle.setFont(fuenteEnunciados);

		Chunk nregularstyle = new Chunk("Regular X");
		nregularstyle.setFont(fuenteEnunciados);

		Chunk nmalostyle = new Chunk("Malo X");
		nmalostyle.setFont(fuenteEnunciados);

		ArrayList<String> estados = new ArrayList<String>(Arrays.asList(datosdano.get(6).split(",")));

		String efunc = estados.get(1);
		String efisc = estados.get(0);

		datareport = new PdfPCell(new Phrase(descripstyle));
		datareport.setColspan(4);
		datareport.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla3.addCell(datareport);

		Chunk descrta = new Chunk(mtto.getObservacionesh());
		descrta.setFont(rta);
		datareport = new PdfPCell(new Phrase(descrta));
		datareport.setColspan(4);
		datareport.setMinimumHeight(400);
		tabla3.addCell(datareport);

		datareport = new PdfPCell(new Phrase(subdestyle));
		datareport.setColspan(4);
		tabla3.addCell(datareport);

		tabla3.setSpacingAfter(10);

		Chunk estadofisico = new Chunk("FISICO");
		estadofisico.setFont(fuenteEnunciados);

		Chunk estadofuncional = new Chunk("FUNCIONAL");
		estadofuncional.setFont(fuenteEnunciados);

		PdfPTable table4 = new PdfPTable(6);
		PdfPCell estadocell = new PdfPCell(new Phrase(estadostyle));
		estadocell.setColspan(6);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(estadofisico));
		estadocell.setColspan(3);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		estadocell = new PdfPCell(new Phrase(estadofuncional));
		estadocell.setColspan(3);
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efisc.equals("1")) {
			estadocell = new PdfPCell(new Phrase(nbuenostyle));
		} else {
			estadocell = new PdfPCell(new Phrase(buenostyle));
		}

		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efisc.equals("2")) {
			estadocell = new PdfPCell(new Phrase(nregularstyle));
		} else {
			estadocell = new PdfPCell(new Phrase(regularstyle));
		}
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efisc.equals("3")) {
			estadocell = new PdfPCell(new Phrase(nmalostyle));
		} else {
			estadocell = new PdfPCell(new Phrase(malostyle));
		}
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efunc.equals("1")) {
			estadocell = new PdfPCell(new Phrase(nbuenostyle));
		} else {
			estadocell = new PdfPCell(new Phrase(buenostyle));
		}
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efunc.equals("2")) {
			estadocell = new PdfPCell(new Phrase(nregularstyle));
		} else {
			estadocell = new PdfPCell(new Phrase(regularstyle));
		}
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		if (efunc.equals("3")) {
			estadocell = new PdfPCell(new Phrase(nmalostyle));
		} else {
			estadocell = new PdfPCell(new Phrase(malostyle));
		}
		estadocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table4.addCell(estadocell);

		table4.setSpacingAfter(10);

		PdfPTable tabla5 = new PdfPTable(6);
		PdfPCell datacor = new PdfPCell(new Phrase(coorstyle));
		datacor.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacor.setColspan(6);
		tabla5.addCell(datacor);

		complete = new Phrase();
		complete.add(namecorstyle);
		datarta = new Chunk(mtto.getAutor_recibido());
		datarta.setFont(rta);
		complete.add(datarta);
		datacor = new PdfPCell(new Phrase(complete));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		complete = new Phrase();
		complete.add(cargocorstyle);
		datarta = new Chunk(datosdano.get(3));
		datarta.setFont(rta);
		complete.add(datarta);
		datacor = new PdfPCell(new Phrase(complete));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(firmacorstyle));
		datacor.setColspan(2);
		tabla5.addCell(datacor);

		datacor = new PdfPCell(new Phrase(recibidostyle));
		datacor.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacor.setColspan(6);
		tabla5.addCell(datacor);

		complete = new Phrase();
		complete.add(fecharecstyle);
		datarta = new Chunk(datosdano.get(4));
		datarta.setFont(rta);
		complete.add(datarta);
		datacor = new PdfPCell(new Phrase(complete));
		datacor.setColspan(3);
		tabla5.addCell(datacor);

		complete = new Phrase();
		complete.add(dependenciastyle);
		datarta = new Chunk(datosdano.get(5));
		datarta.setFont(rta);
		complete.add(datarta);
		datacor = new PdfPCell(new Phrase(complete));
		datacor.setColspan(3);
		tabla5.addCell(datacor);

		document.add(tabla);

		document.add(tabla2);

		document.add(tabla3);

		document.add(table4);
		document.add(tabla5);

		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getCorrectivoPDF(SystemMantenimiento mtto) throws DocumentException, IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO CORRECTIVO HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-02");
		Chunk vs = new Chunk("VERSION: 03");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda0.setMinimumHeight(40);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda3);
		tabla.addCell(celda4);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA SOLICITANTE:");

		espnull.setFont(rta);

		Chunk espnullrta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		espnullrta.setFont(rta);

		Chunk space = new Chunk("                         ");

		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		Chunk consec = new Chunk("CONSECUTIVO: ");
		consec.setFont(rta);
		Chunk consecrta = new Chunk(mtto.getNumero_reporte());
		consecrta.setFont(rta);

		Chunk personsol = new Chunk("PERSONA QUE LO SOLICITA:");
		personsol.setFont(rta);
		Chunk personsolrta = new Chunk(mtto.getAutor_recibido());
		personsolrta.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(espnullrta));
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(consec));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(consecrta));
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);

		inv1 = new PdfPCell(new Phrase(personsol));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(personsolrta));
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(fecharta));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);

		tablainv1.setSpacingAfter(10);

		Chunk equipotitle = new Chunk("EQUIPO:");
		equipotitle.setFont(rta);
		Chunk equipotitlerta = new Chunk(
				mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo() + " " + mtto.getEquipo().getNombre_equipo());
		equipotitlerta.setFont(rta);
		Chunk marcatitle = new Chunk("MARCA:");
		marcatitle.setFont(rta);
		Chunk marcatitlerta = new Chunk(mtto.getEquipo().getMarca());
		marcatitlerta.setFont(rta);

		Chunk modelotitle = new Chunk("MODELO:");
		modelotitle.setFont(rta);
		Chunk modelotitlerta = new Chunk(mtto.getEquipo().getModelo());
		modelotitlerta.setFont(rta);

		Chunk serialtitle = new Chunk("SERIE:");
		serialtitle.setFont(rta);
		Chunk serialtitlerta = new Chunk(mtto.getEquipo().getSerie());
		serialtitlerta.setFont(rta);

		Chunk placatitle = new Chunk("PLACA:");
		placatitle.setFont(rta);
		Chunk placatitlerta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		placatitlerta.setFont(rta);

		Chunk attservicetitle = new Chunk("ATENCIÓN AL SERVICIO:");
		attservicetitle.setFont(rta);
		Chunk attservicetitlerta = new Chunk(
				mtto.getEquipo().getUbicacion() + " " + mtto.getEquipo().getUbicacion_especifica());
		attservicetitlerta.setFont(rta);

		Chunk fallatitle = new Chunk("FALLA TÉCNICA");
		fallatitle.setFont(writers);
		Chunk fallatitlerta = new Chunk(mtto.getObservacionesh());
		fallatitlerta.setFont(rta);

		Chunk sntitle = new Chunk("SOLUCIÓN");
		sntitle.setFont(writers);
		String obscor = mtto.getRutasoftware();
		if (obscor == null) {
			obscor = "";
		}
		Chunk sntitlerta = new Chunk(obscor);
		sntitlerta.setFont(rta);

		PdfPTable tabladata = new PdfPTable(6);
		PdfPCell datacell = new PdfPCell(new Phrase(equipotitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(equipotitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(marcatitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(marcatitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(modelotitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(modelotitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(serialtitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(serialtitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(placatitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(placatitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(attservicetitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(attservicetitlerta));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(fallatitle));
		datacell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(sntitle));
		datacell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(fallatitlerta));
		datacell.setMinimumHeight(60);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(sntitlerta));
		datacell.setMinimumHeight(60);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		tabladata.setSpacingAfter(10);

		Chunk personreceive = new Chunk("TÉCNICO QUE REALIZA EL SERVICIO:");
		personreceive.setFont(rta);
		Chunk personreceiverta = new Chunk(mtto.getAutor_realizado());
		personreceiverta.setFont(rta);

		Chunk timereceive = new Chunk("TIEMPO EMPLEADO:");
		timereceive.setFont(rta);
		LocalTime hi = mtto.getHora_inicio().toLocalTime();
		LocalTime hf = mtto.getHora_terminacion().toLocalTime();

		hf = hf.minusHours(hi.getHour());
		hf = hf.minusMinutes(hi.getMinute());
		hf = hf.minusSeconds(hi.getSecond());

		Chunk timereceiverta = new Chunk(String.valueOf(hf));
		timereceiverta.setFont(rta);

		Chunk obsreceive = new Chunk("OBSERVACIONES:");
		obsreceive.setFont(rta);

		Chunk obsreceiverta = new Chunk(mtto.getObservacioness());
		obsreceiverta.setFont(rta);

		Chunk nombrereceive = new Chunk("NOMBRE DE QUIEN RECIBE:");
		nombrereceive.setFont(rta);
		Chunk nombrereceiverta = new Chunk(mtto.getAutor_recibido());
		nombrereceiverta.setFont(rta);

		Chunk firmareceive = new Chunk("FIRMA:");
		firmareceive.setFont(rta);

		Chunk cedulareceive = new Chunk("CEDULA:");
		cedulareceive.setFont(rta);

		PdfPTable tablareceive = new PdfPTable(5);
		PdfPCell receivecell = new PdfPCell(new Phrase(personreceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(personreceiverta));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		receivecell.setColspan(2);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(timereceive));
		receivecell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(timereceiverta));
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		receivecell.setBorder(Rectangle.BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(obsreceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setMinimumHeight(30);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(obsreceiverta));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setColspan(4);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(nombrereceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(nombrereceiverta));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		receivecell.setColspan(2);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(cedulareceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);

		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(firmareceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setMinimumHeight(30);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setColspan(3);
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabladata);
		document.add(tablareceive);
		document.close();
		// Retornamos la variable al finalizar
		return bos;
	}

	public ByteArrayOutputStream getoriginalCorrectivoPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO CORRECTIVO HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-02");
		Chunk vs = new Chunk("VERSION: 03");
		Chunk date = new Chunk("Fecha: 29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda0.setMinimumHeight(40);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda3);
		tabla.addCell(celda4);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk espnull = new Chunk("ÁREA SOLICITANTE:");

		espnull.setFont(rta);
		Chunk space = new Chunk("                         ");

		Chunk fecha = new Chunk("FECHA:");
		fecha.setFont(rta);

		Chunk consec = new Chunk("CONSECUTIVO: ");
		consec.setFont(rta);

		Chunk personsol = new Chunk("PERSONA QUE LO SOLICITA:");
		personsol.setFont(rta);

		PdfPTable tablainv1 = new PdfPTable(5);
		PdfPCell inv1 = new PdfPCell(new Phrase(espnull));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(consec));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);

		inv1 = new PdfPCell(new Phrase(personsol));
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);
		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		inv1.setColspan(2);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(fecha));
		inv1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		inv1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		inv1.setBorder(Rectangle.NO_BORDER);
		tablainv1.addCell(inv1);

		inv1 = new PdfPCell(new Phrase(""));
		inv1.setBorder(Rectangle.BOTTOM);
		tablainv1.addCell(inv1);
		inv1.setMinimumHeight(20);

		tablainv1.setSpacingAfter(10);

		Chunk equipotitle = new Chunk("EQUIPO:");
		equipotitle.setFont(rta);
		Chunk marcatitle = new Chunk("MARCA:");
		marcatitle.setFont(rta);
		Chunk modelotitle = new Chunk("MODELO:");
		modelotitle.setFont(rta);
		Chunk serialtitle = new Chunk("SERIE:");
		serialtitle.setFont(rta);
		Chunk placatitle = new Chunk("PLACA:");
		placatitle.setFont(rta);
		Chunk attservicetitle = new Chunk("ATENCIÓN AL SERVICIO:");
		attservicetitle.setFont(rta);

		Chunk fallatitle = new Chunk("FALLA TÉCNICA");
		fallatitle.setFont(writers);
		Chunk sntitle = new Chunk("SOLUCIÓN");
		sntitle.setFont(writers);

		PdfPTable tabladata = new PdfPTable(6);
		PdfPCell datacell = new PdfPCell(new Phrase(equipotitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(marcatitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(modelotitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(serialtitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(placatitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(attservicetitle));
		datacell.setColspan(2);
		tabladata.addCell(datacell);
		datacell = new PdfPCell(new Phrase(""));
		datacell.setColspan(4);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(fallatitle));
		datacell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(sntitle));
		datacell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		datacell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		datacell = new PdfPCell(new Phrase(""));
		datacell.setMinimumHeight(60);
		datacell.setColspan(3);
		tabladata.addCell(datacell);

		tabladata.addCell(datacell);

		tabladata.setSpacingAfter(10);

		Chunk personreceive = new Chunk("TÉCNICO QUE REALIZA EL SERVICIO:");
		personreceive.setFont(rta);

		Chunk timereceive = new Chunk("TIEMPO EMPLEADO:");
		timereceive.setFont(rta);

		Chunk obsreceive = new Chunk("OBSERVACIONES:");
		obsreceive.setFont(rta);

		Chunk nombrereceive = new Chunk("NOMBRE DE QUIEN RECIBE:");
		nombrereceive.setFont(rta);

		Chunk firmareceive = new Chunk("FIRMA:");
		firmareceive.setFont(rta);

		Chunk cedulareceive = new Chunk("CEDULA:");
		cedulareceive.setFont(rta);

		PdfPTable tablareceive = new PdfPTable(5);
		PdfPCell receivecell = new PdfPCell(new Phrase(personreceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setColspan(2);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(timereceive));
		receivecell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(obsreceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setMinimumHeight(30);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setColspan(4);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(nombrereceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setColspan(2);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(cedulareceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(firmareceive));
		receivecell.setBorder(Rectangle.NO_BORDER);
		receivecell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setBorder(Rectangle.BOTTOM);
		receivecell.setMinimumHeight(30);
		tablareceive.addCell(receivecell);

		receivecell = new PdfPCell(new Phrase(""));
		receivecell.setColspan(3);
		receivecell.setBorder(Rectangle.NO_BORDER);
		tablareceive.addCell(receivecell);

		document.add(tabla);
		document.add(tablainv1);
		document.add(tabladata);
		document.add(tablareceive);
		document.close();
		// Retornamos la variable al finalizar
		return bos;
	}

	public ByteArrayOutputStream getoriginalHsysVPDF() throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("HOJA DE VIDA DIGITAL EQUIPO COMPUTO Y DE COMUNICACIONES HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-06");
		Chunk vs = new Chunk("VERSION: 06");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk idenstyle = new Chunk("IDENTIFICACIÓN");
		idenstyle.setFont(fuenteTituloHospital);

		Chunk depstyle = new Chunk("DEPARTAMENTO:");
		depstyle.setFont(fuenteEnunciados);

		Chunk deprtastyle = new Chunk("");
		deprtastyle.setFont(rta);

		Chunk munstyle = new Chunk("MUNICIPIO:");
		munstyle.setFont(fuenteEnunciados);

		Chunk munrtastyle = new Chunk("");
		munrtastyle.setFont(rta);

		Chunk addresstyle = new Chunk("DIRECCIÓN:");
		addresstyle.setFont(fuenteEnunciados);

		Chunk addresrtastyle = new Chunk("");
		addresrtastyle.setFont(rta);

		Chunk telestyle = new Chunk("TELÉFONO:");
		telestyle.setFont(fuenteEnunciados);

		Chunk telertastyle = new Chunk("");
		telertastyle.setFont(rta);

		Chunk emailstyle = new Chunk("E-MAIL:");
		emailstyle.setFont(fuenteEnunciados);

		Chunk emailrtastyle = new Chunk("");
		emailrtastyle.setFont(correo);

		Chunk codstyle = new Chunk("NIVEL:");
		codstyle.setFont(fuenteEnunciados);

		Chunk codrtastyle = new Chunk("");
		codrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteEnunciados);

		Chunk servicertastyle = new Chunk("");
		servicertastyle.setFont(fuenteEnunciados);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteEnunciados);

		Chunk ubrtastyle = new Chunk("");
		ubrtastyle.setFont(fuenteEnunciados);

		PdfPTable tabladhos = new PdfPTable(5);

		PdfPCell idencell = new PdfPCell(new Phrase(idenstyle));
		idencell.setColspan(6);
		idencell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		PdfPCell fotocell = new PdfPCell(new Phrase(""));

		fotocell.setRowspan(4);
		fotocell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		fotocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		fotocell.setMinimumHeight(80);

		tabladhos.addCell(idencell);

		tabladhos.addCell(new PdfPCell(new Phrase(depstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(deprtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munrtastyle)));
		tabladhos.addCell(fotocell);
		tabladhos.addCell(new PdfPCell(new Phrase(addresstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(addresrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telestyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telertastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(emailstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(emailrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codrtastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(serviceestyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(servicertastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubrtastyle)));

		tabladhos.setSpacingAfter(10);

		Chunk dataeqstyle = new Chunk("DATOS DEL EQUIPO");
		dataeqstyle.setFont(fuenteTituloHospital);

		Chunk equipostyle = new Chunk("NOMBRE DEL EQUIPO: ");
		equipostyle.setFont(rta);

		Chunk equiportastyle = new Chunk("");
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(rta);

		Chunk marcartastyle = new Chunk("");
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(rta);

		Chunk modelortastyle = new Chunk("");
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(rta);

		Chunk seriertastyle = new Chunk("");
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(rta);

		Chunk placartastyle = new Chunk("");
		placartastyle.setFont(rta);

		Chunk anofstyle = new Chunk("TIPO DE EQUIPO:");
		anofstyle.setFont(rtasmall);

		Chunk anofrtastyle = new Chunk("");
		anofrtastyle.setFont(rta);

		Chunk fadqstyle = new Chunk("FORMA DE ADQUISICIÓN");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk xrtastyle = new Chunk("X");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk compradstyle = new Chunk("COMPRA DIRECTA:");
		compradstyle.setFont(rta);

		Chunk convstyle = new Chunk("CONVENIO:");
		convstyle.setFont(rta);

		Chunk donadostyle = new Chunk("DONADO:");
		donadostyle.setFont(rta);

		Chunk asignminstyle = new Chunk("ASIGNADO POR EL MINISTERIO:");
		asignminstyle.setFont(rta);

		Chunk asigngobstyle = new Chunk("ASIGNADO POR LA GOBERNACIÓN:");
		asigngobstyle.setFont(rtasmall);

		Chunk comodatostyle = new Chunk("COMODATO:");
		comodatostyle.setFont(rta);

		Chunk dcomprastyle = new Chunk("DATOS DE LA COMPRA");
		dcomprastyle.setFont(fuenteTituloHospital);

		Chunk fcomprastyle = new Chunk("FECHA DE COMPRA:");
		fcomprastyle.setFont(rta);

		Chunk fcomprartastyle = new Chunk("");
		fcomprartastyle.setFont(rta);

		Chunk finstallstyle = new Chunk("FECHA DE INSTALACIÓN:");
		finstallstyle.setFont(rta);

		Chunk finstallrtastyle = new Chunk("");
		finstallrtastyle.setFont(rta);

		Chunk fstartstyle = new Chunk("FECHA DE INICIO DE OPERACIÓN:");
		fstartstyle.setFont(rta);

		Chunk fstartrtastyle = new Chunk("");
		fstartrtastyle.setFont(rta);

		Chunk fvctistyle = new Chunk("FECHA VENCIMIENTO GARANTÍA:");
		fvctistyle.setFont(rta);

		Chunk fvctirtastyle = new Chunk("");
		fvctirtastyle.setFont(rta);

		Chunk fcostostyle = new Chunk("COSTO EN PESOS:");
		fcostostyle.setFont(rta);

		Chunk fcostortastyle = new Chunk("");
		fcostortastyle.setFont(rta);

		Chunk contractstyle = new Chunk("CONTRATO:");
		contractstyle.setFont(rta);

		Chunk contractrtastyle = new Chunk("");
		contractrtastyle.setFont(rta);

		PdfPTable tablaeqcom = new PdfPTable(16);
		PdfPCell datoequipo = new PdfPCell(new Phrase(dataeqstyle));
		datoequipo.setColspan(5);
		PdfPCell datoadq = new PdfPCell(new Phrase(fadqstyle));
		datoadq.setColspan(5);
		PdfPCell datocompra = new PdfPCell(new Phrase(dcomprastyle));
		datocompra.setColspan(6);

		tablaeqcom.addCell(datoequipo);
		tablaeqcom.addCell(datoadq);
		tablaeqcom.addCell(datocompra);
		// first row
		datoequipo = new PdfPCell(new Phrase(equipostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(equiportastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(compradstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcomprastyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcomprartastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// second row
		datoequipo = new PdfPCell(new Phrase(marcastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(marcartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(convstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(finstallstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(finstallrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// third row
		datoequipo = new PdfPCell(new Phrase(modelostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(modelortastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(donadostyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fstartstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fstartrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fourth row
		datoequipo = new PdfPCell(new Phrase(seriestyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(seriertastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asignminstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fvctistyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fvctirtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fifth row
		datoequipo = new PdfPCell(new Phrase(placastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(placartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asigngobstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcostostyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcostortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// sixth row
		datoequipo = new PdfPCell(new Phrase(anofstyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(anofrtastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(comodatostyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		datoadq = new PdfPCell(new Phrase(""));

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(contractstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		tablaeqcom.setSpacingAfter(10);

		// table registro tecnico
		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(fuenteEnunciados);

		PdfPTable tablert = new PdfPTable(4);
		Chunk rtstyle = new Chunk("REGISTRO TÉCNICO");
		rtstyle.setFont(fuenteTituloHospital);

		PdfPCell cellrt = new PdfPCell(new Phrase(rtstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellrt.setColspan(4);
		tablert.addCell(cellrt);

		Chunk accstyle = new Chunk("ACCESORIOS");
		accstyle.setFont(fuenteEnunciados);
		Chunk marcaaccstyle = new Chunk("MARCA:");
		marcaaccstyle.setFont(fuenteEnunciados);
		Chunk modeloccstyle = new Chunk("MODELO:");
		modeloccstyle.setFont(fuenteEnunciados);

		cellrt = new PdfPCell(new Phrase(accstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(marcaaccstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(modeloccstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(obsstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(" "));
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);
		tablert.addCell(cellrt);

		tablert.setSpacingAfter(10);
		// table info
		PdfPTable tableinfoe = new PdfPTable(4);
		Chunk infoestyle = new Chunk("INFORMACIÓN DEL EQUIPO");
		infoestyle.setFont(fuenteTituloHospital);
		PdfPCell cellinfoerta = new PdfPCell(new Phrase(" "));

		PdfPCell cellinfoe = new PdfPCell(new Phrase(infoestyle));
		cellinfoe.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellinfoe.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellinfoe.setColspan(4);
		tableinfoe.addCell(cellinfoe);

		Chunk chartecestyle = new Chunk("CARACTERÍSTICAS TÉCNICAS");
		chartecestyle.setFont(fuenteEnunciados);

		cellinfoe = new PdfPCell(new Phrase(chartecestyle));
		cellinfoe.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellinfoe.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellinfoe.setColspan(4);
		tableinfoe.addCell(cellinfoe);

		Chunk ramstyle = new Chunk("MEMORIA RAM:");
		ramstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(ramstyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk processstyle = new Chunk("PROCESADOR:");
		processstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(processstyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk hardstyle = new Chunk("DISCO DURO:");
		hardstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(hardstyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk operstyle = new Chunk("SISTEMA OPERATIVO:");
		operstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(operstyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk offistyle = new Chunk("OFFICE:");
		offistyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(offistyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk ipstyle = new Chunk("DIRECCIÓN IP:");
		ipstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(ipstyle));
		tableinfoe.addCell(cellinfoe);
		tableinfoe.addCell(cellinfoerta);

		Chunk tonnerstyle = new Chunk("REFERENCIA TONNER:");
		tonnerstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(tonnerstyle));

		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(" "));
		cellinfoerta.setColspan(3);
		tableinfoe.addCell(cellinfoerta);

		tableinfoe.setSpacingAfter(10);

		// table obs

		PdfPTable tableobs = new PdfPTable(8);

		PdfPCell obs = new PdfPCell(new Phrase(obsstyle));
		obs.setColspan(8);
		obs.setMinimumHeight(20);
		obs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		obs.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tableobs.addCell(obs);

		obs = new PdfPCell(new Phrase(" "));
		obs.setColspan(8);
		tableobs.addCell(obs);
		tableobs.addCell(obs);
		tableobs.addCell(obs);
		tableobs.addCell(obs);
		tableobs.addCell(obs);

		Chunk espnull = new Chunk("ND: NO DISPONIBLE NR: NO REGISTRA NE: NO ESPECIFICA NA: NO APLICA");
		espnull.setFont(rta);
		obs = new PdfPCell(new Phrase(espnull));
		obs.setColspan(8);
		obs.setBorder(Rectangle.NO_BORDER);
		tableobs.addCell(obs);

		document.add(tabla);
		document.add(tabladhos);
		document.add(tablaeqcom);

		document.add(tablert);
		document.add(tableinfoe);
		document.add(tableobs);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getHsysVPDF(List<String> mttos, List<SystemRepuestos> repuestos,
			SystemHoja_vida hoja_vida) throws DocumentException, IOException {

		// Creamos la instancia de memoria en la que se escribirá el archivo
		// temporalmente
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("HOJA DE VIDA DIGITAL EQUIPO COMPUTO Y DE COMUNICACIONES HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-06");
		Chunk vs = new Chunk("VERSION: 06");
		Chunk date = new Chunk("Fecha:29/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk idenstyle = new Chunk("IDENTIFICACIÓN");
		idenstyle.setFont(fuenteTituloHospital);

		Chunk depstyle = new Chunk("DEPARTAMENTO:");
		depstyle.setFont(fuenteEnunciados);

		Chunk deprtastyle = new Chunk(hoja_vida.getDepartamento());
		deprtastyle.setFont(rta);

		Chunk munstyle = new Chunk("MUNICIPIO:");
		munstyle.setFont(fuenteEnunciados);

		Chunk munrtastyle = new Chunk(hoja_vida.getMunicipio());
		munrtastyle.setFont(rta);

		Chunk addresstyle = new Chunk("DIRECCIÓN:");
		addresstyle.setFont(fuenteEnunciados);

		Chunk addresrtastyle = new Chunk(hoja_vida.getDireccion());
		addresrtastyle.setFont(rta);

		Chunk telestyle = new Chunk("TELÉFONO:");
		telestyle.setFont(fuenteEnunciados);

		Chunk telertastyle = new Chunk(hoja_vida.getTelefonoinstitucion());
		telertastyle.setFont(rta);

		Chunk emailstyle = new Chunk("E-MAIL:");
		emailstyle.setFont(fuenteEnunciados);

		Chunk emailrtastyle = new Chunk(hoja_vida.getEmailinstitucion());
		emailrtastyle.setFont(correo);

		Chunk codstyle = new Chunk("NIVEL:");
		codstyle.setFont(fuenteEnunciados);

		Chunk codrtastyle = new Chunk(hoja_vida.getNivelinstitucion());
		codrtastyle.setFont(rta);

		Chunk serviceestyle = new Chunk("SERVICIO:");
		serviceestyle.setFont(fuenteEnunciados);

		Chunk servicertastyle = new Chunk(hoja_vida.getSystemEquipo().getServicio().getNombre_servicio());
		servicertastyle.setFont(fuenteEnunciados);

		Chunk ubstyle = new Chunk("UBICACIÓN: ");
		ubstyle.setFont(fuenteEnunciados);

		Chunk ubrtastyle = new Chunk(hoja_vida.getSystemEquipo().getUbicacion());
		ubrtastyle.setFont(fuenteEnunciados);

		PdfPTable tabladhos = new PdfPTable(5);

		PdfPCell idencell = new PdfPCell(new Phrase(idenstyle));
		idencell.setColspan(6);
		idencell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

		String pathroute = "./src/main/resources/static" + hoja_vida.getFoto();

		Image photodevice = Image.getInstance(pathroute);
		photodevice.setAlignment(Image.ALIGN_CENTER);
		photodevice.scaleAbsolute(80, 70);

		PdfPCell fotocell = new PdfPCell(photodevice);

		fotocell.setRowspan(4);
		fotocell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		fotocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		fotocell.setMinimumHeight(80);

		tabladhos.addCell(idencell);

		tabladhos.addCell(new PdfPCell(new Phrase(depstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(deprtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(munrtastyle)));
		tabladhos.addCell(fotocell);
		tabladhos.addCell(new PdfPCell(new Phrase(addresstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(addresrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telestyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(telertastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(emailstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(emailrtastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(codrtastyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(serviceestyle)));

		tabladhos.addCell(new PdfPCell(new Phrase(servicertastyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubstyle)));
		tabladhos.addCell(new PdfPCell(new Phrase(ubrtastyle)));

		tabladhos.setSpacingAfter(10);

		Chunk dataeqstyle = new Chunk("DATOS DEL EQUIPO");
		dataeqstyle.setFont(fuenteTituloHospital);

		Chunk equipostyle = new Chunk("NOMBRE DEL EQUIPO: ");
		equipostyle.setFont(rta);

		Chunk equiportastyle = new Chunk(hoja_vida.getSystemEquipo().getNombre_equipo());
		equiportastyle.setFont(rta);

		Chunk marcastyle = new Chunk("MARCA: ");
		marcastyle.setFont(rta);

		Chunk marcartastyle = new Chunk(hoja_vida.getSystemEquipo().getMarca());
		marcartastyle.setFont(rta);

		Chunk modelostyle = new Chunk("MODELO:");
		modelostyle.setFont(rta);

		Chunk modelortastyle = new Chunk(hoja_vida.getSystemEquipo().getModelo());
		modelortastyle.setFont(rta);

		Chunk seriestyle = new Chunk("SERIE: ");
		seriestyle.setFont(rta);

		Chunk seriertastyle = new Chunk(hoja_vida.getSystemEquipo().getSerie());
		seriertastyle.setFont(rta);

		Chunk placastyle = new Chunk("INVENTARIO:");
		placastyle.setFont(rta);

		Chunk placartastyle = new Chunk(hoja_vida.getSystemEquipo().getPlaca_inventario());
		placartastyle.setFont(rta);

		Chunk anofstyle = new Chunk("TIPO DE EQUIPO:");
		anofstyle.setFont(rtasmall);

		Chunk anofrtastyle = new Chunk(hoja_vida.getSystemEquipo().getTipo_equipo().getNombre_Tipo_equipo());
		anofrtastyle.setFont(rta);

		Chunk fadqstyle = new Chunk("FORMA DE ADQUISICIÓN");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk xrtastyle = new Chunk("X");
		fadqstyle.setFont(fuenteTituloHospital);

		Chunk compradstyle = new Chunk("COMPRA DIRECTA:");
		compradstyle.setFont(rta);

		Chunk convstyle = new Chunk("CONVENIO:");
		convstyle.setFont(rta);

		Chunk donadostyle = new Chunk("DONADO:");
		donadostyle.setFont(rta);

		Chunk asignminstyle = new Chunk("ASIGNADO POR EL MINISTERIO:");
		asignminstyle.setFont(rta);

		Chunk asigngobstyle = new Chunk("ASIGNADO POR LA GOBERNACIÓN:");
		asigngobstyle.setFont(rtasmall);

		Chunk comodatostyle = new Chunk("COMODATO:");
		comodatostyle.setFont(rta);

		Chunk dcomprastyle = new Chunk("DATOS DE LA COMPRA");
		dcomprastyle.setFont(fuenteTituloHospital);

		Chunk fcomprastyle = new Chunk("FECHA DE COMPRA:");
		fcomprastyle.setFont(rta);

		Chunk fcomprartastyle = new Chunk(String.valueOf(hoja_vida.getFecha_compra()));
		fcomprartastyle.setFont(rta);

		Chunk finstallstyle = new Chunk("FECHA DE INSTALACIÓN:");
		finstallstyle.setFont(rta);

		Chunk finstallrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_instalacion()));
		finstallrtastyle.setFont(rta);

		Chunk fstartstyle = new Chunk("FECHA DE INICIO DE OPERACIÓN:");
		fstartstyle.setFont(rta);

		Chunk fstartrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_iniciooperacion()));
		fstartrtastyle.setFont(rta);

		Chunk fvctistyle = new Chunk("FECHA VENCIMIENTO GARANTÍA:");
		fvctistyle.setFont(rta);

		Chunk fvctirtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_vencimientogarantia()));
		fvctirtastyle.setFont(rta);

		Chunk fcostostyle = new Chunk("COSTO EN PESOS:");
		fcostostyle.setFont(rta);

		Chunk fcostortastyle = new Chunk(String.valueOf(hoja_vida.getCosto_compra()));
		fcostortastyle.setFont(rta);

		Chunk contractstyle = new Chunk("CONTRATO:");
		contractstyle.setFont(rta);

		Chunk contractrtastyle = new Chunk(hoja_vida.getContrato());
		contractrtastyle.setFont(rta);

		PdfPTable tablaeqcom = new PdfPTable(16);
		PdfPCell datoequipo = new PdfPCell(new Phrase(dataeqstyle));
		datoequipo.setColspan(5);
		PdfPCell datoadq = new PdfPCell(new Phrase(fadqstyle));
		datoadq.setColspan(5);
		PdfPCell datocompra = new PdfPCell(new Phrase(dcomprastyle));
		datocompra.setColspan(6);

		tablaeqcom.addCell(datoequipo);
		tablaeqcom.addCell(datoadq);
		tablaeqcom.addCell(datocompra);
		// first row
		datoequipo = new PdfPCell(new Phrase(equipostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(equiportastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(compradstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isCompraddirecta()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcomprastyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcomprartastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// second row
		datoequipo = new PdfPCell(new Phrase(marcastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(marcartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(convstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isConvenio()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(finstallstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(finstallrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// third row
		datoequipo = new PdfPCell(new Phrase(modelostyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(modelortastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(donadostyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isDonado()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fstartstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fstartrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fourth row
		datoequipo = new PdfPCell(new Phrase(seriestyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(seriertastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asignminstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isAsignadoporministerio()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fvctistyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fvctirtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// fifth row
		datoequipo = new PdfPCell(new Phrase(placastyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(placartastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(asigngobstyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isAsignadoporgobernacion()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(fcostostyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(fcostortastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		// sixth row
		datoequipo = new PdfPCell(new Phrase(anofstyle));
		datoequipo.setColspan(2);
		tablaeqcom.addCell(datoequipo);
		datoequipo = new PdfPCell(new Phrase(anofrtastyle));
		datoequipo.setColspan(3);
		tablaeqcom.addCell(datoequipo);

		datoadq = new PdfPCell(new Phrase(comodatostyle));
		datoadq.setColspan(4);
		tablaeqcom.addCell(datoadq);
		if (hoja_vida.isComodato()) {
			datoadq = new PdfPCell(new Phrase(xrtastyle));
			datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else {
			datoadq = new PdfPCell(new Phrase(""));
		}

		tablaeqcom.addCell(datoadq);

		datocompra = new PdfPCell(new Phrase(contractstyle));
		datocompra.setColspan(4);
		tablaeqcom.addCell(datocompra);
		datocompra = new PdfPCell(new Phrase(contractrtastyle));
		datocompra.setColspan(2);
		tablaeqcom.addCell(datocompra);

		tablaeqcom.setSpacingAfter(10);

		// table registro tecnico
		Chunk obsstyle = new Chunk("OBSERVACIONES:");
		obsstyle.setFont(fuenteEnunciados);

		PdfPTable tablert = new PdfPTable(4);
		Chunk rtstyle = new Chunk("REGISTRO TÉCNICO");
		rtstyle.setFont(fuenteTituloHospital);

		PdfPCell cellrt = new PdfPCell(new Phrase(rtstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellrt.setColspan(4);
		tablert.addCell(cellrt);

		Chunk accstyle = new Chunk("ACCESORIOS");
		accstyle.setFont(fuenteEnunciados);
		Chunk marcaaccstyle = new Chunk("MARCA:");
		marcaaccstyle.setFont(fuenteEnunciados);
		Chunk modeloccstyle = new Chunk("MODELO:");
		modeloccstyle.setFont(fuenteEnunciados);

		cellrt = new PdfPCell(new Phrase(accstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(marcaaccstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(modeloccstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);

		cellrt = new PdfPCell(new Phrase(obsstyle));
		cellrt.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellrt.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tablert.addCell(cellrt);
		Chunk rpto = new Chunk(" ");
		if (repuestos.size() > 1) {
			for (int i = 0; i < repuestos.size(); i++) {
				rpto = new Chunk(repuestos.get(i).getNombre_repuesto());
				rpto.setFont(rta);
				cellrt = new PdfPCell(new Phrase(rpto));
				tablert.addCell(cellrt);

				rpto = new Chunk(repuestos.get(i).getMarca());
				rpto.setFont(rta);
				cellrt = new PdfPCell(new Phrase(rpto));
				tablert.addCell(cellrt);

				rpto = new Chunk(repuestos.get(i).getModelo());
				rpto.setFont(rta);
				cellrt = new PdfPCell(new Phrase(rpto));
				tablert.addCell(cellrt);

				rpto = new Chunk(repuestos.get(i).getObservaciones());
				rpto.setFont(rta);
				cellrt = new PdfPCell(new Phrase(rpto));
				tablert.addCell(cellrt);

			}
		} else {
			cellrt = new PdfPCell(new Phrase(rpto));
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
			tablert.addCell(cellrt);
		}

		tablert.setSpacingAfter(10);
		// table info
		PdfPTable tableinfoe = new PdfPTable(4);
		Chunk infoestyle = new Chunk("INFORMACIÓN DEL EQUIPO");
		infoestyle.setFont(fuenteTituloHospital);
		PdfPCell cellinfoerta = new PdfPCell(new Phrase(" "));

		PdfPCell cellinfoe = new PdfPCell(new Phrase(infoestyle));
		cellinfoe.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellinfoe.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellinfoe.setColspan(4);
		tableinfoe.addCell(cellinfoe);

		Chunk chartecestyle = new Chunk("CARACTERÍSTICAS TÉCNICAS");
		chartecestyle.setFont(fuenteEnunciados);

		cellinfoe = new PdfPCell(new Phrase(chartecestyle));
		cellinfoe.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		cellinfoe.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cellinfoe.setColspan(4);
		tableinfoe.addCell(cellinfoe);

		Chunk ramstyle = new Chunk("MEMORIA RAM:");

		ramstyle.setFont(rta);
		Chunk ramrtastyle = new Chunk(hoja_vida.getRam());
		ramrtastyle.setFont(rta);

		cellinfoe = new PdfPCell(new Phrase(ramstyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(ramrtastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk processstyle = new Chunk("PROCESADOR:");
		processstyle.setFont(rta);
		Chunk processrtastyle = new Chunk(hoja_vida.getProcesador());
		processrtastyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(processstyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(processrtastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk hardstyle = new Chunk("DISCO DURO:");
		hardstyle.setFont(rta);
		Chunk hardrtastyle = new Chunk(hoja_vida.getDisco_duro());
		hardrtastyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(hardstyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(hardrtastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk operstyle = new Chunk("SISTEMA OPERATIVO:");
		operstyle.setFont(rta);
		Chunk opertastyle = new Chunk(hoja_vida.getSistema_operativo());
		opertastyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(operstyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(opertastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk offistyle = new Chunk("OFFICE:");
		offistyle.setFont(rta);
		Chunk offirtastyle = new Chunk(hoja_vida.getOffice());
		offirtastyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(offistyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(offirtastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk ipstyle = new Chunk("DIRECCIÓN IP:");
		ipstyle.setFont(rta);
		Chunk iprtastyle = new Chunk(hoja_vida.getIp());
		iprtastyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(ipstyle));
		tableinfoe.addCell(cellinfoe);
		cellinfoerta = new PdfPCell(new Phrase(iprtastyle));
		tableinfoe.addCell(cellinfoerta);

		Chunk tonnerstyle = new Chunk("REFERENCIA TONNER:");
		tonnerstyle.setFont(rta);
		cellinfoe = new PdfPCell(new Phrase(tonnerstyle));

		tableinfoe.addCell(cellinfoe);
		Chunk tonnertastyle = new Chunk(hoja_vida.getTonner());
		tonnertastyle.setFont(rta);
		cellinfoerta = new PdfPCell(new Phrase(tonnertastyle));
		cellinfoerta.setColspan(3);
		tableinfoe.addCell(cellinfoerta);

		tableinfoe.setSpacingAfter(10);

		// table obs

		PdfPTable tableobs = new PdfPTable(8);

		PdfPCell obs = new PdfPCell(new Phrase(obsstyle));
		obs.setColspan(8);
		obs.setMinimumHeight(20);
		obs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		obs.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		tableobs.addCell(obs);
		Chunk obsph = new Chunk(" ");
		if (mttos.size() > 0) {
			for (int j = 0; j < mttos.size(); j++) {
				obsph = new Chunk(mttos.get(j));
				obsph.setFont(rta);

				obs = new PdfPCell(new Phrase(obsph));
				obs.setColspan(8);
				tableobs.addCell(obs);

			}
		} else {
			obs = new PdfPCell(new Phrase(" "));
			obs.setColspan(8);
			tableobs.addCell(obs);
			tableobs.addCell(obs);
			tableobs.addCell(obs);
			tableobs.addCell(obs);
		}

		Chunk espnull = new Chunk("ND: NO DISPONIBLE NR: NO REGISTRA NE: NO ESPECIFICA NA: NO APLICA");
		espnull.setFont(rta);
		obs = new PdfPCell(new Phrase(espnull));
		obs.setColspan(8);
		obs.setBorder(Rectangle.NO_BORDER);
		tableobs.addCell(obs);

		document.add(tabla);
		document.add(tabladhos);
		document.add(tablaeqcom);

		document.add(tablert);
		document.add(tableinfoe);
		document.add(tableobs);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getoriginalSwitchsysMPPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE SWITCH HRCATCH");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-    ");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:20/10/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		Chunk marca = new Chunk("MARCA");
		marca.setFont(rta);
		Chunk modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		Chunk serie = new Chunk("SERIE");
		serie.setFont(rta);
		Chunk inv = new Chunk("INVENTARIO");
		inv.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(6);

		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(" "));
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		Chunk actividades = new Chunk("ACTIVIDADES A REALIZAR PARA MANTENIMIENTO PREVENTIVO DE SWITCH");
		actividades.setFont(fuenteEnunciados);

		Chunk first = new Chunk("Limpieza interna general de todo el equipo.");
		first.setFont(rta);
		Chunk second = new Chunk("Limpieza externa general de todo el equipo.");
		second.setFont(rta);
		Chunk third = new Chunk("OS actualizado.");
		third.setFont(rta);
		Chunk fourth = new Chunk("Realizar Backups.");
		fourth.setFont(rta);
		Chunk fifthy = new Chunk("Puertos dañados.");
		fifthy.setFont(rta);
		Chunk six = new Chunk("Acceso remoto verificación por https, telnet y ssh.");
		six.setFont(rta);
		Chunk seven = new Chunk("Vlan de datos y voz.");
		seven.setFont(rta);
		Chunk eight = new Chunk("Creación y validación de usuarios.");
		eight.setFont(rta);
		PdfPTable tabla3 = new PdfPTable(12);

		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		Chunk number = new Chunk("1");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(first));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 2
		number = new Chunk("2");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(second));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 3
		number = new Chunk("3");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(third));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 4
		number = new Chunk("4");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fourth));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 5
		number = new Chunk("5");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(fifthy));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 6
		number = new Chunk("6");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(six));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 7
		number = new Chunk("7");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(seven));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 8
		number = new Chunk("8");
		number.setFont(rta);
		cellact = new PdfPCell(new Phrase(number));
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(eight));
		cellact.setColspan(10);
		tabla3.addCell(cellact);
		cellact = new PdfPCell(new Phrase(" "));
		tabla3.addCell(cellact);
		// 9
		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		cellobs = new PdfPCell(new Phrase(" "));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("INGENIERO/TECNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk("");
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("INGENIERO/TÉCNICO HUSRT: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk("");
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getSwitchsysMPPDF(SystemMantenimiento mtto, List<Protocolo_preventivo> protos)
			throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);
		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);
		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(8);
		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);
		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);
		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);
		Chunk titulo1 = new Chunk();
		Chunk titulo2 = new Chunk();
		Chunk titulo3 = new Chunk();
		Chunk code = new Chunk();
		Chunk vs = new Chunk();
		Chunk date = new Chunk();
		PdfPTable tabla = new PdfPTable(5);
		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		PdfPCell celda2 = new PdfPCell(logo);
		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		Chunk dequipo = new Chunk("DATOS DEL EQUIPO");
		Chunk proceso = new Chunk("PROCESO AL QUE PERTENECE");
		Chunk procesorta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		Chunk fecha = new Chunk("FECHA DE EJECUCIÓN");
		Chunk fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		Chunk marca = new Chunk("MARCA");
		Chunk marcarta = new Chunk(mtto.getEquipo().getMarca());
		Chunk modelo = new Chunk("MODELO");
		Chunk modelorta = new Chunk(mtto.getEquipo().getModelo());
		Chunk serie = new Chunk("SERIE");
		Chunk serierta = new Chunk(mtto.getEquipo().getSerie());
		Chunk inv = new Chunk("INVENTARIO");
		Chunk invrta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		PdfPTable tabla2 = new PdfPTable(6);
		PdfPCell cell = new PdfPCell(new Phrase(dequipo));
		PdfPTable tablatipo = new PdfPTable(12);
		Chunk tipoequipo = new Chunk("TIPO DE EQUIPO");
		PdfPCell celltipo = new PdfPCell(new Phrase(tipoequipo));
		Chunk xselect = new Chunk("X");
		tipoequipo = new Chunk("PC:");
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		tablatipo.addCell(celltipo);
		tipoequipo = new Chunk("IMPRESORA:");
		celltipo = new PdfPCell(new Phrase(tipoequipo));
		Chunk actividades = new Chunk("LISTA DE ACTIVIDADES A REALIZAR");
		Chunk first = new Chunk("Desconectar cables y periféricos de los equipos.");
		PdfPTable tabla3 = new PdfPTable(12);
		PdfPCell cellact = new PdfPCell(new Phrase(actividades));
		Chunk number = new Chunk("1");
		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		Chunk obsrta = new Chunk(mtto.getObservacionesh());
		cellobs = new PdfPCell(new Phrase(obsrta));
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("TÉCNICO: ");
		Chunk realizadortastyle = new Chunk(mtto.getAutor_realizado());
		Chunk recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		Chunk recibidortastyle = new Chunk(mtto.getAutor_recibido());
		Chunk cedula = new Chunk("CEDULA: ");
		Phrase realize = new Phrase();
		Phrase recibe = new Phrase();
		PdfPCell realizado = new PdfPCell(realize);
		PdfPCell recibido = new PdfPCell(recibe);
		Chunk firma = new Chunk("______________________ FIRMA");
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));
		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));
		PdfPCell firmreci = new PdfPCell(new Phrase(firma));

		titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		titulo2 = new Chunk("MANTENIMIENTO PREVENTIVO DE SWITCH HRCATCH");
		titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		code = new Chunk("CÓDIGO S-F-    ");
		vs = new Chunk("VERSION: 01");
		date = new Chunk("Fecha:20/10/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		tabla = new PdfPTable(5);

		celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		dequipo = new Chunk("DATOS DEL EQUIPO");
		dequipo.setFont(fuenteEnunciados);
		proceso = new Chunk("PROCESO AL QUE PERTENECE");
		proceso.setFont(rta);
		procesorta = new Chunk(mtto.getEquipo().getServicio().getNombre_servicio());
		procesorta.setFont(rta);

		fecha = new Chunk("FECHA DE EJECUCIÓN");
		fecha.setFont(rta);
		fecharta = new Chunk(String.valueOf(mtto.getFecha()));
		fecharta.setFont(rta);

		marca = new Chunk("MARCA");
		marca.setFont(rta);
		marcarta = new Chunk(mtto.getEquipo().getMarca());
		marcarta.setFont(rta);

		modelo = new Chunk("MODELO");
		modelo.setFont(rta);
		modelorta = new Chunk(mtto.getEquipo().getModelo());
		modelorta.setFont(rta);

		serie = new Chunk("SERIE");
		serie.setFont(rta);
		serierta = new Chunk(mtto.getEquipo().getSerie());
		serierta.setFont(rta);

		inv = new Chunk("INVENTARIO");
		inv.setFont(rta);
		invrta = new Chunk(mtto.getEquipo().getPlaca_inventario());
		invrta.setFont(rta);

		tabla2 = new PdfPTable(6);

		cell = new PdfPCell(new Phrase(dequipo));
		cell.setColspan(6);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(proceso));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(fecha));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(marca));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(modelo));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(serie));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(inv));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(procesorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(fecharta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(marcarta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(modelorta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(serierta));
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase(invrta));
		tabla2.addCell(cell);

		tabla2.setSpacingAfter(10);

		actividades = new Chunk("ACTIVIDADES A REALIZAR PARA MANTENIMIENTO PREVENTIVO DE SWITCH");
		actividades.setFont(fuenteEnunciados);

		Chunk second = new Chunk("Instalación de sistema operativo.");
		second.setFont(rta);

		tabla3 = new PdfPTable(12);

		cellact = new PdfPCell(new Phrase(actividades));
		cellact.setColspan(12);
		cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla3.addCell(cellact);

		// 1
		number = new Chunk("1");
		ArrayList<String> strnums = new ArrayList<String>(Arrays.asList(mtto.getRutinas().split(",")));

		for (int p = 0; p < protos.size(); p++) {
			number = new Chunk(String.valueOf(p));
			number.setFont(rta);
			cellact = new PdfPCell(new Phrase(number));
			cellact.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			tabla3.addCell(cellact);
			first = new Chunk(protos.get(p).getPaso());
			first.setFont(rta);
			cellact = new PdfPCell(new Phrase(first));
			cellact.setColspan(10);
			tabla3.addCell(cellact);
			if (strnums.contains(String.valueOf(p))) {
				cellact = new PdfPCell(new Phrase(xselect));
			} else {
				cellact = new PdfPCell(new Phrase(" "));
			}
			tabla3.addCell(cellact);
		}

		tabla3.setSpacingAfter(10);

		tabla4 = new PdfPTable(1);
		observaciones = new Chunk("OBSERVACIONES Y HALLAZGOS");
		observaciones.setFont(fuenteEnunciados);
		cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		obsrta = new Chunk(mtto.getObservacioness());
		obsrta.setFont(rta);
		cellobs = new PdfPCell(new Phrase(obsrta));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		tabla5 = new PdfPTable(4);
		realizadostyle = new Chunk("TÉCNICO: ");
		realizadostyle.setFont(fuenteTituloHospital);

		realizadortastyle = new Chunk(mtto.getAutor_realizado());
		realizadortastyle.setFont(rta);

		recibidostyle = new Chunk("NOMBRE DEL USUARIO: ");
		recibidostyle.setFont(fuenteTituloHospital);

		recibidortastyle = new Chunk(mtto.getAutor_recibido());
		recibidortastyle.setFont(rta);

		cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		firma = new Chunk("______________________ FIRMA");
		firma.setFont(rta);
		nombrerea = new PdfPCell(new Phrase(cedula));

		firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		nombrereci = new PdfPCell(new Phrase(cedula));

		firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);

		document.add(tabla3);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getOriginalBackpusPDF() throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("FORMATO DE INVENTARIO BACKUPS INFRAESTRUCTURA TECNOLOGICA");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-    ");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:20/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);
		tabla.setSpacingBefore(20);
		tabla.setSpacingAfter(10);

		Chunk dRecurso = new Chunk("RECURSO A REALIZAR BACKUP");
		dRecurso.setFont(fuenteEnunciados);
		Chunk servidorV = new Chunk("SERVIDOR VIRTUAL");
		servidorV.setFont(rta);
		Chunk servidorF = new Chunk("SERVIDOR FISICO");
		servidorF.setFont(rta);
		Chunk basededatos = new Chunk("BASE DE DATOS");
		basededatos.setFont(rta);
		Chunk computador = new Chunk("PC");
		computador.setFont(rta);
		Chunk Eswitch = new Chunk("SWITCH");
		Eswitch.setFont(rta);
		Chunk trd = new Chunk("TRD");
		trd.setFont(rta);
		Chunk correoE = new Chunk("CORREO");
		correoE.setFont(rta);
		Chunk otroE = new Chunk("OTRO");
		otroE.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(8);

		PdfPCell cell = new PdfPCell(new Phrase(dRecurso));
		cell.setColspan(8);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(servidorV));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(servidorF));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(basededatos));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(computador));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(Eswitch));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(trd));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(correoE));
		tabla2.addCell(cell);
		
		cell = new PdfPCell(new Phrase(otroE));
		tabla2.addCell(cell);

		PdfPCell cellSV = new PdfPCell(new Phrase(" "));
		PdfPCell cellSF = new PdfPCell(new Phrase(" "));
		PdfPCell cellDB = new PdfPCell(new Phrase(" "));
		PdfPCell cellPC = new PdfPCell(new Phrase(" "));
		PdfPCell cellSW = new PdfPCell(new Phrase(" "));
		PdfPCell cellTRD = new PdfPCell(new Phrase(" "));
		PdfPCell cellEM = new PdfPCell(new Phrase(" "));
		PdfPCell cellOT = new PdfPCell(new Phrase(" "));

		tabla2.addCell(cellSV);
		tabla2.addCell(cellSF);
		tabla2.addCell(cellDB);
		tabla2.addCell(cellPC);
		tabla2.addCell(cellSW);
		tabla2.addCell(cellTRD);
		tabla2.addCell(cellEM);
		tabla2.addCell(cellOT);

		tabla2.setSpacingAfter(10);

		Chunk reporte = new Chunk("REPORTE No.");
		reporte.setFont(fuenteTituloHospital);

		Chunk reporteR = new Chunk();
		reporteR.setFont(fontred);

		Chunk fecha = new Chunk("FECHA: ");
		fecha.setFont(fuenteTituloHospital);

		Chunk fechaR = new Chunk();
		fechaR.setFont(rta);

		Chunk RecursoN = new Chunk("NOMBRE DEL RECURSO: ");
		RecursoN.setFont(fuenteTituloHospital);

		Chunk RecursoNR = new Chunk();
		RecursoNR.setFont(rta);

		Chunk Periodicidad = new Chunk("PERIODICIDAD: ");
		Periodicidad.setFont(fuenteTituloHospital);

		Chunk PeriodicidadR = new Chunk();
		PeriodicidadR.setFont(rta);

		Chunk medio = new Chunk("MEDIO:");
		medio.setFont(fuenteTituloHospital);

		Chunk medioR = new Chunk();
		medioR.setFont(rta);

		Chunk destino = new Chunk("DESTINO: ");
		destino.setFont(fuenteTituloHospital);

		Chunk destinoR = new Chunk();
		destinoR.setFont(rta);
		
		
		Chunk tamano = new Chunk("TAMAÑO (MB): ");
		tamano.setFont(fuenteTituloHospital);

		Chunk tamanoR = new Chunk();
		tamanoR.setFont(rta);

		Chunk casoMS = new Chunk("CASO MESA DE SERVICIOS: ");
		casoMS.setFont(fuenteTituloHospital);

		Chunk casoMSR = new Chunk();
		casoMSR.setFont(rta);

		PdfPTable tablaData = new PdfPTable(8);

		PdfPCell cellReporte = new PdfPCell(new Phrase(reporte));
		cellReporte.setColspan(3);
		PdfPCell cellReporteR = new PdfPCell(new Phrase(reporteR));
		cellReporteR.setColspan(5);

		PdfPCell cellFecha = new PdfPCell(new Phrase(fecha));
		cellFecha.setColspan(3);
		PdfPCell cellFechaR = new PdfPCell(new Phrase(fechaR));
		cellFechaR.setColspan(5);

		PdfPCell cellRecursoN = new PdfPCell(new Phrase(RecursoN));
		cellRecursoN.setColspan(3);
		PdfPCell cellRecursoNR = new PdfPCell(new Phrase(RecursoNR));
		cellRecursoNR.setColspan(5);

		PdfPCell cellPeriodicidad = new PdfPCell(new Phrase(Periodicidad));
		cellPeriodicidad.setColspan(3);
		PdfPCell cellPeriodicidadR = new PdfPCell(new Phrase(PeriodicidadR));
		cellPeriodicidadR.setColspan(5);

		PdfPCell cellMedio = new PdfPCell(new Phrase(medio));
		cellMedio.setColspan(3);
		PdfPCell cellMedioR = new PdfPCell(new Phrase(medioR));
		cellMedioR.setColspan(5);

		PdfPCell cellDestino = new PdfPCell(new Phrase(destino));
		cellDestino.setColspan(3);
		PdfPCell cellDestinoR = new PdfPCell(new Phrase(destinoR));
		cellDestinoR.setColspan(5);
		
		PdfPCell cellTamano = new PdfPCell(new Phrase(tamano));
		cellTamano.setColspan(3);
		PdfPCell cellTamanoR = new PdfPCell(new Phrase(tamanoR));
		cellTamanoR.setColspan(5);

		PdfPCell cellCasoMS = new PdfPCell(new Phrase(casoMS));
		cellCasoMS.setColspan(3);
		PdfPCell cellCasoMSR = new PdfPCell(new Phrase(casoMSR));
		cellCasoMSR.setColspan(5);

		tablaData.addCell(cellReporte);
		tablaData.addCell(cellReporteR);
		tablaData.addCell(cellFecha);
		tablaData.addCell(cellFechaR);
		tablaData.addCell(cellRecursoN);
		tablaData.addCell(cellRecursoNR);
		tablaData.addCell(cellPeriodicidad);
		tablaData.addCell(cellPeriodicidadR);
		tablaData.addCell(cellMedio);
		tablaData.addCell(cellMedioR);
		tablaData.addCell(cellDestino);
		tablaData.addCell(cellDestinoR);
		tablaData.addCell(cellTamano);
		tablaData.addCell(cellTamanoR);
		tablaData.addCell(cellCasoMS);
		tablaData.addCell(cellCasoMSR);

		tablaData.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		cellobs = new PdfPCell(new Phrase(" "));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("PERSONA QUE SOLICITA: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk("");
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("PERSONA QUE REALIZA: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk("");
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("___________________________");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tablaData);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;

	}

	public ByteArrayOutputStream getBackpusPDFSpec(Backup backup) throws DocumentException, IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Font rta = new Font();
		rta.setStyle(Font.NORMAL);
		rta.setSize(10);

		Document document = new Document(PageSize.LETTER);
		document.setMargins(5, 5, 20, 10);

		PdfWriter writer = PdfWriter.getInstance(document, bos);

		document.open();

		Image logo = Image.getInstance(image);
		logo.setAlignment(Image.ALIGN_CENTER);

		logo.scaleAbsolute(65, 35);
		// contentByte.beginText();

		Font fuenteTitulo = new Font();
		fuenteTitulo.setSize(11);
		fuenteTitulo.setStyle(Font.BOLD);

		Font fuenteTituloHospital = new Font();
		fuenteTituloHospital.setSize(10);
		fuenteTituloHospital.setStyle(Font.BOLD);

		Font fuenteEnunciados = new Font();
		fuenteEnunciados.setSize(9);
		fuenteEnunciados.setStyle(Font.BOLD);

		Font negrita = new Font();
		negrita.setStyle(Font.BOLD);
		negrita.setSize(7);

		Font writers = new Font();
		writers.setStyle(Font.BOLD);
		writers.setSize(8);

		Font fontred = new Font();
		fontred.setSize(10);
		fontred.setStyle(Font.BOLD);
		fontred.setColor(100, 0, 0);

		Font rtasmall = new Font();
		rtasmall.setStyle(Font.NORMAL);
		rtasmall.setSize(7);

		Font rtaultrasmall = new Font();
		rtaultrasmall.setStyle(Font.NORMAL);
		rtaultrasmall.setSize(6);

		Font correo = new Font();
		correo.setStyle(Font.NORMAL);
		correo.setSize(7);

		Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
		Chunk titulo2 = new Chunk("FORMATO DE INVENTARIO BACKUPS INFRAESTRUCTURA TECNOLOGICA");
		Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
		Chunk code = new Chunk("CÓDIGO S-F-    ");
		Chunk vs = new Chunk("VERSION: 01");
		Chunk date = new Chunk("Fecha:20/11/2023");
		// titulo.setUnderline(2f, -2f);

		titulo1.setFont(fuenteTituloHospital);
		titulo2.setFont(fuenteTitulo);
		titulo3.setFont(fuenteTituloHospital);
		code.setFont(negrita);
		vs.setFont(negrita);
		date.setFont(negrita);

		PdfPTable tabla = new PdfPTable(5);

		PdfPCell celda0 = new PdfPCell(new Phrase(code));
		celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda0.setMinimumHeight(40);
		celda0.setRowspan(2);

		PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
		celda1.setColspan(3);
		celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda2 = new PdfPCell(logo);
		celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		celda2.setRowspan(2);

		PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
		celda4.setColspan(3);
		celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda3 = new PdfPCell(new Phrase(vs));
		celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
		celda6.setColspan(3);
		celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		PdfPCell celda5 = new PdfPCell(new Phrase(date));
		celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

		tabla.addCell(celda0);
		tabla.addCell(celda1);
		tabla.addCell(celda2);
		tabla.addCell(celda4);
		tabla.addCell(celda3);
		tabla.addCell(celda6);
		tabla.addCell(celda5);

		tabla.setSpacingAfter(10);

		Chunk dRecurso = new Chunk("RECURSO A REALIZAR BACKUP");
		dRecurso.setFont(fuenteEnunciados);
		Chunk servidorV = new Chunk("SERVIDOR VIRTUAL");
		servidorV.setFont(rta);
		Chunk servidorF = new Chunk("SERVIDOR FISICO");
		servidorF.setFont(rta);
		Chunk basededatos = new Chunk("BASE DE DATOS");
		basededatos.setFont(rta);
		Chunk computador = new Chunk("PC");
		computador.setFont(rta);
		Chunk Eswitch = new Chunk("SWITCH");
		Eswitch.setFont(rta);
		Chunk trd = new Chunk("TRD");
		trd.setFont(rta);
		Chunk correoE = new Chunk("CORREO");
		correoE.setFont(rta);
		Chunk otro = new Chunk("OTRO");
		otro.setFont(rta);

		PdfPTable tabla2 = new PdfPTable(8);

		PdfPCell cell = new PdfPCell(new Phrase(dRecurso));
		cell.setColspan(8);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(servidorV));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(servidorF));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(basededatos));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(computador));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(Eswitch));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(trd));
		tabla2.addCell(cell);

		cell = new PdfPCell(new Phrase(correoE));
		tabla2.addCell(cell);
		
		cell = new PdfPCell(new Phrase(otro));
		tabla2.addCell(cell);


		PdfPCell cellSV = new PdfPCell(new Phrase(" "));
		PdfPCell cellSF = new PdfPCell(new Phrase(" "));
		PdfPCell cellDB = new PdfPCell(new Phrase(" "));
		PdfPCell cellPC = new PdfPCell(new Phrase(" "));
		PdfPCell cellSW = new PdfPCell(new Phrase(" "));
		PdfPCell cellTRD = new PdfPCell(new Phrase(" "));
		PdfPCell cellEM = new PdfPCell(new Phrase(" "));
		PdfPCell cellOT = new PdfPCell(new Phrase(" "));

		if (backup.getTipo_recurso().equals("ServidorVirtual")) {
			cellSV = new PdfPCell(new Phrase("X"));
			cellSV.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("ServidorFisico")) {
			cellSF = new PdfPCell(new Phrase("X"));
			cellSF.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("BaseDeDatos")) {
			cellDB = new PdfPCell(new Phrase("X"));
			cellDB.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("Computador")) {
			cellPC = new PdfPCell(new Phrase("X"));
			cellPC.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("Correo")) {
			cellEM = new PdfPCell(new Phrase("X"));
			cellEM.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("Switch")) {
			cellSW = new PdfPCell(new Phrase("X"));
			cellSW.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("TRD")) {
			cellTRD = new PdfPCell(new Phrase("X"));
			cellTRD.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		} else if (backup.getTipo_recurso().equals("Otro")) {
			cellOT = new PdfPCell(new Phrase("X"));
			cellOT.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		}

		tabla2.addCell(cellSV);
		tabla2.addCell(cellSF);
		tabla2.addCell(cellDB);
		tabla2.addCell(cellPC);
		tabla2.addCell(cellSW);
		tabla2.addCell(cellTRD);
		tabla2.addCell(cellEM);
		tabla2.addCell(cellOT);

		tabla2.setSpacingAfter(10);

		Chunk reporte = new Chunk("REPORTE No.");
		reporte.setFont(fuenteTituloHospital);

		Chunk reporteR = new Chunk(backup.getId_Backup() + "");
		reporteR.setFont(fontred);

		Chunk fecha = new Chunk("FECHA: ");
		fecha.setFont(fuenteTituloHospital);

		Chunk fechaR = new Chunk(backup.getFecha_backup() + "");
		fechaR.setFont(rta);

		Chunk RecursoN = new Chunk("NOMBRE DEL RECURSO: ");
		RecursoN.setFont(fuenteTituloHospital);

		Chunk RecursoNR = new Chunk(backup.getNombre_recurso());
		RecursoNR.setFont(rta);

		Chunk Periodicidad = new Chunk("PERIODICIDAD: ");
		Periodicidad.setFont(fuenteTituloHospital);

		Chunk PeriodicidadR = new Chunk(backup.getPeriodicidad());
		PeriodicidadR.setFont(rta);

		Chunk medio = new Chunk("MEDIO:");
		medio.setFont(fuenteTituloHospital);

		Chunk medioR = new Chunk(backup.getMedio());
		medioR.setFont(rta);

		Chunk destino = new Chunk("DESTINO: ");
		destino.setFont(fuenteTituloHospital);

		Chunk destinoR = new Chunk(backup.getDestino());
		destinoR.setFont(rta);
		
		Chunk tamano = new Chunk("TAMAÑO (MB): ");
		tamano.setFont(fuenteTituloHospital);

		Chunk tamanoR = new Chunk(backup.getTamano() + " Mb");
		tamanoR.setFont(rta);

		Chunk casoMS = new Chunk("CASO MESA DE SERVICIOS: ");
		casoMS.setFont(fuenteTituloHospital);

		Chunk casoMSR = new Chunk(backup.getCaso_glpi());
		casoMSR.setFont(rta);

		PdfPTable tablaData = new PdfPTable(8);

		PdfPCell cellReporte = new PdfPCell(new Phrase(reporte));
		cellReporte.setColspan(3);
		PdfPCell cellReporteR = new PdfPCell(new Phrase(reporteR));
		cellReporteR.setColspan(5);

		PdfPCell cellFecha = new PdfPCell(new Phrase(fecha));
		cellFecha.setColspan(3);
		PdfPCell cellFechaR = new PdfPCell(new Phrase(fechaR));
		cellFechaR.setColspan(5);

		PdfPCell cellRecursoN = new PdfPCell(new Phrase(RecursoN));
		cellRecursoN.setColspan(3);
		PdfPCell cellRecursoNR = new PdfPCell(new Phrase(RecursoNR));
		cellRecursoNR.setColspan(5);

		PdfPCell cellPeriodicidad = new PdfPCell(new Phrase(Periodicidad));
		cellPeriodicidad.setColspan(3);
		PdfPCell cellPeriodicidadR = new PdfPCell(new Phrase(PeriodicidadR));
		cellPeriodicidadR.setColspan(5);

		PdfPCell cellMedio = new PdfPCell(new Phrase(medio));
		cellMedio.setColspan(3);
		PdfPCell cellMedioR = new PdfPCell(new Phrase(medioR));
		cellMedioR.setColspan(5);

		PdfPCell cellDestino = new PdfPCell(new Phrase(destino));
		cellDestino.setColspan(3);
		PdfPCell cellDestinoR = new PdfPCell(new Phrase(destinoR));
		cellDestinoR.setColspan(5);
		
		PdfPCell cellTamano = new PdfPCell(new Phrase(tamano));
		cellTamano.setColspan(3);
		PdfPCell cellTamanoR = new PdfPCell(new Phrase(tamanoR));
		cellTamanoR.setColspan(5);

		PdfPCell cellCasoMS = new PdfPCell(new Phrase(casoMS));
		cellCasoMS.setColspan(3);
		PdfPCell cellCasoMSR = new PdfPCell(new Phrase(casoMSR));
		cellCasoMSR.setColspan(5);

		tablaData.addCell(cellReporte);
		tablaData.addCell(cellReporteR);
		tablaData.addCell(cellFecha);
		tablaData.addCell(cellFechaR);
		tablaData.addCell(cellRecursoN);
		tablaData.addCell(cellRecursoNR);
		tablaData.addCell(cellPeriodicidad);
		tablaData.addCell(cellPeriodicidadR);
		tablaData.addCell(cellMedio);
		tablaData.addCell(cellMedioR);
		tablaData.addCell(cellDestino);
		tablaData.addCell(cellDestinoR);
		tablaData.addCell(cellTamano);
		tablaData.addCell(cellTamanoR);
		tablaData.addCell(cellCasoMS);
		tablaData.addCell(cellCasoMSR);

		tablaData.setSpacingAfter(10);

		PdfPTable tabla4 = new PdfPTable(1);
		Chunk observaciones = new Chunk("OBSERVACIONES");
		observaciones.setFont(fuenteEnunciados);
		PdfPCell cellobs = new PdfPCell(new Phrase(observaciones));
		cellobs.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		tabla4.addCell(cellobs);
		cellobs = new PdfPCell(new Phrase(backup.getObservaciones()));
		cellobs.setMinimumHeight(80);
		tabla4.addCell(cellobs);
		tabla4.setSpacingAfter(10);
		// h
		PdfPTable tabla5 = new PdfPTable(4);
		Chunk realizadostyle = new Chunk("PERSONA QUE SOLICITA: ");
		realizadostyle.setFont(fuenteTituloHospital);

		Chunk realizadortastyle = new Chunk(backup.getAutor_solicita());
		realizadortastyle.setFont(rta);

		Chunk recibidostyle = new Chunk("PERSONA QUE REALIZA: ");
		recibidostyle.setFont(fuenteTituloHospital);

		Chunk recibidortastyle = new Chunk(backup.getUsuario().getNombre() + " " + backup.getUsuario().getApellido());
		recibidortastyle.setFont(rta);

		Chunk cedula = new Chunk("CEDULA: ");
		cedula.setFont(fuenteTituloHospital);

		recibidostyle.setFont(fuenteTituloHospital);

		Phrase realize = new Phrase();
		realize.add(realizadostyle);
		realize.add(realizadortastyle);

		Phrase recibe = new Phrase();
		recibe.add(recibidostyle);
		recibe.add(recibidortastyle);

		PdfPCell realizado = new PdfPCell(realize);
		realizado.setColspan(2);
		realizado.setMinimumHeight(20);

		PdfPCell recibido = new PdfPCell(recibe);
		recibido.setColspan(2);
		recibido.setMinimumHeight(20);

		Chunk firma = new Chunk("___________________________");
		firma.setFont(rta);
		PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

		PdfPCell firmrea = new PdfPCell(new Phrase(firma));
		firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmrea.setMinimumHeight(50);

		PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

		PdfPCell firmreci = new PdfPCell(new Phrase(firma));
		firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
		firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		firmreci.setMinimumHeight(50);
		tabla5.addCell(realizado);
		tabla5.addCell(recibido);
		tabla5.addCell(nombrerea);
		tabla5.addCell(firmrea);
		tabla5.addCell(nombrereci);
		tabla5.addCell(firmreci);

		document.add(tabla);
		document.add(tabla2);
		document.add(tablaData);
		document.add(tabla4);
		document.add(tabla5);
		document.close();
		// Retornamos la variable al finalizar
		return bos;
	}

}
