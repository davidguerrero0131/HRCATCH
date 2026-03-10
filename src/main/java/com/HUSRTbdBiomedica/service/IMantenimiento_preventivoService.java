package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;


public interface IMantenimiento_preventivoService {
	public List<Mantenimiento_preventivo> ListMantenimientos();
	public Optional<Mantenimiento_preventivo> Lismttobyid(Long id);
	public Mantenimiento_preventivo findOne(Long id);
	public void save(Mantenimiento_preventivo mantenimiento_prevenvitvo);
	public void delete(Long id);
	
	public List<Mantenimiento_preventivo> findBytecnico(Long id, int mes, int ano);
	public List<Mantenimiento_preventivo> findbyfecha(int mes, int ano);
	
	public Mantenimiento_preventivo findbyreport(Long id);
	public List<Mantenimiento_preventivo> findbyEquipo(Long id);

}
