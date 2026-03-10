package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.SystemEquipo;


public interface ISystemEquipoService {

	public List<SystemEquipo>ListSystemEquipo();
	public int cantidadEquiposTipo(Long idTipoEquipo);
	public int cantidadEquiposSys();
	public Optional<SystemEquipo>ListSystemEquipobyId(Long id);
	public SystemEquipo findOne(Long id);
	public void save(SystemEquipo equipo);
	public void delete(Long id);
	public List<SystemEquipo> getAll();
	public int countEquiposys();
	public List<SystemEquipo> getAllActivo();
	public List<SystemEquipo> getAllInactivos();
	public List<String> listSySeries();
	public List<SystemEquipo> findbySerie(String serie);
	public List<SystemEquipo> findbyEnero();
	public List<SystemEquipo> findbyFebrero();
	public List<SystemEquipo> findbyMarzo();
	public List<SystemEquipo> findbyAbril();
	public List<SystemEquipo> findbyMayo();
	public List<SystemEquipo> findbyJunio();
	public List<SystemEquipo> findbyJulio();
	public List<SystemEquipo> findbyAgosto();
	public List<SystemEquipo> findbySeptiembre();
	public List<SystemEquipo> findbyOctubre();
	public List<SystemEquipo> findbyNoviembre();
	public List<SystemEquipo> findbyDiciembre();
	
	public List<SystemEquipo> listbyAno(Date ano);
	
	public List<String> listnmmsp();
	public List<SystemEquipo> orderbyTiponid();
	
	public void bajaEquipo(Long idEquipo);
	public void updateSwitchData(Long idEquipo, String Vlans, int numeroPuertos, Boolean administrable);
	
	
	
	
}
