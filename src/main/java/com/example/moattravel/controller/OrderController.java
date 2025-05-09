package com.example.moattravel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moattravel.entity.Order;
import com.example.moattravel.service.OrderService;

@Controller
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//注文ボタンを押すと注文データを保存し、注文履歴に返される処理
	@PostMapping("/orders/create")
	public String createOrder(@RequestParam Integer user_id, @RequestParam Integer goodsId, @RequestParam Integer quantity) {
	    orderService.createOrder(user_id, goodsId, quantity);
	    return "redirect:/orders/" + user_id; // 作成後に注文履歴へリダイレクト
	}
	//注文一覧を表示するメソッド
	@GetMapping("/orders/{user_id}")
	public String getOrders(@PathVariable Integer user_id, Model model) {
		List<Order> orderList = orderService.getOrderHistory(user_id);
		System.out.println(orderList); // ✅ データがあるか確認！

	    model.addAttribute("orders", orderService.getOrderHistory(user_id));
	    return "order"; // order.html を作る予定なら仮置き
	}
	
	//決済機能を実行するときのメソッド
	@PostMapping("/orders/checkout")
	public String checkout(@RequestParam Integer user_id, @RequestParam Integer order_id) {
	    orderService.processCheckout(user_id, order_id);
	    return "redirect:/orders/" + user_id; // 決済完了後に注文履歴へリダイレクト
	}
}
