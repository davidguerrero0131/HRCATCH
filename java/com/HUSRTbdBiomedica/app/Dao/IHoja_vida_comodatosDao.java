package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_comodatos;


public interface IHoja_vida_comodatosDao extends CrudRepository<Hoja_vida_comodatos, Long>{

	@Query("SELECT COUNT(ho) from Hoja_vida_comodatos ho")
    public int countAll();
	
	@Query("SELECT MAX(ho.id_Hoja_vida_otros) FROM Hoja_vida_comodatos ho")
    public Long findLastHV();
	
	@Query("SELECT ho.Proveedor_otros FROM Hoja_vida_comodatos ho WHERE ho.ComodatoActivo = true GROUP BY ho.Proveedor_otros")
    public List<String> findProveedores();
	

	@Query("SELECT ho FROM Hoja_vida_comodatos ho WHERE ho.ComodatoActivo = true AND ho.Proveedor_otros=?1")
    public List<Hoja_vida_comodatos> findByProveedor(String nombreProveedor);
    
    
	@Transactional
	@Modifying
	@Query("UPDATE Hoja_vida_comodatos ho SET ho.ComodatoActivo = false WHERE ho.id_Hoja_vida_otros = ?1")
	public void desactivarComodato(long idHVComodato);
}
