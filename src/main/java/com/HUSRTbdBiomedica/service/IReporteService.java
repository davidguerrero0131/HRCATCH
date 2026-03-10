package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import com.HUSRTbdBiomedica.app.entity.Reporte;


public interface IReporteService {
	public List<Reporte>ListReportes();
	public List<Reporte>mantenimientosCorrectivos(Date fecha1, Date fecha2); 
	public Optional<Reporte>ListReportesbyId(Long id);
	public Reporte findOne(Long id);
	public Reporte findOneByNumber(String numero);
	public void save(Reporte reporte);
	public void delete(Long id);
	public List<Reporte>ReportesbyEquipo(Long id);
	public List<Reporte>ReportesBySerieYPlaca(String serie, String placa);
	public int countReportesbyEquipo(Long id);
	public Long LastIdReporte();
	public String MaxtimeOut(Long id, Date fecha1, Date fecha2);
	//indicadores individuales
	public List<String> acorrectivos(Date fecha1, Date fecha2);
	public List<String> apreventivos(Date fecha1, Date fecha2);
	public List<String> apredictivos(Date fecha1, Date fecha2);
	public List<String> aotros(Date fecha1, Date fecha2);
	
	
	
	
	public List<String> fdesgaste(Date fecha1, Date fecha2);
	public List<String> fopindebida(Date fecha1, Date fecha2);
	public List<String> fcausaexterna(Date fecha1, Date fecha2);
	public List<String> faccesorios(Date fecha1, Date fecha2);
	public List<String> fdesconocido(Date fecha1, Date fecha2);
	public List<String> fsinfallas(Date fecha1, Date fecha2);
	public List<String> fotros(Date fecha1, Date fecha2);
	
	
	
	//indices
	public Time avghorallamado(Date fecha1, Date fecha2);
	public Time avghorainicio(Date fecha1, Date fecha2);
	
	public Time tiempopromedio(Date fecha1,Date fecha2);
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
	//mttoprev
	public Reporte findmttoprevbyrepor(Long id);
	//visualizacionotros
	public List<Reporte> findotrosreportesbyFecha(Long id, Date fecha1, Date fecha2);
	
	//indicadoresporequipo
	public int numpreventivobyequipo(Date fecha1, Date fecha2, Long id);
	public int numpredictivobyequipo(Date fecha1, Date fecha2, Long id);
	public int numcorrectivobyequipo(Date fecha1, Date fecha2, Long id);
	public int numotrobyequipo(Date fecha1, Date fecha2, Long id);
	
	public int numfdesgastebyequipo(Date fecha1, Date fecha2, Long id);
	public int numfopindebidabyequipo(Date fecha1, Date fecha2, Long id);
	public int numfcausaexbyequipo(Date fecha1, Date fecha2, Long id);
	public int numfaccesoriosbyequipo(Date fecha1, Date fecha2, Long id);
	public int numfdesconocidobyequipo(Date fecha1, Date fecha2, Long id);
	public int numfsinfallasbyequipo(Date fecha1, Date fecha2, Long id);
	public int numfotrosbyequipo(Date fecha1, Date fecha2, Long id);
	
	public int numtotalreportesfecha(Date fecha1, Date fecha2, Long id);
	//repuestos
	public List<Reporte> reportesbyRepuestos(Date fecha1, Date fecha2);
	//rango
	public List<Reporte> reportebyRange(Date fecha1, Date fecha2);

}
