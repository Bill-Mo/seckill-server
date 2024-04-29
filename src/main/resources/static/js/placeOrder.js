function placeOrder(button) {
    var paymentMethod = document.querySelector('select').value;
    var orderId = button.getAttribute('orderId');
    var url = '/seckill/order/placeOrder?paymentMethod=' + paymentMethod + '&orderId=' + orderId;

    fetch(url, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            window.location.href = `/seckill/order/success?orderId=${data.orderId}` + `&totalPrice=${data.totalPrice}`;
        } else {
            alert(`下单失败,错误信息: ${data.message}`);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}