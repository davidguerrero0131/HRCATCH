package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_Biomedica;

@Repository
public interface IHoja_Vida_BiomedicaDao extends CrudRepository<Hoja_vida_Biomedica, Long> {

	
}
