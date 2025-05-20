package com.example.moattravel.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.moattravel.entity.Role;
import com.example.moattravel.entity.User;
import com.example.moattravel.repository.RoleRepository;
import com.example.moattravel.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	//ユーザー追加	
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ ここで暗号化！

		Optional<Role> role = roleRepository.findByName("ROLE_GENERAL");
		if (role.isPresent()) {
			user.setRole(role.get());
		} else {
			throw new RuntimeException("ロールが見つかりません");
		}
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RuntimeException("ユーザーが見つかりません: " + email);
		}
	}
	 // ✅ ユーザー一覧を取得（ページング対応）
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // ✅ ユーザー検索（名前・メールアドレス・電話番号）
    public Page<User> searchUsers(String keyword, Pageable pageable) {
        return userRepository.findByNameContainingOrEmailContainingOrPhoneNumberContaining(keyword, keyword, keyword, pageable);
    }

    // ✅ ユーザーの凍結（ログイン不可）
    public void freezeUser(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
        user.setFrozen(!user.isFrozen());  // ✅ `frozen` フィールドを `true` にする！
        userRepository.saveAndFlush(user); // ✅ 変更を即時反映！

    }

    // ✅ ユーザー情報の編集（名前・メール・電話番号）
    public void updateUser(Integer userId, String name, String email, String phoneNumber) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    public User findUserById(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
    }

}