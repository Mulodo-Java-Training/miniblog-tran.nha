package frontend.DAOForm;

public class PostForm {
	
	private int PostID;
	private String Title;
	private String Content;
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
