package com.example.moattravel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moattravel.entity.Goods;
import com.example.moattravel.service.GoodsService;

@RestController // ✅ API専用に変更！
@RequestMapping("/api/goods") // ✅ API用のURLに変更！
public class GoodsController {

	private final GoodsService goodsService;

	public GoodsController(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	// 商品一覧を取得（JSONで返す）
	@GetMapping
	public List<Goods> getGoods() {
		return goodsService.getGoods();
	}

	// 商品IDで取得（JSONで返す）
	@GetMapping("/{id}")
	public ResponseEntity<Goods> getGoodsById(@PathVariable Integer id) {
		Optional<Goods> goods = goodsService.getGoodsById(id);
		return goods.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// 商品追加（JSONで返す）
	@PostMapping
	public Goods addGoods(@RequestBody Goods goods) {
		return goodsService.addGoods(goods);
	}

	// 商品情報を更新（JSONで返す）
	@PutMapping("/{id}")
	public ResponseEntity<Goods> updateGoods(@PathVariable Integer id, @RequestBody Goods newGoods) {
		return ResponseEntity.ok(goodsService.updateGoods(id, newGoods));
	}

	// 商品を削除（JSONで返す）
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGoods(@PathVariable Integer id) {
		goodsService.deleteGoods(id);
		return ResponseEntity.noContent().build();
	}
	
	
}