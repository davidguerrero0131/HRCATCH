package com.HUSRTbdBiomedica.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Baja;
import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.Calificacion;
import com.HUSRTbdBiomedica.app.entity.Criterios;
import com.HUSRTbdBiomedica.app.entity.Criterios_economicos;
import com.HUSRTbdBiomedica.app.entity.Criterios_especificos;
import com.HUSRTbdBiomedica.app.entity.Criterios_tecnicos;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Reporte_baja;
import com.HUSRTbdBiomedica.app.entity.Validacion;
import com.HUSRTbdBiomedica.service.IBajaService;
import com.HUSRTbdBiomedica.service.ICalibracionService;
import com.HUSRTbdBiomedica.service.ICalificacionService;
import com.HUSRTbdBiomedica.service.ICriteriosService;
import com.HUSRTbdBiomedica.service.ICriterios_economicosService;
import com.HUSRTbdBiomedica.service.ICriterios_especificosService;
import com.HUSRTbdBiomedica.service.ICriterios_tecnicosService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.IReporte_bajaService;
import com.HUSRTbdBiomedica.service.IValidacionService;
import com.HUSRTbdBiomedica.service.Protocolo_preventivoServiceImp;
import com.HUSRTbdBiomedica.service.ReporteServiceImp;

@Controller
@SessionAttributes("baja")
@RequestMapping
public class BajaController {
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IBajaService BajaService;
	
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IReporte_bajaService Reporte_bajaService;
	
	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired 
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;
	
	@Autowired
	private ICriteriosService CriteriosService;
	
	@Autowired
	private ICalibracionService CalibracionService;
	
	@Autowired
	private ICalificacionService CalificacionService;
	
	@Autowired
	private IValidacionService ValidacionService;
	
	@Autowired
	private ICriterios_economicosService Criterios_economicosService;
	
	@Autowired
	private ICriterios_especificosService Criterios_especificosService;
	
	@Autowired
	private ICriterios_tecnicosService Criterios_tecnicosService;
	
	@GetMapping("/listarbajas")
	public String cuadrarCuatrimestrales(Model model) {
		
		model.addAttribute("bajas",BajaService.listBajas());
		return "listarbajas";
	}

	@GetMapping(value = "/formatoreportebaja/{id}")
    public String generacionformatoreportebaja(@PathVariable(value = "id") Long id,Model model,
    										RedirectAttributes flash) {
    	Reporte_baja reporte_baja = Reporte_bajaService.findOne(id);
    	Reporte reporte = ReporteService.findOneByNumber(reporte_baja.getNumero_reporte_baja());
    	model.addAttribute("fecha",reporte_baja.getFecha_baja());
    	model.addAttribute("idreporte",reporte.getId_Reporte());
    	model.addAttribute("numeroreporte",reporte_baja.getNumero_reporte_baja());
    	model.addAttribute("horallamado",reporte_baja.getHora_llamado_baja());
    	model.addAttribute("horainicio",reporte_baja.getHora_inicio_baja());
    	model.addAttribute("horafinal",reporte_baja.getHora_terminacion_baja());
    	model.addAttribute("equipo",reporte_baja.getNombre_equipo_baja());
    	model.addAttribute("marca",reporte_baja.getMarca_baja());
    	model.addAttribute("modelo",reporte_baja.getModelo_baja());
    	model.addAttribute("serie",reporte_baja.getSerie_baja());
    	model.addAttribute("inventario",reporte_baja.getPlaca_inventario_baja());
    	model.addAttribute("servicio",reporte_baja.getServicio_baja());
    	model.addAttribute("ubicacion",reporte_baja.getUbicacion_baja());
    	model.addAttribute("tipomtto",reporte_baja.getTipo_mantenimiento_baja());
    	model.addAttribute("tipofalla",reporte_baja.getTipo_falla_baja());
    	model.addAttribute("trabajo",reporte_baja.getTrabajo_realizado_baja());
    	model.addAttribute("repuestos",reporte_baja.getRepuesto_cambiado_baja());
    	model.addAttribute("observaciones",reporte_baja.getObservaciones_baja());
    	model.addAttribute("nombrerealizado",reporte_baja.getAutor_realizado_baja());
    	model.addAttribute("nombrerecibido",reporte_baja.getAutor_recibido_baja());
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findbyreport(id);
    	List<Protocolo_preventivo> protocols = new ArrayList<Protocolo_preventivo>();
    	Baja baja = reporte_baja.getBaja();
    	Time timer = Time.valueOf(LocalTime.now());
    	int hora = 0;
    	int minuto =0;
    	int minutos = 0;
    	ArrayList<String> materiales = new ArrayList<String>();
    	ArrayList<String> herramientas = new ArrayList<String>();
    	
    	ArrayList<String> repuestos = new ArrayList<String>();
    	if(mantenimiento_preventivo!=null) {
    		protocols = Protocolo_preventivoService.protocolobymtto(mantenimiento_preventivo.getId_Mantenimiento_preventivo());
    		timer = mantenimiento_preventivo.getTiempo_realizacion();
    		LocalTime tiempor = timer.toLocalTime();
    		hora = tiempor.getHour();minuto = tiempor.getMinute();
    		minutos = hora*60+minuto;
    		materiales = new ArrayList<String>(Arrays.asList(mantenimiento_preventivo.getTipo_equipo().getMaterial_consumible().split(",")));
    		herramientas = new ArrayList<String>(Arrays.asList(mantenimiento_preventivo.getTipo_equipo().getHerramienta().split(",")));
    		repuestos = new ArrayList<String>(Arrays.asList(mantenimiento_preventivo.getTipo_equipo().getRepuestos_minimos().split(",")));
    	}
    	else {
    		protocols = null;
    		
    		
    	}
    	
    	
		
		
		model.addAttribute("materiales",materiales);
		model.addAttribute("repuestos",repuestos);
		model.addAttribute("herramientas",herramientas);
    	model.addAttribute("mtto",mantenimiento_preventivo);
    	model.addAttribute("protocols",protocols);
    	model.addAttribute("minutos",minutos);
    	//Se requiere sumar la cedula, leugo de crear la estrategia con los usuarios
    	
    	return "formatoreporte";
    }
	@GetMapping(value="/reportesbaja/{id}")
	public String showreportesbaja(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Baja baja = BajaService.findOne(id);
		List<Reporte_baja> report = Reporte_bajaService.ReportesbyBaja(id);
		ArrayList<List<Protocolo_preventivo>> protocolos = new ArrayList<List<Protocolo_preventivo>>();
		ArrayList<String> repuestos = new ArrayList<String>();
		ArrayList<String> herramientas = new ArrayList<String>();
		ArrayList<String> materiales = new ArrayList<String>();
		for(int i = 0; i<report.size();i++) {
			if(report.get(i).getMantenimiento_preventivo()!=null) {
				List<Protocolo_preventivo> protocolo =Protocolo_preventivoService.protocolobymtto(report.get(i).getMantenimiento_preventivo().getId_Mantenimiento_preventivo());
				if(protocolo!=null) {
					protocolos.add(protocolo);
					materiales = new ArrayList<String>(Arrays.asList(report.get(i).getMantenimiento_preventivo().getTipo_equipo().getMaterial_consumible().split(",")));
					herramientas = new ArrayList<String>(Arrays.asList(report.get(i).getMantenimiento_preventivo().getTipo_equipo().getHerramienta().split(",")));
					repuestos = new ArrayList<String>(Arrays.asList(report.get(i).getMantenimiento_preventivo().getTipo_equipo().getRepuestos_minimos().split(",")));
					
				}
				
				
			}
			else {
				protocolos.add(null);
			}
			
			
		}
		
		model.addAttribute("protocols",protocolos);
		model.addAttribute("materiales",materiales);
		model.addAttribute("repuestos",repuestos);
		model.addAttribute("herramientas",herramientas);
		model.addAttribute("reportes",report);
		model.addAttribute("numreports",Reporte_bajaService.countReportesbyBaja(id));
		model.addAttribute("baja",baja);
		return "visualizacionreportesbaja";
	}
	@GetMapping(value="/buscarhvbaja/{id}")
	public String showhvformatobaja(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Baja baja = BajaService.findOne(id);
		
		
		model.addAttribute("period",1);
    	model.addAttribute("idequipo",baja.getId_Baja());
    	model.addAttribute("nombreequipo",baja.getNombre_Baja());
    	
    	model.addAttribute("marcaequipo",baja.getMarca_baja());
    	model.addAttribute("modeloequipo",baja.getModelo_baja());
    	model.addAttribute("serieequipo",baja.getSerie_baja());
    	model.addAttribute("servicioequipo",baja.getServicio_baja());
    	model.addAttribute("ubicacionequipo",baja.getUbicacion_baja());
    	
    	model.addAttribute("anoingresoequipo",baja.getAno_ingreso_baja());
    	model.addAttribute("placainventario",baja.getPlaca_inventario_baja());
    	model.addAttribute("contratoequipo",baja.getHoja_vida().getContrato());
    	model.addAttribute("hoja_vidaequipo",baja.getHoja_vida());
		return "formatohojadevida";
	}
	@GetMapping("/eliminarbaja/{id}")
	public String eliminarbaja(@PathVariable(value = "id") Long id,
            RedirectAttributes flash) {
		
		Baja baja = BajaService.findOne(id);
		Hoja_vida hoja_vida = baja.getHoja_vida();
		
		baja.setHoja_vida(null);
		if(hoja_vida!=null) {
			hoja_vida.setEquipo(null);
			Hoja_vidaService.delete(hoja_vida.getId_Hoja_vida());
			
		}
		
		List<Reporte_baja> reportes_baja = Reporte_bajaService.ReportesbyBaja(id);
		if(reportes_baja!=null) {
			for(int bajan=0;bajan<reportes_baja.size();bajan++) {
				reportes_baja.get(bajan).setBaja(null);
				Mantenimiento_preventivo mantenimiento_preventivo = reportes_baja.get(bajan).getMantenimiento_preventivo();
				if(mantenimiento_preventivo!=null) {
					List<Protocolo_preventivo> protocolos = Protocolo_preventivoService.protocolobymtto(mantenimiento_preventivo.getId_Mantenimiento_preventivo());
					for(int proto = 0; proto<protocolos.size();proto++) {
						protocolos.get(proto).setMantenimiento_preventivo(null);
						Protocolo_preventivoService.delete(protocolos.get(proto).getId_Procolo_preventivo());
					}
					mantenimiento_preventivo.setEquipo(null);
					mantenimiento_preventivo.setReporte(null);
					mantenimiento_preventivo.setTipo_equipo(null);
					mantenimiento_preventivo.setUsuario(null);
					Mantenimiento_preventivoService.delete(mantenimiento_preventivo.getId_Mantenimiento_preventivo());
				}
				reportes_baja.get(bajan).setMantenimiento_preventivo(null);
				Reporte_bajaService.delete(reportes_baja.get(bajan).getId_Reporte_baja());
			}
		}
		baja.setHospital(null);
		BajaService.delete(id);
		
		return "redirect:/listarbajas";
	}
	@GetMapping("/paradarbaja")
	public String vistaparadarbaja(Model model,
            RedirectAttributes flash) {
		List<Equipo> equipos = EquipoService.getAllequiposF();
		List<Equipo> malestado = new ArrayList<Equipo>();
		List<Equipo> regular = new ArrayList<Equipo>();
		List<Equipo> buenosonoev = new ArrayList<Equipo>();
		int valor = 0;
		for(int i = 0;i<equipos.size();i++) {
			if(CriteriosService.findLastEvaluation(equipos.get(i).getId_Equipo())!=null) {
				
				valor = CriteriosService.findOne(Long.valueOf(CriteriosService.findLastEvaluation(equipos.get(i).getId_Equipo()))).getTotal_puntos();
				if(Integer.valueOf(valor)<25) {
					malestado.add(equipos.get(i));
				}
				else if(Integer.valueOf(valor)>=25 && Integer.valueOf(valor)<60) {
					regular.add(equipos.get(i));
				}
				else {
					buenosonoev.add(equipos.get(i));
				}
				
			}
			else {
				buenosonoev.add(equipos.get(i));
			}
			
		}

		model.addAttribute("malos",malestado);
		model.addAttribute("regular",regular);
		model.addAttribute("otros",buenosonoev);
		
		return "recomendaciones";
	}
	@GetMapping("/darbaja/{id}")
	public String vistadarbaja(@PathVariable(value = "id") Long id,Model model,
            RedirectAttributes flash) {
		
		
		model.addAttribute("equipo",EquipoService.findOne(id));
		model.addAttribute("lastev",CriteriosService.findLastEvaluation(id));
		return "formulariobaja";
	}
//	@PostMapping("/darbaja/{id}")
//	public String darbaja(@PathVariable(value = "id") Long id,
//            RedirectAttributes flash,
//            @RequestParam(value="codigo") String codigo,
//            @RequestParam(value="preguntas") String respuestas,
//            @RequestParam(value="causa") String causa) {
//		Equipo equipo = EquipoService.findOne(id);
//		
//		
//		if(equipo.getCodigo().equals(codigo) && respuestas.split(",").length>1 ) {
//			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
//			Baja baja = new Baja();
//			if(hoja_vida!=null) {
//				baja.setHoja_vida(hoja_vida);
//				hoja_vida.setEquipo(null);
//			}
//			else {
//				baja.setHoja_vida(null);
//			}
//			
//			//baja.setId_Baja(equipo.getId_Equipo());
//			baja.setAno_ingreso_baja(equipo.getAno_ingreso());
//			baja.setCodigo(equipo.getCodigo());
//			
//			baja.setHospital(equipo.getHospital());
//			baja.setMarca_baja(equipo.getMarca());
//			baja.setModelo_baja(equipo.getModelo());
//			baja.setNombre_Baja(equipo.getNombre_Equipo());
//			baja.setPlaca_inventario_baja(equipo.getPlaca_inventario());
//			baja.setSerie_baja(equipo.getSerie());
//			baja.setServicio_baja(equipo.getServicios());
//			baja.setUbicacion_baja(equipo.getUbicacion());
//			baja.setUbicacion_especifica_baja(equipo.getUbicacion_especifica());
//			baja.setCausa(causa+respuestas);
//			
//			BajaService.save(baja);
//			//ordenlogicoeliminacion o reasignacion a baja
//			//protocolo->mantenimiento->reporte->hojavida->equipo
//			//reportes
//			List<Mantenimiento_preventivo> mttos = Mantenimiento_preventivoService.findbyEquipo(id);
//			
//			if(mttos!=null) {
//				for(int mtto = 0;mtto<mttos.size();mtto++) {
//					mttos.get(mtto).setEquipo(null);
//					mttos.get(mtto).setMes(0);
//				}
//				
//			}
//			List<Calibracion> cals = CalibracionService.listCalibracionbyEquipo(id);
//			if(cals!=null) {
//				for(int cal = 0;cal<cals.size();cal++) {
//					cals.get(cal).setEquipo(null);
//					cals.get(cal).setMes_programado(0);
//				}
//				
//			}
//			List<Calificacion> califs = CalificacionService.listCalificacionbyEquipo(id);
//			if(califs!= null) {
//				for(int calif = 0;calif<califs.size();calif++) {
//					califs.get(calif).setEquipo(null);
//					califs.get(calif).setMes_programado(0);
//				}
//			}
//			List<Validacion> vals = ValidacionService.listValidacionbyEquipo(id);
//			if(vals!=null) {
//				for(int val = 0;val<vals.size();val++)
//				{
//					vals.get(val).setEquipo(null);
//					vals.get(val).setMes_programado(0);
//				}
//			}
//			
//			List<Reporte> reportes = ReporteService.ReportesbyEquipo(id);
//			if(reportes!=null) {
//				for(int bajan=0;bajan<reportes.size();bajan++){
//					Reporte_baja reporte_baja = new Reporte_baja();
//					//reporte_baja.setId_Reporte_baja(reportes.get(bajan).getId_Reporte());
//					reporte_baja.setAutor_realizado_baja(reportes.get(bajan).getAutor_realizado());
//					reporte_baja.setAutor_recibido_baja(reportes.get(bajan).getAutor_recibido());
//					reporte_baja.setBaja(baja);
//					reporte_baja.setComodato_baja(reportes.get(bajan).isComodato());
//					reporte_baja.setComprobante_egreso_baja(reportes.get(bajan).getComprobante_ingreso());
//					reporte_baja.setFecha_baja(reportes.get(bajan).getFecha());
//					reporte_baja.setHora_inicio_baja(reportes.get(bajan).getHora_inicio());
//					reporte_baja.setHora_llamado_baja(reportes.get(bajan).getHora_llamado());
//					reporte_baja.setHora_terminacion_baja(reportes.get(bajan).getHora_terminacion());
//					reporte_baja.setMarca_baja(reportes.get(bajan).getMarca());
//					reporte_baja.setModelo_baja(reportes.get(bajan).getModelo());
//					reporte_baja.setMtto_propio_baja(reportes.get(bajan).isMtto_propio());
//					reporte_baja.setNombre_equipo_baja(reportes.get(bajan).getNombre_equipo());
//					reporte_baja.setNumero_reporte_baja(reportes.get(bajan).getNumero_reporte());
//					reporte_baja.setObservaciones_baja(reportes.get(bajan).getObservaciones());
//					reporte_baja.setPlaca_inventario_baja(reportes.get(bajan).getPlaca_inventario());
//					reporte_baja.setRepuesto_cambiado_baja(reportes.get(bajan).getRepuesto_cambiado());
//					reporte_baja.setSerie_baja(reportes.get(bajan).getSerie());
//					reporte_baja.setServicio_baja(reportes.get(bajan).getServicio());
//					reporte_baja.setTipo_falla_baja(reportes.get(bajan).getTipo_falla());
//					reporte_baja.setTipo_mantenimiento_baja(reportes.get(bajan).getTipo_mantenimiento());
//					reporte_baja.setTotal_horas_baja(reportes.get(bajan).getTotal_horas());
//					reporte_baja.setTrabajo_realizado_baja(reportes.get(bajan).getTrabajo_realizado());
//					reporte_baja.setUbicacion_baja(reportes.get(bajan).getUbicacion());
//					reporte_baja.setMantenimiento_preventivo(Mantenimiento_preventivoService.findbyreport(reportes.get(bajan).getId_Reporte()));
//					//12 august 2022
//					reporte_baja.setRutapdf_baja(reportes.get(bajan).getRutapdf());
//					reporte_baja.setMotivo_baja(reportes.get(bajan).getMotivo());
//					
//					Reporte_bajaService.save(reporte_baja);
//					reportes.get(bajan).setEquipo(null);
//					ReporteService.delete(reportes.get(bajan).getId_Reporte());
//				}
//			}
//			
//			
//			//eliminar equipo
//			equipo.setTipo_equipo(null);
//			equipo.setServicio(null);
//			equipo.setHospital(null);
//			
//			List<Criterios> criterios = CriteriosService.findbyEquipo(id);
//			if(criterios!=null) {
//				for(int cri=0;cri<criterios.size();cri++) {
//					Criterios_economicos criterios_economicos = criterios.get(cri).getCriterios_economicos();
//					Criterios_especificos criterios_especificos = criterios.get(cri).getCriterios_especificos();
//					Criterios_tecnicos criterios_tecnicos = criterios.get(cri).getCriterios_tecnicos();
//					Criterios criterio = criterios.get(cri);
//					criterio.setCriterios_economicos(null);
//					criterio.setCriterios_especificos(null);
//					criterio.setCriterios_tecnicos(null);
//					Criterios_economicosService.delete(criterios_economicos.getId_Criterios_economicos());
//					Criterios_especificosService.delete(criterios_especificos.getId_Criterios_especificos());
//					Criterios_tecnicosService.delete(criterios_tecnicos.getId_Criterios_tecnicos());
//					criterio.setEquipo(null);
//					CriteriosService.delete(criterio.getId_Criterios());
//				}
//			}
//			EquipoService.delete(equipo.getId_Equipo());
//			
//			return "redirect:/producto";
//		}
//		else {
//			return "redirect:/darbaja/"+id;
//		}
//		
//	}
	
	@PostMapping("/darbaja/{id}")
	public String darbaja(@PathVariable(value = "id") Long id,
            RedirectAttributes flash,
            @RequestParam(value="codigo") String codigo,
            @RequestParam(value="preguntas") String respuestas,
            @RequestParam(value="causa") String causa) {
		Equipo equipo = EquipoService.findOne(id);
		
		
		if(equipo.getCodigo().equals(codigo) && respuestas.split(",").length>1 ) {
			Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
			Baja baja = new Baja();
			if(hoja_vida!=null) {
				baja.setHoja_vida(hoja_vida);
			}
			
			//baja.setId_Baja(equipo.getId_Equipo());
			baja.setAno_ingreso_baja(equipo.getAno_ingreso());
			baja.setCodigo(equipo.getCodigo());
			
			baja.setHospital(equipo.getHospital());
			baja.setMarca_baja(equipo.getMarca());
			baja.setModelo_baja(equipo.getModelo());
			baja.setNombre_Baja(equipo.getNombre_Equipo());
			baja.setPlaca_inventario_baja(equipo.getPlaca_inventario());
			baja.setSerie_baja(equipo.getSerie());
			baja.setServicio_baja(equipo.getServicios());
			baja.setUbicacion_baja(equipo.getUbicacion());
			baja.setUbicacion_especifica_baja(equipo.getUbicacion_especifica());
			baja.setCausa(causa+respuestas);
			
			BajaService.save(baja);
			//ordenlogicoeliminacion o reasignacion a baja
			//protocolo->mantenimiento->reporte->hojavida->equipo
			//reportes

			
			List<Reporte> reportes = ReporteService.ReportesbyEquipo(id);
			if(reportes!=null) {
				for(int bajan=0;bajan<reportes.size();bajan++){
					Reporte_baja reporte_baja = new Reporte_baja();
					//reporte_baja.setId_Reporte_baja(reportes.get(bajan).getId_Reporte());
					reporte_baja.setAutor_realizado_baja(reportes.get(bajan).getAutor_realizado());
					reporte_baja.setAutor_recibido_baja(reportes.get(bajan).getAutor_recibido());
					reporte_baja.setBaja(baja);
					reporte_baja.setComodato_baja(reportes.get(bajan).isComodato());
					reporte_baja.setComprobante_egreso_baja(reportes.get(bajan).getComprobante_ingreso());
					reporte_baja.setFecha_baja(reportes.get(bajan).getFecha());
					reporte_baja.setHora_inicio_baja(reportes.get(bajan).getHora_inicio());
					reporte_baja.setHora_llamado_baja(reportes.get(bajan).getHora_llamado());
					reporte_baja.setHora_terminacion_baja(reportes.get(bajan).getHora_terminacion());
					reporte_baja.setMarca_baja(reportes.get(bajan).getMarca());
					reporte_baja.setModelo_baja(reportes.get(bajan).getModelo());
					reporte_baja.setMtto_propio_baja(reportes.get(bajan).isMtto_propio());
					reporte_baja.setNombre_equipo_baja(reportes.get(bajan).getNombre_equipo());
					reporte_baja.setNumero_reporte_baja(reportes.get(bajan).getNumero_reporte());
					reporte_baja.setObservaciones_baja(reportes.get(bajan).getObservaciones());
					reporte_baja.setPlaca_inventario_baja(reportes.get(bajan).getPlaca_inventario());
					reporte_baja.setRepuesto_cambiado_baja(reportes.get(bajan).getRepuesto_cambiado());
					reporte_baja.setSerie_baja(reportes.get(bajan).getSerie());
					reporte_baja.setServicio_baja(reportes.get(bajan).getServicio());
					reporte_baja.setTipo_falla_baja(reportes.get(bajan).getTipo_falla());
					reporte_baja.setTipo_mantenimiento_baja(reportes.get(bajan).getTipo_mantenimiento());
					reporte_baja.setTotal_horas_baja(reportes.get(bajan).getTotal_horas());
					reporte_baja.setTrabajo_realizado_baja(reportes.get(bajan).getTrabajo_realizado());
					reporte_baja.setUbicacion_baja(reportes.get(bajan).getUbicacion());
					reporte_baja.setMantenimiento_preventivo(Mantenimiento_preventivoService.findbyreport(reportes.get(bajan).getId_Reporte()));
					//12 august 2022
					reporte_baja.setRutapdf_baja(reportes.get(bajan).getRutapdf());
					reporte_baja.setMotivo_baja(reportes.get(bajan).getMotivo());
					
					Reporte_bajaService.save(reporte_baja);
				}
			}
			
			EquipoService.bajaEquipo(equipo.getId_Equipo());
			
			return "redirect:/producto";
		}
		else {
			return "redirect:/darbaja/"+id;
		}
		
	}
	
	@GetMapping(value = "/visualpdfreportbaja/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfreportebaja(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
    	Reporte_baja reporte = Reporte_bajaService.findOne(id);
    	File file = new File(reporte.getRutapdf_baja());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }


}
