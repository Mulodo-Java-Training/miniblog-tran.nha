package unittest.service;

import static org.junit.Assert.*;

import java.util.Date;

import miniblog.model.Post;
import miniblog.service.PostService;
import miniblog.service.UserBlogService;
import miniblog.service.imp.UserBlogServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class PostServiceTest {
	
	@Autowired
	PostService postServiceImpl;
	@Autowired
	UserBlogService userServiceImpl;
	@Test
	public void postService_create_edit() {
		userServiceImpl.loginUser("admin", "111222");
		Post post = new Post();
		post.setAuthor(userServiceImpl.getUserBlog());
		post.setTitle("Java and C Sharp");
		post.setContent("Java and C Sharp are OOP");
		post.setStatus("Available");
		post.setModify_date(new Date());
		post.setCreate_date(new Date());
		assertEquals(true, postServiceImpl.createNewPost(post));
		post.setContent("Java is from Sun. C Sharp is from Microsoft.");
		assertEquals(true, postServiceImpl.updatePost(post.getId(), post));
	}
	
	@Test
	public void postService_changeStatus(){
		assertEquals(true, postServiceImpl.changeStatus(2));
	}
	@Test
	public void getPost(){
		assertTrue(null != postServiceImpl.getPost(1));
	}
}
