package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IIndustrialRepuestoDao;
import com.HUSRTbdBiomedica.app.entity.IndustrialRepuesto;

@Service
public class IndustrialRepuestoServiceImp implements IIndustrialRepuestoService{

	@Autowired
	private IIndustrialRepuestoDao IndustrialRepuestoDao;
	
	@Override
	public List<IndustrialRepuesto> ListIndRepuestos() {
		// TODO Auto-generated method stub
		return (List<IndustrialRepuesto>)IndustrialRepuestoDao.findAll();
	}

	@Override
	public Optional<IndustrialRepuesto> ListIndustrialRepuestobyId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public IndustrialRepuesto findOne(Long id) {
		// TODO Auto-generated method stub
		return IndustrialRepuestoDao.findById(id).orElse(null);
	}

	@Override
	public void save(IndustrialRepuesto industrialRepuesto) {
		// TODO Auto-generated method stub
		IndustrialRepuestoDao.save(industrialRepuesto);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		IndustrialRepuestoDao.delete(findOne(id));
	}

}
