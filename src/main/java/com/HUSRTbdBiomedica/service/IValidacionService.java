package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Validacion;

public interface IValidacionService {

	public List<Validacion> listValidacion();
	public List<Validacion> listValidacionbyEquipo(Long id);
	public Validacion findOne(Long id);
	public void save(Validacion validacion);
	public void delete(Long id);
	public List<Validacion> findvalbyDate(int mes, int ano);
	public int countValidacion(int mes, int ano);
	public List<Validacion> findvalidacionbyequipocheck(Long id);
	
}
