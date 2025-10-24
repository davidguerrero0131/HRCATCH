package com.HUSRTbdBiomedica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IRepuestosService;

@Controller
@RequestMapping
public class RepuestosController {

	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IRepuestosService RepuestosService;
	
	
	
}
