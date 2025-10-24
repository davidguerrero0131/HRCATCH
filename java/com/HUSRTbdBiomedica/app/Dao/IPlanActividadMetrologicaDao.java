package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.PlanActividadMetrologica;

@Repository
public interface IPlanActividadMetrologicaDao extends JpaRepository<PlanActividadMetrologica, Long> {

	@Query("Select p from PlanActividadMetrologica p where p.equipo.id_Equipo = ?1order by p.equipo.nombre_Equipo asc")
	public List<PlanActividadMetrologica> getActividadMetrologicaEquipo(Long IdEquipo);
	
	@Query("Select p from PlanActividadMetrologica p where p.equipo.tipo_equipo.id_Tipo_equipo = ?1order by p.equipo.nombre_Equipo asc")
	public List<PlanActividadMetrologica> getActividadMetrologicaTipoEquipo(Long IdTipoEquipo);
	
	@Query("Select p from PlanActividadMetrologica p where p.equipo.servicio.id_Servicio = ?1order by p.equipo.nombre_Equipo asc")
	public List<PlanActividadMetrologica> getActividadMetrologicaServicio(Long IdServicio);
	
	@Query("Select p from PlanActividadMetrologica p where p.mes = ?1and p.ano = ?2order by p.equipo.nombre_Equipo asc")
	public List<PlanActividadMetrologica> getActividadMetrologicaByMes(int mes, int año);
	
	@Query("Select p from PlanActividadMetrologica p where p.mes = ?1and p.ano = ?2order by p.equipo.nombre_Equipo asc")
	public List<PlanActividadMetrologica> getActividadMetrologicaByMesAño(int mes, int año);
}
