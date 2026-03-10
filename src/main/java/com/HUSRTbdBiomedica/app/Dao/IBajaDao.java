package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Baja;

@Repository
public interface IBajaDao extends CrudRepository<Baja,Long> {

	
	@Query("SELECT COUNT(b) from Equipo b")
    public int countAll();
}
