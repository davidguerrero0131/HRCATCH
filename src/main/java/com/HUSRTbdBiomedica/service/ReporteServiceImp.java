package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.IReporteDao;
import com.HUSRTbdBiomedica.app.entity.Reporte;

@Service
public class ReporteServiceImp implements IReporteService{

	@Autowired
	private IReporteDao ReporteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reporte> ListReportes() {
		return (List<Reporte>)ReporteDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Reporte> ListReportesbyId(Long id) {
		return null;
	}

	@Override
	@Transactional
	public Reporte findOne(Long id) {
		return ReporteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Reporte reporte) {
		ReporteDao.save(reporte);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ReporteDao.delete(findOne(id));
	}

	@Override
	@Transactional
	public List<Reporte> ReportesbyEquipo(Long id) {
		return ReporteDao.findReportesbyEquipo(id);
	}
	@Override
	@Transactional
	public int countReportesbyEquipo(Long id) {
		return ReporteDao.countReportesbyEquipo(id);
	}

	@Override
	public Long LastIdReporte() {
		return ReporteDao.findLastIdReporte();
	}

	
	
	//indicadores
	
	
	@Override
	public int countCorrectivos(Date fecha1, Date fecha2) {
		return ReporteDao.countnumcorrectivos(fecha1, fecha2);
	}

	@Override
	public int countPreventivos(Date fecha1, Date fecha2) {
		return ReporteDao.countnumpreventivos(fecha1, fecha2);
	}

	@Override
	public int countPredictivos(Date fecha1, Date fecha2) {
		return ReporteDao.countnumpredictivos(fecha1, fecha2);
	}

	@Override
	public int countOtros(Date fecha1, Date fecha2) {
		return ReporteDao.countnumotros(fecha1, fecha2);
	}

	@Override
	public int countdesgastecorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countdesgastecorrectivo(fecha1, fecha2);
	}

	@Override
	public int countopindebidacorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countopindebidacorrectivo(fecha1, fecha2);
	}

	@Override
	public int countcausaexcorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countcausaexcorrectivo(fecha1, fecha2);
	}

	@Override
	public int countaccesorioscorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countaccesorioscorrectivo(fecha1, fecha2);
	}

	@Override
	public int countdesconocidocorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countdesconocidocorrectivo(fecha1, fecha2);
	}

	@Override
	public int countsinfallascorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countsinfallascorrectivo(fecha1, fecha2);
	}

	@Override
	public int countotrocorrectivo(Date fecha1, Date fecha2) {
		return ReporteDao.countotroscorrectivo(fecha1, fecha2);
	}

	@Override
	public int countdesgaste(Date fecha1, Date fecha2) {
		return ReporteDao.countdesgaste(fecha1, fecha2);
	}

	@Override
	public int countopindebida(Date fecha1, Date fecha2) {
		return ReporteDao.countopindebida(fecha1, fecha2);
	}

	@Override
	public int countcausaex(Date fecha1, Date fecha2) {
		return ReporteDao.countcausaex(fecha1, fecha2);
	}

	@Override
	public int countaccesorios(Date fecha1, Date fecha2) {
		return ReporteDao.countaccesorios(fecha1, fecha2);
	}

	@Override
	public int countdesconocido(Date fecha1, Date fecha2) {
		return ReporteDao.countdesconocido(fecha1, fecha2);
	}

	@Override
	public int countsinfallas(Date fecha1, Date fecha2) {
		return ReporteDao.countsinfallas(fecha1, fecha2);
	}

	@Override
	public int countotro(Date fecha1, Date fecha2) {
		return ReporteDao.countotros(fecha1, fecha2);
	}

	@Override
	public Time tiempopromedio(Date fecha1, Date fecha2) {
		return ReporteDao.listatiempototal(fecha1, fecha2);
	}

	@Override
	public Time avghorallamado(Date fecha1, Date fecha2) {
		if(ReporteDao.horallamado(fecha1, fecha2)!=null) {
			return ReporteDao.horallamado(fecha1, fecha2);
		}
		else {
			return Time.valueOf(LocalTime.of(0, 0, 0));
		}
		
	}

	@Override
	public Time avghorainicio(Date fecha1, Date fecha2) {
		
		if(ReporteDao.horainicio(fecha1, fecha2)!=null) {
			return ReporteDao.horainicio(fecha1, fecha2);
		}
		else {
			return Time.valueOf(LocalTime.of(0, 0, 0));
		}
	}

	@Override
	public List<String> acorrectivos(Date fecha1, Date fecha2) {
		return ReporteDao.correctivos(fecha1, fecha2);
	}

	@Override
	public List<String> apreventivos(Date fecha1, Date fecha2) {
		return ReporteDao.preventivos(fecha1, fecha2);
	}

	@Override
	public List<String> apredictivos(Date fecha1, Date fecha2) {
		return ReporteDao.predictivos(fecha1, fecha2);
	}

	@Override
	public List<String> aotros(Date fecha1, Date fecha2) {
		return ReporteDao.otros(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgaste(Date fecha1, Date fecha2) {
		return ReporteDao.desgaste(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebida(Date fecha1, Date fecha2) {
		return ReporteDao.opindebida(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexterna(Date fecha1, Date fecha2) {
		return ReporteDao.causaex(fecha1, fecha2);
	}

	@Override
	public List<String> faccesorios(Date fecha1, Date fecha2) {
		return ReporteDao.accesorios(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocido(Date fecha1, Date fecha2) {
		return ReporteDao.desconocido(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallas(Date fecha1, Date fecha2) {
		return ReporteDao.sinfallas(fecha1, fecha2);
	}

	@Override
	public List<String> fotros(Date fecha1, Date fecha2) {
		return ReporteDao.aotros(fecha1, fecha2);
	}

	@Override
	public Reporte findmttoprevbyrepor(Long id) {
		return ReporteDao.findReportebymtto(id);
	}

	@Override
	public List<Reporte> findotrosreportesbyFecha(Long id, Date fecha1, Date fecha2) {
		return ReporteDao.findotrosreportesbyfecha(id, fecha1, fecha2);
	}

	@Override
	public String MaxtimeOut(Long id, Date fecha1, Date fecha2) {
		return ReporteDao.findmaxtimeoutservice(id, fecha1, fecha2);
	}

	@Override
	public int numpreventivobyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.mttopreventivobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numpredictivobyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.mttopredictivobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numcorrectivobyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.mttocorrectivobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numotrobyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.mttootrobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfdesgastebyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.falladesgastebyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfopindebidabyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.fallaopindebidabyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfcausaexbyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.fallacausaexbyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfaccesoriosbyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.fallaaccesoriobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfdesconocidobyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.falladesconocidobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfsinfallasbyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.fallasinbyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numfotrosbyequipo(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.fallaotrobyequipo(fecha1, fecha2, id);
	}

	@Override
	public int numtotalreportesfecha(Date fecha1, Date fecha2, Long id) {
		return ReporteDao.countReportesbyEquipodate(fecha1, fecha2, id);
	}

	@Override
	public List<Reporte> reportesbyRepuestos(Date fecha1, Date fecha2) {
		return ReporteDao.repuestos(fecha1, fecha2);
	}

	@Override
	public List<Reporte> reportebyRange(Date fecha1, Date fecha2) {
		return ReporteDao.rangoreportes(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgastecorr(Date fecha1, Date fecha2) {
		return ReporteDao.desgastecorr(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebidacorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.opindebidacorr(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexternacorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.causaexcorr(fecha1, fecha2);
	}

	@Override
	public List<String> faccesorioscorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.accesorioscorr(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocidocorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.desconocidocorr(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallascorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.sinfallascorr(fecha1, fecha2);
	}

	@Override
	public List<String> fotroscorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.aotroscorr(fecha1, fecha2);
	}

	@Override
	public List<Reporte> ReportesBySerieYPlaca(String serie, String placa) {
		// TODO Auto-generated method stub
		return ReporteDao.findreportesBySerieAndPlaca(serie, placa);
	}

	@Override
	public Reporte findOneByNumber(String numero) {
		// TODO Auto-generated method stub
		return ReporteDao.getByNumerReport(numero);
	}

	@Override
	public List<Reporte> mantenimientosCorrectivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return ReporteDao.mantenimientosCorrectivos(fecha1, fecha2);
	}
	

}
