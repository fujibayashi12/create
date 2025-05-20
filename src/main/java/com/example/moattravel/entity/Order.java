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
@Table(name = "orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "goods_id")
	private Goods goods;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "number_of_orders")
	private Integer numberOfOrders;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	private Integer quantity; // ✅ これが定義されているか確認！

	private String status;

	// 必要なゲッター・セッターを追加！
	public Integer getQuantity() {
		return quantity;
	}

	// ✅ ゲッターで計算！
	public Integer getTotalPrice() {
		return (amount != null && quantity != null) ? amount * quantity : 0;
	}

}
