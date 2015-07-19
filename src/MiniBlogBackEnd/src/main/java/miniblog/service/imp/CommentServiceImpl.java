package miniblog.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniblog.DAO.CommentDAO;
import miniblog.model.Comment;
import miniblog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAOImpl;
	int statusNumber;

	public boolean createNewComment(Comment comment) {
		boolean valid = validateInput(comment.getAuthor_id().getId(), comment
				.getPost_id().getId(), comment.getContent(),
				comment.getStatus());
		if (!valid) {
			this.statusNumber = 1001;
			return false;
		} else {
			commentDAOImpl.createNewComment(comment);
			return true;
		}
	}

	public boolean editComment(int commentID, Comment newInfo) {
		if (!validateInput(newInfo.getContent())) {
			this.statusNumber = 1001;
			return false;
		} else {
			commentDAOImpl.editComment(commentID, newInfo);
			return true;
		}
	}

	public boolean deleteComment(int commentID) {
		if (!checkIsExist(commentID)) {
			this.statusNumber = 4001;
			return false;
		}
		if (!checkIsDelete(commentID)) {
			this.statusNumber = 4002;
			return false;
		} else {
			commentDAOImpl.deleteComment(commentID);
			return true;
		}
	}

	public boolean changeStatus(int commentID) {
		if (!checkIsExist(commentID)) {
			this.statusNumber = 4001;
			return false;
		} else {
			commentDAOImpl.changeStatus(commentID);
			return true;
		}
	}

	public List<Comment> getCommentsForPost(int postID) {
		if(null == commentDAOImpl.getCommentsForPost(postID)){
			this.statusNumber = 4003;
			return null;
		}else{
			return commentDAOImpl.getCommentsForPost(postID);
		}
		
	}

	public List<Comment> getCommentsForUser(int userID) {
		if(null == commentDAOImpl.getCommentsForUser(userID)){
			this.statusNumber = 4003;
			return null;
		}else{
			return commentDAOImpl.getCommentsForUser(userID);
		}
	}

	public int getStatusNumber() {
		return this.statusNumber;
	}

	/*--------------------------------------------------------------------
	 --------------------------- Private Area ----------------------------
	 ---------------------------------------------------------------------*/

	private boolean validateInput(int authorID, int postID, String content,
			String status) {
		if (authorID == 0 || postID == 0 || null == content || null == status) {
			return false;
		}
		if (content.length() > 500) {
			return false;
		}
		if (!(!status.equalsIgnoreCase("Available") || !status
				.equalsIgnoreCase("Delete"))) {
			return false;
		}
		return true;
	}

	private boolean validateInput(String content) {
		if (null == content) {
			return false;
		}
		if (content.length() > 500) {
			return false;
		}
		return true;
	}

	private boolean checkIsDelete(int commentID) {
		return commentDAOImpl.isDelete(commentID);
	}

	private boolean checkIsExist(int commentID) {
		return commentDAOImpl.isExist(commentID);
	}
}
