package com.StudentClient.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.StudentClient.beans.Token;

public interface TokenRepo extends MongoRepository<Token, String>{

	public Token findByToken(String header);
	
}
