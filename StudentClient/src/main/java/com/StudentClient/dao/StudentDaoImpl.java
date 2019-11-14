package com.StudentClient.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.StudentClient.beans.Branch;
import com.StudentClient.beans.Gender;
import com.StudentClient.beans.Student;
import com.StudentClient.repo.StudentRepo;

@Repository("dao")
public class StudentDaoImpl implements IStudentDao {

	@Autowired
	private MongoTemplate mtemp;

	@Autowired
	private StudentRepo repo;

	@Override
	public String updateStudent(Student s) {
		boolean flag = false;

		Student nullable_student = repo.findByStudentId(s.getStudentId());

		if (nullable_student == null) {
			throw new UserNotFoundException("NoUser");
		}

		Query query = new Query();
		Query PhoneQuery = new Query();

		PhoneQuery.addCriteria(Criteria.where("phone").is(s.getPhone()));
		List<Student> samephone = mtemp.find(PhoneQuery, Student.class);

		query.addCriteria(new Criteria().andOperator(Criteria.where("rollNo")
				.is(s.getRollNo()), Criteria.where("branch").is(s.getBranch())));
		List<Student> students = mtemp.find(query, Student.class);

		for (Student p : students) {
			if (p.equals(s)) {
				continue;
			} else {
				flag = true;
				throw new NoUniqueUserFound(
						"No unique ness in branch and rollno");
			}
		}

		for (Student phony : samephone) {
			if (phony.equals(s)) {
				continue;
			} else {
				throw new NoUniqueUserFound("Phoneno");
			}
		}

		if (flag == false) {
			mtemp.save(s);
			return "Updated";
		}

		return "checkme";
	}

	@Override
	public List<Student> showByBranch(Branch branch) {

		Query query = new Query();
		query.addCriteria(Criteria.where("branch").is(branch));
		List<Student> sortedByBranch = mtemp.find(query, Student.class);

		if (sortedByBranch == null) {
			throw new NoUniqueUserFound("No related Student Found");
		} else {
			Collections.sort(sortedByBranch,new SortingByRollno());
			return sortedByBranch;
		}
	}

	@Override
	public List<Student> showByGender(Gender gender) {
		Query query = new Query();
		query.addCriteria(Criteria.where("gender").is(gender));
		List<Student> sortedByGender = mtemp.find(query, Student.class);

		if (sortedByGender == null) {
			throw new NoUniqueUserFound("No related Student Found");
		} else {
			Collections.sort(sortedByGender,new SortingByRollno() );
			return sortedByGender;
		}
	}
}

class SortingByRollno implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		if(o1.getRollNo()>o2.getRollNo())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
}