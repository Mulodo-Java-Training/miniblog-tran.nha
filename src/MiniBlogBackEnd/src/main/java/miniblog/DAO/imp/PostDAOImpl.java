package miniblog.DAO.imp;

import java.util.Date;
import java.util.List;

import miniblog.DAO.PostDAO;
import miniblog.model.Post;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void createNewPost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(post);
		tx.commit();
	}

	public List<Post> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Post> posts = session.createQuery("FROM Post").list();
		tx.commit();
		return checkNullListResult(posts);
	}

	public List<Post> getAllByAuthor(int authorID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Post> posts = session
				.createQuery("FROM Post P WHERE P.Author.id = :authorID ORDER BY P.Modify_date DESC")
				.setParameter("authorID", authorID).list();
		tx.commit();
		return checkNullListResult(posts);
	}
	
	public List<Post> searchPost(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery("SELECT post.id, post.Author, post.Title, post.Content, "
				+ "post.Create_date, post.Modify_date. post.Status "
				+ "FROM miniblog.post WHERE MATCH(Title,Content) AGAINST(:searchString) "
				+ "ORDER BY post.Modify_date DESC")
				.setParameter("searchString", searchString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Post> posts = query.list();
		tx.commit();
		return checkNullListResult(posts);
	}

	public Post getPost(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.get(Post.class, postID);
		return post;
	}

	public void updatePost(int postID, Post newinfo) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.load(Post.class, postID);
		post.setContent(newinfo.getContent());
		post.setTitle(newinfo.getTitle());
		post.setModify_date(new Date());
		session.update(post);
		tx.commit();
	}

	public void deletePost(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.load(Post.class, postID);
		session.delete(post);
		tx.commit();
	}

	public void changeStatus(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.get(Post.class, postID);
		if (post.getStatus().equals("Available")) {
			post.setStatus("Delete");
		} else if (post.getStatus().equals("Delete")){
			post.setStatus("Available");
		}
		post.setModify_date(new Date());
		tx.commit();
	}

	public boolean isDelete(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.get(Post.class, postID);
		if (post.getStatus().equalsIgnoreCase("Delete")) {
			return true;
		}
		return false;
	}
	
	public boolean isExist(int postID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Post post = (Post) session.get(Post.class, postID);
		if (null != post) {
			return true;
		}
		return false;
	}
	
	private List<Post> checkNullListResult(List<Post> posts){
		if(posts.size()<1){
			return null;
		}
		return posts;
	}
	

}
