package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ITipo_equipoDao extends CrudRepository<Tipo_equipo, Long> {

	@Query("SELECT COUNT(t) from Tipo_equipo t")
	public int countAll();
	
	@Query("SELECT t from Tipo_equipo t where t.Tipo = 2")
	public List<Tipo_equipo> getAllTiposSys();

	@Query("SELECT t FROM Tipo_equipo t " + "WHERE t.nombre_Tipo_equipo = ?1")
	public Tipo_equipo findTipo_equipobyname(String name);

	@Query("SELECT e FROM Equipo e " + "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
			+ "WHERE e.EstadoBaja = false AND t.id_Tipo_equipo=?1")
	public List<Equipo> findEquiposbyTipoEquipo(Long id);

	
	
	//@Query("SELECT t FROM Tipo_equipo t " + "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
	//		+ "GROUP BY t.id_Tipo_equipo ORDER BY t.id_Tipo_equipo ASC")
	
	@Query("SELECT t FROM Tipo_equipo t " + "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
			
		+ "WHERE e.EstadoBaja = false "  + "GROUP BY t.id_Tipo_equipo" )
	public List<Tipo_equipo> listTiposconEquipos();

	@Query("SELECT t FROM Tipo_equipo t " + "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
			+ "WHERE e.Periodicidad=?1 AND e.EstadoBaja = false " + "GROUP BY t.id_Tipo_equipo")
	public List<Tipo_equipo> listTipo_equiposByPeriodicidad(int id);

	@Query("SELECT COUNT(e) from Equipo e "
			+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
			+ "WHERE e.EstadoBaja = false AND e.Periodicidad=?1 AND t.id_Tipo_equipo=?2")
	public int countEspecificPbyTipoEquipo(int period, Long id);

	@Query("SELECT t FROM Tipo_equipo t " + "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
			+ "INNER JOIN Hoja_vida h ON h.equipo.id_Equipo = e.id_Equipo WHERE h.Requierecalibracion=1 "
			+ "GROUP BY t.id_Tipo_equipo")
	public List<Tipo_equipo> listTipoequipoCalVal();

	@Query("SELECT t FROM Tipo_equipo t " + "INNER JOIN Equipo e ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
			+ "INNER JOIN Hoja_vida h ON h.equipo.id_Equipo = e.id_Equipo WHERE h.Requierecalibracion=0 "
			+ "GROUP BY t.id_Tipo_equipo")
	public List<Tipo_equipo> listTipoequiponoCalVal();

	
	@Query("select te from Tipo_equipo te where te.id_Tipo_equipo in (select e.tipo_equipo.id_Tipo_equipo from Equipo e where e.id_Equipo in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false)")
	public List<Tipo_equipo> getTipoEquipoConComodatos();

	
	// SYS

	@Query("SELECT t FROM Tipo_equipo t WHERE t.Tipo = 1 order by t.nombre_Tipo_equipo asc")
	public List<Tipo_equipo> listTiposBiomedica();

	@Query("SELECT t FROM Tipo_equipo t WHERE t.Tipo = 2 order by t.nombre_Tipo_equipo asc")
	public List<Tipo_equipo> listTiposSistemas();

	@Query("SELECT e FROM SystemEquipo e "
			+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
			+ "WHERE t.id_Tipo_equipo=?1 AND e.EstadoBaja = false")
	public List<SystemEquipo> findSystemEquiposbyTipoEquipo(Long id);

	@Query("SELECT t FROM Tipo_equipo t "
			+ "INNER JOIN SystemEquipo e ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
			+ "WHERE e.EstadoBaja = false GROUP BY t.id_Tipo_equipo ORDER BY t.id_Tipo_equipo ASC")
	public List<Tipo_equipo> listTiposconSystemEquipos();


}