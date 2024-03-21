function updateCountdown() {
    var countdownElements = document.getElementsByClassName("countdown");
    for (var i = 0; i < countdownElements.length; i++) {
        var countdownElement = countdownElements[i];
        var startTime = new Date(countdownElement.dataset.startTime);
        var endTime = new Date(countdownElement.dataset.endTime);
        var now = new Date();

        if (now < startTime) {
            var diffStart = startTime.getTime() - now.getTime();
            countdownElement.textContent = "距离开始: " + formatTime(diffStart);
            countdownElement.classList.remove("started");
            countdownElement.classList.remove("ended");
            countdownElement.classList.add("not-started");
        } else if (now < endTime) {
            var diffEnd = endTime.getTime() - now.getTime();
            countdownElement.textContent = "距离结束: " + formatTime(diffEnd);
            countdownElement.classList.remove("not-started");
            countdownElement.classList.remove("ended");
            countdownElement.classList.add("started");
        } else {
            countdownElement.textContent = "已结束";
            countdownElement.classList.remove("not-started");
            countdownElement.classList.remove("started");
            countdownElement.classList.add("ended");
        }
    }
}

function formatTime(milliseconds) {
    var seconds = Math.floor(milliseconds / 1000);
    var minutes = Math.floor(seconds / 60);
    var hours = Math.floor(minutes / 60);
    var days = Math.floor(hours / 24);

    hours = hours % 24;
    minutes = minutes % 60;
    seconds = seconds % 60;

    var timeString = "";
    if (days > 0) {
        timeString += days + "天";
    }
    if (hours > 0 || timeString.length > 0) {
        timeString += hours + "小时";
    }
    if (minutes > 0 || timeString.length > 0) {
        timeString += minutes + "分钟";
    }
    if (seconds > 0 || timeString.length > 0) {
        timeString += seconds + "秒";
    }
    return timeString;
}




function updateBuyButton() {
    var buyButton = document.querySelector(".buy-button");
    var startTime = new Date(buyButton.dataset.startTime);
    var endTime = new Date(buyButton.dataset.endTime);
    var now = new Date();

    if (now >= startTime && now < endTime) {
        buyButton.disabled = false;
        buyButton.classList.remove('disabled');
    } else {
        buyButton.disabled = true;
        buyButton.classList.add('disabled');
    }
}

setInterval(updateCountdown, 1000);
updateBuyButton();
// setInterval(updateBuyButton, 1000);
