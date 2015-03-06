package miniblog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="comment")
public class Comment{
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty
	private int id;
	
	@ManyToOne
	@JoinColumn(name="Post_id")
	@JsonProperty
	private Post Post_id;
	
	@ManyToOne
	@JoinColumn(name="Author_id")
	private UserBlog Author_id;
	
	private String Content;
	private Date Create_date;
	private Date Modify_date;
	private String Status;

	public Post getPost_id() {
		return Post_id;
	}

	public void setPost_id(Post post_id) {
		Post_id = post_id;
	}

	public UserBlog getAuthor_id() {
		return Author_id;
	}

	public void setAuthor_id(UserBlog author_id) {
		Author_id = author_id;
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

	public int getId() {
		return id;
	}
	
	
}
