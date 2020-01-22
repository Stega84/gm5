package WildCodeSchoolProject.GiftMeFive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import WildCodeSchoolProject.GiftMeFive.entity.User;
import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;

@Controller
public class WishController {

	WishRepository repository = new WishRepository();

	@RequestMapping("/list")
	public String list() {
		return "list";
	}

	@RequestMapping("/list2")
	public String list2() {
		return "list2";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("wishform_list")
	public String wishform_list(Model model, @RequestParam String name, @RequestParam Long id) {
		model.addAttribute("name", name);
		model.addAttribute("id", id);
		model.addAttribute("wishlist", repository.showWishlist(id));
		return "wishform_list";
	}
	
	
	@GetMapping("/findWishlist")
	public String show(Model model, @RequestParam Long id) {

		model.addAttribute("wishlist", repository.showWishlist(id));
		return "wishlistoutput";
	}

	@GetMapping("/createWishlist")
	public String create(Model model, @RequestParam String name, @RequestParam String datum,
			RedirectAttributes redirectAttributes) {

		Long id = repository.erstellen(name, datum);
		redirectAttributes.addAttribute("name", name);
		redirectAttributes.addAttribute("id", id);

		return "redirect:/wishform_list";
	}

//	@PostMapping("/createWishlist")
//	public String create(@ModelAttribute User user, Model model) {
//		System.out.println(user.getName());
//	  model.addAttribute("name", user.getName());
//	  model.addAttribute("id", repository.erstellen(user.getName(), user.getDatum()));
//	  return "wishlist_form";
//	}
}
