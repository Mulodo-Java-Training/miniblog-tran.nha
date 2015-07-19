package miniblog.controller;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import miniblog.model.Comment;
import miniblog.modelform.CommentForm;
import miniblog.service.CommentService;

import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Path("Comment")
@Controller
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {

	@Autowired
	UserBlogController userController;
	@Autowired
	PostController postController;
	@Autowired
	CommentService commentServiceImpl;
	int statuscode;
	
	@POST
	@Path("CreateComment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewComment(CommentForm data){
		Comment comment = new Comment();
		comment.setAuthor_id(userController.getUser(data.getAuthorID()));
		comment.setPost_id(postController.getPost(data.getPostID()));
		comment.setContent(data.getContent());
		comment.setCreate_date(new Date());
		comment.setModify_date(new Date());
		comment.setStatus("Available");
		statuscode = 200;
		if(!commentServiceImpl.createNewComment(comment)){
			statuscode = commentServiceImpl.getStatusNumber();
			return Response.status(statuscode).entity("Validation Error!!!").build();
		}
		return Response.status(statuscode).entity("New comment has create!!!").build();
	}
	
	@PUT
	@Path("EditComment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response editComment(@FormParam("CommentID")int commentID,@FormParam("Content")String content){
		Comment comment = new Comment();
		comment.setContent(content);
		statuscode = 200 ;
		if(!commentServiceImpl.editComment(commentID, comment)){
			statuscode = commentServiceImpl.getStatusNumber();
			return Response.status(statuscode).entity("Validation Error!!!").build();
		}
		return Response.status(statuscode).entity("This comment has updated!!!").build();
	}
	
	@POST
	@Path("DeleteComment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteComment(@FormParam("CommentID")int commentID){
		statuscode = 200;
		if(!commentServiceImpl.deleteComment(commentID)){
			statuscode = commentServiceImpl.getStatusNumber();
			switch (statuscode){
			case 4001:
				return Response.status(statuscode).entity("Comment is not exist!!!").build();
			case 4002:
				return Response.status(statuscode).entity("Comment must be delete status!!!").build();
			}
		}
		return Response.status(statuscode).entity("Comment has delete!!!").build();
	}
	
	@PUT
	@Path("ChangeStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response changeStatus(@FormParam("CommentID")int commentID){
		statuscode = 200;
		if(!commentServiceImpl.changeStatus(commentID)){
			statuscode =commentServiceImpl.getStatusNumber();
			return Response.status(statuscode).entity("This Comment is not exist!!!").build();
		}
		return Response.status(statuscode).entity("Status has changed!!!").build();
	}
	
	@GET
	@Path("Post/{postID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentForPost(@PathParam("postID")int postID){
		statuscode = 200;
		if(null == commentServiceImpl.getCommentsForPost(postID)){
			statuscode = commentServiceImpl.getStatusNumber();
			return Response.status(statuscode).entity("No result has found!!!").build();
		}
		return Response.status(statuscode).entity(commentServiceImpl.getCommentsForPost(postID)).build();
	}
	
	@GET
	@Path("{userID}/Comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentForUser(@PathParam("userID")int userID){
		statuscode = 200;
		if(null == commentServiceImpl.getCommentsForUser(userID)){
			statuscode = commentServiceImpl.getStatusNumber();
			return Response.status(statuscode).entity("No result has found!!!").build();
		}
		return Response.status(statuscode).entity(commentServiceImpl.getCommentsForUser(userID)).build();
	}
	
	@GET
	@Path("GetComment/{commentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentInfo(@PathParam("commentID")int commentID){
		return Response.status(statuscode).entity("").build();
	}
}