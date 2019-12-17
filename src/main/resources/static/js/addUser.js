$(document).ready(function () {

    $("#addFormUser").click(function (event) {
        event.preventDefault();
        addForm();
        $(':input', '#addForm').val('');    //опустошаем форму ввода
        getTable();
    });

    function addForm() {

        var addRole = [];
        var userName = $("#addName").val();
        var password = $("#addPassword").val();

        //проверка на добавление ролей
        if ($('#addRoleAdmin').is(':checked') && $('#addRoleUser').is(':checked')) {
            addRole.push({"id": 1, "name": "ROLE_USER"}, {"id": 2, "name": "ROLE_ADMIN"})
        } else if ($('#addRoleUser').is(':checked')) {
            addRole.push({"id": 1, "name": "ROLE_USER"})
        } else if ($('#addRoleAdmin').is(':checked')) {
            addRole.push({"id": 2, "name": "ROLE_ADMIN"})
        } else {
            addRole.push({"id": 1, "name": "ROLE_USER"})
        }

        let user = {
            userName: userName,
            password: password,
            roles: addRole
        };

        $.ajax({

            type: 'POST',
            url: "/admin/rest/create",

            contentType: 'application/json;',
            data: JSON.stringify(user),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            async: false, //асинхронное  перезагрузка страницы
            /*dataType: 'JSON',*/
            success: function () {
                getTable();
            }
        });
    }
});