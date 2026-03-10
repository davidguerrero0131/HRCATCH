package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HUSRTbdBiomedica.app.Dao.ITipoDocumentoDao;
import com.HUSRTbdBiomedica.app.entity.TipoDocumento;

@Service
public class TipoDocumentoServiceImp implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	
	@Override
	public void addTipoDocumento(TipoDocumento tipoDocumento) {
		tipoDocumentoDao.save(tipoDocumento);
	}

	@Override
	public List<TipoDocumento> getAllTiposDocumento() {
		return tipoDocumentoDao.findAll();
	}

	@Override
	public TipoDocumento getTipoDocumento(Long idTipoDocumento) {
		return tipoDocumentoDao.getById(idTipoDocumento);
	}

	@Override
	public List<TipoDocumento> getDocumentosPEndientesEquipo(Long idEquipo) {
		return tipoDocumentoDao.getDocumentosPendientesEquipo(idEquipo);
	}

}
