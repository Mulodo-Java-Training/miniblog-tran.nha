package miniblog.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniblog.DAO.PostDAO;
import miniblog.model.Post;
import miniblog.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAOImpl;
	int statusNumber;

	public List<Post> getAll() {
		return postDAOImpl.getAll();
	}

	public List<Post> getPostForUser(int authorID) {
		if(null == postDAOImpl.getAllByAuthor(authorID)){
			statusNumber = 3003;
			return postDAOImpl.getAllByAuthor(authorID);
		}
		return postDAOImpl.getAllByAuthor(authorID);
	}

	public List<Post> searchPost(String searchString) {
		return postDAOImpl.searchPost(searchString);
	}

	public boolean createNewPost(Post post) {
		boolean valid = validateInput(post.getAuthor().getId(),
				post.getTitle(), post.getContent(), post.getStatus());
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		} else {
			postDAOImpl.createNewPost(post);
			return true;
		}
	}

	public boolean updatePost(int postID, Post post) {
		boolean valid = validateInput(post.getTitle(), post.getContent());
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		} else {
			postDAOImpl.updatePost(postID, post);
			return true;
		}
	}

	public boolean deletePost(int postID) {
		if (!checkIsExist(postID)) {
			this.statusNumber = 3001;
			return false;
		}
		if (!checkIsDelete(postID)) {
			this.statusNumber = 3002;
			return false;
		} else {
			postDAOImpl.deletePost(postID);
			return true;
		}
	}

	public boolean changeStatus(int postID) {
		if(checkIsExist(postID)){
			postDAOImpl.changeStatus(postID);
			return true;
		}
		else{
			this.statusNumber = 3001;
			return false;
		}
	}

	public Post getPost(int postID) {
		if(null == postDAOImpl.getPost(postID)){
			this.statusNumber = 3001;
		}
		return postDAOImpl.getPost(postID);
	}

	public int getStatusNumber() {
		return this.statusNumber;
	}

	
	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/
	
	private boolean validateInput(int authorID, String title, String content,
			String status) {
		if (authorID == 0 || null == title || null == content || null == status) {
			return false;
		}
		if (title.length() > 100) {
			return false;
		}
		if (content.length() > 2000) {
			return false;
		}
		if (!(!status.equalsIgnoreCase("Available") || !status.equalsIgnoreCase("Delete"))) {
			return false;
		}
		return true;
	}
	
	private boolean validateInput(String title ,String content) {
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

	private boolean checkIsDelete(int postID) {
		return postDAOImpl.isDelete(postID);
	}

	private boolean checkIsExist(int postID) {
		return postDAOImpl.isExist(postID);
	}
}
