function addMoney(type)  {
    // 결과를 표시할 element
    const resultElement = document.getElementById('money');

    // 현재 화면에 표시된 값
    let number = resultElement.innerText;

    // 더하기/빼기
    if(type == '100') {
        number = parseInt(number) + 100;
    }else if(type == '500')  {
        number = parseInt(number) + 500;
    }else if(type == '1000') {
        number = parseInt(number) + 1000;
    }else if(type == '0') {
        number = 0;
    }

    // 결과 출력
    resultElement.innerText = number;
}

function purchase(type){
    // 결과를 표시할 element
    const resultElement = document.getElementById('money');
    // 현재 화면에 표시된 값
    let number = resultElement.innerText;
    console.log("price = " + type);
}
