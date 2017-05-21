package com.mattvoget.sarlacc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattvoget.sarlacc.models.App;
import com.mattvoget.sarlacc.repositories.AppRepository;

@Service
public class AppService {

	@Autowired private AppRepository appRepo;
	
	public App findAppByName(String appName) {
		return appRepo.findAppByName(appName);
	}
	
	public List<App> findAllApps() {
		return appRepo.findAll();
	}
	
	public App createNewApp(App newApp) {
		return appRepo.save(newApp);
	}
	
	@Transactional
	public void deleteApp(String appName) {
		appRepo.delete(appRepo.findAppByName(appName));
	}
}
