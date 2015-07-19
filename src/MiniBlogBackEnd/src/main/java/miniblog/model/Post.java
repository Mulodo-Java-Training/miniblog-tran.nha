package miniblog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="post")
public class Post{
	
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	

	@ManyToOne
	@JoinColumn(name="Author")
	private UserBlog Author;
	
	@Transient
	private int AuthorID;
	@Transient
	private String Username;
	private String Title;
	private String Content;
	private Date Create_date;
	private Date Modify_date;
	private String Status;
	
	@JsonIgnore
	@OneToMany(mappedBy="Post_id",cascade=CascadeType.ALL)
	private List<Comment> comments;
	@JsonIgnore
	public UserBlog getAuthor() {
		return Author;
	}

	public void setAuthor(UserBlog author) {
		Author = author;
	}

	public int getAuthorID(){
		return Author.getId();
	}
	
	public String getUsername() {
		return Author.getUsername();
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}
}
