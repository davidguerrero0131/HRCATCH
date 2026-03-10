package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Equipo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IEquipoDao extends CrudRepository<Equipo, Long> {
	
	
	
	@Query("Select e from Equipo e where e.servicio.id_Servicio = ?1 and e.EstadoBaja = false order by e.nombre_Equipo asc")
	public List<Equipo> getEquiposServicio(Long idServicio);
    
    @Query("SELECT e from Equipo e where e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.tipo_equipo.id_Tipo_equipo not in(135, 141) and e.servicio.id_Servicio != 83) and e.EstadoBaja = false")
    public List<Equipo> getEquiposPlan();
	

    @Query("select e from Equipo e where e.responsable.id_Responsable = ?1 and e.EstadoBaja = false order by e.nombre_Equipo asc")
    public List<Equipo> getEquiposComodatoResponsable(Long responsable);
    
    @Query("SELECT COUNT(e) from Equipo e")
    public int countAll();
    
    @Query("SELECT e from Equipo e where e.EstadoBaja = false and e.tipo_equipo.id_Tipo_equipo != 1336")
    public List<Equipo> getAllEquiposF();
    
    @Query("SELECT e from Equipo e")
    public List<Equipo> getAllEquiposT();
    
    @Query("SELECT e from Equipo e where e.EstadoBaja = false and e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true)")
    public List<Equipo> getAllEquiposSinComodatos();
    
    @Query("SELECT e from Equipo e where e.EstadoBaja = false and e.id_Equipo in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true)")
    public List<Equipo> getAllComodatos();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.EstadoBaja = false AND e.Periodicidad=4 order by e.nombre_Equipo asc")
    public int countCuatrimestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.EstadoBaja = false AND e.Periodicidad=3 order by e.nombre_Equipo asc")
    public int countTrimestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.EstadoBaja = false AND e.Periodicidad=2 order by e.nombre_Equipo asc")
    public int countSemestral();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.EstadoBaja = false AND e.Periodicidad=1 order by e.nombre_Equipo asc")
    public int countAnual();
    
    @Query("SELECT COUNT(e) from Equipo e WHERE e.EstadoBaja = false GROUP BY e.tipo_equipo.id_Tipo_equipo ORDER BY e.tipo_equipo.id_Tipo_equipo")
    public List<Integer> countbyTiposEquipo();

       
    @Query("SELECT e from Equipo e WHERE e.EstadoBaja = false AND e.nombre_Equipo LIKE %?1%")
    public List<Equipo> findByNombre(String term);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.EstadoBaja = false AND e.Periodicidad = 4 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposCuatrimestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.EstadoBaja = false AND e.Periodicidad = 3 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposTrimestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.EstadoBaja = false AND e.Periodicidad = 2 AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposSemestral(Long id);
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "WHERE e.EstadoBaja = false AND t.id_Tipo_equipo=?1 AND e.Periodicidad=1")
    public List<Equipo> findEquiposAnual(Long id);
    

    
    @Query("SELECT e FROM Equipo e "+
    		"INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+"WHERE e.EstadoBaja = false AND t.id_Tipo_equipo=?1")
    public List<Equipo> findEquiposbyTipoEquipo(Long id);
    
    
    
    //para criterios tecnicos
    
    @Query("SELECT count(r) FROM Reporte r "+
    		"INNER JOIN Equipo e ON r.equipo.id_Equipo=e.id_Equipo "
    		+"WHERE e.EstadoBaja = false AND r.Tipo_mantenimiento=2 AND e.id_Equipo =?1 AND r.Fecha>=?2 AND r.Fecha<=?3")
    public int numerocorrectivosano(Long id, Date fechainicialano,Date fechaactual);
    
    //numero de calibraciones programadas
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND e.Enero_mantenimiento='k' OR e.Enero_mantenimiento='K'")
    public int numcalenero();
    
    //numero mantenimientos preventivos programados
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M')")
    public int numprevenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M')")
    public int numprefebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M')")
    public int numpremarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M')")
    public int numpreabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M')")
    public int numpremayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M')")
    public int numprejunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M')")
    public int numprejulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M')")
    public int numpreagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M')")
    public int numpreseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M')")
    public int numpreoctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M')")
    public int numprenoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M')")
    public int numprediciembre();
    
    //numcontratoagendados
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C')")
    public int numcontratovenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C')")
    public int numcontratofebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C')")
    public int numcontratomarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C')")
    public int numcontratoabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C')")
    public int numcontratomayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C')")
    public int numcontratojunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C')")
    public int numcontratojulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C')")
    public int numcontratoagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C')")
    public int numcontratoseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C')")
    public int numcontratooctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C')")
    public int numcontratonoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C')")
    public int numcontratodiciembre();
    
    
    //numgarantiaagendados
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G')")
    public int numgarantiavenero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G')")
    public int numgarantiafebrero();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G')")
    public int numgarantiamarzo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G')")
    public int numgarantiaabril();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G')")
    public int numgarantiamayo();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G')")
    public int numgarantiajunio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G')")
    public int numgarantiajulio();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G')")
    public int numgarantiaagosto();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G')")
    public int numgarantiaseptiembre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G')")
    public int numgarantiaoctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G')")
    public int numgarantianoviembre();
 
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.EstadoBaja = false AND (e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G')")
    public int numgarantiadiciembre();
    
    
    //tojavascript
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo,e.EstadoBaja "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenameenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M')  and e.EstadoBaja = false")
    public List<String>prenamefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenameabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenameagosto();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenameseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenameoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M') and e.EstadoBaja = false")
    public List<String>prenamediciembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomeenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomeabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomeagosto();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomeseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomeoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<String> contratomediciembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiameenero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamefebrero();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamemarzo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiameabril();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamemayo();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamejunio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamejulio();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiameagosto();

    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiameseptiembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and ( e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiameoctubre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamenoviembre();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Ubicacion_especifica,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo "
    		+ "FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<String> garantiamediciembre();
    //preventivosagendados
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='m' OR e.Enero_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prevenero();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='m' OR e.Febrero_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prefebrero();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='m' OR e.Marzo_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> premarzo();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='m' OR e.Abril_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> preabril();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='m' OR e.Mayo_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> premayo();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='m' OR e.Junio_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prejunio();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='m' OR e.Julio_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prejulio();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='m' OR e.Agosto_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> preagosto();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='m' OR e.Septiembre_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> preseptiembre();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Octubre_mantenimiento='m' OR e.Octubre_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> preoctubre();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='m' OR e.Noviembre_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prenoviembre();
 
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo=t.id_Tipo_equipo "
    		+ "INNER JOIN Servicio s ON e.servicio.id_Servicio = s.id_Servicio "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='m' OR e.Diciembre_mantenimiento='M') and e.EstadoBaja = false ORDER BY s.Nombre_servicio ASC,e.id_Equipo ASC")
    public List<Equipo> prediciembre();
    
//numcontratoagendados
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='c' OR e.Enero_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratovenero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='c' OR e.Febrero_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratofebrero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='c' OR e.Marzo_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratomarzo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='c' OR e.Abril_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratoabril();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='c' OR e.Mayo_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratomayo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='c' OR e.Junio_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratojunio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='c' OR e.Julio_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratojulio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='c' OR e.Agosto_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratoagosto();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE  e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='c' OR e.Septiembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratoseptiembre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Octubre_mantenimiento='c' OR e.Octubre_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratooctubre();
    
    @Query("SELECT COUNT(e) FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='c' OR e.Noviembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratonoviembre();
 
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='c' OR e.Diciembre_mantenimiento='C') and e.EstadoBaja = false")
    public List<Equipo> contratodiciembre();
    
    //garantiaagendados
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento='g' OR e.Enero_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiavenero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Febrero_mantenimiento='g' OR e.Febrero_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiafebrero();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marzo_mantenimiento='g' OR e.Marzo_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiamarzo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Abril_mantenimiento='g' OR e.Abril_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiaabril();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Mayo_mantenimiento='g' OR e.Mayo_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiamayo();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Junio_mantenimiento='g' OR e.Junio_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiajunio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Julio_mantenimiento='g' OR e.Julio_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiajulio();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Agosto_mantenimiento='g' OR e.Agosto_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiaagosto();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Septiembre_mantenimiento='g' OR e.Septiembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiaseptiembre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Octubre_mantenimiento='g' OR e.Octubre_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiaoctubre();
    
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Noviembre_mantenimiento='g' OR e.Noviembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantianoviembre();
 
    @Query("SELECT e FROM Equipo e "+
    		"WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Diciembre_mantenimiento='g' OR e.Diciembre_mantenimiento='G') and e.EstadoBaja = false")
    public List<Equipo> garantiadiciembre();
    
    //calval
    @Query("SELECT e FROM Equipo e WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Calibracion!=0 OR e.Califiacion!=0 OR e.Validacion !=0) and e.EstadoBaja = false")
    public List<Equipo> equiposcalval();
    
    @Query("SELECT e.nombre_Equipo,e.Serie,e.Marca,e.Modelo,e.Placa_inventario,e.Servicios,e.Ubicacion,e.Dias_mantenimiento,e.Meses_mantenimiento,e.Periodicidad,e.Ano_ingreso,e.Activo,e.Calibracion,e.Califiacion,e.Validacion "
    		+ "FROM Equipo e WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Calibracion!=0 OR e.Califiacion!=0 OR e.Validacion!=0) and e.EstadoBaja = false")
    public List<String> equiposcalvalstr();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Tipo_equipo t ON e.tipo_equipo.id_Tipo_equipo = t.id_Tipo_equipo "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Calibracion!=0 OR e.Califiacion!=0 OR e.Validacion !=0) and e.EstadoBaja = false GROUP BY t.id_Tipo_equipo")
    public List<Equipo> equiposcv();
    //byserie
    @Query("SELECT e.Serie FROM Equipo e WHERE e.EstadoBaja = false order by e.Serie asc")
    public List<String> listaseries();
    
    @Query("SELECT e FROM Equipo e WHERE e.EstadoBaja = false AND e.Serie=?1")
    public List<Equipo> findEquipobySerie(String serie);
    
    //equipos inactivos
    @Query("SELECT e FROM Equipo e WHERE e.EstadoBaja = false AND e.Activo=0")
    public List<Equipo> findEquiposinactivos();
    
    //planeacion anual
    @Query("SELECT e FROM Equipo e INNER JOIN Servicio s ON s.id_Servicio = e.servicio.id_Servicio "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.EstadoBaja = false AND e.Enero_mantenimiento = 'm' OR e.Febrero_mantenimiento = 'm' "
    		+ "OR e.Marzo_mantenimiento = 'm' OR e.Abril_mantenimiento = 'm' OR e.Mayo_mantenimiento = 'm' "
    		+ "OR e.Junio_mantenimiento = 'm' OR e.Julio_mantenimiento = 'm' OR e.Agosto_mantenimiento = 'm' "
    		+ "OR e.Septiembre_mantenimiento = 'm' OR e.Octubre_mantenimiento = 'm' OR e.Noviembre_mantenimiento = 'm' "
    		+ "OR e.Diciembre_mantenimiento = 'm') ORDER BY s.Fmpp DESC,e.id_Equipo DESC")
    public List<Equipo> findEquipostoPlanning();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Responsable r "
    		+ "ON e.responsable.id_Responsable = r.id_Responsable "
    		+ "WHERE e.EstadoBaja = false AND r.id_Responsable=?1")
    public List<Equipo> findEquiposbyResponsable(Long id);
  
    @Query("SELECT e.id_Equipo,e.nombre_Equipo,e.Marca,e.Modelo,COUNT(e) FROM Equipo e WHERE e.EstadoBaja = false GROUP BY e.nombre_Equipo,e.Marca,e.Modelo")
    public List<String> groupbynmm();
    
    @Query("SELECT e.id_Equipo,e.nombre_Equipo,e.Marca,e.Modelo,COUNT(e) FROM Equipo e "
    		+ "WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento != 'm' AND e.Febrero_mantenimiento != 'm' AND "
    		+ "e.Marzo_mantenimiento != 'm' AND e.Abril_mantenimiento != 'm' AND e.Mayo_mantenimiento != 'm' AND "
    		+ "e.Junio_mantenimiento != 'm' AND e.Julio_mantenimiento != 'm' AND e.Agosto_mantenimiento != 'm' AND "
    		+ "e.Septiembre_mantenimiento != 'm' AND e.Octubre_mantenimiento != 'm' AND e.Noviembre_mantenimiento != 'm' AND "
    		+ "e.Diciembre_mantenimiento != 'm') and e.EstadoBaja = false GROUP BY e.nombre_Equipo,e.Marca,e.Modelo")
    public List<String> equiposSinCyG();
    
    @Query("SELECT e.nombre_Equipo,e.Marca,e.Modelo,COUNT(e)  FROM Equipo e WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento = 'G' OR e.Febrero_mantenimiento = 'G' "
    		+ "OR e.Marzo_mantenimiento = 'G' OR e.Abril_mantenimiento = 'G' OR e.Mayo_mantenimiento = 'G' "
    		+ "OR e.Junio_mantenimiento = 'G' OR e.Julio_mantenimiento = 'G' OR e.Agosto_mantenimiento = 'G' "
    		+ "OR e.Septiembre_mantenimiento = 'G' OR e.Octubre_mantenimiento = 'G' OR e.Noviembre_mantenimiento = 'G' "
    		+ "OR e.Diciembre_mantenimiento = 'G') and e.EstadoBaja = false GROUP BY e.nombre_Equipo,e.Marca,e.Modelo")
    public List<String> equiposGarantia();
  
    @Query("SELECT e.nombre_Equipo,e.Marca,e.Modelo,COUNT(e)  FROM Equipo e WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento = 'C' OR e.Febrero_mantenimiento = 'C' "
    		+ "OR e.Marzo_mantenimiento = 'C' OR e.Abril_mantenimiento = 'C' OR e.Mayo_mantenimiento = 'C' "
    		+ "OR e.Junio_mantenimiento = 'C' OR e.Julio_mantenimiento = 'C' OR e.Agosto_mantenimiento = 'C' "
    		+ "OR e.Septiembre_mantenimiento = 'C' OR e.Octubre_mantenimiento = 'C' OR e.Noviembre_mantenimiento = 'C' "
    		+ "OR e.Diciembre_mantenimiento = 'C') and e.EstadoBaja = false GROUP BY e.nombre_Equipo,e.Marca,e.Modelo")
    public List<String> equiposContrato();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Servicio s ON s.id_Servicio = e.servicio.id_Servicio WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento = 'G' OR e.Febrero_mantenimiento = 'G' "
    		+ "OR e.Marzo_mantenimiento = 'G' OR e.Abril_mantenimiento = 'G' OR e.Mayo_mantenimiento = 'G' "
    		+ "OR e.Junio_mantenimiento = 'G' OR e.Julio_mantenimiento = 'G' OR e.Agosto_mantenimiento = 'G' "
    		+ "OR e.Septiembre_mantenimiento = 'G' OR e.Octubre_mantenimiento = 'G' OR e.Noviembre_mantenimiento = 'G' "
    		+ "OR e.Diciembre_mantenimiento = 'G') and e.EstadoBaja = false ORDER BY s.Fmpp DESC,e.id_Equipo DESC")
    public List<Equipo> equiposcompleteGarantia();
    
    @Query("SELECT e FROM Equipo e INNER JOIN Servicio s ON s.id_Servicio = e.servicio.id_Servicio WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Enero_mantenimiento = 'C' OR e.Febrero_mantenimiento = 'C' "
    		+ "OR e.Marzo_mantenimiento = 'C' OR e.Abril_mantenimiento = 'C' OR e.Mayo_mantenimiento = 'C' "
    		+ "OR e.Junio_mantenimiento = 'C' OR e.Julio_mantenimiento = 'C' OR e.Agosto_mantenimiento = 'C' "
    		+ "OR e.Septiembre_mantenimiento = 'C' OR e.Octubre_mantenimiento = 'C' OR e.Noviembre_mantenimiento = 'C' "
    		+ "OR e.Diciembre_mantenimiento = 'C') and e.EstadoBaja = false ORDER BY s.Fmpp DESC,e.id_Equipo DESC")
    public List<Equipo> equiposcompleteContrato();
    
    @Query("SELECT e FROM Equipo e WHERE e.id_Equipo not in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and (e.Marca =?1 AND e.Modelo =?2 AND e.EstadoBaja = false)")
    public List<Equipo> findEquiposByMM(String marca, String modelo);
    
    
	@Transactional
	@Modifying
	@Query("UPDATE Equipo e SET e.EstadoBaja = true WHERE e.id_Equipo = ?1")
	public void bajaEquipo(long idEquipo);
	
	
	@Query("select e from Equipo e where e.id_Equipo in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.tipo_equipo.id_Tipo_equipo = ?1 and e.EstadoBaja = false")
	public List<Equipo> getComodatosPorTipoEquipo(long idTipoEquipo);
	
	@Query("select e from Equipo e where e.id_Equipo in (select hv.equipo.id_Equipo from Hoja_vida hv where hv.Comodato = true) and e.servicio.id_Servicio = ?1 and e.EstadoBaja = false")
	public List<Equipo> getComodatosPorServicio(long idServicio);
	
	
}
