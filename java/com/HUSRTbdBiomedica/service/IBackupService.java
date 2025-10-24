package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Backup;

public interface IBackupService {

	public List<Backup> getAllBackup();
	public void addBackup(Backup backup); 
	public Backup findById(Long id);
}
