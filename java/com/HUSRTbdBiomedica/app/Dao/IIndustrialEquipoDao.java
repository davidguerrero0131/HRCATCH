package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.IndustrialEquipo;

@Repository
public interface IIndustrialEquipoDao extends CrudRepository<IndustrialEquipo, Long>{

	@Query("SELECT COUNT(ie) FROM IndustrialEquipo ie")
    public int countAll();
	
	@Query("SELECT ie FROM IndustrialEquipo ie "+
    		"INNER JOIN Tipo_equipo t ON ie.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+"WHERE t.id_Tipo_equipo=?1")
    public List<IndustrialEquipo> findIndEquiposbyTipoEquipo(Long id);
	
	@Query("SELECT ie FROM IndustrialEquipo ie "
			+ "INNER JOIN Servicio s ON ie.servicio.id_Servicio= s.id_Servicio "+
			"WHERE s.id_Servicio=?1")
	public List<IndustrialEquipo> findIndEquiposbyServicio(Long id);
	
}
