package unittest.service;

import static org.junit.Assert.*;

import java.util.Date;

import miniblog.model.Comment;
import miniblog.service.CommentService;
import miniblog.service.PostService;
import miniblog.service.UserBlogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class CommentServiceTest {
	
	@Autowired
	CommentService commentServiceImpl;
	@Autowired
	UserBlogService userServiceImpl;
	@Autowired
	PostService postServiceImpl;
	
	@Test
	public void commentservice_create_update() {
		userServiceImpl.loginUser("admin", "111222");
		Comment comment = new Comment();
		comment.setAuthor_id(userServiceImpl.getUserBlog());
		comment.setPost_id(postServiceImpl.getPost(5));
		comment.setContent("Open Source");
		comment.setStatus("Available");
		comment.setCreate_date(new Date());
		comment.setModify_date(new Date());
		assertEquals(true, commentServiceImpl.createNewComment(comment));
		comment.setContent("Open Source");
		assertEquals(true, commentServiceImpl.editComment(2, comment));
	}
	
	@Test
	public void commentService_deleteComment(){
		assertEquals(true, commentServiceImpl.deleteComment(2));
	}
	@Test
	public void getComment(){
		assertTrue(null != commentServiceImpl.getCommentsForPost(3));
	}

}
