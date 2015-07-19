package miniblog.modelform;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import miniblog.model.Post;
import miniblog.model.UserBlog;
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class CommentForm {
	
	private int id;
	private int PostID;
	private int AuthorID;
	private String Content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostID() {
		return PostID;
	}
	public void setPostID(int post_id) {
		PostID = post_id;
	}
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int author_id) {
		AuthorID = author_id;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
