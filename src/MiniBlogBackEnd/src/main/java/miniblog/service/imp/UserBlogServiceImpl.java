package miniblog.service.imp;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniblog.DAO.UserBlogDAO;
import miniblog.model.UserBlog;
import miniblog.service.UserBlogService;

@Service
public class UserBlogServiceImpl implements UserBlogService {
	@Autowired
	UserBlogDAO userBlogDAOImpl;
	UserBlog user;
	int statusNumber = 200;

	public List<UserBlog> getAll() {

		return userBlogDAOImpl.getAll();
	}

	public List<UserBlog> searchUser(String searchUser) {
		if (!validateInput(searchUser)) {
			this.statusNumber = 1001;
			return null;
		} else if (null == userBlogDAOImpl.searchUserByName(searchUser)) {
			this.statusNumber = 2004;
			return null;
		} else {
			return userBlogDAOImpl.searchUserByName(searchUser);
		}
	}

	public boolean createNewUser(UserBlog user) {
		boolean valid = validateInput(user.getUsername(), user.getPassword(),
				user.getEmail(), user.getLastname(), user.getFirstname(),
				user.getBirthday(), user.getGender(), user.getCreate_date(),
				user.getModify_date());
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		} else if (!checkUserExist(user.getUsername())) {

			this.statusNumber = 2001;
			return false;
		} else {
			userBlogDAOImpl.createNewUser(user);
			return true;
		}
	}

	public boolean deleteUser(int userID) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteUser(String username){
		if(!validateInput(username)){
			return false;
		}else{
			userBlogDAOImpl.deleteUser(username);
			return true;
		}
	}

	public boolean updateUser(int userID, UserBlog user) {
		boolean valid = validateInput(user.getUsername(), user.getPassword(),
				user.getEmail(), user.getLastname(), user.getFirstname(),
				user.getBirthday(), user.getGender(), user.getCreate_date(),
				user.getModify_date());
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		} else {
			userBlogDAOImpl.updateUserInfo(userID, user);
			return true;
		}
	}

	public UserBlog findBy(int id) {
		return userBlogDAOImpl.getInfo(id);
	}

	public boolean loginUser(String user, String password) {
		boolean valid = validateInput(user, password);
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		}
		if (!userBlogDAOImpl.check(user, password)) {
			this.statusNumber = 2003;
			return false;
		} else {
			this.user = userBlogDAOImpl.getUser(user);
			return true;
		}
	}

	public boolean changePassword(int id, String password, String newPassword) {
		if (!validateInput(password, newPassword)) {
			this.statusNumber = 1001;
			return false;
		}
		if (!checkPassword(id, password)) {
			this.statusNumber = 2003;
			return false;
		} else {
			userBlogDAOImpl.changePassword(id, newPassword);
			return true;
		}
	}
	
	public UserBlog getUserBlog(){
		return this.user;
	}

	public int getStatusNumber() {
		return this.statusNumber;
	}

	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/
	
	private boolean checkUserExist(String usernameinput) {
		return userBlogDAOImpl.check(usernameinput);
	}
	
	private boolean validateInput(String username, String password,
			String email, String lastname, String firstname, String birthday,
			String gender, Date createDate, Date modifyDate) {
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

	private boolean validateInput(String searchString) {
		if (null == searchString || searchString.length() > 40) {
			return false;
		}
		return true;
	}

	private boolean checkPassword(int id, String password) {
		return userBlogDAOImpl.check(id, password);
	}

}
