package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ICalibracionDao;
import com.HUSRTbdBiomedica.app.entity.Calibracion;

@Service
public class CalibracionServiceImp implements ICalibracionService{

	@Autowired
	private ICalibracionDao CalibracionDao;
	
	@Override
	public List<Calibracion> listCalibracion() {
		// TODO Auto-generated method stub
		return (List<Calibracion>)CalibracionDao.findAll();
	}

	@Override
	public List<Calibracion> listCalibracionbyEquipo(Long id) {
		return CalibracionDao.findcalbyEquipo(id);
	}

	@Override
	public Calibracion findOne(Long id) {
		return CalibracionDao.findById(id).orElse(null);
	}

	@Override
	public void save(Calibracion calibracion) {
		CalibracionDao.save(calibracion);
	}

	@Override
	public void delete(Long id) {
		CalibracionDao.delete(findOne(id));
	}

	@Override
	public List<Calibracion> findbyDate(int mes, int ano) {
		return CalibracionDao.findbydate(mes, ano);
	}

	@Override
	public int countCal(int mes, int ano) {
		// TODO Auto-generated method stub
		return CalibracionDao.countCalval(mes,ano);
	}

	@Override
	public List<Calibracion> findcalbyequipocheck(Long id) {
		// TODO Auto-generated method stub
		return CalibracionDao.calcomplete(id);
	}

	@Override
	public List<Calibracion> findActbyequipoProgramada(Long idEquipo, int mesProgramada, int añoProgramada) {
		// TODO Auto-generated method stub
		return CalibracionDao.getActividadPRogramadaEquipo(idEquipo, mesProgramada, añoProgramada);
	}

	@Override
	public List<Calibracion> findbyDateAll(int mes, int ano) {
		// TODO Auto-generated method stub
		return CalibracionDao.findbydateAll(mes, ano);
	}

}
