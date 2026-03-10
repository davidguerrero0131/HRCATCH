package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.IndustrialRepuesto;


public interface IIndustrialRepuestoService {

	public List<IndustrialRepuesto>ListIndRepuestos();
	public Optional<IndustrialRepuesto>ListIndustrialRepuestobyId(Long id);
	public IndustrialRepuesto findOne(Long id);
	public void save(IndustrialRepuesto industrialRepuesto);
	public void delete(Long id);
}
