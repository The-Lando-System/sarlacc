package com.mattvoget.sarlacc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.mattvoget.sarlacc.models.App;

@Component
public interface AppRepository extends MongoRepository<App, String> {
	public App findAppByName(String name);
}
