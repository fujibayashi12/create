package com.example.moattravel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeWebhookService {
	 private final String webhookSecret;

	    public StripeWebhookService(@Value("${stripe.webhook.secret}") String webhookSecret) {
	        this.webhookSecret = webhookSecret;
	    }

	    public String getWebhookSecret() {
	        return webhookSecret; // ✅ Webhookの秘密キーを取得
	    }

}
