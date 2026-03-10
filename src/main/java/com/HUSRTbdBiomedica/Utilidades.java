package com.HUSRTbdBiomedica;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HUSRTbdBiomedica.app.entity.Calibracion;
import com.HUSRTbdBiomedica.app.entity.PlanActividadMetrologica;
import com.HUSRTbdBiomedica.service.ICalibracionService;

@Component("utilidades")
public class Utilidades {
	
	@Autowired
	private ICalibracionService calibracionService;
	
	public Long verificarActividadMetrologica(PlanActividadMetrologica actividadMetrologica) {
		List<Calibracion> calibraciones = calibracionService.findActbyequipoProgramada(actividadMetrologica.getEquipo().getId_Equipo(), actividadMetrologica.getMes(), actividadMetrologica.getAno());
		System.out.println(calibraciones.size());
		if(calibraciones.size() == 0) {
			return (long) 0;
		}else {
			return calibraciones.get(0).getId_Calibracion();
		}
		
	}

	public String getNombreMes(int mes) {
		if(mes == 1) {
			return "ENERO";
		}else if(mes == 2) {
			return "FEBRERO";
		}else if(mes == 3) {
			return "MARZO";
		}else if(mes == 4) {
			return "ABRIL";
		}else if(mes == 5) {
			return "MAYO";
		}else if(mes == 6) {
			return "JUNIO";
		}else if(mes == 7) {
			return "JULIO";
		}else if(mes == 8) {
			return "AGOSTO";
		}else if(mes == 9) {
			return "SEPTIEMBRE";
		}else if(mes == 10) {
			return "OCTUBRE";
		}else if(mes == 11){
			return "NOVIEMBRE";
		}else if(mes == 12) {
			return "DICIEMBRE";
		}else {
			return "";
		}
	}
	
    /**
     * Convierte un string con formato "yyyy-MM" en un java.sql.Date
     * con el primer día de ese mes.
     *
     * @param yearMonthStr Cadena en formato "yyyy-MM" (ej: "2025-01")
     * @return java.sql.Date correspondiente al primer día del mes (ej: 2025-01-01)
     */
    public static LocalDate primerDiaDelMes(String yearMonthStr) {
        YearMonth ym = YearMonth.parse(yearMonthStr);
        LocalDate primerDia = ym.atDay(1);
        return primerDia;
    }
}
