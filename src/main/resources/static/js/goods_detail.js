
function clickButton(requestPath, returnPath) {
    const inputElement = document.querySelector(".amount-input").value;
    const goodsId = document.querySelector('.add-to-cart').dataset.goodsId;
    console.log(`Adding ${inputElement} of goods ${goodsId} to cart`);
    fetch(requestPath, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            goodsId: goodsId,
            amount: inputElement,
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            console.log('Item added to cart.');
            window.location.href = returnPath;
        } else if (data.code !== 200) {
            alert(data.message);
            if (data.code === 401) {
                window.location.href = '/seckill/login';
            }
        }
    })
    .catch(error => {
        console.error('Error adding to cart:', error);
    });
}

amountInputs = document.querySelector('.amount-input');
amountInputs.addEventListener('change', (event) => {
    const purchaseLimit = event.target.dataset.purchaseLimit;
    const stock = event.target.dataset.stock;
    const newAmount = parseInt(event.target.value);
    const limit = Math.min(purchaseLimit, stock);

    console.log(`purchaseLimit: ${limit}, newAmount: ${newAmount}`);
    if (newAmount > limit) {
        alert(`You cannot buy more than ${limit} items`);
        event.target.value = limit;
        return;
    }
});
