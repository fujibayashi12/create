package com.example.moattravel.Form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank(message = "氏名を入力してください。")
	private String name;
	
	@NotBlank(message = "フリガナを入力してください。")
	private String furigana;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
	
	@NotBlank(message = "メールアドレスを入力してください。")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください。")
	private String passWord;
	
	@NotBlank(message = "確認用パスワードを入力してください。")
	private String passWordConfirmation;
}
