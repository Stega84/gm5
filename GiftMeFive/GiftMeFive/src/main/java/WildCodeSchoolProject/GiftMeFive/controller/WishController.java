package WildCodeSchoolProject.GiftMeFive.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/wishform_list")
	public String wishform_list(Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);

		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		return "wishform_list";
	}
	
	@GetMapping("/wishlistSaved")
	public String wishformSaved(Model model, @RequestParam String titlename, @RequestParam Long wishlistId, @RequestParam String userId, @RequestParam String friendsId ) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("userId", userId);
		model.addAttribute("friendsId", friendsId);

		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		return "wishlistSaved";
	}

	@PostMapping("/addWish")
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

		String titlename = repository.getWishlistname(wishlistId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/unreserveWish")
	public String unreservewishDo(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam String articlename, @RequestParam String reservationname) {

		repository.unreserveWish(articleId);

		redirect.addAttribute("reservationname", reservationname);
		return "redirect:/reservationoutput";
	}

	@GetMapping("/findWishlist")
	public String show(RedirectAttributes redirect, Model model, @RequestParam String userId) {

		Long wishlistId;
		String[] viewId = userId.split("_");
		wishlistId = Long.parseLong(viewId[1]);

		if (viewId.length == 2) {
			redirect.addAttribute("userId", userId);
			redirect.addAttribute("friendsId", userId+"_friends");
			redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/wishlistSaved";
		}

		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/reservationoutput")
	public String show(Model model, @RequestParam String reservationname) {

		model.addAttribute("wishlist", repository.showReservations(reservationname));
		model.addAttribute("reservationname", reservationname);
		return "reservationoutput";
	}

	@GetMapping("/createWishlist")
	public String create(Model model, @RequestParam String titlename, @RequestParam String enddate,
			RedirectAttributes redirectAttributes) {
		System.out.println(enddate);
		Long wishlistId = repository.createWishlist(titlename, enddate);
		redirectAttributes.addAttribute("titlename", titlename);
		redirectAttributes.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/saveWishlist")
	public String saveWishlist(RedirectAttributes redirect, @RequestParam String titlename,
			@RequestParam Long wishlistId) {

		titlename = titlename.replaceAll("_", "");
		String userId = titlename + "_" + wishlistId;
		String friendsId = titlename + "_" + wishlistId + "_friends";
		
		redirect.addAttribute("userId", userId);
		redirect.addAttribute("friendsId", friendsId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistSaved";
	}
}
