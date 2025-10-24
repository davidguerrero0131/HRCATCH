package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;

@Repository
public interface ISystemRepuestosDao extends CrudRepository<SystemRepuestos, Long>{

	
	@Query("SELECT COUNT(sr) from SystemRepuestos sr")
    public int countAll();
	
	@Query("SELECT sr FROM SystemRepuestos sr INNER JOIN SystemEquipo se "
			+ "ON sr.equipo.id_Sysequipo = se.id_Sysequipo WHERE se.id_Sysequipo=?1")
	public List<SystemRepuestos> listrepuestosbyequipo(Long id);
	
	@Query("SELECT sr FROM SystemRepuestos sr WHERE sr.equipo IS NULL")
	public List<SystemRepuestos> listwithoutEquipo();
	
	
	
}
