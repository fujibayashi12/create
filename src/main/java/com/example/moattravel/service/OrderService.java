package com.example.moattravel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moattravel.entity.Goods;
import com.example.moattravel.entity.Order;
import com.example.moattravel.entity.User;
import com.example.moattravel.repository.GoodsRepository;
import com.example.moattravel.repository.OrderRepository;
import com.example.moattravel.repository.UserRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private UserRepository userRepository;
	private GoodsRepository goodsRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository,
			GoodsRepository goodsRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.goodsRepository = goodsRepository;
	}

	//１商品、合計金額をそのユーザーのカートに保存
	@Transactional
	public void createOrder(Integer user_id, Integer goodsId, Integer numberOfOrders) {
		User user = userRepository.findById(user_id).get();
		Goods goods = goodsRepository.findById(goodsId).get();

		// 在庫チェック
		if (goods.getStock() < numberOfOrders) {
			throw new IllegalArgumentException("在庫が不足しています");
		}
		// 在庫更新
		goods.setStock(goods.getStock() - numberOfOrders);
		goodsRepository.save(goods); // 更新後の在庫を保存

		//注文の合計金額を計算（例: 商品価格 * 注文数）
		Integer amount = goods.getPrice() * numberOfOrders;

		Order order = new Order();
		order.setUser(user);
		order.setGoods(goods);
		order.setAmount(amount);
		order.setNumberOfOrders(numberOfOrders);

		orderRepository.save(order);
	}

	//２カート（orderRepository）の中身を呼び出してcontrollerに返すメソッド。
	public List<Order> orderList(Integer user_id) {
		return orderRepository.findByUserId(user_id);
	}

	// ユーザーの注文履歴を取得するメソッド
	public List<Order> getOrderHistory(Integer user_id) {
		return orderRepository.findByUserId(user_id);
	}
	
	//決済処理のメソッド
	@Transactional
	public void processCheckout(Integer user_id, Integer order_id) {
	    Order order = orderRepository.findById(order_id)
	        .orElseThrow(() -> new IllegalArgumentException("注文が見つかりません"));

	    if (!order.getUser().getId().equals(user_id)) {
	        throw new IllegalArgumentException("ユーザー情報が一致しません");
	    }

	    // ✅ 決済処理（後でStripe連携を追加）
	    order.setStatus("PAID"); // 仮のステータス変更
	    orderRepository.save(order);
	}

}