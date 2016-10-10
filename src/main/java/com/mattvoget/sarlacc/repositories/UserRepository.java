package com.mattvoget.sarlacc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mattvoget.sarlacc.models.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
	User findByUsername(String username);
}
