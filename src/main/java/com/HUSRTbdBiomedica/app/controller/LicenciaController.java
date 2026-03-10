package com.HUSRTbdBiomedica.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

import com.HUSRTbdBiomedica.app.entity.Licencia;
import com.HUSRTbdBiomedica.service.IHospital_Service;
import com.HUSRTbdBiomedica.service.ILicenciaService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.UploadFileService;

@Controller
@RequestMapping
public class LicenciaController {

	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private IHospital_Service HospitalService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private ILicenciaService LicenciaService;
	
	@PostMapping(value = "/uploadlicencia/{id}")
	public String uploadFile(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
		
		String upload_folder = "./src/main/resources/licencias/";		
		Licencia licencia = LicenciaService.findOne(id);
		licencia.setRutaformato(upload_folder + String.valueOf(id)+ file.getOriginalFilename());
		
		uploadFileService.saveFile(file,id);
		LicenciaService.save(licencia);
		
		return "producto";
		
	}
	@GetMapping("/nuevalicencia")	
	public String nuevalicencia(
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {	
		Licencia licencia = new Licencia();	
		map.put("licencia",licencia);
		
		return "nuevalicencia";
	}
	@PostMapping("/nuevalicencia")
	public String guardarllamado(@RequestParam("file") MultipartFile file,
    		@Valid Licencia alicencia,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) throws IOException {
	
		String lastid = LicenciaService.lastIdLicencia();
		Long id = 1L;
		if(lastid != null) {
			id = Long.valueOf(lastid);
		}
		String upload_folder = "./src/main/resources/licencias/";	
		alicencia.setRutaformato(upload_folder+ String.valueOf(id)+ file.getOriginalFilename());
		uploadFileService.saveLicencias(file, id);
		alicencia.setHospital(HospitalService.findOne(1L));
		LicenciaService.save(alicencia);
		
		
		flash.addAttribute("realizado", "Licencia guardada con exito");
		
		return "redirect:/listlicencias";
	}
	@GetMapping("/listlicencias")	
	public String listlicencias(
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {	
		model.addAttribute("licencias", LicenciaService.ListLicencia());
		return "listlicencias";
	}
	
	@GetMapping(value = "/visualpdflicense/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdflicense(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
    	Licencia licencia = LicenciaService.findOne(id);
    	File file = new File(licencia.getRutaformato());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
}
