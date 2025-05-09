package com.example.moattravel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class StripeWebhookController {
	@PostMapping("/charge")
	public String charge(@RequestParam String stripeToken) {
	    try {
	        Map<String, Object> params = new HashMap<>();
	        params.put("amount", 5000);  // 5000円（Stripeでは「最小単位=1円」）
	        params.put("currency", "jpy");
	        params.put("source", stripeToken);
	        params.put("description", "テスト決済");

	        Charge charge = Charge.create(params);
	        return "redirect:/success"; // ✅ 決済完了後にリダイレクト

	    } catch (StripeException e) {
	        return "redirect:/error"; // ✅ エラー時の処理
	    }
	}
}
