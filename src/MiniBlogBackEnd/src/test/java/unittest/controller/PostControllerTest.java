package unittest.controller;

import static org.junit.Assert.*;

import java.util.Date;

import miniblog.controller.PostController;
import miniblog.controller.UserBlogController;
import miniblog.model.Post;
import miniblog.modelform.PostForm;
import miniblog.service.UserBlogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import unittest.service.UserServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class PostControllerTest {
	
	@Autowired
	PostController postController;
	@Autowired
	UserBlogController userController;
	@Test
	public void createNewPost_code200() {
		userController.loginUser("admin", "111222");
		PostForm post = new PostForm();
		post.setTitle("Java and C Sharp");
		post.setContent("Java and C Sharp are OOP");
		assertEquals(200, postController.createNewPost(post).getStatus());
	}
	@Test
	public void createNewPost_code1001() {
		userController.loginUser("admin", "111222");
		PostForm post = new PostForm();
		post.setTitle("Java and C Sharp. Java and C Sharp. Java and C Sharp. Java and C Sharp. Java and C Sharp. Java and C Sharp.");
		post.setContent("Java and C Sharp are OOP");
		assertEquals(1001, postController.createNewPost(post).getStatus());
	}
	@Test
	public void changeStatus_code200(){
		assertEquals(200, postController.changeStatus(2).getStatus());
	}
	@Test
	public void changeStatus_code3001(){
		assertEquals(3001, postController.changeStatus(0).getStatus());
	}
	@Test
	public void edit_code200(){
		PostForm post = new PostForm();
		post.setPostID(5);
		post.setTitle("Java Hello World");
		post.setContent("Java had found by SUN in 1996");
		assertEquals(200, postController.editPost(post).getStatus());
	}
	@Test
	public void edit_code1001(){
		PostForm post = new PostForm();
		post.setPostID(1);
		post.setTitle(null);
		post.setContent("Java had found by SUN in 1996");
		assertEquals(1001, postController.editPost(post).getStatus());
	}
//	@Test
//	public void delete_code200(){
//		assertEquals(200, postController.deletePost(1).getStatus());
//	}
	@Test
	public void delete_code3001(){
		assertEquals(3001, postController.deletePost(0).getStatus());
	}
	@Test
	public void delete_code3002(){
		assertEquals(3002, postController.deletePost(10).getStatus());
	}
	@Test
	public void getAll_code200(){
		assertEquals(200,postController.getAllPosts().getStatus());
	}
	@Test
	public void getAllForUser_code200(){
		assertEquals(200, postController.getPostsForUser(15).getStatus());
	}
	@Test
	public void getAllForUser_code3003(){
		assertEquals(3003, postController.getPostsForUser(1).getStatus());
	}
	@Test
	public void getPostInfo_200(){
		assertEquals(200, postController.getPostInfo(2).getStatus());
	}
	public void getPostInfo_3001(){
		assertEquals(3001, postController.getPostInfo(0).getStatus());
	}
}
