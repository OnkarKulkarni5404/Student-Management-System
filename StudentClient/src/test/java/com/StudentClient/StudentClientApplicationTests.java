package com.StudentClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.StudentClient.beans.Branch;
import com.StudentClient.beans.Gender;
import com.StudentClient.beans.Student;
import com.StudentClient.dao.NoUniqueUserFound;
import com.StudentClient.dao.StudentDaoImpl;
import com.StudentClient.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentClientApplicationTests {
	
	
	@InjectMocks
	StudentService service=new StudentService();
	
	@Mock
	StudentDaoImpl dao;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 	
	String result="Updated";
	Student student=new Student("5dcbbc8581f1e50d8430aff4",4,"Spider-okulkarni",Branch.CS,Gender.Male,"1234567894","sman@gmail.com","jalgaon");
	
	@Test
	public void testUpdateStudentReturnUpdated()
	{
		when(dao.updateStudent(student)).thenReturn(result);
		assertEquals(dao.updateStudent(student),"Updated");
	}
	
	
	Student student2=new Student("5dcbbc8581f1e50d8430aff4",4,"Spider-okulkarni",Branch.ME,Gender.Male,"1234567894","sman@gmail.com","jalgaon");
	@Test
	public void testUpdateStudentSameBranchAndRollNo()
	{
		NoUniqueUserFound obj = new NoUniqueUserFound("No uniqueness in branch and rollno");
		when(dao.updateStudent(student2)).thenThrow(new NoUniqueUserFound("No uniqueness in branch and rollno"));
		
		try{
			assertEquals(dao.updateStudent(student2), "Updated");
		}
		catch(Exception e){
			String expectedMsg="No uniqueness in branch and rollno";
			assertEquals(e.getMessage(),expectedMsg);
		}
	}

	
	Student student3=new Student("5dcbbc8581f1e50d8430aff4",4,"Spider-okulkarni",Branch.CS,Gender.Male,"9876534200","sman@gmail.com","jalgaon");
	@Test
	public void testUpdateStudentSamePhoneno()
	{
		when(dao.updateStudent(student3)).thenReturn("Updated");
		try{
			assertEquals(dao.updateStudent(student3),"Updated");
		}
		catch(Exception e){
			String expectedMsg="No uniqueness in branch and rollno";
			assertEquals(e.getMessage(),expectedMsg);
		}
	}
	
	Student student4=null;
	
	@Test
	public void testNullStudent()
	{
		when(dao.updateStudent(student4)).thenReturn("Updated");
		try{
			assertEquals(dao.updateStudent(student4),"Updated");
		}
		catch(Exception e){
			String expectedMsg="NoUser";
			assertEquals(e.getMessage(),expectedMsg);
		}
		
	}
	
	
}
