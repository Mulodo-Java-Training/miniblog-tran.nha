package frontend.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Comment {
	private int id;
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
	
}
