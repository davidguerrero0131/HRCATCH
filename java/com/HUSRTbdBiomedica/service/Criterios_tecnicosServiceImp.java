package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.ICriterios_tecnicosDao;
import com.HUSRTbdBiomedica.app.Dao.IEquipoDao;

import com.HUSRTbdBiomedica.app.entity.Criterios_tecnicos;

@Service
public class Criterios_tecnicosServiceImp implements ICriterios_tecnicosService {

	@Autowired
	private ICriterios_tecnicosDao Criterios_tecnicosDao;
	
	@Autowired
	private IEquipoDao EquipoDao;
	
	@Override
	@Transactional
	public List<Criterios_tecnicos> ListCriterios_tecnicos() {
		// TODO Auto-generated method stub
		return (List<Criterios_tecnicos>)Criterios_tecnicosDao.findAll();
		
	}

	@Override
	@Transactional
	public Optional<Criterios_tecnicos> ListCriterios_tecnicosbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Criterios_tecnicos findOne(Long id) {
		// TODO Auto-generated method stub
		return Criterios_tecnicosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Criterios_tecnicos criterios_tecnicos) {
		// TODO Auto-generated method stub
		Criterios_tecnicosDao.save(criterios_tecnicos);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Criterios_tecnicosDao.delete(findOne(id));
	}

	@Override
	public int numerocorrectivosanuales(Long id, LocalDate fechaactual,LocalDate fechainicialano) {
		// TODO Auto-generated method stub
		Date fechaa = Date.valueOf(fechaactual);
		Date fechainicial = Date.valueOf(fechainicialano);
		return EquipoDao.numerocorrectivosano(id, fechaa, fechainicial);
	}

}
