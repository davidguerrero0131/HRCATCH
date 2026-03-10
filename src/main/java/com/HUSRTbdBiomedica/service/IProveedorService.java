package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Proveedor;

public interface IProveedorService {

    public List<Proveedor> getListProvedores();
    public int getIdProveedor(String nombreProveedor);

}
