package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Criterios;
import com.HUSRTbdBiomedica.app.entity.Criterios_economicos;
import com.HUSRTbdBiomedica.app.entity.Criterios_especificos;
import com.HUSRTbdBiomedica.app.entity.Criterios_tecnicos;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.service.ICriteriosService;
import com.HUSRTbdBiomedica.service.ICriterios_economicosService;
import com.HUSRTbdBiomedica.service.ICriterios_especificosService;
import com.HUSRTbdBiomedica.service.ICriterios_tecnicosService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IServicioService;

@Controller
@SessionAttributes("criterios_tecnicos")
@RequestMapping
public class Criterios_tecnicosController {
	
	@Autowired
	private ICriterios_tecnicosService Criterios_tecnicosService;
	
	@Autowired
	private ICriteriosService CriteriosService;
	
	@Autowired
	private ICriterios_economicosService Criterios_economicosService;
	
	@Autowired
	private ICriterios_especificosService Criterios_especificosService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@GetMapping("/semaforizacionportipoequipo")
	public String semaforizaciontipoequipo(Model model,
            RedirectAttributes flash) {
		
			
			
			
			model.addAttribute("tiposequipos",Tipo_equipoService.ListTipo_equipo());
			return "semaforizacionportipoequipo";
			}
	@GetMapping("/semaforizacionporservicio")
	public String semaforizacionservicio(Model model,
            RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.ListServicio());
		return "semaforizacionporservicio";
	}
	@GetMapping("/equiposparasemaforizarservicio/{id}")
	public String equiposporsemaforizarservicio(@PathVariable(value="id") Long id,Model model,
			  RedirectAttributes flash) {
		model.addAttribute("servicio",ServicioService.findOne(id));
		model.addAttribute("equiposservicio",ServicioService.findEquiposbyServicio(id));
		model.addAttribute("numequipos",ServicioService.countEspecificbyServicio(id));
		return "equiposparasemaforizarservicio";
	}
	@PostMapping("/equiposparasemaforizarservicio/{id}")
	public String ejecucionsemafespecial(@PathVariable Long id,
			  Model model,
			  @RequestParam(value="equiposselect",defaultValue = "1")String ides,
			  RedirectAttributes flash,
			  SessionStatus status) {

		ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(ides.split(",")));
		
		
		Criterios criterios = new Criterios();
		Criterios_tecnicos criterios_tecnicos =new Criterios_tecnicos();
		Criterios_economicos criterios_economicos = new Criterios_economicos();
		Criterios_especificos criterios_especificos = new Criterios_especificos();
		for(int indice=0;indice<listcheck.size();indice++) {
			criterios = new Criterios();
			criterios_tecnicos =new Criterios_tecnicos();
			criterios_economicos = new Criterios_economicos();
			criterios_especificos = new Criterios_especificos();
			criterios.setCriterios_economicos(criterios_economicos);
			criterios.setCriterios_tecnicos(criterios_tecnicos);
			criterios.setCriterios_especificos(criterios_especificos);
			Equipo equipo = EquipoService.findOne(Long.valueOf(listcheck.get(indice)));
			
			try {
				Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(equipo.getId_Equipo());
	    		if(hoja_vida==null) {
	    			int puntintuso = equipo.puntajeIntensidadUso(equipo.getPeriodicidad());
					criterios_tecnicos.setIntensidad_uso(puntintuso);
					int puntedad = equipo.puntajeEdad(equipo.getAno_ingreso());
					criterios_tecnicos.setEdad(puntedad);
					
					
					
					LocalDate Current_Date = LocalDate.now();
					LocalDate yearpastactual = Current_Date.minusYears(1);

					int correctivos = Criterios_tecnicosService.numerocorrectivosanuales(equipo.getId_Equipo(),yearpastactual, Current_Date);
					
					int puntajenumcorrectivos=0;
					if(correctivos==0) {
						puntajenumcorrectivos = 100;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==1) {
						puntajenumcorrectivos = 80;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==2) {
						puntajenumcorrectivos = 55;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==3) {
						puntajenumcorrectivos = 30;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==4) {
						puntajenumcorrectivos = 15;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos>=5) {
						puntajenumcorrectivos = 0;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					
					
					//criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					
					
					
					//criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
					String tiempo = ReporteService.MaxtimeOut(equipo.getId_Equipo(), Date.valueOf(yearpastactual), Date.valueOf(Current_Date));
					int puntfuerase = 100;
					if(tiempo!=null) {
						puntfuerase = equipo.puntajeFueraServicio(Integer.valueOf(tiempo));
					}
					criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
					
					
					
					
					//criterios_tecnicos.setSeguridad(puntseguridad);
					int puntseguridad = equipo.puntajeSeguridad(equipo.getId_Equipo());
					criterios_tecnicos.setSeguridad(puntseguridad);
					
					
					
					int puntgarantia = 0;
					
					//criterios_tecnicos.setGarantia_repuestos(puntgarantia);
					criterios_tecnicos.setGarantia_repuestos(puntgarantia);
					int punttotal = puntintuso*15 +puntedad*20+puntajenumcorrectivos*30+puntfuerase*10
							+puntseguridad*5+puntgarantia*20;
					
					double punt = punttotal/100;
					punttotal = (int)Math.round(punt);
					//criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
					criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
					criterios_economicos.setTotal_puntos_economicos(0);
					criterios_especificos.setTotal_puntos_economicos(0);
					criterios.setTotal_puntos(punttotal);
					criterios.setEquipo(equipo);
					criterios.setFecha(Date.valueOf(Current_Date));
					CriteriosService.save(criterios);
					Criterios_tecnicosService.save(criterios_tecnicos);
					Criterios_economicosService.save(criterios_economicos);
					Criterios_especificosService.save(criterios_especificos);
					status.setComplete();
					
				}
				else {
					int puntintuso = equipo.puntajeIntensidadUso(equipo.getPeriodicidad());
					criterios_tecnicos.setIntensidad_uso(puntintuso);
					int puntedad = equipo.puntajeEdad(equipo.getAno_ingreso());
					criterios_tecnicos.setEdad(puntedad);
					
					
					
					LocalDate Current_Date = LocalDate.now();
					LocalDate yearpastactual = Current_Date.minusYears(1);

					int correctivos = Criterios_tecnicosService.numerocorrectivosanuales(equipo.getId_Equipo(),yearpastactual, Current_Date);
					
					int puntajenumcorrectivos=0;
					if(correctivos==0) {
						puntajenumcorrectivos = 100;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==1) {
						puntajenumcorrectivos = 80;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==2) {
						puntajenumcorrectivos = 55;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==3) {
						puntajenumcorrectivos = 30;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos==4) {
						puntajenumcorrectivos = 15;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					else if(correctivos>=5) {
						puntajenumcorrectivos = 0;
						criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					}
					
					
					//criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
					
					
					
					//criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
					String tiempo = ReporteService.MaxtimeOut(equipo.getId_Equipo(), Date.valueOf(yearpastactual), Date.valueOf(Current_Date));
					int puntfuerase = 100;
					if(tiempo!=null) {
						puntfuerase = equipo.puntajeFueraServicio(Integer.valueOf(tiempo));
					}
					
					
					criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
					
					
					
					
					//criterios_tecnicos.setSeguridad(puntseguridad);
					int puntseguridad = equipo.puntajeSeguridad(equipo.getId_Equipo());
					criterios_tecnicos.setSeguridad(puntseguridad);
					
					
					
					int puntgarantia = hoja_vida.evgarantia(hoja_vida.getFecha_vencimientogarantia());
					
					//criterios_tecnicos.setGarantia_repuestos(puntgarantia);
					criterios_tecnicos.setGarantia_repuestos(puntgarantia);
					int punttotal = puntintuso*15 +puntedad*20+puntajenumcorrectivos*30+puntfuerase*10
							+puntseguridad*5+puntgarantia*20;
					
					double punt = punttotal/100;
					punttotal = (int)Math.round(punt);
					//criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
					criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
					criterios_economicos.setTotal_puntos_economicos(0);
					criterios_especificos.setTotal_puntos_economicos(0);
					criterios.setTotal_puntos(punttotal);
					criterios.setEquipo(equipo);
					criterios.setFecha(Date.valueOf(Current_Date));
					CriteriosService.save(criterios);
					Criterios_tecnicosService.save(criterios_tecnicos);
					Criterios_economicosService.save(criterios_economicos);
					Criterios_especificosService.save(criterios_especificos);
					status.setComplete();
				}
	    		
					
	
	    	}
	    	catch(Exception e) {
	    		
	    		return "redirect:/listarhvrepeat/"+equipo.getId_Equipo();
	    	}
			
		}
		return "redirect:/buscarultimaevaluacion";
	}
	@GetMapping("/buscarultimaevaluacion")
	public String elegirtipolastev(Model model,
			  RedirectAttributes flash) {
		model.addAttribute("tiposequipos",Tipo_equipoService.ListTipo_equipo());
		
		return "buscarultimaevaluacion";
	}
	
	@GetMapping("/equiposparasemaforizar/{id}")
	public String equiposporsemaforizar(@PathVariable(value="id") Long id,Model model,
			  RedirectAttributes flash) {
		Criterios criterios = new Criterios();
		Criterios_tecnicos criterios_tecnicos =new Criterios_tecnicos();
		Criterios_economicos criterios_economicos = new Criterios_economicos();
		Criterios_especificos criterios_especificos = new Criterios_especificos();
		
		
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		List<Equipo> equipostipo = Tipo_equipoService.findEquiposbyTipoEquipo(id);
		ArrayList<Criterios> listacriterios = new ArrayList<Criterios>();
		
		ArrayList<Criterios_tecnicos> listacriteriostecnicos = new ArrayList<Criterios_tecnicos>();
		ArrayList<Criterios_economicos> listacriterioseconomicos = new ArrayList<Criterios_economicos>();
		
		ArrayList<Criterios_especificos> listacriteriosespecificos = new ArrayList<Criterios_especificos>();
		for(int indice=0;indice<equipostipo.size();indice++) {
			listacriterios.add(new Criterios());
			listacriteriostecnicos.add(new Criterios_tecnicos());
			listacriterioseconomicos.add(new Criterios_economicos());
			listacriteriosespecificos.add(new Criterios_especificos());
			
		}
		
		
		
    	model.addAttribute("nombretipoequipo",tipo_equipo.getNombre_Tipo_equipo());
      
        model.addAttribute("tipo_equipossem",Tipo_equipoService.findEquiposbyTipoEquipo(id));
        Equipo equipo=null;
        if(id>0){
    		equipo = EquipoService.findOne(104L);
    		for(int indice=0;indice<equipostipo.size();indice++) {
    			Long id_equipo = equipostipo.get(indice).getId_Equipo();
    			listacriterios.get(indice).setEquipo(EquipoService.findOne(id_equipo));
    			listacriterios.get(indice).setCriterios_tecnicos(listacriteriostecnicos.get(indice));
    			listacriterios.get(indice).setCriterios_economicos(listacriterioseconomicos.get(indice));
    			listacriterios.get(indice).setCriterios_especificos(listacriteriosespecificos.get(indice));
    		}
    		criterios.setEquipo(EquipoService.findOne(104L));
    		criterios.setFecha(Date.valueOf(LocalDate.now()));
    		criterios.setCriterios_tecnicos(criterios_tecnicos);
    		criterios.setCriterios_economicos(criterios_economicos);
    		criterios.setCriterios_especificos(criterios_especificos);
        	criterios.setTotal_puntos(0);
    		if(equipo==null) {
    			flash.addFlashAttribute("error","El producto no existe en la base de datos");
    			return "redirect:/semaforizacionportipoequipo";
    		}
    		
    	}
    	
    	else {
    		flash.addFlashAttribute("error","El ID no puede ser cero");
    		return "redirect:/semaforizacionportipoequipo";
    	}
        model.addAttribute("listacriterios",listacriterios);
		model.addAttribute("listacriteriostecnicos",listacriteriostecnicos);
		model.addAttribute("listacriterioseconomicos",listacriterioseconomicos);
		model.addAttribute("listacriteriosespecificos",listacriteriosespecificos);
        model.addAttribute("idequipo",tipo_equipo.getId_Tipo_equipo());
		return "equiposparasemaforizar";
	}
	@PostMapping("/equiposparasemaforizar/{id}")
	public String guardarcriterios(@PathVariable Long id,
					  Model model,
					  RedirectAttributes flash,
					  SessionStatus status){
		Criterios criterios = new Criterios();
		Criterios_tecnicos criterios_tecnicos =new Criterios_tecnicos();
		Criterios_economicos criterios_economicos = new Criterios_economicos();
		Criterios_especificos criterios_especificos = new Criterios_especificos();
		
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		List<Equipo> equipostipo = Tipo_equipoService.findEquiposbyTipoEquipo(id);
		ArrayList<Criterios> listacriterios = new ArrayList<Criterios>();
		
		ArrayList<Criterios_tecnicos> listacriteriostecnicos = new ArrayList<Criterios_tecnicos>();
		ArrayList<Criterios_economicos> listacriterioseconomicos = new ArrayList<Criterios_economicos>();
		
		ArrayList<Criterios_especificos> listacriteriosespecificos = new ArrayList<Criterios_especificos>();
		for(int indice=0;indice<equipostipo.size();indice++) {
			listacriterios.add(new Criterios());
			listacriteriostecnicos.add(new Criterios_tecnicos());
			listacriterioseconomicos.add(new Criterios_economicos());
			listacriteriosespecificos.add(new Criterios_especificos());
			
		}
		
		
		
    	model.addAttribute("nombretipoequipo",tipo_equipo.getNombre_Tipo_equipo());
      
        model.addAttribute("tipo_equipossem",Tipo_equipoService.findEquiposbyTipoEquipo(id));
        Equipo equipo=null;
        if(id>0){
    		equipo = EquipoService.findOne(104L);
    		for(int indice=0;indice<equipostipo.size();indice++) {
    			Long id_equipo = equipostipo.get(indice).getId_Equipo();
    			listacriterios.get(indice).setEquipo(EquipoService.findOne(id_equipo));
    			listacriterios.get(indice).setCriterios_tecnicos(listacriteriostecnicos.get(indice));
    			listacriterios.get(indice).setCriterios_economicos(listacriterioseconomicos.get(indice));
    			listacriterios.get(indice).setCriterios_especificos(listacriteriosespecificos.get(indice));
    		}
    		criterios.setEquipo(EquipoService.findOne(104L));
    		criterios.setCriterios_tecnicos(criterios_tecnicos);
    		criterios.setCriterios_economicos(criterios_economicos);
    		criterios.setCriterios_especificos(criterios_especificos);
        	criterios.setTotal_puntos(0);
    		if(equipo==null) {
    			flash.addFlashAttribute("error","El producto no existe en la base de datos");
    			return "redirect:/semaforizacionportipoequipo";
    		}
    		
    	}
    	
    	else {
    		flash.addFlashAttribute("error","El ID no puede ser cero");
    		return "redirect:/semaforizacionportipoequipo";
    	}
		for(int indice=0;indice<equipostipo.size();indice++) {
			
			Long id_equipo = equipostipo.get(indice).getId_Equipo();
			
			equipo = EquipoService.findOne(id_equipo);
			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(equipo.getId_Equipo());
			if(hoja_vida==null) {
				int puntintuso = equipo.puntajeIntensidadUso(equipo.getPeriodicidad());
				listacriteriostecnicos.get(indice).setIntensidad_uso(puntintuso);
				
				//criterios_tecnicos.setIntensidad_uso(puntintuso);
				
				int puntedad = equipo.puntajeEdad(equipo.getAno_ingreso());
				listacriteriostecnicos.get(indice).setEdad(puntedad);
				//criterios_tecnicos.setEdad(puntedad);
				
				
				
				LocalDate Current_Date = LocalDate.now();
				LocalDate yearpastactual = Current_Date.minusYears(1);

				int correctivos = Criterios_tecnicosService.numerocorrectivosanuales(equipo.getId_Equipo(),yearpastactual, Current_Date);
				
				
				int puntajenumcorrectivos=0;
				if(correctivos==0) {
					puntajenumcorrectivos = 100;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				else if(correctivos==1) {
					puntajenumcorrectivos = 80;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				else if(correctivos==2) {
					puntajenumcorrectivos = 55;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				else if(correctivos==3) {
					puntajenumcorrectivos = 30;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				else if(correctivos==4) {
					puntajenumcorrectivos = 15;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				else if(correctivos>=5) {
					puntajenumcorrectivos = 0;
					listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				}
				
				
				//criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
				
				int puntfuerase = 70;
				
				//criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
				listacriteriostecnicos.get(indice).setTiempo_fuera_servicio(puntfuerase);
				
				int puntmanual = 0;
				//criterios_tecnicos.setManual_usuario_servicio(puntmanual);
				listacriteriostecnicos.get(indice).setManual_usuario_servicio(puntmanual);
				
				int puntseguridad = 70;
				//criterios_tecnicos.setSeguridad(puntseguridad);
				listacriteriostecnicos.get(indice).setSeguridad(puntseguridad);
				
				int puntgarantia = 0;
				
				//criterios_tecnicos.setGarantia_repuestos(puntgarantia);
				listacriteriostecnicos.get(indice).setGarantia_repuestos(puntgarantia);
				
				
				int punttotal = puntintuso*15 +puntedad*15+puntajenumcorrectivos*30+puntfuerase*10
						+puntmanual*5+puntseguridad*5+puntgarantia*20;
				
				double punt = punttotal/100;
				punttotal = (int)Math.round(punt);
				//criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
				listacriteriostecnicos.get(indice).setTotal_puntos_tecnicos(punttotal);
				listacriteriosespecificos.get(indice).setTotal_puntos_economicos(0);
				listacriterioseconomicos.get(indice).setTotal_puntos_economicos(0);
				listacriterios.get(indice).setTotal_puntos(punttotal);
				listacriterios.get(indice).setFecha(Date.valueOf(Current_Date));
				Criterios_economicosService.save(listacriterioseconomicos.get(indice));
				Criterios_especificosService.save(listacriteriosespecificos.get(indice));
				Criterios_tecnicosService.save(listacriteriostecnicos.get(indice));
				CriteriosService.save(listacriterios.get(indice));
				
				status.setComplete();
				
			}
			else {
				int puntintuso = equipo.puntajeIntensidadUso(equipo.getPeriodicidad());
				listacriteriostecnicos.get(indice).setIntensidad_uso(puntintuso);
				
				//criterios_tecnicos.setIntensidad_uso(puntintuso);
				
				int puntedad = equipo.puntajeEdad(equipo.getAno_ingreso());
				listacriteriostecnicos.get(indice).setEdad(puntedad);
				//criterios_tecnicos.setEdad(puntedad);
				
				LocalDate Current_Date = LocalDate.now();
				
				int current_year= Current_Date.getYear();
				String ano_str = Integer.toString(current_year)+"-01-01";
				DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate enero1 =LocalDate.parse(ano_str,pattern);
				
				//Date Current_Date1 = java.sql.Date.valueOf(Current_Date);
				
				
				//Date enero = java.sql.Date.valueOf(enero1);
				
				int correctivos = Criterios_tecnicosService.numerocorrectivosanuales(id_equipo,enero1, Current_Date);
				
				int puntajenumcorrectivos=0;
				if(correctivos==0) {
					puntajenumcorrectivos = 100;
					
				}
				else if(correctivos==1) {
					puntajenumcorrectivos = 80;
					
				}
				else if(correctivos==2) {
					puntajenumcorrectivos = 55;
					
				}
				else if(correctivos==3) {
					puntajenumcorrectivos = 30;
					
				}
				else if(correctivos==4) {
					puntajenumcorrectivos = 15;
					
				}
				else if(correctivos>=5) {
					puntajenumcorrectivos = 0;
					
				}
				
				listacriteriostecnicos.get(indice).setNumero_mantenimientos(puntajenumcorrectivos);
				//criterios_tecnicos.setNumero_mantenimientos(puntajenumcorrectivos);
				
				int puntfuerase = 70;
				
				//criterios_tecnicos.setTiempo_fuera_servicio(puntfuerase);
				listacriteriostecnicos.get(indice).setTiempo_fuera_servicio(puntfuerase);
				
				
				int puntmanual = hoja_vida.evmanual(hoja_vida.isManual_operacion(),hoja_vida.isManual_tecnico());
				//criterios_tecnicos.setManual_usuario_servicio(puntmanual);
				listacriteriostecnicos.get(indice).setManual_usuario_servicio(puntmanual);
				
				int puntseguridad = 70;
				//criterios_tecnicos.setSeguridad(puntseguridad);
				listacriteriostecnicos.get(indice).setSeguridad(puntseguridad);
				
				int puntgarantia = hoja_vida.evgarantia(hoja_vida.getFecha_vencimientogarantia());
				
				//criterios_tecnicos.setGarantia_repuestos(puntgarantia);
				listacriteriostecnicos.get(indice).setGarantia_repuestos(puntgarantia);
				
				
				int punttotal = puntintuso*15 +puntedad*15+puntajenumcorrectivos*30+puntfuerase*10
						+puntmanual*5+puntseguridad*5+puntgarantia*20;
				
				double punt = punttotal/100;
				punttotal = (int)Math.round(punt);
				//criterios_tecnicos.setTotal_puntos_tecnicos(punttotal);
				listacriteriostecnicos.get(indice).setTotal_puntos_tecnicos(punttotal);
				listacriteriosespecificos.get(indice).setTotal_puntos_economicos(0);
				listacriterioseconomicos.get(indice).setTotal_puntos_economicos(0);
				listacriterios.get(indice).setTotal_puntos(punttotal);
				listacriterios.get(indice).setFecha(Date.valueOf(Current_Date));
				Criterios_economicosService.save(listacriterioseconomicos.get(indice));
				Criterios_especificosService.save(listacriteriosespecificos.get(indice));
				Criterios_tecnicosService.save(listacriteriostecnicos.get(indice));
				CriteriosService.save(listacriterios.get(indice));
				
				status.setComplete();
				
			}
			
			
			
		}
			
		
		flash.addFlashAttribute("agregado","Evaluación agregado correctamente");
		return "redirect:/ultimosresultadosevtecnica/"+equipo.getTipo_equipo().getId_Tipo_equipo();
	}
	
	
	@GetMapping("/resultadosevaluacionporequipos")
	public String allresults(Model model,
			  RedirectAttributes flash) {
		
		model.addAttribute("buenos",CriteriosService.ListBuenos());
		model.addAttribute("regulares",CriteriosService.ListRegulares());
		model.addAttribute("malos",CriteriosService.ListMalos());
		model.addAttribute("cribu",CriteriosService.Listcribuenos());
		model.addAttribute("criregu",CriteriosService.Listcriregulares());
		model.addAttribute("crimal",CriteriosService.Listcrimalos());
		
		return "resultadosevaluacionporequipos";
	}
	
	@GetMapping("/ultimosresultadosevtecnica/{id}")
	public String mostrarultimosresultadosporequipo(@PathVariable Long id,
						Model model,RedirectAttributes flash) {
		List<Equipo> equipos = Tipo_equipoService.findEquiposbyTipoEquipo(id);
		ArrayList<List<String>> criteriosequipos = new ArrayList<List<String>>();
		List<Criterios> criteriosultimos = new ArrayList<Criterios>();
		ArrayList<List<Integer>> mttos  = new ArrayList<List<Integer>>();
		
		
		Date fechaactual = Date.valueOf(LocalDate.now());
		Date fechapasada = Date.valueOf(LocalDate.now().minusYears(1));
		
		
		for(int ep = 0;ep<equipos.size();ep++) {
			criteriosequipos.add(CriteriosService.findcbyequipoanddate(equipos.get(ep).getId_Equipo(), fechapasada, fechaactual));
			
			List<Integer> couneequipo = new ArrayList<Integer>();
			couneequipo.add(ReporteService.numcorrectivobyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numpredictivobyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numpreventivobyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numotrobyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfdesgastebyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfopindebidabyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfcausaexbyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfaccesoriosbyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfdesconocidobyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfsinfallasbyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numfotrosbyequipo(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			couneequipo.add(ReporteService.numtotalreportesfecha(fechapasada, fechaactual, equipos.get(ep).getId_Equipo()));
			mttos.add(couneequipo);
			if(CriteriosService.findLastEvaluation(equipos.get(ep).getId_Equipo())!=null){
				criteriosultimos.add(CriteriosService.findOne(Long.valueOf(CriteriosService.findLastEvaluation(equipos.get(ep).getId_Equipo()))));
			}
			else {
				criteriosultimos.add(null);
			}
			
		}
		model.addAttribute("fecha1",fechapasada);
		model.addAttribute("fecha2",fechaactual);
		model.addAttribute("equipos",equipos);
		model.addAttribute("historialevanual",criteriosequipos);
		model.addAttribute("ultimoscriterios",criteriosultimos);
		model.addAttribute("indicadoresequipo",mttos);
		model.addAttribute("tipoequipo", Tipo_equipoService.findOne(id));
		return "ultimosresultadosevtecnica";
	}
	
	
	

}
