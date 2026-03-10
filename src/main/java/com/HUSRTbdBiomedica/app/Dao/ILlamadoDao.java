package com.HUSRTbdBiomedica.app.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Llamado;

@Repository
public interface ILlamadoDao extends CrudRepository<Llamado, Long>{

	@Query("SELECT COUNT(l) from Llamado l")
    public int countAll();
	
	@Query("SELECT COUNT(l) FROM Llamado l "
			+"WHERE l.Fecha>=?1 AND l.Fecha<=?2")
	public int countbyDate(Date fecha1, Date fecha2);
	
	
	@Query("SELECT l FROM Llamado l "
			+"WHERE l.Fecha>=?1 AND l.Fecha<=?2")
	public List<Llamado> listbyrange(Date fecha1, Date fecha2);
	
	@Query("SELECT COUNT(l),l.Atendido,AVG(TIMESTAMPDIFF(SECOND,l.Fecha,l.Fecha_r)+TIME_TO_SEC(l.Hora_respuesta)-TIME_TO_SEC(l.Hora_llamado)),l.Tipo "
			+ "FROM Llamado l WHERE l.Estado>0 AND l.Fecha>=?1 AND l.Fecha<=?2 GROUP BY l.Atendido,l.Tipo ORDER BY l.Tipo")
	public List<String> lisybyatend(Date fecha1, Date fecha2);
	
	@Query("SELECT l.Atendido "
			+ "FROM Llamado l WHERE l.Estado>0 AND l.Fecha>=?1 AND l.Fecha<=?2 GROUP BY l.Atendido")
	public List<String> listtec(Date fecha1, Date fecha2);
	
	@Query("SELECT COUNT(l),s.Nombre_servicio,l.Area,l.Tipo FROM Llamado l INNER JOIN Servicio s "
			+ "ON s.id_Servicio = l.servicio.id_Servicio WHERE l.Estado>0 AND l.Fecha>=?1 AND l.Fecha<=?2 "
			+ "GROUP BY s.id_Servicio,l.Tipo ORDER BY l.Tipo")
	public List<String> listservices(Date fecha1, Date fecha2);
	
	@Query("SELECT s.Nombre_servicio FROM Llamado l INNER JOIN Servicio s "
			+"ON s.id_Servicio = l.servicio.id_Servicio WHERE l.Estado>0 AND l.Fecha>=?1 AND l.Fecha<=?2 "
			+ "GROUP BY s.id_Servicio")
	public List<String> listonlyservice(Date fecha1,Date fecha2);
	
	@Query("SELECT l.Prioridad, COUNT(l),AVG(TIMESTAMPDIFF(SECOND,l.Fecha,l.Fecha_r)+TIME_TO_SEC(l.Hora_respuesta)-TIME_TO_SEC(l.Hora_llamado)),"
			+ "AVG(l.Atencion_prioridad),AVG(TIMESTAMPDIFF(SECOND,l.Fecha_r,l.Fecha_sn)+TIME_TO_SEC(l.Hora_solucion)-TIME_TO_SEC(l.Hora_respuesta)) "
			+ "FROM Llamado l WHERE l.Fecha>=?1 AND l.Fecha<=?2 AND l.Estado>1 "
			+ "GROUP BY l.Prioridad ORDER BY l.Prioridad")
	public List<String> listbyprioridad(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l),l.Atendido FROM Llamado l WHERE l.Por_telefono=1 AND l.Fecha>=?1 AND l.Fecha<=?2 GROUP BY l.Atendido")
	public List<String> listatencionphone(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l),l.Atendido FROM Llamado l WHERE l.Confirmacion_telefono=1 AND l.Fecha>=?1 AND l.Fecha<=?2 GROUP BY l.Atendido")
	public List<String> listconfirmatencionphone(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l) FROM Llamado l WHERE l.Fecha>=?1 AND l.Fecha<=?2")
	public int countCalls(Date fecha1,Date fecha2);
	
	@Query("SELECT AVG(TIMESTAMPDIFF(SECOND,l.Fecha_r,l.Fecha_sn)+TIME_TO_SEC(l.Hora_solucion)-TIME_TO_SEC(l.Hora_respuesta)) FROM Llamado l WHERE l.Estado>1 AND l.Fecha>=?1 AND l.Fecha<=?2")
	public float countSecondsTtotal(Date fecha1,Date fecha2);
	
	@Query("SELECT AVG(TIMESTAMPDIFF(SECOND,l.Fecha,l.Fecha_r)+TIME_TO_SEC(l.Hora_respuesta)-TIME_TO_SEC(l.Hora_llamado)) FROM Llamado l WHERE l.Estado>1 AND l.Fecha>=?1 AND l.Fecha<=?2")
	public float countSecondsTrta(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l) FROM Llamado l WHERE l.Estado>0 AND l.Fecha>=?1 AND l.Fecha<=?2")
	public int countCallAt(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l) FROM Llamado l WHERE l.Estado>1 AND l.Fecha>=?1 AND l.Fecha<=?2")
	public int countCallSn(Date fecha1,Date fecha2);
	
	@Query("SELECT COUNT(l),t.nombre_Tipo_equipo FROM Llamado l "
			+ "INNER JOIN Tipo_equipo t ON l.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
			+ "WHERE l.Fecha>=?1 AND l.Fecha<=?2 GROUP BY t.id_Tipo_equipo ORDER BY COUNT(l) DESC")
	public List<String> listtopequiposcall(Date fecha1,Date fecha2);
	
}