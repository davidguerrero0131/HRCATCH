package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IUsuarioDao;
import com.HUSRTbdBiomedica.app.entity.Authority;
import com.HUSRTbdBiomedica.app.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImp implements IUsuarioService{

	@Autowired
	private IUsuarioDao UsuarioDao;
	
	@Override
	@Transactional
	public List<Usuario> ListarUsuarios() {
		return (List<Usuario>)UsuarioDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Usuario> ListUsuariosbyid(Long id) {
		return null;
	}

	@Override
	@Transactional
	public Usuario findOne(Long id) {
		return UsuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		UsuarioDao.save(usuario);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		UsuarioDao.delete(findOne(id));
	}

	@Override
	public Usuario findBycc(String term) {
		return UsuarioDao.findByCedula(term);
	}

	@Override
	public int countAll() {
		return UsuarioDao.countAll();
	}

	@Override
	public List<Usuario> tecnauxbiomedico() {
		return UsuarioDao.tecnauxbiomedicos();
	}

	@Override
	public List<Usuario> users() {
		// TODO Auto-generated method stub
		return UsuarioDao.users();
	}

	@Override
	public List<Authority> userauth() {
		// TODO Auto-generated method stub
		return UsuarioDao.usersauth();
	}

	@Override
	public List<Usuario> tecnicoSistemas() {
		// TODO Auto-generated method stub
		return UsuarioDao.tecsystem();
	}

	@Override
	public void setPassword(String password, String cedula) {
		UsuarioDao.setPassword(password, cedula);
		
	}
	

}
