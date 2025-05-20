package com.example.moattravel.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.moattravel.Form.GoodsEditForm;
import com.example.moattravel.entity.Goods;
import com.example.moattravel.repository.GoodsRepository;

@Service
public class GoodsService {

	private final GoodsRepository goodsRepository;

	public GoodsService(GoodsRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}

	// 商品一覧を取得
	public List<Goods> getGoods() {
		return goodsRepository.findAll();
	}

	// 商品IDで取得
	public Optional<Goods> getGoodsById(Integer goodsId) {
		return goodsRepository.findById(goodsId);
	}

	// 商品を追加
	public Goods addGoods(Goods goods) {
		// ここで作成日時と更新日時をセットする責任を持つ
        goods.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        goods.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return goodsRepository.save(goods);
    }

	//画像を保存してファイル名を返すメソッド
	//public String saveImageFile(MultipartFile imageFile) {
		//String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename(); // 一意のファイル名を作る
		//Path filePath = Paths.get("images/", fileName); // サーバーの保存パス

		//try {
		//	Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // 画像を保存
		//} catch (IOException e) {
		//	throw new RuntimeException("画像の保存に失敗しました", e);
		//}
		//return fileName;
	//}

	// 商品情報を編集
	public Goods editGoods(Integer goodsId, GoodsEditForm goodsEditForm) {
		Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);

		if (optionalGoods.isPresent()) {
			Goods goods = optionalGoods.get();
			goods.setName(goodsEditForm.getName());
			goods.setPrice(goodsEditForm.getPrice());
			goods.setCategory(goodsEditForm.getCategory());
			goods.setDescription(goodsEditForm.getDescription());
			goods.setOrderCapacity(goodsEditForm.getOrderCapacity());
			goods.setStock(goodsEditForm.getStock());

			// ✅ アップロード処理を削除して、画像URLを直接保存！
			goods.setImageUrl(goodsEditForm.getImageUrl());

			goods.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // ✅ 更新日時をセット！

			return goodsRepository.save(goods);
		} else {
			System.out.println("⚠ 商品 ID " + goodsId + " が見つかりません！");
			throw new RuntimeException("商品が見つかりません");
		}
	}

	// 商品を削除
	public void deleteGoods(Integer goodsId) {
		goodsRepository.deleteById(goodsId);
	}

	//キーワードの名前と合うものをDBから探す
	//public List<Goods> searchGoods(String keyword) {
	//	return goodsRepository.findByNameContainingIgnoreCase(keyword.trim()); // ✅ 余分なスペースを削除して検索！
	//}

	//DBにある商品を全て取得
	public List<Goods> getAllGoods() {
		return goodsRepository.findAll();
	}

	//DBにある商品を20件ごとに分けて、全て取得
	public Page<Goods> getAllGoods(int page) {
		Pageable pageable = PageRequest.of(page, 20); // ✅ 20件ごとのページネーション！
		return goodsRepository.findAll(pageable);
	}

	//キーワードの名前と合うものを20件ごとに分けて、DBから探す
	public Page<Goods> searchGoods(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 20);
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return goodsRepository.findAll(pageable);
	    } else {
	        return goodsRepository.findByNameContainingIgnoreCase(keyword, pageable);
	    }
	}

	public Page<Goods> searchGoodsByKeyword(String keyword, int page) {
		return goodsRepository.findByNameContainingIgnoreCase(keyword, PageRequest.of(page, 10));
	}

	public Page<Goods> searchGoodsByCategoryAndKeyword(String category, String keyword, int page) {
		return goodsRepository.findByCategoryAndNameContainingIgnoreCase(category, keyword, PageRequest.of(page, 10));
	}

	public Page<Goods> searchGoodsByCategory(String category, int page) {
		return goodsRepository.findByCategory(category, PageRequest.of(page, 10));
	}
}
