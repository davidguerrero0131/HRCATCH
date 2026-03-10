package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.HUSRTbdBiomedica.app.entity.Calificacion;


public interface ICalificacionDao extends CrudRepository<Calificacion,Long>{

	@Query("SELECT COUNT(cf) from Calificacion cf")
    public int countAll();
	
	@Query("SELECT cf FROM Calificacion cf "+
			"INNER JOIN Equipo e ON cf.equipo.id_Equipo = e.id_Equipo "+
			"WHERE e.EstadoBaja = false AND e.id_Equipo=?1")
	public List<Calificacion> findcalificacionbyEquipo(Long id);
	
	@Query("SELECT cf FROM Calificacion cf "+
			"WHERE cf.Mes_programado=?1 AND cf.Ano_programado=?2")
	public List<Calificacion> findbydate(int mes, int ano);
	
	@Query("SELECT COUNT(cf) FROM Calificacion cf "+
			"WHERE cf.Mes_programado=?1 AND cf.Ano_programado=?2 AND cf.Numero_certificado IS NOT NULL")
	public int countCalval(int mes, int ano);
	
	@Query("SELECT cf FROM Calificacion cf "+
			"WHERE cf.equipo.id_Equipo=?1 AND cf.Numero_certificado IS NOT NULL")
	public List<Calificacion> calcomplete(Long id);
}
