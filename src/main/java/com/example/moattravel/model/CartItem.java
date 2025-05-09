package com.example.moattravel.model;

import com.example.moattravel.entity.Goods;

import lombok.Data;

/**
 * CartItem は、カート内で管理する各商品の情報と数量を保持するクラスです。
 */
@Data
public class CartItem {
    // 商品情報（Goodsエンティティへの参照）
    private Goods goods;
    
    // カート内に入っている商品の数量
    private int quantity;
}