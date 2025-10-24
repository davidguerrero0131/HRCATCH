package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida_Biomedica;

public interface IHoja_vida_BiomedicaService {

	public List<Hoja_vida_Biomedica> getAllHVBiomedica();
	public void AddHoja_Vida_Biomedica(Hoja_vida_Biomedica hoja_vida_Biomedica);
	
}
