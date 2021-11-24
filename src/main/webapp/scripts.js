function loadOff() {
    $('#itemsList ul').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/it',
        dataType: 'json'
    }).done(function (data) {
        for (let item of data) {
            if (item.done) {
                continue;
            }
            $('#itemsList ul').append(
                `<li class="list-group-item">
                     <input class="form-check-input me-1" type="radio"  name="check" value="${item.id}" aria-label="...">
                     ${item.description}</li>`);
        }
    }).fail(function (err) {
        console.log(err);
    })
}

function loadAll() {
    $('#itemsList ul').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/all',
        dataType: 'json'
    }).done(function (data) {
        for (let item of data) {
            let checker = '';
            if (item.done) {
                checker = 'checked';
            }
            $('#itemsList ul').append(
                `<li class="list-group-item">
                                <input class="form-check-input me-1" type="checkbox"  name="check" value="${item.id}" aria-label="..." ${checker}>
                                ${item.description}</li>`);
        }
    }).fail(function (err) {
        console.log(err);
    })
}

function newItem () {
    var elements = document.forms[0].elements;
    for (var i = 0; i < elements.length - 1; i++) {
        if ($(elements[i]).val() == '') {
            alert($(elements[i]).attr('title'));
            return false;
        }
    }
    let newTask = $('#addNew').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/it',
        data: {
            task: newTask
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function wasDone() {
    let checks = document.getElementsByName("check");
    let buff;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) {
            buff = checks[i].getAttribute('value');
        }
    }
    let temp = JSON.stringify(buff);
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/close',
        data: {
            value: temp
        },
        dataType: 'json'
    }).fail(function (err) {
        console.log(err);
    });
    window.location.reload();
    return false;
}