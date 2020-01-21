package WildCodeSchoolProject.GiftMeFive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;

@Controller
public class WishController {
	
	WishRepository repository = new WishRepository();

	@RequestMapping ("/list")
	public String list () {
		return "list";
	}
	
	@RequestMapping ("/list2")
	public String list2 () {
		return "list2";
	}
	
	@GetMapping ("/addWish")
	public String wishform_list (Model model, @RequestParam Long wunschliste_id) {
		model.addAttribute("Wish", repository.addWish(Name, Beschreibung, Datum, Bildlink, Produktlink, Preis, wunschliste_id));
		return "wishform_list";
	}
	
	@GetMapping("/findWishlist")
	public String random(Model model, @RequestParam Long id) {

	  model.addAttribute("wishlist", repository.showWishlist(id));
	  return "wishlistoutput";
	}
	
}
