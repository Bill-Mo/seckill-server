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
    fetch(`/seckill/order/update/address?orderId=${orderId}&address=${encodeURIComponent(address)}`, {
        method: 'GET', // Assuming GET, but POST might be more appropriate
        headers: {
            'Content-Type': 'application/json',
            // Include any other headers like Authorization if required
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            window.location.reload(); // Reload the page to show the updated address
        } else {
            window.location.href = '/error'; // Redirect to an error page
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
    var url = '/seckill/order/placeOrder?paymentMethod=' + paymentMethod + '&orderId=' + orderId;

    fetch(url, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            window.location.href = `/seckill/order/success?orderId=${data.orderId}` + `&totalPrice=${data.totalPrice}`;
        } else {
            alert(`Place order fail: ${data.message}`);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
