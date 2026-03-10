package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Criterios_tecnicos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriterios_tecnicosDao extends CrudRepository<Criterios_tecnicos, Long> {

    @Query("SELECT COUNT(ct) from Criterios_tecnicos ct")
    public int countAll();
}
