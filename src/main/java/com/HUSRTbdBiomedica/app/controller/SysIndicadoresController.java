package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.Dao.ITipo_equipoDao;
import com.HUSRTbdBiomedica.app.entity.ObjetoCompuesto;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;


@Controller
@RequestMapping
public class SysIndicadoresController {

	@Autowired
	private ISystemMantenimientoService SystemMantenimientoService;
	
	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired
	private ITipo_equipoService tipo_equipoService;
	
	public List<ObjetoCompuesto<Tipo_equipo, Integer>> cantidadEquipos(){
		ArrayList<ObjetoCompuesto<Tipo_equipo, Integer>> CantidadEquiposTipo = new ArrayList<>();
		List<Tipo_equipo> tiposEquipo = tipo_equipoService.getAllTiposEquipoSys();
		for (int i = 0; i < tiposEquipo.size(); i++) {
			int cantidadEquipos = SystemEquipoService.cantidadEquiposTipo(tiposEquipo.get(i).getId_Tipo_equipo());
			CantidadEquiposTipo.add(new ObjetoCompuesto<Tipo_equipo, Integer>(tiposEquipo.get(i), (Integer)cantidadEquipos));
		}
		
		return CantidadEquiposTipo;
	}
	
	
	
	@GetMapping("/sysindicadores")
	public String sysindicadores(Model model) {
	
		LocalDate Current_Date = LocalDate.now();
		
		Current_Date = Current_Date.minusDays(Current_Date.getDayOfMonth()-1);

		LocalDate fecha_1 = Current_Date.minusMonths(3);
		LocalDate fecha_3 = Current_Date.plusMonths(3);
		int Mesfinal = Current_Date.getMonthValue(); 
		Date fecha1 = Date.valueOf(fecha_1);
		Date fecha2 = Date.valueOf(Current_Date);
		Date fecha3 = Date.valueOf(fecha_3);
		
		
		model.addAttribute("fechaactual",fecha3);
		model.addAttribute("fechaanterior",fecha2);
		int numcmesanterior = SystemMantenimientoService.countCorrectivos(fecha1, fecha2);
		int numcmesactual = SystemMantenimientoService.countCorrectivos(fecha2, fecha3);
		
		float correctivosind = 0;
		if (numcmesanterior==0) {
			correctivosind = 0;
			
		}
		else {
			correctivosind = (float)numcmesactual/numcmesanterior;
		}
		
		int numpreventivosactual = SystemMantenimientoService.countPreventivos(fecha2, fecha3);
		float correcvspreven = 0;
		if(numpreventivosactual==0) {
			correcvspreven=0;
			
		}
		else {
			correcvspreven = (float)numcmesactual/numpreventivosactual;
			
		}
		
		int numpredictivosactual = SystemMantenimientoService.countPredictivos(fecha2, fecha3);
		float correcvspredic = 0;
		if(numpredictivosactual==0) {
			correcvspredic=0;
		}
		else {
			correcvspredic = (float)numcmesactual/numpredictivosactual;
			
		}
		int numotrosactual = SystemMantenimientoService.countOtros(fecha2, fecha3);
		
		int numopindebidacorrectivo = SystemMantenimientoService.countopindebidacorrectivo(fecha2, fecha3);
		float opincorrecvscorrecactual = 0;
		if(numcmesactual ==0) {
			opincorrecvscorrecactual=0;
		}
		else {
			opincorrecvscorrecactual = (float)numopindebidacorrectivo/numcmesactual;
		}

		int numcausaexcorrectivo = SystemMantenimientoService.countcausaexcorrectivo(fecha2, fecha3);
		float causaexcorrecvscorrecactual = 0;
		if(numcmesactual==0) {
			causaexcorrecvscorrecactual = 0;
			
		}
		else {
			causaexcorrecvscorrecactual = (float)numcausaexcorrectivo/numcmesactual;
		}
		int previstos = 1;
		if (Mesfinal==1) {
			previstos = SystemEquipoService.findbyEnero().size();
		}
		else if(Mesfinal==2) {
			previstos = SystemEquipoService.findbyFebrero().size();
		}
		else if(Mesfinal==3) {
			previstos = SystemEquipoService.findbyMarzo().size();		
		}
		else if(Mesfinal==4) {
			previstos = SystemEquipoService.findbyAbril().size();
		}
		else if(Mesfinal==5) {
			previstos = SystemEquipoService.findbyMayo().size();
		}
		else if(Mesfinal==6) {
			previstos = SystemEquipoService.findbyJunio().size();
		}
		else if(Mesfinal==7) {
			previstos = SystemEquipoService.findbyJulio().size();
		}
		else if(Mesfinal==8) {
			previstos = SystemEquipoService.findbyAgosto().size();
		}
		else if(Mesfinal==9) {
			previstos = SystemEquipoService.findbySeptiembre().size();
		}
		else if(Mesfinal==10) {
			previstos = SystemEquipoService.findbyOctubre().size();
		}
		else if(Mesfinal==11) {
			previstos = SystemEquipoService.findbyNoviembre().size();
		}
		else if(Mesfinal==12) {
			previstos = SystemEquipoService.findbyDiciembre().size();
		}		
		else {
			previstos = 1;
		}
		float prevenvsprevistos = (float)numpreventivosactual/previstos;
		
		
		int numdesgastecorrectivo = SystemMantenimientoService.countdesgastecorrectivo(fecha2, fecha3);
		int numaccesorioscorrectivo = SystemMantenimientoService.countaccesorioscorrectivo(fecha2, fecha3);
		int numdesconocidocorrectivo = SystemMantenimientoService.countdesconocidocorrectivo(fecha2, fecha3);
		int numsinfallascorrectivo = SystemMantenimientoService.countsinfallascorrectivo(fecha2, fecha3);
		int numotrocorrectivo = SystemMantenimientoService.countotrocorrectivo(fecha2, fecha3);
		
		int numdesgastemes = SystemMantenimientoService.countdesgaste(fecha2, fecha3);
		int numopindebidames = SystemMantenimientoService.countopindebida(fecha2, fecha3);
		int numcausaexmes = SystemMantenimientoService.countcausaex(fecha2, fecha3);
		int numaccesioriosmes = SystemMantenimientoService.countaccesorios(fecha2, fecha3);
		int numdesconocidomes = SystemMantenimientoService.countdesconocido(fecha2, fecha3);
		int numsinfallasmes = SystemMantenimientoService.countsinfallas(fecha2, fecha3);
		int numotromes = SystemMantenimientoService.countotro(fecha2, fecha3);
		
		Time total_h = null;
		if(SystemMantenimientoService.avghoraterminacion(fecha2, fecha3)!=null && SystemMantenimientoService.avghorainicio(fecha2, fecha3)!=null) {
			LocalTime time1 =SystemMantenimientoService.avghoraterminacion(fecha2, fecha3).toLocalTime();
			LocalTime time2 = SystemMantenimientoService.avghorainicio(fecha2, fecha3).toLocalTime();
			
			LocalTime timetol = time1.minusHours(time2.getHour());
			timetol = time1.minusMinutes(time2.getMinute());
			total_h = Time.valueOf(timetol.minusSeconds(time2.getSecond()));
		}
		
		
		
		model.addAttribute("scorrectivos",SystemMantenimientoService.acorrectivos(fecha2, fecha3));
		model.addAttribute("spreventivos", SystemMantenimientoService.apreventivos(fecha2, fecha3));
		model.addAttribute("spredictivos", SystemMantenimientoService.apyv(fecha2, fecha3));
		model.addAttribute("sotrosmtto", SystemMantenimientoService.aotros(fecha2, fecha3));
	
		
		model.addAttribute("fcdesgaste", SystemMantenimientoService.fdesgastecorr(fecha2, fecha3));
		model.addAttribute("fcopindebida", SystemMantenimientoService.fopindebidacorr(fecha2, fecha3));
		model.addAttribute("fcaccesorios", SystemMantenimientoService.faccesorioscorr(fecha2, fecha3));
		model.addAttribute("fccausaex", SystemMantenimientoService.fcausaexternacorr(fecha2, fecha3));
		model.addAttribute("fcdesconocido", SystemMantenimientoService.fdesconocidocorr(fecha2, fecha3));
		model.addAttribute("fcsinfallas", SystemMantenimientoService.fsinfallascorr(fecha2, fecha3));
		model.addAttribute("fcotros", SystemMantenimientoService.fotroscorr(fecha2, fecha3));
		
		model.addAttribute("fdesgaste", SystemMantenimientoService.fdesgaste(fecha2, fecha3));
		model.addAttribute("fopindebida", SystemMantenimientoService.fopindebida(fecha2, fecha3));
		model.addAttribute("faccesorios", SystemMantenimientoService.faccesorios(fecha2, fecha3));
		model.addAttribute("fcausaex", SystemMantenimientoService.fcausaexterna(fecha2, fecha3));
		model.addAttribute("fdesconocido", SystemMantenimientoService.fdesconocido(fecha2, fecha3));
		model.addAttribute("fsinfallas", SystemMantenimientoService.fsinfallas(fecha2, fecha3));
		model.addAttribute("fotros", SystemMantenimientoService.fotros(fecha2, fecha3));
		
		model.addAttribute("listsystecs", SystemMantenimientoService.listSysTecs(fecha2, fecha3));
		model.addAttribute("sysmttobytecs", SystemMantenimientoService.listByMtto(fecha2, fecha3));
		
		
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
		model.addAttribute("cantidadEquiposTipos", cantidadEquipos());
		model.addAttribute("totalEquiposSys", SystemEquipoService.cantidadEquiposSys());
		System.out.println("---------------Total Equipos: " + SystemEquipoService.cantidadEquiposSys());
		return "sysindicadores";
	}
	@PostMapping("/sysindicadores")
	public String obtenermesanoindsys(Model model,
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
		
		model.addAttribute("fechaactual",fecha3);
		model.addAttribute("fechaanterior",fecha2);
		int numcmesanterior = SystemMantenimientoService.countCorrectivos(fecha1, fecha2);
		int numcmesactual = SystemMantenimientoService.countCorrectivos(fecha2, fecha3);
		
		float correctivosind = 0;
		if (numcmesanterior==0) {
			correctivosind = 0;
			
		}
		else {
			correctivosind = (float)numcmesactual/numcmesanterior;
		}
		
		int numpreventivosactual = SystemMantenimientoService.countPreventivos(fecha2, fecha3);
		float correcvspreven = 0;
		if(numpreventivosactual==0) {
			correcvspreven=0;
			
		}
		else {
			correcvspreven = (float)numcmesactual/numpreventivosactual;
			
		}
		
		int numpredictivosactual = SystemMantenimientoService.countPredictivos(fecha2, fecha3);
		float correcvspredic = 0;
		if(numpredictivosactual==0) {
			correcvspredic=0;
		}
		else {
			correcvspredic = (float)numcmesactual/numpredictivosactual;
			
		}
		int numotrosactual = SystemMantenimientoService.countOtros(fecha2, fecha3);
		
		int numopindebidacorrectivo = SystemMantenimientoService.countopindebidacorrectivo(fecha2, fecha3);
		float opincorrecvscorrecactual = 0;
		if(numcmesactual ==0) {
			opincorrecvscorrecactual=0;
		}
		else {
			opincorrecvscorrecactual = (float)numopindebidacorrectivo/numcmesactual;
		}

		int numcausaexcorrectivo = SystemMantenimientoService.countcausaexcorrectivo(fecha2, fecha3);
		float causaexcorrecvscorrecactual = 0;
		if(numcmesactual==0) {
			causaexcorrecvscorrecactual = 0;
			
		}
		else {
			causaexcorrecvscorrecactual = (float)numcausaexcorrectivo/numcmesactual;
		}
		int previstos = 1;
		if (mesactual==1) {
			previstos = SystemEquipoService.findbyEnero().size();
		}
		else if(mesactual==2) {
			previstos = SystemEquipoService.findbyFebrero().size();
		}
		else if(mesactual==3) {
			previstos = SystemEquipoService.findbyMarzo().size();		
		}
		else if(mesactual==4) {
			previstos = SystemEquipoService.findbyAbril().size();
		}
		else if(mesactual==5) {
			previstos = SystemEquipoService.findbyMayo().size();
		}
		else if(mesactual==6) {
			previstos = SystemEquipoService.findbyJunio().size();
		}
		else if(mesactual==7) {
			previstos = SystemEquipoService.findbyJulio().size();
		}
		else if(mesactual==8) {
			previstos = SystemEquipoService.findbyAgosto().size();
		}
		else if(mesactual==9) {
			previstos = SystemEquipoService.findbySeptiembre().size();
		}
		else if(mesactual==10) {
			previstos = SystemEquipoService.findbyOctubre().size();
		}
		else if(mesactual==11) {
			previstos = SystemEquipoService.findbyNoviembre().size();
		}
		else if(mesactual==12) {
			previstos = SystemEquipoService.findbyDiciembre().size();
		}		
		else {
			previstos = 1;
		}
		float prevenvsprevistos = (float)numpreventivosactual/previstos;
		
		
		int numdesgastecorrectivo = SystemMantenimientoService.countdesgastecorrectivo(fecha2, fecha3);
		int numaccesorioscorrectivo = SystemMantenimientoService.countaccesorioscorrectivo(fecha2, fecha3);
		int numdesconocidocorrectivo = SystemMantenimientoService.countdesconocidocorrectivo(fecha2, fecha3);
		int numsinfallascorrectivo = SystemMantenimientoService.countsinfallascorrectivo(fecha2, fecha3);
		int numotrocorrectivo = SystemMantenimientoService.countotrocorrectivo(fecha2, fecha3);
		
		int numdesgastemes = SystemMantenimientoService.countdesgaste(fecha2, fecha3);
		int numopindebidames = SystemMantenimientoService.countopindebida(fecha2, fecha3);
		int numcausaexmes = SystemMantenimientoService.countcausaex(fecha2, fecha3);
		int numaccesioriosmes = SystemMantenimientoService.countaccesorios(fecha2, fecha3);
		int numdesconocidomes = SystemMantenimientoService.countdesconocido(fecha2, fecha3);
		int numsinfallasmes = SystemMantenimientoService.countsinfallas(fecha2, fecha3);
		int numotromes = SystemMantenimientoService.countotro(fecha2, fecha3);
		
		Time total_h = null;
		if(SystemMantenimientoService.avghoraterminacion(fecha2, fecha3)!=null && SystemMantenimientoService.avghorainicio(fecha2, fecha3)!=null) {
			LocalTime time1 =SystemMantenimientoService.avghoraterminacion(fecha2, fecha3).toLocalTime();
			LocalTime time2 = SystemMantenimientoService.avghorainicio(fecha2, fecha3).toLocalTime();
			
			LocalTime timetol = time1.minusHours(time2.getHour());
			timetol = time1.minusMinutes(time2.getMinute());
			total_h = Time.valueOf(timetol.minusSeconds(time2.getSecond()));
		}
		
		
		model.addAttribute("scorrectivos",SystemMantenimientoService.acorrectivos(fecha2, fecha3));
		model.addAttribute("spreventivos", SystemMantenimientoService.apreventivos(fecha2, fecha3));
		model.addAttribute("spredictivos", SystemMantenimientoService.apyv(fecha2, fecha3));
		model.addAttribute("sotrosmtto", SystemMantenimientoService.aotros(fecha2, fecha3));
	
		
		model.addAttribute("fcdesgaste", SystemMantenimientoService.fdesgastecorr(fecha2, fecha3));
		model.addAttribute("fcopindebida", SystemMantenimientoService.fopindebidacorr(fecha2, fecha3));
		model.addAttribute("fcaccesorios", SystemMantenimientoService.faccesorioscorr(fecha2, fecha3));
		model.addAttribute("fccausaex", SystemMantenimientoService.fcausaexternacorr(fecha2, fecha3));
		model.addAttribute("fcdesconocido", SystemMantenimientoService.fdesconocidocorr(fecha2, fecha3));
		model.addAttribute("fcsinfallas", SystemMantenimientoService.fsinfallascorr(fecha2, fecha3));
		model.addAttribute("fcotros", SystemMantenimientoService.fotroscorr(fecha2, fecha3));
		
		model.addAttribute("fdesgaste", SystemMantenimientoService.fdesgaste(fecha2, fecha3));
		model.addAttribute("fopindebida", SystemMantenimientoService.fopindebida(fecha2, fecha3));
		model.addAttribute("faccesorios", SystemMantenimientoService.faccesorios(fecha2, fecha3));
		model.addAttribute("fcausaex", SystemMantenimientoService.fcausaexterna(fecha2, fecha3));
		model.addAttribute("fdesconocido", SystemMantenimientoService.fdesconocido(fecha2, fecha3));
		model.addAttribute("fsinfallas", SystemMantenimientoService.fsinfallas(fecha2, fecha3));
		model.addAttribute("fotros", SystemMantenimientoService.fotros(fecha2, fecha3));
		
		
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
		
		model.addAttribute("listsystecs", SystemMantenimientoService.listSysTecs(fecha2, fecha3));
		model.addAttribute("sysmttobytecs", SystemMantenimientoService.listByMtto(fecha2, fecha3));
		
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
		return "sysindicadores";
	}
}
