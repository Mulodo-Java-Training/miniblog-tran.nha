package frontend.DAO;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import frontend.model.ObjectReturn;
import frontend.model.User;

@Repository
public class UserDAO {

	@Autowired
	CommonLink link;
	User user;

	public int registerUser(User userdata) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_REGISTER_USER);
		Response response = target.request().post(Entity.entity(userdata, MediaType.APPLICATION_JSON));
		return response.getStatus();
	}

	public int loginUser(String username, String password) throws Exception {
		Form form = new Form();
		form.param("Username", username).param("Password", password);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_LOGIN_USER);
		Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			ObjectReturn objectjson = mapper.readValue(jsoncontent, ObjectReturn.class);
			this.user = (User) objectjson.getData();
		}
			return response.getStatus();
	}
	
	public int updateUser(User userdata){
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_UPDATE_USER);
		Response response = target.request().put(Entity.entity(userdata, MediaType.APPLICATION_JSON));
		return response.getStatus();
	}
	
	public int changePassword(int userID, String password, String newpassword){
		Form form = new Form();
		form.param("id", Integer.toString(userID)).param("Password", password).param("NewPassword",newpassword);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_CHANGEPASSWORD_USER);
		Response response = target.request().put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	
	public User gettUserInfo(int userID) throws Exception{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETINFO_USER+userID);
		Response response = target.request().get();
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
    		ObjectMapper mapper = new ObjectMapper();
    		User userinfo = mapper.readValue(jsoncontent, User.class);
    		return userinfo;
		}
		return null;
	}
	
	public List<User> searchUserByName(String name) throws JsonParseException, JsonMappingException, IOException{
    	ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_SEARCH_USER+name);
		Response response = target.request().get();
		if(response.getStatus()==200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<User> users = mapper.readValue(jsoncontent, TypeFactory.defaultInstance().constructCollectionType(List.class,User.class));
			return users;
		}
		return null;
	}
	
	public User getUser(){
		return this.user;
	}
}
