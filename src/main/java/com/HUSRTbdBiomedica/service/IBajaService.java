package com.HUSRTbdBiomedica.service;


import java.util.List;
import java.util.Optional;
import com.HUSRTbdBiomedica.app.entity.Baja;

public interface IBajaService {

	public List<Baja> listBajas();
	public Optional<Baja>ListEquipobyId(Long id);
	public Baja findOne(Long id);
	public void save(Baja baja);
	public void delete(Long id);
}
