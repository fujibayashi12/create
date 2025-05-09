package com.example.moattravel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/account/**").authenticated()  // ✅ `/account` はログイン必須！
	                .requestMatchers("/login", "/register", "/").permitAll()  // ✅ ログイン不要

	                .anyRequest().permitAll()
	            )
	            .formLogin(login -> login
	                .loginPage("/login")  // ✅ ログインページの設定！
	                .defaultSuccessUrl("/goods", true)  // ✅ ログイン成功時は `/goods` へ！
	                .failureUrl("/login?error=true")  // ✅ 失敗時のリダイレクト！
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutSuccessUrl("/logout")  // ✅ ログアウト後は `/login` へ！
	                .permitAll()
	            );
	        return http.build();
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();  // ✅ パスワードの暗号化！
	    }
	}
