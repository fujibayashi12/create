package com.example.moattravel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moattravel.entity.Cart;
import com.example.moattravel.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("")
    public String showCartDefault() {
        // ユーザーIDを決めるか、ログイン中のユーザーIDを取得する処理を入れてください
        Integer defaultUserId = 1;  
        return "redirect:/cart/" + defaultUserId;
    }


    // GET: ユーザーのカート画面を表示 (例：/cart/{userId})
    @GetMapping("/{userId}")
    public String getCart(@PathVariable Integer userId, Model model) {
        List<Cart> cartItems = cartService.getCart(userId);
        System.out.println("✅ カートの商品数: " + (cartItems != null ? cartItems.size() : "null"));

        model.addAttribute("cartItems", cartItems);
        int totalPrice = cartService.calculateCartTotal(userId);
        model.addAttribute("totalPrice", totalPrice);
        
        return "cart";
    }

    // POST: カートに商品を追加する
    @PostMapping("/add")
    public String addToCart(@RequestParam(value = "userId", required = false) Integer userId,
                            @RequestParam("goodsId") Integer goodsId) {
        if (userId == null) {
            userId = 1; // 仮のデフォルトユーザーID（適宜変更）
        }

        cartService.addItemToCart(userId, goodsId);
        return "redirect:/cart/" + userId;
    }

    // PUT: カート内商品の数量を更新する
    @PutMapping("/update")
    public String updateCart(@RequestParam("userId") Integer userId,
                             @RequestParam("goodsId") Integer goodsId,
                             @RequestParam("newQuantity") Integer newQuantity) {
        cartService.updateCart(userId, goodsId, newQuantity);
        return "redirect:/cart/" + userId;
    }

    // DELETE: カートをクリアする
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Integer userId) {
        cartService.clearCart(userId);
        return "redirect:/cart/" + userId;
    }
    @PostMapping("/remove")
    public String removeItemFromCart(@RequestParam(value = "userId", required = false) Integer userId,
                                     @RequestParam("goodsId") Integer goodsId) {
        if (userId == null) {
            userId = 1; // 仮のデフォルトユーザーID（適宜変更）
        }
        cartService.removeItemFromCart(userId, goodsId);
        return "redirect:/cart/" + userId;
    }
   
    
}