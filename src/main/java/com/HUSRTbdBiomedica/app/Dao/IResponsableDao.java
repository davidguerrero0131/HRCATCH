package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Responsable;

@Repository
public interface IResponsableDao extends CrudRepository<Responsable,Long>{

	@Query("SELECT COUNT(r) FROM Responsable r")
    public int countAll();
	
	@Query("SELECT r FROM Responsable r WHERE r.Externo = '1'")
	public List<Responsable> listexternos();
	
	@Query("Select r from Responsable r where r.Calificacion = 150")
	public List<Responsable> listResponsablesComodatos();
	

}
