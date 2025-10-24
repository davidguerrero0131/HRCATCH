package com.HUSRTbdBiomedica.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.HUSRTbdBiomedica.app.entity.Llamado;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.CustomUserDetails;
import com.HUSRTbdBiomedica.service.EnvioEmail;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.ILlamadoService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.UploadFileService;

@Controller
@RequestMapping
public class LlamadoController {

	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private ILlamadoService LlamadoService;
	
	@Autowired
	private IServicioService ServicioService;
	
	@Autowired
	private IUsuarioService UsuarioService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private EnvioEmail envioEmail;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@GetMapping("/nuevollamado/{id}")	
	public String nuevollamado(@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		
		Llamado llamado = new Llamado();
		
		map.put("llamado",llamado);
		model.addAttribute("tipos", Tipo_equipoService.ListTipo_equipo());
		model.addAttribute("servicio",ServicioService.findOne(id));
		model.addAttribute("fecha", LocalDate.now());
		
		
		return "nuevollamado";
	}
	@PostMapping("/nuevollamado/{id}")
	public String guardarllamado(@PathVariable(value = "id")Long id,    		
    		@Valid Llamado llamado,
    		@RequestParam(value = "tipo_equipo",defaultValue = "1")Long idtipo,
    		@RequestParam(value = "afecta_usuario",defaultValue = "false")boolean impactoPascinete,
    		@RequestParam(value = "equipo_operativo",defaultValue = "false")boolean operativo,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
		Tipo_equipo tipo = Tipo_equipoService.findOne(idtipo);
		llamado.setTipo_equipo(tipo);
		Servicio service = ServicioService.findOne(id);
		llamado.setFecha(Date.valueOf(LocalDate.now()));
		llamado.setHora_llamado(Time.valueOf(LocalTime.now()));
		llamado.setServicio(service);
		llamado.setEstado(0);
		llamado.setPor_telefono(false);
		llamado.setConfirmacion_telefono(false);
		llamado.setAfectaPaciente(impactoPascinete);
		llamado.setEquipoDesabilitado(operativo);
		llamado.setTiempoParada(0);
		LlamadoService.save(llamado);
		envioEmail.sendEmail("biomedicahusrt@gmail.com", service.getNombre_servicio()+' '+tipo.getNombre_Tipo_equipo(), llamado.getDescripcion());
		
		
		
		flash.addAttribute("realizado", "Llamado realizado con exito");
		
		return "redirect:/servicellamados";
	}
	@GetMapping("/atenderllamado/{id}")
	public String atenderllamado(@PathVariable(value = "id")Long id,
				Map<String,Object>map,Model model,
				RedirectAttributes flash,
				SessionStatus status) {
		
		Llamado llamado = LlamadoService.findOne(id);
		map.put("allamado",llamado);
		model.addAttribute("servicio", llamado.getServicio());
		model.addAttribute("tipo_equipo", llamado.getTipo_equipo());
		model.addAttribute("equipos", Tipo_equipoService.findEquiposbyTipoEquipo(llamado.getTipo_equipo().getId_Tipo_equipo()));
		return "atenderllamado";
	}
	
	@PostMapping("/atenderllamado/{id}")
	public String atendergllamado(@PathVariable(value = "id")Long id,
			@RequestParam(value="hora_respuesta",defaultValue = "00:00")String hora_respuesta,
			@RequestParam(value="fecha_respuesta")String fecha_respuesta,
			@RequestParam(value="serie")String Serie,
			@RequestParam(value="equipo_operativo")boolean equipoOperativo,
			@RequestParam(value="foto")MultipartFile foto,
			@Valid Llamado allamado,BindingResult binRes,
			BindingResult result,
			Model model,
			RedirectAttributes flash,
			SessionStatus status
			) throws IOException {
		Authentication auth = SecurityContextHolder
			    .getContext()
			    .getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		
		Llamado llamado = LlamadoService.findOne(id);
		llamado.setAtendido(user.getNombre()+' '+user.getApellido());
		llamado.setTipo(allamado.getTipo());
		
		llamado.setUbicacion_exacta(allamado.getUbicacion_exacta());
	
		llamado.setSerie(Serie+','+EquipoService.findOne(Long.valueOf(Serie)).getSerie());
		if(allamado.isPor_telefono()) {
			Time hf = Time.valueOf(LocalTime.parse(hora_respuesta));
			llamado.setPor_telefono(true);
			llamado.setHora_respuesta(hf);
			LocalDate fecharta = LocalDate.parse(fecha_respuesta);
	    	Date fechaas = Date.valueOf(fecharta);
	    	llamado.setFecha_r(fechaas);
		}
		else {
			llamado.setHora_respuesta(Time.valueOf(LocalTime.now()));
			llamado.setFecha_r(Date.valueOf(LocalDate.now()));
			llamado.setPor_telefono(false);
			llamado.setConfirmacion_telefono(false);
		}
		if(foto.getOriginalFilename()!="") {
			uploadFileService.saveImageLlamado(foto, id);
			String Fotohv = "/images/llamados/"+String.valueOf(id)+foto.getOriginalFilename();
			llamado.setFoto(Fotohv);
		}
		llamado.setEstado(1);
		llamado.setEquipoDesabilitado(equipoOperativo);
		llamado.setTipo_equipo(EquipoService.findOne(Long.valueOf(Serie)).getTipo_equipo());
		LlamadoService.save(llamado);
		
		return "redirect:/servicellamados";
		
	}
	@GetMapping("/solucionarllamado/{id}")
	public String solvellamado(@PathVariable(value = "id")Long id,
			Map<String,Object>map,Model model,
			RedirectAttributes flash,
			SessionStatus status) {
	
		Llamado llamado = LlamadoService.findOne(id);
		ArrayList<String> idserie = new ArrayList<String>(Arrays.asList(llamado.getSerie().split(",")));
		
		
		map.put("allamado",llamado);
		model.addAttribute("servicio", llamado.getServicio());
		model.addAttribute("tipo_equipo", llamado.getTipo_equipo());
		model.addAttribute("equipo",EquipoService.findOne(Long.valueOf(idserie.get(0))));
		return "solvellamado";
	}
	@PostMapping("/solucionarllamado/{id}")
	public String savesolvellamado(@PathVariable(value = "id")Long id,
			@Valid Llamado allamado,
			BindingResult result,
			Model model,
			RedirectAttributes flash,
			SessionStatus status) {
		Authentication auth = SecurityContextHolder
			    .getContext()
			    .getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		
		Llamado llamado = LlamadoService.findOne(id);
		llamado.setSolucionado_por(user.getNombre()+' '+user.getApellido());
		llamado.setConfirmacion_telefono(allamado.isConfirmacion_telefono());
		llamado.setEstado(2);
		llamado.setSolucion(allamado.getSolucion());
		llamado.setAtencion_prioridad(allamado.getAtencion_prioridad());
		llamado.setHora_solucion(Time.valueOf(LocalTime.now()));
		llamado.setFecha_sn(Date.valueOf(LocalDate.now()));
		if(llamado.isEquipoDesabilitado()) {
			
			double time = calcularDiferenciaHoras(llamado.getFecha() + "", llamado.getHora_llamado() + "", llamado.getFecha_sn() + "", llamado.getHora_solucion() + "");
			
			llamado.setTiempoParada(time);
		}else {
			llamado.setTiempoParada(0);
		}
		
		LlamadoService.save(llamado);
		
		return "redirect:/servicellamados";
	}
	
	@PostMapping("/calificacionllamado/{id}")
	public String calificacionllamado(@PathVariable(value = "id")Long id,
			@Valid Llamado allamado,
			BindingResult result,
			Model model,
			RedirectAttributes flash,
			SessionStatus status) {
		
		Llamado llamado = LlamadoService.findOne(id);
		llamado.setCalificacion(allamado.getCalificacion());
		llamado.setEstado(3);
		LlamadoService.save(llamado);
		
		return "redirect:/servicellamados";
	}
	@GetMapping("/servicellamados")
	public String listllamadosmes(Model model) {
		
		LocalDate hoy = LocalDate.now();
		List<Llamado> llamados = LlamadoService.listllamadobyrange(Date.valueOf(hoy.minusMonths(1L)), Date.valueOf(hoy));
		List<Llamado> sinr = new ArrayList<Llamado>();
		List<Llamado> atn = new ArrayList<Llamado>();
		List<Llamado> sl = new ArrayList<Llamado>();
		for(int l = 0;l<llamados.size();l++) {
			if(llamados.get(l).getEstado() == 0) {sinr.add(llamados.get(l));}
			else if(llamados.get(l).getEstado() == 1) {atn.add(llamados.get(l));}
			else {sl.add(llamados.get(l));}
		}
		Date fecha1 = Date.valueOf(hoy.minusMonths(1L));
		List<String> prior = LlamadoService.listPrioridad(fecha1, Date.valueOf(hoy));
		
		List<String> prioridadllamado = new ArrayList<String>();
		List<String> horasllamado = new ArrayList<String>();
		List<String> horastotales = new ArrayList<String>();
		for(int i = 0;i<prior.size();i++) {
			
			prioridadllamado = Arrays.asList(prior.get(i).split(","));
		
			Integer seconds =Math.round(Float.valueOf(prioridadllamado.get(2)));
			int horas = (seconds / 3600);
		    int minutos = ((seconds-horas*3600)/60);
		    int segundos = seconds-(horas*3600+minutos*60);
			
			String horapromedio = String.valueOf(horas)+":"+String.valueOf(minutos)+":"+String.valueOf(segundos);
			horasllamado.add(horapromedio);
			
			seconds = Math.round(Float.valueOf(prioridadllamado.get(4)));
			horas = (seconds / 3600);
			minutos = ((seconds-horas*3600)/60);
			segundos = seconds-(horas*3600+minutos*60);
			
			horapromedio = String.valueOf(horas)+":"+String.valueOf(minutos)+":"+String.valueOf(segundos);
			horastotales.add(horapromedio);
//Math.floor((timeotrafalla/fotros)/60)+':'+Math.floor((timeotrafalla/fotros)%60)+':'+
//			Math.floor(((timeotrafalla/fotros)%60-Math.floor((timeotrafalla/fotros)%60))*60)
			
		}
		
		
//		int rseconds =Math.round(LlamadoService.countSecondRta(fecha1, Date.valueOf(hoy)));
//		int rhoras = (rseconds / 3600);
//	    int rminutos = ((rseconds-rhoras*3600)/60);
//	    int rsegundos = rseconds-(rhoras*3600+rminutos*60);
//	    String rhorapromedio = String.valueOf(rhoras)+":"+String.valueOf(rminutos)+":"+String.valueOf(rsegundos);
//		
//	    int tseconds =Math.round(LlamadoService.countSecondTotal(fecha1, Date.valueOf(hoy)));
//		int thoras = (tseconds / 3600);
//	    int tminutos = ((tseconds-thoras*3600)/60);
//	    int tsegundos = tseconds-(thoras*3600+tminutos*60);
//	    String thorapromedio = String.valueOf(thoras)+":"+String.valueOf(tminutos)+":"+String.valueOf(tsegundos);
//		
		
		
		
		model.addAttribute("top", LlamadoService.listopEquipos(fecha1, Date.valueOf(hoy)));
		
		model.addAttribute("bytec", LlamadoService.listllamadobytec(fecha1, Date.valueOf(hoy)));
		model.addAttribute("tecs", LlamadoService.listbytec(fecha1, Date.valueOf(hoy)));
		model.addAttribute("byservices",LlamadoService.listbyservices(fecha1, Date.valueOf(hoy)));
		model.addAttribute("services",LlamadoService.listonlyservices(fecha1, Date.valueOf(hoy)));
		model.addAttribute("llamados",sinr);
		model.addAttribute("atendidos",atn);
		model.addAttribute("solves",sl);
		model.addAttribute("numll",LlamadoService.countbyDate(Date.valueOf(hoy.minusMonths(1L)), Date.valueOf(hoy)));
		model.addAttribute("fecha1",hoy.minusMonths(1L));
		model.addAttribute("fecha2",hoy);
		model.addAttribute("ltotal", LlamadoService.contarCall(fecha1, Date.valueOf(hoy)));
		model.addAttribute("latend", LlamadoService.contarCallatt(fecha1, Date.valueOf(hoy)));
		model.addAttribute("lsol", LlamadoService.contarCallsn(fecha1, Date.valueOf(hoy)));
		//model.addAttribute("lrta", rhorapromedio);
		//model.addAttribute("lt",thorapromedio);
		model.addAttribute("ltotalphone", LlamadoService.listattPhone(fecha1, Date.valueOf(hoy)));
		model.addAttribute("lrtaphone", LlamadoService.listsnPhone(fecha1, Date.valueOf(hoy)));
		model.addAttribute("horasllamado", horasllamado);
		model.addAttribute("horastotales", horastotales);
		model.addAttribute("prior", prior);
		return "listllamados";
	}
	@PostMapping("/servicellamados")
	public String listllamadossearchmes(Model model, RedirectAttributes flash,
			@RequestParam(value="daterange")String rangofechas) {
		
		String[] fechas = rangofechas.split("-");
		ArrayList<String> fecha12 = new ArrayList<String>(Arrays.asList(fechas));
		String fecha1 = fecha12.get(0).trim();
		String fecha2 = fecha12.get(1).trim();
		ArrayList<String> fecha1div = new ArrayList<String>(Arrays.asList(fecha1.split("/")));
		ArrayList<String> fecha2div = new ArrayList<String>(Arrays.asList(fecha2.split("/")));
		
		Integer mes1 = Integer.valueOf(fecha1div.get(0));
		int dia1 = Integer.valueOf(fecha1div.get(1));
		int ano1 = Integer.valueOf(fecha1div.get(2));
		
		Integer mes2 = Integer.valueOf(fecha2div.get(0));
		int dia2 = Integer.valueOf(fecha2div.get(1));
		int ano2 = Integer.valueOf(fecha2div.get(2));
		fecha1 = String.valueOf(ano1)+'-'+String.valueOf(mes1)+'-'+String.valueOf(dia1);
		fecha2 = String.valueOf(ano2)+'-'+String.valueOf(mes2)+'-'+String.valueOf(dia2);

		Date fecha_1 = Date.valueOf(fecha1);
		Date fecha_2 = Date.valueOf(fecha2);
	
		
		List<Llamado> llamados = LlamadoService.listllamadobyrange(fecha_1,fecha_2);
		List<Llamado> sinr = new ArrayList<Llamado>();
		List<Llamado> atn = new ArrayList<Llamado>();
		List<Llamado> sl = new ArrayList<Llamado>();
		for(int l = 0;l<llamados.size();l++) {
			if(llamados.get(l).getEstado() == 0) {sinr.add(llamados.get(l));}
			else if(llamados.get(l).getEstado() == 1) {atn.add(llamados.get(l));}
			else {sl.add(llamados.get(l));}
		}
		List<String> prior = LlamadoService.listPrioridad(fecha_1, fecha_2);
		
		List<String> prioridadllamado = new ArrayList<String>();
		List<String> horasllamado = new ArrayList<String>();
		List<String> horastotales = new ArrayList<String>();
		for(int i = 0;i<prior.size();i++) {
			
			prioridadllamado = Arrays.asList(prior.get(i).split(","));
		
			Integer seconds =Math.round(Float.valueOf(prioridadllamado.get(2)));
			int horas = (seconds / 3600);
		    int minutos = ((seconds-horas*3600)/60);
		    int segundos = seconds-(horas*3600+minutos*60);
			
			String horapromedio = String.valueOf(horas)+":"+String.valueOf(minutos)+":"+String.valueOf(segundos);
			horasllamado.add(horapromedio);
			
			seconds = Math.round(Float.valueOf(prioridadllamado.get(4)));
			horas = (seconds / 3600);
			minutos = ((seconds-horas*3600)/60);
			segundos = seconds-(horas*3600+minutos*60);
			
			horapromedio = String.valueOf(horas)+":"+String.valueOf(minutos)+":"+String.valueOf(segundos);
			horastotales.add(horapromedio);
			
		}
		String rhorapromedio = "";
		String thorapromedio = "";
		try {
			int rseconds =Math.round(LlamadoService.countSecondRta(fecha_1, fecha_2));
			int rhoras = (rseconds / 3600);
		    int rminutos = ((rseconds-rhoras*3600)/60);
		    int rsegundos = rseconds-(rhoras*3600+rminutos*60);
		    rhorapromedio = String.valueOf(rhoras)+":"+String.valueOf(rminutos)+":"+String.valueOf(rsegundos);
			
		    int tseconds =Math.round(LlamadoService.countSecondTotal(fecha_1, fecha_2));
			int thoras = (tseconds / 3600);
		    int tminutos = ((tseconds-thoras*3600)/60);
		    int tsegundos = tseconds-(thoras*3600+tminutos*60);
		    thorapromedio = String.valueOf(thoras)+":"+String.valueOf(tminutos)+":"+String.valueOf(tsegundos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    model.addAttribute("top", LlamadoService.listopEquipos(fecha_1,fecha_2));
		
		model.addAttribute("llamados",sinr);
		model.addAttribute("atendidos",atn);
		model.addAttribute("solves",sl);
	
		model.addAttribute("bytec", LlamadoService.listllamadobytec(fecha_1,fecha_2));
		model.addAttribute("tecs", LlamadoService.listbytec(fecha_1, fecha_2));
		model.addAttribute("byservices",LlamadoService.listbyservices(fecha_1, fecha_2));
		model.addAttribute("services",LlamadoService.listonlyservices(fecha_1, fecha_2));
		model.addAttribute("ltotal", LlamadoService.contarCall(fecha_1, fecha_2));
		model.addAttribute("latend", LlamadoService.contarCallatt(fecha_1, fecha_2));
		model.addAttribute("lsol", LlamadoService.contarCallsn(fecha_1, fecha_2));
		model.addAttribute("lrta", rhorapromedio);
		model.addAttribute("lt",thorapromedio);
		model.addAttribute("ltotalphone", LlamadoService.listattPhone(fecha_1, fecha_2));
		model.addAttribute("lrtaphone", LlamadoService.listsnPhone(fecha_1, fecha_2));
		model.addAttribute("numll",LlamadoService.countbyDate(fecha_1,fecha_2));
		model.addAttribute("fecha1",fecha1);
		model.addAttribute("fecha2",fecha2);
		
		model.addAttribute("horasllamado", horasllamado);
		model.addAttribute("horastotales", horastotales);
		model.addAttribute("prior", prior);
		return "listllamados";
	}
	
	@GetMapping("/testDiferenciaHoras")
	public void testDiferenciaHoras() {
		
		List<Llamado> listaLlamados = LlamadoService.ListLlamado();
		for (int i = 0; i < listaLlamados.size(); i++) {
			if(listaLlamados.get(i).getFecha() != null && listaLlamados.get(i).getHora_llamado() != null && listaLlamados.get(i).getFecha_sn() != null && listaLlamados.get(i).getHora_solucion() != null) {
				double diferencia = calcularDiferenciaHoras(listaLlamados.get(i).getFecha() + "", listaLlamados.get(i).getHora_llamado() + "", listaLlamados.get(i).getFecha_sn() + "", listaLlamados.get(i).getHora_solucion() + "");
				System.out.println("FechaI: " + listaLlamados.get(i).getFecha() + " -- HoraI: " + listaLlamados.get(i).getHora_llamado() + " -- FechaF: " + listaLlamados.get(i).getFecha_sn() + " -- HoraF: " + listaLlamados.get(i).getHora_solucion() + " -- Diferencia: " + diferencia);
		
				if(listaLlamados.get(i).isEquipoDesabilitado()) {
					double time = calcularDiferenciaHoras(listaLlamados.get(i).getFecha() + "", listaLlamados.get(i).getHora_llamado() + "", listaLlamados.get(i).getFecha_sn() + "", listaLlamados.get(i).getHora_solucion() + "");
					listaLlamados.get(i).setTiempoParada(time);
				}else {
					listaLlamados.get(i).setTiempoParada(0);
				}
				LlamadoService.save(listaLlamados.get(i));
			}
		}
	}
	
    public double calcularDiferenciaHoras(String fechaInicio, String horaInicio, String fechaFin, String horaFin) {
        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String inicio = fechaInicio + " " + horaInicio;
            String fin = fechaFin + " " + horaFin;
            
            java.util.Date utilDateInicio = formato.parse(inicio);
            java.util.Date utilDateFin = formato.parse(fin);


            Date sqlDateInicio = new Date(utilDateInicio.getTime());
            Date sqlDateFin = new Date(utilDateFin.getTime());
            
            
            double diferenciaMilisegundos = sqlDateFin.getTime() - sqlDateInicio.getTime();

            return redondearAUnDecimal(diferenciaMilisegundos / (1000 * 60 * 60));


        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Retorna -1 en caso de error
        }
    }
	
    
    public static double redondearAUnDecimal(double numero) {
        return Math.round(numero * 10.0) / 10.0;
    }
}
