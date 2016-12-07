package com.mattvoget.sarlacc.repositories;

import com.mattvoget.sarlacc.models.User;

public interface UserRepositoryCustom {

	User createAccount(User newAccount);
	
}
