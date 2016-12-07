package com.mattvoget.sarlacc.repositories;

import com.mattvoget.sarlacc.client.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
	User findByUsername(String username);
}
