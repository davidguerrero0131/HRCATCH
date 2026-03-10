package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IReporte_bajaDao;
import com.HUSRTbdBiomedica.app.entity.Reporte_baja;
@Service
public class Reporte_bajaServiceImp implements IReporte_bajaService{

	@Autowired
	private IReporte_bajaDao Reporte_bajaDao;
	
	@Override
	public List<Reporte_baja> ListReportes() {
		return (List<Reporte_baja>)Reporte_bajaDao.findAll();
	}

	@Override
	public Optional<Reporte_baja> ListReportesbyId(Long id) {
		return null;
	}

	@Override
	public Reporte_baja findOne(Long id) {
		return Reporte_bajaDao.findById(id).orElse(null);
	}

	@Override
	public void save(Reporte_baja reporte_baja) {
		Reporte_bajaDao.save(reporte_baja);
		
	}

	@Override
	public void delete(Long id) {
		Reporte_bajaDao.delete(findOne(id));
		
	}

	@Override
	public List<Reporte_baja> ReportesbyBaja(Long id) {
		return Reporte_bajaDao.findReportesbyBaja(id);
	}

	@Override
	public int countReportesbyBaja(Long id) {
		return Reporte_bajaDao.countReportesbyBaja(id);
	}

	@Override
	public Long LastIdReporteBaja() {
		return Reporte_bajaDao.findLastIdReporte();
	}

	@Override
	public int countCorrectivosbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countnumcorrectivosbaja(fecha1, fecha2);
	}

	@Override
	public int countPreventivosbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countnumpreventivosbaja(fecha1, fecha2);
	}

	@Override
	public int countPredictivosbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countnumpredictivosbaja(fecha1, fecha2);
	}

	@Override
	public int countOtrosbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countnumotrosbaja(fecha1, fecha2);
	}

	@Override
	public int countdesgastecorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countdesgastecorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countopindebidacorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countopindebidacorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countcausaexcorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countcausaexcorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countaccesorioscorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countaccesorioscorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countdesconocidocorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countdesconocidocorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countsinfallascorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countsinfallascorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countotrocorrectivobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countotroscorrectivobaja(fecha1, fecha2);
	}

	@Override
	public int countdesgastebaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countdesgastebaja(fecha1, fecha2);
	}

	@Override
	public int countopindebidabaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countopindebidabaja(fecha1, fecha2);
	}

	@Override
	public int countcausaexbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countcausaexbaja(fecha1, fecha2);
	}

	@Override
	public int countaccesoriosbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countaccesoriosbaja(fecha1, fecha2);
	}

	@Override
	public int countdesconocidobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countdesconocidobaja(fecha1, fecha2);
	}

	@Override
	public int countsinfallasbaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countsinfallasbaja(fecha1, fecha2);
	}

	@Override
	public int countotrobaja(Date fecha1, Date fecha2) {
		return Reporte_bajaDao.countotrosbaja(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgastebaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.desgaste(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebidabaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.opindebida(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexternabaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.causaex(fecha1, fecha2);
	}

	@Override
	public List<String> faccesoriosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.accesorios(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocidobaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.desconocido(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallasbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.sinfallas(fecha1, fecha2);
	}

	@Override
	public List<String> fotrosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.aotros(fecha1, fecha2);
	}

	@Override
	public List<String> acorrectivosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.correctivosbaja(fecha1, fecha2);
	}

	@Override
	public List<String> apreventivosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.preventivosbaja(fecha1, fecha2);
	}

	@Override
	public List<String> apredictivosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.predictivosbaja(fecha1, fecha2);
	}

	@Override
	public List<String> aotrosbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.otrosbaja(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgastecorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.desconocidocorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebidacorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.opindebidacorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexternacorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.causaexcorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> faccesorioscorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.accesorioscorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocidocorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.desconocidocorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallascorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.sinfallascorrbj(fecha1, fecha2);
	}

	@Override
	public List<String> fotroscorrbaja(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Reporte_bajaDao.aotroscorrbj(fecha1, fecha2);
	}

	
	
}
