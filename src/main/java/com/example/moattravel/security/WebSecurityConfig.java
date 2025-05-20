package com.example.moattravel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN") // ✅ 管理者だけ `/admin` へアクセス可能！
						.requestMatchers("/account/**").authenticated() // ✅ `/account` はログイン必須
						.requestMatchers("/login", "/signup", "/").permitAll() // ✅ ログイン不要
						.anyRequest().permitAll()) // ✅ ここは制限なし
				.formLogin(login -> login
						.loginPage("/login")
						.successHandler(successHandler) // ✅ `successHandler` でリダイレクト
						.failureUrl("/login?error=true")
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/logout")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout") // ✅ `/logout` でログアウト
						.logoutSuccessUrl("/login") // ✅ ログアウト後は `/login` へリダイレクト！
						.permitAll());

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // ✅ パスワードの暗号化！
	}
}
