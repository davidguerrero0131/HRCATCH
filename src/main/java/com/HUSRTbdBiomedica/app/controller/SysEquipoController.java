package com.HUSRTbdBiomedica.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hospital;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.SystemBaja;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.CustomUserDetails;
import com.HUSRTbdBiomedica.service.IHospital_Service;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ISystemBajaService;
import com.HUSRTbdBiomedica.service.ISystemEquipoService;
import com.HUSRTbdBiomedica.service.ISystemHoja_vidaService;
import com.HUSRTbdBiomedica.service.ISystemMantenimientoService;
import com.HUSRTbdBiomedica.service.ISystemRepuestosService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.lowagie.text.DocumentException;

@Controller
@SessionAttributes("sysequipo")
@RequestMapping
public class SysEquipoController {

	@Autowired
	private ITipo_equipoService Tipo_equipoService;

	@Autowired
	private IUsuarioService UsuarioService;
	
	@Autowired
	private ISystemBajaService SystemBajaService;

	@Autowired
	private IServicioService ServicioService;

	@Autowired
	private IHospital_Service HospitalService;

	@Autowired
	private ISystemEquipoService SystemEquipoService;

	@Autowired
	private ISystemRepuestosService SystemRepuestosService;

	@Autowired
	private ISystemMantenimientoService SystemMantenimientoService;

	@Autowired
	private ISystemHoja_vidaService SystemHoja_vidaService;

	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired
	private IHospital_Service hospital_Service;

	@GetMapping("/syshome")
	public String syshome(Model model) {

		return "syshome";
	}

	@GetMapping("/systipos")
	public String systipo(Model model) {
		model.addAttribute("series", SystemEquipoService.listSySeries());
		model.addAttribute("tipos", Tipo_equipoService.findbySystem());
		model.addAttribute("servicios", ServicioService.ListServicioSys());
		return "systipos";
	}

	@PostMapping("/syserie")
	public String syserie(@RequestParam(value = "serie", defaultValue = "null") String serie, Model model,
			RedirectAttributes flash) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		SystemBaja baja = new SystemBaja();
		if (serie.equals("null")) {
			flash.addAttribute("Null", "Equipo no existe");
			return "redirect:/systipos";
		} else {
			List<SystemEquipo> equipo = SystemEquipoService.findbySerie(serie);
			if (equipo != null) {
				model.addAttribute("sysBaja", baja);
				model.addAttribute("titulo", equipo.get(0).getTipo_equipo().getNombre_Tipo_equipo());
				model.addAttribute("tipoEquipo", equipo.get(0).getTipo_equipo().getId_Tipo_equipo());
				model.addAttribute("equipos", equipo);
				model.addAttribute("usuario", user);
				return "sysequipos";
			} else {
				flash.addAttribute("Null", "Equipo no existe");
				return "redirect:/systipos";
			}
		}

	}

	@GetMapping("/sysequipos/{id}")
	public String sysequipos(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		SystemBaja baja = new SystemBaja();
		List<SystemEquipo> equipos = Tipo_equipoService.findSystemEquiposbyTipoEquipo(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		model.addAttribute("sysBaja", baja);
		model.addAttribute("equipos", equipos);
		model.addAttribute("titulo", tipo_equipo.getNombre_Tipo_equipo());
		model.addAttribute("usuario", user);
		return "sysequipos";
	}
	
	
	

	@GetMapping("/sysequiposervice/{id}")
	public String sysequiposervice(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Servicio servicio = ServicioService.findOne(id);

		List<SystemEquipo> equipos = ServicioService.listSystemEquiposbyServicio(id);
		SystemBaja baja = new SystemBaja();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		model.addAttribute("usuario", user);
		model.addAttribute("sysBaja", baja);
		model.addAttribute("equipos", equipos);
		model.addAttribute("titulo", servicio.getNombre_servicio());
		return "sysequipos";
	}

	@GetMapping("/sysreportes/{id}")
	public String sysreportes(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		List<Protocolo_preventivo> protocoloh = Protocolo_preventivoService
				.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
		List<Protocolo_preventivo> protocolos = Protocolo_preventivoService
				.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth.getPrincipal();
		Usuario user = UsuarioService.findBycc(userDetail.getUsername());
		model.addAttribute("usuario", user);
		model.addAttribute("protocoloh", protocoloh);
		model.addAttribute("protocolos", protocolos);
		model.addAttribute("repuestos", SystemRepuestosService.listrepuestosbyequipo(id));
		model.addAttribute("hoja_vida", SystemHoja_vidaService.findHv(id));
		model.addAttribute("equipo", SystemEquipoService.findOne(id));
		model.addAttribute("mttos", SystemMantenimientoService.mttosbyequipo(id));
		return "sysreportes";
	}

	@GetMapping("/formatohvsyspdf")
	public void downloadReportFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalHsysVPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoHVSys2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatodanospdf")
	public void downloadDamageFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalDanosPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoDanos2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatoentregaportatilpdf")
	public void downloadentregaportatilFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalEntregaPortatilsysPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoEntregaPortatilSys2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatoentregapdf")
	public void downloadentregaFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalEntregasysPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoEntregaSys2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatomphpdf")
	public void downloadmphFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalMPHsysPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoMPHSys2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatocorrectivopdf")
	public void downloadcorrectivoFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalCorrectivoPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoCorrectivo2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatoPreventivoSwitchpdf")
	public void downloadpreventivoSwitcFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalSwitchsysMPPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoPreventivosSwitch2023";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatobackuppdf")
	public void downloadBackupFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getOriginalBackpusPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoBackupInfraEstructuraTecnologica2023";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}
	
	
	@GetMapping("/formatompspdf")
	public void downloadmpsFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalMPSsysPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoMPSSys2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@PostMapping("/asignarcodigosys")
	public String asigncoderand(@RequestParam(value = "myTipo", defaultValue = "1") String id, Model model,
			Map<String, Object> map, RedirectAttributes flash) {

		List<SystemEquipo> equipos = Tipo_equipoService.findSystemEquiposbyTipoEquipo(Long.valueOf(id));
		int min_val = 100000 + 10000 * Integer.valueOf(id);
		int max_val = 100000 + 10000 * Integer.valueOf(id) + 10000;
		Random rand = new Random();
		IntStream stream = rand.ints(equipos.size(), min_val, max_val);
		List<Integer> nums = stream.boxed().toList();
		for (int n = 0; n < nums.size(); n++) {

			equipos.get(n).setCodigo(String.valueOf(nums.get(n)));
			SystemEquipoService.save(equipos.get(n));
		}
		// desordenando los elementos

		return "producto";
	}

	@PostMapping("/sysnuevoequipobytipo")
	public String syscrearnuevoequipomtto(@RequestParam(value = "myTipo", defaultValue = "IMPRESORA") String name,
			Model model, Map<String, Object> map, RedirectAttributes flash) {
		SystemEquipo equipo = new SystemEquipo();
		Tipo_equipo tipo = Tipo_equipoService.findbyName(name);
		List<Hospital> sedes = hospital_Service.ListHospital();
		Hospital hospital = HospitalService.findOne(1L);

		if (tipo == null) {
			return "redirect:/sysusuarios";
		} else {

			equipo.setTipo_equipo(tipo);
			equipo.setHospital(hospital);
			List<Servicio> servicios = ServicioService.ListServicio();
			model.addAttribute("servicios", servicios);
			model.addAttribute("sedes", sedes);
			map.put("sysequipo", equipo);

			return "sysnuevoequipo";
		}
	}
	
	@PostMapping("/sysnuevoequipo/{id}")
	public String guardarnuevoequipomtto(@PathVariable(value = "id") Long id, @Valid SystemEquipo sysequipo,
			BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
			@RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "daterange", defaultValue = "NR") String dias,
			@RequestParam(value = "myService") Long servicio,
			@RequestParam(value = "sede") Long sede) {

		LocalDate fechareporte = LocalDate.parse(fecha);
		Date fechaas = Date.valueOf(fechareporte);
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		LocalDate now = LocalDate.now();
		sysequipo.setFecha_modificacion(Date.valueOf(now));
		sysequipo.setUbicacion_anterior("EQUIPO NUEVO");
		sysequipo.setTipo_equipo(tipo_equipo);
		sysequipo.setHospital(HospitalService.findOne(sede));
		sysequipo.setServicio(ServicioService.findOne(servicio));
		sysequipo.setAno_ingreso(fechaas);
		sysequipo.setDias_mantenimiento(dias);
		SystemEquipoService.save(sysequipo);
		return "redirect:/sysequipos/" + tipo_equipo.getId_Tipo_equipo();
	}
	
	@PostMapping("/nuevaBajaSysEquipo/{idUser}/{idEquipo}")
	public String nuevabajasysEquipo(@PathVariable(value = "idUser") Long idUser,@ModelAttribute("sysBaja") SystemBaja sysBaja,
			@PathVariable(value = "idEquipo") Long idEquipo, BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) throws ParseException {
		SystemEquipo equipo = SystemEquipoService.findOne(idEquipo);
		Usuario usuario = UsuarioService.findOne(idUser);
		sysBaja.setEquipo(equipo);
		sysBaja.setUsuario(usuario);
		LocalDate todaysDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(todaysDate);
		sysBaja.setFechaBaja(date);
		SystemBajaService.save(sysBaja);
		SystemEquipoService.bajaEquipo(equipo.getId_Sysequipo());
		
		return "redirect:/systipos";
	}
	

	@GetMapping("/editarsysequipo/{id}")
	public String editarequipo(@PathVariable(value = "id") Long id, Model model, Map<String, Object> map,
			RedirectAttributes flash) {
		SystemEquipo equipo = SystemEquipoService.findOne(id);
		List<Servicio> servicios = ServicioService.ListServicio();

		model.addAttribute("servicios", servicios);
		model.addAttribute("sedes", hospital_Service.ListHospital());
		model.addAttribute("dias", equipo.getDias_mantenimiento());
		model.addAttribute("fecha", equipo.getAno_ingreso());
		model.addAttribute("idservice", equipo.getServicio().getId_Servicio());
		model.addAttribute("fechaingreso", equipo.getAno_ingreso());
		map.put("sysequipo", equipo);
		return "syseditarequipo";
	}
	
	@PostMapping("/ActualizarSwitch/{id}")
	public String actualizarSwitch(@PathVariable(value = "id") Long id, @Valid SystemEquipo sysequipo,
			BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
			@RequestParam(value = "direccionIP") String direccionIp,
			@RequestParam(value = "vlans") String direccionamientoVlan,
			@RequestParam(value = "numeroPuertos") int numeroPuertos,
			@RequestParam(value = "administrable") Boolean administrbale) {
		
		SystemEquipoService.updateSwitchData(id, direccionamientoVlan, numeroPuertos, administrbale);
		SystemHoja_vidaService.updateIPHv(id, direccionIp);
		return "redirect:/sysequipos/130";
	}
	
	
	@PostMapping("/syseditarequipo/{id}")
	public String editarequipomtto(@PathVariable(value = "id") Long id, @Valid SystemEquipo sysequipo,
			BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
			@RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "daterange", defaultValue = "NR") String dias,
			@RequestParam(value = "myService") Long servicio,
			@RequestParam(value = "sede", defaultValue = "0") Long sede
			) {

		LocalDate fechareporte = LocalDate.parse(fecha);
		Date fechaas = Date.valueOf(fechareporte);

		SystemEquipo equipo = SystemEquipoService.findOne(id);
		LocalDate now = LocalDate.now();
		equipo.setFecha_modificacion(Date.valueOf(now));
		equipo.setUbicacion_anterior(equipo.getUbicacion() + ' ' + equipo.getUbicacion_especifica());
		equipo.setServicio(ServicioService.findOne(servicio));
		equipo.setAno_ingreso(fechaas);
		equipo.setDias_mantenimiento(dias);
		equipo.setActivo(sysequipo.isActivo());
		equipo.setCodigo(sysequipo.getCodigo());
		equipo.setMarca(sysequipo.getMarca());
		equipo.setModelo(sysequipo.getModelo());
		equipo.setMtto(sysequipo.getMtto());
		equipo.setNombre_equipo(sysequipo.getNombre_equipo());
		equipo.setPeriodicidad(sysequipo.getPeriodicidad());
		equipo.setPlaca_inventario(sysequipo.getPlaca_inventario());
		equipo.setPreventivo_s(sysequipo.isPreventivo_s());
		equipo.setSerie(sysequipo.getSerie());
		equipo.setUbicacion(sysequipo.getUbicacion());
		equipo.setUbicacion_especifica(sysequipo.getUbicacion_especifica());
		if(sede != 0) {
		equipo.setHospital(hospital_Service.findOne(sede));
		}
		SystemEquipoService.save(equipo);
		return "redirect:/sysequipos/" + equipo.getTipo_equipo().getId_Tipo_equipo();

	}

	@GetMapping("/sysmantenimiento")
	public String sysmantenimiento(Model model, RedirectAttributes flash, SessionStatus status) {

		LocalDate hoy = LocalDate.now();
		int mes = hoy.getMonthValue();
		LocalDate past_date = hoy.minusMonths(1);
		int mes_anterior = past_date.getMonthValue();

		List<SystemEquipo> equipos = new ArrayList<SystemEquipo>();
		List<Boolean> mantenimiento = new ArrayList<>();
		if (mes == mes_anterior) {
			if (mes == 1) {
				equipos = SystemEquipoService.findbyEnero();

			} else if (mes == 2) {
				equipos = SystemEquipoService.findbyFebrero();
			} else if (mes == 3) {
				equipos = SystemEquipoService.findbyMarzo();
			} else if (mes == 4) {
				equipos = SystemEquipoService.findbyAbril();
			} else if (mes == 5) {
				equipos = SystemEquipoService.findbyMayo();
			} else if (mes == 6) {
				equipos = SystemEquipoService.findbyJunio();
			} else if (mes == 7) {
				equipos = SystemEquipoService.findbyJulio();
			} else if (mes == 8) {
				equipos = SystemEquipoService.findbyAgosto();
			} else if (mes == 9) {
				equipos = SystemEquipoService.findbySeptiembre();
			} else if (mes == 10) {
				equipos = SystemEquipoService.findbyOctubre();
			} else if (mes == 11) {
				equipos = SystemEquipoService.findbyNoviembre();
			} else if (mes == 12) {
				equipos = SystemEquipoService.findbyDiciembre();
			}
		} else {
			List<SystemEquipo> equipos2mes = new ArrayList<SystemEquipo>();
			if (mes == 1) {
				equipos = SystemEquipoService.findbyEnero();
				equipos2mes = SystemEquipoService.findbyDiciembre();
			} else if (mes == 2) {
				equipos = SystemEquipoService.findbyFebrero();
				equipos2mes = SystemEquipoService.findbyEnero();
			} else if (mes == 3) {
				equipos = SystemEquipoService.findbyMarzo();
				equipos2mes = SystemEquipoService.findbyFebrero();
			} else if (mes == 4) {
				equipos = SystemEquipoService.findbyAbril();
				equipos2mes = SystemEquipoService.findbyMarzo();
			} else if (mes == 5) {
				equipos = SystemEquipoService.findbyMayo();
				equipos2mes = SystemEquipoService.findbyAbril();
			} else if (mes == 6) {
				equipos = SystemEquipoService.findbyJunio();
				equipos2mes = SystemEquipoService.findbyMayo();
			} else if (mes == 7) {
				equipos = SystemEquipoService.findbyJulio();
				equipos2mes = SystemEquipoService.findbyJunio();
			} else if (mes == 8) {
				equipos = SystemEquipoService.findbyAgosto();
				equipos2mes = SystemEquipoService.findbyJulio();
			} else if (mes == 9) {
				equipos = SystemEquipoService.findbySeptiembre();
				equipos2mes = SystemEquipoService.findbyAgosto();
			} else if (mes == 10) {
				equipos = SystemEquipoService.findbyOctubre();
				equipos2mes = SystemEquipoService.findbySeptiembre();
			} else if (mes == 11) {
				equipos = SystemEquipoService.findbyNoviembre();
				equipos2mes = SystemEquipoService.findbyOctubre();
			} else if (mes == 12) {
				equipos = SystemEquipoService.findbyDiciembre();
				equipos2mes = SystemEquipoService.findbyNoviembre();
			}
			for (int i = 0; i < equipos2mes.size(); i++) {
				equipos.add(equipos2mes.get(i));
			}
			
			for (int i = 0; i < equipos.size(); i++) {
				mantenimiento.add(SystemMantenimientoService.MantenimientoEquipoAño(equipos.get(i).getId_Sysequipo()));
			}
		}

		model.addAttribute("equipos", equipos);
		model.addAttribute("fecha2", hoy);
		model.addAttribute("fecha1", past_date);
		model.addAttribute("validarMantenimiento", mantenimiento);
		return "sysmantenimiento";

	}

	@PostMapping("/sysmantenimiento")
	public String sysmantenimientosearch(Model model, @RequestParam(value = "daterange") String rangofechas,
			RedirectAttributes flash, SessionStatus status) {

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
		fecha1 = String.valueOf(ano1) + '-' + String.valueOf(mes1) + '-' + String.valueOf(dia1);
		fecha2 = String.valueOf(ano2) + '-' + String.valueOf(mes2) + '-' + String.valueOf(dia2);

		Date fecha_1 = Date.valueOf(fecha1);
		Date fecha_2 = Date.valueOf(fecha2);

		int mes = Integer.valueOf(fecha2div.get(0));

		int mes_anterior = Integer.valueOf(fecha1div.get(0));
		
		List<SystemEquipo> equipos = new ArrayList<SystemEquipo>();
		
		List<Boolean> mantenimiento = new ArrayList<>();
		if (mes == mes_anterior) {
			if (mes == 1) {
				equipos = SystemEquipoService.findbyEnero();

			} else if (mes == 2) {
				equipos = SystemEquipoService.findbyFebrero();
			} else if (mes == 3) {
				equipos = SystemEquipoService.findbyMarzo();
			} else if (mes == 4) {
				equipos = SystemEquipoService.findbyAbril();
			} else if (mes == 5) {
				equipos = SystemEquipoService.findbyMayo();
			} else if (mes == 6) {
				equipos = SystemEquipoService.findbyJunio();
			} else if (mes == 7) {
				equipos = SystemEquipoService.findbyJulio();
			} else if (mes == 8) {
				equipos = SystemEquipoService.findbyAgosto();
			} else if (mes == 9) {
				equipos = SystemEquipoService.findbySeptiembre();
			} else if (mes == 10) {
				equipos = SystemEquipoService.findbyOctubre();
			} else if (mes == 11) {
				equipos = SystemEquipoService.findbyNoviembre();
			} else if (mes == 12) {
				equipos = SystemEquipoService.findbyDiciembre();
			}
		} else {
			List<SystemEquipo> equipos2mes = new ArrayList<SystemEquipo>();
			if (mes == 1) {
				equipos = SystemEquipoService.findbyEnero();
				equipos2mes = SystemEquipoService.findbyDiciembre();
			} else if (mes == 2) {
				equipos = SystemEquipoService.findbyFebrero();
				equipos2mes = SystemEquipoService.findbyEnero();
			} else if (mes == 3) {
				equipos = SystemEquipoService.findbyMarzo();
				equipos2mes = SystemEquipoService.findbyFebrero();
			} else if (mes == 4) {
				equipos = SystemEquipoService.findbyAbril();
				equipos2mes = SystemEquipoService.findbyMarzo();
			} else if (mes == 5) {
				equipos = SystemEquipoService.findbyMayo();
				equipos2mes = SystemEquipoService.findbyAbril();
			} else if (mes == 6) {
				equipos = SystemEquipoService.findbyJunio();
				equipos2mes = SystemEquipoService.findbyMayo();
			} else if (mes == 7) {
				equipos = SystemEquipoService.findbyJulio();
				equipos2mes = SystemEquipoService.findbyJunio();
			} else if (mes == 8) {
				equipos = SystemEquipoService.findbyAgosto();
				equipos2mes = SystemEquipoService.findbyJulio();
			} else if (mes == 9) {
				equipos = SystemEquipoService.findbySeptiembre();
				equipos2mes = SystemEquipoService.findbyAgosto();
			} else if (mes == 10) {
				equipos = SystemEquipoService.findbyOctubre();
				equipos2mes = SystemEquipoService.findbySeptiembre();
			} else if (mes == 11) {
				equipos = SystemEquipoService.findbyNoviembre();
				equipos2mes = SystemEquipoService.findbyOctubre();
			} else if (mes == 12) {
				equipos = SystemEquipoService.findbyDiciembre();
				equipos2mes = SystemEquipoService.findbyNoviembre();
			}
			for (int i = 0; i < equipos2mes.size(); i++) {
				equipos.add(equipos2mes.get(i));
			}
			
		}
		
		for (int i = 0; i < equipos.size(); i++) {
			mantenimiento.add(SystemMantenimientoService.MantenimientoEquipoAño(equipos.get(i).getId_Sysequipo()));
		}

		model.addAttribute("equipos", equipos);
		model.addAttribute("fecha2", fecha_2);
		model.addAttribute("fecha1", fecha_1);
		model.addAttribute("validarMantenimiento", mantenimiento);
		return "sysmantenimiento";

	}
}
