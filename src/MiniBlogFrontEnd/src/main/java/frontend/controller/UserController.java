package frontend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import frontend.model.Post;
import frontend.model.User;
import frontend.service.PostService;
import frontend.service.UserService;

@Controller
@RequestMapping(value = "User")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	PostService postService;
	
	@RequestMapping(value= "UpdateInfo", method = RequestMethod.POST)
	public String updateinfo(@RequestParam("lastname")String lastname,@RequestParam("firstname")String firstname,
			@RequestParam("email")String email, @RequestParam("gender")String gender,
			@RequestParam("password")String password, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = new User();
		user = (User) session.getAttribute("SessionUser");
		if(!user.getPassword().equalsIgnoreCase(password)){
			model.addAttribute("ErrorMessage", "Wrong Password!!!");
			return "updateinfo";
		}
		else{
			user.setLastname(lastname);
			user.setFirstname(firstname);
			user.setEmail(email);
			user.setGender(gender);
			if(userService.updateUser(user)){
				return "redirect:/User/LogOut";
			}
			model.addAttribute("ErrorMessage", userService.getMessageError());
			return "updateinfo";
		}
	}
	@RequestMapping(value = "ChangePassword",method = RequestMethod.POST)
	public String changepassword(@RequestParam("password")String password,
			@RequestParam("newpassword")String newpassword, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("SessionUser");
		if(userService.changePassword(user.getId(), password, newpassword)){
			return "redirect:/User/LogOut";
		}
		model.addAttribute("ErrorMessage", userService.getMessageError());
		return "changepassword";
	}
	@RequestMapping(value = "SearchName", method = RequestMethod.POST)
	public String searchName(@RequestParam("namesearch")String namesearch, Model model) throws JsonParseException, JsonMappingException, IOException{
		if(userService.searchUser(namesearch).size()>0){
			model.addAttribute("Users", userService.searchUser(namesearch));
			return "searchuser";
		}
		else{
			model.addAttribute("ErrorMessage", "No result!!!");
			return "redirect:Welcome";
		}
	}
	
	/*---------------------------
	 --------Return page---------
	 ---------------------------*/
	@RequestMapping(value = "ChangePassword",method = RequestMethod.GET)
	public String changepasswordPage(){
		return "changepassword";
	}
	
	@RequestMapping(value = "UpdateInfo",method = RequestMethod.GET)
	public String updateInfoPage(){
		return "updateinfo";
	}
	
	@RequestMapping(value = "LogOut",method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("SessionUser");
		return "redirect:/home/Login";
	}
	
	@RequestMapping(value = "Welcome", method = RequestMethod.GET)
	public String welcomePage(Model model){
		List<Post> posts = null;
		posts =	postService.getAllPost();
		model.addAttribute("Posts", posts);
		return "welcome";
	}
}
