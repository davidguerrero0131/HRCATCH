package com.HUSRTbdBiomedica.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Responsable;
import com.HUSRTbdBiomedica.service.IResponsableService;


@Controller
@RequestMapping
public class ResponsableController {

	@Autowired
	private IResponsableService ResponsableService;
	
	@GetMapping("/nuevoresponsable")
	public String nuevoresponsable(Model model) {
		Responsable responsable = new Responsable();
		model.addAttribute("responsable",responsable);
		
		return "nuevoresponsable";
	}
	@PostMapping("/nuevoresponsable")
	public String guardaresponsable( @Valid Responsable responsable,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
		ResponsableService.save(responsable);		
		return "redirect:/usuarios";
	}
	@GetMapping("/editresponsable/{id}")
	public String editresponsable(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Responsable responsable = ResponsableService.findOne(id);
		map.put("responsable", responsable);
		return "editresponsable";
	}
	@PostMapping("/editresponsable/{id}")
	public String guardaeditresponsable(@PathVariable(value="id") Long id,
				@Valid Responsable responsable,
			  BindingResult result,
			  Model model,
			  RedirectAttributes flash,
			  SessionStatus status) {
		Responsable responedit = ResponsableService.findOne(id);
		responedit.setCalificacion(responsable.getCalificacion());
		responedit.setExterno(responsable.isExterno());
		responedit.setNombre_responsable(responsable.getNombre_responsable());
		ResponsableService.save(responedit);
		return "redirect:/usuarios";
	}
	
}
