package com.HUSRTbdBiomedica.app.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_otros;

public interface IHoja_vida_otrosDao extends CrudRepository<Hoja_vida_otros,Long>{
	
	@Query("SELECT COUNT(ho) from Hoja_vida_otros ho")
    public int countAll();
	
	@Query("SELECT MAX(ho.id_Hoja_vida_otros) FROM Hoja_vida_otros ho")
    public Long findLastHV();
	
	@Query("SELECT ho.Proveedor_otros FROM Hoja_vida_otros ho GROUP BY ho.Proveedor_otros")
    public List<String> findProveedores();
	

	@Query("SELECT ho FROM Hoja_vida_otros ho WHERE ho.Proveedor_otros=?1")
    public List<Hoja_vida_otros> findByProveedor(String nombreProveedor);
}
