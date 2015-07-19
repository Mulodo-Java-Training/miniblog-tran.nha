package frontend.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Post {
	private int id;
	private int AuthorID;
	private String Username;
	private String Title;
	private String Content;
	private Date Create_date;
	private Date Modify_date;
	private String Status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getCreate_date() {
		return Create_date;
	}
	public void setCreate_date(Date create_date) {
		Create_date = create_date;
	}
	public Date getModify_date() {
		return Modify_date;
	}
	public void setModify_date(Date modify_date) {
		Modify_date = modify_date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int AuthorID) {
		this.AuthorID = AuthorID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
}
