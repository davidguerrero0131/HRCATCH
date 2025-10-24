package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IRepuestosDao;
import com.HUSRTbdBiomedica.app.entity.Repuestos;
@Service
public class RepuestosServiceImp implements IRepuestosService{

	@Autowired
	private IRepuestosDao RepuestosDao;
	
	@Override
	public List<Repuestos> ListRepuestos() {
		// TODO Auto-generated method stub
		return (List<Repuestos>)RepuestosDao.findAll();
	}

	@Override
	public Repuestos findOne(Long id) {
		// TODO Auto-generated method stub
		return RepuestosDao.findById(id).orElse(null);
	}

	@Override
	public void save(Repuestos repuestos) {
		// TODO Auto-generated method stub
		RepuestosDao.save(repuestos);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		RepuestosDao.delete(findOne(id));
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return RepuestosDao.countAll();
	}

	@Override
	public int countRepuestosbyEquipo(Long id) {
		// TODO Auto-generated method stub
		return RepuestosDao.countRepuestosbyEquipo(id);
	}

	@Override
	public List<Repuestos> findRepuestosbyEquipo(Long id) {
		// TODO Auto-generated method stub
		return RepuestosDao.RepuestosbyEquipo(id);
	}

	@Override
	public List<Repuestos> findRepuestosbyTipoEquipo(Long id) {
		// TODO Auto-generated method stub
		return RepuestosDao.RepuestosbyTipoEquipo(id);
	}

}
