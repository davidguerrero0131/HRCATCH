package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.HUSRTbdBiomedica.app.Dao.IHoja_vida_otrosDao;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_otros;

@Service
public class Hoja_vida_otrosServiceImp implements IHoja_vida_otrosService{

	@Autowired
	private IHoja_vida_otrosDao Hoja_vida_otrosDao;
	
	@Override
	@Transactional
	public List<Hoja_vida_otros> Hoja_vida_otrosObtainallHV() {
		return (List<Hoja_vida_otros>) Hoja_vida_otrosDao.findAll();
	}

	@Override
	@Transactional
	public int CounallOtrosHV() {
		return Hoja_vida_otrosDao.countAll();
	}

	@Override
	@Transactional
	public Hoja_vida_otros findOne(Long id) {
		return Hoja_vida_otrosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Hoja_vida_otros hoja_vida_otros) {
		Hoja_vida_otrosDao.save(hoja_vida_otros);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Hoja_vida_otrosDao.delete(findOne(id));
		
	}

	@Override
	public Long LastHV() {
		// TODO Auto-generated method stub
		return Hoja_vida_otrosDao.findLastHV();
	}

	@Override
	public List<String> listProveedores() {
		// TODO Auto-generated method stub
		return Hoja_vida_otrosDao.findProveedores();
	}

	@Override
	public List<Hoja_vida_otros> listByProveedor(String nombreProveedor) {
		// TODO Auto-generated method stub
		return Hoja_vida_otrosDao.findByProveedor(nombreProveedor);
	}
	

}
