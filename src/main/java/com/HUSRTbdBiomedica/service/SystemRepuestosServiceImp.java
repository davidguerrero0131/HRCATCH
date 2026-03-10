package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ISystemRepuestosDao;
import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;

@Service
public class SystemRepuestosServiceImp implements ISystemRepuestosService{

	@Autowired
	private ISystemRepuestosDao SystemRepuestosDao;
	
	@Override
	public List<SystemRepuestos> listrepuestossys() {
		// TODO Auto-generated method stub
		return (List<SystemRepuestos>)SystemRepuestosDao.findAll();
	}

	@Override
	public SystemRepuestos findOne(Long id) {
		// TODO Auto-generated method stub
		return SystemRepuestosDao.findById(id).orElse(null);
	}

	@Override
	public void save(SystemRepuestos repuesto) {
		// TODO Auto-generated method stub
		SystemRepuestosDao.save(repuesto);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SystemRepuestosDao.delete(findOne(id));	
	}

	@Override
	public int countRepuestossys() {
		// TODO Auto-generated method stub
		return SystemRepuestosDao.countAll();
	}

	@Override
	public List<SystemRepuestos> listrepuestosbyequipo(Long id) {
		// TODO Auto-generated method stub
		return SystemRepuestosDao.listrepuestosbyequipo(id);
	}

	@Override
	public List<SystemRepuestos> listrespuestosavail() {
		// TODO Auto-generated method stub
		return SystemRepuestosDao.listwithoutEquipo();
	}

}
