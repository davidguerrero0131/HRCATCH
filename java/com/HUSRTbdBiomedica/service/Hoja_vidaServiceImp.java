package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IHoja_vidaDao;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida;

@Service
public class Hoja_vidaServiceImp implements IHoja_vidaService {
	
	@Autowired
	private IHoja_vidaDao Hoja_vidaDao;

	@Override
	@Transactional
	public List<Hoja_vida> ObtainallHV() {
		return (List<Hoja_vida>)Hoja_vidaDao.findAll();
	}

	@Override
	@Transactional
	public int countAllHV() {
		return Hoja_vidaDao.countAll();
	}

	@Override
	@Transactional
	public Hoja_vida findOne(Long id) {
		return Hoja_vidaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Hoja_vida hoja_vida) {
		Hoja_vidaDao.save(hoja_vida);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Hoja_vidaDao.deleteById(id);;
	}

	@Override
	@Transactional
	public Hoja_vida findHVbyEquipo(Long id) {
		return Hoja_vidaDao.findHVbyEquipo(id);
	}

	@Override
	public List<Hoja_vida> findHvbyVcto(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return Hoja_vidaDao.findhvbyVcto(fecha1, fecha2);
	}

	
	
	
	

}
