package com.HUSRTbdBiomedica.app.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.temporal.WeekFields;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.temporal.ChronoUnit;

import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.Calificacion;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Responsable;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.app.entity.Validacion;
import com.HUSRTbdBiomedica.service.ICalibracionService;
import com.HUSRTbdBiomedica.service.ICalificacionService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IResponsableService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.IValidacionService;


@Controller
@RequestMapping
public class CalendarioController {
	
	@Autowired
	private IEquipoService EquipoService;
	
	
	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;
	
	@Autowired
	private IUsuarioService UsuarioService;
	
	@Autowired
	private ICalibracionService CalibracionService;
	
	@Autowired
	private ICalificacionService CalificacionService;
	
	@Autowired
	private IValidacionService ValidacionService;
	
	@Autowired
	private IResponsableService ResponsableService;
	
	@GetMapping("/calendario")
	public String calendarios(Model model,
			RedirectAttributes flash) {
		
		model.addAttribute("calval", EquipoService.equiposcalval());
		model.addAttribute("calvalstr", EquipoService.equiposcalvalstr());
		
		model.addAttribute("eneroprepro",EquipoService.preeneroname());
		model.addAttribute("febreroprepro",EquipoService.prefebreroname());
		model.addAttribute("marzoprepro",EquipoService.premarzoname());
		model.addAttribute("abrilprepro",EquipoService.preabrilname());
		model.addAttribute("mayoprepro",EquipoService.premayoname());
		model.addAttribute("junioprepro",EquipoService.prejunioname());
		model.addAttribute("julioprepro",EquipoService.prejulioname());
		model.addAttribute("agostoprepro",EquipoService.preagostoname());
		model.addAttribute("septiembreprepro",EquipoService.preseptiembrename());
		model.addAttribute("octubreprepro",EquipoService.preoctubrename());
		model.addAttribute("noviembreprepro",EquipoService.prenoviembrename());
		model.addAttribute("diciembreprepro",EquipoService.prediciembrename());
		
		model.addAttribute("eneroprecon",EquipoService.coneneroname());
		model.addAttribute("febreroprecon",EquipoService.confebreroname());
		model.addAttribute("marzoprecon",EquipoService.conmarzoname());
		model.addAttribute("abrilprecon",EquipoService.conabrilname());
		model.addAttribute("mayoprecon",EquipoService.conmayoname());
		model.addAttribute("junioprecon",EquipoService.conjunioname());
		model.addAttribute("julioprecon",EquipoService.conjulioname());
		model.addAttribute("agostoprecon",EquipoService.conagostoname());
		model.addAttribute("septiembreprecon",EquipoService.conseptiembrename());
		model.addAttribute("octubreprecon",EquipoService.conoctubrename());
		model.addAttribute("noviembreprecon",EquipoService.connoviembrename());
		model.addAttribute("diciembreprecon",EquipoService.condiciembrename());
		
		model.addAttribute("eneropregar",EquipoService.gareneroname());
		model.addAttribute("febreropregar",EquipoService.garfebreroname());
		model.addAttribute("marzopregar",EquipoService.garmarzoname());
		model.addAttribute("abrilpregar",EquipoService.garabrilname());
		model.addAttribute("mayopregar",EquipoService.garmayoname());
		model.addAttribute("juniopregar",EquipoService.garjunioname());
		model.addAttribute("juliopregar",EquipoService.garjulioname());
		model.addAttribute("agostopregar",EquipoService.garagostoname());
		model.addAttribute("septiembrepregar",EquipoService.garseptiembrename());
		model.addAttribute("octubrepregar",EquipoService.garoctubrename());
		model.addAttribute("noviembrepregar",EquipoService.garnoviembrename());
		model.addAttribute("diciembrepregar",EquipoService.gardiciembrename());
		
		model.addAttribute("equipos", EquipoService.equiposCV());
	
		model.addAttribute("usuarios",UsuarioService.tecnauxbiomedico());
		return "calendario";
	}
	@PostMapping("/programarcalval")
	public String programacioncalval(Model model, RedirectAttributes flash,
			@RequestParam(value = "mescalval",defaultValue = "0")String mes) {
		
		List<Equipo> equipos = EquipoService.equiposcalval();
		int mescalval = Integer.valueOf(mes);
		
		for(int e = 0;e<equipos.size();e++) {
			if(mescalval==equipos.get(e).getCalibracion()) {
				Calibracion cal = new Calibracion();
				cal.setEquipo(equipos.get(e));
				cal.setMes_programado(mescalval);
				cal.setAno_programado(LocalDate.now().getYear());
				CalibracionService.save(cal);
			}
			else if(mescalval == equipos.get(e).getValidacion()) {
				
				Validacion val = new Validacion();
				val.setEquipo(equipos.get(e));
				val.setMes_programado(mescalval);
				val.setAno_programado(LocalDate.now().getYear());
				ValidacionService.save(val);
				
			}
			else if(mescalval == equipos.get(e).getCalifiacion()){
				
				Calificacion calif = new Calificacion();
				calif.setEquipo(equipos.get(e));
				calif.setMes_programado(mescalval);
				calif.setAno_programado(LocalDate.now().getYear());
				CalificacionService.save(calif);
			}
			else {
				if(e>=equipos.size()-1){
					flash.addAttribute("WithoutDevice", "No hay equipos en la planeación");
					return "redirect:/calendario";
				}
				
			}
		}
		
		return "redirect:/procesoscalval";
	}
	@PostMapping("/listplanning")
	public String saveplanning(Model model, RedirectAttributes flash, @RequestParam(value="fecha")String fecha,
			@RequestParam(value="responsable",defaultValue = "ES")String responsable) {
		LocalDate date = LocalDate.of(2023,01,01); // Java 8 date
		//dada
		if(responsable.equals("ES")) {
			
			return "redirect:/listplanning";
		}
		else {
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(responsable.split(",")));
			for(int c = 0;c<listcheck.size();c++) {
				ArrayList<String> contrato = new ArrayList<String>(Arrays.asList(listcheck.get(c).split(";")));
				Responsable resp = ResponsableService.findOne(Long.valueOf(contrato.get(0)));
				Equipo equipo = EquipoService.findOne(Long.valueOf(contrato.get(1)));
				List<Equipo> changede = EquipoService.listEquiposByMM(equipo.getMarca(), equipo.getModelo());
				for(int ec = 0;ec<changede.size();ec++) {
					changede.get(ec).setEnero_mantenimiento("C");
					changede.get(ec).setFebrero_mantenimiento("");
					changede.get(ec).setMarzo_mantenimiento("");
					changede.get(ec).setAbril_mantenimiento("");
					changede.get(ec).setMayo_mantenimiento("");
					changede.get(ec).setJunio_mantenimiento("");
					changede.get(ec).setJulio_mantenimiento("");
					changede.get(ec).setAgosto_mantenimiento("");
					changede.get(ec).setSeptiembre_mantenimiento("");
					changede.get(ec).setOctubre_mantenimiento("");
					changede.get(ec).setNoviembre_mantenimiento("");
					changede.get(ec).setDiciembre_mantenimiento("");
					changede.get(ec).setResponsable(resp);
					EquipoService.save(changede.get(ec));
				}
				
			}
		}
		//garantias y contratos
		List<Equipo> contratos = EquipoService.listContratos();
		
		//PROGRAMACION POR MES GARANTIAS
		int totalmttosg = 0;
	    for(int i = 0; i<contratos.size();i++) {	  
    		int fmpp = contratos.get(i).getServicio().getFmpp();  
    		
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(contratos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttosg+=2;
	    			contratos.get(i).setPeriodicidad(2);
	    		}
	    		else {
	    			totalmttosg+=4;
	    			contratos.get(i).setPeriodicidad(3);
	    		}
	    		
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(contratos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttosg+=2;
	    			contratos.get(i).setPeriodicidad(2);
	    		}
	    		else {
	    			totalmttosg+=3;
	    			contratos.get(i).setPeriodicidad(4);
	    		}
	    
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		//SEMESTRAL
	    		contratos.get(i).setPeriodicidad(2);
	    		totalmttosg+=2;
	    	}
	    	else if(fmpp<6) {
	    		if(contratos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttosg+=1;
	    		}
	    		else {
	    			totalmttosg+=1;
	    		}
	    		contratos.get(i).setPeriodicidad(1);
	    	}  
	    }
		float garbymes = (float)totalmttosg/12;
		int countgar =0;
		for(int i = 0; i<contratos.size();i++) {
			int fmpp = contratos.get(i).getServicio().getFmpp();  
    		countgar+=1;
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(countgar<garbymes+7) {
	    			contratos.get(i).setFebrero_mantenimiento("C");
	    			contratos.get(i).setMayo_mantenimiento("C");
	    			contratos.get(i).setAgosto_mantenimiento("C");
	    			contratos.get(i).setNoviembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("FEBRERO,MAYO,AGOSTO,NOVIEMBRE");
	    			
	    		}
	    		else if(countgar<garbymes*2+7) {
	    			contratos.get(i).setMarzo_mantenimiento("C");
	    			contratos.get(i).setJunio_mantenimiento("C");
	    			contratos.get(i).setSeptiembre_mantenimiento("C");
	    			contratos.get(i).setDiciembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("MARZO,JUNIO,SEPTIEMBRE,DICIEMBRE");
	    		}
	    		else {
	    			contratos.get(i).setFebrero_mantenimiento("C");
	    			contratos.get(i).setMayo_mantenimiento("C");
	    			contratos.get(i).setAgosto_mantenimiento("C");
	    			contratos.get(i).setNoviembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("FEBRERO,MAYO,AGOSTO,NOVIEMBRE");
	    		}		
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(countgar<garbymes*2+7) {
	    			contratos.get(i).setFebrero_mantenimiento("C");
	    			contratos.get(i).setJunio_mantenimiento("C");
	    			contratos.get(i).setOctubre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("FEBRERO,JUNIO,DICIEMBRE");
	    			
	    		}
	    		else if(countgar<garbymes*3+7) {
	    			contratos.get(i).setMarzo_mantenimiento("C");
	    			contratos.get(i).setJulio_mantenimiento("C");
	    			contratos.get(i).setNoviembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("MARZO,JULIO,NOVIEMBRE");
	    		}
	    		else {
	    			contratos.get(i).setAbril_mantenimiento("C");
	    			contratos.get(i).setAgosto_mantenimiento("C");
	    			contratos.get(i).setDiciembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("ABRIL,AGOSTO,DICIEMBRE");
	    		}	
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		//SEMESTRAL
	    		if(countgar<garbymes*4+7) {
	    			contratos.get(i).setMayo_mantenimiento("C");
	    			contratos.get(i).setNoviembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("MAYO,NOVIEMBRE");
	    			
	    		}
	    		else if(countgar<garbymes*5+7) {
	    			contratos.get(i).setJunio_mantenimiento("C");
	    			contratos.get(i).setDiciembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("JUNIO,DICIEMBRE");
	    		}
	    		else {
	    			contratos.get(i).setAbril_mantenimiento("C");
	    			contratos.get(i).setOctubre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("ABRIL,OCTUBRE");
	    		}	
	    		
	    	}
	    	else if(fmpp<6) {
	    		//ANUAL
	    		if(countgar<garbymes*6+7) {
	    			contratos.get(i).setJulio_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("JULIO");
	    			
	    		}
	    		else if(countgar<garbymes*7+7) {
	    			contratos.get(i).setAgosto_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("AGOSTO");
	    		}
	    		else if(countgar<garbymes*8+7){
	    			contratos.get(i).setSeptiembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("SEPTIEMBRE");
	    		}	
	    		else if(countgar<garbymes*9+7){
	    			contratos.get(i).setOctubre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("OCTUBRE");
	    		}	
	    		else {
	    			contratos.get(i).setNoviembre_mantenimiento("C");
	    			contratos.get(i).setDias_mantenimiento("C");
	    			contratos.get(i).setMeses_mantenimiento("NOVIEMBRE");
	    		}
	    	}  
		
		}
		
		
	    List<Equipo> equipos = EquipoService.listequipostoplan();
		LocalDate pascua = LocalDate.parse(fecha);
		int initialWeekOfyear = date.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
	    int weekOfYear = initialWeekOfyear;
	    int totalweeks = 0;
	    int totaldaysWork = 0;
	    //fecha a programar
	    
	    
	    
	    //emiliani
	    LocalDate reyday = LocalDate.of(2023,01,06);
	    LocalDate joseday = LocalDate.of(2023,03,19);
	    LocalDate pedropabloday = LocalDate.of(2023,06,29);
	    LocalDate virginday = LocalDate.of(2023, 8, 15);
	    LocalDate razaday = LocalDate.of(2023,10,12);
	    LocalDate allsaintsday = LocalDate.of(2023,11,1);
	    LocalDate cartday = LocalDate.of(2023,11,11);
	
	    //ajustando festivos emiliani
	    if(reyday.getDayOfWeek().getValue()!=1) {
	    	reyday = reyday.plusWeeks(1).minusDays(reyday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(joseday.getDayOfWeek().getValue()!=1) {
	    	joseday = joseday.plusWeeks(1).minusDays(joseday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(pedropabloday.getDayOfWeek().getValue()!=1) {
	    	pedropabloday = pedropabloday.plusWeeks(1).minusDays(pedropabloday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(virginday.getDayOfWeek().getValue()!=1) {
	    	virginday = virginday.plusWeeks(1).minusDays(virginday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(razaday.getDayOfWeek().getValue()!=1) {
	    	razaday = razaday.plusWeeks(1).minusDays(razaday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(allsaintsday.getDayOfWeek().getValue()!=1) {
	    	allsaintsday = allsaintsday.plusWeeks(1).minusDays(allsaintsday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(cartday.getDayOfWeek().getValue()!=1) {
	    	cartday = cartday.plusWeeks(1).minusDays(cartday.getDayOfWeek().getValue()-1);
	    	
	    }
	    //pascua
	    LocalDate saintfday = pascua.minusDays(2);

	    LocalDate ascenday = pascua.plusDays(43);
	    LocalDate corpusday = pascua.plusDays(64);
	    LocalDate jesusheartday = pascua.plusDays(71);
	    
	    //fechas fijas
	    LocalDate hoy = LocalDate.now();
	    LocalDate anonuevo = LocalDate.of(2023, 01, 01);
	    LocalDate jobday = LocalDate.of(2023,05,01);
	    LocalDate indday = LocalDate.of(2023,7,20);
	    LocalDate boyday = LocalDate.of(2023,8,7);
	    LocalDate velday = LocalDate.of(2023,12,8);
	    LocalDate navday = LocalDate.of(2023,12,25);
	    List<LocalDate> weeks = new ArrayList<LocalDate>();
	    List<Integer> dayspw = new ArrayList<Integer>();
	    do {
	        LocalDate firstDayOfWeek = date.with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
	        LocalDate lastWorkingDayOfWeek = date.with(WeekFields.of(Locale.US).dayOfWeek(), 6L);
	        LocalDate changemonthdayofWeek = LocalDate.now();
	        if(firstDayOfWeek.equals(reyday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(joseday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(pedropabloday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(virginday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(razaday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(allsaintsday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(cartday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(ascenday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(corpusday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(jesusheartday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        //fijos
	        else if(firstDayOfWeek.equals(jobday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(boyday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(navday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(lastWorkingDayOfWeek.equals(velday)) {
	        	lastWorkingDayOfWeek = lastWorkingDayOfWeek.minusDays(1);
	        }
	        else if(lastWorkingDayOfWeek.equals(saintfday)) {
	        	lastWorkingDayOfWeek = lastWorkingDayOfWeek.minusDays(2);
	        }
	        


	        StringBuilder result = new StringBuilder("Week : ");
	        result.append(weekOfYear);
	        result.append(", start=");
	        result.append(firstDayOfWeek);
	        result.append(", end=");
	        result.append(lastWorkingDayOfWeek);
	        if(firstDayOfWeek.getMonthValue()!=lastWorkingDayOfWeek.getMonthValue()) {
	        	changemonthdayofWeek = lastWorkingDayOfWeek.minusDays(lastWorkingDayOfWeek.getDayOfMonth());
	        	Period period1 = Period.between(firstDayOfWeek, changemonthdayofWeek.plusDays(1));
	        	weeks.add(firstDayOfWeek);
	        	dayspw.add(period1.getDays());
	        	Period period2 = Period.between(changemonthdayofWeek.plusDays(1), lastWorkingDayOfWeek.plusDays(1));
	        	weeks.add(changemonthdayofWeek.plusDays(1));
	        	dayspw.add(period2.getDays());
	        }
	        else {
	        	Period period = Period.between(firstDayOfWeek, lastWorkingDayOfWeek.plusDays(1));
	        	totaldaysWork+= period.getDays();
	        	weeks.add(firstDayOfWeek);
		        dayspw.add(period.getDays());
	        }
	        
	        
	        totalweeks = weekOfYear;
	        date = date.plusWeeks(1);
	        
	        
	    } 
	    while ((weekOfYear = date.get(WeekFields.of(Locale.US).weekOfWeekBasedYear())) != initialWeekOfyear);
	  
	    int totalmttos = 0;

	    int mttosemana = 0;
	    //maxdia = (totalmttos/totaldaysWork) =+- 11
	    List<Integer> list = new ArrayList<Integer>(Collections.nCopies(weeks.size(), 0));
	    
	    int mttobyweek = 0;
	    int equipolast = 0;
	    int counts = 0;
	    
	    List<Integer> meses = new ArrayList<Integer>(Collections.nCopies(12, 0));
	    //mediames
	    for(int j = 0;j<weeks.size();j++) {
	    	meses.set(weeks.get(j).getMonthValue()-1,meses.get(weeks.get(j).getMonthValue()-1)+dayspw.get(j));
	    }
	    List<Integer> mesesprog = new ArrayList<Integer>(Collections.nCopies(12, 0));
	    
	    //PROGRAMACION POR MES
	    for(int i = 0; i<equipos.size();i++) {	  
    		int fmpp = equipos.get(i).getServicio().getFmpp();  
    		
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=2;
	    			equipos.get(i).setPeriodicidad(2);
	    		}
	    		else {
	    			totalmttos+=4;
	    			equipos.get(i).setPeriodicidad(3);
	    		}
	    		
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=2;
	    			equipos.get(i).setPeriodicidad(2);
	    		}
	    		else {
	    			totalmttos+=3;
	    			equipos.get(i).setPeriodicidad(4);
	    		}
	    
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		//SEMESTRAL
	    		equipos.get(i).setPeriodicidad(2);
	    		totalmttos+=2;
	    	}
	    	else if(fmpp<6) {
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=1;
	    		}
	    		else {
	    			totalmttos+=1;
	    		}
	    		equipos.get(i).setPeriodicidad(1);
	    	}  
	    }
	    int count = 0;
	    for(int i = 0;i<dayspw.size();i++) {
	    	count+=dayspw.get(i);
	    }
	    List<String> dias = new ArrayList<String>((Collections.nCopies(equipos.size(), new String())));
	    float promedio = totalmttos/count;
	    int countmes = 0;

	    
	    ArrayList<List<Equipo>> equiposbymes = new ArrayList<List<Equipo>>();
	    for(int k = 0;k<12;k++) {
	    	equiposbymes.add(new ArrayList<Equipo>());
	    }
	    
	    List<Equipo> mes = new ArrayList<Equipo>();
	    for(int i = 0; i<equipos.size();i++) {

	    	int fmpp = equipos.get(i).getServicio().getFmpp(); 
	    	equipos.get(i).setDias_mantenimiento("");
	    	equipos.get(i).setMeses_mantenimiento("");
	    	equipos.get(i).setEnero_mantenimiento("");
	    	equipos.get(i).setFebrero_mantenimiento("");
	    	equipos.get(i).setMarzo_mantenimiento("");
	    	equipos.get(i).setAbril_mantenimiento("");
	    	equipos.get(i).setMayo_mantenimiento("");
	    	equipos.get(i).setJunio_mantenimiento("");
	    	equipos.get(i).setJulio_mantenimiento("");
	    	equipos.get(i).setAgosto_mantenimiento("");
    		equipos.get(i).setSeptiembre_mantenimiento("");
    		equipos.get(i).setOctubre_mantenimiento("");
    		equipos.get(i).setNoviembre_mantenimiento("");
    		equipos.get(i).setDiciembre_mantenimiento("");
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {

	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=3) {
		    				countmes = 0;
			    		}
	    				
		    		}

	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    		}
	    		else {
	
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=3) {
		    				countmes = 0;
			    		}
	    				
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+3, mesesprog.get(countmes+3)+1);
	    			equiposbymes.get(countmes+3).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    			mesesprog.set(countmes+9, mesesprog.get(countmes+9)+1);
	    			equiposbymes.get(countmes+9).add(equipos.get(i));
	    			
	    		}
	    		
	    		
	    		countmes+=1;
	    		if(countmes>=3) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}	    				
		    		}
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    		}
	    		else {
	    
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=4) {
		    				countmes = 0;
			    		}
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+4, mesesprog.get(countmes+4)+1);
	    			equiposbymes.get(countmes+4).add(equipos.get(i));
	    			mesesprog.set(countmes+8, mesesprog.get(countmes+8)+1);	
	    			equiposbymes.get(countmes+8).add(equipos.get(i));
	    		}
	    		  
	    		countmes+=1;
	    		if(countmes>=4) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		//SEMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	  
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}	
		    		}
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    			
	    		}
	    		else {
	    		
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));		
	    		}
	    		
	    		countmes+=1;
	    		if(countmes>=6) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<6) {
	    		//ANUAL
    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)+15) {
    				countmes+=1;
    				if(countmes>=12) {
	    				countmes = 0;
		    		}
	    		}    			
    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
    			equiposbymes.get(countmes).add(equipos.get(i));
	    		countmes+=1;
	    		if(countmes>=12) {
    				countmes = 0;
	    		}
	    	}
	    	
	    }
	    int countm = 0;
    	for(int k = 0;k<mesesprog.size();k++) {
	    	countm+=mesesprog.get(k);
	    }

    	int eqmes = 0;
    	for(int j = 0; j<equiposbymes.size();j++) {
    		
    		String monthname = "";
    		for(int e = 0;e<equiposbymes.get(j).size();e++) {
    			eqmes+=equiposbymes.get(j).size();
        		
        		
    			if(j == 0) {
        			equiposbymes.get(j).get(e).setEnero_mantenimiento("m");
        			monthname = "ENERO";
        		}
    			else if(j == 1) {
        			equiposbymes.get(j).get(e).setFebrero_mantenimiento("m");
        			monthname = "FEBRERO";
    			}
    			else if(j == 2) {
        			equiposbymes.get(j).get(e).setMarzo_mantenimiento("m");
        			monthname = "MARZO";
        		}
    			else if(j == 3) {
        			equiposbymes.get(j).get(e).setAbril_mantenimiento("m");
        			monthname = "ABRIL";
        		}
    			else if(j == 4) {
        			equiposbymes.get(j).get(e).setMayo_mantenimiento("m");
        			monthname = "MAYO";
        		}
    			else if(j == 5) {
        			equiposbymes.get(j).get(e).setJunio_mantenimiento("m");
        			monthname = "JUNIO";
        		}
    			else if(j == 6) {
        			equiposbymes.get(j).get(e).setJulio_mantenimiento("m");
        			monthname = "JULIO";
        		}
    			else if(j == 7) {
        			equiposbymes.get(j).get(e).setAgosto_mantenimiento("m");
        			monthname = "AGOSTO";
        		}
    			else if(j == 8) {
        			equiposbymes.get(j).get(e).setSeptiembre_mantenimiento("m");
        			monthname = "SEPTIEMBRE";
        		}
    			else if(j == 9) {
        			equiposbymes.get(j).get(e).setOctubre_mantenimiento("m");
        			monthname = "OCTUBRE";
        		}
    			else if(j == 10) {
        			equiposbymes.get(j).get(e).setNoviembre_mantenimiento("m");
        			monthname = "NOVIEMBRE";
        		}
    			else if(j == 11) {
        			equiposbymes.get(j).get(e).setDiciembre_mantenimiento("m");
        			monthname = "DICIEMBRE";
        		}
    			
    			for(int w = 0;w<weeks.size();w++) {
    				
    				
    				if(weeks.get(w).getMonthValue()-1 == j && promedio*dayspw.get(w)+7>=list.get(w)) {
    					list.set(w, list.get(w)+1);
    					if(equiposbymes.get(j).get(e).getDias_mantenimiento().equals("")) {
    						String day1 = String.valueOf(weeks.get(w).getDayOfMonth());
    						String day2 = String.valueOf(weeks.get(w).plusDays(dayspw.get(w)-1).getDayOfMonth());
    						equiposbymes.get(j).get(e).setDias_mantenimiento(day1+"-"+day2);
    						
    						equiposbymes.get(j).get(e).setMeses_mantenimiento(monthname);
    						EquipoService.save(equiposbymes.get(j).get(e));
    					
    						
    					}
    					else {
    						String day1 = String.valueOf(weeks.get(w).getDayOfMonth());
    						String day2 = String.valueOf(weeks.get(w).plusDays(dayspw.get(w)-1).getDayOfMonth());
    						String days = equiposbymes.get(j).get(e).getDias_mantenimiento();
    						
    						days = days.concat(","+day1+"-"+day2);

    						equiposbymes.get(j).get(e).setDias_mantenimiento(days);
    						
    						String months = equiposbymes.get(j).get(e).getMeses_mantenimiento();
    						months = months.concat(","+monthname);
    						equiposbymes.get(j).get(e).setMeses_mantenimiento(months);
    						EquipoService.save(equiposbymes.get(j).get(e));
    						
    					
    					}
    					w = weeks.size();
    				}
    				
    			} 			
    		}
    	}
    	for(int d = 0;d<equipos.size();d++) {
    		EquipoService.save(equipos.get(d));
    	}
	    int mttocount = 0;
	    for(int m = 0;m<list.size();m++) {
	    	mttocount+=list.get(m);
	    }
	    
		return "listplanning";
	}
	@GetMapping("/listplanning")
	public String listplanning(Model model, RedirectAttributes flash) {
		
		LocalDate date = LocalDate.of(2023,01,01); // Java 8 date
		List<Equipo> equipos = EquipoService.listequipostoplan();
		//dada
		LocalDate pascua = LocalDate.of(2023,04,9);		
		int initialWeekOfyear = date.get(WeekFields.of(Locale.US).weekOfWeekBasedYear());
	    int weekOfYear = initialWeekOfyear;
	    int totalweeks = 0;
	    int totaldaysWork = 0;
	    //fecha a programar
	    
	    
	    
	    //emiliani
	    LocalDate reyday = LocalDate.of(2023,01,06);
	    LocalDate joseday = LocalDate.of(2023,03,19);
	    LocalDate pedropabloday = LocalDate.of(2023,06,29);
	    LocalDate virginday = LocalDate.of(2023, 8, 15);
	    LocalDate razaday = LocalDate.of(2023,10,12);
	    LocalDate allsaintsday = LocalDate.of(2023,11,1);
	    LocalDate cartday = LocalDate.of(2023,11,11);
	
	    //ajustando festivos emiliani
	    if(reyday.getDayOfWeek().getValue()!=1) {
	    	reyday = reyday.plusWeeks(1).minusDays(reyday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(joseday.getDayOfWeek().getValue()!=1) {
	    	joseday = joseday.plusWeeks(1).minusDays(joseday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(pedropabloday.getDayOfWeek().getValue()!=1) {
	    	pedropabloday = pedropabloday.plusWeeks(1).minusDays(pedropabloday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(virginday.getDayOfWeek().getValue()!=1) {
	    	virginday = virginday.plusWeeks(1).minusDays(virginday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(razaday.getDayOfWeek().getValue()!=1) {
	    	razaday = razaday.plusWeeks(1).minusDays(razaday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(allsaintsday.getDayOfWeek().getValue()!=1) {
	    	allsaintsday = allsaintsday.plusWeeks(1).minusDays(allsaintsday.getDayOfWeek().getValue()-1);
	    	
	    }
	    if(cartday.getDayOfWeek().getValue()!=1) {
	    	cartday = cartday.plusWeeks(1).minusDays(cartday.getDayOfWeek().getValue()-1);
	    	
	    }
	    //pascua
	    LocalDate saintfday = pascua.minusDays(2);

	    LocalDate ascenday = pascua.plusDays(43);
	    LocalDate corpusday = pascua.plusDays(64);
	    LocalDate jesusheartday = pascua.plusDays(71);
	    
	    //fechas fijas
	    LocalDate hoy = LocalDate.now();
	    LocalDate anonuevo = LocalDate.of(2023, 01, 01);
	    LocalDate jobday = LocalDate.of(2023,05,01);
	    LocalDate indday = LocalDate.of(2023,7,20);
	    LocalDate boyday = LocalDate.of(2023,8,7);
	    LocalDate velday = LocalDate.of(2023,12,8);
	    LocalDate navday = LocalDate.of(2023,12,25);
	    List<LocalDate> weeks = new ArrayList<LocalDate>();
	    List<Integer> dayspw = new ArrayList<Integer>();
	    do {
	        LocalDate firstDayOfWeek = date.with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
	        LocalDate lastWorkingDayOfWeek = date.with(WeekFields.of(Locale.US).dayOfWeek(), 6L);
	        LocalDate changemonthdayofWeek = LocalDate.now();
	        if(firstDayOfWeek.equals(reyday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(joseday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(pedropabloday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(virginday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(razaday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(allsaintsday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(cartday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(ascenday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(corpusday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(jesusheartday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        //fijos
	        else if(firstDayOfWeek.equals(jobday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(boyday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(firstDayOfWeek.equals(navday)) {
	        	firstDayOfWeek = firstDayOfWeek.plusDays(1);
	        }
	        else if(lastWorkingDayOfWeek.equals(velday)) {
	        	lastWorkingDayOfWeek = lastWorkingDayOfWeek.minusDays(1);
	        }
	        else if(lastWorkingDayOfWeek.equals(saintfday)) {
	        	lastWorkingDayOfWeek = lastWorkingDayOfWeek.minusDays(2);
	        }
	        


	        StringBuilder result = new StringBuilder("Week : ");
	        result.append(weekOfYear);
	        result.append(", start=");
	        result.append(firstDayOfWeek);
	        result.append(", end=");
	        result.append(lastWorkingDayOfWeek);
	        if(firstDayOfWeek.getMonthValue()!=lastWorkingDayOfWeek.getMonthValue()) {
	        	changemonthdayofWeek = lastWorkingDayOfWeek.minusDays(lastWorkingDayOfWeek.getDayOfMonth());
	        	Period period1 = Period.between(firstDayOfWeek, changemonthdayofWeek.plusDays(1));
	        	weeks.add(firstDayOfWeek);
	        	dayspw.add(period1.getDays());
	        	Period period2 = Period.between(changemonthdayofWeek.plusDays(1), lastWorkingDayOfWeek.plusDays(1));
	        	weeks.add(changemonthdayofWeek.plusDays(1));
	        	dayspw.add(period2.getDays());
	        }
	        else {
	        	Period period = Period.between(firstDayOfWeek, lastWorkingDayOfWeek.plusDays(1));
	        	totaldaysWork+= period.getDays();
	        	weeks.add(firstDayOfWeek);
		        dayspw.add(period.getDays());
	        }
	        
	        
	        totalweeks = weekOfYear;
	        date = date.plusWeeks(1);
	        
	        
	    } 
	    while ((weekOfYear = date.get(WeekFields.of(Locale.US).weekOfWeekBasedYear())) != initialWeekOfyear);
	  
	    int totalmttos = 0;

	    int mttosemana = 0;
	    //maxdia = (totalmttos/totaldaysWork) =+- 11
	    List<Integer> list = new ArrayList<Integer>(Collections.nCopies(weeks.size(), 0));
	    
	    int mttobyweek = 0;
	    int equipolast = 0;
	    int counts = 0;
	    
	    List<Integer> meses = new ArrayList<Integer>(Collections.nCopies(12, 0));
	    //mediames
	    for(int j = 0;j<weeks.size();j++) {
	    	meses.set(weeks.get(j).getMonthValue()-1,meses.get(weeks.get(j).getMonthValue()-1)+dayspw.get(j));
	    }
	    List<Integer> mesesprog = new ArrayList<Integer>(Collections.nCopies(12, 0));
	    
	    //PROGRAMACION POR MES
	    for(int i = 0; i<equipos.size();i++) {	  
    		int fmpp = equipos.get(i).getServicio().getFmpp();  
    		
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=2;
	    		}
	    		else {
	    			totalmttos+=4;
	    		}
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=2;
	    		}
	    		else {
	    			totalmttos+=3;
	    		}
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		totalmttos+=2;
	    	}
	    	else if(fmpp<6) {
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    			totalmttos+=1;
	    		}
	    		else {
	    			totalmttos+=1;
	    		}
	    	}  
	    }
	    int count = 0;
	    for(int i = 0;i<dayspw.size();i++) {
	    	count+=dayspw.get(i);
	    }
	    
	    float promedio = totalmttos/count;
	    int countmes = 0;

	    
	    ArrayList<List<Equipo>> equiposbymes = new ArrayList<List<Equipo>>();
	    for(int k = 0;k<12;k++) {
	    	equiposbymes.add(new ArrayList<Equipo>());
	    }
	    
	    List<Equipo> mes = new ArrayList<Equipo>();
	    for(int i = 0; i<equipos.size();i++) {

	    	int fmpp = equipos.get(i).getServicio().getFmpp(); 
	    	if(fmpp>=12) {
	    		//TRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {

	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=3) {
		    				countmes = 0;
			    		}
	    				
		    		}

	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    		}
	    		else {
	
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=3) {
		    				countmes = 0;
			    		}
	    				
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+3, mesesprog.get(countmes+3)+1);
	    			equiposbymes.get(countmes+3).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    			mesesprog.set(countmes+9, mesesprog.get(countmes+9)+1);
	    			equiposbymes.get(countmes+9).add(equipos.get(i));
	    			
	    		}
	    		
	    		
	    		countmes+=1;
	    		if(countmes>=3) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<12 && fmpp>=9) {
	    		//CUATRIMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	    
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}	    				
		    		}
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    		}
	    		else {
	    
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=4) {
		    				countmes = 0;
			    		}
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+4, mesesprog.get(countmes+4)+1);
	    			equiposbymes.get(countmes+4).add(equipos.get(i));
	    			mesesprog.set(countmes+8, mesesprog.get(countmes+8)+1);	
	    			equiposbymes.get(countmes+8).add(equipos.get(i));
	    		}
	    		  
	    		countmes+=1;
	    		if(countmes>=4) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<9 && fmpp>=6) {
	    		//SEMESTRAL
	    		if(equipos.get(i).getNombre_Equipo().contains("BALANZA")) {
	  
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}	
		    		}
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));
	    			
	    		}
	    		else {
	    		
	    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)) {
	    				countmes+=1;
	    				if(countmes>=6) {
		    				countmes = 0;
			    		}
		    		}
	    			
	    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
	    			equiposbymes.get(countmes).add(equipos.get(i));
	    			mesesprog.set(countmes+6, mesesprog.get(countmes+6)+1);
	    			equiposbymes.get(countmes+6).add(equipos.get(i));		
	    		}
	    		
	    		countmes+=1;
	    		if(countmes>=6) {
    				countmes = 0;
	    		}
	    	}
	    	else if(fmpp<6) {
	    		//ANUAL
    			while(mesesprog.get(countmes)>= promedio*meses.get(countmes)+15) {
    				countmes+=1;
    				if(countmes>=12) {
	    				countmes = 0;
		    		}
	    		}    			
    			mesesprog.set(countmes, mesesprog.get(countmes)+1);
    			equiposbymes.get(countmes).add(equipos.get(i));
	    		countmes+=1;
	    		if(countmes>=12) {
    				countmes = 0;
	    		}
	    	}
	    	
	    }
	    int countm = 0;
    	for(int k = 0;k<mesesprog.size();k++) {
	    	countm+=mesesprog.get(k);
	    }

    	int eqmes = 0;
    	for(int j = 0; j<equiposbymes.size();j++) {
    		eqmes+=equiposbymes.get(j).size();
    		String monthname = "";
    		for(int e = 0;e<equiposbymes.get(j).size();e++) {
    			if(j == 0) {
        			equiposbymes.get(j).get(e).setEnero_mantenimiento("m");
        			monthname = "ENERO";
        		}
    			else if(j == 1) {
        			equiposbymes.get(j).get(e).setFebrero_mantenimiento("m");
        			monthname = "FEBRERO";
    			}
    			else if(j == 2) {
        			equiposbymes.get(j).get(e).setMarzo_mantenimiento("m");
        			monthname = "MARZO";
        		}
    			else if(j == 3) {
        			equiposbymes.get(j).get(e).setAbril_mantenimiento("m");
        			monthname = "ABRIL";
        		}
    			else if(j == 4) {
        			equiposbymes.get(j).get(e).setMayo_mantenimiento("m");
        			monthname = "MAYO";
        		}
    			else if(j == 5) {
        			equiposbymes.get(j).get(e).setJunio_mantenimiento("m");
        			monthname = "JUNIO";
        		}
    			else if(j == 6) {
        			equiposbymes.get(j).get(e).setJulio_mantenimiento("m");
        			monthname = "JULIO";
        		}
    			else if(j == 7) {
        			equiposbymes.get(j).get(e).setAgosto_mantenimiento("m");
        			monthname = "AGOSTO";
        		}
    			else if(j == 8) {
        			equiposbymes.get(j).get(e).setSeptiembre_mantenimiento("m");
        			monthname = "SEPTIEMBRE";
        		}
    			else if(j == 9) {
        			equiposbymes.get(j).get(e).setOctubre_mantenimiento("m");
        			monthname = "OCTUBRE";
        		}
    			else if(j == 10) {
        			equiposbymes.get(j).get(e).setNoviembre_mantenimiento("m");
        			monthname = "NOVIEMBRE";
        		}
    			else if(j == 11) {
        			equiposbymes.get(j).get(e).setDiciembre_mantenimiento("m");
        			monthname = "DICIEMBRE";
        		}
    			
    			for(int w = 0;w<weeks.size();w++) {
    				
    				
    				if(weeks.get(w).getMonthValue()-1 == j && promedio*dayspw.get(w)+7>=list.get(w)) {
    					list.set(w, list.get(w)+1);
    					
    					if(equiposbymes.get(j).get(e).getDias_mantenimiento().equals("")) {
    						String day1 = String.valueOf(weeks.get(w).getDayOfMonth());
    						String day2 = String.valueOf(weeks.get(w).plusDays(dayspw.get(w)-1));
    						equiposbymes.get(j).get(e).setDias_mantenimiento(day1+"-"+day2);
    						
    						equiposbymes.get(j).get(e).setMeses_mantenimiento(monthname);
    					}
    					else {
    						String day1 = String.valueOf(weeks.get(w).getDayOfMonth());
    						String day2 = String.valueOf(weeks.get(w).plusDays(dayspw.get(w)-1));
    						String days = equiposbymes.get(j).get(e).getDias_mantenimiento();
    						days.concat(","+day1+"-"+day2);
    						equiposbymes.get(j).get(e).setDias_mantenimiento(days);
    						
    						String months = equiposbymes.get(j).get(e).getMeses_mantenimiento();
    						months.concat(","+monthname);
    						equiposbymes.get(j).get(e).setMeses_mantenimiento(months);
    						
    					}
    					w = weeks.size();
    					
    				}
    				
    			} 			
    		}
    	}
	    int mttocount = 0;
	    for(int m = 0;m<list.size();m++) {
	    	mttocount+=list.get(m);
	    }
	    model.addAttribute("equiposbymes", equiposbymes);
	    model.addAttribute("equipogroup", EquipoService.groupbyNMM());
	    model.addAttribute("responsables", ResponsableService.ListResponsables());
	    return "listplanning";
	}
	
	@PostMapping("/programar")
	public String programacion(Model model, RedirectAttributes flash,
			@RequestParam(value = "daterange")String rangofechas,
			@RequestParam(value = "tecnicosalas")String idtecnicosalas) {
		Long tecnicosalas = Long.valueOf(idtecnicosalas);
		String[] fechas = rangofechas.split("-");
		ArrayList<String> fecha12 = new ArrayList<String>(Arrays.asList(fechas));
		String fecha1 = fecha12.get(0).trim();
		String fecha2 = fecha12.get(1).trim();
		ArrayList<String> fecha1div = new ArrayList<String>(Arrays.asList(fecha1.split("/")));
		ArrayList<String> fecha2div = new ArrayList<String>(Arrays.asList(fecha2.split("/")));
		
		int mes1 = Integer.valueOf(fecha1div.get(0));
		int dia1 = Integer.valueOf(fecha1div.get(1));
		int ano1 = Integer.valueOf(fecha1div.get(2));
		
		int mes2 = Integer.valueOf(fecha2div.get(0));
		int dia2 = Integer.valueOf(fecha2div.get(1));
		int ano2 = Integer.valueOf(fecha2div.get(2));
		List<Equipo> equipos = null;
		if(mes1==mes2 && ano1==ano2) {
			ArrayList<Mantenimiento_preventivo> mantenimientos_preventivos = new ArrayList<Mantenimiento_preventivo>();			
			
			if(mes1==1) {
				
				equipos = EquipoService.prevEnero();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
				
				
								
			}
			
			else if(mes1==2) {
				equipos = EquipoService.preFebrero();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);

			}
			else if(mes1==3) {
				equipos = EquipoService.preMarzo();
				
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
				
					int diainicial = 0;
					int diafinal = 0;
					if(diasmtto.size()==2) {
						diainicial  = diasmtto.get(0);
						
						diafinal  = diasmtto.get(1);
					}
					else {
						diainicial  = diasmtto.get(0);
						diafinal = diainicial+3;
					}
					
					for(int dia=dia1;dia<dia2+1;dia++) {
						
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
					
							
						}
						
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
				
			}
			else if(mes1==4) {
				equipos = EquipoService.preAbril();
				int count = 0;
				
				for(int indice=0;indice<equipos.size();indice++) {
					System.out.println(equipos.get(indice).getSerie());
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					count+=1;
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
						
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
				
			}
			else if(mes1==5) {
				equipos = EquipoService.preMayo();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				
			
				int totalmttos = mantenimientos_preventivos.size();				
				
			
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);

						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
		
				
			}
			else if(mes1==6) {
				equipos = EquipoService.preJunio();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==7) {
				equipos = EquipoService.preJulio();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==8) {
				equipos = EquipoService.preAgosto();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==9) {
				equipos = EquipoService.preSeptiembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==10) {
				equipos = EquipoService.preOctubre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==11) {
				equipos = EquipoService.preNoviembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
					
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else if(mes1==12) {
				equipos = EquipoService.preDiciembre();
				for(int indice=0;indice<equipos.size();indice++) {
					ArrayList<String> mesdias = equipos.get(indice).concaten(equipos.get(indice).getDias_mantenimiento(), equipos.get(indice).getMeses_mantenimiento(), equipos.get(indice).getPeriodicidad());
					ArrayList<Integer> diasmtto=equipos.get(indice).detectarmes_semana(mesdias, mes1);
					int diainicial  = diasmtto.get(0);
					int diafinal  = diasmtto.get(1);
					for(int dia=dia1;dia<dia2+1;dia++) {
						if(diainicial==dia) {
							Mantenimiento_preventivo mantenimiento_preventivo =new Mantenimiento_preventivo();
							mantenimiento_preventivo.setUbicacion(equipos.get(indice).getUbicacion());
							mantenimiento_preventivo.setTipo_equipo(equipos.get(indice).getTipo_equipo());
							mantenimiento_preventivo.setServicio(equipos.get(indice).getServicios());
							mantenimiento_preventivo.setSerie(equipos.get(indice).getSerie());
							mantenimiento_preventivo.setPlaca_inventario(equipos.get(indice).getPlaca_inventario());
							mantenimiento_preventivo.setNombre_Equipo(equipos.get(indice).getNombre_Equipo());
							mantenimiento_preventivo.setModelo(equipos.get(indice).getModelo());
							mantenimiento_preventivo.setMarca(equipos.get(indice).getMarca());
							mantenimiento_preventivo.setEquipo(EquipoService.findOne(equipos.get(indice).getId_Equipo()));
							mantenimiento_preventivo.setDias(String.valueOf(diainicial)+'-'+String.valueOf(diafinal));
							mantenimiento_preventivo.setAno(ano1);
							mantenimiento_preventivo.setMes(mes1);							
							mantenimientos_preventivos.add(mantenimiento_preventivo);
							
							
						}
					}
					
				}
				int totalmttos = mantenimientos_preventivos.size();				
				
				
				List<Usuario> usuarios = UsuarioService.tecnauxbiomedico();
				Usuario tecsalas = UsuarioService.findOne(tecnicosalas);
				ArrayList<Usuario> tecsinsalas = new ArrayList<Usuario>();
				ArrayList<Integer> nummttos = new ArrayList<Integer>();
				for(int user=0;user<usuarios.size();user++) {
					Long tec = usuarios.get(user).getId_Usuario();
					if (tec!=tecsalas.getId_Usuario()) {
						tecsinsalas.add(usuarios.get(user));
						nummttos.add(0);
					}
					
				}
				float mttosbytec = (float)(totalmttos)/usuarios.size();
				float mttoestitecsalas = (float)mttosbytec/2;
				
				float mttopromsinsalas = (float)mttoestitecsalas/(usuarios.size()-1)+mttosbytec;
				int nummttossalas = 0;
				int conttecnico = 0;
				
				for(int mtto=0;mtto<mantenimientos_preventivos.size();mtto++) {
				
					
					if(nummttos.get(conttecnico)<Math.floor(mttopromsinsalas)) {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsinsalas.get(conttecnico));
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttos.set(conttecnico,nummttos.get(conttecnico)+1);
						
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					else {
						mantenimientos_preventivos.get(mtto).setUsuario(tecsalas);
						Mantenimiento_preventivoService.save(mantenimientos_preventivos.get(mtto));
						nummttossalas+=1;
						conttecnico+=1;
						if(conttecnico>=nummttos.size()){
							
							conttecnico = 0;							
						}
					}
					
				}
				model.addAttribute("nummttossalas", nummttossalas);
			}
			else {
				equipos = EquipoService.prevEnero();
			}
			
		}
		else {
			//COMPLETAR PARA FECHAS FUERA DEL MISMO MES
			return "redirect:/calendario";
		}
		
		
		return "redirect:/calendario";
	}
	@PostMapping("/calendario")
	public String calendars(Model model,
			RedirectAttributes flash,
			@RequestParam(value = "mesparatodos")int mesprog
			) {
		List<Equipo> prepropios = null;
		List<Equipo> precontrato = null;
		List<Equipo> pregarantia = null;
		int numprepro = 0;
		int numprecon = 0;
		int numprega = 0;
		String Mes = "No se ha seleccionado ningun mes";
		model.addAttribute("calval", EquipoService.equiposcalval());
		model.addAttribute("calvalstr", EquipoService.equiposcalvalstr());
		if(mesprog==1) {
			Mes = "Enero";
			numprepro = EquipoService.numprevEnero();
			numprecon = EquipoService.numcontratovEnero();
			numprega = EquipoService.numgarantiavEnero();
			prepropios = EquipoService.prevEnero();
			precontrato = EquipoService.contratovEnero();
			pregarantia = EquipoService.garantiavEnero();
		}
		else if(mesprog==2) {
			Mes = "Febrero";
			numprepro = EquipoService.numpreFebrero();
			numprecon = EquipoService.numcontratoFebrero();
			numprega = EquipoService.numgarantiaFebrero();
			prepropios = EquipoService.preFebrero();
			precontrato = EquipoService.contratoFebrero();
			pregarantia = EquipoService.garantiaFebrero();
			
		}
		else if(mesprog==3) {
			Mes = "Marzo";
			numprepro = EquipoService.numpreMarzo();
			numprecon = EquipoService.numcontratoMarzo();
			numprega = EquipoService.numgarantiaMarzo();
			prepropios = EquipoService.preMarzo();
			precontrato = EquipoService.contratoMarzo();
			pregarantia = EquipoService.garantiaMarzo();		
		}
		else if(mesprog==4) {
			Mes = "Abril";
			numprepro = EquipoService.numpreAbril();
			numprecon = EquipoService.numcontratoAbril();
			numprega = EquipoService.numgarantiaAbril();
			prepropios = EquipoService.preAbril();
			precontrato = EquipoService.contratoAbril();
			pregarantia = EquipoService.garantiaAbril();
		}
		else if(mesprog==5) {
			Mes = "Mayo";
			numprepro = EquipoService.numpreMayo();
			numprecon = EquipoService.numcontratoMayo();
			numprega = EquipoService.numgarantiaMayo();
			prepropios = EquipoService.preMayo();
			precontrato = EquipoService.contratoMayo();
			pregarantia = EquipoService.garantiaMayo();
		}
		else if(mesprog==6) {
			Mes = "Junio";
			numprepro = EquipoService.numpreJunio();
			numprecon = EquipoService.numcontratoJunio();
			numprega = EquipoService.numgarantiaJunio();
			prepropios = EquipoService.preJunio();
			precontrato = EquipoService.contratoJunio();
			pregarantia = EquipoService.garantiaJunio();
		}
		else if(mesprog==7) {
			Mes = "Julio";
			numprepro = EquipoService.numpreJulio();
			numprecon = EquipoService.numcontratoJulio();
			numprega = EquipoService.numgarantiaJulio();
			prepropios = EquipoService.preJulio();
			precontrato = EquipoService.contratoJulio();
			pregarantia = EquipoService.garantiaJulio();
		}
		else if(mesprog==8) {
			Mes = "Agosto";
			numprepro = EquipoService.numpreAgosto();
			numprecon = EquipoService.numcontratoAgosto();
			numprega = EquipoService.numgarantiaAgosto();
			prepropios = EquipoService.preAgosto();
			precontrato = EquipoService.contratoAgosto();
			pregarantia = EquipoService.garantiaAgosto();
					
		}
		else if(mesprog==9) {
			Mes = "Septiembre";
			numprepro = EquipoService.numpreSeptiembre();
			numprecon = EquipoService.numcontratoSeptiembre();
			numprega = EquipoService.numgarantiaSeptiembre();
			prepropios = EquipoService.preSeptiembre();
			precontrato = EquipoService.contratoSeptiembre();
			pregarantia = EquipoService.garantiaSeptiembre();
		}
		else if(mesprog==10) {
			Mes = "Octubre";
			numprepro = EquipoService.numpreOctubre();
			numprecon = EquipoService.numcontratoOctubre();
			numprega = EquipoService.numgarantiaOctubre();
			prepropios = EquipoService.preOctubre();
			precontrato = EquipoService.contratoOctubre();
			pregarantia = EquipoService.garantiaOctubre();
		}
		else if(mesprog==11) {
			Mes = "Noviembre";
			numprepro = EquipoService.numpreNoviembre();
			numprecon = EquipoService.numcontratoNoviembre();
			numprega = EquipoService.numgarantiaNoviembre();
			prepropios = EquipoService.preNoviembre();
			precontrato = EquipoService.contratoNoviembre();
			pregarantia = EquipoService.garantiaNoviembre();
		}
	
		else if(mesprog==12) {
			Mes = "Diciembre";
			numprepro = EquipoService.numpreDiciembre();
			numprecon = EquipoService.numcontratoDiciembre();
			numprega = EquipoService.numgarantiaDiciembre();
			prepropios = EquipoService.preDiciembre();
			precontrato = EquipoService.contratoDiciembre();
			pregarantia = EquipoService.garantiaDiciembre();
		}
		model.addAttribute("Messelect",Mes);
		model.addAttribute("numprepro",numprepro);
		model.addAttribute("numprecon",numprecon);
		model.addAttribute("numprega",numprega);
		model.addAttribute("prepropios",prepropios);
		model.addAttribute("precontrato",precontrato);
		model.addAttribute("pregarantia",pregarantia);
		
		model.addAttribute("equipos", EquipoService.equiposCV());
		model.addAttribute("frebrero",EquipoService.preFebrero());
		
		

		return "calendario";
	}

}
