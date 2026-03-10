package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_comodatos;

public interface IHoja_vida_comodatosService {

	public List<Hoja_vida_comodatos> Hoja_vida_otrosObtainallHV();
	public int CounallOtrosHV();
	public Hoja_vida_comodatos findOne(Long id);
	public void save(Hoja_vida_comodatos hoja_vida_otros);
	public void delete(Long id);
	public List<String> listProveedores();
	public List<Hoja_vida_comodatos> listByProveedor(String nombreProveedor);
	public Long LastHV();
	public void desactivarComodato(long idHVComodato);
}
