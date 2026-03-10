package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.Dao.ICriterios_especificosDao;
import com.HUSRTbdBiomedica.app.entity.Criterios_especificos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class Criterios_especificosServiceImp implements ICriterios_especificosService {

	@Autowired
	private ICriterios_especificosDao Criterios_especificosDao;
	
	@Override
	@Transactional
	public List<Criterios_especificos> ListCriterios_especificos() {
		// TODO Auto-generated method stub
		return (List<Criterios_especificos>)Criterios_especificosDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Criterios_especificos> ListCriterios_especificosbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Criterios_especificos findOne(Long id) {
		// TODO Auto-generated method stub
		return Criterios_especificosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Criterios_especificos criterios_especificos) {
		// TODO Auto-generated method stub
		Criterios_especificosDao.save(criterios_especificos);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Criterios_especificosDao.delete(findOne(id));
	}
	

}
