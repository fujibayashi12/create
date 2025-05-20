package com.example.moattravel.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moattravel.entity.Order;
import com.example.moattravel.service.OrderService;

@Controller
@RequestMapping("/admin/ordersList")
@PreAuthorize("hasRole('ADMIN')") 
public class AdminOrdersController {

	private final OrderService orderService;

	public AdminOrdersController(OrderService orderService) {
		this.orderService = orderService;
	}
	// ✅ 注文一覧ページを表示
	@GetMapping("")
	public String showOrdersList(Pageable pageable, Model model) {
	    Page<Order> ordersPage = orderService.getAllOrders(pageable);

	    for (Order order : ordersPage.getContent()) {
	        System.out.println("注文ID: " + order.getId() + ", 合計金額: " + order.getTotalPrice());
	    }

	    model.addAttribute("ordersList", ordersPage.getContent());
	    model.addAttribute("currentPage", ordersPage.getNumber());
	    model.addAttribute("totalPages", ordersPage.getTotalPages());

	    return "admin/ordersList"; 
	}
	
	//ステータス変更メソッド
	@PostMapping("/updateStatus")
	public String updateOrderStatus(@RequestParam("id") Integer orderId, RedirectAttributes redirectAttributes) {
	    // ✅ サービス経由でステータスを「発送済み」に更新
	    orderService.updateOrderStatus(orderId, "発送済み");

	    // ✅ 更新成功メッセージを設定
	    redirectAttributes.addFlashAttribute("successMessage", "注文 ID: " + orderId + " を発送済みに変更しました！");

	    // ✅ 注文一覧ページへリダイレクト
	    return "redirect:/admin/ordersList";
	}

}
