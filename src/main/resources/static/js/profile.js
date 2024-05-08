function showUsernameForm() {
    document.getElementById('usernameForm').style.display = 'block';
}

function updateUsername() {
    const newUsername = document.getElementById('newUsernameInput').value;
    updateUserInfo('username', newUsername);
}

function showPasswordForm() {
    document.getElementById('passwordForm').style.display = 'block';
}

function updatePassword() {
    const newPassword = document.getElementById('newPasswordInput').value;
    updateUserInfo('password', newPassword);
}

function showAddressForm() {
    document.getElementById('addressForm').style.display = 'block';
}

function updateAddress() {
    const newAddress = document.getElementById('newAddressInput').value;
    updateUserInfo('address', newAddress);
}

function updateUserInfo(field, value) {
    fetch('/seckill/user/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest',
        },
        body: JSON.stringify({
            field: field, 
            value: value,
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            location.reload();
        } else {
            alert('Update failed: ' + data.message);
        }
    })
    .catch(error => console.error('Error:', error));
}
