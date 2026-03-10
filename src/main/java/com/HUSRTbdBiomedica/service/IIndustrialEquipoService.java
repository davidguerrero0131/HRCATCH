package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.IndustrialEquipo;


public interface IIndustrialEquipoService {

	public List<IndustrialEquipo>ListIndEquipos();
	public Optional<IndustrialEquipo>ListIndustrialEquipobyId(Long id);
	public IndustrialEquipo findOne(Long id);
	public void save(IndustrialEquipo industrialEquipo);
	public void delete(Long id);
	
	public List<IndustrialEquipo> listIndEquiposbyTipo(Long id);
	public List<IndustrialEquipo> listIndEquiposbyServicio(Long id);
}
