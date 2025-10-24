package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Calibracion;


public interface ICalibracionService {

	public List<Calibracion> listCalibracion();
	public List<Calibracion> listCalibracionbyEquipo(Long id);
	public Calibracion findOne(Long id);
	public void save(Calibracion calibracion);
	public void delete(Long id);
	
	public List<Calibracion> findbyDate(int mes, int ano);
	public List<Calibracion> findbyDateAll(int mes, int ano);
	public int countCal(int mes, int ano);
	
	public List<Calibracion> findcalbyequipocheck(Long id);
	public List<Calibracion> findActbyequipoProgramada(Long idEquipo,int mesProgramada, int añoProgramada);
}
