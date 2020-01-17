package WildCodeSchoolProject.GiftMeFive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishController {

	@RequestMapping ("/list")
	public String list () {
		return "list";
	}
	
	@RequestMapping ("/list2")
	public String list2 () {
		return "list2";
	}
	
	@RequestMapping ("wishform_list")
	public String wishform_list () {
		return "wishform_list";
	}
	
}
