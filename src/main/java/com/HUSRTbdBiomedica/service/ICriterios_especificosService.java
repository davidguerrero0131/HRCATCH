package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Criterios_especificos;


public interface ICriterios_especificosService {
	public List<Criterios_especificos>ListCriterios_especificos();
	public Optional<Criterios_especificos>ListCriterios_especificosbyId(Long id);
	public Criterios_especificos findOne(Long id);
	public void save(Criterios_especificos criterios_especificos);
	public void delete(Long id);

}
