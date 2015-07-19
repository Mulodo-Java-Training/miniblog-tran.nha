package unittest.service;

import java.util.Date;

import miniblog.model.UserBlog;
import miniblog.modelform.UserForm;
import miniblog.service.UserBlogService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserBlogService userBlogServiceImp;

	@Test
	public void UserController_register_update() {
		assertEquals(true, userBlogServiceImp.deleteUser("tester0001"));
		UserBlog user = new UserBlog();
		user.setUsername("tester0001");
		user.setPassword("test123");
		user.setFirstname("Nha Test");
		user.setLastname("Tran Test");
		user.setEmail("abcd@com.vn");
		user.setGender("Male");
		user.setBirthday("01-09-1989");
		user.setCreate_date(new Date());
		user.setModify_date(new Date());
		assertEquals(true, userBlogServiceImp.createNewUser(user));
		int id = 13;
		user.setEmail("abcdf@com.vn");
		assertEquals(true, userBlogServiceImp.updateUser(id, user));
	}

	@Test
	public void UserCotroller_getInfo_searchUserByName() {
		int userID = 13;
		assertTrue(null != userBlogServiceImp.findBy(userID));
		String name = "nha";
		assertTrue(userBlogServiceImp.searchUser(name).size() > 0);
	}

	@Test
	public void UserController_loginUser() {
		String username = "admin";
		String password = "111222";
		assertEquals(true, userBlogServiceImp.loginUser(username, password));
	}
	
	@Test
	public void UserController_changePassword(){
		int id = 13;
		String password = "111111";
		String newPassword = "111111";
		assertEquals(true, userBlogServiceImp.changePassword(id, password, newPassword));
	}
}
