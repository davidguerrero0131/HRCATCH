package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;

@Repository
public interface IServicioDao extends CrudRepository<Servicio,Long>{
	
	
	@Query("SELECT COUNT(s) from Servicio s")
    public int countAll();
	
	@Query("SELECT s from Servicio s where s.activo = true order by s.Nombre_servicio")
	public List<Servicio> getAllServicios();
	
	@Query("SELECT s from Servicio s WHERE s.id_Servicio IN (SELECT se.servicio.id_Servicio from SystemEquipo se GROUP BY se.servicio.id_Servicio) and s.activo = true")
    public List<Servicio> ListServicioSys();
	
	@Query("SELECT s FROM Servicio s "+
    		"WHERE s.activo = true and s.Nombre_servicio = ?1")
    public Servicio findServiciobyname(String name);
	
    
    @Query("SELECT e FROM Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+"WHERE e.EstadoBaja = false AND s.id_Servicio=?1 order by e.nombre_Equipo asc")
    public List<Equipo> findEquiposbyServicio(Long id);
    
    
    @Query("SELECT COUNT(e) from Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "+
    		"WHERE s.activo = true and e.Periodicidad=?1 AND s.id_Servicio=?2")
    public int countEspecificPeriodicidadbyServicio(int period,Long id);
    
    @Query("SELECT s FROM Servicio s "+
    	    "INNER JOIN Equipo e ON e.servicio.id_Servicio=s.id_Servicio "
    		+"WHERE s.activo = true and e.Periodicidad=?1 "+
    	    "GROUP BY s.id_Servicio")
    public List<Servicio> listServiciosByPeriodicidad(int id);
    
    
    @Query("SELECT COUNT(e) from Equipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "+
    		"WHERE s.activo = true and s.id_Servicio=?1")
    public int countEspecificPbyServicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE s.activo = true and e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND e.Periodicidad = 4 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposCuatrimestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE s.activo = true and e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND e.Periodicidad = 3 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposTrimestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE s.activo = true and e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND e.Periodicidad = 2 AND s.id_Servicio=?1")
    public List<Equipo> findEquiposSemestralservicio(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+ "WHERE s.activo = true and e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false AND s.id_Servicio=?1 AND e.Periodicidad=1")
    public List<Equipo> findEquiposAnualservicio(Long id);
    
    //SYS
    @Query("SELECT e FROM SystemEquipo e "+
    		"INNER JOIN Servicio s ON e.servicio.id_Servicio=s.id_Servicio "
    		+"WHERE s.activo = true and e.EstadoBaja = false AND s.id_Servicio=?1")
    public List<SystemEquipo> findSystemEquiposbyServicio(Long id);
    
    @Query("SELECT s FROM Servicio s "+
    		"INNER JOIN SystemEquipo e ON e.servicio.id_Servicio=s.id_Servicio "
    		+"where s.activo = true GROUP BY s.id_Servicio ORDER BY s.id_Servicio ASC")
    public List<Servicio> listServiciosconSystemEquipos();
    
    @Query("SELECT COUNT(e) from Equipo e GROUP BY e.servicio.id_Servicio ORDER BY e.servicio.id_Servicio")
    public List<Integer> countbyTiposServicios();
    
    @Query("SELECT s FROM Servicio s where s.activo = true and s.id_Servicio in(select e.servicio.id_Servicio from Equipo e where e.id_Equipo in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.EstadoBaja = false)")
    public List<Servicio> getServiciosConComodatos();
    

}
