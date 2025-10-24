package com.HUSRTbdBiomedica.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.IndustrialEquipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.IIndustrialEquipoService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;

@Controller
@RequestMapping
public class IndEquipoController {
	
	@Autowired
	private IIndustrialEquipoService IndustrialEquipoService;
	
	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@GetMapping("/newindequipo")
	public String newindequipo(
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		
		IndustrialEquipo equipo  = new IndustrialEquipo();
		List<Servicio> servicios =  ServicioService.ListServicio();
		List<Tipo_equipo> tipos = Tipo_equipoService.ListTipo_equipo();
		model.addAttribute("servicios",servicios);	
		model.addAttribute("tipos", tipos);
		map.put("sysequipo", equipo);
		
		return "newindequipo";
	}
	@PostMapping("/newindequipo/{id}")
	public String savenewindequipo(@PathVariable(value="id")Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		
		return "redirect:/indequipostipo/"+id;
		
	}
	@GetMapping("/indequipostipo/{id}")
	public String listindequipostipo(@PathVariable(value="id")Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		
		List<IndustrialEquipo> equipos = IndustrialEquipoService.listIndEquiposbyTipo(id);
		model.addAttribute("equipos", equipos);
		
		return "listindequipos";
	}
	@GetMapping("/indequiposervicio/{id}")
	public String listindequiposervicio(@PathVariable(value="id")Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		List<IndustrialEquipo> equipos = IndustrialEquipoService.listIndEquiposbyServicio(id);
		model.addAttribute("equipos", equipos);
		return "listindequipos";
	}

}
