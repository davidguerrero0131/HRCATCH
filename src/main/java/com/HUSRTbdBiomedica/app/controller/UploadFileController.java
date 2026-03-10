package com.HUSRTbdBiomedica.app.controller;

import java.io.IOException;
import java.lang.annotation.Documented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Documento;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
import com.HUSRTbdBiomedica.app.entity.TipoDocumento;
import com.HUSRTbdBiomedica.service.IDocumentoService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ITipoDocumentoService;
import com.HUSRTbdBiomedica.service.UploadFileService;

@Controller
@RequestMapping
public class UploadFileController {
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private ITipoDocumentoService tipoDocumentoService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IEquipoService equipoService;
	
	@Autowired
	private IDocumentoService documentoService;
	
	@Autowired
	private ISystemMantenimientoService SystemMantenimientoService;
	
	@PostMapping(value = "/uploaddocument/{id}")
	public String uploadDocument(@PathVariable(value="id") Long idEquipo, @RequestParam("file") MultipartFile file, @RequestParam("idtipodocumento") int idTipodocumento,RedirectAttributes attributes) throws IOException {
		TipoDocumento tipodocumento = tipoDocumentoService.getTipoDocumento((long) idTipodocumento); 
		Equipo equipo = equipoService.findOne(idEquipo);
		Documento documento = new Documento();
		String rutaDocumento = uploadFileService.saveDocumentBiomedica(file, tipodocumento, idEquipo);
		documento.setNombreDocumento(tipodocumento.getNombreTipoDocumento());
		documento.setRutaDocumento(rutaDocumento);
		documento.setTipoDocumento(tipodocumento);
		documento.setEquipo(equipo);
		documento.setActivo(true);
		documentoService.addDocumento(documento);
		
		return "redirect:/hvdocumentosequipo/" + idEquipo;
	}
	
	
	@PostMapping(value = "/uploadreport/{id}")
	public String uploadFile(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
		
		String upload_folder = "./src/main/resources/files/";		
		Reporte reporte = ReporteService.findOne(id);
		reporte.setRutapdf(upload_folder + String.valueOf(id)+ file.getOriginalFilename());
		
		uploadFileService.saveFile(file,id);
		ReporteService.save(reporte);
		
		if(!reporte.getEquipo().getId_Equipo().equals(11111L)) {
			return "redirect:/visualizacionreportes/"+reporte.getEquipo().getId_Equipo();
		}
		else {
			return "redirect:/visualizacionotrosreportes";
		}
		
	}
	
	@PostMapping(value = "/uploadsysreport/{id}")
	public String uploadsysFile(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
		
		String upload_folder = "./src/main/resources/sysfiles/";		
		SystemMantenimiento mtto = SystemMantenimientoService.findOne(id);
		mtto.setRutahardware(upload_folder + String.valueOf(id)+ file.getOriginalFilename());
	
		
		uploadFileService.savesysFile(file,id);
		SystemMantenimientoService.save(mtto);
		
		return "redirect:/sysreportes/"+mtto.getEquipo().getId_Sysequipo();
		
	}

	@PostMapping(value = "/uploadsysentrega/{id}")
	public String uploadsysentregaFile(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
		
		String upload_folder = "./src/main/resources/sysfiles/";		
		SystemMantenimiento mtto = SystemMantenimientoService.findOne(id);
		mtto.setRutaentrega(upload_folder + String.valueOf(id)+ file.getOriginalFilename());
	
		
		uploadFileService.savesysFile(file,id);
		SystemMantenimientoService.save(mtto);
		
		return "redirect:/sysreportes/"+mtto.getEquipo().getId_Sysequipo();
		
	}
}
