package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.ISystemHoja_vidaDao;
import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;

@Service
public class SystemHoja_vidaImp implements ISystemHoja_vidaService{

	@Autowired
	private ISystemHoja_vidaDao SystemHoja_vidaDao;
	
	@Override
	@Transactional
	public List<SystemHoja_vida> listmttosys() {
		// TODO Auto-generated method stub
		return (List<SystemHoja_vida>)SystemHoja_vidaDao.findAll();
	}

	@Override
	@Transactional
	public SystemHoja_vida findOne(Long id) {
		// TODO Auto-generated method stub
		return SystemHoja_vidaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(SystemHoja_vida hv) {
		// TODO Auto-generated method stub
		SystemHoja_vidaDao.save(hv);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SystemHoja_vidaDao.delete(findOne(id));
	}

	@Override
	@Transactional
	public int countHv() {
		// TODO Auto-generated method stub
		return SystemHoja_vidaDao.countAll();
	}

	@Override
	public SystemHoja_vida findHv(Long id) {
		// TODO Auto-generated method stub
		return SystemHoja_vidaDao.findHVbySystemEquipo(id);
	}

	@Override
	public void updateIPHv(long idEquipo, String iP) {
		SystemHoja_vidaDao.UpdateIPEquipo(idEquipo, iP);
		
	}

}
