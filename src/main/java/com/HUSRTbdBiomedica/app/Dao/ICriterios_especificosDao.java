package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Criterios_especificos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriterios_especificosDao extends CrudRepository<Criterios_especificos, Long> {

    @Query("SELECT COUNT(ces) from Criterios_especificos ces")
    public int countAll();
}