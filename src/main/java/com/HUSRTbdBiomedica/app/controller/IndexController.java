package com.HUSRTbdBiomedica.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.HUSRTbdBiomedica.service.IUsuarioService;

@Controller
public class IndexController {
	@Autowired
	private IUsuarioService UsuarioService;
	
	@GetMapping({"","/"})
	public String index(Model model) {
		model.addAttribute("bienvenido","hola");
		return "signinHUSRT";
	}
	
	@GetMapping("/forbidden")
	public String error403(Model model) {
		return "forbidden";
	}

}
