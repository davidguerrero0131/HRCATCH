package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Calibracion;


@Repository
public interface ICalibracionDao extends CrudRepository<Calibracion,Long>{
	
	
	@Query("SELECT COUNT(c) from Calibracion c")
    public int countAll();
	
	@Query("SELECT c FROM Calibracion c "+
			"INNER JOIN Equipo e ON c.equipo.id_Equipo = e.id_Equipo "+
			"WHERE e.id_Equipo=?1ANd c.realizado = true")
	public List<Calibracion> findcalbyEquipo(Long id);
	
	@Query("SELECT c FROM Calibracion c "+
			"WHERE c.Mes_programado=?1 AND c.Ano_programado=?2ANd c.realizado = true")
	public List<Calibracion> findbydate(int mes, int ano);
	
	@Query("SELECT c FROM Calibracion c "+
			"WHERE c.Mes_programado=?1 AND c.Ano_programado=?2")
	public List<Calibracion> findbydateAll(int mes, int ano);
	
	@Query("SELECT COUNT(c) FROM Calibracion c "+
			"WHERE c.Mes_programado=?1 AND c.Ano_programado=?2 AND c.Numero_certificado IS NOT NULL")
	public int countCalval(int mes, int ano);
	
	@Query("SELECT c FROM Calibracion c "+
			"WHERE c.equipo.id_Equipo=?1 AND c.Numero_certificado IS NOT NULL")
	public List<Calibracion> calcomplete(Long id);
	
	@Query("Select c from Calibracion c where c.equipo.id_Equipo = ?1and c.Mes_programado = ?2 and c.Ano_programado = ?3ANd c.realizado = true order by c.equipo.nombre_Equipo asc")
	public List<Calibracion> getActividadPRogramadaEquipo(Long iDEquipo, int mesProgramada, int añoProgramada);
	
	
	
}
