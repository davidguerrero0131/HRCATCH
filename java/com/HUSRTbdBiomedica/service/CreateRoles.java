package com.HUSRTbdBiomedica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;



public class CreateRoles implements CommandLineRunner{

	@Autowired
	IAuthorityService AuthorityService;
	
	@Override
	public void run(String... args) throws Exception{
		/*Authority roleAdmin = new Authority();
		roleAdmin.setAuthority("ADMIN");
		Authority roleEditor = new Authority();
		roleEditor.setAuthority("EDITOR");
		Authority roleUser = new Authority();
		roleUser.setAuthority("USER");
		Authority roleVisitor = new Authority();
		roleVisitor.setAuthority("VISITOR");
		
		AuthorityService.save(roleVisitor);
		AuthorityService.save(roleUser);
		AuthorityService.save(roleEditor);
		AuthorityService.save(roleAdmin);*/
	}
}
