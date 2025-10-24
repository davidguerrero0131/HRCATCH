package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Reporte_baja;

@Repository
public interface IReporte_bajaDao extends CrudRepository<Reporte_baja,Long> {
	
	@Query("SELECT COUNT(rb) from Reporte_baja rb")
    public int countAll();
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"INNER JOIN Baja b ON rb.baja.id_Baja=b.id_Baja "+
    		"WHERE b.id_Baja=?1")
    public int countReportesbyBaja(Long id);
    
    @Query("SELECT rb FROM Reporte_baja rb " + 
    		"INNER JOIN Baja b ON rb.baja.id_Baja=b.id_Baja " + 
    		"WHERE b.id_Baja=?1")    
    public List<Reporte_baja> findReportesbyBaja(Long id);
    
    @Query("SELECT MAX(rb.id_Reporte_baja) FROM Reporte_baja rb")
    public Long findLastIdReporte();
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumcorrectivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=3 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumpreventivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=4 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumpredictivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=1 AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countnumotrosbaja(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=1 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesgastecorrectivobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=2 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countopindebidacorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=3 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countcausaexcorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=4 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countaccesorioscorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=5 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesconocidocorrectivobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=6 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countsinfallascorrectivobaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_mantenimiento_baja=2 AND rb.Tipo_falla_baja=7 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countotroscorrectivobaja(Date fecha1, Date fecha2);
    
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=1 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesgastebaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=2 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countopindebidabaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=3 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countcausaexbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=4 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countaccesoriosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=5 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countdesconocidobaja(Date fecha1,Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=6 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countsinfallasbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT COUNT(rb) FROM Reporte_baja rb "+
    		"WHERE rb.Tipo_falla_baja=7 "+
    		"AND rb.Fecha_baja>=?1 AND rb.Fecha_baja<?2 AND rb.Mtto_propio_baja=1")
    public int countotrosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=1 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> desgastecorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=2 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> opindebidacorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=3 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> causaexcorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=4 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> accesorioscorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=5 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> desconocidocorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=6 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> sinfallascorrbj(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=7 AND r.Tipo_mantenimiento_baja=2 AND r.Mtto_propio_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> aotroscorrbj(Date fecha1, Date fecha2);
    
    
    //
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_falla_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_mantenimiento_baja=2 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> correctivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_falla_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_mantenimiento_baja=3 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> preventivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_falla_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_mantenimiento_baja=4 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> predictivosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_falla_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_mantenimiento_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> otrosbaja(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=1 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> desgaste(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=2 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> opindebida(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=3 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> causaex(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=4 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> accesorios(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=5 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> desconocido(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=6 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> sinfallas(Date fecha1, Date fecha2);
    
    @Query("SELECT r.Fecha_baja,r.Hora_inicio_baja,r.Hora_terminacion_baja,r.Hora_llamado_baja,r.Autor_realizado_baja,r.Total_horas_baja,"
    		+ "r.id_Reporte_baja,r.Nombre_equipo_baja,r.Marca_baja,r.Modelo_baja,r.Serie_baja,r.Placa_inventario_baja,r.Servicio_baja,r.Tipo_mantenimiento_baja,r.Trabajo_realizado_baja,r.Autor_recibido_baja,r.Rutapdf_baja FROM Reporte_baja r "+
    		"WHERE r.Tipo_falla_baja=7 AND r.Fecha_baja>=?1 AND r.Fecha_baja<?2")
    public List<String> aotros(Date fecha1, Date fecha2);

}
