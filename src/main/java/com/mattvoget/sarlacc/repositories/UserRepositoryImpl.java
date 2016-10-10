package com.mattvoget.sarlacc.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mattvoget.sarlacc.models.User;

public class UserRepositoryImpl implements UserRepositoryCustom {

	private Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public User createAccount(User newAccount) {
		
		Query q = new Query();
		q.addCriteria(Criteria.where("username").is(newAccount.getUsername()));

		User user = mongoTemplate.findOne(q, User.class, "user");
		
		if (user != null){
			throw new RuntimeException("Username already exists!");
		}
		
		mongoTemplate.insert(newAccount, "user");
		return null;
	}

}
