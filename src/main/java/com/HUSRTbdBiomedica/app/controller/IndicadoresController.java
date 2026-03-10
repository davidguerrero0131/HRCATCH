package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IReporte_bajaService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;

@Controller
@RequestMapping
public class IndicadoresController {
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IReporte_bajaService Reporte_bajaService;
	
	@GetMapping("/indicadores")
	public String mostrarmesanoind(Model model,
			RedirectAttributes flash) {
		
		LocalDate Current_Date = LocalDate.now();
		
		Current_Date = Current_Date.minusDays(Current_Date.getDayOfMonth()-1);

		LocalDate fecha_1 = Current_Date.minusMonths(1);
		LocalDate fecha_3 = Current_Date.plusMonths(1);
		int Mesfinal = Current_Date.getMonthValue(); 
		Date fecha1 = Date.valueOf(fecha_1);
		Date fecha2 = Date.valueOf(Current_Date);
		Date fecha3 = Date.valueOf(fecha_3);
		
		
		model.addAttribute("fechaactual",fecha3);
		model.addAttribute("fechaanterior",fecha2);
		int numcmesanterior = ReporteService.countCorrectivos(fecha1, fecha2)+Reporte_bajaService.countCorrectivosbaja(fecha1, fecha2);
		int numcmesactual = ReporteService.countCorrectivos(fecha2, fecha3)+Reporte_bajaService.countCorrectivosbaja(fecha2, fecha3);
		float correctivosind = 0;
		if (numcmesanterior==0) {
			correctivosind = 0;
			
		}
		else {
			correctivosind = (float)numcmesactual/numcmesanterior;
		}
		
		int numpreventivosactual = ReporteService.countPreventivos(fecha2, fecha3)+ Reporte_bajaService.countPreventivosbaja(fecha2, fecha3);
		System.out.println("Prueva de indicadores ------------" + numpreventivosactual);
		float correcvspreven = 0;
		if(numpreventivosactual==0) {
			correcvspreven=0;
			
		}
		else {
			correcvspreven = (float)numcmesactual/numpreventivosactual;
			
		}
		
		int numpredictivosactual = ReporteService.countPredictivos(fecha2, fecha3)+ Reporte_bajaService.countPredictivosbaja(fecha2, fecha3);
		float correcvspredic = 0;
		if(numpredictivosactual==0) {
			correcvspredic=0;
		}
		else {
			correcvspredic = (float)numcmesactual/numpredictivosactual;
			
		}
		int numotrosactual = ReporteService.countOtros(fecha2, fecha3)+Reporte_bajaService.countOtrosbaja(fecha2, fecha3);
		
		int numopindebidacorrectivo = ReporteService.countopindebidacorrectivo(fecha2, fecha3)+Reporte_bajaService.countopindebidacorrectivobaja(fecha2, fecha3);
		float opincorrecvscorrecactual = 0;
		if(numcmesactual ==0) {
			opincorrecvscorrecactual=0;
		}
		else {
			opincorrecvscorrecactual = (float)numopindebidacorrectivo/numcmesactual;
		}

		int numcausaexcorrectivo = ReporteService.countcausaexcorrectivo(fecha2, fecha3)+Reporte_bajaService.countcausaexcorrectivobaja(fecha2, fecha3);
		float causaexcorrecvscorrecactual = 0;
		if(numcmesactual==0) {
			causaexcorrecvscorrecactual = 0;
			
		}
		else {
			causaexcorrecvscorrecactual = (float)numcausaexcorrectivo/numcmesactual;
		}
		int previstos = 1;
		if (Mesfinal==1) {
			previstos = EquipoService.numprevEnero();
		}
		else if(Mesfinal==2) {
			previstos = EquipoService.numpreFebrero();
		}
		else if(Mesfinal==3) {
			previstos = EquipoService.numpreMarzo();		
		}
		else if(Mesfinal==4) {
			previstos = EquipoService.numpreAbril();
		}
		else if(Mesfinal==5) {
			previstos = EquipoService.numpreMayo();
		}
		else if(Mesfinal==6) {
			previstos = EquipoService.numpreJunio();
		}
		else if(Mesfinal==7) {
			previstos = EquipoService.numpreJulio();
		}
		else if(Mesfinal==8) {
			previstos = EquipoService.numpreAgosto();
		}
		else if(Mesfinal==9) {
			previstos = EquipoService.numpreSeptiembre();
		}
		else if(Mesfinal==10) {
			previstos = EquipoService.numpreOctubre();
		}
		else if(Mesfinal==11) {
			previstos = EquipoService.numpreNoviembre();
		}
		else if(Mesfinal==12) {
			previstos = EquipoService.numpreDiciembre();
		}		
		else {
			previstos = 1;
		}
		float prevenvsprevistos = (float)numpreventivosactual/previstos;
		
		
		int numdesgastecorrectivo = ReporteService.countdesgastecorrectivo(fecha2, fecha3)+Reporte_bajaService.countdesgastecorrectivobaja(fecha2, fecha3);
		int numaccesorioscorrectivo = ReporteService.countaccesorioscorrectivo(fecha2, fecha3)+Reporte_bajaService.countaccesorioscorrectivobaja(fecha2, fecha3);
		int numdesconocidocorrectivo = ReporteService.countdesconocidocorrectivo(fecha2, fecha3)+Reporte_bajaService.countdesconocidocorrectivobaja(fecha2, fecha3);
		int numsinfallascorrectivo = ReporteService.countsinfallascorrectivo(fecha2, fecha3) +Reporte_bajaService.countsinfallascorrectivobaja(fecha2, fecha3);
		int numotrocorrectivo = ReporteService.countotrocorrectivo(fecha2, fecha3)+Reporte_bajaService.countotrocorrectivobaja(fecha2, fecha3);
		
		int numdesgastemes = ReporteService.countdesgaste(fecha2, fecha3)+Reporte_bajaService.countdesgastebaja(fecha2, fecha3);
		int numopindebidames = ReporteService.countopindebida(fecha2, fecha3)+Reporte_bajaService.countopindebidabaja(fecha2, fecha3);
		int numcausaexmes = ReporteService.countcausaex(fecha2, fecha3) + Reporte_bajaService.countcausaexbaja(fecha2, fecha3);
		int numaccesioriosmes = ReporteService.countaccesorios(fecha2, fecha3)+Reporte_bajaService.countaccesoriosbaja(fecha2, fecha3);
		int numdesconocidomes = ReporteService.countdesconocido(fecha2, fecha3)+Reporte_bajaService.countdesconocidobaja(fecha2, fecha3);
		int numsinfallasmes = ReporteService.countsinfallas(fecha2, fecha3)+Reporte_bajaService.countsinfallasbaja(fecha2, fecha3);
		int numotromes = ReporteService.countotro(fecha2, fecha3)+Reporte_bajaService.countotrobaja(fecha2, fecha3);
		
		Time total_h = ReporteService.tiempopromedio(fecha2, fecha3);
		
		
		model.addAttribute("scorrectivos",ReporteService.acorrectivos(fecha2, fecha3));
		model.addAttribute("spreventivos", ReporteService.apreventivos(fecha2, fecha3));
		model.addAttribute("spredictivos", ReporteService.apredictivos(fecha2, fecha3));
		model.addAttribute("sotrosmtto", ReporteService.aotros(fecha2, fecha3));
		model.addAttribute("sbcorrectivos",Reporte_bajaService.acorrectivosbaja(fecha2, fecha3));
		model.addAttribute("sbpreventivos", Reporte_bajaService.apreventivosbaja(fecha2, fecha3));
		model.addAttribute("sbpredictivos", Reporte_bajaService.apredictivosbaja(fecha2, fecha3));
		model.addAttribute("sbotrosmtto", Reporte_bajaService.aotrosbaja(fecha2, fecha3));
		
		model.addAttribute("fcdesgaste", ReporteService.fdesgastecorr(fecha2, fecha3));
		model.addAttribute("fcopindebida", ReporteService.fopindebidacorr(fecha2, fecha3));
		model.addAttribute("fcaccesorios", ReporteService.faccesorioscorr(fecha2, fecha3));
		model.addAttribute("fccausaex", ReporteService.fcausaexternacorr(fecha2, fecha3));
		model.addAttribute("fcdesconocido", ReporteService.fdesconocidocorr(fecha2, fecha3));
		model.addAttribute("fcsinfallas", ReporteService.fsinfallascorr(fecha2, fecha3));
		model.addAttribute("fcotros", ReporteService.fotroscorr(fecha2, fecha3));
		
		model.addAttribute("fdesgaste", ReporteService.fdesgaste(fecha2, fecha3));
		model.addAttribute("fopindebida", ReporteService.fopindebida(fecha2, fecha3));
		model.addAttribute("faccesorios", ReporteService.faccesorios(fecha2, fecha3));
		model.addAttribute("fcausaex", ReporteService.fcausaexterna(fecha2, fecha3));
		model.addAttribute("fdesconocido", ReporteService.fdesconocido(fecha2, fecha3));
		model.addAttribute("fsinfallas", ReporteService.fsinfallas(fecha2, fecha3));
		model.addAttribute("fotros", ReporteService.fotros(fecha2, fecha3));
		
		
		model.addAttribute("tiempopromedio", total_h);
		model.addAttribute("mantprevistos",previstos);
		model.addAttribute("prevenvsprevistos",prevenvsprevistos);
		model.addAttribute("desgaste",numdesgastemes);
		model.addAttribute("opindebida",numopindebidames);
		model.addAttribute("causaexterna",numcausaexmes);
		model.addAttribute("accesorios",numaccesioriosmes);
		model.addAttribute("desconocido",numdesconocidomes);
		model.addAttribute("sinfallas",numsinfallasmes);
		model.addAttribute("otros",numotromes);
		
		model.addAttribute("correctivosdesgaste",numdesgastecorrectivo);
		model.addAttribute("correctivosaccesorios",numaccesorioscorrectivo);
		model.addAttribute("correctivosdesconocido",numdesconocidocorrectivo);
		model.addAttribute("correctivossinfallas",numsinfallascorrectivo);
		model.addAttribute("correctivosotros",numotrocorrectivo);
		
		model.addAttribute("causaexcorrecvscorrecactual",causaexcorrecvscorrecactual);
		model.addAttribute("correctivoscausaexterna",numcausaexcorrectivo);
		
		
		model.addAttribute("correctivosopindebida",numopindebidacorrectivo);
		model.addAttribute("opincorrecvscorrecactual",opincorrecvscorrecactual);		
		model.addAttribute("otrosmactual",numotrosactual);
		model.addAttribute("correcvspredic",correcvspredic);
		model.addAttribute("predictivosmactual",numpredictivosactual);
		model.addAttribute("correcvspreven",correcvspreven);
		model.addAttribute("preventivosmactual",numpreventivosactual);
		model.addAttribute("correctivosmactual",numcmesactual);
		model.addAttribute("correctivosmpasado",numcmesanterior);
		model.addAttribute("correctivos",correctivosind);
		return "indicadores";
		
	}
	@PostMapping("/indicadores")
	public String obtenermesanoind(Model model,
			RedirectAttributes flash, 
			@RequestParam(value="fecha_inicial",defaultValue = "2022-03-01")String fecha_inicial,
			@RequestParam(value="fecha_final",defaultValue = "2022-02-01")String fecha_final) {
		
		
		LocalDate f1 = LocalDate.parse(fecha_inicial);
		int ano = f1.getYear();
		int anoanterior = ano;
		Month mes1 = f1.getMonth();
		int mesactual =mes1.getValue();
		int mesanterior = mesactual-1;
		String mesant = null;
		if (mesanterior<10 && mesanterior!=0) {
			mesant = "0"+mesanterior;
			
		}
		else if(mesanterior==0) {
			mesant="12";
			anoanterior = ano-1;
		}
		else {
			mesant = String.valueOf(mesanterior); 
			
		}
		
		String fecha_1 = anoanterior+"-"+mesant+"-"+"01";
		
		Date fecha1 = Date.valueOf(fecha_1);
		Date fecha2 = Date.valueOf(fecha_inicial);
		Date fecha3 = Date.valueOf(fecha_final);
		System.out.println(fecha1);
		System.out.println(fecha2);
		System.out.println(fecha3);
		
		int numcmesanterior = ReporteService.countCorrectivos(fecha1, fecha2) +Reporte_bajaService.countCorrectivosbaja(fecha1, fecha2);
		int numcmesactual = ReporteService.countCorrectivos(fecha2, fecha3) +Reporte_bajaService.countCorrectivosbaja(fecha2, fecha3);
		float correctivosind = 0;
		if (numcmesanterior==0) {
			correctivosind = 0;
			
		}
		else {
			correctivosind =(float)numcmesactual/numcmesanterior;
		}
		
		int numpreventivosactual = ReporteService.countPreventivos(fecha2, fecha3) +Reporte_bajaService.countPreventivosbaja(fecha2, fecha3);
		float correcvspreven = 0;
		if(numpreventivosactual==0) {
			correcvspreven=0;
			
		}
		else {
			correcvspreven = (float)numcmesactual/numpreventivosactual;
			
		}
		
		int numpredictivosactual = ReporteService.countPredictivos(fecha2, fecha3)+Reporte_bajaService.countPredictivosbaja(fecha2, fecha3);
		float correcvspredic = 0;
		if(numpredictivosactual==0) {
			correcvspredic=0;
		}
		else {
			correcvspredic = (float)numcmesactual/numpredictivosactual;
			
		}
		int numotrosactual = ReporteService.countOtros(fecha2, fecha3)+Reporte_bajaService.countotrobaja(fecha2, fecha3);
		
		int numopindebidacorrectivo = ReporteService.countopindebidacorrectivo(fecha2, fecha3)+Reporte_bajaService.countopindebidacorrectivobaja(fecha2, fecha3);
		float opincorrecvscorrecactual = 0;
		if(numcmesactual ==0) {
			opincorrecvscorrecactual=0;
		}
		else {
			opincorrecvscorrecactual = (float)numopindebidacorrectivo/numcmesactual;
		}
		int numcausaexcorrectivo = ReporteService.countcausaexcorrectivo(fecha2, fecha3)+Reporte_bajaService.countcausaexcorrectivobaja(fecha2, fecha3);
		float causaexcorrecvscorrecactual = 0;
		if(numcmesactual==0) {
			causaexcorrecvscorrecactual = 0;
			
		}
		else {
			causaexcorrecvscorrecactual = (float)numcausaexcorrectivo/numcmesactual;
		}
		int previstos = 1;
		if (mesactual==1) {
			previstos = EquipoService.numprevEnero();
		}
		else if(mesactual==2) {
			previstos = EquipoService.numpreFebrero();
		}
		else if(mesactual==3) {
			previstos = EquipoService.numpreMarzo();		
		}
		else if(mesactual==4) {
			previstos = EquipoService.numpreAbril();
		}
		else if(mesactual==5) {
			previstos = EquipoService.numpreMayo();
		}
		else if(mesactual==6) {
			previstos = EquipoService.numpreJunio();
		}
		else if(mesactual==7) {
			previstos = EquipoService.numpreJulio();
		}
		else if(mesactual==8) {
			previstos = EquipoService.numpreAgosto();
		}
		else if(mesactual==9) {
			previstos = EquipoService.numpreSeptiembre();
		}
		else if(mesactual==10) {
			previstos = EquipoService.numpreOctubre();
		}
		else if(mesactual==11) {
			previstos = EquipoService.numpreNoviembre();
		}
		else if(mesactual==12) {
			previstos = EquipoService.numpreDiciembre();
		}		
		else {
			previstos = 1;
		}
		float prevenvsprevistos = (float)numpreventivosactual/previstos;
		
		
	
		int numdesgastecorrectivo = ReporteService.countdesgastecorrectivo(fecha2, fecha3)+Reporte_bajaService.countdesgastecorrectivobaja(fecha2, fecha3);
		int numaccesorioscorrectivo = ReporteService.countaccesorioscorrectivo(fecha2, fecha3)+Reporte_bajaService.countaccesorioscorrectivobaja(fecha2, fecha3);
		int numdesconocidocorrectivo = ReporteService.countdesconocidocorrectivo(fecha2, fecha3)+Reporte_bajaService.countdesconocidocorrectivobaja(fecha2, fecha3);
		int numsinfallascorrectivo = ReporteService.countsinfallascorrectivo(fecha2, fecha3) +Reporte_bajaService.countsinfallascorrectivobaja(fecha2, fecha3);
		int numotrocorrectivo = ReporteService.countotrocorrectivo(fecha2, fecha3)+Reporte_bajaService.countotrocorrectivobaja(fecha2, fecha3);
		
		int numdesgastemes = ReporteService.countdesgaste(fecha2, fecha3)+Reporte_bajaService.countdesgastebaja(fecha2, fecha3);
		int numopindebidames = ReporteService.countopindebida(fecha2, fecha3)+Reporte_bajaService.countopindebidabaja(fecha2, fecha3);
		int numcausaexmes = ReporteService.countcausaex(fecha2, fecha3) + Reporte_bajaService.countcausaexbaja(fecha2, fecha3);
		int numaccesioriosmes = ReporteService.countaccesorios(fecha2, fecha3)+Reporte_bajaService.countaccesoriosbaja(fecha2, fecha3);
		int numdesconocidomes = ReporteService.countdesconocido(fecha2, fecha3)+Reporte_bajaService.countdesconocidobaja(fecha2, fecha3);
		int numsinfallasmes = ReporteService.countsinfallas(fecha2, fecha3)+Reporte_bajaService.countsinfallasbaja(fecha2, fecha3);
		int numotromes = ReporteService.countotro(fecha2, fecha3)+Reporte_bajaService.countotrobaja(fecha2, fecha3);
		
		Time total_h = ReporteService.tiempopromedio(fecha2, fecha3);
		
		Time thorainicio = ReporteService.avghorainicio(fecha2, fecha3);
		Time thorallamado = ReporteService.avghorallamado(fecha2, fecha3);
		System.out.println(thorainicio);
		LocalTime tinicio = thorainicio.toLocalTime();
		
		LocalTime tllamado = thorallamado.toLocalTime();
		
		LocalTime trta = tinicio.minusHours(tllamado.getHour());
		LocalTime tiemporespuesta = trta.minusMinutes(tllamado.getMinute());
		
		model.addAttribute("scorrectivos",ReporteService.acorrectivos(fecha2, fecha3));
		model.addAttribute("spreventivos", ReporteService.apreventivos(fecha2, fecha3));
		model.addAttribute("spredictivos", ReporteService.apredictivos(fecha2, fecha3));
		model.addAttribute("sotrosmtto", ReporteService.aotros(fecha2, fecha3));
		model.addAttribute("sbcorrectivos",Reporte_bajaService.acorrectivosbaja(fecha2, fecha3));
		model.addAttribute("sbpreventivos", Reporte_bajaService.apreventivosbaja(fecha2, fecha3));
		model.addAttribute("sbpredictivos", Reporte_bajaService.apredictivosbaja(fecha2, fecha3));
		model.addAttribute("sbotrosmtto", Reporte_bajaService.aotrosbaja(fecha2, fecha3));
		
		model.addAttribute("fcdesgaste", ReporteService.fdesgastecorr(fecha2, fecha3));
		model.addAttribute("fcopindebida", ReporteService.fopindebidacorr(fecha2, fecha3));
		model.addAttribute("fcaccesorios", ReporteService.faccesorioscorr(fecha2, fecha3));
		model.addAttribute("fccausaex", ReporteService.fcausaexternacorr(fecha2, fecha3));
		model.addAttribute("fcdesconocido", ReporteService.fdesconocidocorr(fecha2, fecha3));
		model.addAttribute("fcsinfallas", ReporteService.fsinfallascorr(fecha2, fecha3));
		model.addAttribute("fcotros", ReporteService.fotroscorr(fecha2, fecha3));
		
		model.addAttribute("fdesgaste", ReporteService.fdesgaste(fecha2, fecha3));
		model.addAttribute("fopindebida", ReporteService.fopindebida(fecha2, fecha3));
		model.addAttribute("faccesorios", ReporteService.faccesorios(fecha2, fecha3));
		model.addAttribute("fcausaex", ReporteService.fcausaexterna(fecha2, fecha3));
		model.addAttribute("fdesconocido", ReporteService.fdesconocido(fecha2, fecha3));
		model.addAttribute("fsinfallas", ReporteService.fsinfallas(fecha2, fecha3));
		model.addAttribute("fotros", ReporteService.fotros(fecha2, fecha3));
		
		model.addAttribute("tiempoderespuesta",tiemporespuesta);
		model.addAttribute("tiempopromedio", total_h);
		model.addAttribute("mantprevistos",previstos);
		model.addAttribute("prevenvsprevistos",prevenvsprevistos);
		
		model.addAttribute("desgaste",numdesgastemes);
		model.addAttribute("opindebida",numopindebidames);
		model.addAttribute("causaexterna",numcausaexmes);
		model.addAttribute("accesorios",numaccesioriosmes);
		model.addAttribute("desconocido",numdesconocidomes);
		model.addAttribute("sinfallas",numsinfallasmes);
		model.addAttribute("otros",numotromes);
		
		model.addAttribute("correctivosdesgaste",numdesgastecorrectivo);
		model.addAttribute("correctivosaccesorios",numaccesorioscorrectivo);
		model.addAttribute("correctivosdesconocido",numdesconocidocorrectivo);
		model.addAttribute("correctivossinfallas",numsinfallascorrectivo);
		model.addAttribute("correctivosotros",numotrocorrectivo);
		
		model.addAttribute("causaexcorrecvscorrecactual",causaexcorrecvscorrecactual);
		model.addAttribute("correctivoscausaexterna",numcausaexcorrectivo);
		
		
		model.addAttribute("correctivosopindebida",numopindebidacorrectivo);
		model.addAttribute("opincorrecvscorrecactual",opincorrecvscorrecactual);		
		model.addAttribute("otrosmactual",numotrosactual);
		model.addAttribute("correcvspredic",correcvspredic);
		model.addAttribute("predictivosmactual",numpredictivosactual);
		model.addAttribute("correcvspreven",correcvspreven);
		model.addAttribute("preventivosmactual",numpreventivosactual);
		model.addAttribute("correctivosmactual",numcmesactual);
		model.addAttribute("correctivosmpasado",numcmesanterior);
		model.addAttribute("correctivos",correctivosind);
		
		model.addAttribute("fechaactual",fecha3);
		model.addAttribute("fechaanterior",fecha2);

		return"indicadores";
	}

}
