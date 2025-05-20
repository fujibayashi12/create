package com.example.moattravel.service;

import org.springframework.stereotype.Service;

import com.example.moattravel.entity.User;
import com.example.moattravel.entity.VerificationToken;
import com.example.moattravel.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
	private final VerificationTokenRepository verificationTokenRepository;

	public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository = verificationTokenRepository;
	}

	//トークンを作って、DBに保存
	public void createToken(User user, String token) {
		VerificationToken verificationToken = new VerificationToken(user,token);
		verificationTokenRepository.save(verificationToken);
	}

	//トークンをDBから取得する
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);

	}

}