package com.example.moattravel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.moattravel.entity.Goods;
import com.example.moattravel.repository.GoodsRepository;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

	@InjectMocks
	private GoodsService goodsService;

	@Mock
	private GoodsRepository goodsRepository;

	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceTest.class);

	private Goods goods1;
	private Goods goods2;
	private Goods goods3;

	@BeforeEach
	void setup() {
		//仮のデータを作成
		goods1 = new Goods();
		goods1.setId(1);
		goods1.setName("スマホ");
		goods1.setCategory("スマホアクセサリ");
		goods1.setPrice(1500);
		goods1.setStock(100);
		goods1.setOrderCapacity(10);
		goods1.setImageUrl("URL1");

		goods2 = new Goods();
		goods2.setId(2);
		goods2.setName("スマホ");
		goods2.setCategory("スマホアクセサリ");
		goods2.setPrice(1500);
		goods2.setStock(100);
		goods2.setOrderCapacity(10);
		goods2.setImageUrl("URL1");

		goods3 = new Goods();
		goods3.setId(3);
		goods3.setName("デスクライト");
		goods3.setCategory("照明");
		goods3.setPrice(1500);
		goods3.setStock(100);
		goods3.setOrderCapacity(10);
		goods3.setImageUrl("URL1");
	}

	@Test
	@DisplayName("コンストラクタ")
	void testGoodsService() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品を取得")
	void testGetGoods() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品Idを取得")
	void testGetGoodsById() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品を追加")
	void testAddGoods() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("画像ファイルを保存")
	void testSaveImageFile() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品詳細を編集")
	void testEditGoods() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品を削除")
	void testDeleteGoods() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品名の文字列を取得")
	void testSearchGoodsString() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("商品全選択")
	void testGetAllGoods() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("goodsIdを全取得")
	void testGetAllGoodsInt() {
		fail("まだ実装されていません");
	}

	@Test
	@DisplayName("これなに？")
	void testSearchGoodsStringInt() {
		fail("まだ実装されていません");
	}

	@Test
	//検索キーワード 'スマホ' に対して、"スマホ" を含んだ商品がちゃんと返ってくるテスト
	@DisplayName("キーワード検索") //正常系
	void testSearchGoodsByKeyword() {

		Pageable pageable = PageRequest.of(0, 10);

		// モックの設定 - "スマホ" というキーワードで検索
		Mockito.when(goodsRepository.findByNameContainingIgnoreCase("スマホ", pageable))
				.thenReturn(new PageImpl<>(List.of(goods1, goods2)));

		// テスト対象のメソッドを実行
		Page<Goods> result = goodsService.searchGoodsByKeyword("スマホ", 0);

		//assertEquals(期待値,実際の値)、検索結果が正しいかチェック
		assertEquals(2, result.getTotalElements());

		//検索結果の合計件数が expected の 2 件と一致するか確認
		assertTrue(result.getContent().stream().allMatch(goods -> goods.getName().contains("スマホ")));

		logger.info("期待値: {}, 実際の値: {}", 2, result.getTotalElements());
		logger.info("検索結果のリスト:{} ", result.getContent());

	}

	@Test
	@DisplayName("キーワードとカテゴリーの同時検索")
	void testSearchGoodsByCategoryAndKeyword() {

		Pageable pageable = PageRequest.of(0, 10);
		// モックは引数に合わせて「スマホ」で返す設定にする
		Mockito.when(goodsRepository.findByCategoryAndNameContainingIgnoreCase("スマホアクセサリ", "スマホ", pageable))
				.thenReturn(new PageImpl<>(List.of(goods1, goods2)));

		// 実際の呼び出しも同じキーワード「スマホ」で呼ぶ
		Page<Goods> result = goodsService.searchGoodsByCategoryAndKeyword("スマホアクセサリ", "スマホ", 0);

		// 件数の検証
		assertEquals(2, result.getTotalElements());
		// カテゴリーがちゃんと一致してるか
		assertTrue(result.stream().allMatch(goods -> goods.getCategory().equals("スマホアクセサリ")));
		// 名前に「スマホ」が含まれているか（完全一致じゃなくてもよければcontainsにする）
		assertTrue(result.getContent().stream().allMatch(goods -> goods.getName().contains("スマホ")));
	}

	@Test
	@DisplayName("カテゴリーでの検索 - 正常系")
	void testSearchGoodsByCategory() {

		Pageable pageable = PageRequest.of(0, 10);

		Mockito.when(goodsRepository.findByCategory("スマホアクセサリ", pageable))
				.thenReturn(new PageImpl<>(List.of(goods1, goods2)));
		Page<Goods> result = goodsService.searchGoodsByCategory("スマホアクセサリ", 0);
		// goods インスタンスのフィールドが正しくセットされているか検証する
		//assertEquals(期待値,実際の値)
		assertEquals(2, result.getTotalElements());
		assertTrue(result.stream().allMatch(goods -> goods.getCategory().equals("スマホアクセサリ")));
	}

	@Test
	@DisplayName("カテゴリーでの検索 - 異なるカテゴリーの商品が混入した場合")
	void testSearchGoodsByCategory_incorrectCategoryIncluded() {

		Pageable pageable = PageRequest.of(0, 10);

		Mockito.when(goodsRepository.findByCategory("スマホアクセサリ", pageable))
				.thenReturn(new PageImpl<>(List.of(goods1, goods2, goods3)));

		Page<Goods> result = goodsService.searchGoodsByCategory("スマホアクセサリ", 0);

		// goods インスタンスのフィールドが正しくセットされているか検証する
		//assertEquals(期待値,実際の値)
		assertEquals(3, result.getTotalElements());// 検索結果は3件になるはず

		// 全ての商品が検索したカテゴリー ("スマホアクセサリ") と一致しないことを検証
		assertFalse(result.getContent().stream().allMatch(goods -> goods.getCategory().equals("スマホアクセサリ")));

		// 意図したカテゴリー ("スマホアクセサリ") の商品が含まれていることを検証
		assertTrue(result.getContent().stream()
				.anyMatch(goods -> goods.getCategory().equals("スマホアクセサリ") && goods.getId() == 1));
		assertTrue(result.getContent().stream()
				.anyMatch(goods -> goods.getCategory().equals("スマホアクセサリ") && goods.getId() == 2));

		// 意図しないカテゴリー ("照明") の商品が含まれていることを検証
		assertTrue(result.stream().anyMatch(goods -> goods.getCategory().equals("照明") && goods.getId() == 3));

	}
}
