package miniblog.modelform;

import java.util.Date;

import javax.ws.rs.FormParam;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import miniblog.model.UserBlog;
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class PostForm {
	@FormParam("PostID")
	private int PostID;
	@FormParam("Title")
	private String Title;
	@FormParam("Content")
	private String Content;
	@FormParam("AuthorID")
	private int AuthorID;
	
	
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}
	public int getPostID() {
		return PostID;
	}
	public void setPostID(int postID) {
		this.PostID = postID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
