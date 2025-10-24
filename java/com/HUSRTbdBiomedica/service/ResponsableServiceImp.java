package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IResponsableDao;
import com.HUSRTbdBiomedica.app.entity.Responsable;

@Service
public class ResponsableServiceImp implements IResponsableService{

	@Autowired
	private IResponsableDao ResponsableDao;
	
	@Override
	public List<Responsable> ListResponsables() {
		// TODO Auto-generated method stub
		return (List<Responsable>)ResponsableDao.findAll();
	}

	@Override
	public Responsable findOne(Long id) {
		// TODO Auto-generated method stub
		return ResponsableDao.findById(id).orElse(null);
	}

	@Override
	public void save(Responsable responsable) {
		// TODO Auto-generated method stub
		ResponsableDao.save(responsable);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ResponsableDao.delete(findOne(id));
	}

	@Override
	public List<Responsable> listExternos() {
		// TODO Auto-generated method stub
		return ResponsableDao.listexternos();
	}

	@Override
	public List<Responsable> listResponsableComodato() {
		// TODO Auto-generated method stub
		return ResponsableDao.listResponsablesComodatos();
	}
	

}
