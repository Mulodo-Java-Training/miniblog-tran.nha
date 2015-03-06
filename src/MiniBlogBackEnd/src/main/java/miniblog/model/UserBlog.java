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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "userblog")
//@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class UserBlog {
	
	public UserBlog() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Username;
	private String Password;
	private String Email;
	private String Lastname;
	private String Firstname;
	private String Gender;
	private String Birthday;
	private Date Create_date;
	private Date Modify_date;
	
	@OneToMany(mappedBy="Author",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts;
	
	@OneToMany(mappedBy="Author_id",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
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

	public int getId() {
		return id;
	}
}
