package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Criterios_economicos;

public interface ICriterios_economicosService{
	public List<Criterios_economicos>ListCriterios_economicos();
	public Optional<Criterios_economicos>ListCriterios_economicosbyId(Long id);
	public Criterios_economicos findOne(Long id);
	public void save(Criterios_economicos criterios_economicos);
	public void delete(Long id);

}
