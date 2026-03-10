package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HUSRTbdBiomedica.app.Dao.IDocumentoDao;
import com.HUSRTbdBiomedica.app.entity.Documento;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Hospital;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IHospital_Service;
import com.HUSRTbdBiomedica.service.IResponsableService;
import com.HUSRTbdBiomedica.service.IServicioService;
import com.HUSRTbdBiomedica.service.ITipo_equipoService;

@Controller
@SessionAttributes("equipo")
@RequestMapping
public class EquipoController {
	@Autowired
	private IEquipoService EquipoService;

	@Autowired
	private ITipo_equipoService Tipo_equipoService;

	@Autowired
	private IHospital_Service HospitalService;

	@Autowired
	private IServicioService ServicioService;

	@Autowired
	private IResponsableService ResponsableService;

	@Autowired
	private IDocumentoDao documentoDao;

	@GetMapping("/producto")
	public String Dashboard(Model model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object[] roles = auth.getAuthorities().toArray();

		if (roles[0].toString().equals("ADMIN") || roles[0].toString().equals("EDITOR")
				|| roles[0].toString().equals("USER")) {
			return "producto";
		} else if (roles[0].toString().equals("VISITOR")) {
			return "redirect:/clasificacionDHServicio";
		} else if (roles[0].toString().equals("SYSUSER") || roles[0].toString().equals("SYSADMIN")) {

			return "redirect:/syshome";
		} else if (roles[0].toString().equals("SYSVISITOR") || roles[0].toString().equals("SYSADMIN")) {

			return "redirect:/sysvisitante";
		}

		return "producto";
	}

	@GetMapping("/todoslosequipos")
	public String ListarTipo_equipos(Model model) {

		model.addAttribute("tipo_equiporrios", Tipo_equipoService.findTiposconEquipo());

		model.addAttribute("num", EquipoService.countequiposbytipo());
		return "todoslosequipos";
	}

	@GetMapping("/semaforizacion")
	public String Semaforizacion(Model model) {
		return "semaforizacion";
	}

	@GetMapping("/trimestral")
	public String cuadrarTodos(Model model) {
		model.addAttribute("numerodeequipost", EquipoService.ContarEquiposTrimestral());

		model.addAttribute("tiposequipostrimestrales", Tipo_equipoService.findTipo_equipobyPeriodicidad(3));
		return "trimestral";
	}

	@GetMapping("/cuatrimestral")
	public String cuadrarCuatrimestrales(Model model) {
		model.addAttribute("numerodeequiposc", EquipoService.ContarEquiposCuatrimestral());
		model.addAttribute("tiposequiposcuatrimestrales", Tipo_equipoService.findTipo_equipobyPeriodicidad(4));
		return "cuatrimestral";
	}

	@GetMapping("/comodatos")
	public String cuadrarComodatos(Model model) {
		model.addAttribute("responsablecomodatos", ResponsableService.listResponsableComodato());
		return "comodatos";
	}

	@GetMapping("/price")
	public String cuadrarSemestrales(Model model) {
		model.addAttribute("numerodeequiposs", EquipoService.ContarEquiposSemestral());
		model.addAttribute("tiposequipossemestrales", Tipo_equipoService.findTipo_equipobyPeriodicidad(2));
		return "price";
	}

	@GetMapping("/anual")
	public String cuadrarAnual(Model model) {
		model.addAttribute("numerodeequiposa", EquipoService.ContarEquiposAnual());
		model.addAttribute("tiposequiposanuales", Tipo_equipoService.findTipo_equipobyPeriodicidad(1));
		return "anual";
	}

	@GetMapping("/prueba/{id}")
	public void MostrarContadorTipo(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("numeroportipobyp", Tipo_equipoService.countEbyTipoEquipobyP(2, id));
	}

	@GetMapping("/guiaequipo/{id}")
	public String getGuiaEquipo(@PathVariable(value = "id") Long idEquipo) {
		System.out.println("Equipo con id: " + idEquipo);
		List<Documento> guiasEquipo = documentoDao.getGuiaEquipo(idEquipo);
		return "redirect:/visualpdfdocumento/" + guiasEquipo.get(0).getIdDocumento();
	}

	@PostMapping("/buscadoporserie")
	public String buscadoporserio(@RequestParam(value = "mySerie", defaultValue = "") String serie, Model model,
			RedirectAttributes flash) {
		List<Equipo> equipo = EquipoService.findequipobySerie(serie);
		if (equipo != null) {
			model.addAttribute("nombrelineaequipo", equipo.get(0).getNombre_Equipo());
			model.addAttribute("equiposerie", equipo);
			return "buscadoporserie";
		} else {
			return "redirect:/periodicidad";
		}
	}

	@PostMapping("/nuevoequipomtto")
	public String crearnuevoequipomtto(@RequestParam(value = "myTipo", defaultValue = "BALANZA") String name,
			Model model, Map<String, Object> map, RedirectAttributes flash) {
		Equipo equipo = new Equipo();
		Tipo_equipo tipo = Tipo_equipoService.findbyName(name);
		Hospital hospital = HospitalService.findOne(1L);

		if (tipo == null) {
			return "redirect:/usuarios";
		} else {

			equipo.setTipo_equipo(tipo);
			equipo.setHospital(hospital);
			List<String> nombres = new ArrayList<String>();
			List<Servicio> servicios = ServicioService.ListServicio();
			for (int i = 0; i < servicios.size(); i++) {
				nombres.add(servicios.get(i).getNombre_servicio());
			}
			model.addAttribute("serviciosfull", servicios);
			model.addAttribute("servicios", nombres);
			map.put("equipo", equipo);

			return "nuevoequiposinhv";
		}

	}

	@PostMapping("/editarportipo")
	public String editarequiposbytipo(@RequestParam(value = "myTipoedit", defaultValue = "BALANZA") String name,
			Model model, Map<String, Object> map, RedirectAttributes flash) {
		Tipo_equipo tipo = Tipo_equipoService.findbyName(name);
		if (tipo == null) {
			return "redirect:/usuarios";
		} else {
			model.addAttribute("tipo", tipo);
			model.addAttribute("equipos", Tipo_equipoService.findEquiposbyTipoEquipo(tipo.getId_Tipo_equipo()));
			return "listedit";
		}

	}

	@GetMapping("/listinactivos")
	public String listarinactivos(Model model, RedirectAttributes flash) {
		model.addAttribute("equipos", EquipoService.findEquiposInactivos());
		return "listinactivos";
	}

	@GetMapping("/editarequipomtto/{id}")
	public String editarequipo(@PathVariable(value = "id") Long id, Model model, Map<String, Object> map,
			RedirectAttributes flash) {
		Equipo equipo = EquipoService.findOne(id);
		List<String> nombres = new ArrayList<String>();
		List<Servicio> servicios = ServicioService.ListServicio();
		for (int i = 0; i < servicios.size(); i++) {
			nombres.add(servicios.get(i).getNombre_servicio());
		}
		model.addAttribute("servicios", nombres);
		model.addAttribute("serviciosfull", servicios);
		model.addAttribute("meses", equipo.getMeses_mantenimiento());
		model.addAttribute("dias", equipo.getDias_mantenimiento());
		map.put("equipo", equipo);

		return "nuevoequiposinhv";
	}

	@PostMapping("/crearnuevoequipo/{id}")
	public String guardarnuevoequipomtto(@PathVariable(value = "id") Long id, @Valid Equipo equipo,
			BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
			@RequestParam(value = "datetimes", defaultValue = "NR") String dias,
			@RequestParam(value = "metodoMesesPreventivo", defaultValue = "NR") String meses,
			@RequestParam(value = "myService", defaultValue = "HUSRT") String servicio,
			@RequestParam(value = "tipoequipo", defaultValue = "BALANZA") String tipo) {

		Tipo_equipo tipo_equipo = Tipo_equipoService.findOne(id);
		equipo.setTipo_equipo(tipo_equipo);
		equipo.setHospital(HospitalService.findOne(1L));
		if (!servicio.equals("HUSRT")) {
			equipo.setServicio(ServicioService.findbyName(servicio));
			equipo.setServicios(servicio);
		}
		equipo.setMeses_mantenimiento(meses);
		ArrayList<String> mescut = new ArrayList<String>(Arrays.asList(meses.split(",")));
		ArrayList<String> diascut = new ArrayList<String>(Arrays.asList(dias.split(",")));
		List<String> diasasign = new ArrayList<String>();

		for (int mes = 0; mes < mescut.size(); mes++) {
			if (mescut.get(mes).equals("ENERO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(0).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("FEBRERO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(1).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("MARZO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(2).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("ABRIL")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(3).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("MAYO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(4).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("JUNIO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(5).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("JULIO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(6).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("AGOSTO")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(7).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("SEPTIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(8).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("OCTUBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(9).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("NOVIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(10).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			} else if (mescut.get(mes).equals("DICIEMBRE")) {
				ArrayList<String> diaint = new ArrayList<String>(Arrays.asList(diascut.get(11).split("-")));
				diasasign.add(String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(0).split("/"))).get(1).trim()))
						+ '-'
						+ String.valueOf(Integer.valueOf((Arrays.asList(diaint.get(1).split("/"))).get(1).trim())));
			}
		}
		String diasupload = new String();
		for (int diasd = 0; diasd < diasasign.size(); diasd++) {
			if (diasd == diasasign.size() - 1) {
				diasupload += diasasign.get(diasd);
			} else {
				diasupload += diasasign.get(diasd) + ',';
			}
		}
		equipo.setDias_mantenimiento(diasupload);
		EquipoService.save(equipo);
		return "redirect:/visualizaciontipoequipo/" + tipo_equipo.getId_Tipo_equipo();

	}

	@PostMapping("/asignarcodigorand")
	public String asigncoderand(@RequestParam(value = "myService", defaultValue = "1") String id, Model model,
			Map<String, Object> map, RedirectAttributes flash) {

		List<Equipo> equipos = ServicioService.findEquiposbyServicio(Long.valueOf(id));
		int min_val = 100000 + 10000 * Integer.valueOf(id);
		int max_val = 100000 + 10000 * Integer.valueOf(id) + 10000;
		Random rand = new Random();
		IntStream stream = rand.ints(equipos.size(), min_val, max_val);
		List<Integer> nums = stream.boxed().toList();
		for (int n = 0; n < nums.size(); n++) {

			equipos.get(n).setCodigo(String.valueOf(nums.get(n)));
			EquipoService.save(equipos.get(n));
		}
		// desordenando los elementos

		return "producto";
	}

	@GetMapping("/revisargarantiaycontratos")
	public String revisargyc(Model model, Map<String, Object> map, RedirectAttributes flash) {

		model.addAttribute("rgyc", EquipoService.groupbyGyC());
		return "revgyc";
	}

	@PostMapping("/ingresomtto")
	public String ingresomtto(@RequestParam(value = "eqps", defaultValue = "ES") String ides, Model model,
			Map<String, Object> map, RedirectAttributes flash) {
		if (ides.equals("ES")) {

			return "redirect:/revisargarantiaycontratos";
		} else {
			ArrayList<String> listcheck = new ArrayList<String>(Arrays.asList(ides.split(",")));

			for (int i = 0; i < listcheck.size(); i++) {
				Equipo equipo = EquipoService.findOne(Long.valueOf(listcheck.get(i)));
				List<Equipo> changede = EquipoService.listEquiposByMM(equipo.getMarca(), equipo.getModelo());
				for (int j = 0; j < changede.size(); j++) {
					changede.get(j).setEnero_mantenimiento("m");
					changede.get(j).setFebrero_mantenimiento("");
					changede.get(j).setMarzo_mantenimiento("");
					changede.get(j).setAbril_mantenimiento("");
					changede.get(j).setMayo_mantenimiento("");
					changede.get(j).setJunio_mantenimiento("");
					changede.get(j).setJulio_mantenimiento("");
					changede.get(j).setAgosto_mantenimiento("");
					changede.get(j).setSeptiembre_mantenimiento("");
					changede.get(j).setOctubre_mantenimiento("");
					changede.get(j).setNoviembre_mantenimiento("");
					changede.get(j).setDiciembre_mantenimiento("");
					EquipoService.save(changede.get(j));
				}
			}

		}
		return "redirect:/listplanning";
	}

}
