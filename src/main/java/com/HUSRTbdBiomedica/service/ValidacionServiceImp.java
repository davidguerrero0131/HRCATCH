package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IValidacionDao;
import com.HUSRTbdBiomedica.app.entity.Validacion;

@Service
public class ValidacionServiceImp implements IValidacionService{

	@Autowired
	public IValidacionDao ValidacionDao;
	
	@Override
	public List<Validacion> listValidacion() {
		// TODO Auto-generated method stub
		return (List<Validacion>)ValidacionDao.findAll();
	}

	@Override
	public List<Validacion> listValidacionbyEquipo(Long id) {
		// TODO Auto-generated method stub
		return ValidacionDao.findvalidacionbyEquipo(id);
	}

	@Override
	public Validacion findOne(Long id) {
		// TODO Auto-generated method stub
		return ValidacionDao.findById(id).orElse(null);
	}

	@Override
	public void save(Validacion validacion) {
		// TODO Auto-generated method stub
		ValidacionDao.save(validacion);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ValidacionDao.delete(findOne(id));
	}

	@Override
	public List<Validacion> findvalbyDate(int mes, int ano) {
		// TODO Auto-generated method stub
		return ValidacionDao.findbydate(mes, ano);
	}

	@Override
	public int countValidacion(int mes, int ano) {
		// TODO Auto-generated method stub
		return ValidacionDao.countCalval(mes, ano);
	}

	@Override
	public List<Validacion> findvalidacionbyequipocheck(Long id) {
		// TODO Auto-generated method stub
		return ValidacionDao.valcomplete(id);
	}
	
	

	
}
