package miniblog.service;

import java.util.List;

import miniblog.model.Post;

public interface PostService {
	
	public List<Post> getAll();
	
	public List<Post> getPostForUser(int authorID);
	
	public List<Post> searchPost(String searchString);
	
	public boolean createNewPost(Post post);
	
	public boolean updatePost(int postID, Post post);
	
	public boolean deletePost(int postID);
	
	public boolean changeStatus(int postID);
	
	public Post getPost(int postID);
	
	public int getStatusNumber();
}
