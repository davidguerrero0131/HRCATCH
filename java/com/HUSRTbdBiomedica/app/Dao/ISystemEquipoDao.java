package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;

@Repository
public interface ISystemEquipoDao extends CrudRepository<SystemEquipo, Long> {

	@Query("SELECT COUNT(es) from SystemEquipo es WHERE es.EstadoBaja = false")
	public int countAll();
	
	@Query("SELECT COUNT(es) from SystemEquipo es WHERE es.tipo_equipo.Tipo = 2 and es.EstadoBaja = false")
	public int countEquiposSys();
	
	@Query("SELECT COUNT(es) from SystemEquipo es WHERE es.tipo_equipo.id_Tipo_equipo = :idTipoEquipo and es.EstadoBaja = false")
	public int cantidadEquiposTipo(@Param("idTipoEquipo") Long idTipoEquipo);

	@Query("SELECT es from SystemEquipo es WHERE es.EstadoBaja = false")
	public List<SystemEquipo> getAllActivo();
	
	@Query("SELECT es from SystemEquipo es WHERE es.Activo = false AND es.EstadoBaja = false")
	public List<SystemEquipo> getAllInactivos();
	
	@Query("SELECT es FROM SystemEquipo es INNER JOIN Tipo_equipo t ON t.id_Tipo_equipo = es.tipo_equipo.id_Tipo_equipo "
			+ "WHERE es.EstadoBaja = false ORDER BY t.id_Tipo_equipo,es.id_Sysequipo")
	public List<SystemEquipo> orderbytipo();

	@Query("SELECT es FROM SystemEquipo es INNER JOIN Tipo_equipo t ON t.id_Tipo_equipo = es.tipo_equipo.id_Tipo_equipo "
			+ "WHERE es.Ano_ingreso<?1 AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyano(Date ano);

	@Query("SELECT es.Serie FROM SystemEquipo es WHERE es.EstadoBaja = false")
	public List<String> listasysseries();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Serie=?1 AND es.EstadoBaja = false")
	public List<SystemEquipo> findsysEquipobySerie(String serie);

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/01/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyenero();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/02/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyfebrero();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/03/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbymarzo();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/04/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyabril();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/05/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbymayo();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/06/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyjunio();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/07/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyjulio();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/08/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyagosto();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/09/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyseptiembre();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/10/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbyoctubre();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/11/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbynoviembre();

	@Query("SELECT es FROM SystemEquipo es WHERE es.Dias_mantenimiento LIKE '%/12/%' AND es.EstadoBaja = false")
	public List<SystemEquipo> listbydiciembre();

	@Query("SELECT es.id_Sysequipo,es.Nombre_equipo,es.Marca,es.Modelo,es.Serie FROM SystemEquipo es WHERE es.EstadoBaja = false ORDER BY es.id_Sysequipo")
	public List<String> listasysnmmsp();

	// UPDATE sysequipo t SET t.estado_baja = true WHERE t.id_sysequipo = 507

	@Transactional
	@Modifying
	@Query("UPDATE SystemEquipo se SET se.EstadoBaja = true WHERE se.id_Sysequipo = ?1")
	public void bajaEquipo(long idEquipo);
	
	@Transactional
	@Modifying
	@Query("UPDATE SystemEquipo se SET se.DireccionamientoVlan = ?2, se.NumeroPuertos = ?3, se.Administrable = ?4 WHERE se.id_Sysequipo = ?1")
	public void UpdateSwitchData(Long idEquipo, String Vlans, int numeroPuertos, Boolean administrable);

	
}
