<!DOCTYPE html>
<html lang="uz">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hamyon</title>

<style>
* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial, sans-serif;
    background: #f2f5fa;
    color: #17243b;
}

header {
    background: linear-gradient(135deg, #1457d9, #4388ff);
    color: white;
    padding: 25px 20px 35px;
    border-radius: 0 0 25px 25px;
}

.logo {
    font-size: 27px;
    font-weight: bold;
}

.demo {
    font-size: 11px;
    margin-top: 8px;
}

.balance {
    margin-top: 30px;
    font-size: 13px;
}

.money {
    font-size: 32px;
    font-weight: bold;
    margin-top: 8px;
}

main {
    padding: 18px;
    max-width: 500px;
    margin: auto;
}

.actions {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 8px;
}

.action {
    background: white;
    padding: 15px 5px;
    border-radius: 15px;
    text-align: center;
    font-size: 12px;
    cursor: pointer;
}

.icon {
    font-size: 25px;
    display: block;
    margin-bottom: 8px;
}

h3 {
    margin-top: 25px;
}

.cards {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
}

.bank {
    background: #263c64;
    color: white;
    padding: 18px;
    border-radius: 15px;
}

.humo {
    background: #176e65;
}

.bank small {
    display: block;
    margin-top: 25px;
}

.box {
    background: white;
    padding: 16px;
    border-radius: 15px;
}

input, select {
    width: 100%;
    padding: 13px;
    margin: 8px 0;
    border: 1px solid #ddd;
    border-radius: 10px;
}

button {
    width: 100%;
    padding: 14px;
    border: none;
    background: #1457d9;
    color: white;
    border-radius: 10px;
    font-size: 15px;
    cursor: pointer;
    margin-top: 8px;
}

.row {
    display: flex;
    justify-content: space-between;
    padding: 12px 0;
    border-bottom: 1px solid #eee;
    font-size: 13px;
}

.notice {
    text-align: center;
    color: #777;
    font-size: 11px;
    margin-top: 25px;
}
</style>
</head>

<body>

<header>
    <div class="logo">HAMYON</div>
    <div class="demo">SINOV HISOBI</div>

    <div class="balance">Jami demo balans</div>
    <div class="money" id="balance">100 000 000 so‘m</div>
    <div class="demo">Bu haqiqiy pul emas</div>
</header>

<main>

<div class="actions">
    <div class="action" onclick="transfer()">
        <span class="icon">↗️</span>
        O‘tkazma
    </div>

    <div class="action" onclick="topup()">
        <span class="icon">＋</span>
        To‘ldirish
    </div>

    <div class="action" onclick="payment()">
        <span class="icon">📱</span>
        To‘lovlar
    </div>

    <div class="action" onclick="showHistory()">
        <span class="icon">🧾</span>
        Tarix
    </div>
</div>

<h3>Mening kartalarim</h3>

<div class="cards">
    <div class="bank">
        <b>UZCARD</b>
        <small>8600 •••• •••• 2048</small>
    </div>

    <div class="bank humo">
        <b>HUMO</b>
        <small>9860 •••• •••• 7312</small>
    </div>
</div>

<h3>Oxirgi amallar</h3>

<div class="box" id="history">
    Hozircha amallar yo‘q.
</div>

<div class="notice">
    DEMO REJIMI<br>
    HAQIQIY TO‘LOV EMAS
</div>

</main>

<script>

let balance = 100000000;
let history = [];

function formatMoney(number) {
    return new Intl.NumberFormat("uz-UZ").format(number) + " so‘m";
}

function update() {
    document.getElementById("balance").innerText =
        formatMoney(balance);

    let box = document.getElementById("history");

    if (history.length === 0) {
        box.innerHTML = "Hozircha amallar yo‘q.";
        return;
    }

    box.innerHTML = history.slice().reverse().map(item => 
        <div class="row">
            <span>${item.title}</span>
            <b>${item.plus ? "+" : "-"}${formatMoney(item.value)}</b>
        </div>
    ).join("");
}

function transfer() {
    let name = prompt("Qabul qiluvchi ismini kiriting:");
    if (!name) return;

    let value = Number(prompt("O‘tkazma miqdorini kiriting:"));

    if (!Number.isSafeInteger(value)  value <= 0  value > balance) {
        alert("Miqdor noto‘g‘ri yoki balans yetarli emas!");
        return;
    }

    balance -= value;

    history.push({
        title: "Demo o‘tkazma: " + name,
        value: value,
        plus: false
    });

    update();
    alert("Demo o‘tkazma bajarildi!");
}

function topup() {
    let value = Number(prompt("To‘ldirish miqdorini kiriting:"));

    if (!Number.isSafeInteger(value) || value <= 0) {
        alert("Miqdor noto‘g‘ri!");
        return;
    }

    balance += value;

    history.push({
        title: "Demo balans to‘ldirildi",
        value: value,
        plus: true
    });

    update();
}

function payment() {
    let service = prompt(
        "Xizmatni tanlang:\n1. Mobil aloqa\n2. Internet\n3. Kommunal xizmat"
    );

    if (!service) return;

    let value = Number(prompt("To‘lov miqdorini kiriting:"));

    if (!Number.isSafeInteger(value)  value <= 0  value > balance) {
        alert("Miqdor noto‘g‘ri yoki balans yetarli emas!");
        return;
    }

    balance -= value;

    history.push({
        title: "Demo to‘lov: " + service,
        value: value,
        plus: false
    });

    update();
    alert("Demo to‘lov bajarildi!");
}

function showHistory() {
    document.getElementById("history").scrollIntoView({
        behavior: "smooth"
    });
}

update();

</script>

</body>
</html>
