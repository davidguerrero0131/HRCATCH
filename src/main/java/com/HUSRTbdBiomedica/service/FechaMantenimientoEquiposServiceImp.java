package com.HUSRTbdBiomedica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.IFechaMantenimientoEquipoDao;
import com.HUSRTbdBiomedica.app.entity.FechaMantenimientoEquipos;

@Service
public class FechaMantenimientoEquiposServiceImp implements IFechaMantenimientoEquipoService{

	@Autowired
	private IFechaMantenimientoEquipoDao fechaMantenimientoEquiposDao;
	
	@Override
	@Transactional
	public void addRegistro(FechaMantenimientoEquipos fechaMantenimientoEquipos) {
		fechaMantenimientoEquiposDao.save(fechaMantenimientoEquipos);
		
	}

}
