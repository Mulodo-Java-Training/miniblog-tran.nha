package miniblog.DAO;

import java.util.List;

import miniblog.model.Comment;

public interface CommentDAO {
	
	public void createNewComment(Comment comment);
	
	public void editComment(int commentID , Comment newInfo);
	
	public void deleteComment(int commentID);
	
	public void changeStatus(int commentID);
	
	public List<Comment> getCommentsForPost(int postID);
	
	public List<Comment> getCommentsForUser(int userID);
	
	public boolean isExist(int commentID);
	
	public boolean isDelete(int commentID);
}
