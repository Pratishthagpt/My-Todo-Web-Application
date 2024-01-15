package dev.pratishtha.springboot.myTodoWebApp.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WelcomeController {
		
//		Handling both GET and POST so we add a specified GET method so that it can handle only GET request
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String gotoWelcomeJsp(ModelMap model) {
		model.put("name", getLoggedInUser());		
		return "welcome";
	}
	
	public String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}


}




	//Whenever we want to pass anything from our controller to jsp, we can do this by putting it into MODEL.
	//In product application, we should never do "System.out.println()", but right now we are taking a shortcut here.



