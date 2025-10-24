package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.FechaMantenimientoEquipos;

@Repository
public interface IFechaMantenimientoEquipoDao extends CrudRepository<FechaMantenimientoEquipos, Long>{

	
	@Query("select fme from FechaMantenimientoEquipos fme")
	public List<FechaMantenimientoEquipos> getAllRegistros();
	
	
}
