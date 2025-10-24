package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IProveedorDao;
import com.HUSRTbdBiomedica.app.entity.Proveedor;

@Service
public class ProveedorServiceImp implements IProveedorService {

    @Autowired
    private IProveedorDao proveedorDao;

    @Override
    public List<Proveedor> getListProvedores() {
        
        return proveedorDao.getAllProveedores();
    }

	@Override
	public int getIdProveedor(String nombreProveedor) {
		
		return proveedorDao.getProveedorPorNombre(nombreProveedor).getIdProveedor();
	}
    
}
