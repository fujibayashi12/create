package com.example.moattravel.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.moattravel.entity.User;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();

		if (user.getRole().getName().equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin/dashboard"); // ✅ 管理者なら `/admin` にリダイレクト！
		} else {
			response.sendRedirect("/goodsList"); // ✅ 一般ユーザーは `/goodsList` へ！
		}
	}
}
