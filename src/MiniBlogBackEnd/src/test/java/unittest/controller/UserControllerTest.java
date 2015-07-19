package unittest.controller;

import java.util.Date;

import miniblog.controller.UserBlogController;
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
public class UserControllerTest {

	@Autowired
	private UserBlogController userController;
	UserForm data;
	
	@Test
	public void registerCode200(){
		assertEquals(true, userController.deleteUser("tester0100"));
		filldata();
		assertEquals(200, userController.register(getData()).getStatus());
	}
	
	@Test
	public void registerCode1001(){
		filldata();
		UserForm data = getData();
		data.setUsername("");
		assertEquals(1001, userController.register(data).getStatus());
	}
	
	@Test
	public void resgisterCode2001(){
		filldata();
		UserForm data = getData();
		data.setUsername("admin");
		assertEquals(2001, userController.register(getData()).getStatus());
	}
	
	@Test
	public void changePasswordCode200(){
		int id = 13;
		String password = "111111";
		String newpassword = "111111";
		assertEquals(200, userController.changePassword(id, password, newpassword).getStatus());
	}
	
	@Test
	public void changePasswordCode1001(){
		int id = 13;
		String password = null;
		String newpassword = "111111";
		assertEquals(1001, userController.changePassword(id, password, newpassword).getStatus());
	}
	
	@Test
	public void changePasswordCode2003(){
		int id = 13;
		String password = "111111";
		String newpassword = "111111";
		assertEquals(2003, userController.changePassword(id, password, newpassword).getStatus());
	}
	
	@Test
	public void updateCode200(){
		filldata();
		UserForm data = getData();
		data.setId(13);
		data.setEmail("tester@test.com");
		assertEquals(200, userController.updateInfo(data).getStatus());
	}
	
	@Test
	public void updateCode1001(){
		filldata();
		UserForm data = getData();
		data.setId(13);
		data.setEmail("email@com");
		assertEquals(1001, userController.updateInfo(data).getStatus());
	}
	
	@Test
	public void infoCode200(){
		int id = 13;
		assertEquals(200, userController.getInfo(id).getStatus());
	}
	
	@Test
	public void infoCode2005(){
		int id = 9999;
		assertEquals(2005, userController.getInfo(id).getStatus());
	}
	
	@Test
	public void loginCode200(){
		String username ="master";
		String password = "222222";
		assertEquals(200, userController.loginUser(username, password).getStatus());
	}
	
	@Test
	public void loginCode1001(){
		String username ="mastermastermastermastermastermastermastermaster";
		String password = "222222";
		assertEquals(1001, userController.loginUser(username, password).getStatus());
	}
	
	@Test
	public void loginCode2003(){
		String username ="master";
		String password = "122222";
		assertEquals(2003, userController.loginUser(username, password).getStatus());
	}
	
	@Test
	public void searchCode200(){
		String  name = "nha";
		assertEquals(200, userController.searchUserByName(name).getStatus());
	}
	
	@Test
	public void searchCode1001(){
		String  name = "Code la mot nghe thuat va coder la mot nghe si!!!!!!!!!!";
		assertEquals(1001, userController.searchUserByName(name).getStatus());
	}
	
	@Test
	public void searchCode2004(){
		String name = "Do exercise!!!";
		assertEquals(2004, userController.searchUserByName(name).getStatus());
	}

	private UserForm getData() {
		return this.data;
	}
	 private void filldata(){
		 	data = new UserForm();
			this.data.setUsername("tester0100");
			this.data.setPassword("000001");
			this.data.setFirstname("Nha Test");
			this.data.setLastname("Tran Test");
			this.data.setEmail("abcd@com.vn");
			this.data.setGender("Male");
			this.data.setBirthday("01-09-1989");
			this.data.setCreate_date(new Date());
			this.data.setModify_date(new Date());
	 }
	
}
