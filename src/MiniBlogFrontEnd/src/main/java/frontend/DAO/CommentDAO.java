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

import frontend.DAOForm.CommentForm;
import frontend.model.Comment;
import frontend.model.Post;

@Repository
public class CommentDAO {
	@Autowired
	CommonLink link;
	
	public int createNewComment(CommentForm commentdata){
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_CREATENEW_COMMENT);
		Response response = target.request().post(Entity.entity(commentdata, MediaType.APPLICATION_JSON));
		return response.getStatus();	
	}
	public int editComment(int commentID,String content){
		Form form = new Form();
		form.param("CommentID", Integer.toString(commentID)).param("Content", content);
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_EDIT_COMMENT);
		Response response = target.request().put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	public int deleteComment(int commentID){
		Form form = new Form();
		form.param("CommentID", Integer.toString(commentID));
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_DELETE_COMMENT);
		Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	public int changeStatus(int commentID){
		Form form = new Form();
		form.param("CommentID", Integer.toString(commentID));
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_CHANGESTATUS_COMMENT);
		Response response = target.request().put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		return response.getStatus();
	}
	public List<Comment> getCommentForPost(int postID) throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETFORPOST_COMMENT+postID);
		Response response = target.request().get();
		if(response.getStatus()==200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Comment> commments = mapper.readValue(jsoncontent, TypeFactory.defaultInstance().constructCollectionType(List.class, Comment.class));
			return commments;
		}
		return null;
	}
	public List<Comment> getCommentForUser(int userID) throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETFORUSER_COMMENT(userID));
		Response response = target.request().get();
		if(response.getStatus()==200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<Comment> commments = mapper.readValue(jsoncontent, TypeFactory.defaultInstance().constructCollectionType(List.class, Comment.class));
			return commments;
		}
		return null;
	}
	public Comment getComment(int commentID) throws JsonParseException, JsonMappingException, IOException{
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(link.URL_GETINFO_COMMENT+commentID);
		Response response = target.request().get();
		if(response.getStatus() == 200){
			String jsoncontent = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			Comment comment = mapper.readValue(jsoncontent, Comment.class);
			return comment;
		}
		return null;
	}
}
