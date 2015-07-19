package frontend.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frontend.DAO.UserDAO;
import frontend.model.User;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	String messageError;
	User user;
	
	public boolean registerUser(User user){
		boolean valid = validateInput(user.getUsername(), user.getPassword(), user.getEmail(), 
				user.getLastname(), user.getFirstname(), user.getBirthday(), user.getGender());
		if(!valid){
			this.messageError = "Validation Error!!!";
			return false;
		}
		switch (userDAO.registerUser(user)){
		case 1001:	
			this.messageError = "Validation Error!!!";
			return false;
		case 2001:	
			this.messageError = "Username has already exist!!!";
			return false;
		case 200:
			return true;
		}
		return false;
	}
	
	public boolean changePassword(int userID, String password , String newpassword){
		if(!validateInput(password, newpassword)){
			this.messageError = "Validation Error!!!";
			return false;
		}
		if(userDAO.changePassword(userID, password, newpassword) == 200){
			return true;
		}else if(userDAO.changePassword(userID, password, newpassword) == 2003){
			this.messageError = "Wrong Password!!!";
			return false;
		}else {
			return false;
		}
	}
	
	public boolean updateUser(User user){
		boolean valid = validateInput(user.getUsername(), user.getPassword(), user.getEmail(), 
				user.getLastname(), user.getFirstname(), user.getBirthday(), user.getGender());
		if(!valid){
			this.messageError = "Validation Error!!!";
			return false;
		}
		if(userDAO.updateUser(user) == 200){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean loginUser(String username, String password) throws Exception{
		if(!validateInput(username, password)){
			return false;
		}
		if(userDAO.loginUser(username, password)== 200){
			this.user = userDAO.getUser();
			return true;
		}else if(userDAO.loginUser(username, password) == 2003){
			this.messageError = "Wrong Password!!!";
			return false;
		}else{
			return false;
		}
	}

	public User getUserInfo(int userID) throws Exception{
		if(null!= userDAO.gettUserInfo(userID)){
			return userDAO.gettUserInfo(userID);
		}
		return null;
	}
	
	public List<User> searchUser(String searchName) throws JsonParseException, JsonMappingException, IOException{
		return userDAO.searchUserByName(searchName);
	}	
	
	public User getInfo(){
		return this.user;
	}
	
	public String getMessageError(){
		return this.messageError;
	} 
	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/
	
	private boolean validateInput(String username, String password, String email, 
			String lastname, String firstname, String birthday, String gender){
		if (null == username || null == email || null == lastname
				|| null == firstname || null == birthday || null == gender) {
			return false;
		}
		if (!lastname.matches("^[ A-z]+") || !firstname.matches("^[ A-z]+")
				|| lastname.length() > 40 || firstname.length() > 40) {
			return false;
		}
		if (!username.matches("^[A-z0-9]+$") || username.length() > 40) {
			return false;
		}
		if (password.length() > 40) {
			return false;
		}
		if (!email
				.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
				|| email.length() > 50) {
			return false;
		}
		if (!gender.matches("[a-zA-Z]+")) {
			return false;
		}
		if (!birthday
				.matches("(0?[1-9]|1[012])-([12][0-9]|3[01]|0?[1-9])-((?:19|20)\\d\\d)")) { /* Formating inputted String
																							 * must be mm-dd-yyyy */
			return false;
		}
		return true;
	}
	private boolean validateInput(String password, String newPassword) {
		if (null == password || password.length() > 40 || null == newPassword
				|| newPassword.length() > 40) {
			return false;
		}
		return true;
	}
}
