package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_comodatos;
import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.ObjetoCompuesto;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Responsable;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.ICalibracionService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IHoja_vida_comodatosService;
import com.HUSRTbdBiomedica.service.IMantenimientoMSVService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IResponsableService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

@Controller
@SessionAttributes("reporte")
@RequestMapping
public class ReporteController {

	@Autowired
	private IReporteService ReporteService;

	@Autowired
	private IHoja_vida_comodatosService comodatosService;

	@Autowired
	private ITipo_equipoService Tipo_equipoService;

	@Autowired
	private ICalibracionService CalibracionService;

	@Autowired
	private IEquipoService EquipoService;

	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;

	@Autowired
	private IMantenimientoMSVService mantenimientoMSVService;

	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@Autowired
	private IResponsableService responsableService;

	@GetMapping("/allReportes")
	public String showAllReportes(Model model) {
		model.addAttribute("todosreportes", ReporteService.ListReportes());
		return "visualizacionreportes";

	}

	@GetMapping("/visualizacionreportes/{id}")
	public String ShowReportesbyEquipo(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		List<Calibracion> calibraciones = CalibracionService.findcalbyequipocheck(id);
		List<Reporte> reportes = ReporteService.ReportesbyEquipo(id);
		List<Mantenimiento_preventivo> mttos = new ArrayList<Mantenimiento_preventivo>();
		List<List<Protocolo_preventivo>> protocols = new ArrayList<List<Protocolo_preventivo>>();
		for (int i = 0; i < reportes.size(); i++) {
			Mantenimiento_preventivo mtto = Mantenimiento_preventivoService
					.findbyreport(reportes.get(i).getId_Reporte());
			mttos.add(mtto);
			if (mtto != null) {
				List<Protocolo_preventivo> protocol = Protocolo_preventivoService
						.protocolobymtto(mtto.getId_Mantenimiento_preventivo());
				protocols.add(protocol);
			} else {
				protocols.add(null);
			}

		}

		ArrayList<String> materiales = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getMaterial_consumible().split(",")));
		ArrayList<String> herramientas = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getHerramienta().split(",")));
		ArrayList<String> repuestos = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getRepuestos_minimos().split(",")));

		model.addAttribute("calibraciones", calibraciones);
		model.addAttribute("materiales", materiales);
		model.addAttribute("repuestos", repuestos);
		model.addAttribute("herramientas", herramientas);
		model.addAttribute("nombreequipo", equipo.getNombre_Equipo());
		model.addAttribute("marcaequipo", equipo.getMarca());
		model.addAttribute("modeloequipo", equipo.getModelo());
		model.addAttribute("serieequipo", equipo.getSerie());
		model.addAttribute("servicioequipo", equipo.getServicios());
		model.addAttribute("placainventarioequipo", equipo.getPlaca_inventario());
		model.addAttribute("ubicacionequipo", equipo.getUbicacion());
		model.addAttribute("equipo", equipo);

		model.addAttribute("numeroreportes", ReporteService.countReportesbyEquipo(id));
		model.addAttribute("todosreportes", reportes);
		model.addAttribute("mttos", mttos);
		model.addAttribute("protocols", protocols);
		return "visualizacionreportes";
	}

	@GetMapping("/reportesComodatos/{serie}/{placa}")
	public String ShowReportesbySerieComodatos(@PathVariable(value = "serie") String serie,
			@PathVariable(value = "placa") String placa, Model model, RedirectAttributes flash) {

		List<Calibracion> calibraciones = CalibracionService.findcalbyequipocheck(Long.parseLong(0 + ""));
		List<Reporte> reportes = ReporteService.ReportesBySerieYPlaca(serie, placa);
		List<Mantenimiento_preventivo> mttos = new ArrayList<Mantenimiento_preventivo>();
		List<List<Protocolo_preventivo>> protocols = new ArrayList<List<Protocolo_preventivo>>();
		for (int i = 0; i < reportes.size(); i++) {
			Mantenimiento_preventivo mtto = Mantenimiento_preventivoService
					.findbyreport(reportes.get(i).getId_Reporte());
			mttos.add(mtto);
			if (mtto != null) {
				List<Protocolo_preventivo> protocol = Protocolo_preventivoService
						.protocolobymtto(mtto.getId_Mantenimiento_preventivo());
				protocols.add(protocol);
			} else {
				protocols.add(null);
			}

		}

		model.addAttribute("calibraciones", calibraciones);
		model.addAttribute("numeroreportes", ReporteService.countReportesbyEquipo(Long.parseLong(0 + "")));
		model.addAttribute("todosreportes", reportes);
		model.addAttribute("mttos", mttos);
		model.addAttribute("protocols", protocols);
		return "VistaReportesComodatos";
	}

	@GetMapping(value = "/visualizaciontipoequipo/{id}")
	public String showequiposbytipoequipo(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		model.addAttribute("nombrelineaequipo", tipo_equipo.getNombre_Tipo_equipo());

		model.addAttribute("equipos_tipos", Tipo_equipoService.findEquiposbyTipoEquipo(id));
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(Tipo_equipoService.findEquiposbyTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
		return "visualizaciontipoequipo";
	}

	@GetMapping(value = "/visualizacionequiposanuales/{id}")
	public String showequiposanualesbytipoequipo(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		model.addAttribute("nombrelineaequipo", tipo_equipo.getNombre_Tipo_equipo());
		model.addAttribute("equipos_tipos", EquipoService.findEquiposAnual(id));
		model.addAttribute("numeroequiposportipo", Tipo_equipoService.countEbyTipoEquipobyP(1, id));
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(Tipo_equipoService.findEquiposbyTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
		return "visualizacionequiposanuales";
	}

	@GetMapping(value = "/visualizacionequipossemestrales/{id}")
	public String showequipossemestralesbytipoequipo(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		model.addAttribute("nombrelineaequipo", tipo_equipo.getNombre_Tipo_equipo());
		model.addAttribute("equipos_tipos", EquipoService.findEquiposSemestral(id));
		model.addAttribute("numeroequiposportipo", Tipo_equipoService.countEbyTipoEquipobyP(2, id));
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(Tipo_equipoService.findEquiposbyTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
        
		return "visualizacionequipossemestrales";
	}

	@GetMapping(value = "/visualizacionequiposcuatrimestrales/{id}")
	public String showequiposcuatrimestralesbytipoequipo(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		model.addAttribute("nombrelineaequipo", tipo_equipo.getNombre_Tipo_equipo());
		model.addAttribute("equipos_tipos", EquipoService.findEquiposCuatrimestral(id));
		model.addAttribute("numeroequiposportipo", Tipo_equipoService.countEbyTipoEquipobyP(4, id));
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(Tipo_equipoService.findEquiposbyTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
		
		return "visualizacionequiposcuatrimestrales";
	}
	
	
	@GetMapping(value = "/visualizacionequiposcomodatos/{id}")
	public String showequiposcomodatosbytipoequipo(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		
		Responsable responsable = responsableService.findOne(id);
		model.addAttribute("nombrelineaequipo", responsable.getNombre_responsable());
		model.addAttribute("equiposResponsable", EquipoService.getEquiposResponsableComodato(id));
		model.addAttribute("numeroequiposportipo", EquipoService.getEquiposResponsableComodato(id).size());
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(EquipoService.getComodatosPorTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
		
		return "visualizacionequiposcomodatos";
	}

	@GetMapping(value = "/visualizacionequipostrimestrales/{id}")
	public String showequipostrimestralesbytipoequipo(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		model.addAttribute("nombrelineaequipo", tipo_equipo.getNombre_Tipo_equipo());
		model.addAttribute("equipos_tipos", EquipoService.findEquiposTrimestral(id));
		model.addAttribute("numeroequiposportipo", Tipo_equipoService.countEbyTipoEquipobyP(3, id));
		
		ArrayList<Equipo> listEquipo = new ArrayList<>(Tipo_equipoService.findEquiposbyTipoEquipo(id));
        ArrayList<ObjetoCompuesto<Equipo, Hoja_vida>> listEquipoHv = new ArrayList<>();
        
        for (int i = 0; i < listEquipo.size(); i++) {
        	try {
    			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(listEquipo.get(i).getId_Equipo());
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			} catch (Exception e) {
    			Hoja_vida hoja_vida = null;
    			listEquipoHv.add(new ObjetoCompuesto<Equipo, Hoja_vida>(listEquipo.get(i), hoja_vida));
			}
		}
        
        model.addAttribute("equipoHv", listEquipoHv);
		
		return "visualizacionequipostrimestrales";
	}

	@GetMapping(value = "/formatoreporte/{id}")
	public String generacionformatoreporte(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reporte reporte = ReporteService.findOne(id);
		model.addAttribute("fecha", reporte.getFecha());
		model.addAttribute("idreporte", reporte.getId_Reporte());
		model.addAttribute("numeroreporte", reporte.getNumero_reporte());
		model.addAttribute("horallamado", reporte.getHora_llamado());
		model.addAttribute("horainicio", reporte.getHora_inicio());
		model.addAttribute("horafinal", reporte.getHora_terminacion());
		model.addAttribute("equipo", reporte.getNombre_equipo());
		model.addAttribute("marca", reporte.getMarca());
		model.addAttribute("modelo", reporte.getModelo());
		model.addAttribute("serie", reporte.getSerie());
		model.addAttribute("inventario", reporte.getPlaca_inventario());
		model.addAttribute("servicio", reporte.getServicio());
		model.addAttribute("ubicacion", reporte.getUbicacion());
		model.addAttribute("tipomtto", reporte.getTipo_mantenimiento());
		model.addAttribute("tipofalla", reporte.getTipo_falla());
		model.addAttribute("trabajo", reporte.getTrabajo_realizado());
		model.addAttribute("repuesto", reporte.getRepuesto_cambiado());
		model.addAttribute("cegreso", reporte.getComprobante_ingreso());

		model.addAttribute("motivo", reporte.getMotivo());
		model.addAttribute("observaciones", reporte.getObservaciones());
		model.addAttribute("nombrerealizado", reporte.getAutor_realizado());
		model.addAttribute("nombrerecibido", reporte.getAutor_recibido());
		Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findbyreport(id);
		List<Protocolo_preventivo> protocols = new ArrayList<Protocolo_preventivo>();
		Equipo equipo = reporte.getEquipo();
		Time timer = Time.valueOf(LocalTime.now());
		int hora = 0;
		int minuto = 0;
		int minutos = 0;
		if (mantenimiento_preventivo != null) {
			protocols = Protocolo_preventivoService
					.protocolobymtto(mantenimiento_preventivo.getId_Mantenimiento_preventivo());
			timer = mantenimiento_preventivo.getTiempo_realizacion();
			LocalTime tiempor = timer.toLocalTime();
			hora = tiempor.getHour();
			minuto = tiempor.getMinute();
			minutos = hora * 60 + minuto;
		} else {
			protocols = null;

		}

		ArrayList<String> materiales = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getMaterial_consumible().split(",")));
		ArrayList<String> herramientas = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getHerramienta().split(",")));
		ArrayList<String> repuestos = new ArrayList<String>(
				Arrays.asList(equipo.getTipo_equipo().getRepuestos_minimos().split(",")));

		model.addAttribute("materiales", materiales);
		model.addAttribute("repuestos", repuestos);
		model.addAttribute("herramientas", herramientas);
		model.addAttribute("mtto", mantenimiento_preventivo);
		model.addAttribute("protocols", protocols);
		model.addAttribute("minutos", minutos);

		MantenimientoMSV mantenimientoMSV = null;
		if (reporte.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 9 && mantenimiento_preventivo != null) {
			mantenimientoMSV = mantenimientoMSVService.getMantenimientoMSVEspec(
					mantenimiento_preventivo.getEquipo().getId_Equipo(),
					mantenimiento_preventivo.getId_Mantenimiento_preventivo());
			// Se requiere sumar la cedula, leugo de crear la estrategia con los usuarios

		}
		model.addAttribute("mttoMSV", mantenimientoMSV);
		return "formatoreporte";
	}

	@GetMapping(value = "/visualpdfreport/{id}")
	public ResponseEntity<InputStreamResource> visualizarpdfreporte(HttpServletRequest request,
			HttpServletResponse response, @PathVariable(value = "id") Long id, Map<String, Object> map, Model model,
			RedirectAttributes flash) throws IOException {
		Reporte reporte = ReporteService.findOne(id);
		File file = new File(reporte.getRutapdf());
		HttpHeaders headers = new HttpHeaders();

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);

	}

	@GetMapping(value = "/nuevoreporte/{id}")
	public String generarnuevoreporte(@PathVariable(value = "id") Long id, Map<String, Object> model, Model modaladd,
			RedirectAttributes flash) {
		Reporte reporte = new Reporte();
		Equipo equipo = null;
		if (id > 0) {
			equipo = EquipoService.findOne(id);
			reporte.setEquipo(equipo);
			if (equipo == null) {
				flash.addFlashAttribute("error", "El producto no existe en la base de datos");
				return "redirect:/visualizacionreportes/{id}";
			}

		}

		else {
			flash.addFlashAttribute("error", "El ID no puede ser cero");
			return "redirect:/visualizacionreportes/{id}";
		}
		modaladd.addAttribute("serieequipo", equipo.getSerie());
		modaladd.addAttribute("placa", equipo.getPlaca_inventario());
		modaladd.addAttribute("servicioequipo", equipo.getServicios());
		modaladd.addAttribute("idequipo", equipo.getId_Equipo());
		modaladd.addAttribute("ubicacionequipo", equipo.getUbicacion());
		modaladd.addAttribute("tipoequipo", equipo.getTipo_equipo());
		modaladd.addAttribute("nombreequipo", equipo.getNombre_Equipo());
		modaladd.addAttribute("periodicidad", equipo.getPeriodicidad());
		model.put("equipo", equipo);
		modaladd.addAttribute("reporte", reporte);

		modaladd.addAttribute("numeroreporte", ReporteService.LastIdReporte() + 20001);

		Date defaultdate = Date.valueOf(LocalDate.now());
		Time defaulthour = Time.valueOf(LocalTime.now());
		Time defaultcall = Time.valueOf(LocalTime.parse("00:00"));
		modaladd.addAttribute("fecha", defaultdate);
		modaladd.addAttribute("horallamado", defaultcall);
		modaladd.addAttribute("horainicio", defaulthour);
		modaladd.addAttribute("horaterminacion", defaulthour);

		return "nuevoreporte";
	}

	@GetMapping("/download/{id}")
	public void downloadFile(HttpServletResponse response, @PathVariable Long id)
			throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();
		Reporte reporte = ReporteService.findOne(id);
		Mantenimiento_preventivo mtto = Mantenimiento_preventivoService.findbyreport(reporte.getId_Reporte());

		List<Protocolo_preventivo> protocols = new ArrayList<Protocolo_preventivo>();
		if (mtto != null) {
			protocols = Protocolo_preventivoService.protocolobymtto(mtto.getId_Mantenimiento_preventivo());

		} else {
			protocols = null;
		}
		MantenimientoMSV mantenimientoMSV = null;
		if (mtto != null && reporte.getTipo_mantenimiento() == 3 && reporte.getEquipo().getTipo_equipo().getId_Tipo_equipo() == 9) {
			mantenimientoMSV = mantenimientoMSVService
					.getMantenimientoMSVEspec(mtto.getEquipo().getId_Equipo(), mtto.getId_Mantenimiento_preventivo());
			if (mantenimientoMSV == null) {
				mantenimientoMSV = new MantenimientoMSV();
				mantenimientoMSV.setEquipo(mtto.getEquipo());
				mantenimientoMSV.setMantenimiento(mtto);
			}
		}
		byte[] pdfReport = generator.getPDF(ReporteService.findOne(id), mtto, mantenimientoMSV, protocols)
				.toByteArray();

		String mimeType = "application/pdf";
		String namefile = reporte.getNumero_reporte();
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());

	}

	@GetMapping("/formatoreportepdf")
	public void downloadReportFormat(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getoriginalPDF().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoReportes2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/formatoreporteMSVpdf")
	public void downloadReportFormatMSV(HttpServletResponse response) throws IOException, DocumentException {
		PdfGenarator generator = new PdfGenarator();

		byte[] pdfReport = generator.getPDFMSV().toByteArray();

		String mimeType = "application/pdf";
		String namefile = "formatoReportes2022";
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", namefile + ".pdf"));

		response.setContentLength(pdfReport.length);

		ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);

		FileCopyUtils.copy(inStream, response.getOutputStream());
	}

	@GetMapping("/editreporte/{id}")
	public String editarreporte(@PathVariable(value = "id") Long id, Model model, Map<String, Object> map,
			RedirectAttributes flash) {
		Reporte reporte = ReporteService.findOne(id);
		Equipo equipo = reporte.getEquipo();

		model.addAttribute("serieequipo", equipo.getSerie());
		model.addAttribute("placa", equipo.getPlaca_inventario());
		model.addAttribute("servicioequipo", equipo.getServicios());
		model.addAttribute("idequipo", equipo.getId_Equipo());
		model.addAttribute("ubicacionequipo", equipo.getUbicacion());
		model.addAttribute("tipoequipo", equipo.getTipo_equipo());
		model.addAttribute("nombreequipo", equipo.getNombre_Equipo());
		model.addAttribute("periodicidad", equipo.getPeriodicidad());
		map.put("reporte", reporte);

		model.addAttribute("equipo", equipo);

		model.addAttribute("numeroreporte", reporte.getNumero_reporte());

		model.addAttribute("fecha", reporte.getFecha());
		model.addAttribute("horallamado", reporte.getHora_llamado());
		model.addAttribute("horainicio", reporte.getHora_inicio());
		model.addAttribute("horaterminacion", reporte.getHora_terminacion());
		if (equipo.getId_Equipo() != 11111L) {
			return "nuevoreportedit";
		} else {
			return "nuevoreporteditotro";
		}
	}

	@PostMapping(value = "/nuevoreportedit/{id}")
	public String guardarnuevoreportedit(@PathVariable Long id, @RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "hora_llamado", defaultValue = "00:00") String hora_llamado,
			@RequestParam(value = "hora_inicio", defaultValue = "00:00") String hora_inicio,
			@RequestParam(value = "hora_finalizacion", defaultValue = "00:00") String hora_finalizacion,
			@Valid Reporte reporte, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		Equipo equipo = EquipoService.findOne(id);

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

		if (equipo.getId_Equipo() != 11111L) {
			reporte.setNombre_equipo(equipo.getNombre_Equipo());
			reporte.setMarca(equipo.getMarca());
			reporte.setModelo(equipo.getModelo());
			reporte.setSerie(equipo.getSerie());
			reporte.setPlaca_inventario(equipo.getPlaca_inventario());
			reporte.setServicio(equipo.getServicios());
			reporte.setUbicacion(equipo.getUbicacion());
		}

		ReporteService.save(reporte);

		status.setComplete();

		flash.addFlashAttribute("agregado", "Reporte agregado correctamente");
		flash.addFlashAttribute("nombreequipo", equipo.getNombre_Equipo());
		flash.addFlashAttribute("serieequipo", equipo.getSerie());
		if (equipo.getId_Equipo() != 11111L) {
			return "redirect:/visualizacionreportes/" + equipo.getId_Equipo();
		} else {
			return "redirect:/visualizacionotrosreportes";
		}

	}

	@PostMapping(value = "/nuevoreporte/{id}")
	public String guardarnuevoreporte(@PathVariable Long id, @RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "hora_llamado", defaultValue = "00:00") String hora_llamado,
			@RequestParam(value = "hora_inicio", defaultValue = "00:00") String hora_inicio,
			@RequestParam(value = "hora_finalizacion", defaultValue = "00:00") String hora_finalizacion,
			@Valid Reporte reporte, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		Equipo equipo = EquipoService.findOne(id);

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

		reporte.setNombre_equipo(equipo.getNombre_Equipo());
		reporte.setMarca(equipo.getMarca());
		reporte.setModelo(equipo.getModelo());
		reporte.setSerie(equipo.getSerie());
		reporte.setPlaca_inventario(equipo.getPlaca_inventario());
		reporte.setServicio(equipo.getServicios());
		reporte.setUbicacion(equipo.getUbicacion());

		String numr = Long.toString(ReporteService.LastIdReporte() + 20001);
		reporte.setNumero_reporte(numr);

		ReporteService.save(reporte);

		status.setComplete();

		flash.addFlashAttribute("agregado", "Reporte agregado correctamente");
		flash.addFlashAttribute("nombreequipo", equipo.getNombre_Equipo());
		flash.addFlashAttribute("serieequipo", equipo.getSerie());

		return "redirect:/visualizacionreportes/" + equipo.getId_Equipo();

	}

	@PostMapping("/reporteselect")
	public List<Reporte> saveSelected(@RequestBody JSONObject params) {

		List<Reporte> reportes = new ArrayList<Reporte>();
		reportes.add(ReporteService.findOne(10L));
		System.out.println(params.getString("username"));
		return reportes;
	}

	@GetMapping("NuevoReporteComodato/{id}")
	public String nuevoReporteComodato(@PathVariable(value = "id") Long id, Map<String, Object> map, Model model,
			RedirectAttributes flash) {
		Hoja_vida_comodatos hoja_vida_comodatos = comodatosService.findOne(id);
		Reporte reporte = new Reporte();
		reporte.setEquipo(EquipoService.findOne(11111L));
		model.addAttribute("numeroreporte", ReporteService.LastIdReporte() + 20001);
		map.put("reporte", reporte);
		model.addAttribute("HojaVidaComodato", hoja_vida_comodatos);
		return "nuevoReporteComodato";
	}

	@PostMapping(value = "/nuevoreporteComodato/{id}")
	public String guardarnuevoreportecomodato(@PathVariable(value = "id") Long idHVComodato,
			@RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "hora_llamado", defaultValue = "00:00") String hora_llamado,
			@RequestParam(value = "hora_inicio", defaultValue = "00:00") String hora_inicio,
			@RequestParam(value = "hora_finalizacion", defaultValue = "00:00") String hora_finalizacion,
			@ModelAttribute Reporte reporte, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		Hoja_vida_comodatos hoja_vida_comodatos = comodatosService.findOne(idHVComodato);
		reporte.setNombre_equipo(hoja_vida_comodatos.getNombre_equipo_otros());
		reporte.setMarca(hoja_vida_comodatos.getMarca_otros());
		reporte.setModelo(hoja_vida_comodatos.getModelo_otros());
		reporte.setSerie(hoja_vida_comodatos.getSerie_otros());
		reporte.setPlaca_inventario(hoja_vida_comodatos.getPlaca_inventario_otros());
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
		String numr = Long.toString(ReporteService.LastIdReporte() + 20001);
		reporte.setNumero_reporte(numr);

		ReporteService.save(reporte);
		return "redirect:/comodatos/" + hoja_vida_comodatos.getProveedor_otros();
	}

}
