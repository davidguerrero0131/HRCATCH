package com.HUSRTbdBiomedica.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.Validacion;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IValidacionService;
import com.HUSRTbdBiomedica.service.UploadFileService;

@Controller
@RequestMapping
public class ValidacionController {

	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private IValidacionService ValidacionService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	
	@GetMapping(value = "/visualpdfvalidacion/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfval(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
		Validacion validacion = ValidacionService.findOne(id);

    	File file = new File(validacion.getCertificado());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
	@GetMapping("/nuevavalidacion/{id}")
	public String nuevaval(Model model,
			@PathVariable(value="id") Long id,
			Map<String,Object>map,RedirectAttributes flash) {
		
		Validacion val = ValidacionService.findOne(id);
		map.put("validacion", val);
		
		model.addAttribute("fechacal", LocalDate.now());
		model.addAttribute("fecharec", LocalDate.now());
		
		return "nuevavalidacion";
	}
	@PostMapping("/nuevavalidacion/{id}")
	public String guardarval(@PathVariable Long id,@RequestParam(value="date")String fecha,
    		@RequestParam("file") MultipartFile file,
    		@Valid Validacion validacion,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) throws IOException {
		Validacion val = ValidacionService.findOne(id);
		
		String validaciones_folder = "./src/main/resources/validaciones/";
		
		uploadFileService.saveValidacion(file,id);
		
		val.setCondiciones(validacion.getCondiciones());
		val.setAprobado_por(validacion.getAprobado_por());
		val.setFecha_proceso(Date.valueOf(LocalDate.parse(fecha)));
		val.setNumero_certificado(validacion.getNumero_certificado());
		val.setRealizado_por(validacion.getRealizado_por());
		val.setEmpresa(validacion.getEmpresa());
		
		val.setCertificado(validaciones_folder+id+ file.getOriginalFilename());

		ValidacionService.save(val);
		status.setComplete();
		
		return "redirect:/procesoscalval";
	}
}
