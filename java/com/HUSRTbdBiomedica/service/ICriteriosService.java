package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Criterios;
import com.HUSRTbdBiomedica.app.entity.Equipo;

public interface ICriteriosService {
	public List<Criterios>ListCriterios();
	
	public List<Equipo>ListMalos();
	public List<Equipo>ListRegulares();
	public List<Equipo>ListBuenos();
	
	public List<Criterios>Listcrimalos();
	public List<Criterios>Listcriregulares();
	public List<Criterios>Listcribuenos();
	
	public Optional<Criterios>ListCriteriosbyId(Long id);
	public Criterios findOne(Long id);
	public void save(Criterios criterios);
	public void delete(Long id);
	
	public List<Criterios>findbyEquipo(Long id);
	
	public String findLastEvaluation(Long id);
	public List<String> findcbyequipoanddate(Long id, Date fecha1, Date fecha2);

}
