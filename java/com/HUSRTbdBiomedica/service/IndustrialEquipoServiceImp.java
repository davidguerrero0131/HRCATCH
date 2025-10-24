package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IIndustrialEquipoDao;
import com.HUSRTbdBiomedica.app.entity.IndustrialEquipo;

@Service
public class IndustrialEquipoServiceImp implements IIndustrialEquipoService{

	@Autowired
	private IIndustrialEquipoDao IndustrialEquipoDao;
	
	@Override
	public List<IndustrialEquipo> ListIndEquipos() {
		// TODO Auto-generated method stub
		return (List<IndustrialEquipo>)IndustrialEquipoDao.findAll();
	}

	@Override
	public Optional<IndustrialEquipo> ListIndustrialEquipobyId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public IndustrialEquipo findOne(Long id) {
		// TODO Auto-generated method stub
		return IndustrialEquipoDao.findById(id).orElse(null);
	}

	@Override
	public void save(IndustrialEquipo industrialEquipo) {
		// TODO Auto-generated method stub
		IndustrialEquipoDao.save(industrialEquipo);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		IndustrialEquipoDao.delete(findOne(id));
	}

	@Override
	public List<IndustrialEquipo> listIndEquiposbyTipo(Long id) {
		// TODO Auto-generated method stub
		return IndustrialEquipoDao.findIndEquiposbyTipoEquipo(id);
	}

	@Override
	public List<IndustrialEquipo> listIndEquiposbyServicio(Long id) {
		// TODO Auto-generated method stub
		return IndustrialEquipoDao.findIndEquiposbyServicio(id);
	}

}
