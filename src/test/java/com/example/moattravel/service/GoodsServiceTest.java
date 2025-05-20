package com.example.moattravel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

import com.example.moattravel.Form.GoodsEditForm;
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
	private Goods goods4;
	private Goods goods5;
	private Goods goods6;
	private Goods goods7;
	private Goods goods8;
	private Goods goods9;
	private Goods goods10;
	private Goods goods11;
	private Goods goods12;
	private Goods goods13;
	private Goods goods14;
	private Goods goods15;
	private Goods goods16;
	private Goods goods17;
	private Goods goods18;
	private Goods goods19;
	private Goods goods20;
	private Goods goods21;

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

		goods4 = new Goods();
		goods4.setId(4);
		goods4.setName("USB-C");
		goods4.setCategory("PC周辺機器");
		goods4.setPrice(1500);
		goods4.setStock(100);
		goods4.setOrderCapacity(10);
		goods4.setImageUrl("URL4");

		goods5 = new Goods();
		goods5.setId(5);
		goods5.setName("スマホ三脚");
		goods5.setCategory("スマホアクセサリ");
		goods5.setPrice(1500);
		goods5.setStock(100);
		goods5.setOrderCapacity(10);
		goods5.setImageUrl("URL5");

		goods6 = new Goods();
		goods6.setId(6);
		goods6.setName("エコバック");
		goods6.setCategory("日用品");
		goods6.setPrice(1500);
		goods6.setStock(100);
		goods6.setOrderCapacity(10);
		goods6.setImageUrl("URL6");

		goods7 = new Goods();
		goods7.setId(7);
		goods7.setName("USB-C");
		goods7.setCategory("PC周辺機器");
		goods7.setPrice(1500);
		goods7.setStock(100);
		goods7.setOrderCapacity(10);
		goods7.setImageUrl("URL7");

		goods8 = new Goods();
		goods8.setId(8);
		goods8.setName("耐熱マグカップ");
		goods8.setCategory("キッチン用品");
		goods8.setPrice(1500);
		goods8.setStock(100);
		goods8.setOrderCapacity(10);
		goods8.setImageUrl("URL");

		goods9 = new Goods();
		goods9.setId(9);
		goods9.setName("USB-C");
		goods9.setCategory("PC周辺機器");
		goods9.setPrice(1500);
		goods9.setStock(100);
		goods9.setOrderCapacity(10);
		goods9.setImageUrl("URL7");

		goods10 = new Goods();
		goods10.setId(10);
		goods10.setName("デスクライト");
		goods10.setCategory("照明");
		goods10.setPrice(1500);
		goods10.setStock(100);
		goods10.setOrderCapacity(10);
		goods10.setImageUrl("URL1");

		goods11 = new Goods();
		goods11.setId(11);
		goods11.setName("スマホ三脚");
		goods11.setCategory("スマホアクセサリ");
		goods11.setPrice(1500);
		goods11.setStock(100);
		goods11.setOrderCapacity(10);
		goods11.setImageUrl("URL5");

		goods12 = new Goods();
		goods12.setId(12);
		goods12.setName("USB-C");
		goods12.setCategory("PC周辺機器");
		goods12.setPrice(1500);
		goods12.setStock(100);
		goods12.setOrderCapacity(10);
		goods12.setImageUrl("URL7");

		goods13 = new Goods();
		goods13.setId(13);
		goods13.setName("デスクライト");
		goods13.setCategory("照明");
		goods13.setPrice(1500);
		goods13.setStock(100);
		goods13.setOrderCapacity(10);
		goods13.setImageUrl("URL1");

		goods14 = new Goods();
		goods14.setId(14);
		goods14.setName("スマホ三脚");
		goods14.setCategory("スマホアクセサリ");
		goods14.setPrice(1500);
		goods14.setStock(100);
		goods14.setOrderCapacity(10);
		goods14.setImageUrl("URL5");

		goods15 = new Goods();
		goods15.setId(15);
		goods15.setName("USB-C");
		goods15.setCategory("PC周辺機器");
		goods15.setPrice(1500);
		goods15.setStock(100);
		goods15.setOrderCapacity(10);
		goods15.setImageUrl("URL7");

		goods16 = new Goods();
		goods16.setId(16);
		goods16.setName("USB-C");
		goods16.setCategory("PC周辺機器");
		goods16.setPrice(1500);
		goods16.setStock(100);
		goods16.setOrderCapacity(10);
		goods16.setImageUrl("URL7");

		goods17 = new Goods();
		goods17.setId(17);
		goods17.setName("デスクライト");
		goods17.setCategory("照明");
		goods17.setPrice(1500);
		goods17.setStock(100);
		goods17.setOrderCapacity(10);
		goods17.setImageUrl("URL1");

		goods18 = new Goods();
		goods18.setId(18);
		goods18.setName("USB-C");
		goods18.setCategory("PC周辺機器");
		goods18.setPrice(1500);
		goods18.setStock(100);
		goods18.setOrderCapacity(10);
		goods18.setImageUrl("URL4");

		goods19 = new Goods();
		goods19.setId(19);
		goods19.setName("スマホ三脚");
		goods19.setCategory("スマホアクセサリ");
		goods19.setPrice(1500);
		goods19.setStock(100);
		goods19.setOrderCapacity(10);
		goods19.setImageUrl("URL5");

		goods20 = new Goods();
		goods20.setId(20);
		goods20.setName("エコバック");
		goods20.setCategory("日用品");
		goods20.setPrice(1500);
		goods20.setStock(100);
		goods20.setOrderCapacity(10);
		goods20.setImageUrl("URL6");

		goods21 = new Goods();
		goods21.setId(21);
		goods21.setName("エコバック");
		goods21.setCategory("日用品");
		goods21.setPrice(1500);
		goods21.setStock(100);
		goods21.setOrderCapacity(10);
		goods21.setImageUrl("URL6");
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
	@DisplayName("商品を追加 - 正常系: 新しい商品を正しく保存できる")
	void testAddGoods_successfulAddition() {
	    // 1. テストデータの準備 (変更なし)
	    Goods newGoods = new Goods();
	    newGoods.setName("新しいPC");
	    newGoods.setPrice(150000);
	    newGoods.setCategory("PC周辺機器");
	    newGoods.setDescription("高性能デスクトップPC");
	    newGoods.setOrderCapacity(2);
	    newGoods.setStock(20);
	    newGoods.setImageUrl("new_pc.jpg");
	    // ここで createdAt は設定しない（@PrePersistで設定されるため）
	    // updatedAt はGoodsクラスの初期化時に設定されるので、そのままにしておく

	    // 2. モックの設定
	    Mockito.when(goodsRepository.save(Mockito.any(Goods.class))).thenAnswer(invocation -> {
	        Goods goodsToSave = invocation.getArgument(0);

	        // ★★★ ここが修正点！ @PrePersist の挙動をモックで再現 ★★★
	        // サービスが setCreatedAt を呼んでいても、@PrePersist が優先されるため、
	        // save() に渡される goodsToSave は createdAt が null であると想定。
	        // そして、save() が返却するオブジェクトにcreatedAtが設定されると模擬する。
	        // goodsToSave.setCreatedAt(Timestamp.from(Instant.now())); // これでも良いが、JPAの挙動に合わせるため以下のように新しいインスタンスで返す
	        
	        Goods returnedGoods = new Goods(); // save()が返すと仮定する新しいGoodsオブジェクト
	        returnedGoods.setId(99); // DBが割り当てた仮のID

	        // サービスが設定したプロパティと、デフォルト値で設定されたupdatedAtをコピー
	        returnedGoods.setName(goodsToSave.getName());
	        returnedGoods.setPrice(goodsToSave.getPrice());
	        returnedGoods.setCategory(goodsToSave.getCategory());
	        returnedGoods.setDescription(goodsToSave.getDescription());
	        returnedGoods.setOrderCapacity(goodsToSave.getOrderCapacity());
	        returnedGoods.setStock(goodsToSave.getStock());
	        returnedGoods.setImageUrl(goodsToSave.getImageUrl());
	        
	        // ★★★ createdAt と updatedAt の処理を再考 ★★★
	        // GoodsService.addGoods() は createdAt と updatedAt を設定しています。
	        // そして Goods クラスには @PrePersist があります。
	        // 実際の動作では @PrePersist が実行されるため、service.addGoods() 内の setCreatedAt は上書きされます。
	        // updatedAt は Goods クラスの初期化時に設定され、service.addGoods() でも上書きされます。
	        
	        // したがって、save() に渡される goodsToSave は、
	        // service.addGoods() で設定された createdAt と updatedAt を持つはずです。
	        // そして、save() が返す returnedGoods は、IDとcreatedAt (@PrePersist由来)を持つべきです。
	        
	        // ここでは、service.addGoods() が設定した createdAt と updatedAt をコピー
	        // もし @PrePersist が優先されると考えるなら、returnedGoods.setCreatedAt(Timestamp.from(Instant.now())); を使う
	        // しかし、addGoods のテストでは「サービスが設定した値」を検証したいので、goodsToSaveからコピーする方が適切
	        returnedGoods.setCreatedAt(goodsToSave.getCreatedAt()); 
	        returnedGoods.setUpdatedAt(goodsToSave.getUpdatedAt()); 

	        return returnedGoods;
	    });

	    // 3. テスト対象のメソッドを実行 (変更なし)
	    Goods resultGoods = goodsService.addGoods(newGoods);

	    // 4. 検証

	    // a. goodsRepository.save() が、正しい内容のGoodsオブジェクトで1回呼び出されたことを確認
	    Mockito.verify(goodsRepository, Mockito.times(1)).save(Mockito.argThat(goodsInSave ->
	        // save()に渡される Goods オブジェクトのIDは、サービスが設定しないため **nullであるべき**
	        goodsInSave.getId() == null && 

	        Objects.equals(goodsInSave.getName(), newGoods.getName()) &&
	        Objects.equals(goodsInSave.getPrice(), newGoods.getPrice()) &&
	        Objects.equals(goodsInSave.getCategory(), newGoods.getCategory()) &&
	        Objects.equals(goodsInSave.getDescription(), newGoods.getDescription()) &&
	        Objects.equals(goodsInSave.getOrderCapacity(), newGoods.getOrderCapacity()) &&
	        Objects.equals(goodsInSave.getStock(), newGoods.getStock()) &&
	        Objects.equals(goodsInSave.getImageUrl(), newGoods.getImageUrl()) &&
	        
	        // ★★★ ここも修正点！ createdAt と updatedAt がサービスによって設定されていることを検証 ★★★
	        // @PrePersist は save() の後なので、save() に渡される時点ではサービスが設定した値が入っているはず
	        goodsInSave.getCreatedAt() != null &&
	        goodsInSave.getUpdatedAt() != null &&
	        // 時刻はテスト実行時のわずかなズレを考慮し、現在時刻から1秒以内であることを検証
	        goodsInSave.getCreatedAt().after(new Timestamp(System.currentTimeMillis() - 1000)) &&
	        goodsInSave.getUpdatedAt().after(new Timestamp(System.currentTimeMillis() - 1000))
	    ));

	    // b. addGoods メソッドが返したオブジェクトが期待通りに設定されていることを確認 (変更なし)
	    assertEquals(99, resultGoods.getId(), "返された商品のIDが期待通りでない");
	    assertEquals(newGoods.getName(), resultGoods.getName(), "返された商品の名前が期待通りでない");
	    assertEquals(newGoods.getPrice(), resultGoods.getPrice(), "返された商品の価格が期待通りでない");
	    assertEquals(newGoods.getCategory(), resultGoods.getCategory(), "返された商品のカテゴリが期待通りでない");
	    assertEquals(newGoods.getDescription(), resultGoods.getDescription(), "返された商品の説明が期待通りでない");
	    assertEquals(newGoods.getOrderCapacity(), resultGoods.getOrderCapacity(), "返された商品の注文上限数が期待通りでない");
	    assertEquals(newGoods.getStock(), resultGoods.getStock(), "返された商品の在庫数が期待通りでない");
	    assertEquals(newGoods.getImageUrl(), resultGoods.getImageUrl(), "返された商品の画像URLが期待通りでない");

	    // createdAt と updatedAt が設定されていることを確認
	    assertNotNull(resultGoods.getCreatedAt(), "返された商品の作成日時がnullである");
	    assertNotNull(resultGoods.getUpdatedAt(), "返された商品の更新日時がnullである");
	    assertTrue(resultGoods.getCreatedAt().after(new Timestamp(System.currentTimeMillis() - 1000)), "返された商品の作成日時が古すぎる");
	    assertTrue(resultGoods.getUpdatedAt().after(new Timestamp(System.currentTimeMillis() - 1000)), "返された商品の更新日時が古すぎる");
	}
	//@Test
	//@DisplayName("画像ファイルを保存")
	//void testSaveImageFile() {
	//fail("まだ実装されていません");
	//}

	@Test //うまくテストが実行されないのでスルーしてます
	@DisplayName("商品詳細を編集 - 正常系: 存在するIDの商品を正しく更新できる")
	void testEditGoods_existingId_success() {
		// 1. テストデータの準備
		Integer targetGoodsId = 1; // 更新対象のID
		// goods1 は @BeforeEach で設定済みだが、今回は更新前の状態として使用
		// そのため、一時的に goods1 のコピーを作成するか、更新前の状態を明確にする

		// 更新前の goods1 の状態を模倣するために、モックが返すオブジェクトを別途作成
		// こうすることで、goods1 自体を変更せず、テストが独立性を保てます。
		Goods existingGoods = new Goods();
		existingGoods.setId(1);
		existingGoods.setName("古いスマホ");
		existingGoods.setPrice(10000);
		existingGoods.setCategory("古いカテゴリ");
		existingGoods.setDescription("古い説明");
		existingGoods.setOrderCapacity(5);
		existingGoods.setStock(50);
		existingGoods.setImageUrl("old_url.jpg");
		existingGoods.setCreatedAt(new Timestamp(System.currentTimeMillis() - 100000)); // 少し前の時間

		// 更新する情報を含む GoodsEditForm を作成
		GoodsEditForm editForm = new GoodsEditForm();
		editForm.setName("新しいスマホ");
		editForm.setPrice(20000);
		editForm.setCategory("スマホアクセサリ");
		editForm.setDescription("最新のスマホアクセサリ");
		editForm.setOrderCapacity(10);
		editForm.setStock(100);
		editForm.setImageUrl("new_url.jpg");

		// 2. モックの設定
		// goodsRepository.findById(targetGoodsId) が呼び出されたら、既存の商品 (existingGoods) を返す
		Mockito.when(goodsRepository.findById(targetGoodsId)).thenReturn(Optional.of(existingGoods));

		// goodsRepository.save(任意のGoodsオブジェクト) が呼び出されたら、その引数をそのまま返す
		// これは、save メソッドが更新されたエンティティを返すことをシミュレートします。
		// Mockito.any(Goods.class) は、Goods 型の任意のオブジェクトを引数として受け入れることを意味します。
		Mockito.when(goodsRepository.save(Mockito.any(Goods.class))).thenAnswer(invocation -> {
			// save メソッドに渡された引数（Goodsオブジェクト）をそのまま返す
			// これにより、テストで検証したい更新後のGoodsオブジェクトが返される
			return invocation.getArgument(0);
		});

		// 3. テスト対象のメソッドを実行
		Goods updatedGoods = goodsService.editGoods(targetGoodsId, editForm);

		// 4. 検証
		// a. goodsRepository.findById が正しいIDで1回呼び出されたことを確認
		Mockito.verify(goodsRepository, Mockito.times(1)).findById(targetGoodsId);

		// b. goodsRepository.save が更新されたGoodsオブジェクトで1回呼び出されたことを確認
		// Mockito.argThat を使用して、save に渡された Goods オブジェクトの内容を検証できます
		Mockito.verify(goodsRepository, Mockito.times(1))
				.save(Mockito.argThat(goods -> goods.getId().equals(targetGoodsId) && // ID は変わらない
						goods.getName().equals(editForm.getName()) && // 名前が更新されている
						goods.getPrice().equals(editForm.getPrice()) && // 価格が更新されている
						goods.getCategory().equals(editForm.getCategory()) && // カテゴリが更新されている
						goods.getDescription().equals(editForm.getDescription()) && // 説明が更新されている
						goods.getOrderCapacity().equals(editForm.getOrderCapacity()) && // 注文許容量が更新されている
						goods.getStock().equals(editForm.getStock()) && // 在庫が更新されている
						goods.getImageUrl().equals(editForm.getImageUrl()) && // 画像URLが更新されている
						// updatedAt が更新されていること (null でないこと、かつある程度新しい時間であること)
						goods.getUpdatedAt() != null && goods.getUpdatedAt().after(existingGoods.getCreatedAt())));

		// c. editGoods メソッドが返したオブジェクトが期待通りに更新されていることを確認
		assertEquals(targetGoodsId, updatedGoods.getId());
		assertEquals(editForm.getName(), updatedGoods.getName());
		assertEquals(editForm.getPrice(), updatedGoods.getPrice());
		assertEquals(editForm.getCategory(), updatedGoods.getCategory());
		assertEquals(editForm.getDescription(), updatedGoods.getDescription());
		assertEquals(editForm.getOrderCapacity(), updatedGoods.getOrderCapacity());
		assertEquals(editForm.getStock(), updatedGoods.getStock());
		assertEquals(editForm.getImageUrl(), updatedGoods.getImageUrl());
		// updatedAt が更新されていること
		assertNotNull(updatedGoods.getUpdatedAt());
		assertTrue(updatedGoods.getUpdatedAt().after(existingGoods.getCreatedAt()));
	}

	@Test
	@DisplayName("商品を削除 :正常系")
	void testDeleteGoods() {

		Integer goodsIdToDelete = 15;

		goodsService.deleteGoods(goodsIdToDelete);

		Mockito.verify(goodsRepository, Mockito.times(1)).deleteById(goodsIdToDelete);

		// 削除後に同じ ID で検索
		Optional<Goods> resultAfterDeletion = goodsService.getGoodsById(goodsIdToDelete);

		// 検証: 検索結果が空であることを確認
		assertTrue(resultAfterDeletion.isEmpty());
	}

	@Test
	@DisplayName("商品を削除 - 削除後に存在しないことを確認 :正常系")
	void testDeleteGoods_notExistsAfterDeletion() {
		// 削除したい goodsId を設定
		Integer goodsIdToDelete = 15;

		// まず、削除対象の商品が存在するようにモックを設定
		Mockito.when(goodsRepository.findById(goodsIdToDelete)).thenReturn(Optional.of(goods15));

		// 削除処理を実行
		goodsService.deleteGoods(goodsIdToDelete);

		// 次に、削除後に同じ ID で検索した際に Optional.empty() が返ってくるようにモックを設定
		Mockito.when(goodsRepository.findById(goodsIdToDelete)).thenReturn(Optional.empty());

		// 削除後に同じ ID で検索
		Optional<Goods> resultAfterDeletion = goodsService.getGoodsById(goodsIdToDelete);

		// 検証: 検索結果が空であることを確認
		assertTrue(resultAfterDeletion.isEmpty());
	}

	@Test
	@DisplayName("商品を削除 - 異常系: 存在しないIDを指定した場合、他の商品は影響を受けない")
	void testDeleteGoods_nonExistingIdDoesNotAffectOthers() {
		Integer nonExistingGoodsId = 999; // 存在しないID

		// モックの設定: deleteById が呼び出されたときに何も起きない (void メソッドのデフォルト)
		// 明示的に指定することも可能だが、通常は不要: Mockito.doNothing().when(goodsRepository).deleteById(nonExistingGoodsId);

		// 事前条件として、goods1とgoods2が存在するようにモックを設定
		// (これはgetGoodsByIdなどの呼び出しがある場合に必要になる。deleteGoods単体なら不要だが、
		//  念のためサービスにgetGoodsByIdのモックを追加しておくことで、その後の確認が可能)
		Mockito.when(goodsRepository.findById(1)).thenReturn(Optional.of(goods1));
		Mockito.when(goodsRepository.findById(2)).thenReturn(Optional.of(goods2));

		// テスト対象のメソッドを実行 (存在しないIDで削除を試みる)
		goodsService.deleteGoods(nonExistingGoodsId);

		// 検証1: deleteById メソッドが、存在しないIDで1回だけ呼び出されたことを確認
		Mockito.verify(goodsRepository, Mockito.times(1)).deleteById(nonExistingGoodsId);

		// 検証2: goods1 が削除されていないことを確認 (getGoodsById を呼び出す)
		// 削除後も goods1 が存在するようにモックを継続
		Mockito.when(goodsRepository.findById(1)).thenReturn(Optional.of(goods1));
		Optional<Goods> goods1AfterDeletion = goodsService.getGoodsById(1);
		assertTrue(goods1AfterDeletion.isPresent());
		assertEquals(goods1, goods1AfterDeletion.get());

		// 検証3: goods2 が削除されていないことを確認
		Mockito.when(goodsRepository.findById(2)).thenReturn(Optional.of(goods2));
		Optional<Goods> goods2AfterDeletion = goodsService.getGoodsById(2);
		assertTrue(goods2AfterDeletion.isPresent());
		assertEquals(goods2, goods2AfterDeletion.get());
	}

	//@Test
	//@DisplayName("商品名の文字列を取得")
	//void testSearchGoodsString() {
	//	fail("まだ実装されていません");
	//}

	@Test
	@DisplayName("DB内の商品を全て取得 - 正常系: ")
	void testGetAllGoods() {
		// テストデータとして使用する全ての商品リストを作成
		List<Goods> allGoods = List.of(goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8, goods9, goods10,
				goods11, goods12, goods13, goods14, goods15, goods16, goods17, goods18, goods19, goods20, goods21);

		// モックの設定: goodsRepository.findAll() が呼び出されたら、allGoods を返すように設定
		Mockito.when(goodsRepository.findAll()).thenReturn(allGoods);

		// テスト対象のメソッドを実行
		List<Goods> result = goodsService.getAllGoods();

		// 検証:
		// 1. 結果として返ってきたリストのサイズが、期待する商品の数と一致することを確認
		assertEquals(allGoods.size(), result.size());

		// 2. 結果として返ってきたリストの内容が、期待する全ての商品を含んでいることを確認
		assertTrue(result.containsAll(allGoods));
	}

	@Test
	@DisplayName("DB内の商品を全て取得 - 異常系: DBが空の場合")
	void testGetAllGoods_emptyDatabase() {
		// モックの設定: goodsRepository.findAll() が呼び出されたら、空のリストを返すように設定
		Mockito.when(goodsRepository.findAll()).thenReturn(Collections.emptyList());

		// テスト対象のメソッドを実行
		List<Goods> result = goodsService.getAllGoods();

		// 検証: 結果として返ってきたリストが空であること
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("IDで商品を1件取得 - 正常系: 存在するIDの場合")
	void testGetGoodsById_existingId() {
		// テストデータとして使用する goodsId
		Integer goodsId = 10;

		// モックの設定: goodsRepository.findById(goodsId) が呼び出されたら、Optional.of(goods10) を返すように設定
		Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.of(goods10));

		// テスト対象のメソッドを実行
		Optional<Goods> result = goodsService.getGoodsById(goodsId);

		// 検証:
		// 1. 結果として Optional が返ってきており、値が存在することを確認
		assertTrue(result.isPresent());

		// 2. Optional から取り出した Goods オブジェクトが、期待する goods10 と等しいことを確認
		assertEquals(goods10, result.get());
	}

	@Test
	@DisplayName("IDで商品を1件取得 - 異常系: 存在しないIDの場合")
	void testGetGoodsById_nonExistingId() {
		// 存在しないであろう goodsId を設定
		Integer nonExistingGoodsId = 999;

		// モックの設定: goodsRepository.findById(nonExistingGoodsId) が呼び出されたら、Optional.empty() を返すように設定
		Mockito.when(goodsRepository.findById(nonExistingGoodsId)).thenReturn(Optional.empty());

		// テスト対象のメソッドを実行
		Optional<Goods> result = goodsService.getGoodsById(nonExistingGoodsId);

		// 検証: 結果として Optional が返ってきており、値が存在しない (空である) ことを確認
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("IDで商品を1件取得 - 異常系: リポジトリが誤った商品を返した場合 (通常は起こらない)")
	void testGetGoodsById_repositoryReturnsWrongGoods() {
		// テストで使用する goodsId
		Integer goodsId = 1;
		// 意図的に間違った商品 (goods2) を返すようにモックを設定
		Mockito.when(goodsRepository.findById(goodsId)).thenReturn(Optional.of(goods2));

		// テスト対象のメソッドを実行
		Optional<Goods> result = goodsService.getGoodsById(goodsId);

		// 検証:
		assertTrue(result.isPresent());
		// 取得した商品が、期待する goods1 ではないことを確認
		assertNotEquals(goods1, result.get());
	}

	@Test
	@DisplayName("キーワード検索 (ページネーションあり) - 指定キーワードで1ページ目の20件取得")
	void testSearchGoodsStringInt_firstPage() {
		String keyword = "スマホ";
		int page = 0;
		int pageSize = 20;
		Pageable pageable = PageRequest.of(page, pageSize);
		List<Goods> expectedGoods = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Goods goods = new Goods();
			goods.setId(i + 1);
			goods.setName("スマホ" + (i + 1));
			goods.setCategory("スマホアクセサリ");
			goods.setPrice(1000 + i * 100);
			goods.setStock(50 + i);
			expectedGoods.add(goods);
		}

		Page<Goods> expectedPage = new PageImpl<>(expectedGoods, pageable, 50);

		Mockito.when(goodsRepository.findByNameContainingIgnoreCase(keyword, pageable)).thenReturn(expectedPage);

		Page<Goods> result = goodsService.searchGoods(keyword, page);

		assertEquals(expectedPage.getTotalElements(), result.getTotalElements());
		assertEquals(expectedPage.getTotalPages(), result.getTotalPages());
		assertEquals(expectedPage.getContent(), result.getContent());
		assertEquals(pageSize, result.getSize());
		assertEquals(page, result.getNumber());
	}

	@Test
	@DisplayName("キーワード検索 (ページネーションあり) - キーワードが null の場合、全商品表示")
	void testSearchGoodsStringInt_nullKeyword_showAll() {
		int page = 0;
		int pageSize = 20;

		Pageable pageable = PageRequest.of(page, pageSize);
		List<Goods> allGoods = List.of(goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8, goods9, goods10,
				goods11, goods12, goods13, goods14, goods15, goods16, goods17, goods18, goods19, goods20, goods21);
		Page<Goods> allGoodsPage = new PageImpl<>(allGoods, pageable, allGoods.size());

		Mockito.when(goodsRepository.findAll(pageable)).thenReturn(allGoodsPage);

		Page<Goods> result = goodsService.searchGoods(null, page);

		assertEquals(allGoods.size(), result.getTotalElements());
		assertEquals(allGoodsPage.getContent(), result.getContent());
		assertEquals(pageSize, result.getSize());
		assertEquals(page, result.getNumber());
	}

	@Test //成功
	//検索キーワード 'スマホ' に対して、"スマホ" を含んだ商品がちゃんと返ってくるテスト
	@DisplayName("\"キーワード検索 (10件/ページ) - 指定キーワードで1ページ目を取得\"") //正常系
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
	}

	@Test //成功
	@DisplayName("キーワード検索 - キーワードに合わない商品が混入した場合")
	void testSearchGoodsByKeyword_incorrectKeywordIncluded() {
		Pageable pageable = PageRequest.of(0, 10);

		// モックの設定 - "スマホ" で検索した際に、合致する商品 (goods1, goods2) と合致しない商品 (goods3) を混ぜて返す
		Mockito.when(goodsRepository.findByNameContainingIgnoreCase("スマホ", pageable))
				.thenReturn(new PageImpl<>(List.of(goods1, goods2, goods3)));

		//テスト対象のメソッドを実行
		Page<Goods> result = goodsService.searchGoodsByKeyword("スマホ", 0);

		//検証
		assertEquals(2, result.getTotalElements());
		// 全ての商品名に "スマホ" が含まれているか
		assertTrue(result.getContent().stream().allMatch(goods -> goods.getName().contains("スマホ")));

		//スマホを含まない商品が一つも含まれてないか
		assertFalse(result.getContent().stream().anyMatch(goods -> !goods.getName().contains("スマホ")));
	}

	@Test //成功
	@DisplayName("キーワード検索 - 空のキーワードで検索した場合")
	void testSearchGoodsByKeyword_emptyKeyword() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Goods> allGoods = List.of(goods1, goods2, goods3);
		Page<Goods> allGoodsPage = new PageImpl<>(allGoods, pageable, allGoods.size());

		// モックの設定 - 空のキーワードで検索された場合に、全ての商品を含む Page を返すように設定
		Mockito.when(goodsRepository.findByNameContainingIgnoreCase("", pageable))
				.thenReturn(allGoodsPage);

		// テスト対象のメソッドを実行
		Page<Goods> result = goodsService.searchGoodsByKeyword("", 0);

		// 検証
		assertEquals(allGoods.size(), result.getTotalElements()); // 全ての商品が返ってくるはず
		// 結果に全ての商品が含まれているか
		assertTrue(result.getContent().containsAll(allGoods));
	}

	@Test //しっぱい
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

	@Test //成功
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

	@Test //成功
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

	@Test //成功
	@DisplayName("カテゴリーでの検索 - 空のカテゴリーの場合")
	void testSearchGoodsByCategory_emptyCategory() {
		Pageable pageable = PageRequest.of(0, 10);

		// モックの設定 - 空のカテゴリーで検索された場合に、空の Page を返すように設定
		Mockito.when(goodsRepository.findByCategory("", pageable))
				.thenReturn(new PageImpl<>(List.of()));

		Page<Goods> result = goodsService.searchGoodsByCategory("", 0);

		assertEquals(0, result.getTotalElements());
		assertTrue(result.getContent().isEmpty());
	}

	@Test //成功
	@DisplayName("カテゴリーでの検索 - nullのカテゴリーの場合")
	void testSearchGoodsByCategory_nullCategory() {
		// GoodsService の実装が null チェックを行って例外をスローする場合
		assertThrows(IllegalArgumentException.class, () -> goodsService.searchGoodsByCategory(null, 0));

		//GoodsService の実装が null をそのままリポジトリに渡す場合 (非推奨)
		Pageable pageable = PageRequest.of(0, 10);
		Mockito.when(goodsRepository.findByCategory(null, pageable))
				.thenReturn(new PageImpl<>(List.of()));
		Page<Goods> result = goodsService.searchGoodsByCategory(null, 0);
		assertEquals(0, result.getTotalElements());
		assertTrue(result.getContent().isEmpty());
	}
}
