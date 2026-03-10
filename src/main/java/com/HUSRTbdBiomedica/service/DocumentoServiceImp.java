package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IDocumentoDao;
import com.HUSRTbdBiomedica.app.entity.Documento;

@Service
public class DocumentoServiceImp implements IDocumentoService{

	
	@Autowired
	private IDocumentoDao documentoDao;
	
	@Override
	public void addDocumento(Documento documento) {
		documentoDao.save(documento);
	}

	@Override
	public List<Documento> DocumentosEquipo(Long idEquipo) {
		return documentoDao.documentosEquipo(idEquipo);
	}

	@Override
	public Documento getdocumentoById(Long idDocumento) {
		return documentoDao.getById(idDocumento);
	}

	@Override
	public void disabledocumento(Long idDocumento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Documento> getDocumentoEquipo(Long idEquipo) {
		// TODO Auto-generated method stub
		return documentoDao.documentosEquipo(idEquipo);
	}

}
