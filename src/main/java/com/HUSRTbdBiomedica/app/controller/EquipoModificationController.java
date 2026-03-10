package com.HUSRTbdBiomedica.app.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.FechaMantenimientoEquipos;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_Biomedica;
import com.HUSRTbdBiomedica.app.entity.Proveedor;
import com.HUSRTbdBiomedica.service.Hoja_vidaServiceImp;
import com.HUSRTbdBiomedica.service.IEquipoService;
import com.HUSRTbdBiomedica.service.IFechaMantenimientoEquipoService;
import com.HUSRTbdBiomedica.service.IHoja_vidaService;
import com.HUSRTbdBiomedica.service.IHoja_vida_BiomedicaService;
import com.HUSRTbdBiomedica.service.IProveedorService;
import com.HUSRTbdBiomedica.service.hoja_Vida_BiomedicaServiceImp;

@Controller
@SessionAttributes("equipo")
@RequestMapping
public class EquipoModificationController {

	@Autowired
	private IEquipoService EquipoService;

	@Autowired
	private IProveedorService iProveedorService;

	@Autowired
	private IHoja_vidaService hoja_vidaService;

	@Autowired
	private IHoja_vida_BiomedicaService hoja_vida_BiomedicaService;

	@Autowired
	private IFechaMantenimientoEquipoService fechaMantenimientoEquipoService;

	@GetMapping("/SaveDates")
	public String buildDateEquipos() {

		List<Equipo> equipos = EquipoService.getAllEquiposT();

		for (int i = 0; i < equipos.size(); i++) {
			System.out.println(
					equipos.get(i).getDias_mantenimiento() + "Meses:   --- " + equipos.get(i).getMeses_mantenimiento());
			List<String> dias1 = new ArrayList<>();
			List<String> dias2 = new ArrayList<>();
			List<String> Meses = new ArrayList<>();

			if (!equipos.get(i).getDias_mantenimiento().equals("0")) {
				String aux = "";
				for (int j = 0; j < equipos.get(i).getDias_mantenimiento().length(); j++) {
					if (equipos.get(i).getDias_mantenimiento().charAt(j) != '-'
							&& equipos.get(i).getDias_mantenimiento().charAt(j) != ','
							&& j < equipos.get(i).getDias_mantenimiento().length() - 1) {
						aux = aux + equipos.get(i).getDias_mantenimiento().charAt(j);
					} else if (equipos.get(i).getDias_mantenimiento().charAt(j) == '-') {
						dias1.add(aux);
						aux = "";
					} else if (equipos.get(i).getDias_mantenimiento().charAt(j) == ',') {
						dias2.add(aux);
						aux = "";
					} else if (equipos.get(i).getDias_mantenimiento().length() - 1 == j) {
						aux = aux + equipos.get(i).getDias_mantenimiento().charAt(j);
						dias2.add(aux);
						aux = "";
					}
				}
				String auxMes = "";
				for (int j = 0; j < equipos.get(i).getMeses_mantenimiento().length(); j++) {
					if (equipos.get(i).getMeses_mantenimiento().charAt(j) != ',') {
						auxMes = auxMes + equipos.get(i).getMeses_mantenimiento().charAt(j);
					} else {
						Meses.add(auxMes);
						auxMes = "";
					}
					if (j == equipos.get(i).getMeses_mantenimiento().length() - 1) {
						Meses.add(auxMes);
						auxMes = "";
					}

				}

				System.out.println("Dias Registrados: " + dias1.size() + " --- Dias registrados: " + dias2.size()
						+ " --- MesesRegistrados:   " + Meses.size());
				for (int j = 0; j < dias1.size(); j++) {
					System.out.println("Dias inicio: " + dias1.get(j) + " --- Dias fin: " + dias2.get(j) + " ---  Mes: "
							+ Meses.get(j));

					FechaMantenimientoEquipos fechaMantenimientoEquipos = new FechaMantenimientoEquipos();
					fechaMantenimientoEquipos.setDia1(Integer.parseInt(dias1.get(j)));
					fechaMantenimientoEquipos.setDia2(Integer.parseInt(dias2.get(j)));
					fechaMantenimientoEquipos.setMes(Meses.get(j));
					fechaMantenimientoEquipos.setAño(equipos.get(i).getAno_mantenimiento());
					fechaMantenimientoEquipos.setDebeRealizar("PROPIO");
					fechaMantenimientoEquipos.setEquipo(equipos.get(i));
					fechaMantenimientoEquipoService.addRegistro(fechaMantenimientoEquipos);

				}
			}
		}

		return "/producto";
	}

	// ES NECESARIO AJUSTAR LA BASE DE DATOS EN LA TABLA DE HOJA DE VIDA LOS CAPOS
	// DE PROVEEDOR ANTES DE EJECUTAR ---------------- CON LA BASE DE DATOS MAS
	// RECIENTE

	@GetMapping("/saveHvNew")
	public String setDataHV() {

		List<Hoja_vida> listHV = hoja_vidaService.ObtainallHV();

		for (int i = 0; i < listHV.size(); i++) {
			if (listHV.get(i).getEquipo() != null) {
				Hoja_vida_Biomedica hoja_vida_Biomedica = new Hoja_vida_Biomedica();
				hoja_vida_Biomedica.setAno_fabricacion(listHV.get(i).getAno_fabricacion());
				hoja_vida_Biomedica.setCodinternacional(listHV.get(i).getCodinternacional());
				hoja_vida_Biomedica.setContrato(listHV.get(i).getContrato());
				hoja_vida_Biomedica.setFormaAdquisicion(getIdFormaAdquisicionPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setFecha_compra(listHV.get(i).getFecha_compra());
				hoja_vida_Biomedica.setFecha_instalacion(listHV.get(i).getFecha_instalacion());
				hoja_vida_Biomedica.setFecha_iniciooperacion(listHV.get(i).getFecha_iniciooperacion());
				hoja_vida_Biomedica.setFecha_vencimientogarantia(listHV.get(i).getFecha_vencimientogarantia());
				hoja_vida_Biomedica.setCosto_compra(listHV.get(i).getCosto_compra());
				hoja_vida_Biomedica.setRegistro_invima(listHV.get(i).getRegistro_invima());
				hoja_vida_Biomedica.setFabricante(listHV.get(i).getFabricante());
				hoja_vida_Biomedica.setPaisfabricante(listHV.get(i).getPaisfabricante());
				hoja_vida_Biomedica.setProveedor(getIdProveedorPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setVmaxoperacion(listHV.get(i).getVmaxoperacion());
				hoja_vida_Biomedica.setVminoperacion(listHV.get(i).getVminoperacion());
				hoja_vida_Biomedica.setImaxoperacion(listHV.get(i).getImaxoperacion());
				hoja_vida_Biomedica.setIminoperacion(listHV.get(i).getIminoperacion());
				hoja_vida_Biomedica.setWconsumida(listHV.get(i).getWconsumida());
				hoja_vida_Biomedica.setFrecuencia(listHV.get(i).getFrecuencia());
				hoja_vida_Biomedica.setPresion(listHV.get(i).getPresion());
				hoja_vida_Biomedica.setVelocidad(listHV.get(i).getVelocidad());
				hoja_vida_Biomedica.setTemperatura(listHV.get(i).getTemperatura());
				hoja_vida_Biomedica.setPeso(listHV.get(i).getPeso());
				hoja_vida_Biomedica.setCapacidad(listHV.get(i).getCapacidad());
				hoja_vida_Biomedica.setOtrosdatostecnicos(listHV.get(i).getOtrosdatostecnicos());
				hoja_vida_Biomedica.setFuenteAlimentacion(getIdFuenteAlimentacionPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setEquipotipofijo(listHV.get(i).isEquipotipofijo());
				hoja_vida_Biomedica.setEquipotipoportatil(listHV.get(i).isEquipotipoportatil());
				hoja_vida_Biomedica.setTipoUso(getTipoUsoPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setRiesgo(getIdRiesgoPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setClaseTecnologia(getIdTecnologiaPredominantePorHV(listHV.get(i)));
				hoja_vida_Biomedica.setTipoMantenimientoActual(getIdTipoMantenimientoActual(listHV.get(i)));
				hoja_vida_Biomedica.setPropiedad(getPropietarioPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setManual_operacion(listHV.get(i).isManual_operacion());
				hoja_vida_Biomedica.setManual_tecnico(listHV.get(i).isManual_tecnico());
				hoja_vida_Biomedica.setRequiereCalibracion(listHV.get(i).isRequierecalibracion());
				hoja_vida_Biomedica.setPeriodoCalibracion(getPeriodoCalibracionPorHV(listHV.get(i)));
				hoja_vida_Biomedica.setAccesorio1(listHV.get(i).getAccesorio1());
				hoja_vida_Biomedica.setAccesorio2(listHV.get(i).getAccesorio2());
				hoja_vida_Biomedica.setAccesorio3(listHV.get(i).getAccesorio3());
				hoja_vida_Biomedica.setAccesorio4(listHV.get(i).getAccesorio4());
				hoja_vida_Biomedica.setFoto(listHV.get(i).getFoto());
				hoja_vida_Biomedica.setObservaciones(listHV.get(i).getObservaciones());
				hoja_vida_Biomedica.setEquipo(listHV.get(i).getEquipo());
				hoja_vida_Biomedica.setClasificacionBiomedica(getIdClasificacionBiomedicaPorHV(listHV.get(i)));
				System.out.println(listHV.get(i).getId_Hoja_vida() + "   : Id Hoja de vida");
				System.out.println(listHV.get(i).getEquipo().getId_Equipo() + " :   ID Equipo");
				hoja_vida_BiomedicaService.AddHoja_Vida_Biomedica(hoja_vida_Biomedica);

			}

		}

		return "/producto";
	}

	
	public int getPeriodoCalibracionPorHV(Hoja_vida hoja_vida) {
		int res = 0;
		if (hoja_vida.isPcalsemestral()) {
			res = 2;
		} else if (hoja_vida.isPcalanual()) {
			res = 1;
		}
		return res;
	}

	public char getTipoUsoPorHV(Hoja_vida hoja_vida) {
		char res = ' ';
		if (hoja_vida.isUsomedico()) {
			res = 'M';
		} else if (hoja_vida.isUsobasico()) {
			res = 'B';
		} else if (hoja_vida.isUsoapoyo()) {
			res = 'A';
		} else {
			res = 'N';
		}

		return res;

	}

	public int getIdFormaAdquisicionPorHV(Hoja_vida hoja_vida) {
		int res = 7;
		if (hoja_vida.isCompraddirecta()) {
			res = 1;
		} else if (hoja_vida.isConvenio()) {
			res = 2;
		} else if (hoja_vida.isDonado()) {
			res = 3;
		} else if (hoja_vida.isAsignadoporministerio()) {
			res = 4;
		} else if (hoja_vida.isAsignadoporgobernacion()) {
			res = 5;
		} else if (hoja_vida.isComodato()) {
			res = 6;
		}
		return res;
	}

	public int getIdFuenteAlimentacionPorHV(Hoja_vida hoja_vida) {
		int res = 8;
		if (hoja_vida.isFuenteaelectricidad()) {
			res = 1;
		} else if (hoja_vida.isFuenteavaporagua()) {
			res = 2;
		} else if (hoja_vida.isFuenteaenergiasolar()) {
			res = 3;
		} else if (hoja_vida.isFuenteaderivadospetroleo()) {
			res = 4;
		} else if (hoja_vida.isFuenteaagua()) {
			res = 5;
		} else if (hoja_vida.isFuenteagas()) {
			res = 6;
		} else if (hoja_vida.isFuenteaotros()) {
			res = 7;
		}
		return res;
	}

	public int getIdTecnologiaPredominantePorHV(Hoja_vida hoja_vida) {
		int res = 9;
		if (hoja_vida.isClaseelectrico()) {
			res = 1;
		} else if (hoja_vida.isClaseelectronico()) {
			res = 2;
		} else if (hoja_vida.isClasemecanico()) {
			res = 3;
		} else if (hoja_vida.isClaseelectromecanico()) {
			res = 4;
		} else if (hoja_vida.isClasehidraulico()) {
			res = 5;
		} else if (hoja_vida.isClaseneumatico()) {
			res = 6;
		} else if (hoja_vida.isClasevapor()) {
			res = 7;
		} else if (hoja_vida.isClasesolar()) {
			res = 8;
		}
		return res;
	}

	public int getIdClasificacionBiomedicaPorHV(Hoja_vida hoja_vida) {
		int res = 6;
		if (hoja_vida.isBiomedicdiagnostico()) {
			res = 1;
		} else if (hoja_vida.isBiomedictratamiento()) {
			res = 2;
		} else if (hoja_vida.isBiomedicrehabilitacion()) {
			res = 3;
		} else if (hoja_vida.isBiomedicprevencion()) {
			res = 4;
		} else if (hoja_vida.isBiomedicanalisis()) {
			res = 5;
		}
		return res;
	}

	public int getIdRiesgoPorHV(Hoja_vida hoja_vida) {
		int res = 0;
		if (hoja_vida.isRiesgoi()) {
			res = 1;
		} else if (hoja_vida.isRiesgoiia()) {
			res = 2;
		} else if (hoja_vida.isRiesgoiib()) {
			res = 3;
		} else if (hoja_vida.isRiesgoiii()) {
			res = 4;
		} else {
			res = 5;

		}

		return res;
	}

	public int getIdTipoMantenimientoActual(Hoja_vida hoja_vida) {
		int res = 0;

		if (hoja_vida.isMapropio()) {
			res = 1;
		} else if (hoja_vida.isMacontratado()) {
			res = 2;
		} else if (hoja_vida.isMacomodato()) {
			res = 3;
		} else if (hoja_vida.isMagarantia()) {
			res = 4;
		} else {
			res = 5;
		}

		return res;
	}

	public int getIdProveedorPorHV(Hoja_vida hoja_vida) {
		int res = 99;
		res = iProveedorService.getIdProveedor(hoja_vida.getProveedor());
		return res;
	}

	public int getPropietarioPorHV(Hoja_vida hoja_vida) {
		int res = 4;
		if (hoja_vida.isProphospital()) {
			res = 1;
		} else if (hoja_vida.isPropproveedor()) {
			res = 2;
		} else if (hoja_vida.isPropotro()) {
			res = 3;
		}
		return res;
	}

}
