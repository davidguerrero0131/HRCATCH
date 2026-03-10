package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Repuestos;

@Repository
public interface IRepuestosDao extends CrudRepository<Repuestos, Long>{

	@Query("SELECT COUNT(r) from Repuestos r")
    public int countAll();
	
	@Query("SELECT COUNT(r) FROM Repuestos r "+
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo "+
    		"WHERE e.id_Equipo=?1")
    public int countRepuestosbyEquipo(Long id);
	
	@Query("SELECT r FROM Repuestos r "+
    		"INNER JOIN Tipo_equipo t ON r.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "+
    		"WHERE t.id_Tipo_equipo=?1")
    public List<Repuestos> RepuestosbyTipoEquipo(Long id);
	
	@Query("SELECT r FROM Repuestos r "+
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo "+
    		"WHERE e.id_Equipo=?1")
    public List<Repuestos> RepuestosbyEquipo(Long id);
	
}
