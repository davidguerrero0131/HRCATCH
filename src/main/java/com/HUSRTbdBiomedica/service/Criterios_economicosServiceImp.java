package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.Dao.ICriterios_economicosDao;
import com.HUSRTbdBiomedica.app.entity.Criterios_economicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class Criterios_economicosServiceImp implements ICriterios_economicosService{
	@Autowired
	private ICriterios_economicosDao Criterios_economicosDao;
	
	@Override
	@Transactional
	public List<Criterios_economicos> ListCriterios_economicos() {
		// TODO Auto-generated method stub
		return (List<Criterios_economicos>)Criterios_economicosDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Criterios_economicos> ListCriterios_economicosbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Criterios_economicos findOne(Long id) {
		// TODO Auto-generated method stub
		return Criterios_economicosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Criterios_economicos criterios_economicos) {
		// TODO Auto-generated method stub
		Criterios_economicosDao.save(criterios_economicos);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Criterios_economicosDao.delete(findOne(id));
		
	}
	

}
