package unittest.controller;

import static org.junit.Assert.*;
import miniblog.controller.CommentController;
import miniblog.controller.PostController;
import miniblog.controller.UserBlogController;
import miniblog.modelform.CommentForm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class CommentControllerTest {
	
	@Autowired
	CommentController commentcontroller;
	@Autowired
	PostController postcontroller;
	@Autowired
	UserBlogController usercontroller;
	@Test
	public void createNewComment_code200() {
		usercontroller.loginUser("admin", "111222");
		postcontroller.getPostInfo(5);
		CommentForm data = new CommentForm();
		data.setContent("Me too.");
		assertEquals(200, commentcontroller.createNewComment(data).getStatus());
	}
	
	@Test
	public void createNewComment_code1001() {
		usercontroller.loginUser("admin", "111222");
		postcontroller.getPostInfo(5);
		CommentForm data = new CommentForm();
		data.setContent(null);
		assertEquals(1001, commentcontroller.createNewComment(data).getStatus());
	}
	
	@Test
	public void editComment_code200(){
		CommentForm data = new CommentForm();
		data.setId(1);
		data.setContent("Java has jse, jme and jee.");
		assertEquals(200, commentcontroller.editComment(data.getId(), data.getContent()).getStatus());
	}
	
	@Test
	public void editComment_code1001(){
		CommentForm data = new CommentForm();
		data.setId(1);
		data.setContent(null);
		assertEquals(1001, commentcontroller.editComment(data.getId(), data.getContent()).getStatus());
	}
	
//	@Test
//	public void delete_code200(){
//		assertEquals(200, commentcontroller.deleteComment(1).getStatus());
//	}
	
	@Test
	public void delete_code4001(){
		assertEquals(4001, commentcontroller.deleteComment(0).getStatus());
	}
	
	@Test
	public void delete_code4002(){
		assertEquals(4002, commentcontroller.deleteComment(2).getStatus());
	}
	
	@Test
	public void getCommentForPost_code200(){
		assertEquals(200, commentcontroller.getCommentForPost(3).getStatus());
	}
	
	@Test
	public void getCommentForPost_code4003(){
		assertEquals(4003, commentcontroller.getCommentForPost(0).getStatus());
	}
	
	@Test
	public void getCommentForUser_code200(){
		assertEquals(200, commentcontroller.getCommentForUser(15).getStatus());
	}
	
	@Test
	public void getCommentForUser_code4003(){
		assertEquals(4003, commentcontroller.getCommentForUser(0).getStatus());
	}
}
