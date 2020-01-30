package WildCodeSchoolProject.GiftMeFive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/wishlistoutput")
	public String wishlistoutput(Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		
		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
		return "wishlistoutput";
	}

	@GetMapping("wishform_list")
	public String wishform_list(Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);

		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		return "wishform_list";
	}

	@GetMapping("/addWish")
	public String wishform_list(RedirectAttributes redirect, Model model, @RequestParam String articlename,
			@RequestParam String description, @RequestParam String userimage, @RequestParam Long wishlistId,
			@RequestParam String titlename, String categoryImage) {

		// Funktion schreiben die aus dem eingegebenen Namen ein Amazonsuchlink macht
		String productlink = "https://www.amazon.de/s?k=play+Station";

		if (userimage.equals("")) {
			repository.addWish(articlename, description, categoryImage, productlink, wishlistId);
		} else {
			repository.addWish(articlename, description, userimage, productlink, wishlistId);
		}

		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/removeWish")
	public String removewish_fromform(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String titlename) {

		repository.removeWish(articleId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/reserveWish")
	public String reservewishDo(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String reservationname) {

		repository.reserveWish(articleId, reservationname);
//		redirect.addAttribute("wishlistId", wishlistId);
//		return "redirect:/findWishlist";
//		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
//		return "wishlistoutput";
		String titlename = repository.getWishlistname(wishlistId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/findWishlist")
	public String show(Model model, @RequestParam Long wishlistId) {

		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
		// Methode im repository erstellen um den Name der Liste abzufragen:		
		String titlename = repository.getWishlistname(wishlistId);
		model.addAttribute("titlename", titlename);
		return "wishlistoutput";
	}

	@GetMapping("/createWishlist")
	public String create(Model model, @RequestParam String titlename, @RequestParam String enddate,
			RedirectAttributes redirectAttributes) {

		Long wishlistId = repository.createWishlist(titlename, enddate);
		redirectAttributes.addAttribute("titlename", titlename);
		redirectAttributes.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

}
