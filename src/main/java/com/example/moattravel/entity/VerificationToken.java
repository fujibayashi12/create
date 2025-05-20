package com.example.moattravel.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "verification")
@Data
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	public VerificationToken(User user, String token) {
	    this.user = user;
	    this.token = token;
	    this.createdAt = new Timestamp(System.currentTimeMillis());  // ✅ 登録時の作成日時をセット！
	    this.updatedAt = new Timestamp(System.currentTimeMillis());  // ✅ 初期値として現在の時刻をセット！
	}
}
