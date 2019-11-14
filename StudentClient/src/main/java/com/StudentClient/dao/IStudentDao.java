package com.StudentClient.dao;

import java.util.List;

import com.StudentClient.beans.Branch;
import com.StudentClient.beans.Gender;
import com.StudentClient.beans.Student;

public interface IStudentDao {

	public String updateStudent(Student s);
	public List<Student> showByBranch(Branch branch);
	public List<Student> showByGender(Gender gender);
}