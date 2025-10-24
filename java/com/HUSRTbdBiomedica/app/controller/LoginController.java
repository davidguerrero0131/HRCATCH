package com.HUSRTbdBiomedica.app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
@Controller
public class LoginController {
	@GetMapping("/signinHUSRT")
	public String login(@RequestParam(value="error",required=false)String error,
						@RequestParam(value="logout",required=false)String logout,
						Model model, Principal principal, RedirectAttributes flash) {
		if(principal!=null) {
			flash.addFlashAttribute("mensaje", "Log in")
            .addFlashAttribute("clase", "success");
			return "redirect:/producto";
		}
		if(error!=null) {
	
			flash
			.addFlashAttribute("mensaje", "Agregado correctamente")
            .addFlashAttribute("clase", "success");
		
		}
		if(logout!=null) {
	
			flash
			.addFlashAttribute("mensaje", "Sesión cerrada con exito")
            .addFlashAttribute("clase", "success");
		}
		return "signinHUSRT";
	}

}
