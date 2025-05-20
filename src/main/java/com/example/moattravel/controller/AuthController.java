package com.example.moattravel.controller;

import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moattravel.Form.SignupForm;
import com.example.moattravel.entity.User;
import com.example.moattravel.service.UserService;
import com.example.moattravel.service.VerificationTokenService;

@Controller
public class AuthController {

	private final VerificationTokenService verificationTokenService;
	private final UserService userService;

	public AuthController(VerificationTokenService verificationTokenService, UserService userService) {
		this.verificationTokenService = verificationTokenService;
		this.userService = userService;
	}

	//トークンをDBから取得する
	@GetMapping("/login")
	public String login(@RequestParam(required = false) String token) {
		if (token != null) {
			verificationTokenService.getVerificationToken(token);
		}
		return "login";
	}

	//新規登録画面を表示
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "signup";
	}

	//フォームデータを処理
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute SignupForm form) {
		User user = new User(form.getName(), form.getFurigana(), form.getPostalCode(), form.getAddress(),
				form.getPhoneNumber(), form.getEmail(), form.getPassWord());
		
		user.setEnabled(true);// ✅ アカウントを有効化！（デフォルトで `true` にする）
		userService.registerUser(user);
		verificationTokenService.createToken(user, generateToken());
		return "redirect:/login";
	}

	//新規登録時にトークンを発行する
	private String generateToken() {
		return UUID.randomUUID().toString();//トークンの生成
	}
}
