package systemUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import offerings.CourseOffering;
import offerings.ICourseOffering;

public class StudentModel implements IStudentModel{
	
	private String name;
	private String surname;
	private String ID;
	private List<ICourseOffering> coursesAllowed;
	private List<ICourseOffering> coursesEnrolled;
	private Map<ICourseOffering, EvaluationTypes> evaluationEntities;
//	check the comments at the Marks Class this map should contain as many pairs of <CourseOffering, Marks> as course that 
//	the student has enrolled in.
	private Map<ICourseOffering, Marks> perCourseMarks;
	private NotificationTypes notificationType = NotificationTypes.EMAIL;
//	0 for off, 1 for on
	private int notificationStatus;
	
	private String email;
	private String phoneno;
	private String pig_post;
	
	public String setEmail(String email) {
		this.email = email;
		return this.email;
	}
	public String setPhone(String phone) {
		phoneno = phone;
		return phoneno;
	}
	public String setPigPost(String post) {
		pig_post = post;
		return pig_post;
	}
	public void setNotifPref(NotificationTypes select) {
		notificationType = select;
	}
	public int getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(int notificationStatus) {
		this.notificationStatus = notificationStatus;
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
	
	public List<ICourseOffering> getCoursesAllowed() {
		return coursesAllowed;
	}
	
	public void setCoursesAllowed(List<ICourseOffering> coursesAllowed) {
		this.coursesAllowed = coursesAllowed;
	}
	
	public List<ICourseOffering> getCoursesEnrolled() {
		return coursesEnrolled;
	}
	
	public void setCoursesEnrolled(List<ICourseOffering> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}
	
	public Map<ICourseOffering, EvaluationTypes> getEvaluationEntities() {
		return evaluationEntities;
	}
	
	public void setEvaluationEntities(Map<ICourseOffering, EvaluationTypes> evaluationEntities) {
		this.evaluationEntities = evaluationEntities;
	}
	
	public Map<ICourseOffering, Marks> getPerCourseMarks() {
		return perCourseMarks;
	}
	
	public void setPerCourseMarks(Map<ICourseOffering, Marks> perCourseMarks) {
		this.perCourseMarks = perCourseMarks;
	}
	
	public NotificationTypes getNotificationType() {
		return notificationType;
	}
	
	public void setNotificationType(NotificationTypes notificationType) {
		this.notificationType = notificationType;
	}
	
	public boolean enroll(CourseOffering course) {
		List<StudentModel> enrolledStudents = course.getStudentsEnrolled();
		if(checkIfStudentCanTakeCourse(course)) {
			if (coursesEnrolled == null)
				coursesEnrolled = new ArrayList<>();
			enrolledStudents.add(this);
			coursesEnrolled.add(course);
			putPerCourseMarks(course);
			return true;
		}
		return false;
	}
	
	public boolean checkIfStudentCanTakeCourse(CourseOffering course) {
		List<StudentModel> studentsAllowedToEnroll = course.getStudentsAllowedToEnroll();
		
		if(studentsAllowedToEnroll.contains(this)) {
			if(coursesAllowed.contains(course))
				return true;
		}
		return false;
	}
	
	public boolean checkEnrolled(String courseID) {
		for(ICourseOffering course: getCoursesEnrolled()) {
			if (course.getCourseID().equals(courseID)) {
				return true;
			}
		}
		return false;
	}
	
	public void putPerCourseMarks(CourseOffering course) {
		if(perCourseMarks == null) {
			Map<ICourseOffering, Marks> courseMark = new HashMap<ICourseOffering, Marks>();
			Marks mark = new Marks();
			courseMark.put(course, mark);
			this.setPerCourseMarks(courseMark);
		}
		else {
			Marks mark = new Marks();
			perCourseMarks.put(course, mark);
		}
	}
}