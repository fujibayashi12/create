package com.example.moattravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout"; // ✅ `checkout.html` を表示するための処理！
    }
}