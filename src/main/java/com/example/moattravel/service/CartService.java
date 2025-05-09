package com.example.moattravel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moattravel.entity.Cart;
import com.example.moattravel.entity.Goods;
import com.example.moattravel.entity.User;
import com.example.moattravel.repository.CartRepository;
import com.example.moattravel.repository.GoodsRepository;
import com.example.moattravel.repository.OrderRepository;
import com.example.moattravel.repository.UserRepository;

@Service
public class CartService {

    private final OrderRepository orderRepository;
    private final GoodsRepository goodsRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(OrderRepository orderRepository, GoodsRepository goodsRepository,
                       CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.goodsRepository = goodsRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    // ① カートに商品を追加する
    @Transactional
    public void addToCart(Integer userId, Integer goodsId, Integer quantity) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません"));
        if (goods.getStock() < quantity) {
            throw new IllegalArgumentException("在庫が不足しています");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));

        Cart cart = cartRepository.findByUserIdAndGoodsId(userId, goodsId)
                .orElseGet(() -> new Cart(user, goods, 0));
        cart.setNumberOfItems(cart.getNumberOfItems() + quantity);
        cartRepository.save(cart);
    }

    // ② カート内の商品の数量を更新する
    @Transactional
    public void updateCart(Integer userId, Integer goodsId, Integer newQuantity) {
        Cart cart = cartRepository.findByUserIdAndGoodsId(userId, goodsId)
                .orElseThrow(() -> new IllegalArgumentException("カート情報が見つかりません"));
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません"));
        if (goods.getStock() < newQuantity) {
            throw new IllegalArgumentException("在庫が不足しています");
        }
        // 在庫調整（このロジックは必要に応じ検討してください）
        goods.setStock(goods.getStock() - (newQuantity - cart.getNumberOfItems()));
        goodsRepository.save(goods);

        cart.setNumberOfItems(newQuantity);
        cartRepository.save(cart);
    }

    // ③ ユーザーのカートを空にする
    @Transactional
    public void clearCart(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(carts);
    }

    // ④ カートの合計金額を計算する
    public Integer calculateCartTotal(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        return carts.stream()
                .mapToInt(cart -> cart.getGoods().getPrice() * cart.getNumberOfItems())
                .sum();
    }

    // ⑤ ユーザーのカートを取得する
    public List<Cart> getCart(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    // ⑥ Goods をIDから取得するユーティリティメソッド（必要なら）
    public Goods getGoodsById(Integer goodsId) {
        return goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません"));
    }

	public void addItemToCart(Integer userId, Integer goodsId) {
		 Goods goods = goodsRepository.findById(goodsId)
		            .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません"));

		    User user = userRepository.findById(userId)
		            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));

		    // すでにカートに商品がある場合は数量を増やし、新規なら追加
		    Cart cart = cartRepository.findByUserIdAndGoodsId(userId, goodsId)
		            .orElseGet(() -> new Cart(user, goods, 0));

		    cart.setNumberOfItems(cart.getNumberOfItems() + 1);  // 数量を1つ増やす（必要なら動的に変更）
		    cartRepository.save(cart);
		}

	@Transactional
	public void removeItemFromCart(Integer userId, Integer goodsId) {
	    Cart cartItem = cartRepository.findByUserIdAndGoodsId(userId, goodsId)
	            .orElseThrow(() -> new IllegalArgumentException("商品がカートにありません"));

	    cartRepository.delete(cartItem); // ✅ 特定の商品だけ削除
	}
		
	}