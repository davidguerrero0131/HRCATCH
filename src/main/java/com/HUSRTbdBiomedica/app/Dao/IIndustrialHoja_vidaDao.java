package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.IndustrialHoja_vida;

@Repository
public interface IIndustrialHoja_vidaDao extends CrudRepository<IndustrialHoja_vida, Long>{

	@Query("SELECT COUNT(ih) FROM IndustrialHoja_vida ih")
    public int countAll();
}
