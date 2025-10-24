package com.HUSRTbdBiomedica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IMantenimiento_preventivoService;
import com.HUSRTbdBiomedica.service.IReporteService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;
import com.HUSRTbdBiomedica.service.IUsuarioService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class MantenimientoController {
	
	@Autowired
	private IEquipoService EquipoService;
	
	@Autowired
	private ITipo_equipoService Tipo_equipoService;
	
	@Autowired
	private IReporteService ReporteService;
	
	@Autowired
	private IUsuarioService UsuarioService;
	
	@Autowired
	private IMantenimiento_preventivoService Mantenimiento_preventivoService;

	@PostMapping("/mantenimiento")
	public String obtenermtto(Model model, RedirectAttributes flash,
			@RequestParam(value="daterange")String rangofechas,
			@RequestParam(value="usuarioprogramado") Long idUsuario) {
		
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

		
		model.addAttribute("mantenimientosCorrectivos", ReporteService.mantenimientosCorrectivos(fecha_1, fecha_2));
		model.addAttribute("indicorrec",ReporteService.acorrectivos(fecha_1,fecha_2));
		model.addAttribute("indiprev",ReporteService.apreventivos(fecha_1,fecha_2));
		model.addAttribute("indipredic",ReporteService.apredictivos(fecha_1,fecha_2));
		model.addAttribute("indiotrosm",ReporteService.aotros(fecha_1,fecha_2));
		model.addAttribute("indidesg",ReporteService.fdesgaste(fecha_1,fecha_2));
		model.addAttribute("indiopind",ReporteService.fopindebida(fecha_1,fecha_2));
		model.addAttribute("indicausa",ReporteService.fcausaexterna(fecha_1,fecha_2));
		model.addAttribute("indiaccesorios",ReporteService.faccesorios(fecha_1,fecha_2));
		model.addAttribute("inddesconocido",ReporteService.fdesconocido(fecha_1,fecha_2));
		model.addAttribute("indsinfallas",ReporteService.fsinfallas(fecha_1, fecha_2));
		model.addAttribute("indotrosf",ReporteService.fotros(fecha_1,fecha_2));
		List<Mantenimiento_preventivo> mtto_preventivos = new ArrayList<Mantenimiento_preventivo>();
		List<Mantenimiento_preventivo> mtto_preventivosop = new ArrayList<Mantenimiento_preventivo>();
		Usuario usuario = new Usuario();
		if(idUsuario!=1) {
			usuario = UsuarioService.findOne(idUsuario);
			model.addAttribute("nombre",usuario.getNombre()+' '+usuario.getApellido());
			if(mes1.equals(mes2)) {
				mtto_preventivos = Mantenimiento_preventivoService.findBytecnico(idUsuario, mes1, ano1);
				
				
			}
			else {
				
				mtto_preventivos = Mantenimiento_preventivoService.findBytecnico(idUsuario, mes1, ano1);
				mtto_preventivosop = Mantenimiento_preventivoService.findBytecnico(idUsuario, mes2, ano2);
			}
		}
		else {
			model.addAttribute("nombre","Todos");
			if(mes1.equals(mes2)) {
				mtto_preventivos = Mantenimiento_preventivoService.findbyfecha(mes1, ano1);
			}
			else {
				mtto_preventivos = Mantenimiento_preventivoService.findbyfecha(mes1, ano1);
				mtto_preventivosop = Mantenimiento_preventivoService.findbyfecha(mes2, ano2);
			}
			model.addAttribute("asign","prueba");
		}
		int hechos = 0;
		int sinrealizar = 0;
		ArrayList<Mantenimiento_preventivo> mtto_hechos = new ArrayList<Mantenimiento_preventivo>();
		ArrayList<Mantenimiento_preventivo> mtto_sinrealizar = new ArrayList<Mantenimiento_preventivo>();
		String dias = null; 
		String diasop = null;
		ArrayList<Mantenimiento_preventivo> mtto_preventivo_filt = new ArrayList<Mantenimiento_preventivo>();
		int tiempototal = 0;
		int tiemporealizado = 0;
		int tiempofaltante = 0;
		if(mes1.equals(mes2)) {
			for(int mtto=0;mtto<mtto_preventivos.size();mtto++) {
				
				dias = mtto_preventivos.get(mtto).getDias();
				
				ArrayList<String> selectdia = new ArrayList<String>(Arrays.asList(dias.split("-")));
				
	 			for(int dia=dia1;dia<dia2+1;dia++) {
					if(dia==Integer.valueOf(selectdia.get(0))) {
						tiempototal+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
						mtto_preventivo_filt.add(mtto_preventivos.get(mtto));
						if(mtto_preventivos.get(mtto).getReporte()!=null) {
							if(mtto_preventivos.get(mtto).getReporte().getRutapdf()!=null) {
								hechos+=1;
								tiemporealizado+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_hechos.add(mtto_preventivos.get(mtto));
							}
							else {
								sinrealizar+=1;
								tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_sinrealizar.add(mtto_preventivos.get(mtto));
							}
						}
						else {
							sinrealizar+=1;
							tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
							mtto_sinrealizar.add(mtto_preventivos.get(mtto));
						}
					}
				}
								
			}
			
		}
		else {
			for(int mtto=0;mtto<mtto_preventivos.size();mtto++) {
				
				dias = mtto_preventivos.get(mtto).getDias();
				
				ArrayList<String> selectdia = new ArrayList<String>(Arrays.asList(dias.split("-")));
				
				for(int dia=dia1;dia<32;dia++) {
					if(dia==Integer.valueOf(selectdia.get(0))) {
						tiempototal+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
						mtto_preventivo_filt.add(mtto_preventivos.get(mtto));
						if(mtto_preventivos.get(mtto).getReporte()!=null) {
							if(mtto_preventivos.get(mtto).getReporte().getRutapdf()!=null) {
								hechos+=1;
								tiemporealizado+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_hechos.add(mtto_preventivos.get(mtto));
							}
							else {
								sinrealizar+=1;
								tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_sinrealizar.add(mtto_preventivos.get(mtto));
							}
						}
						else {
							sinrealizar+=1;
							tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
							mtto_sinrealizar.add(mtto_preventivos.get(mtto));
						}
					}
				}
				
				
			}
			for(int mtto = 0; mtto<mtto_preventivosop.size();mtto++) {
				
				diasop = mtto_preventivosop.get(mtto).getDias();
				ArrayList<String> selectdiaop = new ArrayList<String>(Arrays.asList(diasop.split("-")));
				for(int dia=0;dia<dia2+1;dia++){
					if(dia==Integer.valueOf(selectdiaop.get(0))) {
						tiempototal+= mtto_preventivosop.get(mtto).getTipo_equipo().getTiempo_minutos();
						mtto_preventivo_filt.add(mtto_preventivosop.get(mtto));
						if(mtto_preventivosop.get(mtto).getReporte()!=null) {
							if(mtto_preventivosop.get(mtto).getReporte().getRutapdf()!=null) {
								hechos+=1;
								tiemporealizado+= mtto_preventivosop.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_hechos.add(mtto_preventivosop.get(mtto));
							}
							else {
								sinrealizar+=1;
								tiempofaltante+= mtto_preventivosop.get(mtto).getTipo_equipo().getTiempo_minutos();
								mtto_sinrealizar.add(mtto_preventivosop.get(mtto));
							}
							
						}
						else {
							sinrealizar+=1;
							tiempofaltante+= mtto_preventivosop.get(mtto).getTipo_equipo().getTiempo_minutos();
							mtto_sinrealizar.add(mtto_preventivosop.get(mtto));
						}
					}
				}
			}
		}
		
		float advance = 0;
		if(mtto_preventivo_filt.size()== 0 || mtto_preventivo_filt==null) {
			advance = (float)hechos/1;
		}
		else {
			advance = (float)hechos/mtto_preventivo_filt.size();
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
		model.addAttribute("tiempototal",tiempototal);
		model.addAttribute("tiempofaltante",tiempofaltante);
		model.addAttribute("tiemporealizado",tiemporealizado);
		
		
		model.addAttribute("listhechos",mtto_hechos);
		model.addAttribute("listsinrealizar",mtto_sinrealizar);
		model.addAttribute("hechos",hechos);
		model.addAttribute("sinrealizar",sinrealizar);
		model.addAttribute("porcentaje",advance*100);
		model.addAttribute("advancecolor",advancecolor);
		model.addAttribute("totalmtto",mtto_preventivo_filt.size());
		model.addAttribute("mttoactual",mtto_preventivo_filt);
		model.addAttribute("ptomtto",UsuarioService.tecnauxbiomedico());
		model.addAttribute("fecha1",fecha_1);
		model.addAttribute("fecha2",fecha_2);
		return "mantenimiento";
	}
	@GetMapping("/mantenimiento")
	public String mantenimientofunciones(Model model,
			RedirectAttributes flash) {
		
		int tiempototal = 0;
		int tiemporealizado = 0;
		int tiempofaltante = 0;
		LocalDate hoy = LocalDate.now();
		
		LocalDate dia1mes = hoy.minusDays(hoy.getDayOfMonth()-1);
		Date fechafhoy = Date.valueOf(dia1mes);
		Date fechainicial = Date.valueOf(hoy);
		model.addAttribute("mantenimientosCorrectivos", ReporteService.mantenimientosCorrectivos(fechafhoy, fechainicial));
		model.addAttribute("indicorrec",ReporteService.acorrectivos(fechafhoy, fechainicial));
		model.addAttribute("indiprev",ReporteService.apreventivos(fechafhoy, fechainicial));
		model.addAttribute("indipredic",ReporteService.apredictivos(fechafhoy, fechainicial));
		model.addAttribute("indiotrosm",ReporteService.aotros(fechafhoy, fechainicial));
		model.addAttribute("indidesg",ReporteService.fdesgaste(fechafhoy, fechainicial));
		model.addAttribute("indiopind",ReporteService.fopindebida(fechafhoy, fechainicial));
		model.addAttribute("indicausa",ReporteService.fcausaexterna(fechafhoy, fechainicial));
		model.addAttribute("indiaccesorios",ReporteService.faccesorios(fechafhoy, fechainicial));
		model.addAttribute("inddesconocido",ReporteService.fdesconocido(fechafhoy, fechainicial));
		model.addAttribute("indsinfallas",ReporteService.fsinfallas(fechafhoy, fechainicial));
		model.addAttribute("indotrosf",ReporteService.fotros(fechafhoy, fechainicial));
		List<Mantenimiento_preventivo> mtto_preventivos = Mantenimiento_preventivoService.findbyfecha(hoy.getMonth().getValue(), hoy.getYear());
		
		int hechos = 0;
		int sinrealizar = 0;
		ArrayList<Mantenimiento_preventivo> mtto_hechos = new ArrayList<Mantenimiento_preventivo>();
		ArrayList<Mantenimiento_preventivo> mtto_sinrealizar = new ArrayList<Mantenimiento_preventivo>();
		for(int mtto=0;mtto<mtto_preventivos.size();mtto++) {
			tiempototal+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
			
			if(mtto_preventivos.get(mtto).getReporte()!=null) {
				if(mtto_preventivos.get(mtto).getReporte().getRutapdf()!=null) {
					hechos+=1;
					mtto_hechos.add(mtto_preventivos.get(mtto));
					tiemporealizado+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
				}
				else {
					sinrealizar+=1;
					mtto_sinrealizar.add(mtto_preventivos.get(mtto));
					tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
				}

			}
			else {
				sinrealizar+=1;
				mtto_sinrealizar.add(mtto_preventivos.get(mtto));
				tiempofaltante+= mtto_preventivos.get(mtto).getTipo_equipo().getTiempo_minutos();
			}
		}
		float advance = 0;
		if(mtto_preventivos.size()==0 || mtto_preventivos==null) {
			advance = (float)hechos/1;
		}
		else {
			advance = (float)hechos/mtto_preventivos.size();
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
		model.addAttribute("asign","prueba");
		model.addAttribute("tiempototal",tiempototal);
		model.addAttribute("tiempofaltante",tiempofaltante);
		model.addAttribute("tiemporealizado",tiemporealizado);
		
		model.addAttribute("nombre","Todos");
		model.addAttribute("listhechos",mtto_hechos);
		model.addAttribute("listsinrealizar",mtto_sinrealizar);
		model.addAttribute("hechos",hechos);
		model.addAttribute("sinrealizar",sinrealizar);
		model.addAttribute("porcentaje",advance*100);
		model.addAttribute("advancecolor",advancecolor);
		model.addAttribute("totalmtto",mtto_preventivos.size());
		model.addAttribute("mttoactual",mtto_preventivos);
		model.addAttribute("ptomtto",UsuarioService.tecnauxbiomedico());
		model.addAttribute("fecha1",fechafhoy);
		model.addAttribute("fecha2",fechainicial);
		return "mantenimiento";
	}

}
