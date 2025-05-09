// ✅ Stripeの公開キーを指定
const stripe = Stripe("pk_test_51RBPESFxJeskCwCnhCTNcn7LGfNfsqikuXDgCCiWgZIji9Xo1t1jDzyagecZ42xvpl67mMlP8eBhRzbXKpUZ94k700Z3W8CH5H");  // ✅ 公開キーを設定（正しいキーに変更！）

// ✅ 決済用の要素を作成
const elements = stripe.elements();
const card = elements.create("card");
card.mount("#card-element");

// ✅ フォームの送信時に決済処理を実行
document.querySelector("#payment-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    const { token, error } = await stripe.createToken(card);
    
    if (error) {
        console.error("決済エラー: ", error);
    } else {
        document.querySelector("#stripeToken").value = token.id;
        document.querySelector("#payment-form").submit();
    }
});