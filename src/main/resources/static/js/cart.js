const selectedCountElement = document.getElementById('selectedCount');
const cartItems = document.querySelectorAll('.cart-item');
const totalPriceElement = document.getElementById('totalPrice');
const removeSelectedButton = document.querySelector('.footer-left button');

const amountInputs = document.querySelectorAll('.amount-input');
const decreaseButtons = document.querySelectorAll('.amount-btn.decrease');
const increaseButtons = document.querySelectorAll('.amount-btn.increase');

const checkoutButton = document.querySelector('#checkout-btn');


// Select items
const selectAllCheckbox = document.getElementById('selectAll');
const selectAllFooterCheckbox = document.getElementById('selectAllFooter');

selectAllCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        checkbox.checked = selectAllCheckbox.checked
    });
    selectAllFooterCheckbox.checked = selectAllCheckbox.checked;
    updateSelectedCount();
});

selectAllFooterCheckbox.addEventListener('change', () => {
    cartItems.forEach(item => {
        const checkbox = item.querySelector('input[type="checkbox"]');
        checkbox.checked = selectAllFooterCheckbox.checked
    });
    selectAllCheckbox.checked = selectAllFooterCheckbox.checked;
    updateSelectedCount();
});

// Select single item
cartItems.forEach(item => {
    const checkbox = item.querySelector('input[type="checkbox"]');
    checkbox.addEventListener('change', () => {
        updateSelectAllCheckbox();
        updateSelectedCount();
    });
});

// Remove items
removeSelectedButton.addEventListener('click', () => {
    const selectedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    selectedItems.forEach(selectedItem => {
        const cartItem = selectedItem.closest('.cart-item');
        removeItem(cartItem.dataset.goodsId);
    });
    window.location.reload();
});

const removeButtons = document.getElementsByClassName('remove-button');
for (let i = 0; i < removeButtons.length; i++) {
    removeButtons[i].addEventListener('click', (event) => {
        const cartItem = event.currentTarget.closest('.cart-item');
        removeItem(cartItem.dataset.goodsId);
        window.location.reload();
    });
}

// Change item amount
decreaseButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        const input = amountInputs[index];
        const cartItem = input.closest('.cart-item');
        const itemId = cartItem.dataset.goodsId; // 假设每个商品项都有一个唯一的id
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
    const orginalAmount = parseInt(event.target.dataset.originalAmount);
    const newAmount = parseInt(event.target.value);
    updateCartItemAmount(itemId, newAmount - orginalAmount);
  });
});

checkoutButton.addEventListener('click', handleCheckout);

function removeItem(itemId) {
    fetch(`/seckill/cart/delete/${itemId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
        }
    });
}

function updateCartItemAmount(itemId, newAmount) {
    fetch(`/seckill/cart/update/?id=${itemId}&amount=${newAmount}`, {
        method: 'POST'
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        }
    });
}

function updateSelectAllCheckbox() {
    selectedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
    selectAllCheckbox.checked = selectedCount === cartItems.length;
    selectAllFooterCheckbox.checked = selectAllCheckbox.checked;
}

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

function handleCheckout() {
    console.log('Checkout button clicked');
    const selectedCheckbox = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
    selectedCheckbox.forEach(checkbox => {
        const cartItem = checkbox.closest('.cart-item');
        const itemId = cartItem.dataset.goodsId;
        updateSelectedStatus(itemId);
    });

  if (selectedCheckbox.length > 0) {
    checkoutCart();
  } else {
    console.log('No items selected for checkout');
  }
}

function updateSelectedStatus(itemId) {
    fetch(`/seckill/cart/select/${itemId}`, {
        method: 'POST'
    }).then(response => {
        if (response.ok) {
        }
    });
}

function checkoutCart() {
    fetch('/seckill/order/checkout', {
      method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
      if (data.code === 200) {
        window.location.href = `/seckill/order/detail?orderId=${data.orderId}`;
      } else {
        alert(`下单失败,错误信息: ${data.message}`);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
  }