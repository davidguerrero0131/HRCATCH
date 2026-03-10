package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Reporte;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReporteDao extends CrudRepository<Reporte, Long> {

    @Query("SELECT COUNT(r) from Reporte r")
    public int countAll();
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo "+
    		"WHERE e.id_Equipo=?1")
    public int countReportesbyEquipo(Long id);
    
    @Query("select r from Reporte r where r.Numero_reporte = ?1")
    public Reporte getByNumerReport(String number);
    
    @Query("SELECT r FROM Reporte r " + 
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo " + 
    		"WHERE e.id_Equipo=?1 ORDER BY r.Fecha DESC")    
    public List<Reporte> findReportesbyEquipo(Long id);
    
    @Query("SELECT MAX(r.id_Reporte) FROM Reporte r")
    public Long findLastIdReporte();
    //Indicadores individuales mtto
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=3 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> reportesservicio(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_falla,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_mantenimiento=2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> correctivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r FROM Reporte r where r.Tipo_mantenimiento=2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<Reporte> mantenimientosCorrectivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_falla,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_mantenimiento=3 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> preventivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_falla,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_mantenimiento=4 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> predictivos(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_falla,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_mantenimiento=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> otros(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desgaste(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=2 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> opindebida(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r  "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=3 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> causaex(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=4 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> accesorios(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=5 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desconocido(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=6 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> sinfallas(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=7 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> aotros(Date fecha1, Date fecha2);
    
    //repuestos
    @Query("SELECT r FROM Reporte r "+
    		"WHERE r.Fecha>=?1 AND r.Fecha<=?2 AND r.Repuesto_cambiado!=''")
    public List<Reporte> repuestos(Date fecha1, Date fecha2);
    //reportesporrangos
    @Query("SELECT r FROM Reporte r "+
    		"WHERE r.Fecha>=?1 AND r.Fecha<=?2")
    public List<Reporte> rangoreportes(Date fecha1, Date fecha2);
    //Indicadores
    
    @Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Hora_inicio))) FROM Reporte r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Hora_inicio!='00:00:00' AND r.Hora_llamado!='00:00:00' AND r.Mtto_propio=1")
    public Time horainicio(Date fecha1, Date fecha2);
    
    @Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Hora_llamado))) FROM Reporte r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Hora_llamado!='00:00:00' AND r.Hora_inicio!='00:00:00' AND r.Mtto_propio=1")
    public Time horallamado(Date fecha1, Date fecha2);
    
    @Query("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(r.Total_horas))) FROM Reporte r "
    		+ "WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.Total_horas!='00:00:00' AND r.Mtto_propio=1")
    public Time listatiempototal(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Fecha>=?1 AND r.Fecha<=?2 AND r.Mtto_propio=1")
    public int countnumcorrectivos(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=3 AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countnumpreventivos(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=4 AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countnumpredictivos(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=1 AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countnumotros(Date fecha1, Date fecha2);
    
    
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countdesgastecorrectivo(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countopindebidacorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countcausaexcorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countaccesorioscorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=5 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countdesconocidocorrectivo(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=6 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countsinfallascorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 AND r.Tipo_falla=7 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countotroscorrectivo(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=1 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desgastecorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=2 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> opindebidacorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=3 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> causaexcorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=4 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> accesorioscorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=5 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> desconocidocorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=6 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> sinfallascorr(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha,r.Hora_inicio,r.Hora_terminacion,r.Hora_llamado,r.Autor_realizado,r.Total_horas,"
    		+ "r.id_Reporte,r.Nombre_equipo,r.Marca,r.Modelo,r.Serie,r.Placa_inventario,r.Servicio,r.Tipo_mantenimiento,r.Trabajo_realizado,r.Autor_recibido,r.Rutapdf,e.id_Equipo FROM Reporte r "+
    		"INNER JOIN Equipo e ON e.id_Equipo = r.equipo.id_Equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND r.Tipo_falla=7 AND r.Tipo_mantenimiento=2 AND r.Mtto_propio=1 AND r.Fecha>=?1 AND r.Fecha<?2")
    public List<String> aotroscorr(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countdesgaste(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countopindebida(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countcausaex(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countaccesorios(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=5 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countdesconocido(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=6 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countsinfallas(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=7 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.Mtto_propio=1")
    public int countotros(Date fecha1, Date fecha2);
    
    //crearmttoprev
    @Query("SELECT r FROM Reporte r "+
			"INNER JOIN Mantenimiento_preventivo mp ON mp.reporte.id_Reporte = r.id_Reporte "+
			"WHERE mp.id_Mantenimiento_preventivo=?1")
	public Reporte findReportebymtto(Long id);
    
    //visualizacionotros
  
    
    @Query("SELECT r FROM Reporte r " +
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo = e.id_Equipo "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND e.id_Equipo =?1 AND r.Fecha>=?2 AND r.Fecha<?3")
    public List<Reporte> findotrosreportesbyfecha(Long id, Date fecha1, Date fecha2);
    
    //tiempofueraservicio
    @Query("SELECT MAX(r.Tiempo_fuera_servicio) FROM Reporte r "+
    		"WHERE r.equipo.id_Equipo=?1 AND r.Fecha>=?2 AND r.Fecha<?3")
    public String findmaxtimeoutservice(Long id, Date fecha1, Date fecha2);
    
    //indicadorporequipo
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int mttopreventivobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int mttocorrectivobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int mttopredictivobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_mantenimiento=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int mttootrobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=1 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int falladesgastebyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=2 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int fallaopindebidabyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=3 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int fallacausaexbyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=4 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int fallaaccesoriobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=5 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int falladesconocidobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=6 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int fallasinbyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Tipo_falla=7 "+
    		"AND r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int fallaotrobyequipo(Date fecha1, Date fecha2, Long id);
    
    @Query("SELECT COUNT(r) FROM Reporte r "+
    		"WHERE r.Fecha>=?1 AND r.Fecha<?2 AND r.equipo.id_Equipo=?3")
    public int countReportesbyEquipodate(Date fecha1, Date fecha2,Long id);
    
    @Query("SELECT r FROM Reporte r where r.Serie=?1 AND r.Placa_inventario=?2")
    public List<Reporte> findreportesBySerieAndPlaca(String serie, String placa);
    
    
    
    
}