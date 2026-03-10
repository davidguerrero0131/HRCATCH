package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ISystemMantenimientoDao;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;
@Service
public class SystemMantenimientoImp implements ISystemMantenimientoService{

	@Autowired
	private ISystemMantenimientoDao SystemMantenimientoDao;
	
	@Override
	public List<SystemMantenimiento> listmttosys() {
		// TODO Auto-generated method stub
		return (List<SystemMantenimiento>)SystemMantenimientoDao.findAll();
	}

	@Override
	public SystemMantenimiento findOne(Long id) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.findById(id).orElse(null);
	}

	@Override
	public void save(SystemMantenimiento mtto) {
		// TODO Auto-generated method stub
		SystemMantenimientoDao.save(mtto);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SystemMantenimientoDao.delete(findOne(id));
	}

	@Override
	public int countMttosys() {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countAll();
	}

	@Override
	public Long lastMttosysid() {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.findLastIdSysMtto();
	}

	@Override
	public List<SystemMantenimiento> mttosbyequipo(Long id) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.listMttobyequipo(id);
	}

	@Override
	public List<SystemMantenimiento> listcorrectbyequipo(Long id) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.listCorrectbyequipo(id);
	}

	@Override
	public List<String> liststrcorrbyequipo(Long id) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.listCorrstrbyequipo(id);
	}

	@Override
	public Time avghorallamado(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.horallamado(fecha1, fecha2);
	}

	@Override
	public Time avghorainicio(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.horainicio(fecha1, fecha2);
	}

	@Override
	public Time avghoraterminacion(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.horaterminacion(fecha1, fecha2);
	}

	@Override
	public int countCorrectivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countnumcorrectivos(fecha1, fecha2);
	}

	@Override
	public int countPreventivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countnumpreventivos(fecha1, fecha2);
	}

	@Override
	public int countPredictivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countnumpyv(fecha1, fecha2);
	}

	@Override
	public int countOtros(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countnumotros(fecha1, fecha2);
	}

	@Override
	public int countdesgastecorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countdesgastecorrectivo(fecha1, fecha2);
	}

	@Override
	public int countopindebidacorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countopindebidacorrectivo(fecha1, fecha2);
	}

	@Override
	public int countcausaexcorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countcausaexcorrectivo(fecha1, fecha2);
	}

	@Override
	public int countaccesorioscorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countaccesorioscorrectivo(fecha1, fecha2);
	}

	@Override
	public int countdesconocidocorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countdesconocidocorrectivo(fecha1, fecha2);
	}

	@Override
	public int countsinfallascorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countsinfallascorrectivo(fecha1, fecha2);
	}

	@Override
	public int countotrocorrectivo(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countotroscorrectivo(fecha1, fecha2);
	}

	@Override
	public int countdesgaste(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countdesgaste(fecha1, fecha2);
	}

	@Override
	public int countopindebida(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countopindebida(fecha1, fecha2);
	}

	@Override
	public int countcausaex(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countcausaex(fecha1, fecha2);
	}

	@Override
	public int countaccesorios(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countaccesorios(fecha1, fecha2);
	}

	@Override
	public int countdesconocido(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countdesconocido(fecha1, fecha2);
	}

	@Override
	public int countsinfallas(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countsinfallas(fecha1, fecha2);
	}

	@Override
	public int countotro(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.countotros(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgastecorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.desgastecorr(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebidacorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.opindebidacorr(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexternacorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.causaexcorr(fecha1, fecha2);
	}

	@Override
	public List<String> faccesorioscorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.accesorioscorr(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocidocorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.desconocidocorr(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallascorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.sinfallascorr(fecha1, fecha2);
	}

	@Override
	public List<String> fotroscorr(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.aotroscorr(fecha1, fecha2);
	}

	@Override
	public List<String> acorrectivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.correctivos(fecha1, fecha2);
	}

	@Override
	public List<String> apreventivos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.preventivos(fecha1, fecha2);
	}

	@Override
	public List<String> apyv(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.pyv(fecha1, fecha2);
	}

	@Override
	public List<String> aotros(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.otros(fecha1, fecha2);
	}

	@Override
	public List<String> fdesgaste(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.desgaste(fecha1, fecha2);
	}

	@Override
	public List<String> fopindebida(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.opindebida(fecha1, fecha2);
	}

	@Override
	public List<String> fcausaexterna(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.causaex(fecha1, fecha2);
	}

	@Override
	public List<String> faccesorios(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.accesorios(fecha1, fecha2);
	}

	@Override
	public List<String> fdesconocido(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.desconocido(fecha1, fecha2);
	}

	@Override
	public List<String> fsinfallas(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.sinfallas(fecha1, fecha2);
	}

	@Override
	public List<String> fotros(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.aotros(fecha1, fecha2);
	}

	@Override
	public List<SystemMantenimiento> ReporteMovimientos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.sysreportealmacen(fecha1, fecha2);
	}

	@Override
	public List<SystemMantenimiento> Entregas() {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.sysentregas();
	}

	@Override
	public List<String> listByMtto(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.listbymtto(fecha1, fecha2);
	}

	@Override
	public List<String> listSysTecs(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return SystemMantenimientoDao.listsystec(fecha1, fecha2);
	}

	@Override
	public Boolean MantenimientoEquipoAño(Long idEquipo) {
		Boolean res = false;
		if(SystemMantenimientoDao.fintMantenimientoEquipo(idEquipo) != null) {
			res = true;
		}
		return res;
	}
	
}
