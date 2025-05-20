package com.example.moattravel.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GoodsRegisterForm {

	@NotBlank(message = "商品名を登録してください")
	private String name;
	
	@NotBlank(message = "概要を入力してください")
	private String description;
	
	@NotNull(message = "注文上限数を入力してください")
	private Integer orderCapacity;
	
	@NotNull(message = "在庫数を入力してください")
	private Integer stock;
	
	@NotBlank(message = "カテゴリを選択してください")
	private String category;
	
	@NotNull(message = "金額を入力してください")
	private Integer price;
	
	@NotBlank(message = "写真を貼り付けてください")
	private String imageUrl;

	//private MultipartFile imageFile;
}
