package miniblog.service;

import java.util.List;

import miniblog.model.UserBlog;

public interface UserBlogService {
	
	public List<UserBlog> getAll();
	
	public List<UserBlog> searchUser(String searchUser);

	public boolean createNewUser(UserBlog user);

	public boolean deleteUser(int userID);

	public boolean updateUser(int userID,UserBlog user);

	public UserBlog findBy(int id);
	
	public boolean loginUser(String user, String password);
	
	public boolean changePassword(int id, String password, String newPassword);
	
	public int getStatusNumber();
}
