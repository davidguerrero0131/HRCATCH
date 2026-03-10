package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;

public interface ISystemMantenimientoService {

	public List<SystemMantenimiento> listmttosys();
	public SystemMantenimiento findOne(Long id);
	public void save(SystemMantenimiento mtto);
	public void delete(Long id);
	
	public int countMttosys();
	public Long lastMttosysid();
	
	public List<SystemMantenimiento> mttosbyequipo(Long id);
	
	public List<SystemMantenimiento> listcorrectbyequipo(Long id);
	public List<String> liststrcorrbyequipo(Long id);
	
	//indicadores
	public Time avghorallamado(Date fecha1, Date fecha2);
	public Time avghorainicio(Date fecha1, Date fecha2);
	
	public Time avghoraterminacion(Date fecha1,Date fecha2);
	public int countCorrectivos(Date fecha1, Date fecha2);
	public int countPreventivos(Date fecha1, Date fecha2);
	public int countPredictivos(Date fecha1, Date fecha2);
	public int countOtros(Date fecha1, Date fecha2);
	
	public int countdesgastecorrectivo(Date fecha1,Date fecha2);
	public int countopindebidacorrectivo(Date fecha1,Date fecha2);
	public int countcausaexcorrectivo(Date fecha1,Date fecha2);
	public int countaccesorioscorrectivo(Date fecha1,Date fecha2);
	public int countdesconocidocorrectivo(Date fecha1,Date fecha2);
	public int countsinfallascorrectivo(Date fecha1,Date fecha2);
	public int countotrocorrectivo(Date fecha1,Date fecha2);
	
	public int countdesgaste(Date fecha1,Date fecha2);
	public int countopindebida(Date fecha1,Date fecha2);
	public int countcausaex(Date fecha1,Date fecha2);
	public int countaccesorios(Date fecha1,Date fecha2);
	public int countdesconocido(Date fecha1,Date fecha2);
	public int countsinfallas(Date fecha1,Date fecha2);
	public int countotro(Date fecha1,Date fecha2);
	
	
	
	public List<String> fdesgastecorr(Date fecha1, Date fecha2);
	public List<String> fopindebidacorr(Date fecha1, Date fecha2);
	public List<String> fcausaexternacorr(Date fecha1, Date fecha2);
	public List<String> faccesorioscorr(Date fecha1, Date fecha2);
	public List<String> fdesconocidocorr(Date fecha1, Date fecha2);
	public List<String> fsinfallascorr(Date fecha1, Date fecha2);
	public List<String> fotroscorr(Date fecha1, Date fecha2);
	
	//indicadores2
	public List<String> acorrectivos(Date fecha1, Date fecha2);
	public List<String> apreventivos(Date fecha1, Date fecha2);
	public List<String> apyv(Date fecha1, Date fecha2);
	public List<String> aotros(Date fecha1, Date fecha2);
	
	
	
	
	public List<String> fdesgaste(Date fecha1, Date fecha2);
	public List<String> fopindebida(Date fecha1, Date fecha2);
	public List<String> fcausaexterna(Date fecha1, Date fecha2);
	public List<String> faccesorios(Date fecha1, Date fecha2);
	public List<String> fdesconocido(Date fecha1, Date fecha2);
	public List<String> fsinfallas(Date fecha1, Date fecha2);
	public List<String> fotros(Date fecha1, Date fecha2);
	
	public List<SystemMantenimiento> ReporteMovimientos(Date fecha1, Date fecha2);
	public List<SystemMantenimiento> Entregas();
	
	public List<String> listByMtto(Date fecha1,Date fecha2);
	public List<String> listSysTecs(Date fecha1, Date fecha2);
	
	public Boolean MantenimientoEquipoAño(Long idequipo);

}
