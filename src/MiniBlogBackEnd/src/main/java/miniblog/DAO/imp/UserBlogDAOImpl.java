package miniblog.DAO.imp;

import java.util.List;

import org.apache.log4j.pattern.FullLocationPatternConverter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import miniblog.DAO.UserBlogDAO;
import miniblog.model.UserBlog;

;
@Repository
public class UserBlogDAOImpl implements UserBlogDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createNewUser(UserBlog user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}

	public List<UserBlog> getAll() {
		Session session = this.sessionFactory.openSession();
		List<UserBlog> listUser = session.createQuery("from UserBlog").list();
		return listUser;
	}

	public List<UserBlog> searchUserByName(String searchString) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"SELECT userblog.id, userblog.Username, userblog.Firstname, userblog.Lastname , userblog.Gender "
								+ "FROM miniblog.userblog WHERE MATCH (Username,Firstname,Lastname) AGAINST (:searchString)")
				.setParameter("searchString", searchString)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (query.list().size()<1) {
			return null;
		}else {
			return query.list();
		}

	}

	public UserBlog getInfo(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserBlog user = (UserBlog) session.get(UserBlog.class, id);
		// tx.commit();
		return user;
	}

	public void deleteUser(int userID) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserBlog user = (UserBlog) session.get(UserBlog.class, userID);
		session.delete(user);
		tx.commit();
	}

	public void deleteUser(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(
				"DELETE FROM Users WHERE username=:username").setParameter(
				"username", userName);
		tx.commit();
	}

	public void updateUserInfo(int userID, UserBlog newInfo) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserBlog user = (UserBlog) session.load(UserBlog.class, userID);
		// user.setUsername(newInfo.getUsername());
		user.setLastname(newInfo.getLastname());
		user.setFirstname(newInfo.getFirstname());
		user.setEmail(newInfo.getEmail());
		user.setGender(newInfo.getGender());
		user.setBirthday(newInfo.getBirthday());
		user.setModify_date(newInfo.getModify_date());
		session.update(user);
		tx.commit();
	}

	public boolean check(String username) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("FROM UserBlog U WHERE U.Username = :username");
		query.setParameter("username", username);
		List<UserBlog> users = query.list();
		if (null == users) {
			return false;
		}
		return true;
	}

	public boolean check(int userID, String password) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserBlog userBlog = (UserBlog) session.load(UserBlog.class, userID);
		if (userBlog.getPassword().equalsIgnoreCase(password)) {
			return true;
		}
		return false;
	}

	public boolean check(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("FROM UserBlog U WHERE U.Username = :username AND U.Password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}

	/* Change/update password to database */
	public void changePassword(int userID, String newpass) {

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserBlog user = (UserBlog) session.load(UserBlog.class, userID);
		user.setPassword(newpass);
		session.update(user);
		tx.commit();
	}
}
