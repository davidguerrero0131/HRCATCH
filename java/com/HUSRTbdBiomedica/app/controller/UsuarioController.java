package com.HUSRTbdBiomedica.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Authority;
import com.HUSRTbdBiomedica.app.entity.Baja;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_otros;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Reporte_baja;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.BajaExcelExporter;
import com.HUSRTbdBiomedica.service.EquipoExcelExporter;
import com.HUSRTbdBiomedica.service.Hoja_vidaExcelExporter;
import com.HUSRTbdBiomedica.service.Hoja_vida_otrosExporter;
import com.HUSRTbdBiomedica.service.IAuthorityService;
import com.HUSRTbdBiomedica.service.IBajaService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IHoja_vida_otrosService;
import com.HUSRTbdBiomedica.service.IHospital_Service;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IReporte_bajaService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.ReporteExcelExporter;
import com.HUSRTbdBiomedica.service.Reporte_bajaExporter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Secured("ADMIN")
@Controller
@SessionAttributes({"usuario","authority"})
public class UsuarioController {

    @Autowired
    private IUsuarioService UsuarioService;
    
    @Autowired
    private IAuthorityService AuthorityService;
    
    @Autowired
    private IServicioService ServicioService;
   
    @Autowired
    private ITipo_equipoService Tipo_equipoService;
    
    @Autowired
    private IHoja_vidaService Hoja_vidaService;
    
    @Autowired
    private IReporteService ReporteService;
    
    @Autowired
    private IEquipoService EquipoService;
    
    @Autowired
    private IHospital_Service HospitalService;
    
    @Autowired
    private IBajaService BajaService;
    
    @Autowired
    private IReporte_bajaService Reporte_bajaService;
    
    @Autowired
    private IHoja_vida_otrosService Hoja_vida_otrosService;

    @GetMapping(value = "/infoUsuario/{id}")
    public String ver(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
        Usuario usuario = UsuarioService.findOne(id);

        if(usuario==null){
            flash.addFlashAttribute("error","El Usuario no existe en la Base de Datos");
            return "redirect:/listarUsuarios";
        }
        model.addAttribute("usuario",usuario);
        model.addAttribute("titulo","Detalle Usuario "+ usuario.getNombre());
        return "infoUsuario";
    }

    @GetMapping("/listarUsuarios")
    public String listar(Model model){
        model.addAttribute("titulo","Listar Usuarios");
        model.addAttribute("usuarios", UsuarioService.ListarUsuarios());
        return "listarUsuarios";
    }
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventarioTodos.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.getAllequiposF();
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
    
    @GetMapping("/exportequipossincomodatos")
    public void exportToExcelSinComodatos(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventarioPropios.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.getAllEquiposSinComodatos();
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
    
    @GetMapping("/exportcomodatos")
    public void exportToExcelComodatos(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventarioComodatos.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.getAllComodatos();
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
    
    @GetMapping("/exportinvadmin")
    public void exportinventarioToExcel(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventario.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.getAllequiposF();
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.exportadmin(response);
    }
    @GetMapping("/exportplan")
    public void exportplantoExcel(HttpServletResponse response) throws IOException{
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = plan.xlsx";
    	response.setHeader(headerKey, headerValue);
    	List<Equipo> equipos = EquipoService.ListEquipo();
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.exportPlan(response);
    }
    @GetMapping("/exportbajas")
    public void exportbajas(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = bajas.xlsx";
    	response.setHeader(headerKey, headerValue);
    	List<Baja> bajas = BajaService.listBajas();
    	BajaExcelExporter excelExporter = new BajaExcelExporter(bajas);
    	excelExporter.export(response);
    }
    @GetMapping("/exportreportesbajas")
    public void exportreportbajas(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = reportesbajas.xlsx";
    	response.setHeader(headerKey, headerValue);
    	List<Reporte_baja> reportebaja = Reporte_bajaService.ListReportes();
    	Reporte_bajaExporter excelExporter = new Reporte_bajaExporter(reportebaja);
    	excelExporter.export(response);
    }
    @GetMapping("/exporthvotros")
    public void exporthvotros(HttpServletResponse response) throws IOException {
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = hojavidaotros.xlsx";
    	response.setHeader(headerKey, headerValue);
    	List<Hoja_vida_otros> hoja_vida_otros = Hoja_vida_otrosService.Hoja_vida_otrosObtainallHV();
    	Hoja_vida_otrosExporter excelExporter = new Hoja_vida_otrosExporter(hoja_vida_otros);
    	excelExporter.export(response);
    }
    @GetMapping("/exporthv")
    public void exporthv(HttpServletResponse response) throws IOException{
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = hojasvida.xlsx";
    	response.setHeader(headerKey, headerValue);
    	List<Hoja_vida> hoja_vida = Hoja_vidaService.ObtainallHV();
    	Hoja_vidaExcelExporter excelExporter = new Hoja_vidaExcelExporter(hoja_vida);
    	
    	excelExporter.export(response);
    }
    
    @GetMapping("/exportumi")
    public void exportToExcelUmi(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventarioUMI.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.ListEquiposServicio((long) 32);
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
    
    @GetMapping("/exportconsultaexterna")
    public void exportToExcelConsultaExterna(HttpServletResponse response) throws IOException {
    	
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = inventarioConsultaExterna.xlsx";
    	response.setHeader(headerKey, headerValue);
    	
    	List<Equipo> equipos = EquipoService.ListEquiposServicio((long) 7);
    	EquipoExcelExporter excelExporter = new EquipoExcelExporter(equipos);
    	excelExporter.export(response);
    }
    
    @PostMapping("/exportarreportes")
    public void exportreports(HttpServletResponse response,
    		@RequestParam(value="fecha_inicial",defaultValue = "2022-02-01")String fecha_inicial,
			@RequestParam(value="fecha_final",defaultValue = "2022-03-01")String fecha_final) throws IOException{
    	System.out.println(fecha_final);
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachement; filename = reportes.xlsx";
    	LocalDate f1 = LocalDate.parse(fecha_inicial);
    	LocalDate f2 = LocalDate.parse(fecha_final);
    	List<Reporte> reportes = ReporteService.reportebyRange(Date.valueOf(f1), Date.valueOf(f2));
    	if(reportes!=null) {
    		
    		response.setHeader(headerKey, headerValue);
        	ReporteExcelExporter excelExporter  = new ReporteExcelExporter(reportes);
        	excelExporter.export(response);
    		
    	}
    	else {
    		reportes = ReporteService.ListReportes();
    		response.setHeader(headerKey, headerValue);
        	ReporteExcelExporter excelExporter  = new ReporteExcelExporter(reportes);
        	excelExporter.export(response);
    	}
    	
    }
    
    @GetMapping("/usuarios")
	public String Usuarios(Model model) {
    	LocalDate hoy = LocalDate.now();
    	LocalDate past = hoy.minusMonths(2);
    	LocalDate future = hoy.plusMonths(2);
    	LocalDate rptopast = hoy.minusDays(14);
    	
    	List<Reporte> reportes = ReporteService.reportesbyRepuestos(Date.valueOf(rptopast), Date.valueOf(hoy));
    	List<Reporte> repuestosnonulos = new ArrayList<Reporte>();
    	if(reportes!=null) {
    		for(int i = 0; i<reportes.size();i++) {
    			
    			if(reportes.get(i).getRepuesto_cambiado()!=null) {
    				repuestosnonulos.add(reportes.get(i));
    			}
    		}
    		
    	}
    	model.addAttribute("servicios", ServicioService.ListServicio());
    	model.addAttribute("repuestos",repuestosnonulos);
    	model.addAttribute("paravencer",Hoja_vidaService.findHvbyVcto(Date.valueOf(hoy), Date.valueOf(future)));
    	model.addAttribute("vencidos",Hoja_vidaService.findHvbyVcto(Date.valueOf(past), Date.valueOf(hoy)));
    	model.addAttribute("usuarios", UsuarioService.ListarUsuarios());
    	model.addAttribute("tipos",Tipo_equipoService.ListTipo_equipo());
		return "usuarios";
	}
    @GetMapping("/crearusuario")
    public String crearusuarios(Model model,Map<String, Object> map) {
    	Usuario usuario = new Usuario();
    	List<Authority> roles = new ArrayList<Authority>();
    	roles = AuthorityService.listrauthorities();
    	map.put("usuario",usuario);
    	model.addAttribute("auths", roles);
    	return "crearusuario";
    }
    
    @PostMapping("/setpassword")
    public String setpassword(
    		@RequestParam(value = "cedula")String cedula,
            @RequestParam(value = "password",defaultValue = "123456")String password,
            Model model,
            RedirectAttributes flash,
            SessionStatus status) {
    	
    		Usuario usuario = UsuarioService.findBycc(cedula);
    		if(usuario != null) {
    			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    			String rawPassword = password;
    			String encodedPassword = encoder.encode(rawPassword);
    			UsuarioService.setPassword(encodedPassword, cedula);
    		}
    	


    			return "redirect:/producto";
			
	}
    
    @PostMapping("/crearusuario")
    public String guardar(@Valid Usuario usuario,
            BindingResult result,
            @RequestParam(value = "password",defaultValue = "123456")String password,
            @RequestParam(value = "auths",defaultValue = "NONE")String auth,
            Model model,
            RedirectAttributes flash,
            SessionStatus status) {
			
    		if(password.equals("123456")||auth.equals("NONE")) {
    			
    			flash.addAttribute("Clave", "Clave no valida o rol no asignado");
    			return "redirect:/crearusuario";
    		}
    		else {
    			Set<Authority> roles = new HashSet<>();
    			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    			String rawPassword = password;
    			String encodedPassword = encoder.encode(rawPassword);
    			usuario.setHospital(HospitalService.findOne(1L));
    			usuario.setPassword(encodedPassword);
    			usuario.setEstado(true);
    			roles.add(AuthorityService.findOne(Long.valueOf(auth)));
    			usuario.setAuthority(roles);
    			UsuarioService.save(usuario);
    			return "redirect:/sysusuarios";
    		}
			
	}
    /*********************
    @RequestMapping(value = "/crearUsuario")
    public String crearUsuario(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        
        usuario.setEstado(true);

        model.put("usuario", usuario);
        model.put("bodegas",iBodegaService.findAll());
        model.put("titulo", "Crear de Usuario");
        return "crearUsuario";
    }
	*****************************/

    /*********************
    @RequestMapping(value="/crearUsuario/{id}")
    public String editar(@PathVariable(value="id") Long id,
                         Map<String, Object> model,
                         RedirectAttributes flash) {
        Usuario usuario = null;
        if(id > 0) {
            usuario = iUsuarioService.findOne(id);
            if(usuario==null){
                flash.addFlashAttribute("error","El Usuario no esta creado aún");
                return "redirect:/listarUsuarios";
            }
        } else {
            flash.addFlashAttribute("error","El ID del Usuario no puede ser cero!");
            return "redirect:/listarUsuarios";
        }
        model.put("usuario", usuario);
        model.put("bodegas",iBodegaService.findAll());
        model.put("titulo", "Editar Usuario");
        return "crearUsuario";
    }
    *****************************/
    /*********************
    @RequestMapping(value = "/crearUsuario", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash,
                          SessionStatus status) {
        if(result.hasErrors()){
            model.addAttribute("titulo","Crear Usuario");
            return "crearUsuario";
        }
        String pass = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(pass);
        String mensajeFlash = (usuario.getIdUsuario()!=null)?"Usuario editado con éxito":"Usuario creado con éxito";

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUsuario(usuario);
        usuario.addAuthority(authority);
        iUsuarioService.save(usuario);


        status.setComplete();
        flash.addFlashAttribute("success",mensajeFlash);
        return "redirect:listarUsuarios";
    }
    *****************************/
    /*********************
    @RequestMapping(value="/eliminarUsuario/{id}")
    public String eliminar(@PathVariable(value="id") Long id,
                           RedirectAttributes flash) {
        if(id > 0) {
            iUsuarioService.delete(id);
            flash.addFlashAttribute("success","Usuario eliminado con éxito");
        }
        return "redirect:/listarUsuarios";
    }
    *****************************/

}
