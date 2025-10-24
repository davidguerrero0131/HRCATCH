package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;


@Repository
public interface IProtocolo_preventivoDao extends CrudRepository<Protocolo_preventivo,Long> {
	
	@Query("SELECT COUNT(pp) from Protocolo_preventivo pp")
    public int countAll();
	
	@Query("SELECT pp FROM Protocolo_preventivo pp WHERE pp.tipo_equipo.id_Tipo_equipo=?1 AND pp.Cumplimiento=0 "
			+ "AND pp.mantenimiento_preventivo IS NULL")
	public List<Protocolo_preventivo> encontrarprotocolosgeneral(Long id);
	
	
	@Query("SELECT pp FROM Protocolo_preventivo pp WHERE pp.tipo_equipo.id_Tipo_equipo=?1 AND pp.Cumplimiento=1 "
			+ "AND pp.mantenimiento_preventivo IS NULL")
	public List<Protocolo_preventivo> encontrarprotocolosexcepciones(Long id);
	
	@Query("SELECT pp FROM Protocolo_preventivo pp "+
			"INNER JOIN Mantenimiento_preventivo mp ON pp.mantenimiento_preventivo.id_Mantenimiento_preventivo=mp.id_Mantenimiento_preventivo "
			+"WHERE mp.id_Mantenimiento_preventivo=?1")
	public List<Protocolo_preventivo> mostrarprotocoloasignadopormtto(Long id);
	
	
	
	
	
	
	
}
