package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.HUSRTbdBiomedica.app.entity.SystemMantenimiento;

public interface ISystemMantenimientoDao extends CrudRepository<SystemMantenimiento, Long>{

	@Query("SELECT COUNT(sm) from SystemMantenimiento sm")
    public int countAll();
	
	@Query("SELECT MAX(sm.id_Sysmtto) FROM SystemMantenimiento sm")
    public Long findLastIdSysMtto();
	
	@Query("SELECT sm FROM SystemMantenimiento sm "+
			"INNER JOIN SystemEquipo e ON e.id_Sysequipo = sm.equipo.id_Sysequipo "+
			"WHERE e.id_Sysequipo=?1 ORDER BY sm.Fecha")
	public List<SystemMantenimiento> listMttobyequipo(Long id);


	@Query("SELECT sm FROM SystemMantenimiento sm "+
			"INNER JOIN SystemEquipo e ON e.id_Sysequipo = sm.equipo.id_Sysequipo "+
			"WHERE e.id_Sysequipo=?1 AND (sm.Tipo_mantenimiento = 2 OR sm.Tipo_mantenimiento = 3) ORDER BY sm.Fecha")
	public List<SystemMantenimiento> listCorrectbyequipo(Long id);
	
	@Query("SELECT sm.Fecha,sm.Observacionesh,sm.Observacioness FROM SystemMantenimiento sm "+
			"INNER JOIN SystemEquipo e ON e.id_Sysequipo = sm.equipo.id_Sysequipo "+
			"WHERE e.id_Sysequipo=?1 AND (sm.Tipo_mantenimiento = 2 OR sm.Tipo_mantenimiento = 3) ORDER BY sm.Fecha")
	public List<String> listCorrstrbyequipo(Long id);
	
	@Query("SELECT sm FROM SystemMantenimiento sm "+
			"INNER JOIN SystemEquipo e ON e.id_Sysequipo = sm.equipo.id_Sysequipo "+
			"WHERE sm.Fecha>=?1 AND sm.Fecha<?2")
	public List<SystemMantenimiento> reportesservicio(Date fecha1, Date fecha2);

	@Query("SELECT sm FROM SystemMantenimiento sm "+
			"INNER JOIN SystemEquipo e ON e.id_Sysequipo = sm.equipo.id_Sysequipo "+
			"WHERE sm.Fecha>=?1 AND sm.Fecha<?2")
	public List<String> reportesstrbydate(Date fecha1, Date fecha2);
	
	
	//indicadores
	
	@Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Hora_inicio))) FROM SystemMantenimiento r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Hora_inicio!='00:00:00' AND r.Hora_llamado!='00:00:00'")
    public Time horainicio(Date fecha1, Date fecha2);
    
    @Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Hora_llamado))) FROM SystemMantenimiento r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Hora_llamado!='00:00:00' AND r.Hora_inicio!='00:00:00'")
    public Time horallamado(Date fecha1, Date fecha2);
    
    @Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Hora_terminacion))) FROM SystemMantenimiento r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Hora_terminacion!='00:00:00'")
    public Time horaterminacion(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countnumcorrectivos(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_mantenimiento=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countnumpreventivos(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_mantenimiento=3 AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countnumpyv(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_mantenimiento=0 AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countnumotros(Date fecha1, Date fecha2);
    
    
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countdesgastecorrectivo(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countopindebidacorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countcausaexcorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countaccesorioscorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=5 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countdesconocidocorrectivo(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=6 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countsinfallascorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Tipo_falla=7 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countotroscorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=1 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desgastecorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=2 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> opindebidacorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=3 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> causaexcorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=4 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> accesorioscorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=5 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desconocidocorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=6 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> sinfallascorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla=7 AND (r.Tipo_mantenimiento=2 OR r.Tipo_mantenimiento = 3) AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> aotroscorr(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countdesgaste(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countopindebida(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countcausaex(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countaccesorios(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=5 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countdesconocido(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=6 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countsinfallas(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM SystemMantenimiento r "+
    		"WHERE r.Tipo_falla=7 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2")
    public int countotros(Date fecha1, Date fecha2);
    
    
  //Indicadores individuales mtto

    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_mantenimiento=2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> correctivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_mantenimiento = 1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> preventivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_mantenimiento = 3 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> pyv(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo,r.Rutaentrega FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_mantenimiento = 0 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> otros(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desgaste(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> opindebida(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 3 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> causaex(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 4 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> accesorios(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 5 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desconocido(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 6 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> sinfallas(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,"
    		+ "r.id_Sysmtto,e.Nombre_equipo,e.Marca,e.Modelo,e.Serie,e.Placa_inventario,r.Tipo_mantenimiento,r.Observacionesh,r.Autor_recibido,r.Rutahardware,e.id_Sysequipo FROM SystemMantenimiento r "+
    		"INNER JOIN SystemEquipo e ON r.equipo.id_Sysequipo = e.id_Sysequipo WHERE r.Tipo_falla = 7 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> aotros(Date fecha1, Date fecha2);
    
    
    @Query("SELECT sm FROM SystemMantenimiento sm WHERE sm.Entrega = '1' AND sm.Fecha>=?1 AND sm.Fecha<=?2")
    public List<SystemMantenimiento> sysreportealmacen(Date fecha1, Date fecha2);
    
    @Query("SELECT sm FROM SystemMantenimiento sm WHERE sm.Entrega = '1'")
    public List<SystemMantenimiento> sysentregas();
    
    @Query("SELECT u.id_Usuario,sm.Autor_realizado,AVG(TIME_TO_SEC(sm.Hora_terminacion)-TIME_TO_SEC(sm.Hora_inicio)),sm.Tipo_mantenimiento,COUNT(sm) FROM SystemMantenimiento sm "
    		+ "INNER JOIN Usuario u ON sm.usuario.id_Usuario = u.id_Usuario WHERE sm.Fecha>=?1 AND sm.Fecha<?2 "
    		+ "GROUP BY sm.Autor_realizado, sm.Tipo_mantenimiento ORDER BY sm.Autor_realizado")
    public List<String> listbymtto(Date fecha1, Date fecha2);
    
    @Query("SELECT sm.Autor_realizado FROM SystemMantenimiento sm "
    		+ "WHERE sm.Fecha>=?1 AND sm.Fecha<?2 GROUP BY sm.Autor_realizado")
    public List<String> listsystec(Date fecha1,Date fecha2);
    
 
	@Query("select sm from SystemMantenimiento sm where sm.equipo.id_Sysequipo = ?1 and sm.Fecha >= '2025-01-01' and sm.Fecha <= '2025-12-31' and sm.Tipo_mantenimiento = 1 group by id_sysequipo_fk")
	public SystemMantenimiento fintMantenimientoEquipo(Long idEquipo);
	
}
