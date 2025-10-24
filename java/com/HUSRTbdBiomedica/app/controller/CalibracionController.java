package com.HUSRTbdBiomedica.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.PlanActividadMetrologica;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.app.entity.Validacion;
import com.HUSRTbdBiomedica.service.ICalibracionService;
import com.HUSRTbdBiomedica.service.ICalificacionService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IPlanActividaMEtrologicaService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IValidacionService;
import com.HUSRTbdBiomedica.service.UploadFileService;
import java.time.LocalDate;
import java.time.YearMonth;

import com.HUSRTbdBiomedica.Utilidades;

@Controller
@RequestMapping
public class CalibracionController {

	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private Utilidades utilidades;
	
	@Autowired
	private ICalibracionService CalibracionService;
	
	@Autowired
	private IValidacionService ValidacionService;
	
	@Autowired
	private ICalificacionService CalificacionService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IPlanActividaMEtrologicaService planActividaMEtrologicaService;
	
	@GetMapping("/programarcalval")
	public String cuadrarCuatrimestrales(Model model) {
		
		model.addAttribute("tiposcalval", Tipo_equipoService.findTiposCalval());
		model.addAttribute("tiposnocalval", Tipo_equipoService.findTiposnoCalval());
		
		return "assigncalval";
	}
	@GetMapping(value = "/visualpdfcalibracion/{id}")
    public ResponseEntity<InputStreamResource> visualizarpdfcal(HttpServletRequest request,HttpServletResponse response,@PathVariable(value="id") Long id,
			  Map<String,Object>map,Model model,
			  RedirectAttributes flash) throws IOException{
    	Calibracion calibracion = CalibracionService.findOne(id);
    	File file = new File(calibracion.getCertificado());
    	HttpHeaders headers = new HttpHeaders();
    	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType("application/pdf"))
    			.body(resource);
    			
    }
	@GetMapping("/procesoscalval")
	public String procesos(Model model) {
		
		
		LocalDate fecha = LocalDate.now();
		List<Calibracion> calsAll = CalibracionService.findbyDateAll(fecha.getMonthValue(), fecha.getYear());
		List<Calibracion> calsinrealizar = new ArrayList<Calibracion>();
		List<Calibracion> calrealizado = new ArrayList<Calibracion>();
		List<PlanActividadMetrologica> actividadMEtrologica = planActividaMEtrologicaService.getPlanActividadMetrologica();
		List<Validacion> vals = ValidacionService.findvalbyDate(fecha.getMonthValue(), fecha.getYear());
		List<Validacion> valsinrealizar = new ArrayList<Validacion>();
		List<Validacion> valrealizado = new ArrayList<Validacion>();
			model.addAttribute("actividadMetrologica", actividadMEtrologica);
			model.addAttribute("calibraciones", calsAll);
		model.addAttribute("realizado",calrealizado);
		model.addAttribute("sinrealizar",calsinrealizar);
		model.addAttribute("vrealizado", valrealizado);
		model.addAttribute("vsinrealizar", valsinrealizar);
		model.addAttribute("val",vals);
		model.addAttribute("numCals",calsAll.size());
		model.addAttribute("mes",fecha.getMonthValue());
		model.addAttribute("ano",fecha.getYear());
		
		return "calibracion";
	}
	
	@PostMapping("/procesoscalvalfec")
	public String procesosFecha(Model model, @RequestParam(value="mesSel")String mesSel) {
		LocalDate fecha = utilidades.primerDiaDelMes(mesSel);
		System.out.println("ESTA ES LA FECHA:             -----------------------------     " + fecha);
		List<Calibracion> calsAll = CalibracionService.findbyDateAll(fecha.getMonthValue(), fecha.getYear());
		List<Calibracion> calsinrealizar = new ArrayList<Calibracion>();
		List<Calibracion> calrealizado = new ArrayList<Calibracion>();
		List<PlanActividadMetrologica> actividadMEtrologica = planActividaMEtrologicaService.getPlanActividadMetrologica();
		List<Validacion> vals = ValidacionService.findvalbyDate(fecha.getMonthValue(), fecha.getYear());
		List<Validacion> valsinrealizar = new ArrayList<Validacion>();
		List<Validacion> valrealizado = new ArrayList<Validacion>();
			model.addAttribute("actividadMetrologica", actividadMEtrologica);
			model.addAttribute("calibraciones", calsAll);
		model.addAttribute("realizado",calrealizado);
		model.addAttribute("sinrealizar",calsinrealizar);
		model.addAttribute("vrealizado", valrealizado);
		model.addAttribute("vsinrealizar", valsinrealizar);
		model.addAttribute("val",vals);
		model.addAttribute("numCals",calsAll.size());
		model.addAttribute("mes",fecha.getMonthValue());
		model.addAttribute("ano",fecha.getYear());
		
		
		return "calibracion";
	}
	
	@GetMapping("/nuevacalibracion/{id}")
	public String nuevacal(Model model,
			@PathVariable(value="id") Long id,
			Map<String,Object>map,RedirectAttributes flash) {
		
		Calibracion cal = CalibracionService.findOne(id);
		map.put("calibracion", cal);
		model.addAttribute("calibracion", cal);
		model.addAttribute("fechacal", LocalDate.now());
		model.addAttribute("fecharec", LocalDate.now());
		
		return "nuevacalibracion";
	}


	@PostMapping("agregarcalibracion/{id}")
	public String addCalibracion(@PathVariable Long id,
			@RequestParam("aprobo") String  aprobo,
			@RequestParam("calibro") String  calibro,
			@RequestParam("condiciones") String  condiciones,
			@RequestParam("empresa") String  empresa,
			@RequestParam("numerocer") String  numeroCer,
			@RequestParam("fechaprogramada") Date  fechaProgramada,
			@RequestParam("fechacalibrado") Date  fechaCalibrado,
			@RequestParam("fecharecibido") Date  fechaRecibido,
			@RequestParam("file") MultipartFile file
            ) throws IOException {
		
		@SuppressWarnings("deprecation")
		int dia = fechaProgramada.getDay();
		@SuppressWarnings("deprecation")
		int mes = fechaProgramada.getMonth();
		@SuppressWarnings("deprecation")
		int año = fechaProgramada.getYear();
		System.out.println("ID que llego: " + id);
		Equipo equipo = EquipoService.findOne(id);
		System.out.println("ID, Serie equipo encontrado:" + equipo.getId_Equipo() + ", " + equipo.getSerie());
		Calibracion calibracion = new Calibracion();
		String rutaDocumento = uploadFileService.saveCalibracion(file, id);
		calibracion.setAprobado_por(aprobo);
		calibracion.setCalibrado_por(calibro);
		calibracion.setEquipo(equipo);
		calibracion.setAno_programado(año);
		calibracion.setCertificado(rutaDocumento);
		calibracion.setCondiciones(condiciones);
		calibracion.setEmpresa(empresa);
		calibracion.setFecha_calibracion(fechaCalibrado);
		calibracion.setFecha_recepcion(fechaRecibido);
		calibracion.setMes_programado(mes);
		calibracion.setNumero_certificado(numeroCer);
		
		System.out.println(calibracion.getNumero_certificado());
		
		//CalibracionService.save(calibracion);
		
		System.out.println(calibracion.getCertificado());
		
		return "redirect:/";
		//return "redirect:/visualizaciontipoequipo/" + equipo.getTipo_equipo().getId_Tipo_equipo();
	}
	
	@PostMapping("/nuevacalibracion/{id}")
	public String guardarcal(@PathVariable Long id,@RequestParam(value="date")String fecha,
    		@RequestParam(value="daterec")String fecharec,
    		@RequestParam("file") MultipartFile file,
    		@Valid Calibracion calibracion,
    								  BindingResult result,
    								  Model model,
    								  RedirectAttributes flash,
    								  SessionStatus status) throws IOException {
		Calibracion cal = CalibracionService.findOne(id);
		
		
		String calibraciones_folder = "./src/main/resources/calibraciones/";
		uploadFileService.saveCalibracion(file,id);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		cal.setAprobado_por(calibracion.getAprobado_por());
		cal.setCalibrado_por(calibracion.getCalibrado_por());
		cal.setCondiciones(calibracion.getCondiciones());
		cal.setFecha_calibracion(Date.valueOf(LocalDate.parse(fecha)));
		cal.setFecha_recepcion(Date.valueOf(LocalDate.parse(fecharec)));
		cal.setCertificado(calibraciones_folder+id+ file.getOriginalFilename());
		cal.setEmpresa(calibracion.getEmpresa());
		cal.setNumero_certificado(calibracion.getNumero_certificado());
		CalibracionService.save(cal);
		status.setComplete();
		return "redirect:/procesoscalval";
	}
	
	
	
	
	
	@PostMapping("/procesoscalval")
	public String procesospost(Model model,
			RedirectAttributes flash,
			@RequestParam(value="Month",defaultValue = "0")String mes) {
		System.out.println(mes + "--------------------------");
		if(mes.equals("0")) {
			flash.addAttribute("InvalidMonth","Mes invalido");
			return "redirect:/procesoscalval";
		}
		else {
			String[] mesano = mes.split("-");

			int mescalval = Integer.valueOf(mesano[1]);
			int anocalval = Integer.valueOf(mesano[0]);
			System.out.println("Mes: " + mescalval + " -- Año: " + anocalval);
			List<PlanActividadMetrologica> actividadesMetrologica = planActividaMEtrologicaService.getActividadMetrologicaByMesAño(mescalval, anocalval);
			List<Calibracion> cals = CalibracionService.findbyDate(mescalval, anocalval);
			List<Calibracion> calsinrealizar = new ArrayList<Calibracion>();
			List<Calibracion> calrealizado = new ArrayList<Calibracion>();
			Calibracion calequipo = new Calibracion();
			
			
			List<Validacion> vals = ValidacionService.findvalbyDate(mescalval, anocalval);
			List<Validacion> valsinrealizar = new ArrayList<Validacion>();
			List<Validacion> valrealizado = new ArrayList<Validacion>();
			
			
			for(int c = 0;c<cals.size();c++) {
				calequipo = cals.get(c);
				if(calequipo.getNumero_certificado()!=null) {
					calrealizado.add(calequipo);
				}
				else {
					calsinrealizar.add(calequipo);
				}
			}
			Validacion valequipo = new Validacion();
			for(int v = 0;v<vals.size();v++) {
				valequipo = vals.get(v);
				if(valequipo.getNumero_certificado()!=null) {
					valrealizado.add(valequipo);
				}
				else {
					valsinrealizar.add(valequipo);
				}
				
			}
			int calrealize = CalibracionService.countCal(mescalval, anocalval) +ValidacionService.countValidacion(mescalval, anocalval);
			float advance = 0;
			if(cals.size()==0 || cals==null) {
				advance = (float)calrealize/1;
			}
			else {
				advance = (float)calrealize/(cals.size()+vals.size());
			}
			int advancecolor = 0;
			if (advance==1) {
				advancecolor=1;
			}
			else if(advance>=0.75 && advance<1) {
				advancecolor=2;
			}
			else if(advance>=0.5 && advance<0.75) {
				advancecolor=3;
			}
			else if(advance>=0.25 && advance<0.5) {
				advancecolor=4;
			}
			else {
				advancecolor=5;
			}
			model.addAttribute("realizado",calrealizado);
			model.addAttribute("sinrealizar",calsinrealizar);
			model.addAttribute("cal",actividadesMetrologica);
			model.addAttribute("vrealizado", valrealizado);
			model.addAttribute("vsinrealizar", valsinrealizar);
			model.addAttribute("val",vals);
			model.addAttribute("porcentaje",advance*100);
			model.addAttribute("advancecolor",advancecolor);
			model.addAttribute("mes",mescalval);
			model.addAttribute("ano",anocalval);
		}
	
		return "calibracion";
	}
	@PostMapping("/planeacioncalval")
	public String asignarguia(		
			@RequestParam(value="mes",defaultValue = "0")String meses,
			@RequestParam(value = "proceso",defaultValue = "0") String procesos ,
			@RequestParam(value = "checktipo",defaultValue = "0") String ids,
            Model model,
            RedirectAttributes flash,
            SessionStatus status) {
		if (meses.equals("0")||procesos.equals("0")||ids.equals("0")) {
			flash.addAttribute("unselect", "No selecciono ningún tipo");
			return "redirect:/programarcalval";
		}
		
		else {
			ArrayList<String> mesint = new ArrayList<String>(Arrays.asList(meses.split(",")));
			ArrayList<String> procesosint = new ArrayList<String>(Arrays.asList(procesos.split(",")));
			ArrayList<String> idsint = new ArrayList<String>(Arrays.asList(ids.split(",")));
			
			List<Equipo> equipos = new ArrayList<Equipo>();
			Equipo equipo = new Equipo();
			int process = 0;
			int mesp = 0;
			for(int i = 0;i<idsint.size();i++) {
				equipos = Tipo_equipoService.findEquiposbyTipoEquipo(Long.valueOf(idsint.get(i)));
				for(int j = 0;j<equipos.size();j++) {
					equipo = equipos.get(j);
					process = Integer.valueOf(procesosint.get(i));
					mesp = Integer.valueOf(mesint.get(i));
					if(process==1) {
						equipo.setCalibracion(mesp);
					}
					else if(process==2) {
						equipo.setValidacion(mesp);
					}
					else {
						equipo.setCalifiacion(mesp);
					}
					EquipoService.save(equipo);
				}
				
			}
			return "redirect:/calendario";
		}
		
		
		
	}
}
