package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ILicenciaDao;
import com.HUSRTbdBiomedica.app.entity.Licencia;

@Service
public class LicenciaServiceImp implements ILicenciaService{

	@Autowired
	private ILicenciaDao LicenciaDao;
	
	@Override
	public List<Licencia> ListLicencia() {
		// TODO Auto-generated method stub
		return (List<Licencia>)LicenciaDao.findAll();
	}

	@Override
	public Licencia findOne(Long id) {
		// TODO Auto-generated method stub
		return LicenciaDao.findById(id).orElse(null);
	}

	@Override
	public void save(Licencia licencia) {
		// TODO Auto-generated method stub
		LicenciaDao.save(licencia);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		LicenciaDao.delete(findOne(id));
	}

	@Override
	public int countAllLicencia() {
		// TODO Auto-generated method stub
		return LicenciaDao.countAll();
	}

	@Override
	public String lastIdLicencia() {
		// TODO Auto-generated method stub
		return LicenciaDao.findLastIdLicencia();
	}

}
