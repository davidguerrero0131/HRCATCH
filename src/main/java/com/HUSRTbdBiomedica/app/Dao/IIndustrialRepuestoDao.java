package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.IndustrialRepuesto;

@Repository
public interface IIndustrialRepuestoDao extends CrudRepository<IndustrialRepuesto, Long>{

	@Query("SELECT COUNT(ir) FROM IndustrialRepuesto ir")
    public int countAll();
}
