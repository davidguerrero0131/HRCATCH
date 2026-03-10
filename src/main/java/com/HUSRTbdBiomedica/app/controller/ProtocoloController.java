package com.HUSRTbdBiomedica.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IMantenimientoMSVService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IProtocolo_preventivoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.UploadFileService;


@Controller
@RequestMapping
public class ProtocoloController {
	
	@Autowired
	private IProtocolo_preventivoService Protocolo_preventivoService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IMantenimientoMSVService iMantenimientoMSVService;

	@Autowired
	private UploadFileService uploadFileService;
	
	@GetMapping(value = "/rutinamtto/{id}/{idmtto}")
	public String generarrutina(@PathVariable(value="id") Long id,@PathVariable(value="idmtto") Long idmtto,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		Equipo equipo  = EquipoService.findOne(id);
		System.out.println(Protocolo_preventivoService.protocolobymtto(idmtto).size());
		if(Protocolo_preventivoService.protocolobymtto(idmtto).size()!=0){
			return "redirect:/nuevopreventivo/"+id+"/"+idmtto;
		}
		else {
			List<Protocolo_preventivo> protocolos_preventivo = new ArrayList<Protocolo_preventivo>();
			if(id.equals(662L)) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
			}
			else if(id.equals(416L)) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			else {
				protocolos_preventivo = Protocolo_preventivoService.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			List<Protocolo_preventivo> nuevos_protocolo_preventivo = new ArrayList<Protocolo_preventivo>();
			for(int proto=0;proto<protocolos_preventivo.size();proto++) {
				Protocolo_preventivo protocolo = new Protocolo_preventivo();
				protocolo.setTipo_equipo(protocolos_preventivo.get(proto).getTipo_equipo());
				protocolo.setMantenimiento_preventivo(Mantenimiento_preventivoService.findOne(idmtto));
				protocolo.setPaso(protocolos_preventivo.get(proto).getPaso());
				protocolo.setCumplimiento(false);

				nuevos_protocolo_preventivo.add(protocolo);
				
			}
			
			model.addAttribute("protocolos",nuevos_protocolo_preventivo);
			map.put("protocolo",nuevos_protocolo_preventivo);
			
			ArrayList<String> materiales = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getMaterial_consumible().split(",")));
			ArrayList<String> herramientas = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getHerramienta().split(",")));
			ArrayList<String> repuestos = new ArrayList<String>(Arrays.asList(equipo.getTipo_equipo().getRepuestos_minimos().split(",")));
			
			model.addAttribute("idmtto",idmtto);
			model.addAttribute("materiales",materiales);
			model.addAttribute("repuestos",repuestos);
			model.addAttribute("herramientas",herramientas);
			model.addAttribute("equipo",equipo);
			
			
			return "rutinamtto";
			
		}
		
	}
	@PostMapping(value = "/rutinamtto/{id}/{idmtto}")
	public String codnsaveprotocol(@PathVariable(value = "id") Long id,@PathVariable(value="idmtto") Long idmtto,		
			@RequestParam(value="cumplimientos")String cumplimientos,
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="codigo",defaultValue = "empty")String codigo,
            SessionStatus status) {
		
		Equipo equipo  = EquipoService.findOne(id);
		
		if(codigo.equals(equipo.getCodigo())) {
			System.out.println(codigo);
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(cumplimientos.split(",")));
			ArrayList<Integer> listchecknum = new ArrayList<Integer>();
			for(int i = 0;i<listcheck.size();i++) {
				listchecknum.add(i,Integer.valueOf(listcheck.get(i)));
			}
			
			
			
			List<Protocolo_preventivo> protocolos_preventivo = new ArrayList<Protocolo_preventivo>();
			if(id==662L) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
			}
			else if(id==416L) {
				protocolos_preventivo = Protocolo_preventivoService.protocoloexcepcion(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			else {
				protocolos_preventivo = Protocolo_preventivoService.protocologeneral(equipo.getTipo_equipo().getId_Tipo_equipo());
				
			}
			
			for(int proto=0;proto<protocolos_preventivo.size();proto++) {
				Protocolo_preventivo protocolo = new Protocolo_preventivo();
				protocolo.setTipo_equipo(protocolos_preventivo.get(proto).getTipo_equipo());
				protocolo.setMantenimiento_preventivo(Mantenimiento_preventivoService.findOne(idmtto));
				protocolo.setPaso(protocolos_preventivo.get(proto).getPaso());
				if(listchecknum.contains(proto)) {
					protocolo.setCumplimiento(true);
				}
				else {
					protocolo.setCumplimiento(false);
				}
				Protocolo_preventivoService.save(protocolo);

			}
			return "redirect:/nuevopreventivo/"+id+"/"+idmtto;
			
		}
		else {
			return "redirect:/rutinamtto/"+id+"/"+idmtto;
		}

	}
	@GetMapping(value="/nuevopreventivo/{id}/{idmtto}")
    public String generarnuevoreportepreventivo(@PathVariable(value="id") Long id,@PathVariable(value="idmtto") Long idmtto,
    								  Map<String,Object>model,Model modaladd,
    								  RedirectAttributes flash) {
    	Reporte reporte = new Reporte();
    	Equipo equipo=null;
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findOne(idmtto);
    	if(id>0){
    		equipo = EquipoService.findOne(id);
        	reporte.setEquipo(equipo);
        	mantenimiento_preventivo.setReporte(reporte);
    		if(equipo==null) {
    			flash.addFlashAttribute("error","El producto no existe en la base de datos");
    			return "redirect:/visualizacionreportes/{id}";
    		}
    		
    	}    	
    	else {
    		flash.addFlashAttribute("error","El ID no puede ser cero");
    		return "redirect:/visualizacionreportes/{id}";
    	}
    	modaladd.addAttribute("serieequipo",equipo.getSerie());
    	modaladd.addAttribute("placa",equipo.getPlaca_inventario());
    	modaladd.addAttribute("servicioequipo",equipo.getServicios());
    	modaladd.addAttribute("idequipo",equipo.getId_Equipo());
    	modaladd.addAttribute("idmtto",idmtto);
    	modaladd.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	modaladd.addAttribute("tipoequipo",equipo.getTipo_equipo());
    	modaladd.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	modaladd.addAttribute("periodicidad",equipo.getPeriodicidad());
    	modaladd.addAttribute("equipo", equipo);
    	model.put("equipo", equipo);
    	modaladd.addAttribute("reporte",reporte);
    	
    	modaladd.addAttribute("numeroreporte",ReporteService.LastIdReporte()+20001);
    	return "nuevopreventivo";
    }
    @PostMapping(value="/nuevopreventivo/{id}/{idmtto}")
    public String guardarnuevoreportepreventivo(@PathVariable Long id,@RequestParam(value="fecha")String fecha,
    		@PathVariable(value="idmtto") Long idmtto,
    		@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
    		@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@Valid Reporte reporte,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
    	
    	Equipo equipo = EquipoService.findOne(id);
        
    	LocalDate fechareporte = LocalDate.parse(fecha);
    	Date fechaas = Date.valueOf(fechareporte);
    	reporte.setFecha(fechaas);
    	
    	String hora1 = hora_llamado+":00";    	
    	Time hl = Time.valueOf(hora1);
    	reporte.setHora_llamado(hl);
    	
    	String hora2 = hora_inicio+":00";
    	Time hi = Time.valueOf(hora2);
    	reporte.setHora_inicio(hi);
    	
    	String hora3 = hora_finalizacion+":00";
    	Time hf = Time.valueOf(hora3);
    	reporte.setHora_terminacion(hf);
    	
    	LocalTime hinicio = hi.toLocalTime();
    	LocalTime hfinal = hf.toLocalTime();
    	LocalTime hginalminus = hfinal.minusHours(hinicio.getHour());
    	LocalTime thora = hginalminus.minusMinutes(hinicio.getMinute());
    	Time totalhoras = Time.valueOf(thora);
    	
    	reporte.setTotal_horas(totalhoras);
    	
    	reporte.setEquipo(equipo);
    	
    	reporte.setComodato(false);
    	reporte.setNombre_equipo(equipo.getNombre_Equipo());
    	reporte.setMarca(equipo.getMarca());
    	reporte.setModelo(equipo.getModelo());
    	reporte.setSerie(equipo.getSerie());
    	reporte.setPlaca_inventario(equipo.getPlaca_inventario());
    	reporte.setServicio(equipo.getServicios());
    	reporte.setUbicacion(equipo.getUbicacion());
    	String numr = Long.toString(ReporteService.LastIdReporte()+20001);
    	reporte.setNumero_reporte(numr);
    	reporte.setMtto_propio(true);
    	ReporteService.save(reporte);
    	
    	status.setComplete();
    	
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findOne(idmtto);
    	mantenimiento_preventivo.setReporte(reporte);
    	mantenimiento_preventivo.setEquipo(equipo);
    	mantenimiento_preventivo.setFecha_realizacion(fechaas);
    	mantenimiento_preventivo.setTiempo_realizacion(totalhoras);
    	mantenimiento_preventivo.setRealizado(true);
    	Mantenimiento_preventivoService.save(mantenimiento_preventivo);
    	
    	flash.addFlashAttribute("agregado","Reporte agregado correctamente");
    	flash.addFlashAttribute("nombreequipo",equipo.getNombre_Equipo());
    	flash.addFlashAttribute("serieequipo",equipo.getSerie());
    	
    	return "redirect:/mantenimiento";
    	
    }
    
    
    
    
    
    @PostMapping(value="/nuevopreventivoMSV/{id}/{idmtto}")
    public String guardarnuevoreportepreventivoMSV(@PathVariable Long id,@RequestParam(value="fecha")String fecha,
    		@PathVariable(value="idmtto") Long idmtto,
    		@RequestParam(value="hora_llamado",defaultValue = "00:00")String hora_llamado,
    		@RequestParam(value="hora_inicio",defaultValue = "00:00")String hora_inicio,
    		@RequestParam(value = "hora_finalizacion",defaultValue = "00:00")String hora_finalizacion,
    		@RequestParam(value = "65medidoSPO2") Double v65medidoSPO2,
    		@RequestParam(value = "75medidoSPO2") Double v75medidoSPO2,
    		@RequestParam(value = "100medidoSPO2") Double v100medidoSPO2,
    		@RequestParam(value = "45medidoECG") Double v45medidoECG,
    		@RequestParam(value = "60medidoECG") Double v60medidoECG,
    		@RequestParam(value = "120medidoECG") Double v120medidoECG,
    		@RequestParam(value = "180medidoECG") Double v180medidoECG,
    		@RequestParam(value = "120medidoSistolica") Double v120medidoSistolica,
    		@RequestParam(value = "80medidoDiastolica") Double v80medidoDiastolica,
    		@RequestParam(value = "92medidoMedia") Double v92medidoMedia,
    		@RequestParam(value = "200medidoSistolica") Double v200medidoSistolica,
    		@RequestParam(value = "150medidoDiastolica") Double v150medidoDiastolica,
    		@RequestParam(value = "167medidoMedia") Double v167medidoMedia,
    		@RequestParam(value = "60medidoSistolica") Double v60medidoSistolica,
    		@RequestParam(value = "30medidoDiastolica") Double v30medidoDiastolica,
    		@RequestParam(value = "40medidoMedia") Double v40medidoMedia,
    		@RequestParam(value = "80medidoSistolica") Double v80medidoSistolica,
    		@RequestParam(value = "50medidoDiastolica") Double v50medidoDiastolica,
    		@RequestParam(value = "60medidoMedia") Double v60medidoMedia,
    		@Valid Reporte reporte,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) {
    	
    	Equipo equipo = EquipoService.findOne(id);
        
    	LocalDate fechareporte = LocalDate.parse(fecha);
    	Date fechaas = Date.valueOf(fechareporte);
    	reporte.setFecha(fechaas);
    	
    	String hora1 = hora_llamado+":00";    	
    	Time hl = Time.valueOf(hora1);
    	reporte.setHora_llamado(hl);
    	
    	String hora2 = hora_inicio+":00";
    	Time hi = Time.valueOf(hora2);
    	reporte.setHora_inicio(hi);
    	
    	String hora3 = hora_finalizacion+":00";
    	Time hf = Time.valueOf(hora3);
    	reporte.setHora_terminacion(hf);
    	
    	
    	LocalTime hinicio = hi.toLocalTime();
    	LocalTime hfinal = hf.toLocalTime();
    	LocalTime hginalminus = hfinal.minusHours(hinicio.getHour());
    	LocalTime thora = hginalminus.minusMinutes(hinicio.getMinute());
    	Time totalhoras = Time.valueOf(thora);
    	
    	reporte.setTotal_horas(totalhoras);
    	
    	
    	
    	reporte.setEquipo(equipo);
    	
    	reporte.setComodato(false);
    	reporte.setNombre_equipo(equipo.getNombre_Equipo());
    	reporte.setMarca(equipo.getMarca());
    	reporte.setModelo(equipo.getModelo());
    	reporte.setSerie(equipo.getSerie());
    	reporte.setPlaca_inventario(equipo.getPlaca_inventario());
    	reporte.setServicio(equipo.getServicios());
    	reporte.setUbicacion(equipo.getUbicacion());
    	String numr = Long.toString(ReporteService.LastIdReporte()+20001);
    	reporte.setNumero_reporte(numr);
    	reporte.setMtto_propio(true);
    	ReporteService.save(reporte);
    	
    	status.setComplete();
    	
    	Mantenimiento_preventivo mantenimiento_preventivo = Mantenimiento_preventivoService.findOne(idmtto);
    	mantenimiento_preventivo.setReporte(reporte);
    	mantenimiento_preventivo.setEquipo(equipo);
    	mantenimiento_preventivo.setFecha_realizacion(fechaas);
    	mantenimiento_preventivo.setTiempo_realizacion(totalhoras);
    	mantenimiento_preventivo.setRealizado(true);
    	Mantenimiento_preventivoService.save(mantenimiento_preventivo);
    	
    	flash.addFlashAttribute("agregado","Reporte agregado correctamente");
    	flash.addFlashAttribute("nombreequipo",equipo.getNombre_Equipo());
    	flash.addFlashAttribute("serieequipo",equipo.getSerie());
    	
    	
    	System.out.println("------------------" + v92medidoMedia);
    	MantenimientoMSV mantenimientoMSV = new MantenimientoMSV();
    	mantenimientoMSV.setV65medidoSPO2(v65medidoSPO2);
    	mantenimientoMSV.setV75medidoSPO2(v75medidoSPO2);
    	mantenimientoMSV.setV100medidoSPO2(v100medidoSPO2);
    	mantenimientoMSV.setV45medidoECG(v45medidoECG);
    	mantenimientoMSV.setV60medidoECG(v60medidoECG);
    	mantenimientoMSV.setV120medidoECG(v120medidoECG);
    	mantenimientoMSV.setV180medidoECG(v180medidoECG);
    	mantenimientoMSV.setV120medidoSistolica(v120medidoSistolica);
    	mantenimientoMSV.setV80medidoDiastolica(v80medidoDiastolica);
    	mantenimientoMSV.setV92medidoMedia(v92medidoMedia);
    	mantenimientoMSV.setV200medidoSistolica(v200medidoSistolica);
    	mantenimientoMSV.setV150medidoDiastolica(v150medidoDiastolica);
    	mantenimientoMSV.setV167medidoMedia(v167medidoMedia);
    	mantenimientoMSV.setV60medidoSistolica(v60medidoSistolica);
    	mantenimientoMSV.setV30medidoDiastolica(v30medidoDiastolica);
    	mantenimientoMSV.setV40medidoMedia(v40medidoMedia);
    	mantenimientoMSV.setV80medidoSistolica(v80medidoSistolica);
    	mantenimientoMSV.setV50medidoDiastolica(v50medidoDiastolica);
    	mantenimientoMSV.setV60medidoMedia(v60medidoMedia);
    	mantenimientoMSV.setMantenimiento(mantenimiento_preventivo);
    	mantenimientoMSV.setEquipo(equipo);
    	
		iMantenimientoMSVService.addMantenimientoMSV(mantenimientoMSV);
    	
    	
    	return "redirect:/mantenimiento";
    	
    }
    
    
    
    
    
    
	
	@GetMapping(value = "/rutinaformatomtto/{id}")
	public String generarformatorutina(@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) {
		return "rutinaformatomtto";
	}
	
	@GetMapping(value ="/revisarguiarutina")
	public String reviewguiderut(Model model) {
		
		List<Equipo> equipos = EquipoService.ListEquipo();
		model.addAttribute("equipos", equipos);
		ArrayList<List<Protocolo_preventivo>> protocolos = new ArrayList<List<Protocolo_preventivo>>();
		Long id = 0L;
		for(int e = 0;e<equipos.size();e++) {

			id = equipos.get(e).getId_Equipo();
			if(id.equals(662L)) {
				protocolos.add(Protocolo_preventivoService.protocoloexcepcion(equipos.get(e).getTipo_equipo().getId_Tipo_equipo()));
			}
			else if(id.equals(416L)) {
				protocolos.add(Protocolo_preventivoService.protocoloexcepcion(equipos.get(e).getTipo_equipo().getId_Tipo_equipo()));
				
			}
			else {
				protocolos.add(Protocolo_preventivoService.protocologeneral(equipos.get(e).getTipo_equipo().getId_Tipo_equipo()));
				
			}
		}
		model.addAttribute("protocolos", protocolos);
		
		
		return "reviewguiderutine";
	}
	
	@PostMapping(value ="/asignarguia")
	public String asignarguia(		
			@RequestParam(value="guias",defaultValue = "EMPTY")String guias,
			@RequestParam("file") MultipartFile file,
            Model model,
            RedirectAttributes flash,
            SessionStatus status) throws IOException {
		
		String upload_folder = "./src/main/resources/guias/";
		if(guias.equals("EMPTY")) {
			flash.addAttribute("unselect", "No selecciono ningun equipo");
			return "redirect:/revisarguiarutina";
		}
		else {
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(guias.split(",")));
			ArrayList<Equipo> listchecknum = new ArrayList<Equipo>();
			for(int i = 0;i<listcheck.size();i++) {
				listchecknum.add(i,EquipoService.findOne(Long.valueOf(listcheck.get(i))));
			}
			
			for(int e = 0;e<listchecknum.size();e++) {
				listchecknum.get(e).setGuia(upload_folder+String.valueOf(listchecknum.get(0).getId_Equipo())+ file.getOriginalFilename());
				EquipoService.save(listchecknum.get(e));
			}
			uploadFileService.saveGuide(file,listchecknum.get(0).getId_Equipo());
		
			
			
			
			return "redirect:/clasificacionDHServicio";
		}
		
			

	}
	@GetMapping(value = "/visualpdfguia/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfguia(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
    	Equipo equipo = EquipoService.findOne(id);
    	File file = new File(equipo.getGuia());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
	
	@GetMapping(value = "/visualpdfficha/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfficha(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
    	Equipo equipo = EquipoService.findOne(id);
    	File file = new File(equipo.getFicha_Tecnica());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
	
}
