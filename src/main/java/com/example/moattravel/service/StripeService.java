package com.example.moattravel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class StripeService {

	public StripeService(@Value("${stripe.secret.key}") String apiKey) {
		Stripe.apiKey = apiKey; 
		
	}

	public String createPaymentIntent(Integer amount) throws StripeException {
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount * 100); // ✅ 金額を最小単位（セント）で指定！
		params.put("currency", "jpy"); // ✅ 日本円指定！
		params.put("payment_method_types", List.of("card"));

		PaymentIntent paymentIntent = PaymentIntent.create(params);
		return paymentIntent.getClientSecret(); // ✅ クライアントへ渡す決済情報
	}
}