package frontend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frontend.DAOForm.PostForm;
import frontend.model.Comment;
import frontend.model.Post;
import frontend.model.User;
import frontend.service.CommentService;
import frontend.service.PostService;

@Controller
@RequestMapping(value="Post")
public class PostController {
	
	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="AllPostsUser",method = RequestMethod.POST)
	public String allpostsuser(@RequestParam("userid")int userid, Model model){
		List<Post> posts = new ArrayList<Post>();
		posts =	postService.getPostForUser(userid);
		model.addAttribute("Posts", posts);
		return "allpostuser";
	}
	@RequestMapping(value="CreatePost", method = RequestMethod.POST)
	public String createpost(@RequestParam("title")String title, @RequestParam("content")String content,
			RedirectAttributes atr,Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("SessionUser");
		PostForm post = new PostForm();
		post.setAuthorID(user.getId());
		post.setTitle(title);
		post.setContent(content);
		if(!postService.createNewPost(post)){
			atr.addFlashAttribute("ErrorMessage", postService.getMessageError());
			return "redirect:CreatePost";
		}
		model.addAttribute("SystemMeassage", "New Post has create!!!");
		return "redirect:/User/Welcome";
	}
	@RequestMapping(value="UpdatePost",method = RequestMethod.POST)
	public String updatepost(@RequestParam("postid")int postid,@RequestParam("title")String title,
			@RequestParam("content")String content, RedirectAttributes atr,
			HttpServletRequest request){
		String currentURL = request.getHeader("Referer");
		if(!postService.editPost(postid, title, content)){
			atr.addFlashAttribute("ErrorMessage", postService.getMessageError());
			return "redirect:"+currentURL;
		}
		return "redirect:/Post/PostDisplay?id="+postid;
	}
	@RequestMapping(value="ChangeStatus", method = RequestMethod.POST)
	public String changestatus(@RequestParam("postid")int postid, Model model, RedirectAttributes atr){
		if(!postService.changeStatus(postid)){
			atr.addFlashAttribute("ErrorMessage", postService.getMessageError());
		}else{
			atr.addFlashAttribute("SystemMessage", "Post has changed status!");
		}
		return "redirect:/User/Welcome";
	}
	@RequestMapping(value="DeletePost",method=RequestMethod.POST)
	public String deletepost(@RequestParam("postid")int postid, RedirectAttributes atr){
		if(!postService.detelePost(postid)){
			atr.addFlashAttribute("ErrorMessage", postService.getMessageError());
		}else{
			atr.addFlashAttribute("SystemMessage", "Post has deleted!");
		}
		return "redirect:/User/Welcome";
	}
	/*---------------------------
	 --------Return page---------
	 ---------------------------*/
	@RequestMapping(value="EditPost",method=RequestMethod.POST)
	public String editpostPage(@RequestParam("postid")int postid,Model model){
		Post post = new Post();
		post =	postService.getPostInfo(postid);
		model.addAttribute("Post", post);
		return "editpost";
	}
	@RequestMapping(value="PostDisplay")
	public String displaypostPage(@RequestParam("id")int postID,Model model){
		Post post = new Post(); 
		post =	postService.getPostInfo(postID);
		List<Comment> comments = null;
		comments =	commentService.getCommentForPost(postID);
		model.addAttribute("Post", post);
		model.addAttribute("Comments", comments);
		return "displaypost";
	}
	@RequestMapping(value="CreatePost")
	public String createpostPage(){
		return "createpost";
	}
}
