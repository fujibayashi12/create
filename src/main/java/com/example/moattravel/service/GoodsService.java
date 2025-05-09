package com.example.moattravel.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return goodsRepository.save(goods);
    }

    // 商品情報を更新
    public Goods updateGoods(Integer goodsId, Goods newGoods) {
        return goodsRepository.findById(goodsId)
                .map(goods -> {
                    goods.setName(newGoods.getName());
                    goods.setPrice(newGoods.getPrice());
                    goods.setCategory(newGoods.getCategory());
                    return goodsRepository.save(goods);
                }).orElseThrow(() -> new RuntimeException("商品が見つかりません"));
    }

    // 商品を削除
    public void deleteGoods(Integer goodsId) {
        goodsRepository.deleteById(goodsId);
    }

    public List<Goods> searchGoods(String keyword) {
        return goodsRepository.findByNameContainingIgnoreCase(keyword.trim());  // ✅ 余分なスペースを削除して検索！
    }
	public List<Goods> getAllGoods() {
		return goodsRepository.findAll();
	}
	public Page<Goods> getAllGoods(int page) {
	    Pageable pageable = PageRequest.of(page, 20);  // ✅ 20件ごとのページネーション！
	    return goodsRepository.findAll(pageable);
	}

	public Page<Goods> searchGoods(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 20);
	    return goodsRepository.findByNameContainingIgnoreCase(keyword, pageable);
	}

	
}