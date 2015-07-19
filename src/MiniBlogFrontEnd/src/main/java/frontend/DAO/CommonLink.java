package frontend.DAO;

import org.springframework.stereotype.Component;

@Component
public class CommonLink {

	public final String URL_API = "http://localhost:8080/MiniBlogBackEnd/rest";

	/*------UserBlog URL------*/
	public final String URL_REGISTER_USER = URL_API + "/UserBlog/Register";
	public final String URL_CHANGEPASSWORD_USER = URL_API + "/UserBlog/ChangePassword";
	public final String URL_UPDATE_USER = URL_API + "/UserBlog/Update";
	public final String URL_GETINFO_USER = URL_API + "/UserBlog/Info/";					// Get Method
	public final String URL_LOGIN_USER = URL_API + "/UserBlog/Login";
	public final String URL_SEARCH_USER = URL_API + "/UserBlog/Search/";				// Get Method
	
	/*--------Post URL--------*/
	public final String URL_CREATENEW_POST = URL_API + "/Post/CreatePost";
	public final String URL_CHANGESTATUS_POST = URL_API + "/Post/ChangeStatus";
	public final String URL_EDIT_POST = URL_API + "/Post/Edit";
	public final String URL_DELETE_POST = URL_API + "/Post/Delete";
	public final String URL_GETALL_POST = URL_API + "/Post/All";							// Get Method
	public final String URL_GETINFO_POST = URL_API + "/Post/GetPost/";					// Get Method
	public final String URL_GETFORUSER_POST (int userID){								// Get Method
		return URL_API + "/Post/"+userID+"/Posts";
	}
	
	/*--------Comment URL--------*/
	public final String URL_CREATENEW_COMMENT = URL_API + "/Comment/CreateComment";
	public final String URL_EDIT_COMMENT = URL_API + "/Comment/EditComment";
	public final String URL_DELETE_COMMENT = URL_API + "/Comment/DeleteComment";
	public final String URL_CHANGESTATUS_COMMENT = URL_API + "/Comment/ChangeStatus";
	public final String URL_GETFORPOST_COMMENT = URL_API + "/Comment/Post/";			// Get Method
	public final String URL_GETFORUSER_COMMENT (int userID){							// Get Method
		return URL_API+"/Comment/" + userID + "/Comments";
				
	}
	public final String URL_GETINFO_COMMENT = URL_API + "/Comment/GetComment/";
	
}
