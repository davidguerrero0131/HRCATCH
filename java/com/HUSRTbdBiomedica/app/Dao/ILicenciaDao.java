package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Licencia;

@Repository
public interface ILicenciaDao extends CrudRepository<Licencia, Long>{

	@Query("SELECT COUNT(l) FROM Licencia l")
    public int countAll();
	
	@Query("SELECT MAX(l.id_Licencia) FROM Licencia l")
    public String findLastIdLicencia();
	
}
