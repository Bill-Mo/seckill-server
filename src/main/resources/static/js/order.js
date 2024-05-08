document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.querySelector('.change-addr-btn');
    if (editButton) {
        editButton.addEventListener('click', function() {
            const currentAddress = document.querySelector('.address-section p').textContent;
            const newAddress = prompt('Please enter your new address:', currentAddress);

            if (newAddress && newAddress !== currentAddress) {
                const orderId = this.getAttribute('data-order-id'); // Ensure your button has 'data-order-id' attribute
                updateAddress(orderId, newAddress);
            }
        });
    }
});

function updateAddress(orderId, address) {
    fetch(`/seckill/order/address`, 
    {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            orderId: orderId,
            address: address
        }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            window.location.reload(); // Reload the page to show the updated address
        } else {
            alert(`Error updating address: ${data.message}`);
        }
    })
    .catch(error => {
        console.error('Error updating address:', error);
        window.location.href = '/seckill/error'; // Redirect to an error page on fetch failure
    });
}


function placeOrder(button) {
    var paymentMethod = document.querySelector('select').value;
    var orderId = button.getAttribute('orderId');

    fetch(`/seckill/order/placeOrder`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            orderId: orderId,
            paymentMethod: paymentMethod,
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            window.location.href = `/seckill/order/success?orderId=${orderId}` + `&totalPrice=${data.obj}`;
        } else {
            alert(`Place order fail: ${data.message}`);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
