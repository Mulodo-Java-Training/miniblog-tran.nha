package miniblog.controller;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import miniblog.model.UserBlog;
import miniblog.modelform.UserForm;
import miniblog.service.UserBlogService;
import miniblog.service.imp.UserBlogServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Path("UserBlog")
@Controller
@Produces(MediaType.APPLICATION_JSON)
public class UserBlogController {
	@Autowired
	UserBlogService userBlogServiceImpl;
	int statuscode;

	// Register User
	@POST
	@Path("Register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(UserForm data) {
		Date today = new Date();
		UserBlog user = new UserBlog();
		user.setUsername(data.getUsername());
		user.setPassword(data.getPassword());
		user.setFirstname(data.getFirstname());
		user.setLastname(data.getLastname());
		user.setEmail(data.getEmail());
		user.setGender(data.getGender());
		user.setBirthday(data.getBirthday());
		user.setCreate_date(today);
		user.setModify_date(today);
		statuscode = 200;
		if (!userBlogServiceImpl.createNewUser(user)) {
			statuscode = userBlogServiceImpl.getStatusNumber();
			switch (statuscode) {
			case 1001:
				return Response.status(statuscode).entity("Validate Error!!!")
						.build();
			case 2001:
				return Response.status(statuscode)
						.entity("Username has already exist!!!").build();
			}
		}
		return Response.status(statuscode)
				.entity("New user has created!!!").build();
	}

	//Change Password
	@PUT
	@Path("ChangePassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response changePassword(@FormParam("id") int id,
			@FormParam("Password") String password,
			@FormParam("NewPassword") String newpassword) {
		statuscode = 200;
		if (!userBlogServiceImpl.changePassword(id, password, newpassword)) {
			statuscode = userBlogServiceImpl.getStatusNumber();
			switch (statuscode) {
			case 1001:
				return Response.status(statuscode).entity("Validate Error!!!")
						.build();
			case 2003:
				return Response.status(statuscode).entity("Wrong Password!!!")
						.build();
			}
		}
		return Response.status(statuscode)
				.entity("New password has changed!!!").build();
	}

	//Update info
	@PUT
	@Path("Update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateInfo(UserForm data) {
		Date today = new Date();
		UserBlog user = new UserBlog();
		user.setUsername(data.getUsername());
		user.setPassword(data.getPassword());
		user.setFirstname(data.getFirstname());
		user.setLastname(data.getLastname());
		user.setEmail(data.getEmail());
		user.setGender(data.getGender());
		user.setBirthday(data.getBirthday());
		user.setModify_date(today);
		statuscode = 200;
		if (!userBlogServiceImpl.updateUser(data.getId(), user)) {
			statuscode = userBlogServiceImpl.getStatusNumber();
			switch (statuscode) {
			case 1001:
				return Response.status(statuscode).entity("Validate Error!!!")
						.build();
			}
		}
		return Response.status(statuscode)
				.entity("New Info has updated!!!").build();
	}

	//Get User's Info
	@GET
	@Path("Info/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfo(@PathParam("id") int id) {
		statuscode = 200;
		if (null == userBlogServiceImpl.findBy(id)) {
			statuscode = 2005;
			return Response.status(statuscode).entity("No result!!!").build();
		}
		return Response.status(statuscode)
				.entity(userBlogServiceImpl.findBy(id)).build();

	}
	
	//Login User
	@POST
	@Path("Login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response loginUser(@FormParam("Username") String username,
			@FormParam("Password") String password) {
		statuscode = 200;
		if (!userBlogServiceImpl.loginUser(username, password)) {
			statuscode = userBlogServiceImpl.getStatusNumber();
			switch (statuscode) {
			case 1001:
				return Response.status(statuscode).entity("Validate Error!!!")
						.build();
			case 2003:
				return Response.status(statuscode).entity("Wrong Password!!!")
						.build();
			}
		}
		return Response.status(statuscode)
				.entity("Login Successful!!!").build();
	}

	//Search Users by name(username, lastname and firstname)
	@GET
	@Path("Search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUserByName(@PathParam("name") String searchName) {
		statuscode = 200;
		if (null == userBlogServiceImpl.searchUser(searchName)) {
			statuscode = userBlogServiceImpl.getStatusNumber();
			switch (statuscode) {
			case 1001:
				return Response.status(statuscode).entity("Validate Error!!!")
						.build();
			case 2004:
				return Response.status(statuscode)
						.entity("No result has found!!!").build();
			}
		}
		return Response.status(statuscode)
				.entity(userBlogServiceImpl.searchUser(searchName)).build();
	}
}
