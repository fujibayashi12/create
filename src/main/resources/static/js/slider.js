document.addEventListener('DOMContentLoaded', function() {
	let splide = new Splide('#image-carousel', {
		type: 'loop',   // 🔥 無限ループ
		perPage: 1,     // 🔥 1枚ずつ表示
		autoplay: true, // 🔥 自動再生
		interval: 5000, // 🔥 5秒ごとに切り替え
	});

	splide.mount();
	document.querySelectorAll('.splide__slide img').forEach(img => {
		img.style.width = "100%";
		img.style.height = "400px"; // 🔥 高さを統一
	});
	// 🔥 手動でボタンを押したときの処理
	document.getElementById("next").addEventListener("click", () => {
		splide.go("+1"); // 🔥 1枚進む
	});

	document.getElementById("prev").addEventListener("click", () => {
		splide.go("-1"); // 🔥 1枚戻る
	});

});