package com.mattvoget.sarlacc.repositories;

import com.mattvoget.sarlacc.client.models.User;

public interface UserRepositoryCustom {

	User createAccount(User newAccount);
	
}
