package authenticatedUsers;

import java.io.BufferedReader;

import authenticationServer.AuthenticationToken;
import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import registrar.ModelRegister;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;

public class LoggedInInstructor implements LoggedInAuthenticatedUser {

	private String name;
	private String surname;
	private String ID;
	private AuthenticationToken authenticationToken;
	

	public LoggedInInstructor() {
		this.name = "";
		this.surname = "";
		this.ID = "";
		this.authenticationToken = null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public AuthenticationToken getAuthenticationToken() {
		return authenticationToken;
	}
	
	public void setAuthenticationToken(AuthenticationToken authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	
	public void addMark(String SID, String CID, String eval, Double newMark) {
		StudentModel target = null;
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(CID);
		for (StudentModel studentModel : course.getStudentsEnrolled()) { // for studentModel in CourseOffering List of students enrolled
			System.out.println(studentModel.getID());
			if (studentModel.getID().equals(SID)) {
				target = studentModel;
			}
		}
		Marks marks = target.getPerCourseMarks().get(course); // get Marks matched with key (CourseOffering)
		marks.addToEvalStrategy(eval, newMark);
		System.out.println("Mark added: "+target.getPerCourseMarks().get(course).getValueWithKey(eval));
		System.out.println("Notification via "+target.getNotificationType()+" sent!");
	}
	
	public void modifyMark(String SID, String CID, String eval, Double newMark) {
		StudentModel target = null;
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(CID);
		for (StudentModel studentModel : course.getStudentsEnrolled()) { // for studentModel in CourseOffering List of students enrolled
			System.out.println(studentModel.getID());
			if (studentModel.getID().equals(SID)) {
				target = studentModel;
			}
		}
		Marks marks = target.getPerCourseMarks().get(course); // get Marks matched with key (CourseOffering)
		marks.addToEvalStrategy(eval, newMark);
		System.out.println("Mark added: "+target.getPerCourseMarks().get(course).getValueWithKey(eval));
		System.out.println("Notificiation via "+target.getNotificationType()+" sent!");
	}
	
	public double calcFinalGrade(String SID, String CID) {
		StudentModel target = null;
		Double finalGrade;
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(CID);
		for(StudentModel studentModel : course.getStudentsEnrolled()) // for studentModel in CourseOffering List of students enrolled
			if (studentModel.getID().equals(SID)) 
				target = studentModel; // if student ID is found, set target to that student
		finalGrade = 0D;
		Weights weights = course.getEvaluationStrategies().get(target.getEvaluationEntities().get(course)); // taken from CourseOffering code
		Marks marks  = target.getPerCourseMarks().get(course);
		weights.initializeIterator();
		while(weights.hasNext()){
			weights.next();
			System.out.println("W:"+weights.getCurrentValue());
			System.out.println("M:"+marks.getValueWithKey(weights.getCurrentKey()));
			finalGrade += weights.getCurrentValue() * marks.getValueWithKey(weights.getCurrentKey());
		}
		return finalGrade;
	}
	
	public void printClassRecord(String SID, String CID) {
		StudentModel target = null;
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(CID);
		for(StudentModel studentModel : course.getStudentsEnrolled()) // for studentModel in CourseOffering List of students enrolled
			if (studentModel.getID().equals(ID)) 
				target = studentModel; // if student ID is found, set target to that student
		System.out.println("Full Name: "+target.getSurname()+", "+target.getName());
		System.out.println("SID: "+target.getID());
		Double fin = calcFinalGrade(SID, CID);
		System.out.println("Final mark: "+fin);
	}
}
