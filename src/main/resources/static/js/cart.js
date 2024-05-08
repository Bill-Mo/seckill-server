const selectedCountElement = document.getElementById('selectedCount');
const cartItems = document.querySelectorAll('.cart-item');
const totalPriceElement = document.getElementById('totalPrice');
const removeSelectedButton = document.querySelector('.footer-left button');

const amountInputs = document.querySelectorAll('.amount-input');
const decreaseButtons = document.querySelectorAll('.amount-btn.decrease');
const increaseButtons = document.querySelectorAll('.amount-btn.increase');

const checkoutButton = document.querySelector('#checkout-btn');

function removeItem(itemId, cartItem) {
    fetch(`/seckill/cart/${itemId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            itemId
        }),
    }).then(response => {
        if (response.ok) {
            cartItem.remove();
        }
    });
}

function updateCartItemAmount(itemId, newAmount) {
    fetch(`/seckill/cart/${itemId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            amount: newAmount,
        }),
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        }
    });
}

function updateSelectAllCheckbox() {
    console.log('Update select all checkbox');
    selectedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
    selectAllCheckbox.checked = (selectedCount === cartItems.length && selectedCount > 0);
    console.log(`Select all checkbox: ${selectAllCheckbox.checked}`);
    selectAllFooterCheckbox.checked = selectAllCheckbox.checked;
}

function updateSelectedCount() {
    const selectedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    const selectedCount = selectedItems.length;
    selectedCountElement.textContent = selectedCount;

    console.log(`Selected count: ${selectedCount}`);
    if (selectedCount === 0) {
        removeSelectedButton.disabled = true;
        checkoutButton.disabled = true;
    } else {
        removeSelectedButton.disabled = false;
        checkoutButton.disabled = false;
    }

    if (selectedCount === cartItems.length) {
        selectAllCheckbox.checked = true;
        selectAllFooterCheckbox.checked = true;
    }

    let totalPrice = 0;
    selectedItems.forEach(selectedItem => {
        const itemTotalElement = selectedItem.closest('.cart-item').querySelector('.item-total');
        const itemTotal = parseFloat(itemTotalElement.textContent.replace('$', ''));
        totalPrice += itemTotal;
    });
    totalPriceElement.textContent = `$${totalPrice.toFixed(2)}`;
}

function updateSelectedStatus(itemId) {
    console.log(`Update selected status for item: ${itemId}`);
    fetch(`/seckill/cart/${itemId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({}),
    }).then(response => {
        if (response.ok) {
        }
    });
}

function checkoutCart() {
    fetch('/seckill/order', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
      },
    })
    .then(response => response.json())
    .then(data => {
      if (data.code === 200) {
        window.location.href = `/seckill/order/${data.obj}`;
      } else {
        alert(`下单失败,错误信息: ${data.message}`);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// Select items
const selectAllCheckbox = document.getElementById('selectAll');
const selectAllFooterCheckbox = document.getElementById('selectAllFooter');

selectAllCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        if (checkbox.checked !== selectAllCheckbox.checked) {
            updateSelectedStatus(item.dataset.goodsId);
            checkbox.checked = selectAllCheckbox.checked;
        }
    });
    selectAllFooterCheckbox.checked = selectAllCheckbox.checked;
    updateSelectedCount();
});

selectAllFooterCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        if (checkbox.checked !== selectAllFooterCheckbox.checked) {
            updateSelectedStatus(item.dataset.goodsId);
            checkbox.checked = selectAllFooterCheckbox.checked;
        }
    });
    selectAllCheckbox.checked = selectAllFooterCheckbox.checked;
    updateSelectedCount();
});

// Select single item
cartItems.forEach(item => {
    const checkbox = item.querySelector('input[type="checkbox"]');
    checkbox.addEventListener('change', () => {
        updateSelectedStatus(item.dataset.goodsId);
        updateSelectAllCheckbox();
        updateSelectedCount();
    });
});

// Remove items
removeSelectedButton.addEventListener('click', () => {
    const selectedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    selectedItems.forEach(selectedItem => {
        const cartItem = selectedItem.closest('.cart-item');
        removeItem(cartItem.dataset.goodsId, cartItem);
    });
});

const removeButtons = document.getElementsByClassName('remove-button');
for (let i = 0; i < removeButtons.length; i++) {
    removeButtons[i].addEventListener('click', (event) => {
        const cartItem = event.currentTarget.closest('.cart-item');
        removeItem(cartItem.dataset.goodsId, cartItem);
    });
}

// Change item amount
decreaseButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        const input = amountInputs[index];
        const cartItem = input.closest('.cart-item');
        const itemId = cartItem.dataset.goodsId;
        let value = parseInt(input.value);
        if (value > 1) {
            updateCartItemAmount(itemId, -1);
        }
    });
});

increaseButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        const input = amountInputs[index];
        const cartItem = input.closest('.cart-item');
        const itemId = cartItem.dataset.goodsId;
        updateCartItemAmount(itemId, 1);
    });
});

amountInputs.forEach(input => {
  input.addEventListener('change', (event) => {
    const itemId = event.target.closest('.cart-item').dataset.goodsId;
    const purchaseLimit = parseInt(event.target.dataset.purchaseLimit);
    const stock = parseInt(event.target.dataset.stock);
    const orginalAmount = parseInt(event.target.dataset.originalAmount);
    const limit = Math.min(purchaseLimit, stock);

    var newAmount = parseInt(event.target.value);
    if (newAmount > limit) {
        alert(`You cannot buy more than ${limit}`);
        newAmount = event.target.value = limit;
    }
    if (newAmount < 1) {
        alert('Amount cannot be less than 1');
        newAmount = event.target.value = 1;
    }
    updateCartItemAmount(itemId, newAmount - orginalAmount);
  });
});

checkoutButton.addEventListener('click', checkoutCart);

updateSelectedCount();
updateSelectAllCheckbox();
