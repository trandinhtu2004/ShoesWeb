
        //hien ra mot cua so nho ben tren bao hieu 
window.alert = function (message, timeout = null) {
    const alert = document.createElement('div');
    const alertButton = document.createElement('button'); //tao alert button 
    alertButton.innerHTML = 'OK';
    alert.classList.add('alert');
    alert.setAttribute('style', ` 
        position: fixed;
        top: 50px;
        left: 50%;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 10px 5px 0 #00000022;
        display: flex;
        flex-direction: column;
        transform: translateX(-50%);
        background-color: #5ced73;
    `);
    alertButton.setAttribute('style', `
        border: 1px solid #333;
        background: white;
        border-radius: 5px;
        padding: 5px;
    `)
    alert.innerHTML = `<span style="padding: 10px">${message}</span>`; 
    alert.appendChild(alertButton);
    alertButton.addEventListener('click', (e) => {
        alert.remove();
    });
    if (timeout !== null) {
        setTimeout(() => {
            alert.remove();
        }, Number(timeout));
    }
    document.body.appendChild(alert);
};