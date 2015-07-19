package miniblog.DAO;

import java.util.List;

import miniblog.model.UserBlog;

public interface UserBlogDAO {
	
	public void createNewUser(UserBlog user);

	public List<UserBlog> getAll();
	
	public List<UserBlog> searchUserByName(String username);
	
	public UserBlog getInfo (int id);

	public void deleteUser(int userID);
	
	public void deleteUser(String userName);
	
	public void updateUserInfo(int userID, UserBlog newInfo);
	
	public void changePassword(int userID, String newpass);
	
	public UserBlog getUser(String username);

	public boolean check(String username);
	
	public boolean check(int userID,String password);
	
	public boolean check(String username,String password);
	
}
