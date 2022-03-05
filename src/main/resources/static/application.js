function loadAddressBooks()
{
    let divElement = document.getElementById("addressBooks");
    divElement.innerHTML = "";

    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function()
    {
        if(httpRequest.readyState == XMLHttpRequest.DONE)
        {
            if (httpRequest.status === 200) 
            {
                let addressBooks = JSON.parse(httpRequest.responseText);
                addressBooks.forEach(element => {
                      divElement += `<div>${element.id}</div>`;
                      divElement += `<button id=${element.id} onclick=addBuddy(this.id)>Add buddy</button>`
                      element.buddies.forEach(buddy => {
                        divElement += `<div>${buddy.name}, ${buddy.address}, ${buddy.phoneNumber}</div>`;
                      });
                });
            }
        }
    };

    httpRequest.open("GET", "localhost:8080/addressBooksAPI");
    httpRequest.send();
}

function addAddressBook()
{
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "localhost:8080/addBookSPA");
    httpRequest.onreadystatechange = function()
    {
        if(httpRequest.readyState == XMLHttpRequest.DONE)
        {
            if (httpRequest.status === 200) 
            {
                loadAddressBooks();
            }
        }
    };

    httpRequest.send();
}

function addBuddy(buttonId)
{
    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let phone = document.getElementById("number");

    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "localhost:8080/addBuddy");
    httpRequest.onreadystatechange = function()
    {
        if(httpRequest.readyState == XMLHttpRequest.DONE)
        {
            if (httpRequest.status === 200) 
            {
                loadAddressBooks();
            }
        }
    };

    httpRequest.send(`book=${buttonId}&name=${name}&address=${address}&phone=${phone}`);
}