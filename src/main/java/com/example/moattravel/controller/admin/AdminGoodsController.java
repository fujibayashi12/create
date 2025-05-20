package com.example.moattravel.controller.admin;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moattravel.Form.GoodsEditForm;
import com.example.moattravel.Form.GoodsRegisterForm;
import com.example.moattravel.entity.Goods;
import com.example.moattravel.entity.User;
import com.example.moattravel.service.GoodsService;
import com.example.moattravel.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminGoodsController {

	private final GoodsService goodsService;

	public AdminGoodsController(GoodsService goodsService, UserService userService) {
		this.goodsService = goodsService;

	}
	@GetMapping("/dashboard")
    public String showDashboard() {
        return "admin/dashboard"; // ✅ `dashboard.html` を表示！
    }


	//商品一覧ページを開くメソッド
	@GetMapping("/goodsList")
	public String showGoodsList(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<Goods> goodsPage = goodsService.getAllGoods(page);

		model.addAttribute("goodsList", goodsPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", goodsPage.getTotalPages());
		return "admin/goodsList";
	}

	//詳細ページを開くメソッド
	@GetMapping("/goodsList/{id}")
	public String showGoodsDetailPage(@PathVariable Integer id, Model model, Principal principal) {
	    Optional<Goods> goods = goodsService.getGoodsById(id);
	    if (goods.isPresent()) {
	        model.addAttribute("goods", goods.get());
	        model.addAttribute("user", getUserFromPrincipal(principal));
	        return "admin/goodsDetail"; // ✅ 正しいビューを返す！
	    } else {
	        return "redirect:/admin/goodsList";
	    }
	}

	//検索処理メソッド
	@GetMapping("/goodsList/search")
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

		return "admin/goodsList";
	}
	
	//新規登録のページへ遷移
	@GetMapping("/goodsList/goodsRegister")
	public String showRegisterPage(Model model) {
	    model.addAttribute("goodsRegisterForm", new GoodsRegisterForm()); // フォームをモデルに追加
	    return "admin/goodsRegister"; // 商品登録ページを表示
	}

	//商品を新規登録するメソッド
	@PostMapping("/goodsList/goodsRegister")
	public String register(GoodsRegisterForm goodsRegisterForm, Model model, RedirectAttributes redirectAttributes) {
		Goods goods = new Goods(); // ✅ `Goods` のインスタンスを作成

		// ✅ フォームの値を手動でコピー
		goods.setName(goodsRegisterForm.getName());
		goods.setDescription(goodsRegisterForm.getDescription());
		goods.setStock(goodsRegisterForm.getStock());
		goods.setCategory(goodsRegisterForm.getCategory());
		goods.setPrice(goodsRegisterForm.getPrice());
		goods.setOrderCapacity(goodsRegisterForm.getOrderCapacity());

		goods.setImageUrl(goodsRegisterForm.getImageUrl());
		
		goods.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		goodsService.addGoods(goods); // ✅ `Goods` をサービスへ渡す

		redirectAttributes.addFlashAttribute("successMessage", "商品が正常に登録されました！");
		return "redirect:/admin/goodsList"; // 商品一覧へリダイレクト
	}

	//商品編集ページへ遷移
	@GetMapping("/goodsList/goodsEdit/{id}")
	public String showEditPage(@PathVariable("id") Integer goodsId, Model model) {
	    Optional<Goods> goodsOpt = goodsService.getGoodsById(goodsId);
	    
	    if (goodsOpt.isPresent()) {
	        Goods goods = goodsOpt.get();
	        GoodsEditForm goodsEditForm = new GoodsEditForm();
	        
	        goodsEditForm.setName(goods.getName());
	        goodsEditForm.setPrice(goods.getPrice());
	        goodsEditForm.setCategory(goods.getCategory());
	        goodsEditForm.setDescription(goods.getDescription());
	        goodsEditForm.setOrderCapacity(goods.getOrderCapacity());
	        goodsEditForm.setStock(goods.getStock());
	        goodsEditForm.setImageUrl(goods.getImageUrl());

	        model.addAttribute("goodsEditForm", goodsEditForm);
	        model.addAttribute("goodsId", goodsId);

	        return "admin/goodsEdit"; // ✅ 正しいビューを返す！
	    } else {
	        return "redirect:/admin/goodsList";
	    }
	}
	
	
	//商品を編集するメソッド
	@PostMapping("/goodsList/goodsEdit/{id}")
	public String edit(@PathVariable("id") Integer goodsId, @ModelAttribute GoodsEditForm goodsEditForm,
	        RedirectAttributes redirectAttributes) {

	    // ✅ 既存の商品情報を取得
	    Optional<Goods> existingGoods = goodsService.getGoodsById(goodsId);

	    if (existingGoods.isPresent()) {
	        Goods goods = existingGoods.get(); // 既存の商品情報を取得

	        // ✅ フォームの値を手動でコピー（編集処理）
	        goods.setName(goodsEditForm.getName());
	        goods.setDescription(goodsEditForm.getDescription());
	        goods.setStock(goodsEditForm.getStock());
	        goods.setCategory(goodsEditForm.getCategory());
	        goods.setPrice(goodsEditForm.getPrice());
	        goods.setOrderCapacity(goodsEditForm.getOrderCapacity());

	        // ✅ 画像のURLを直接保存（アップロード処理なし）
	        goods.setImageUrl(goodsEditForm.getImageUrl());

	        goods.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // ✅ 更新日時をセット！

	        goodsService.editGoods(goodsId, goodsEditForm); // ✅ `goodsId` と `goods` を渡して更新！
	        redirectAttributes.addFlashAttribute("successMessage", "商品詳細を更新しました。");
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "商品が見つかりませんでした！");
	    }

	    return "redirect:/admin/goodsList";
	}

	//商品を削除するメソッド
	@PostMapping("/goodsList/delete")
	public String delete(@RequestParam(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		goodsService.deleteGoods(id);

		redirectAttributes.addFlashAttribute("successMessage", "商品を削除しました。");

		return "redirect:/admin/goodsList";
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