package frontend.DAOForm;

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
