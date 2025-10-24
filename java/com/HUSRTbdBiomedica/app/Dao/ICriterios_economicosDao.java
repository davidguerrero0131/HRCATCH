package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Criterios_economicos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriterios_economicosDao extends CrudRepository<Criterios_economicos, Long> {

   
	
	@Query("SELECT COUNT(ce) from Criterios_economicos ce")
    public int countAll();
}