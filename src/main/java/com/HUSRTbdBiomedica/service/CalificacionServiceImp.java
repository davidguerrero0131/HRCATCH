package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ICalificacionDao;
import com.HUSRTbdBiomedica.app.entity.Calificacion;

@Service
public class CalificacionServiceImp implements ICalificacionService{

	@Autowired
	public ICalificacionDao CalificacionDao;
	
	@Override
	public List<Calificacion> listCalificacion() {
		// TODO Auto-generated method stub
		return (List<Calificacion>)CalificacionDao.findAll();
	}

	@Override
	public List<Calificacion> listCalificacionbyEquipo(Long id) {
		// TODO Auto-generated method stub
		return CalificacionDao.findcalificacionbyEquipo(id);
	}

	@Override
	public Calificacion findOne(Long id) {
		// TODO Auto-generated method stub
		return CalificacionDao.findById(id).orElse(null);
	}

	@Override
	public void save(Calificacion calificacion) {
		// TODO Auto-generated method stub
		CalificacionDao.save(calificacion);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		CalificacionDao.delete(findOne(id));
	}

	@Override
	public List<Calificacion> findbyDate(int mes, int ano) {
		// TODO Auto-generated method stub
		return CalificacionDao.findbydate(mes, ano);
	}

	@Override
	public int countCalificacion(int mes, int ano) {
		// TODO Auto-generated method stub
		return CalificacionDao.countCalval(mes, ano);
	}

	@Override
	public List<Calificacion> findcalificacionbyequipocheck(Long id) {
		// TODO Auto-generated method stub
		return CalificacionDao.calcomplete(id);
	}

}
