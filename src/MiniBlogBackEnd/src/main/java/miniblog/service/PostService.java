package miniblog.service;

import java.util.List;

import miniblog.model.Post;

public interface PostService {
	
	public List<Post> getAll();
	
	public void updatePost(int postID, Post post);
	
	public void deletePost(int postID);
	
	public void changeStatus(int postID);
	
	public List<Post> getAllByAuthor(int authorID);
	
	public boolean checkDelete(int post);
	
}
