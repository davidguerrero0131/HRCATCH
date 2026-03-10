package com.HUSRTbdBiomedica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemRepuestosService;

@Controller
@RequestMapping
public class SysRepuestosController {

	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired
	private ISystemRepuestosService SystemRepuestosService;
	
	@GetMapping("/sysnuevosrepuestos")
	public String cuadrarAnual(Model model) {
		
		return "sysrepuestos";
	}
}
