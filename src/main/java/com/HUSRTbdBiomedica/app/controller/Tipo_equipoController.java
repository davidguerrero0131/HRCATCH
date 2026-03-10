package com.HUSRTbdBiomedica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;

import java.util.List;

@Controller
@SessionAttributes("tipo_equipo")
@RequestMapping
public class Tipo_equipoController {
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	//@GetMapping(value = "/clasificacionDHTipoEquipos/{id}")
	//public String ListTipo_equipos(@PathVariable(value = "id")Long id,
	//								Model model,RedirectAttributes flash) {
		//List<Tipo_equipo> tipo_equipos = Tipo_equipoService.ListTipo_equipo();
		
	//	model.addAttribute("equiposportipo",Tipo_equipoService.findEquiposbyTipoEquipo(id));
		//model.addAttribute("tipo_equipos",Tipo_equipoService.ListTipo_equipo());		
	//	return "clasificacionDHTipoEquipos";
		
	//}

    @GetMapping(value = "/clasificacionDHTipoEquipos/{id}")
    public String ver(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
      

        model.addAttribute("equipos_tipo",Tipo_equipoService.findEquiposbyTipoEquipo(id));
       
        model.addAttribute("tipo_equiposs",Tipo_equipoService.ListTipo_equipo());
        return "clasificacionDHTipoEquipos";
    }
    
	@GetMapping("/clasificacionDHTipoEquipos")
	public String ListarTipo_equipos(Model model) {
		model.addAttribute("tipo_equipos",Tipo_equipoService.ListTipo_equipo());
		return "clasificacionDHTipoEquipos";
	}
	
	@GetMapping("/clasificacion")
	public String Info(Model model) {
		return "clasificacion";
	}
	
	@GetMapping("/clasificacionIntensidadUso")
	public String TablaIntensidadUso(Model model) {
		return "clasificacionIntensidadUso";
	}
	@GetMapping("/clasificacionctEdad")
	public String TablaEdad(Model model) {
		return "clasificacionctEdad";
	}
	@GetMapping("/clasificacionctNMantenimientos")
	public String TablaNmantenimientos(Model model) {
		return "clasificacionctNMantenimientos";
	}
	@GetMapping("/clasificacionTFueraServicio")
	public String TablaTFueraServicio(Model model) {
		return "clasificacionTFueraServicio";
	}
	@GetMapping("/clasificacionMUsuarioServicio")
	public String TablaMUsuarioServicio(Model model) {
		return "clasificacionMUsuarioServicio";
	}
	@GetMapping("/clasificacionctSeguridad")
	public String TablaSeguridad(Model model) {
		return "clasificacionctSeguridad";
	}
	@GetMapping("/clasificacionctGarantia")
	public String TablaGarantia(Model model) {
		return "clasificacionctGarantia";
	}

}
