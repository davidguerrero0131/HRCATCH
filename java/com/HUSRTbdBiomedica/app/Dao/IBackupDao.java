package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.HUSRTbdBiomedica.app.entity.Backup;

@Repository
public interface IBackupDao extends CrudRepository<Backup, Long> {

	
	@Query("Select bu from Backup bu where id_Backup = ?1")
	public Backup findbyId(Long id);
	
	@Query("Select bu from Backup bu order by bu.fecha_backup desc")
	public List<Backup> getallBackups();
}
