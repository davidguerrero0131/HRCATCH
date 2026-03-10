package com.HUSRTbdBiomedica.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemHoja_vidaService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ISystemRepuestosService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.HUSRTbdBiomedica.service.UploadFileService;
import com.lowagie.text.DocumentException;

@Controller
@SessionAttributes("systemhoja_vida")
@RequestMapping
public class SysHoja_vidaController {
	
	@Autowired
	private ISystemHoja_vidaService SystemHoja_vidaService;
	
	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired
	private ISystemRepuestosService SystemRepuestosService;
	
	@Autowired
	private ISystemMantenimientoService SystemMantenimientoService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	
	
	@GetMapping("/downlandHVSYS/{id}")
    public void downloadReportFormat(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        
        List<String> mttos = SystemMantenimientoService.liststrcorrbyequipo(id);
        List<SystemRepuestos> rptos = SystemRepuestosService.listrepuestosbyequipo(id);
        SystemHoja_vida hoja_vida = SystemHoja_vidaService.findHv(id);
    	
        byte[] pdfReport = generator.getHsysVPDF(mttos,rptos,hoja_vida).toByteArray();

        String mimeType =  "application/pdf";
        String namefile = hoja_vida.getSystemEquipo().getTipo_equipo().getNombre_Tipo_equipo()+"_"+hoja_vida.getSystemEquipo().getSerie()+"_"+"formatoHVSys2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	@GetMapping("/sysnuevahv/{id}")
	public String sysnuevahv(@PathVariable(value = "id") Long id,
			Map<String,Object>map,Model model,
            RedirectAttributes flash) {
	
		SystemHoja_vida systemhoja_vida = new SystemHoja_vida();
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		map.put("systemhoja_vida", systemhoja_vida);
	
	
		model.addAttribute("repuestos", SystemRepuestosService.listrespuestosavail());
		model.addAttribute("equipo",equipo);
		model.addAttribute("boughtdate",LocalDate.now());
		model.addAttribute("installdate",LocalDate.now());
		model.addAttribute("startdate",LocalDate.now());
		model.addAttribute("vctodate",LocalDate.now());
		return "sysnuevahv";
	}
	
	@GetMapping("/sysedithv/{id}")	
	public String sysedithv(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash){
		
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		SystemHoja_vida systemhoja_vida = SystemHoja_vidaService.findHv(id);
		System.out.println(systemhoja_vida.getId_Syshoja_vida());
		
		map.put("systemhoja_vida", systemhoja_vida);
		model.addAttribute("repuestos", SystemRepuestosService.listrespuestosavail());
		model.addAttribute("equipo",equipo);
		model.addAttribute("boughtdate",systemhoja_vida.getFecha_compra());
		model.addAttribute("installdate",systemhoja_vida.getFecha_instalacion());
		model.addAttribute("startdate",systemhoja_vida.getFecha_iniciooperacion());
		model.addAttribute("vctodate",systemhoja_vida.getFecha_vencimientogarantia());		
		return "sysedithv";
	}
	@PostMapping("/sysnuevahv/{id}")
	public String savenuevahvsys(@PathVariable(value = "id") Long id,
			@Valid SystemHoja_vida systemhoja_vida, BindingResult binRes,
			  Model model,
			  RedirectAttributes flash,
			  SessionStatus status,
			@RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="repuestos",defaultValue = "NULL")String accesorios,         
            
            @RequestParam(value="foto",defaultValue = "empty")MultipartFile foto) throws IOException {

		
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	LocalDate update = LocalDate.now();
    	Date fechaup = Date.valueOf(update);
    	SystemEquipo equipo = SystemEquipoService.findOne(id);
    	
    	
    	systemhoja_vida.setFecha_compra(fechac);
    	systemhoja_vida.setFecha_instalacion(fechai);
    	systemhoja_vida.setFecha_iniciooperacion(fechaop);
    	systemhoja_vida.setFecha_vencimientogarantia(fechav);
    	systemhoja_vida.setFecha_update(fechaup);
    	systemhoja_vida.setNivelinstitucion(String.valueOf(equipo.getHospital().getNivel()));
    	systemhoja_vida.setSystemEquipo(SystemEquipoService.findOne(id));
    	systemhoja_vida.setMac(systemhoja_vida.getMac());
    	systemhoja_vida.setObservaciones(systemhoja_vida.getObservaciones());
    	if(foto.getOriginalFilename()!="") {
    		uploadFileService.saveSysHV(foto, id);
        	
        	String Fotohv = "/images/sys/"+String.valueOf(id)+foto.getOriginalFilename();
        	systemhoja_vida.setFoto(Fotohv);
    	}
    	SystemRepuestos repuesto = null;
		if(!accesorios.equals("NULL")) {
			ArrayList<String> acc = new ArrayList<String>(Arrays.asList(accesorios.split(",")));
			
			for(int i= 0;i<acc.size();i++) {
				repuesto = SystemRepuestosService.findOne(Long.valueOf(acc.get(i)));
				repuesto.setEquipo(equipo);
				SystemRepuestosService.save(repuesto);
			}
		}
	
		SystemHoja_vidaService.save(systemhoja_vida);
		
    	
    	flash.addFlashAttribute("success","guardado");
		return "redirect:/sysreportes/"+String.valueOf(id);
	}
	@PostMapping("/sysedithv/{id}")
	public String saveedithvsys(@PathVariable(value = "id") Long id,
			@Valid SystemHoja_vida systemhoja_vida, BindingResult binRes,
			  Model model,
			  RedirectAttributes flash,
			  SessionStatus status,
			@RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="repuestos",defaultValue = "NULL")String accesorios,         
            
            @RequestParam(value="foto",defaultValue = "empty")MultipartFile foto) throws IOException {

		
		SystemHoja_vida hoja_vida = SystemHoja_vidaService.findHv(id);
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	LocalDate update = LocalDate.now();
    	Date fechaup = Date.valueOf(update);
    	SystemEquipo equipo = SystemEquipoService.findOne(id);
    	
    	hoja_vida.setAsignadoporgobernacion(systemhoja_vida.isAsignadoporgobernacion());
    	hoja_vida.setAsignadoporministerio(systemhoja_vida.isAsignadoporministerio());
    	hoja_vida.setComodato(systemhoja_vida.isComodato());
    	hoja_vida.setCompraddirecta(systemhoja_vida.isCompraddirecta());
    	hoja_vida.setContrato(systemhoja_vida.getContrato());
    	hoja_vida.setConvenio(systemhoja_vida.isConvenio());
    	hoja_vida.setCosto_compra(systemhoja_vida.getCosto_compra());
    	hoja_vida.setDepartamento(systemhoja_vida.getDepartamento());
    	hoja_vida.setDireccion(systemhoja_vida.getDireccion());
    	hoja_vida.setDisco_duro(systemhoja_vida.getDisco_duro());
    	hoja_vida.setDonado(systemhoja_vida.isDonado());
    	hoja_vida.setEmailinstitucion(systemhoja_vida.getEmailinstitucion());
    	hoja_vida.setIp(systemhoja_vida.getIp());
    	hoja_vida.setMunicipio(systemhoja_vida.getMunicipio());
    	hoja_vida.setNivelinstitucion(String.valueOf(equipo.getHospital().getNivel()));
    	hoja_vida.setNombre_usuario(systemhoja_vida.getNombre_usuario());
    	hoja_vida.setOffice(systemhoja_vida.getOffice());
    	hoja_vida.setProcesador(systemhoja_vida.getProcesador());
    	hoja_vida.setRam(systemhoja_vida.getRam());
    	hoja_vida.setSistema_operativo(systemhoja_vida.getSistema_operativo());
    	hoja_vida.setTelefonoinstitucion(systemhoja_vida.getTelefonoinstitucion());
    	hoja_vida.setTonner(systemhoja_vida.getTonner());
    	hoja_vida.setVendedor(systemhoja_vida.getVendedor());
    	hoja_vida.setTipoUso(systemhoja_vida.getTipoUso());
    	hoja_vida.setMac(systemhoja_vida.getMac());
    	hoja_vida.setObservaciones(systemhoja_vida.getObservaciones());
    	hoja_vida.setFecha_compra(fechac);
    	hoja_vida.setFecha_instalacion(fechai);
    	hoja_vida.setFecha_iniciooperacion(fechaop);
    	hoja_vida.setFecha_vencimientogarantia(fechav);
    	hoja_vida.setFecha_update(fechaup);
    	hoja_vida.setNivelinstitucion(String.valueOf(equipo.getHospital().getNivel()));
    	hoja_vida.setSystemEquipo(SystemEquipoService.findOne(id));
    	if(foto.getOriginalFilename()!="") {
    		uploadFileService.saveSysHV(foto, id);
        	
        	String Fotohv = "/images/sys/"+String.valueOf(id)+foto.getOriginalFilename();
        	hoja_vida.setFoto(Fotohv);
    	}
    	SystemRepuestos repuesto = null;
		if(!accesorios.equals("NULL")) {
			ArrayList<String> acc = new ArrayList<String>(Arrays.asList(accesorios.split(",")));
			
			for(int i= 0;i<acc.size();i++) {
				repuesto = SystemRepuestosService.findOne(Long.valueOf(acc.get(i)));
				repuesto.setEquipo(equipo);
				SystemRepuestosService.save(repuesto);
			}
		}
	
		//SystemHoja_vidaService.save(systemhoja_vida);
		
		SystemHoja_vidaService.save(hoja_vida);
    	flash.addFlashAttribute("success","guardado");
		return "redirect:/sysreportes/"+String.valueOf(id);
	}

}
