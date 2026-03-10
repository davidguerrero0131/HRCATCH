package com.HUSRTbdBiomedica.app.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.Dao.IDocumentoDao;
import com.HUSRTbdBiomedica.app.Dao.ITipoDocumentoDao;
import com.HUSRTbdBiomedica.app.entity.Documento;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.TipoDocumento;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.IDocumentoService;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.PdfGenarator;
import com.HUSRTbdBiomedica.service.UploadFileService;
import com.lowagie.text.DocumentException;

@Controller
@SessionAttributes("hoja_vida")
@RequestMapping
public class Hoja_vidaController {
	@Autowired
	private IHoja_vidaService Hoja_vidaService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private IDocumentoDao documentoDao;
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Autowired
	private IDocumentoService documentoService;
	
	@GetMapping(value = "/matrizhv/{id}")
    public String showhvequipo(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {
		
    	Equipo equipo = EquipoService.findOne(id);
    	
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("diasmtto",equipo.getDias_mantenimiento());
    	model.addAttribute("mesesmtto",equipo.getMeses_mantenimiento());
    	model.addAttribute("anomtto",equipo.getAno_mantenimiento());
    	model.addAttribute("anoingresoequipo",equipo.getAno_ingreso());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
    	
    	
    	try {
    		Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
    		model.addAttribute("hoja_vidaequipo",hoja_vida);
    	}
    	catch(Exception e) {
    		return "redirect:/listarhvrepeat/"+equipo.getId_Equipo();
    	}
    	
    	
      
        return "matrizhv";
    }
	@GetMapping(value = "/listarhvrepeat/{id}")
	public String gotodeletehv(@PathVariable(value = "id") Long id,
            Model model,            
            RedirectAttributes flash) {
		
		List<Hoja_vida> hojas_vida = Hoja_vidaService.ObtainallHV();
		
		model.addAttribute("todashv",hojas_vida);
		model.addAttribute("equipo",EquipoService.findOne(id));
		return "listarhvrepeat";
	}
	
	@GetMapping("/eliminarHV/{id}")
	public String eliminarHV(@PathVariable(value = "id") Long id,
            RedirectAttributes flash) {
		Hoja_vida hoja_vida = Hoja_vidaService.findOne(id);
		hoja_vida.setEquipo(null);
		if(hoja_vida != null) {
			Hoja_vidaService.delete(id);
			flash.addFlashAttribute("success","Hoja de vida eliminada con éxito");	           
			return "redirect:/producto";
		}
		return "redirect:/producto";
	}
	
	@GetMapping("/nuevoequipo/{id}")
	public String crearnuevoequipo(@PathVariable(value = "id") Long id,
            Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		Hoja_vida hoja_vida = new Hoja_vida();
		hoja_vida.setEquipo(equipo);
		map.put("hoja_vida", hoja_vida);
		model.addAttribute("equipo",equipo);
		model.addAttribute("boughtdate",LocalDate.now());
		model.addAttribute("installdate",LocalDate.now());
		model.addAttribute("startdate",LocalDate.now());
		model.addAttribute("vctodate",LocalDate.now());
		
		return "nuevoequipo";
	}
	@GetMapping("/assignfoto")
	public String uploadphoto(Model model) {
		
		List<Equipo> equipos = EquipoService.ListEquipo();
		model.addAttribute("equipos", equipos);
		return "assignfoto";
		
	}
	@GetMapping("/formatohvpdf")
	public void downloadReportFormat(HttpServletResponse response) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        byte[] pdfReport = generator.getoriginalHVPDF().toByteArray();

        String mimeType =  "application/pdf";
        String namefile = "formatoHV2022";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	
	@PostMapping("/assignfoto")
	public String savephotoall
		(		
			@RequestParam(value="fotos",defaultValue = "EMPTY")String fotos,
			@RequestParam("file") MultipartFile file,
	        Model model,
	        RedirectAttributes flash,
	        SessionStatus status) throws IOException {
		
		String images_folder = "./src/main/resources/static/images/";
		if(fotos.equals("EMPTY")) {
			flash.addAttribute("unselect", "No selecciono ningun equipo");
			return "redirect:/assignfoto";
		}
		else {
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(fotos.split(",")));
			ArrayList<Hoja_vida> listchecknum = new ArrayList<Hoja_vida>();
			for(int i = 0;i<listcheck.size();i++) {
				listchecknum.add(i,Hoja_vidaService.findHVbyEquipo(Long.valueOf(listcheck.get(i))));
			}
			
			for(int e = 0;e<listchecknum.size();e++) {
				listchecknum.get(e).setFoto("/images/" +listchecknum.get(0).getId_Hoja_vida()+ file.getOriginalFilename());
				Hoja_vidaService.save(listchecknum.get(e));
			}
			uploadFileService.saveImage(file,listchecknum.get(0).getId_Hoja_vida());
	
			return "redirect:/producto";
			}
	}
	
	@GetMapping("/editarequipo/{id}")
	public String editarhv(@PathVariable(value="id") Long id,
			Model model,Map<String, Object> map,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
		System.out.println(hoja_vida.getId_Hoja_vida());
		map.put("hoja_vida", hoja_vida);
		model.addAttribute("equipo",equipo);
		model.addAttribute("boughtdate",hoja_vida.getFecha_compra());
		model.addAttribute("installdate",hoja_vida.getFecha_instalacion());
		model.addAttribute("startdate",hoja_vida.getFecha_iniciooperacion());
		model.addAttribute("vctodate",hoja_vida.getFecha_vencimientogarantia());
		return  "nuevoequipo";
	}
	
	@PostMapping("/nuevoequipo/{id}")
	public String guardarnuevahv(@PathVariable(value = "id") Long id,@Valid Hoja_vida hoja_vida, BindingResult binRes,            
            Model model,
            RedirectAttributes flash,
            @RequestParam(value="fecha_compra",defaultValue = "2022-01-01")String fecha_compra,
            @RequestParam(value="fecha_instalacion",defaultValue = "2022-01-01")String fecha_instalacion,
            @RequestParam(value="fecha_inicio_operacion",defaultValue = "2022-01-01")String fecha_inicio_operacion,
            @RequestParam(value="fecha_vencimiento",defaultValue = "2022-01-01")String fecha_vencimiento,
            @RequestParam(value="foto",defaultValue = "empty")MultipartFile foto) throws IOException {
		
		
		LocalDate fechacompra = LocalDate.parse(fecha_compra);
    	Date fechac = Date.valueOf(fechacompra);
    	LocalDate fechainstalacion = LocalDate.parse(fecha_instalacion);
    	Date fechai = Date.valueOf(fechainstalacion);
    	LocalDate fechainicio = LocalDate.parse(fecha_inicio_operacion);
    	Date fechaop = Date.valueOf(fechainicio);
    	LocalDate fechavencimiento= LocalDate.parse(fecha_vencimiento);
    	Date fechav = Date.valueOf(fechavencimiento);
    	
    	hoja_vida.setFecha_compra(fechac);
    	hoja_vida.setFecha_instalacion(fechai);
    	hoja_vida.setFecha_iniciooperacion(fechaop);
    	hoja_vida.setFecha_vencimientogarantia(fechav);
    	
    	
		hoja_vida.setEquipo(EquipoService.findOne(id));	
		if(foto.getOriginalFilename()!="") {
			uploadFileService.saveImage(foto, id);
			String Fotohv = "/images/"+String.valueOf(id)+foto.getOriginalFilename();
			hoja_vida.setFoto(Fotohv);
		}
		
		
		
		Hoja_vidaService.save(hoja_vida);
		
        flash.addFlashAttribute("success","guardado");
        return "redirect:/matrizhv/"+hoja_vida.getEquipo().getId_Equipo();

	}
	
	@GetMapping(value="/formatohojadevida/{id}")
	public String showhvformatoequipo(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		
		
		model.addAttribute("period",equipo.getPeriodicidad());
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("diasmtto",equipo.getDias_mantenimiento());
    	model.addAttribute("mesesmtto",equipo.getMeses_mantenimiento());
    	model.addAttribute("anomtto",equipo.getAno_mantenimiento());
    	model.addAttribute("anoingresoequipo",equipo.getAno_ingreso());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
    	model.addAttribute("contratoequipo",Hoja_vidaService.findHVbyEquipo(id).getContrato());
    	model.addAttribute("hoja_vidaequipo",Hoja_vidaService.findHVbyEquipo(id));
		return "formatohojadevida";
	}
	@GetMapping("/downloadHV/{id}")
    public void downloadFile(HttpServletResponse response,@PathVariable Long id) throws IOException, DocumentException {
        PdfGenarator generator = new PdfGenarator();
        Hoja_vida hoja_vida = Hoja_vidaService.findHVbyEquipo(id);
       
        byte[] pdfHV = generator.getHVPDF(hoja_vida).toByteArray();

        String mimeType =  "application/pdf";
        String namefile = String.valueOf(hoja_vida.getEquipo().getId_Equipo())+"_"+hoja_vida.getEquipo().getSerie();
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",namefile+".pdf"));

        response.setContentLength(pdfHV.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream(pdfHV);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	@GetMapping(value ="/hvdatosequipo/{id}")
	public String showhvdatosgenerales(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
		
    	model.addAttribute("idequipo",equipo.getId_Equipo());
    	model.addAttribute("nombreequipo",equipo.getNombre_Equipo());
    	model.addAttribute("ubicacionequipo",equipo.getUbicacion());
    	model.addAttribute("marcaequipo",equipo.getMarca());
    	model.addAttribute("serieequipo",equipo.getSerie());
    	model.addAttribute("modeloequipo",equipo.getModelo());
    	model.addAttribute("servicioequipo",equipo.getServicios());
    	model.addAttribute("placainventario",equipo.getPlaca_inventario());
		model.addAttribute("hoja_vidadg",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatosequipo";
	
	}
	
	@GetMapping(value ="/hvdocumentosequipo/{id}")
	public String showHvDocumentos(@PathVariable(value = "id") long idEquipo,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(idEquipo);
    	List<Documento> listDocumentosEquipo = documentoDao.documentosEquipo(idEquipo);
		List<TipoDocumento> listTiposDocumentosPendientes = tipoDocumentoDao.getDocumentosPendientesEquipo(idEquipo);
		boolean showtable = false;
		if(listDocumentosEquipo.size() > 0) {
			showtable = true;
		}
		model.addAttribute("pendientes", listTiposDocumentosPendientes);
		model.addAttribute("documentosEquipo", listDocumentosEquipo);
		model.addAttribute("equipo", equipo);
		model.addAttribute("showtable", showtable);
		model.addAttribute("idequipo", equipo.getId_Equipo());
		return "hvdocumentos";
	
	}
	
	@GetMapping(value = "/visualpdfdocumento/{id}")
	public ResponseEntity<InputStreamResource> visualizarpdfreporte(HttpServletRequest request,
			HttpServletResponse response, @PathVariable(value = "id") Long idDocumento, Map<String, Object> map, Model model,
			RedirectAttributes flash) throws IOException {
		Documento documento = documentoService.getdocumentoById(idDocumento);
		File file = new File(documento.getRutaDocumento());
		HttpHeaders headers = new HttpHeaders();
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
	}
	
	@GetMapping(value ="/hvdatoscompra/{id}")
	public String showhvdatoscompra(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadc",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatoscompra";
	
	}
	@GetMapping(value="/hvdatostecnicos/{id}")
	public String showhvdatostecnicos(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadt",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatostecnicos";
		
	}
	@GetMapping(value="/hvdatosregulatorios/{id}")
	public String showhvdatosregulatorios(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidadr",Hoja_vidaService.findHVbyEquipo(id));
		return "hvdatosregulatorios";
		
	}
	@GetMapping(value="/hvclasificacionequipo/{id}")
	public String showhvclasificacion(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidac",Hoja_vidaService.findHVbyEquipo(id));
		return "hvclasificacionequipo";
		
	}
	@GetMapping(value="/hvmantenimientoequipo/{id}")
	public String showhvmantenimiento(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
		model.addAttribute("period",equipo.getPeriodicidad());
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidam",Hoja_vidaService.findHVbyEquipo(id));
		return "hvmantenimientoequipo";
		
	}
	@GetMapping(value="/hvaccesorios/{id}")
	public String showhvaccesorios(@PathVariable(value = "id") Long id,
            Model model,
            RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
    	
    	model.addAttribute("idequipo",equipo.getId_Equipo());
		model.addAttribute("hoja_vidaa",Hoja_vidaService.findHVbyEquipo(id));
		return "hvaccesorios";
		
	}
	

}
