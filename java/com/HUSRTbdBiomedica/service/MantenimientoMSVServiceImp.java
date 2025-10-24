package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IMantenimientoMSVDao;
import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;

@Service
public class MantenimientoMSVServiceImp implements IMantenimientoMSVService {
	
	@Autowired
	private IMantenimientoMSVDao mantenimientoMSVDao;

	@Override
	public List<MantenimientoMSV> getAllMantenimientoMSV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMantenimientoMSV(MantenimientoMSV mantenimientoMSV) {
		 mantenimientoMSVDao.save(mantenimientoMSV);		
	}

	@Override
	public MantenimientoMSV getMantenimientoMSVEspec(Long idEquipo, Long idMantenimiento) {
		
		return mantenimientoMSVDao.getMantenimientoMSVEspec(idEquipo, idMantenimiento);
	}

}
