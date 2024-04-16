const selectAllCheckbox = document.getElementById('selectAll');
const selectAllFooterCheckbox = document.getElementById('selectAllFooter');
const cartItems = document.querySelectorAll('.cart-item');
const selectedCountElement = document.getElementById('selectedCount');
const totalPriceElement = document.getElementById('totalPrice');

// 更新选中商品数量和总价格
function updateSelectedCount() {
    const selectedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    const selectedCount = selectedItems.length;
    selectedCountElement.textContent = selectedCount;

    let totalPrice = 0;
    selectedItems.forEach(selectedItem => {
        const itemTotalElement = selectedItem.closest('.cart-item').querySelector('.item-total');
        const itemTotal = parseFloat(itemTotalElement.textContent.replace('$', ''));
        totalPrice += itemTotal;
    });
    totalPriceElement.textContent = `$${totalPrice.toFixed(2)}`;
}

// 全选/全不选
selectAllCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        checkbox.checked = selectAllCheckbox.checked;
    });
    updateSelectedCount();
});

selectAllFooterCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        checkbox.checked = selectAllFooterCheckbox.checked;
    });
    updateSelectedCount();
});

// 监听单个商品选中/取消选中
cartItems.forEach(item => {
    const checkbox = item.querySelector('input[type="checkbox"]');
    checkbox.addEventListener('change', updateSelectedCount);
});

// 删除选中商品
const removeSelectedButton = document.querySelector('.footer-left button');
removeSelectedButton.addEventListener('click', () => {
    const selectedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    selectedItems.forEach(selectedItem => {
        const cartItem = selectedItem.closest('.cart-item');
        cartItem.remove();
    });
    updateSelectedCount();
});

const removeButtons = document.getElementsByClassName('remove-button');
for (let i = 0; i < removeButtons.length; i++) {
    removeButtons[i].addEventListener('click', (event) => {
        const cartItem = event.currentTarget.closest('.cart-item');
        fetch(`/seckill/cart/delete/${cartItem.dataset.goodsId}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                cartItem.remove();
                updateSelectedCount();
            }
        });
    });
}
