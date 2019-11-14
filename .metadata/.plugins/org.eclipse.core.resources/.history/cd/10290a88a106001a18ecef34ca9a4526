package com.StudentClient.beans;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;

public class Assessment {

	@Id
	@Null
	private String assessmentId;
	
	@NotNull(message="studentId must not be NULL")
	private String studentId;

	@Min(value=0, message="Unit test marks should be greather or equal to 0")
	@Max(value=10, message="Unit test marks should be less or equal to 10")
	private Integer unitTest = 0;
	
	@Min(value=0, message="MidTerm test marks should be greather or equal to 0")
	@Max(value=10, message="Unit test marks should be less or equal to 10")
	private Integer midTermTest = 0;
	
	@Min(value=0, message="Final test marks should be greather or equal to 0")
	@Max(value=10, message="Unit test marks should be less or equal to 10")
	private Integer finalTest = 0;
	
	@Null
	private Grade grade;
	
	public Assessment() {
		super();
	}

	public Assessment(
			@NotNull(message = "studentId must not be NULL") String studentId,
			Integer unitTest, Integer midTermTest, Integer finalTest) {
		super();
		this.studentId = studentId;
		this.unitTest = unitTest;
		this.midTermTest = midTermTest;
		this.finalTest = finalTest;
	}

	public Assessment(@Null String assessmentId,
			@NotNull(message = "studentId must not be NULL") String studentId,
			Integer unitTest, Integer midTermTest, Integer finalTest,
			Grade grade) {
		super();
		this.assessmentId = assessmentId;
		this.studentId = studentId;
		this.unitTest = unitTest;
		this.midTermTest = midTermTest;
		this.finalTest = finalTest;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", studentId="
				+ studentId + ", unitTest=" + unitTest + ", midTermTest="
				+ midTermTest + ", finalTest=" + finalTest + ", grade=" + grade
				+ "]";
	}

	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public Integer getUnitTest() {
		return unitTest;
	}
	public void setUnitTest(Integer unitTest) {
		this.unitTest = unitTest;
	}
	
	public Integer getMidTermTest() {
		return midTermTest;
	}
	public void setMidTermTest(Integer midTermTest) {
		this.midTermTest = midTermTest;
	}
	
	public Integer getFinalTest() {
		return finalTest;
	}
	public void setFinalTest(Integer finalTest) {
		this.finalTest = finalTest;
	}
	
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
