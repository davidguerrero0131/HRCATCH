package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IHoja_vida_comodatosDao;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_comodatos;

@Service
public class Hoja_vidaComodatosServiceImp implements IHoja_vida_comodatosService {

	@Autowired
	private IHoja_vida_comodatosDao Hoja_vida_comodatosDao;
	
	@Override
	@Transactional
	public List<Hoja_vida_comodatos> Hoja_vida_otrosObtainallHV() {
		
		return (List<Hoja_vida_comodatos>) Hoja_vida_comodatosDao.findAll();
	}

	@Override
	public int CounallOtrosHV() {
		
		return Hoja_vida_comodatosDao.countAll();
	}

	@Override
	public Hoja_vida_comodatos findOne(Long id) {
		// TODO Auto-generated method stub
		return Hoja_vida_comodatosDao.findById(id).orElse(null);
	}

	@Override
	public void save(Hoja_vida_comodatos hoja_vida_otros) {
		Hoja_vida_comodatosDao.save(hoja_vida_otros);
		
	}

	@Override
	public void delete(Long id) {
		Hoja_vida_comodatosDao.delete(findOne(id));
		
	}

	@Override
	public List<String> listProveedores() {
		// TODO Auto-generated method stub
		return Hoja_vida_comodatosDao.findProveedores();
	}

	@Override
	public List<Hoja_vida_comodatos> listByProveedor(String nombreProveedor) {
		// TODO Auto-generated method stub
		return Hoja_vida_comodatosDao.findByProveedor(nombreProveedor);
	}

	@Override
	public Long LastHV() {
		// TODO Auto-generated method stub
		return  Hoja_vida_comodatosDao.findLastHV();
	}

	@Override
	public void desactivarComodato(long idHVComodato) {
		
		Hoja_vida_comodatosDao.desactivarComodato(idHVComodato);
		
	}


}
