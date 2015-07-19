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

import frontend.DAOForm.PostForm;
import frontend.model.Post;
import frontend.model.User;

@Repository
public class PostDAO {

	@Autowired
	CommonLink link;

	public int createNewPost(PostForm postdata) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_CREATENEW_POST);
		Response response = target.request().post(
				Entity.entity(postdata, MediaType.APPLICATION_JSON));
		return response.getStatus();
	}

	public int changeStatus(int postID) {
		Form form = new Form();
		form.param("PostID", Integer.toString(postID));
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_CHANGESTATUS_POST);
		Response response = target.request().put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}

	public int editPost(int postid, String title, String content) {
		Form form = new Form();
		form.param("PostID", Integer.toString(postid))
				.param("Title", title)
				.param("Content", content);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_EDIT_POST);
		Response response = target.request().put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	
	public int deletePost(int postID){
		Form form = new Form();
		form.param("PostID",Integer.toString(postID));
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_DELETE_POST);
		Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	public List<Post> getAllPost() throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETALL_POST);
		Response response = target.request().get();
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(jsoncontent, TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));
			return posts;
		}
		return null;
	}
	public List<Post> getPostForUser(int userID) throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETFORUSER_POST(userID));
		Response response = target.request().get();
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Post> posts = mapper.readValue(jsoncontent, TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));
			return posts;
		}
		return null;
	}
	public Post getPostInfo(int postID) throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETINFO_POST+postID);
		Response response = target.request().get();
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
    		ObjectMapper mapper = new ObjectMapper();
    		Post post = mapper.readValue(jsoncontent, Post.class);
    		return post;
		}
		return null;
	}
}
