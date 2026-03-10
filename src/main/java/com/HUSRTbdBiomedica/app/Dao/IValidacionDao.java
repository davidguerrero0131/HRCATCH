package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.HUSRTbdBiomedica.app.entity.Validacion;

public interface IValidacionDao extends CrudRepository<Validacion,Long>{

	@Query("SELECT COUNT(v) from Validacion v")
    public int countAll();
	
	@Query("SELECT v FROM Validacion v "+
			"INNER JOIN Equipo e ON v.equipo.id_Equipo = e.id_Equipo "+
			"WHERE e.id_Equipo=?1")
	public List<Validacion> findvalidacionbyEquipo(Long id);
	
	@Query("SELECT v FROM Validacion v "+
			"WHERE v.Mes_programado=?1 AND v.Ano_programado=?2")
	public List<Validacion> findbydate(int mes, int ano);
	
	@Query("SELECT COUNT(v) FROM Validacion v "+
			"WHERE v.Mes_programado=?1 AND v.Ano_programado=?2 AND v.Numero_certificado IS NOT NULL")
	public int countCalval(int mes, int ano);
	
	@Query("SELECT v FROM Validacion v "+
			"WHERE v.equipo.id_Equipo=?1 AND v.Numero_certificado IS NOT NULL")
	public List<Validacion> valcomplete(Long id);
}
