package com.StudentClient.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.StudentClient.beans.Token;

@Repository
public interface TokenRepo extends MongoRepository<Token, String>{

	public Token findByToken(String header);
	
	
}
