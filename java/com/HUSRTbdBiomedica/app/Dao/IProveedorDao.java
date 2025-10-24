package com.HUSRTbdBiomedica.app.Dao;

import com.HUSRTbdBiomedica.app.entity.Proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorDao extends CrudRepository<Proveedor, Integer> {

    @Query("Select p from Proveedor p where p.id = ?1")
    public Proveedor getProveedorPorID(int id);
    
    @Query("Select p from Proveedor p")
    public List<Proveedor> getAllProveedores();
    
    @Query("Select p from Proveedor p where p.idProveedor = 2")
    public Proveedor getProveedorPorNombre(String nombreProveedor);


}