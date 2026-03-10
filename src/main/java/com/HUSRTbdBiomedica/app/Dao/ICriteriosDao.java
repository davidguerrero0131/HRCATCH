package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Criterios;
import com.HUSRTbdBiomedica.app.entity.Equipo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriteriosDao extends CrudRepository<Criterios, Long> {

    @Query("SELECT COUNT(c) from Criterios c")
    public int countAll();
    
  
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Criterios c ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=25")
    public List<Equipo> listaequiposevmala();
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Criterios c ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=60 AND c.Total_puntos>25")
    public List<Equipo> listaequiposevregular();
    
    @Query("SELECT e from Equipo e "
    		+ "INNER JOIN Criterios c ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=100 AND c.Total_puntos>60")
    public List<Equipo> listaequiposevbuena();
    
    
    @Query("SELECT c from Criterios c "
    		+ "INNER JOIN Equipo e ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=25")
    public List<Criterios> listacrievmala();
    
    @Query("SELECT c from Criterios c "
    		+ "INNER JOIN Equipo e ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=60 AND c.Total_puntos>25")
    public List<Criterios> listacrievregular();
    
    @Query("SELECT c from Criterios c "
    		+ "INNER JOIN Equipo e ON c.equipo.id_Equipo=e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND c.Total_puntos<=100 AND c.Total_puntos>60")
    public List<Criterios> listacrievbueno();
    
    @Query("SELECT c FROM Criterios c "
    		+ "INNER JOIN Equipo e ON c.equipo.id_Equipo= e.id_Equipo "
    		+ "WHERE e.EstadoBaja = false AND e.id_Equipo=?1")
    public List<Criterios> findbyequipo(Long id);
    
    
    @Query("SELECT MAX(c.id_Criterios) FROM Criterios c "+
    		"WHERE c.equipo.id_Equipo =?1")
    public String findLastev(Long id);
    
    @Query("SELECT c.Total_puntos,c.Fecha FROM Criterios c "
    		+"INNER JOIN Equipo e ON c.equipo.id_Equipo=e.id_Equipo "
    		+"WHERE e.EstadoBaja = false AND c.equipo.id_Equipo =?1 AND c.Fecha>=?2 AND c.Fecha<=?3")
    public List<String> findCriteriosbyequipof(Long id, Date fecha1, Date fecha2);
    
}