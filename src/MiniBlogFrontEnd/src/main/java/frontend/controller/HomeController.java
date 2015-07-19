package frontend.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import frontend.model.User;
import frontend.service.UserService;

@Controller
@RequestMapping(value = "home")
public class HomeController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model,
			HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		if (userService.loginUser(username, password)) {
			session.setAttribute("SessionUser", userService.getInfo());
			return "redirect:/User/Welcome";
		} else {
			model.addAttribute("ErrorMessage", userService.getMessageError());
			return "index";
		}
	}

	@RequestMapping(value = "Register", method = RequestMethod.POST)
	public String registerUser(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("lastname") String lastname,
			@RequestParam("firstname") String firstname,
			@RequestParam("gender") String gender,
			@RequestParam("birthday") String birthday,
			ModelMap mm) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setLastname(lastname);
		user.setFirstname(firstname);
		user.setGender(gender);
		user.setBirthday(birthday);
		if (userService.registerUser(user)) {
			return "redirect:Login";
		}else{
		mm.put("ErrorMessage", userService.getMessageError());
		return "register";
		}
	}

	/*---------------------------
	 --------Return page---------
	 ---------------------------*/
	@RequestMapping(value = "Login", method = RequestMethod.GET)
	public String loginPage() {
		return "index";
	}

	@RequestMapping(value = "Register", method = RequestMethod.GET)
	public String registerPage() {
		return "register";
	}
}
