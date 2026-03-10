package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IBackupDao;
import com.HUSRTbdBiomedica.app.entity.Backup;

@Service
public class BackupServiceImp implements IBackupService {

	@Autowired
	private IBackupDao backupDao;
	
	@Override
	public List<Backup> getAllBackup() {
		
		return (List<Backup>) backupDao.getallBackups();
	}

	@Override
	public void addBackup(Backup backup) {

		backupDao.save(backup);
		
	}

	@Override
	public Backup findById(Long id) {
		return backupDao.findbyId(id);
	}

}
