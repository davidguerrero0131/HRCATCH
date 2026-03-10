package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Authority;

@Repository
public interface IAuthorityDao extends CrudRepository<Authority, Long> {

	@Override
	default <S extends Authority> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default <S extends Authority> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	default boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	default Iterable<Authority> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Iterable<Authority> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Query("SELECT COUNT(a) from Authority a")
    public int countAll();
	
	@Query("SELECT a FROM Authority a")
	public List<Authority> listRoles();
	

	@Override
	default void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void delete(Authority entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void deleteAll(Iterable<? extends Authority> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	

}
