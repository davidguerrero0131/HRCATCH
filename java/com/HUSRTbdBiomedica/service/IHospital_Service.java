package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;


import com.HUSRTbdBiomedica.app.entity.Hospital;

public interface IHospital_Service {
	public List<Hospital>ListHospital();
	public Optional<Hospital>ListHospitalbyId(Long id);
	public Hospital findOne(Long id);
	public void save(Hospital hospital);
	public void delete(Long id);
	

}
