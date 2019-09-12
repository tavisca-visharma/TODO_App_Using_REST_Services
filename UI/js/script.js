// Script code for TODO Project

let todoSuggestions = [];
let todoList = [];
let previousText = "";


(function onLoad() {
    window.addEventListener("load", makeTodoListSuggetions);
    let addButton = document.querySelector("#todo-add");
    addButton.addEventListener("click", addItem);
    let input = document.getElementById("todo-input");
    input.addEventListener("keyup", function(event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            addItem();
        }
    })
    let searchButton = document.querySelector("#todo-search");
    searchButton.addEventListener("click", search);
})();

function hideAllViews() {
    let elements = document.getElementsByClassName("nav-tabs");
    for (let elem of elements) {
        elem.style.display = "none";
    }
}

function setView(elementToBeViewed) {
    hideAllViews();
    let elementView = document.getElementById(elementToBeViewed.innerHTML.toLowerCase());
    elementView.style.display = "block";
}

function makeTodoListSuggetions() {
    // // let datalist = document.getElementById("todo-list-suggestions");
    // todoSuggestions = localStorage.getItem("suggestions_history").split(",");
    // for (let i = 0; i < todoSuggestions.length; i++) {
    //     addItemInSuggestionsList(todoSuggestions[i]);
    //     console.log(todoSuggestions);
    // }

    //TODO : Implementation of Rest Services
    fetch("http://localhost:8000/todoapi/v1/suggestions/")
        .then(resp => resp.json())
        .then(respJson => respJson.data)
        .then(jsonArray => jsonArray.forEach(data => todoSuggestions.push(data.content) && addItemInSuggestionsList(data.content)))
        .catch(error => {
            console.log(error);
            // alert("Error in Connecting to Server");
        });

    fetch("http://localhost:8000/todoapi/v1/todo/")
        .then(resp => resp.json())
        .then(respJson => respJson.data)
        .then(jsonArray => jsonArray.forEach(data => todoList.push(data) && addItemInDisplayList(data)))
        .catch(error => console.log(error));


}

function isItemExistsInSuggestions(todoItemName) {
    // let todoList = document.getElementById("todo-list-suggestions");
    // let todoSuggestions = todoList.getElementsByTagName("option");
    // console.log(todoItemName);
    for (let i = 0; i < todoSuggestions.length; i++) {
        if (todoItemName.toLowerCase() === todoSuggestions[i].toLowerCase()) {
            return true;
        }
    }
    return false;
}

function addItem() {
    let itemInput = document.getElementById("todo-input");
    let todoItemName = itemInput.value.trim();
    let initialTodoStatus = false;
    if (todoItemName.length !== 0) {
        if (isItemExistsInSuggestions(todoItemName) == false) {
            addNewSuggestion(todoItemName);
            todoSuggestions.push(todoItemName);
        }
        addNewTodoItem(todoItemName, initialTodoStatus);
        itemInput.value = "";
    }
}

function addItemInSuggestionsList(todoItemContent) {

    let datalist = document.getElementById("todo-list-suggestions");
    let option = document.createElement("option");
    option.value = todoItemContent;
    datalist.appendChild(option);
}

function removeItemFromSuggestionsList(itemName) {
    let datalist = document.getElementById("todo-list-suggestions");
    let optionsList = datalist.getElementsByTagName("option");
    for (let i = 0; i < optionsList.length; i++) {
        if (optionsList[i].value.toLowerCase() === itemName.toLowerCase()) {
            optionsList[i].parentNode.removeChild(optionsList[i]);
        }
    }
}


function addItemInDisplayList(todo) {

    let todoId = todo.id;
    let todoItemName = todo.content;

    let displayList = document.getElementById("todo-list");
    if (displayList.getElementsByTagName("tr").length == 0) {
        displayList.appendChild(addHeadersInTableList());
    }
    let item = document.createElement("tr");
    let imagedata = createUncheckedCheckboxImage(item, todo);
    createCheckedCheckboxImage(imagedata, todo);
    createNameToAdd(item, todo);
    let itemEdit = createEditButtonToAdd(item);
    createSaveButtonToAdd(itemEdit);
    createDeleteButtonToAdd(item);
    displayList.appendChild(item);

}

function addHeadersInTableList() {
    let header = document.createElement("tr");
    createHeaderForStatus(header);
    createHeaderForName(header);
    createHeaderForEditButton(header);
    createHeaderForDeleteButton(header);
    return header;
}

function createHeaderForStatus(header) {
    let itemNumberHeader = document.createElement("th");
    itemNumberHeader.innerHTML = "Status";
    header.appendChild(itemNumberHeader);
}

function createHeaderForName(header) {
    let itemNameHeader = document.createElement("th");
    itemNameHeader.innerHTML = "Item";
    header.appendChild(itemNameHeader);
}

function createHeaderForEditButton(header) {
    let itemEditButton = document.createElement("th");
    itemEditButton.innerHTML = "Edit";
    header.appendChild(itemEditButton);
}

function createHeaderForDeleteButton(header) {
    let itemDeleteButton = document.createElement("th");
    itemDeleteButton.innerHTML = "Delete";
    header.appendChild(itemDeleteButton);
}

function createUncheckedCheckboxImage(item, todo) {
    let itemdata = document.createElement("td");
    let image = document.createElement("img");
    image.src = "images/uncheck.png";
    image.width = "20";
    image.height = "20";
    if (todo.checked === "false") {
        image.style.display = "";
    } else {
        image.style.display = "none";
    }
    image.className = "images";
    image.addEventListener("click", toggleImageAndStrike);
    itemdata.appendChild(image);
    item.appendChild(itemdata);
    return itemdata;
}

function createCheckedCheckboxImage(itemdata, todo) {
    let image = document.createElement("img");
    image.src = "images/check.jpg";
    image.width = "20";
    image.height = "20";
    image.className = "images";
    if (todo.checked === "true") {
        image.style.display = "";
    } else {
        image.style.display = "none";
    }
    image.addEventListener("click", toggleImageAndStrike);
    itemdata.appendChild(image);
}

function createNameToAdd(item, todoItem) {
    let itemNameToAdd = document.createElement("td");
    let itemDescription = document.createElement("textarea");
    itemDescription.id = todoItem.id;
    itemDescription.readOnly = true;
    itemDescription.value = todoItem.content;
    itemDescription.rows = 1;
    itemDescription.spellcheck = false;
    if (todoItem.checked == true)
        itemDescription.style.textDecoration = "line-through red";
    else
        itemDescription.style.textDecoration = "none";
    itemNameToAdd.appendChild(itemDescription);
    item.appendChild(itemNameToAdd);
}

function createEditButtonToAdd(item) {
    let itemEdit = document.createElement("td");
    let editButton = document.createElement("button");
    itemEdit.appendChild(editButton);
    editButton.innerHTML = "Edit";
    editButton.id = "edit-btn";
    editButton.style.display = "";
    editButton.addEventListener("click", editItem);
    item.appendChild(itemEdit);
    return itemEdit;
}

function createSaveButtonToAdd(itemEdit) {
    let saveButton = document.createElement("button");
    saveButton.innerHTML = "Save";
    saveButton.id = "save-btn";
    saveButton.style.display = "none";
    saveButton.addEventListener("click", saveItem);
    itemEdit.appendChild(saveButton);
}

function createDeleteButtonToAdd(item) {
    let itemDelete = document.createElement("td");
    let delButton = document.createElement("button");
    delButton.innerHTML = "X";
    delButton.id = "del-btn";
    delButton.addEventListener("click", deleteRowOfTable);
    item.appendChild(itemDelete);
    itemDelete.appendChild(delButton);
}

function deleteRowOfTable() {
    let deleteButton = this;
    let rowToBeDeleted = deleteButton.parentNode.parentNode;
    let textarea = rowToBeDeleted.cells[1].getElementsByTagName("textarea")[0];
    let todoId = textarea.id;
    deleteTodo(todoId);
    rowToBeDeleted.parentNode.removeChild(rowToBeDeleted);
}

function editItem() {
    let editButton = this;
    let rowToBeEdited = editButton.parentNode.parentNode;
    let buttons = rowToBeEdited.cells[2].getElementsByTagName("button");
    toggleButtonsDisplay(buttons);
    let textarea = rowToBeEdited.cells[1].getElementsByTagName("textarea")[0];
    previousText = textarea.value;
    enableTextEditOption(textarea);
}

function saveItem() {
    let saveButton = this;
    let rowToBeEdited = saveButton.parentNode.parentNode;
    let textarea = rowToBeEdited.cells[1].getElementsByTagName("textarea")[0];
    disableTextEditOption(textarea);
    let buttons = rowToBeEdited.cells[2].getElementsByTagName("button");
    toggleButtonsDisplay(buttons);
    let newText = textarea.value;
    let itemId = textarea.id;
    if (isItemExistsInSuggestions(newText) == false) {
        // removeItemFromSuggestionsList(previousText);
        addNewSuggestion(newText);

    }

    // update item in the todo list at server side using Patch/Put Request
    updateContentOfItem(itemId, newText, "");

}

function toggleButtonsDisplay(buttons) {
    if (buttons[0].style.display === "") {
        buttons[0].style.display = "none";
        buttons[1].style.display = "";
    } else {
        buttons[0].style.display = "";
        buttons[1].style.display = "none";
    }
}

function enableTextEditOption(textarea) {
    textarea.readOnly = false;
    textarea.style.background = "white";
    textarea.style.border = "1px solid blue";
}

function disableTextEditOption(textarea) {
    textarea.readOnly = true;
    textarea.style.background = "transparent";
    textarea.style.border = "0";
}

function toggleImageAndStrikeDataOfRowWithId(todoId) {
    for (let i = 0; i < todoList.length; i++) {
        if (todoList[i].id == todoId) {
            if (todoList[i].checked === "false")
                todoList[i].checked = "true";
            else
                todoList[i].checked = true;
            updateContentOfItem(todoId, todoList[i].content, todoList[i].checked);
            break;
        }
    }
}

function toggleImageAndStrike() {
    let checkbox = this;
    let rowChecksToBeToggled = checkbox.parentNode.parentNode;
    let textarea = rowChecksToBeToggled.cells[1].getElementsByTagName("textarea")[0];
    toggleImageAndStrikeDataOfRowWithId(textarea.id);
    let images = rowChecksToBeToggled.cells[0].getElementsByTagName("img");
    if (images[0].style.display === "") {
        images[0].style.display = "none";
        images[1].style.display = "";

        textarea.style.textDecoration = "line-through red";

    } else {
        images[0].style.display = "";
        images[1].style.display = "none";
        let textarea = rowChecksToBeToggled.cells[1].getElementsByTagName("textarea")[0];
        textarea.style.textDecoration = "none";
    }
}

function search() {
    let inputToBeFiltered = document.getElementById("todo-input")
    let listToBeFiltered = document.getElementById("todo-list");
    let str = inputToBeFiltered.value;
    let list = listToBeFiltered.getElementsByTagName("textarea");
    for (let i = 0; i < list.length; i++) {
        let dataString = list[i].value.toLowerCase();
        if (dataString.search(str) > -1 && dataString.search(str) < dataString.length) {
            list[i].parentNode.parentNode.style.display = "";
        } else {
            list[i].parentNode.parentNode.style.display = "none";
        }
    }
}

function addNewSuggestion(todoItemName) {
    // add item to array of Rest Api using post request

    let jsonToAdd = { "content": todoItemName };
    // todoSuggestions = [];
    // // empty the datalist each time
    // document.getElementById("todo-list-suggestions").innerHTML = "";

    console.log(JSON.stringify(jsonToAdd));
    fetch("http://localhost:8000/todoapi/v1/suggestions/", { method: 'post', headers: new Headers({ 'content-type': 'application/json' }), body: JSON.stringify(jsonToAdd) })
        .then(resp => { console.log(resp); return resp.json() })
        .then(respJson => respJson.data)
        // .then(jsonArray => jsonArray.forEach(data => todoSuggestions.push(data.content) && addItemInSuggestionsList(data.content)))
        .then(() => addItemInSuggestionsList(todoItemName))
        .catch(err => {
            // removeItemFromSuggestionsList(todoItemName);
            console.log(error);
            // alert("Error in Connecting to Server");
        });



}

function addNewTodoItem(todoItemName, initialTodoStatus) {
    let jsonToAdd = { "content": todoItemName, "checked": initialTodoStatus };


    fetch("http://localhost:8000/todoapi/v1/todo/", { method: 'post', headers: new Headers({ 'content-type': 'application/json' }), body: JSON.stringify(jsonToAdd) })
        .then(response => response.json())
        .then(respJson => respJson.data)
        .then(data => addItemInDisplayList(data))
        .catch(error => {
            console.log(error);
            // alert("Error in Connecting to Server");
        });



}

function updateContentOfItem(todoId, todoItemUpdatedName, checkStatus) {

    if (checkStatus === "") {
        for (let i = 0; i < todoList.length; i++) {
            if (todoList[i].id == todoId) {
                checkStatus = todoList[i].checked;
                break;
            }
        }
    }
    if (checkStatus === "")
        checkStatus = "false";

    let jsonToAdd = { "content": todoItemUpdatedName, "checked": checkStatus };
    console.log(checkStatus + " --- ")

    fetch("http://localhost:8000/todoapi/v1/todo/" + todoId, { method: 'put', headers: new Headers({ 'content-type': 'application/json' }), body: JSON.stringify(jsonToAdd) })
        .then(response => response.json())
        .then(respJson => respJson.data)
        .catch(error => {
            console.log(error);
            // alert("Error in Connecting to Server");
        });

}

function deleteTodo(todoId) {
    fetch("http://localhost:8000/todoapi/v1/todo/" + todoId, { method: 'delete' })
        .then(response => response.json())
        .then(respJson => respJson.data)
        .catch(error => {
            console.log(error);
            // alert("Error in Connecting to Server");
        });
}