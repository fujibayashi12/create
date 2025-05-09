package com.example.moattravel.entity;

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
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ユーザーに対する関連付け
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 商品に対する関連付け
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    // カート内の商品の数量
    @Column(name = "number_of_items")
    private Integer numberOfItems;

    public Cart() {
    }

    public Cart(User user, Goods goods, Integer numberOfItems) {
        this.user = user;
        this.goods = goods;
        this.numberOfItems = numberOfItems;
    }
}