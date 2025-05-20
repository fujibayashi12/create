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
import com.example.moattravel.entity.User;
import com.example.moattravel.service.GoodsService;

@Controller
@RequestMapping("/goodsList")
public class GoodsPageController {

	private final GoodsService goodsService;

	public GoodsPageController(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	//（ホーム）商品一覧を表示するメソッド
	@GetMapping("")
	public String showGoodsList(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<Goods> goodsPage = goodsService.getAllGoods(page);

		model.addAttribute("goodsList", goodsPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", goodsPage.getTotalPages());
		return "goodsList";
	}

	//詳細ページを開くメソッド
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

	//検索処理メソッド
	@GetMapping("/search")
	public String showSearchResults(@RequestParam(required = false) String keyword,
	                                @RequestParam(required = false) String category,
	                                @RequestParam(defaultValue = "0") int page, Model model) {
	    Page<Goods> goodsPage;

	    if (keyword == null || keyword.trim().isEmpty()) {
	        if (category != null && !category.equals("すべて")) { 
	            goodsPage = goodsService.searchGoodsByCategory(category, page);
	        } else {
	            goodsPage = goodsService.getAllGoods(page);
	        }
	    } else {
	        if ("すべて".equals(category)) {
	            goodsPage = goodsService.searchGoodsByKeyword(keyword, page);
	        } else {
	            goodsPage = goodsService.searchGoodsByCategoryAndKeyword(category, keyword, page);
	        }
	    }

	    model.addAttribute("goodsList", goodsPage.getContent());
	    model.addAttribute("selectedCategory", category); // ✅ そのまま渡す！
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", goodsPage.getTotalPages());

	    return "goodsList";
	}

	//ログイン中のユーザー情報を取得するメソッド 
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