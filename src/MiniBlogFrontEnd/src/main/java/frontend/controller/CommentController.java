package frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import frontend.DAOForm.CommentForm;
import frontend.model.Comment;
import frontend.model.User;
import frontend.service.CommentService;

@Controller
@RequestMapping("Comment")
public class CommentController {
	@Autowired
	CommentService commentservice;
	
	@RequestMapping(value="CreateNewComment", method=RequestMethod.POST)
	public String createComment(@RequestParam("content")String content, @RequestParam("postid")int postid,
			RedirectAttributes atr,	HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("SessionUser");
		String currentURL = request.getHeader("Referer");
		CommentForm comment= new CommentForm();
		comment.setAuthorID(user.getId());
		comment.setPostID(postid);
		comment.setContent(content);
		if(!commentservice.createNewComment(comment)){
			atr.addFlashAttribute("SystemMessage", commentservice.getMessageError());
			return "redirect:"+currentURL;
		}
		else{
			atr.addFlashAttribute("SystemMessage", "New comment has created in this post!!!");
			return "redirect:/Post/PostDisplay?id="+postid;
		}
	}
	@RequestMapping(value="EditComment",method = RequestMethod.POST)
	public String editcomment(@RequestParam("content")String content, @RequestParam("commentid")int commentid,
			RedirectAttributes atr,	HttpServletRequest request){
		String currentURL = request.getHeader("Referer");
		if(!commentservice.editComment(commentid, content)){
			atr.addFlashAttribute("SystemMessage", commentservice.getMessageError());
		}else{
			atr.addFlashAttribute("SystemMessage", "Comment has updated!!!");
		}
		return "redirect:"+currentURL;
	}
	@RequestMapping(value="ChangeStatus",method = RequestMethod.POST)
	public String changestatus(@RequestParam("commentid")int commentid, RedirectAttributes atr,
			Model model, HttpServletRequest request){
		String currentURL = request.getHeader("Referer");
		if(!commentservice.changeStatus(commentid)){
			atr.addFlashAttribute("SystemMessage", commentservice.getMessageError());
		} else{
			atr.addFlashAttribute("SystemMessage", "Comment's has change Status");
		}
		return "redirect:"+currentURL;
	}
	@RequestMapping(value="DeleteComment",method = RequestMethod.POST)
	public String deletecomment(@RequestParam("commentid")int commentid, RedirectAttributes atr,
			Model model, HttpServletRequest request){
		String currentURL = request.getHeader("Referer");
		if(!commentservice.deleteComment(commentid)){
			atr.addFlashAttribute("SystemMessage", commentservice.getMessageError());
		}else{
			atr.addFlashAttribute("SystemMessage", "Comment's has removed!!!");
		}
		return "redirect:"+currentURL;
	}
}
