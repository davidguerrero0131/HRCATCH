package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;

@Repository
public interface IMantenimientoMSVDao extends CrudRepository<MantenimientoMSV, Long> {

	
	@Query("Select msv from MantenimientoMSV msv")
	public List<MantenimientoMSV> getAllMantenimientoMSV();
	
	
	@Query("Select msv from MantenimientoMSV msv where msv.equipo.id_Equipo = ?1 and msv.mantenimiento.id_Mantenimiento_preventivo = ?2")
	public MantenimientoMSV getMantenimientoMSVEspec(Long idEquipo, long idMantenimiento);
	
}
