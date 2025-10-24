package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Hospital;

@Repository
public interface IHospitalDao extends CrudRepository<Hospital, Long> {
	
	@Query("SELECT COUNT(hl) from Hospital hl")
    public int countAll();

}
