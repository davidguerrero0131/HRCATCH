package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_otros;

public interface IHoja_vida_otrosService {
	public List<Hoja_vida_otros> Hoja_vida_otrosObtainallHV();
	public int CounallOtrosHV();
	public Hoja_vida_otros findOne(Long id);
	public void save(Hoja_vida_otros hoja_vida_otros);
	public void delete(Long id);
	public List<String> listProveedores();
	public List<Hoja_vida_otros> listByProveedor(String nombreProveedor);
	public Long LastHV();

}
