package com.example.moattravel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

	 List<Order> findByUserId(Integer user_id) ;

	Optional<Order> findByUserIdAndGoodsId(Integer user_id, Integer goods_id);

}
