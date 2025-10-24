package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Repuestos;


public interface IRepuestosService {

	public List<Repuestos>ListRepuestos();
	public Repuestos findOne(Long id);
	public void save(Repuestos repuestos);
	public void delete(Long id);
	public int countAll();
	public int countRepuestosbyEquipo(Long id);
	public List<Repuestos> findRepuestosbyEquipo(Long id);
	public List<Repuestos> findRepuestosbyTipoEquipo(Long id);
}
