package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;

@Repository
public interface IMantenimiento_preventivoDao extends CrudRepository<Mantenimiento_preventivo,Long>{
	
	@Query("SELECT COUNT(mp) from Mantenimiento_preventivo mp")
    public int countAll();
	
	@Query("SELECT mp FROM Mantenimiento_preventivo mp JOIN mp.equipo e JOIN e.servicio s "
			+ "WHERE mp.usuario.id_Usuario = ?1 AND mp.Mes = ?2 AND mp.Ano = ?3 "
			+ "ORDER BY s.Nombre_servicio ASC")
	public List<Mantenimiento_preventivo> findbytecnicomonthandyear(Long id,int mes, int ano);
	
	@Query("SELECT mp FROM Mantenimiento_preventivo mp JOIN mp.equipo e JOIN e.servicio s "
			+ "WHERE mp.Mes = ?1 AND mp.Ano = ?2 AND e.EstadoBaja = false "
			+ "ORDER BY s.Nombre_servicio ASC")
	public List<Mantenimiento_preventivo> findMesAnoinitial(int mes,int ano);
	
	
	@Query("SELECT mp FROM Mantenimiento_preventivo mp "+
			"INNER JOIN Reporte r ON mp.reporte.id_Reporte = r.id_Reporte "+
			"WHERE r.id_Reporte=?1")
	public Mantenimiento_preventivo findmttobyReporte(Long id);
	
	@Query("SELECT mp FROM Mantenimiento_preventivo mp "+
			"INNER JOIN Equipo e ON mp.equipo.id_Equipo = e.id_Equipo "+
			"WHERE e.id_Equipo=?1 and e.EstadoBaja = false")
	public List<Mantenimiento_preventivo> findmttobyequipo(Long id);
	
	

}
