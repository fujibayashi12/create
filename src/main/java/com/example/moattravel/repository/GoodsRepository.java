package com.example.moattravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods,Integer>{

	List<Goods> findByNameContainingIgnoreCase(String name);

	 Page<Goods> findByNameContainingIgnoreCase(String name, Pageable pageable);  // ✅ ページネーション対応！
	    Page<Goods> findAll(Pageable pageable);  // ✅ 全件取得もページネーション対応！
	}
