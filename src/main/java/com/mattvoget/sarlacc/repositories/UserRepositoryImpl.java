package com.mattvoget.sarlacc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mattvoget.sarlacc.models.User;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@Autowired
    private MongoTemplate mongoTemplate;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public User createAccount(User newAccount) {
		
		Query q = new Query();
		q.addCriteria(Criteria.where("username").is(newAccount.getUsername()));

		User user = mongoTemplate.findOne(q, User.class, "user");
		
		if (user != null){
			throw new RuntimeException("Username already exists!");
		}

		newAccount.setPassword(encoder.encode(newAccount.getPassword()));

		mongoTemplate.insert(newAccount, "user");
		return null;
	}

}
