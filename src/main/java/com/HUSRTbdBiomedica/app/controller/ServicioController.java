package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.ObjetoCompuesto;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemHoja_vidaService;

@Controller
@SessionAttributes("servicio")
@RequestMapping
public class ServicioController {

	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired
	private ISystemHoja_vidaService SystemHoja_vidaService;
	
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@GetMapping("/periodicidad")
	public String ReportesporPeriodicidad(Model model) {
		model.addAttribute("listaseries",EquipoService.listarseries());
		
		return "periodicidad";
	}
	@GetMapping("/clasificacionDHServicio")
	public String ListServicios(Model model) {
		
		model.addAttribute("servicios",ServicioService.ListServicio());
		return "clasificacionDHServicio";
	}
	@GetMapping("/servicio/{id}")
	public String ServicioEquipos(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
		List<Equipo> equipos = ServicioService.findEquiposbyServicio(id);
		List<SystemEquipo> sysequipos = ServicioService.listSystemEquiposbyServicio(id);
		List<SystemHoja_vida> syshoja_vida = new ArrayList<SystemHoja_vida>();
		List<Hoja_vida> hoja_vida = new ArrayList<Hoja_vida>();
		for(int eq = 0;eq<equipos.size();eq++) {
			hoja_vida.add(Hoja_vidaService.findHVbyEquipo(equipos.get(eq).getId_Equipo()));
		}
		for(int se = 0;se<sysequipos.size();se++) {
			syshoja_vida.add(SystemHoja_vidaService.findHv(sysequipos.get(se).getId_Sysequipo()));
		}
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",equipos);
        model.addAttribute("sysequipos",sysequipos);
        model.addAttribute("syshoja_vida",syshoja_vida);
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicio(id));
        model.addAttribute("hojas_vida",hoja_vida);
		
		return "equiposservicio";
	}
	
	
	
	@GetMapping("/serviciosys/{id}")
	public String ServicioEquiposInvitadoSys(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
		List<SystemEquipo> sysequipos = null;
		if(id == 0) {
			sysequipos = SystemEquipoService.getAllActivo();
		}else {
			sysequipos = ServicioService.listSystemEquiposbyServicio(id);
		}
		List<SystemHoja_vida> syshoja_vida = new ArrayList<SystemHoja_vida>();
		List<Hoja_vida> hoja_vida = new ArrayList<Hoja_vida>();
		for(int se = 0;se<sysequipos.size();se++) {
			syshoja_vida.add(SystemHoja_vidaService.findHv(sysequipos.get(se).getId_Sysequipo()));
		}
		
		String nombreServicio = "Todos los Equipos";
		String ubicacionServicio = "";
		if(servicio != null){
			nombreServicio = servicio.getNombre_servicio();
			ubicacionServicio = servicio.getUbicacion_servicio();
		}
    	model.addAttribute("nombreservicio",nombreServicio);
    	model.addAttribute("ubicacionservicio",ubicacionServicio);
        model.addAttribute("sysequipos",sysequipos);
        model.addAttribute("syshoja_vida",syshoja_vida);
        model.addAttribute("hojas_vida",hoja_vida);
		
		return "sysequiposervicio";
	}
	
	
	@GetMapping("/frecuenciamtto")
	public String getfrecuenciamtto(
            Model model,
            RedirectAttributes flash) {
		
		model.addAttribute("servicios", ServicioService.ListServicio());
		return "frecuenciamtto";
	}
	@PostMapping("/frecuenciamtto")
	public String setfrecuenciamtto(Model model, RedirectAttributes flash,
			@RequestParam(value="total")String Total) {

		ArrayList<String> fmttoser = new ArrayList<String>(Arrays.asList(Total.split(",")));
		List<Servicio> servicios = ServicioService.ListServicio();
		for(int s = 0; s<servicios.size();s++) {
			servicios.get(s).setFmpp(Integer.valueOf(fmttoser.get(s)));
			ServicioService.save(servicios.get(s));
		}
		return "producto";
	}
	
	@GetMapping("/todoslosservicios")
	public String listarServicios(Model model) {
		model.addAttribute("servicios",ServicioService.ListServicio());
		
		model.addAttribute("numeroE",ServicioService.countequiposbyServicio());
		
		return "todoslosservicios";
	}
	
	@GetMapping("/serviciostrimestrales")
	public String listarServicioscuatri(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(3));	
		return "serviciostrimestrales";
	}
	
	@GetMapping("/servicioscuatrimestrales")
	public String listarServiciostri(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(4));
		return "servicioscuatrimestrales";
	}
	
	@GetMapping("/servicioscomodatos")
	public String listarServiciosComodatos(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.getServiciosConComodatos());
		return "servicioscomodatos";
	}
	
	@GetMapping("/serviciossemestrales")
	public String listarServiciossem(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(2));
		return "serviciossemestrales";
	}
	
	@GetMapping("/serviciosanuales")
	public String listarServiciosan(
			 Model model,
			 RedirectAttributes flash) {
		model.addAttribute("servicios",ServicioService.findServicebyp(1));
		return "serviciosanuales";
	}
	
	@GetMapping(value = "/visualizacionequiposservicio/{id}")
    public String showequiposbyservicio(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServicio(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicio(id));
        
        ArrayList<Equipo> listEquipo = new ArrayList<>(ServicioService.findEquiposbyServicio(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}

		}
        
        model.addAttribute("equipoHv", listEquipoHv);
    	
        return "visualizacionequiposservicio";
        
        
    }
	
	@GetMapping(value = "/visualizacionservicioanual/{id}")
    public String showequiposbyservicioanual(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServicioanuales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(1, id));
        
		ArrayList<Equipo> listEquipo = new ArrayList<>(ServicioService.findEquiposbyServicioanuales(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
        return "visualizacionservicioanual";
    }
	
	
	@GetMapping(value = "/visualizacionserviciocomodatos/{id}")
    public String showequiposbyserviciocomodatos(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServicioanuales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(1, id));
        
		ArrayList<Equipo> listEquipo = new ArrayList<>(EquipoService.getComodatosPorServicio(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
        return "visualizacionservicioComodatos";
    }
	
	
	
	@GetMapping(value="/visualizacionserviciosemestral/{id}")
	public String showequiposbyserviciosemestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciosemestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(2, id));
        
		ArrayList<Equipo> listEquipo = new ArrayList<>(ServicioService.findEquiposbyServiciosemestrales(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
		return "visualizacionserviciosemestral";
	}
	@GetMapping(value="/visualizacionserviciocuatrimestral/{id}")
	public String showequiposbyserviciocuatrimestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciocuatrimestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(4, id));
        
		ArrayList<Equipo> listEquipo = new ArrayList<>(ServicioService.findEquiposbyServiciosemestrales(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
		return "visualizacionserviciocuatrimestral";
	}
	@GetMapping(value="/visualizacionserviciotrimestral/{id}")
	public String showequiposbyserviciotrimestral(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);
    	model.addAttribute("nombreservicio",servicio.getNombre_servicio());
    	model.addAttribute("ubicacionservicio",servicio.getUbicacion_servicio());
        model.addAttribute("equiposservice",ServicioService.findEquiposbyServiciotrimestrales(id));
        model.addAttribute("numequipos",ServicioService.countEspecificbyServicionP(3, id));
        
		ArrayList<Equipo> listEquipo = new ArrayList<>(ServicioService.findEquiposbyServiciosemestrales(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
		return "visualizacionserviciotrimestral";
	}
}
