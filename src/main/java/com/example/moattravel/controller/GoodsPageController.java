package com.example.moattravel.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moattravel.entity.Goods;
import com.example.moattravel.entity.User; // ユーザーエンティティ
import com.example.moattravel.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsPageController {

    private final GoodsService goodsService;

    public GoodsPageController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("")
    public String showGoodsList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Goods> goodsPage = goodsService.getAllGoods(page);

        model.addAttribute("goodsList", goodsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", goodsPage.getTotalPages());
        return "goodsList";
    }

    @GetMapping("/{id}")
    public String showGoodsDetailPage(@PathVariable Integer id, Model model, Principal principal) {
        Optional<Goods> goods = goodsService.getGoodsById(id);
        if (goods.isPresent()) {
            model.addAttribute("goods", goods.get());
            // principal からユーザーオブジェクトを生成してモデルに追加
            model.addAttribute("user", getUserFromPrincipal(principal));
            return "goodsDetail";
        } else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAddGoodsPage() {
        return "add";
    }

    @GetMapping("/search")
    public String showSearchResults(@RequestParam(required = false) String keyword,
                                    @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Goods> goodsPage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            goodsPage = goodsService.searchGoods(keyword, page);
        } else {
            goodsPage = goodsService.getAllGoods(page);
        }

        model.addAttribute("goodsList", goodsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", goodsPage.getTotalPages());
        return "goodsList";
    }

    // -------------------------------
    // Principal からユーザーを取得するメソッド（例）
    // ここでは簡単なダミー実装となっています。
    // 実際には UserRepository などを用いて principal.getName() からユーザー情報を取得するのが望ましいです。
    private User getUserFromPrincipal(Principal principal) {
        if (principal == null) {
            // 例えば null を返すか、もしくはデフォルトのユーザー情報を返すなど対応
            return null;
        }
        User user = new User();
        user.setId(1); // 必要に応じて実際のIDに置き換える
        user.setEmail(principal.getName());
        return user;
    }

}