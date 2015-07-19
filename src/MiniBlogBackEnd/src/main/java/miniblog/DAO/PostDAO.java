package miniblog.DAO;

import java.util.List;

import miniblog.model.Post;

public interface PostDAO {
	
	public void createNewPost(Post post);
	
	public List<Post> getAll();
	
	public List<Post> getAllByAuthor(int authorID);
	
	public List<Post> searchPost(String searchString);
	
	public Post getPost(int postID);
	
	public void updatePost(int postID, Post newinfo);
	
	public void deletePost(int postID);
	
	public void changeStatus(int postID);
	
	public boolean isDelete(int postID);
	
	public boolean isExist(int postID);
}
