package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.Dao.ICriteriosDao;
import com.HUSRTbdBiomedica.app.entity.Criterios;
import com.HUSRTbdBiomedica.app.entity.Equipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CriteriosServiceImp implements ICriteriosService {
	@Autowired
	private ICriteriosDao CriteriosDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Criterios> ListCriterios() {
		return (List<Criterios>)CriteriosDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Criterios> ListCriteriosbyId(Long id) {
		return null;
	}

	@Override
	@Transactional
	public Criterios findOne(Long id) {
		return CriteriosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Criterios criterios) {
		CriteriosDao.save(criterios);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		CriteriosDao.delete(findOne(id));
	}

	@Override
	public List<Equipo> ListMalos() {
		return CriteriosDao.listaequiposevmala();
	}

	@Override
	public List<Equipo> ListRegulares() {
		return CriteriosDao.listaequiposevregular();
	}

	@Override
	public List<Equipo> ListBuenos() {
		return CriteriosDao.listaequiposevbuena();
	}

	@Override
	public List<Criterios> Listcrimalos() {
		return CriteriosDao.listacrievmala();
	}

	@Override
	public List<Criterios> Listcriregulares() {
		return CriteriosDao.listacrievregular();
	}

	@Override
	public List<Criterios> Listcribuenos() {
		return CriteriosDao.listacrievbueno();
	}

	@Override
	public List<Criterios> findbyEquipo(Long id) {
		return CriteriosDao.findbyequipo(id);
	}

	@Override
	public String findLastEvaluation(Long id) {
		// TODO Auto-generated method stub
		return CriteriosDao.findLastev(id);
	}

	@Override
	public List<String> findcbyequipoanddate(Long id, Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return CriteriosDao.findCriteriosbyequipof(id, fecha1, fecha2);
	}
	

}
