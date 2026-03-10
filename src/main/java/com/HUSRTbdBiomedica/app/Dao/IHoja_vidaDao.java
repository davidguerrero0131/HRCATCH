package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida;

@Repository
public interface IHoja_vidaDao extends CrudRepository<Hoja_vida,Long> {
	
	@Query("SELECT COUNT(h) from Hoja_vida h")
    public int countAll();
	
	@Query("SELECT h FROM Hoja_vida h "
			+ "INNER JOIN Equipo e ON h.equipo.id_Equipo=e.id_Equipo "
			+ "WHERE e.id_Equipo=?1")
	public Hoja_vida findHVbyEquipo(Long id);

	@Query("SELECT h FROM Hoja_vida h "
			+ "WHERE h.Fecha_vencimientogarantia>=?1 AND h.Fecha_vencimientogarantia<?2")
	public List<Hoja_vida> findhvbyVcto(Date fecha1, Date fecha2);
	
			
	

}
