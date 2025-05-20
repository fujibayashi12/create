package com.example.moattravel.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.moattravel.entity.Order;
import com.example.moattravel.entity.User;
import com.example.moattravel.service.OrderService;
import com.example.moattravel.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {

	private final UserService userService;
	private final OrderService orderService;
	public UserController(UserService userService,OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
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
    //注文履歴表示と取得
    @GetMapping("/orders")
    public String getUserOrders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()); // ✅ ログインユーザーの情報を取得！
        
        List<Order> orders = orderService.getOrdersByUser(user.getId()); // ✅ ユーザーごとの注文履歴を取得！
        model.addAttribute("orders", orders);
        
        return "orders"; // ✅ `orders.html` に遷移！
    }
    
}

