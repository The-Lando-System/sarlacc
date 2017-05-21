package com.mattvoget.sarlacc.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.mattvoget.sarlacc.models.AppRole;

@Component
public interface AppRoleRepository extends MongoRepository<AppRole, String> {
	public AppRole findByUsernameAndAppName(String username, String appName);
	public List<AppRole> findByUsername(String username);
}
