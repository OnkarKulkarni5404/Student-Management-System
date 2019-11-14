package com.StudentClient.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.StudentClient.beans.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, Integer>{

	public Student findByStudentId(String id);
		
	
}