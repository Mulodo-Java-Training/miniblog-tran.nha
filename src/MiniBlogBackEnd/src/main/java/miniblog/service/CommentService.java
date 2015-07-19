package miniblog.service;

import java.util.List;

import miniblog.model.Comment;

public interface CommentService {
	
	public boolean createNewComment(Comment comment);
	
	public boolean editComment(int commentID , Comment newInfo);
	
	public boolean deleteComment(int commentID);
	
	public boolean changeStatus(int commentID);
	
	public List<Comment> getCommentsForPost(int postID);

	public List<Comment> getCommentsForUser(int userID);
	
	public int getStatusNumber();
}
