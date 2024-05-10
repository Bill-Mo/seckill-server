$(document).ready(function() {
    // 监听密码和重复密码字段的输入
    $('#password, #rePassword').on('input', function() {
        var password = $('#password').val();
        var rePassword = $('#rePassword').val();

        // 检查两次输入的密码是否相同
        if (password !== rePassword) {
            // 如果不匹配，显示错误信息
            $('#passwordMatchError').text('Password not match!').css('color', 'red');
        } else {
            // 如果匹配，清除错误信息
            $('#passwordMatchError').text('');
        }
    });

    $('#registerForm').on('submit', function(e) {
        var password = $('#password').val();
        var rePassword = $('#rePassword').val();

        // 再次检查以确保表单提交时密码匹配
        if (password !== rePassword) {
            e.preventDefault();
            alert('Password not match! Please check again.');
            return false;
        }
    });
});
