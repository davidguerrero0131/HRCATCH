package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.IndustrialHoja_vida;


public interface IIndustrialHoja_vidaService {

	public List<IndustrialHoja_vida>ListIndHvs();
	public Optional<IndustrialHoja_vida>ListIndustrialHVbyId(Long id);
	public IndustrialHoja_vida findOne(Long id);
	public void save(IndustrialHoja_vida industrialHoja_vida);
	public void delete(Long id);
}
