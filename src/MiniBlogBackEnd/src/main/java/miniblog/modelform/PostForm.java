package miniblog.modelform;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import miniblog.model.UserBlog;
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class PostForm {
	
	private String Title;
	private String Content;
	
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
