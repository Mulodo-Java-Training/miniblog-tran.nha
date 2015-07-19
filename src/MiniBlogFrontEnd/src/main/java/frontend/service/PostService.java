package frontend.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import frontend.DAO.PostDAO;
import frontend.DAOForm.PostForm;
import frontend.model.Post;

@Service
public class PostService {
	@Autowired
	PostDAO postDAO;
	String messageError;

	public boolean createNewPost(PostForm post) {
		boolean valid = validateInput(post.getTitle(), post.getContent());
		if (!valid) {
			this.messageError = "Validation Error!!!";
			return false;
		}
		if (postDAO.createNewPost(post) == 200) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean changeStatus(int postID){
		if(postDAO.changeStatus(postID) == 200){
			return true;
		}else{
			this.messageError = "This Post is not exist!!!";
			return false;
		}
	}
	
	public boolean editPost(int postid, String title, String content){
		if(!validateInput(title, content)){
			this.messageError = "Validation Error!!!";
			return false;
		}
		if(postDAO.editPost(postid,title,content) == 200){
			return true;
		}
		return false;
	}
	
	public boolean detelePost(int postID){
		switch (postDAO.deletePost(postID)){
			case 3001: 
				this.messageError = "This Post is not exist!!!";
				return false;
			case 3002: 
				this.messageError = "This Post must be Delete status!!!";
				return false;
			case 200: 
				return true;
		}
		return false;
	}
	
	public List<Post> getAllPost() {
		try {
			return postDAO.getAllPost();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Post> getPostForUser(int userID){
		try{
			return postDAO.getPostForUser(userID);
		} catch(Exception e){
			return null;
		}
	}
	
	public Post getPostInfo(int postID){
		try {
			return postDAO.getPostInfo(postID);
		} catch(Exception e){
			return null;
		}
	}
	
	public String getMessageError(){
		return this.messageError;
	}
	
	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/

	private void swicth(int deletePost) {
		// TODO Auto-generated method stub
		
	}
	private boolean validateInput(String title, String content) {
		if (null == title || null == content) {
			return false;
		}
		if (title.length() > 100) {
			return false;
		}
		if (content.length() > 2000) {
			return false;
		}
		return true;
	}
}
