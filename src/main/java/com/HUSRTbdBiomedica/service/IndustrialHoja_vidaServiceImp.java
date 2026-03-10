package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IIndustrialHoja_vidaDao;
import com.HUSRTbdBiomedica.app.entity.IndustrialHoja_vida;

@Service
public class IndustrialHoja_vidaServiceImp implements IIndustrialHoja_vidaService{

	@Autowired
	private IIndustrialHoja_vidaDao IndustrialHoja_vidaDao;
	
	@Override
	public List<IndustrialHoja_vida> ListIndHvs() {
		// TODO Auto-generated method stub
		return (List<IndustrialHoja_vida>)IndustrialHoja_vidaDao.findAll();
	}

	@Override
	public Optional<IndustrialHoja_vida> ListIndustrialHVbyId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public IndustrialHoja_vida findOne(Long id) {
		// TODO Auto-generated method stub
		return IndustrialHoja_vidaDao.findById(id).orElse(null);
	}

	@Override
	public void save(IndustrialHoja_vida industrialHoja_vida) {
		// TODO Auto-generated method stub
		IndustrialHoja_vidaDao.save(industrialHoja_vida);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		IndustrialHoja_vidaDao.delete(findOne(id));
	}

}
