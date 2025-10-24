package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Authority;
import com.HUSRTbdBiomedica.app.entity.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("SELECT COUNT(u) from Usuario u")
    public int countAll();
	
	@Query("SELECT u FROM Usuario u WHERE u.Cedula=?1")
    public Usuario findByCedula(String cedula);
	
	@Query("SELECT u FROM Usuario u "+
			"WHERE u.Tipo_cargo_usuario=3 AND u.Area = '1'")
	public List<Usuario> tecnauxbiomedicos();
	
	@Query("SELECT u FROM Usuario u WHERE u.Tipo_cargo_usuario>8")
	public List<Usuario> users();
	
	@Query("SELECT u.roles FROM Usuario u ORDER BY u.id_Usuario")
	public List<Authority> usersauth();
	
	@Query("SELECT u FROM Usuario u "
			+ "WHERE (u.Tipo_cargo_usuario = 4 OR u.Tipo_cargo_usuario = 5) "
			+ "AND u.Area = '2'")
	public List<Usuario> tecsystem();
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario u SET u.Password = ?1 WHERE u.Cedula = ?2")
	public void setPassword(String password, String cedula);
	
	
	
	
}
