package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ISysBajaDao;
import com.HUSRTbdBiomedica.app.entity.SystemBaja;

@Service
public class SysBajaServiceImp implements ISystemBajaService{
	
	@Autowired
	private ISysBajaDao SysBajaDao;

	@Override
	public List<SystemBaja> listBajas() {
		return SysBajaDao.getAllBajas();
	}

	@Override
	public SystemBaja findOne(Long id) {
		
		return SysBajaDao.getById(id);
	}

	@Override
	public void save(SystemBaja baja) {
		SysBajaDao.save(baja);
		
	}

	@Override
	public void delete(Long id) {
		SysBajaDao.delete(null);
	}

}
