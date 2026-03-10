package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Calificacion;

public interface ICalificacionService {

	public List<Calificacion> listCalificacion();
	public List<Calificacion> listCalificacionbyEquipo(Long id);
	public Calificacion findOne(Long id);
	public void save(Calificacion calificacion);
	public void delete(Long id);
	
	public List<Calificacion> findbyDate(int mes, int ano);
	public int countCalificacion(int mes, int ano);
	
	public List<Calificacion> findcalificacionbyequipocheck(Long id);
}
