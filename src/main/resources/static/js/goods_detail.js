function updateCartLink() {
    // 获取 input 标签中的值
    var amountValue = document.getElementById("amount").value;

    // 获取加入购物车的链接标签
    var addToCartLink = document.querySelector(".add-to-cart");

    // 将 input 中的值添加到链接标签的 href 属性中
    addToCartLink.href = addToCartLink.href + "&amount=" + amountValue;
}
