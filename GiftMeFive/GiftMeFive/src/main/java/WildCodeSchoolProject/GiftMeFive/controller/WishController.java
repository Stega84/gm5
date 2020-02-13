package WildCodeSchoolProject.GiftMeFive.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;
import WildCodeSchoolProject.GiftMeFive.entity.Article;

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

	@RequestMapping("/test")
	public String test2() {
		return "test";
	}

	@RequestMapping("/getimage/{id}")
	public ResponseEntity<byte[]> getimage(@PathVariable int id) {

		byte[] image = repository.getImage(1, id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(image);
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/wishlistoutput")
	public String wishlistoutput(Model model, RedirectAttributes redirect, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("wishlist", repository.showWishlist(wishlistId));

		//TODO create endpoint to display "no items selected"
		List<Article> returnedwishlist = (List<Article>) model.getAttribute("wishlist");
		if (returnedwishlist == null || returnedwishlist.isEmpty()) {
			redirect.addAttribute("titlename", "Leere ");
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/no_result";
		}
		return "wishlistoutput";
	}

	@GetMapping("/wishform_list")
	public String wishform_list(Model model, RedirectAttributes redirect, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("imagelink", "/getimage/1");
		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		
		//TODO create endpoint to display "no items selected"
		List<Article> returnedwishlist = (List<Article>) model.getAttribute("wishlist");
		if (returnedwishlist == null || returnedwishlist.isEmpty()) {
			redirect.addAttribute("titlename", "Leere ");
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/no_result";
		}
		return "wishform_list";
	}

	@GetMapping("/wishlistSaved")
	public String wishformSaved(Model model, RedirectAttributes redirect, @RequestParam String titlename, @RequestParam Long wishlistId,
			@RequestParam String userId, @RequestParam String friendsId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("userId", userId);
		model.addAttribute("friendsId", friendsId);
		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		
		//TODO create endpoint to display "no items selected"
		List<Article> returnedwishlist = (List<Article>) model.getAttribute("wishlist");
		if (returnedwishlist == null || returnedwishlist.isEmpty()) {
			redirect.addAttribute("titlename", "Leere ");
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/no_result";
		}
		return "wishlistSaved";
	}

	@PostMapping("/addWish")
	public String addWish(RedirectAttributes redirect, Model model, @RequestParam String articlename,
			@RequestParam String description, @RequestParam Long wishlistId, @RequestParam String titlename,
			@RequestParam String CategoryImage, @RequestParam Long articleId, @RequestParam String productlink) {

		if (articleId != null) {

			repository.editWish(articleId, articlename, description, CategoryImage, productlink, wishlistId);

		} else {
			// Funktion schreiben die aus dem eingegebenen Namen ein Amazonsuchlink macht
			productlink = "https://www.amazon.de/s?k=play+Station";
			repository.addWish(articlename, description, CategoryImage, productlink, wishlistId);
		}

		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/removeWish")
	public String removeWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String titlename) {

		repository.removeWish(articleId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/reserveWish")
	public String reserveWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String reservationname) {

		repository.reserveWish(articleId, reservationname);

		String titlename = repository.getWishlistname(wishlistId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/unreserveWish")
	public String unreservWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam String articlename, @RequestParam String reservationname) {

		repository.unreserveWish(articleId);

		redirect.addAttribute("reservationname", reservationname);
		return "redirect:/reservationoutput";
	}

	@GetMapping("/findWishlist")
	public String findWishlist(RedirectAttributes redirect, Model model, @RequestParam String userId) {

		Long wishlistId;
		String[] viewId = userId.split("_");
		wishlistId = Long.parseLong(viewId[1]);

		if (viewId.length == 2) {
			redirect.addAttribute("userId", userId);
			redirect.addAttribute("friendsId", userId + "_friends");
			redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/wishlistSaved";
		}

		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/reservationoutput")
	public String resevationoutput(Model model, RedirectAttributes redirect, @RequestParam String reservationname) {

		model.addAttribute("wishlist", repository.showReservations(reservationname));
		model.addAttribute("reservationname", reservationname);
		
		//TODO create endpoint to display "no items selected"
		List<Article> returnedwishlist = (List<Article>) model.getAttribute("wishlist");
		if (returnedwishlist == null || returnedwishlist.isEmpty()) {
			redirect.addAttribute("titlename", "Leere ");
			redirect.addAttribute("wishlistId", 0);
			return "redirect:/no_result";
		}	
		return "reservationoutput";
	}

	@GetMapping("/createWishlist")
	public String createWishlist(Model model, @RequestParam String titlename, @RequestParam String enddate,
			RedirectAttributes redirectAttributes) {

		Long wishlistId = repository.createWishlist(titlename, enddate);
		redirectAttributes.addAttribute("titlename", titlename);
		redirectAttributes.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/editWishlist")
	public String editWishlist(RedirectAttributes redirect, Model model, @RequestParam String userId) {

		Long wishlistId;
		String[] viewId = userId.split("_");
		wishlistId = Long.parseLong(viewId[1]);
		redirect.addAttribute("wishlistId", wishlistId);
		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		return "redirect:/wishform_list";
	}

	@GetMapping("/editWish")
	public String editWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam String articlename, @RequestParam String description, @RequestParam String imagelink,
			@RequestParam String productlink, @RequestParam String userimage, @RequestParam String categoryImage,
			@RequestParam Long wishlistId) {

		if (userimage.equals("")) {
			repository.editWish(articleId, articlename, description, categoryImage, productlink, wishlistId);
		} else {
			repository.editWish(articleId, articlename, description, userimage, productlink, wishlistId);
		}

		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/loadWish")
	public String loadWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String articlename, @RequestParam String description,
			@RequestParam String imagelink, @RequestParam String productlink) {
		model.addAttribute("articleId", articleId);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("articlename", articlename);
		model.addAttribute("description", description);
		model.addAttribute("imagelink", imagelink);
		model.addAttribute("productlink", productlink);
		model.addAttribute("titlename", repository.getWishlistname(wishlistId));
		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
		return "wishform_list";
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

	@PostMapping("/saveimage")
	public String saveImage(@RequestParam("userimage") MultipartFile file, Model model,
			@RequestParam String titlename, @RequestParam Long wishlistId, @RequestParam String articlename, @RequestParam String description) {
		long imageid = 0;
		
		try {
			byte[] tmp = file.getBytes();
			imageid = repository.addImage(tmp);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("articlename", articlename);
		model.addAttribute("description", description);
		model.addAttribute("imagelink", "/getimage/"+imageid);
		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));

		return "wishform_list";
	}
	
	@GetMapping("/recycle")
	public String recycleWishlist(Model model, @RequestParam String titlename, @RequestParam String enddate, @RequestParam Long oldwishlistId,
			RedirectAttributes redirectAttributes) {
		
		model.addAttribute("wishlist", repository.showUnreserved(oldwishlistId));
		List<Article> movingwishlist = (List<Article>) model.getAttribute("wishlist");
		if (movingwishlist == null || movingwishlist.isEmpty()) {
			titlename = repository.getWishlistname(oldwishlistId);
			redirectAttributes.addAttribute("titlename", titlename);
			redirectAttributes.addAttribute("wishlistId", oldwishlistId);
			//TODO create endpoint to display "no unreserved items selected, redirected to previous wishlist"
			return "redirect:/no_result";
		}
		Long wishlistId = repository.createWishlist(titlename, enddate);
		repository.moveToWishlist(movingwishlist, wishlistId);
	
		redirectAttributes.addAttribute("titlename", titlename);
		redirectAttributes.addAttribute("wishlistId", wishlistId);
		return "redirect:/saveWishlist";
	}
	
	@GetMapping("/no_result")
	public String no_result (Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		return "no_result";
	}
}
