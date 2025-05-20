package com.example.moattravel.entity;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "goods")
@Data
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "category")
	private String category;

	@Column(name = "price")
	private Integer price;

	@Column(name = "order_capacity")
	private Integer orderCapacity;

	@Column(name = "image_url",nullable = false)
	private String imageUrl = "default-image.png";

	@Column(name = "created_at", nullable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

	// ✅ `created_at` を自動設定
	@PrePersist
	protected void onCreate() {
		createdAt = Timestamp.from(Instant.now());
	}

}
