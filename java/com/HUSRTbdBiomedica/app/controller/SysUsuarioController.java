package com.HUSRTbdBiomedica.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Authority;
import com.HUSRTbdBiomedica.app.entity.Backup;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.SystemBaja;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.SysEquipoExcelExporter;
import com.HUSRTbdBiomedica.service.SysMantenimientoExcelExporter;
import com.lowagie.text.DocumentException;
import com.HUSRTbdBiomedica.service.CustomUserDetails;
import com.HUSRTbdBiomedica.service.EquipoExcelExporter;
import com.HUSRTbdBiomedica.service.IBackupService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ISystemBajaService;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.HUSRTbdBiomedica.service.ReporteExcelExporter;


@Controller
@RequestMapping
public class SysUsuarioController {

	@Autowired
	private ISystemEquipoService SystemEquipoService;
	
	@Autowired 
	private IUsuarioService UsuarioService;
	
	@Autowired
    private IServicioService ServicioService;
	
	@Autowired
	private ISystemBajaService SystemBajaService;
   
    @Autowired
    private ITipo_equipoService Tipo_equipoService;
	
    @Autowired
    private ISystemMantenimientoService SystemMantenimientoService;
    
    @Autowired
    private IBackupService backupService;
    
	@GetMapping("/sysexport")
    public void exportToExcel(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = sysinventarios.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<SystemEquipo> equipos = SystemEquipoService.ListSystemEquipo();
    	
    	SysEquipoExcelExporter excelExporter = new SysEquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
	@GetMapping("/sysexportplan")
    public void exportPlanToExcel(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = sysplan.xlsx";
    	response.setHeader(headerKey, headerValue);
    	LocalDate hoy = LocalDate.now();
    	List<SystemEquipo> equipos = SystemEquipoService.listbyAno(Date.valueOf((hoy.minusYears(1L))));
    	
    	SysEquipoExcelExporter excelExporter = new SysEquipoExcelExporter(equipos);
    	excelExporter.exportsysplan(response);
    }
	
    @GetMapping("/exportSysinvadmin")
    public void exportinventarioToExcel(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventario.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<SystemEquipo> equipos = SystemEquipoService.getAllActivo();
    	SysEquipoExcelExporter excelExporter = new SysEquipoExcelExporter(equipos);
    	excelExporter.exportadmin(response);
    }
    
	@GetMapping("/sysusuarios")
	public String Usuarios(Model model) {
    	LocalDate hoy = LocalDate.now();
    	LocalDate past = hoy.minusMonths(2);
    	LocalDate future = hoy.plusMonths(2);
    	LocalDate rptopast = hoy.minusDays(14);
    	
    	Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
		Object[] roles = auth.getAuthorities().toArray();
	   
	    
		if(roles[0].toString().equals("EDITOR")||roles[0].toString().equals("USER")) {
			return "producto";
		}
    	
    	List<Usuario> alluser = UsuarioService.ListarUsuarios();
    	List<Usuario> sysusers = new ArrayList<Usuario>();
    	List<Authority> rols = UsuarioService.userauth();
    	for(int i = 0;i<alluser.size();i++) {
    		if(alluser.get(i).getAuthority().size()>0) {
    			sysusers.add(alluser.get(i));
    		}
    	}
    	List<Usuario> systemuser = new ArrayList<Usuario>();
    	for(int j = 0;j<sysusers.size();j++) {
    		if(rols.get(j).getAuthority().equals("SYSADMIN")||rols.get(j).getAuthority().equals("SYSUSER")) {
    			systemuser.add(sysusers.get(j));
    		}
    	}

    	List<Reporte> reportes = null;
    	
    	model.addAttribute("servicios", ServicioService.ListServicio());
    	model.addAttribute("repuestos",reportes);
    	model.addAttribute("usuarios", systemuser);
    	model.addAttribute("tipos",Tipo_equipoService.findbySystem());
    	model.addAttribute("sysBajas", SystemBajaService.listBajas());
    	model.addAttribute("sysInactivos", SystemEquipoService.getAllInactivos());
    	
		return "sysusuarios";
	}
	@PostMapping("/asignsystem")
	public String asignsys(Model model) {
		List<Usuario> usuarios = UsuarioService.tecnicoSistemas();
		List<SystemEquipo> equipos = SystemEquipoService.orderbyTiponid();
		int j = 0;
		for(int i = 0;i<equipos.size();i++) {
			equipos.get(i).setUsuario(usuarios.get(j));
			SystemEquipoService.save(equipos.get(i));
			j+=1;
			if(j>=usuarios.size()) {
				j=0;
			}
		}
		return "sysusuarios";
	}
	@PostMapping("/exportsysmovs")
    public void exportreports(HttpServletResponse response,
    		@RequestParam(value="fecha_inicial",defaultValue = "2022-02-01")String fecha_inicial,
			@RequestParam(value="fecha_final",defaultValue = "2022-03-01")String fecha_final) throws IOException{
 
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = reportes.xlsx";
    	LocalDate f1 = LocalDate.parse(fecha_inicial);
    	LocalDate f2 = LocalDate.parse(fecha_final);
    	List <SystemMantenimiento> movs = SystemMantenimientoService.ReporteMovimientos(Date.valueOf(f1), Date.valueOf(f2));
    	if(movs!=null) {
    		
    		response.setHeader(headerKey, headerValue);
        	SysMantenimientoExcelExporter excelExporter  = new SysMantenimientoExcelExporter(movs);
        	excelExporter.export(response);
    		
    	}
    	else {
    		movs = SystemMantenimientoService.Entregas();
    		response.setHeader(headerKey, headerValue);
    		SysMantenimientoExcelExporter excelExporter  = new SysMantenimientoExcelExporter(movs);
        	excelExporter.export(response);
    	}
    	
    }

	/*Usuaruios Visitantes*/
	@GetMapping("/sysvisitante")
	public String ListServiciosSystem(Model model) {
		model.addAttribute("servicios",ServicioService.ListServicioSys());
		return "sysvisitante";
	}
	
	
	/*Usuaruios backups*/
	@GetMapping("/newbackup")
	public String newBackupsys(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		Backup backup = new Backup();
		LocalDate hoy = LocalDate.now();
		String fechaActual = hoy +"";
		
		model.addAttribute("usuario", user);
		model.addAttribute("fechaactual", fechaActual);
		model.addAttribute("sysBackup", backup);
		
		return "sysnuevobackup";
	}
	
	@PostMapping("/nuevobackupsys/{idUser}")
	public String nuevoBackupsys(@PathVariable(value = "idUser") Long idUser,@ModelAttribute("sysBackup") Backup backup,
			BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) throws ParseException {
		Usuario usuario = UsuarioService.findOne(idUser);
		backup.setUsuario(usuario);
		LocalDate todaysDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(todaysDate);
		backup.setFecha_backup(date);
		if(backup.getCaso_glpi().equals("")) {
			backup.setCaso_glpi("N/A");
		}
		backupService.addBackup(backup);
		return "redirect:/ListaBackups";
	}
	
	
	@GetMapping("/ListaBackups")
	public String ListBackup(Model model) {
		model.addAttribute("backups",backupService.getAllBackup());
		return "vistabackups";
	}
	
	@GetMapping("/formatobackup/{id}")
	public void downloadReportBackup(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();
		Backup backup = backupService.findById(id);
		byte[] pdfReport = generator.getBackpusPDFSpec(backup).toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoBackup" + id;
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}
	
}
