package loggedInUserFactory;

import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInAuthenticatedUser;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticationServer.AuthenticationToken;

public class LoggedInUserFactory {

	public LoggedInUserFactory(){
		
	}
	
	public LoggedInAuthenticatedUser createAuthenticatedUser(AuthenticationToken authenticationToken){
		switch(authenticationToken.getUserType()){
		case "Admin":
			return createLoggedInAdmin(authenticationToken);
		case "Student":
			return createLoggedInStudent(authenticationToken);
		case "Instructor":
			return createLoggedInInstructor(authenticationToken);
		default:
			return null;
		}
	}
	
	public LoggedInStudent createLoggedInStudent(AuthenticationToken authenticationToken){
//		TODO add object creation logic
		LoggedInStudent student = new LoggedInStudent();
		student.setAuthenticationToken(authenticationToken);
		return student;
	}
	
	public LoggedInAdmin createLoggedInAdmin(AuthenticationToken authenticationToken){
//		TODO add object creation logic
		LoggedInAdmin admin = new LoggedInAdmin();
		admin.setAuthenticationToken(authenticationToken);
		return admin;
	}
	
	public LoggedInInstructor createLoggedInInstructor(AuthenticationToken authenticationToken){
//		TODO add object creation logic 
		LoggedInInstructor instructor = new LoggedInInstructor();
		instructor.setAuthenticationToken(authenticationToken);
		return instructor;
	}
}
