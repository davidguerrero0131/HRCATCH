package com.HUSRTbdBiomedica.app.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_comodatos;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_otros;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vida_comodatosService;
import com.HUSRTbdBiomedica.service.IHoja_vida_otrosService;
import com.HUSRTbdBiomedica.service.IReporteService;


@Controller
@SessionAttributes("hoja_vida_otros")
@RequestMapping
public class Hoja_vida_otrosController {
	
	@Autowired
	private IHoja_vida_otrosService Hoja_vida_otrosService;
	
	@Autowired
	private IHoja_vida_comodatosService Hoja_vida_comodatosService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@GetMapping("/nuevohvind")
	public String crearnuevahvindp(Model model,Map<String, Object> map,
			RedirectAttributes flash) {
		Hoja_vida_otros hoja_vida_otros = new Hoja_vida_otros();
		hoja_vida_otros.setEquipo_otros(EquipoService.findOne(11111L));
		map.put("hoja_vida", hoja_vida_otros);
		model.addAttribute("idhv",Hoja_vida_otrosService.LastHV());
		return "nuevohvind";
	}
	
	@PostMapping("/nuevohvind/{id}")
	public String guardarhvindp(@PathVariable(value = "id") Long id,@Valid Hoja_vida_otros hoja_vida,            
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="foto",defaultValue = "empty")String foto,
            SessionStatus status) {
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	
    	hoja_vida.setFecha_compra_otros(fechac);
    	hoja_vida.setFecha_instalacion_otros(fechai);
    	hoja_vida.setFecha_iniciooperacion_otros(fechaop);
    	hoja_vida.setFecha_vencimientogarantia_otros(fechav);
    	
    	
		hoja_vida.setEquipo_otros(EquipoService.findOne(11111L));		
		String Fotohv = "/images/"+String.valueOf(foto);
		hoja_vida.setFoto_otros(Fotohv);
		Hoja_vida_otrosService.save(hoja_vida);
		
        status.setComplete();
        flash.addFlashAttribute("success","guardado");
		return "redirect:/listarhvind";
	}
	
	
	@GetMapping("/nuevoComodato")
	public String crearnuevahvindComodatos(Model model,Map<String, Object> map,
			RedirectAttributes flash) {
		Hoja_vida_comodatos hoja_vida_comodatos = new Hoja_vida_comodatos();
		hoja_vida_comodatos.setEquipo_otros(EquipoService.findOne(11111L));
		map.put("hoja_vida", hoja_vida_comodatos);
		model.addAttribute("idhv",Hoja_vida_otrosService.LastHV());
		return "nuevohvindcomodato";
	}
	
	@PostMapping("/nuevohvindcomodato/{id}")
	public String guardarhvindcomodato(@PathVariable(value = "id") Long id,@Valid Hoja_vida_comodatos hoja_vida,            
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="foto",defaultValue = "empty")String foto,
            SessionStatus status) {
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	
    	hoja_vida.setFecha_compra_otros(fechac);
    	hoja_vida.setFecha_instalacion_otros(fechai);
    	hoja_vida.setFecha_iniciooperacion_otros(fechaop);
    	hoja_vida.setFecha_vencimientogarantia_otros(fechav);
    	
    	
		hoja_vida.setEquipo_otros(EquipoService.findOne(11111L));		
		String Fotohv = "/images/"+String.valueOf(foto);
		hoja_vida.setFoto_otros(Fotohv);
		Hoja_vida_comodatosService.save(hoja_vida);
		
        status.setComplete();
        flash.addFlashAttribute("success","guardado");
		return "redirect:/vistaEmpresasComodatos";
	}
	
	@GetMapping("/editarhvind/{id}")
	public String editarhvindp(@PathVariable(value = "id") Long id,
			Model model,Map<String, Object> map,
			RedirectAttributes flash) {
		Hoja_vida_otros hoja_vida_otros = Hoja_vida_otrosService.findOne(id);
		map.put("hoja_vida", hoja_vida_otros);
		
		return "nuevohvind";
	}
	
	@GetMapping("/listarhvind")
	public String listarhvindep(Model model,
			RedirectAttributes flash) {		
		model.addAttribute("hoja_vida_otros",Hoja_vida_otrosService.Hoja_vida_otrosObtainallHV());
		model.addAttribute("numhvind",Hoja_vida_otrosService.CounallOtrosHV());
		return "listarhvind";
	}
	@GetMapping("/formatohvind/{id}")
	public String formatohvindependiente(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		
		model.addAttribute("hoja_vida",Hoja_vida_otrosService.findOne(id));
		return "formatohojadevidaotros";
	}
	@GetMapping("/editreporteotro/{id}")
	public String editarreporteotro(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Reporte reporte  = ReporteService.findOne(id);
		Equipo equipo= reporte.getEquipo();
		model.addAttribute("idequipo",equipo.getId_Equipo());
    	map.put("reporte", reporte);
    
    	
    	model.addAttribute("numeroreporte",reporte.getNumero_reporte());		
    	System.out.println(reporte.getNumero_reporte());
		model.addAttribute("fecha",reporte.getFecha());
		model.addAttribute("horallamado",reporte.getHora_llamado());
		model.addAttribute("horainicio",reporte.getHora_inicio());
		model.addAttribute("horaterminacion",reporte.getHora_terminacion());
		
		
		return "nuevoreporteditotro";
	}
	@PostMapping(value="/editreporteotro/{id}")
    public String guardarnuevoreporteditotro(
    		@PathVariable Long id,@RequestParam(value="fecha")String fecha,
    	    		@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
    	    		@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    	    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    	    		@Valid Reporte reporte,
    	    								  BindingResult result,
    	    								  Model model,
    	    								  RedirectAttributes flash,
    	    								  SessionStatus status)  {
    	
    	
		Equipo equipo = EquipoService.findOne(id);
    	System.out.println(reporte.getNumero_reporte());
    	System.out.println(reporte.getNombre_equipo());
    	LocalDate fechareporte = LocalDate.parse(fecha);
    	Date fechaas = Date.valueOf(fechareporte);
    	
    	reporte.setFecha(fechaas);
    
    	
    	String hora1 = hora_llamado; 
    	
    	Time hl = Time.valueOf(LocalTime.parse(hora1));
    	reporte.setHora_llamado(hl);
    	
    	String hora2 = hora_inicio;
    	Time hi = Time.valueOf(LocalTime.parse(hora2));
    	reporte.setHora_inicio(hi);
    	
    	String hora3 = hora_finalizacion;
    	Time hf = Time.valueOf(LocalTime.parse(hora3));
    	reporte.setHora_terminacion(hf);
    	
    	
    	LocalTime hinicio = hi.toLocalTime();
    	LocalTime hfinal = hf.toLocalTime();
    	LocalTime hginalminus = hfinal.minusHours(hinicio.getHour());
    	LocalTime thora = hginalminus.minusMinutes(hinicio.getMinute());
    	Time totalhoras = Time.valueOf(thora);
    	
    	reporte.setTotal_horas(totalhoras);

    	
    	reporte.setEquipo(equipo);

    	ReporteService.save(reporte);
    	
    	status.setComplete();
    	
    	
    	flash.addFlashAttribute("agregado","Reporte agregado correctamente");
   
    	
    	return "producto";
    	
    }
	@GetMapping("/nuevoreporteotro")
	public String generarnuevoreporteotro(
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		Reporte reporte = new Reporte();
		reporte.setEquipo(EquipoService.findOne(11111L));
		model.addAttribute("numeroreporte",ReporteService.LastIdReporte()+20001);
		map.put("reporte",reporte);
		return "nuevoreporteotro";
	}
	@PostMapping(value="/nuevoreporteotro")
	public String guardarnuevoreporteotro(@RequestParam(value="fecha")String fecha,
    		@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
    		@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@ModelAttribute Reporte reporte,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
		System.out.println(reporte.getNombre_equipo());
		reporte.setEquipo(EquipoService.findOne(11111L));
		LocalDate fechareporte = LocalDate.parse(fecha);
    	Date fechaas = Date.valueOf(fechareporte);
    	reporte.setFecha(fechaas);
    	
    	String hora1 = hora_llamado; 
    	
    	Time hl = Time.valueOf(LocalTime.parse(hora1));
    	reporte.setHora_llamado(hl);
    	
    	String hora2 = hora_inicio;
    	Time hi = Time.valueOf(LocalTime.parse(hora2));
    	reporte.setHora_inicio(hi);
    	
    	String hora3 = hora_finalizacion;
    	Time hf = Time.valueOf(LocalTime.parse(hora3));
    	reporte.setHora_terminacion(hf);
    	
    	
    	LocalTime hinicio = hi.toLocalTime();
    	LocalTime hfinal = hf.toLocalTime();
    	LocalTime hginalminus = hfinal.minusHours(hinicio.getHour());
    	LocalTime thora = hginalminus.minusMinutes(hinicio.getMinute());
    	Time totalhoras = Time.valueOf(thora);
    	
    	reporte.setTotal_horas(totalhoras);
    	String numr = Long.toString(ReporteService.LastIdReporte()+20001);
    	reporte.setNumero_reporte(numr);
    	
		ReporteService.save(reporte);
		return "redirect:/visualizacionotrosreportes";
	}
	@GetMapping("/visualizacionotrosreportes")
	public String visualizarotrosReportes(
			 Model model,
			 RedirectAttributes flash) {
		Date fecha2 = Date.valueOf(LocalDate.now());
		Date fecha1 = Date.valueOf(LocalDate.now().minusMonths(1));
		
		model.addAttribute("fecha1",fecha1);
		model.addAttribute("fecha2",fecha2);
		model.addAttribute("reportes",ReporteService.findotrosreportesbyFecha(11111L, fecha1, fecha2));
		return "visualizacionotrosreportes";
	}
    @PostMapping(value="/visualizacionotrosreportes")
    public String visualizacionotrosReportesselectFecha(Model model,
			RedirectAttributes flash, 
			@RequestParam(value="fecha_inicial",defaultValue = "2022-03-01")String fecha_inicial,
			@RequestParam(value="fecha_final",defaultValue = "2022-02-01")String fecha_final) {
    	
    	LocalDate fecha1 = LocalDate.parse(fecha_inicial);
    	Date fechainicial = Date.valueOf(fecha1);
    	LocalDate fecha2 = LocalDate.parse(fecha_final);
    	Date fechafinal = Date.valueOf(fecha2);
    	List<Reporte> reportes = ReporteService.findotrosreportesbyFecha(11111L, fechainicial, fechafinal);
    	if(reportes!=null) {
    		model.addAttribute("fecha1",fecha1);
    		model.addAttribute("fecha2",fecha2);
    		model.addAttribute("reportes",reportes);
    		return "visualizacionotrosreportes";
    	}
    	else {
    		return "redirect:/vizualizacionotrosreportes";
    	}
    	
    }
    
	@GetMapping("/vistaEmpresasComodatos")
	public String VerComodatos(
			Model model,Map<String,Object>map,
            RedirectAttributes flash) {
		
		model.addAttribute("proveedores", Hoja_vida_comodatosService.listProveedores());
		return "vistaEmpresasComodatos";
	}

	
	@GetMapping("/comodatos/{name}")
	public String VerComodatosPorEmpresa(@PathVariable(value = "name") String name,
			Model model,Map<String,Object>map,
            RedirectAttributes flash) {
		
		model.addAttribute("ComodatosPorProveedor", Hoja_vida_comodatosService.listByProveedor(name));
		
		return "vistaComodatosPorEmpresa";
	}
	
	@GetMapping("/formatohvindcomodato/{id}")
	public String formatohvcomodato(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		
		model.addAttribute("hoja_vida",Hoja_vida_comodatosService.findOne(id));
		return "formatohojadevidaotros";
	}
	
	@GetMapping("/DesactivarEquipo/{id}")
	public String desactivarComodato(@PathVariable(value = "id") Long id){
		Hoja_vida_comodatos hoja_vida_comodatos = Hoja_vida_comodatosService.findOne(id);
		Hoja_vida_comodatosService.desactivarComodato(id);
		
		return "redirect:/comodatos/" + hoja_vida_comodatos.getProveedor_otros();
	}
	
}
