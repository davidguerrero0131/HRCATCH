package com.HUSRTbdBiomedica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Criterios_tecnicos;


public interface ICriterios_tecnicosService {
	public List<Criterios_tecnicos>ListCriterios_tecnicos();
	public Optional<Criterios_tecnicos>ListCriterios_tecnicosbyId(Long id);
	public Criterios_tecnicos findOne(Long id);
	public void save(Criterios_tecnicos criterios_tecnicos);
	public void delete(Long id);
	public int numerocorrectivosanuales(Long id, LocalDate fechaactual,LocalDate fechainicialano);
	
}
