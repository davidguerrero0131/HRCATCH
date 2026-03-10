package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;



import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;


public interface IServicioService {
	public List<Servicio>ListServicio();
	
	public List<Servicio>ListServicioSys();
	public Optional<Servicio>ListServiciobyId(Long id);
	public Servicio findOne(Long id);
	public void save(Servicio servicio);
	public void delete(Long id);
	
	public List<Integer> countequiposbyServicio();
	
	
	public int contarServicios();
	public List<Equipo> findEquiposbyServicio(Long id);
	public int countEspecificbyServicio(Long id);
	
	public List<Equipo> findEquiposbyServiciocuatrimestrales(Long id);
	public List<Equipo> findEquiposbyServiciotrimestrales(Long id);
	public List<Equipo> findEquiposbyServiciosemestrales(Long id);
	public List<Equipo> findEquiposbyServicioanuales(Long id);
	public List<Servicio> findServicebyp(int id);
	public int countEspecificbyServicionP(int period, Long id);
	
	public Servicio findbyName(String name);
	
	public List<Servicio> getServiciosConComodatos();
	
	//SYS
	public List<SystemEquipo> listSystemEquiposbyServicio(Long id);
	public List<Servicio> findServiciosconSystemEquipos();
	
	
	
}
