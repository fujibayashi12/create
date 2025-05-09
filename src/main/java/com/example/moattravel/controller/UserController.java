package com.example.moattravel.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.moattravel.entity.User;
import com.example.moattravel.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@GetMapping("/login")
    public String showLoginPage() {
        return "login";  // ✅ Spring Security とのリンクを確実に！
    }

	// ✅ ログインユーザーの情報を取得
    @GetMapping
    public String getAccountInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "account";  // ✅ `account.html` に遷移！
    }

    // ✅ 新規ユーザー登録
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";  // ✅ 登録後にログインページへ！
    }
}

