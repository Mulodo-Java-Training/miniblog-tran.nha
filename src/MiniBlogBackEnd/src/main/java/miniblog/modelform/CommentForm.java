package miniblog.modelform;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import miniblog.model.Post;
import miniblog.model.UserBlog;
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class CommentForm {
	
	public int getPost_id() {
		return Post_id;
	}
	public void setPost_id(int post_id) {
		Post_id = post_id;
	}
	public int getAuthor_id() {
		return Author_id;
	}
	public void setAuthor_id(int author_id) {
		Author_id = author_id;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	private int Post_id;
	private int Author_id;
	private String Content;
	
}
