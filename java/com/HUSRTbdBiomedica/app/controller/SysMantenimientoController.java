package com.HUSRTbdBiomedica.app.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.CustomUserDetails;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemHoja_vidaService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ISystemRepuestosService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping
public class SysMantenimientoController {

	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IUsuarioService UsuarioService; 
	
	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private ISystemRepuestosService SystemRepuestosService;
	
	@Autowired
	private ISystemMantenimientoService SysMantenimientoService;
	
	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired
	private ISystemHoja_vidaService SystemHoja_vidaService;
	
	@GetMapping("/sysnuevaentega/{id}")
	public String sysnuevaentega(@PathVariable(value = "id") Long id,
			Map<String,Object>map,Model model,
            RedirectAttributes flash) {
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		Long num = SysMantenimientoService.lastMttosysid()+1L;
		SystemMantenimiento mtto = new SystemMantenimiento();
		Time defaulthour = Time.valueOf(LocalTime.now());
		map.put("mtto",mtto);
		model.addAttribute("equipo", equipo);
		model.addAttribute("servicio",equipo.getServicio().getNombre_servicio());
		model.addAttribute("ubicacion",equipo.getUbicacion()+' '+equipo.getUbicacion_especifica());
		model.addAttribute("num",num);
		model.addAttribute("fecha", LocalDate.now());
		model.addAttribute("horallamado",defaulthour);
		model.addAttribute("horainicio",defaulthour);
		model.addAttribute("horaterminacion",defaulthour);
		model.addAttribute("servicios",ServicioService.ListServicio());
		model.addAttribute("equipos",SystemEquipoService.listnmmsp());
		return "sysnuevaentrega";
	}
	@PostMapping("/sysnuevaentega/{id}")
	public String sysnuevaentegasave(@PathVariable(value = "id") Long id,
			Map<String,Object>map,Model model,
			@RequestParam(value="fecha")String fecha,
			@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
			@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@RequestParam(value = "servicio",defaultValue = "NULL")Long ids,
    		@RequestParam(value = "sysequipo",defaultValue = "NULL")String idsys,
    		@RequestParam(value = "ubi",defaultValue = "NULL")String ubi,
    		@RequestParam(value = "ubiesp",defaultValue = "NULL")String ubies,
    		@RequestParam(value="past_ubication",defaultValue = "NULL")String pastservice,
    		@Valid SystemMantenimiento mtto,
    								  BindingResult result,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
		LocalDate fechareporte = LocalDate.parse(fecha.trim());
    	Date fechaas = Date.valueOf(fechareporte);
		Time hl = Time.valueOf(LocalTime.parse(hora_llamado.trim()));
		Time hi = Time.valueOf(LocalTime.parse(hora_inicio.trim()));
		Time hf = Time.valueOf(LocalTime.parse(hora_finalizacion.trim()));
		mtto.setNumero_reporte(String.valueOf(SysMantenimientoService.lastMttosysid()+1L));
		mtto.setFecha(fechaas);
		mtto.setHora_llamado(hl);
		mtto.setHora_inicio(hi);
		mtto.setHora_terminacion(hf);
		mtto.setEntrega(true);
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		mtto.setEquipo(equipo);
		mtto.setMPHardware(false);
		mtto.setMPSoftware(false);
		mtto.setRutahardware(null);
		mtto.setRutasoftware(null);
		Authentication auth = SecurityContextHolder
			    .getContext()
			    .getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		mtto.setUsuario(user);
		if(!idsys.equals("NULL")) {
			mtto.setRutasoftware(idsys);
			SystemEquipo equiporetirado = SystemEquipoService.findOne(Long.valueOf(idsys));
			equiporetirado.setServicio(ServicioService.findOne(45L));
			equiporetirado.setUbicacion("BODEGA SISTEMAS");
			equiporetirado.setUbicacion_especifica("BODEGA SISTEMAS");
			equiporetirado.setUbicacion_anterior(equipo.getServicio().getNombre_servicio()+" "+equipo.getUbicacion_especifica());
			SystemEquipoService.save(equiporetirado);
		}
		
		if(ubi.equals("NULL")||ubies.equals("NULL")||pastservice.equals("NULL")) {
			flash.addAttribute("noubi", "Ingrese la nueva ubicación del equipo");
			
			return "redirect:/sysnuevaentega/"+String.valueOf(id);
		}
		else {
			equipo.setServicio(ServicioService.findOne(ids));
			equipo.setUbicacion(ubi);
			equipo.setUbicacion_especifica(ubies);
			equipo.setUbicacion_anterior(equipo.getServicio().getNombre_servicio()+" "+equipo.getUbicacion_especifica());
			SystemEquipoService.save(equipo);
			mtto.setObservacioness(pastservice);
			
		}
		SysMantenimientoService.save(mtto);
		return "redirect:/sysreportes/"+String.valueOf(id);
	}
	@GetMapping("/downlandEntregaSYS/{id}")
    public void downloadReportFormat(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        
        SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    	SystemHoja_vida hv = SystemHoja_vidaService.findHv(mtto.getEquipo().getId_Sysequipo());
    	SystemEquipo equipo = null;
    	List<SystemRepuestos> repuestos = SystemRepuestosService.listrepuestosbyequipo(mtto.getEquipo().getId_Sysequipo());
    	if(mtto.getRutasoftware()!=null) {
    		equipo = SystemEquipoService.findOne(Long.valueOf(mtto.getRutasoftware()));
    	}
        byte[] pdfReport = null;

        	pdfReport = generator.getEntregaSysPDF(mtto,hv,equipo).toByteArray();
        
        	

        String mimeType =  "application/pdf";
        String namefile = mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo()+"_"+mtto.getEquipo().getSerie()+"_"+"formatoHVSys2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	@GetMapping("/downlandMttoSYS/{id}")
    public void downlandMttoSYS(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        
        SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    	SystemHoja_vida hv = SystemHoja_vidaService.findHv(mtto.getEquipo().getId_Sysequipo());
    	SystemEquipo equipo = null;
    	List<Protocolo_preventivo> protocoloh = Protocolo_preventivoService.protocologeneral(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo());
		List<Protocolo_preventivo> protocolos = Protocolo_preventivoService.protocoloexcepcion(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo());
		
		if(mtto.getRutasoftware()!=null && !mtto.getRutasoftware().equals("NA") && !mtto.getRutasoftware().equals("")) {
    		equipo = SystemEquipoService.findOne(Long.valueOf(mtto.getRutasoftware()));
    	}
        byte[] pdfReport = null;
        if(mtto.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 121L) {

        	pdfReport = generator.getMPHsysPDF(mtto,protocolos,protocoloh).toByteArray();
            
        }else {
        	pdfReport = generator.getMSPHsysPDF(mtto,protocolos,protocoloh).toByteArray();
        }
        	

        String mimeType =  "application/pdf";
        String namefile = mtto.getNumero_reporte()+'_'+mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo()+"_"+mtto.getEquipo().getSerie()+"_"+"formatoHVSys2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	@GetMapping("/sysnuevomtto/{id}")
	public String sysnuevomtto(@PathVariable(value = "id") Long id,
			Map<String,Object>map,Model model,
            RedirectAttributes flash) {
		
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		List<Protocolo_preventivo> protocoloh = Protocolo_preventivoService.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
		List<Protocolo_preventivo> protocolos = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
		
		Long num = SysMantenimientoService.lastMttosysid()+1L;
		SystemMantenimiento mtto = new SystemMantenimiento();
		map.put("mtto",mtto);
		Time defaulthour = Time.valueOf(LocalTime.now());
		model.addAttribute("protocoloh", protocoloh);
		model.addAttribute("protocolos", protocolos);
		model.addAttribute("equipo", equipo);
		model.addAttribute("num",num);
		model.addAttribute("fecha", LocalDate.now());
		model.addAttribute("horallamado",defaulthour);
		model.addAttribute("horainicio",defaulthour);
		model.addAttribute("horaterminacion",defaulthour);
		return "sysnuevomtto";
	}
	@PostMapping("/sysnuevomtto/{id}")
	public String savenuevomtto(@PathVariable(value = "id") Long id,
			Map<String,Object>map,Model model,
			@RequestParam(value="fecha")String fecha,
			@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
			@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@RequestParam(value = "hcumplimientos",defaultValue = "NULL")String hcumplimientos,
    		@RequestParam(value = "scumplimientos",defaultValue = "NULL")String scumplimientos,
    		
    		@RequestParam(value = "cedulareal",defaultValue = "NULL")String cedulareal,
    		@RequestParam(value = "telefonoreal",defaultValue = "NULL")String telefonoreal,
    		@RequestParam(value = "cargoreal",defaultValue = "NULL")String cargoreal,
    		@RequestParam(value = "cargorecep",defaultValue = "NULL")String cargorecep,
    		@RequestParam(value = "daterecep",defaultValue = "NULL")String daterecep,
    		@RequestParam(value = "dependenciacep",defaultValue = "NULL")String dependenciacep,
    		@RequestParam(value = "statede",defaultValue = "NULL")String statede,
    		@Valid SystemMantenimiento mtto,
    								  BindingResult result,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
		
		LocalDate fechareporte = LocalDate.parse(fecha.trim());
    	Date fechaas = Date.valueOf(fechareporte);
		Time hl = Time.valueOf(LocalTime.parse(hora_llamado.trim()));
		Time hi = Time.valueOf(LocalTime.parse(hora_inicio.trim()));
		Time hf = Time.valueOf(LocalTime.parse(hora_finalizacion.trim()));
		mtto.setFecha(fechaas);
		mtto.setHora_llamado(hl);
		mtto.setHora_inicio(hi);
		mtto.setHora_terminacion(hf);
		mtto.setRutaentrega(null);
		mtto.setNumero_reporte(String.valueOf(SysMantenimientoService.lastMttosysid()+1L));
		mtto.setEntrega(false);
		mtto.setEquipo(SystemEquipoService.findOne(id));
		Authentication auth = SecurityContextHolder
			    .getContext()
			    .getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		mtto.setUsuario(user);
		System.out.println(statede);
		if(hcumplimientos.equals("NULL") && scumplimientos.equals("NULL") && statede.equals("NULL")) {
			
			mtto.setMPHardware(false);
			mtto.setMPSoftware(false);
			mtto.setRutinah(null);
			mtto.setRutinas(null);
			mtto.setRutahardware(null);
			mtto.setDano(false);
		
		}
		else if(!statede.equals("NULL")){
			mtto.setMPHardware(false);
			mtto.setMPSoftware(false);
			mtto.setDano(true);
			mtto.setRutahardware(null);
			mtto.setRutinah(null);
			mtto.setRutinas(null);
			
			mtto.setRutasoftware(cedulareal+";"+telefonoreal+";"+cargoreal+";"+cargorecep+";"+daterecep+";"+dependenciacep+";"+statede);
		}
		else {
			mtto.setMPHardware(true);
			mtto.setMPSoftware(true);
			mtto.setDano(false);
			mtto.setRutinah(hcumplimientos);
			mtto.setRutinas(scumplimientos);
		}

		SysMantenimientoService.save(mtto);
		return "redirect:/sysreportes/"+String.valueOf(id);
	}
	@GetMapping("/downlandSysDanos/{id}")
	public void downlandSysDanosFormat(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();
        
        SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    
        byte[] pdfReport = generator.getDanosPDF(mtto).toByteArray();


        String mimeType =  "application/pdf";
        String namefile = mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo()+"_"+mtto.getEquipo().getSerie()+"_"+"DanosSys2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
	}
	@GetMapping("/downlandCorrectivoSYS/{id}")
    public void downloadCorrectivoFormat(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        
        SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    
        byte[] pdfReport = generator.getCorrectivoPDF(mtto).toByteArray();


        String mimeType =  "application/pdf";
        String namefile = mtto.getEquipo().getTipo_equipo().getNombre_Tipo_equipo()+"_"+mtto.getEquipo().getSerie()+"_"+"formatoCorrectivoSys2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	@GetMapping(value = "/visualpdfentrega/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfentrega(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
		SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    
    	File file = new File(mtto.getRutaentrega());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
	@GetMapping(value = "/visualpdfsysmtto/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfmttosys(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
		SystemMantenimiento mtto = SysMantenimientoService.findOne(id);
    
    	File file = new File(mtto.getRutahardware());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
}
