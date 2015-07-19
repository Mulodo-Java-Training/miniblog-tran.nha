package miniblog.DAO.imp;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import miniblog.DAO.CommentDAO;
import miniblog.model.Comment;
import miniblog.model.Post;

@Repository
public class CommentDAOImpl implements CommentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public void createNewComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(comment);
		tx.commit();
	}

	public void editComment(int commentID, Comment newInfo) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment)session.load(Comment.class,commentID);
		comment.setContent(newInfo.getContent());
		comment.setModify_date(new Date());
		session.update(comment);
		tx.commit();
	}

	public void deleteComment(int commentID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment)session.load(Comment.class, commentID);
		session.delete(comment);
		tx.commit();
	}

	public void changeStatus(int commentID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment)session.load(Comment.class, commentID);
		if (comment.getStatus().equals("Available")) {
			comment.setStatus("Delete");
		} else if (comment.getStatus().equals("Delete")){
			comment.setStatus("Available");
		}
		comment.setModify_date(new Date());
		tx.commit();
	}

	public List<Comment> getCommentsForPost(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Comment> comments = null;
		comments = session.createQuery("FROM Comment C WHERE C.Post_id.id = :postID")
				.setParameter("postID", postID).list();
		tx.commit();
		return checkNullListResult(comments);
	}

	public List<Comment> getCommentsForUser(int userID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Comment> comments = session.createQuery("FROM Comment C WHERE C.Author_id.id = :userID")
				.setParameter("userID",userID).list();
		tx.commit();
		return checkNullListResult(comments);
	}

	public boolean isExist(int commentID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment) session.get(Comment.class, commentID);
		if (null != comment) {
			return true;
		}
		return false;
	}

	public boolean isDelete(int commentID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Comment comment = (Comment) session.get(Comment.class, commentID);
		if (comment.getStatus().equalsIgnoreCase("Delete")) {
			return true;
		}
		return false;
	}
	
	private List<Comment> checkNullListResult(List<Comment> comments){
		if(comments.size()<1){
			return null;
		}
		return comments;
	}

}
