$(document).ready(function () {
    getTable();
});

function getTable() {
    $.ajax({
        type: 'GET',
        url: "/admin/rest/all",
        contentType: 'application/json;',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'JSON',
        success: function (listUsers) {
            let htmlTable = "";
            let userRoles = "";
            for (let i = 0; i < listUsers.length; i++) {

                htmlTable += `<tr id="list">`;
                htmlTable += `<td id="tableId" class="text-center">${listUsers[i].id}</td>`;
                for (let k = 0; k < listUsers[i].roles.length; k++) {
                    userRoles += listUsers[i].roles[k].name;
                    userRoles += " ";
                }
                htmlTable += `<td id="tableRole">${userRoles}</td>`;
                userRoles = ""; //опустошаем роли
                htmlTable += `<td id="tableName">${listUsers[i].userName}</td>`;
                htmlTable += `<td id="tablePass" style="max-width: 100px" class="text-truncate">${listUsers[i].password}</td>`;
                htmlTable += `<td><button id="editUserBtn"  class="btn btn-primary" type="button" data-toggle="modal" data-target="#editUser">Edit</button></td>`;
                /* htmlTable+=`<td><button id="deleteUserBtn"  class="btn btn-danger" type="button" data-target="#deleteUser">Delete</button></td>`;*/
                htmlTable += `</tr><br>`;
            }
            $("#tableUser #list").remove();
            $("#tableUser thead").after(htmlTable);
        }
    })
}
