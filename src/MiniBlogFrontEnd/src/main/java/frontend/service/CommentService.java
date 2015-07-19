package frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frontend.DAO.CommentDAO;
import frontend.DAOForm.CommentForm;
import frontend.model.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;
	String messageError;
	
	public boolean createNewComment(CommentForm commentdata){
		if(!validateInput(commentdata.getContent())){
			this.messageError = "Validation Error!!!";
			return false;
		}
		if(commentDAO.createNewComment(commentdata)==200){
			return true;
		}
		return false;
	}
	public boolean editComment(int commentid, String content){
		if(!validateInput(content)){
			this.messageError = "Validation Error!!!";
			return false;
		}
		if(commentDAO.editComment(commentid, content)==200){
			return true;
		}
		return false;
	}
	
	public boolean deleteComment(int commentID){
		switch(commentDAO.deleteComment(commentID)){
			case 4001 : 
				this.messageError = "Comment is not exist!!!";
				return false;
			case 4002 : 
				this.messageError = "Comment must be delete status!!!";
				return false;
			case 200 : 
				return true;
		}
		return false;
	}
	
	public boolean changeStatus(int commentID){
		if(commentDAO.changeStatus(commentID)==200){
			return true;
		}
		else if(commentDAO.changeStatus(commentID) == 4001){
			this.messageError = "Comment is not exist!!!";
			return false;
		}
		return false;
	}
	
	public List<Comment> getCommentForPost(int postID){
		try{
			return commentDAO.getCommentForPost(postID);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Comment> getCommentForUser(int userID){
		try{
			return commentDAO.getCommentForUser(userID);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getMessageError(){
		return this.messageError;
	}
	
	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/
	
	private boolean validateInput(String content) {
		if (null == content) {
			return false;
		}
		if (content.length() > 500) {
			return false;
		}
		return true;
	}
}
