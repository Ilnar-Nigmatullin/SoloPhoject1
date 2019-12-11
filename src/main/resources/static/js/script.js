$(document).ready(function () {
    getTable();

    /*

        $('#addUserButton').click(function () {
            const roleId = $("#role").val();
            let user = {
                userName: $("#name").val(),
                password: $("#password").val()
            };

            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/api/role/" + roleId,
                success: function (role) {
                    user['role'] = role;
                },
                error: function (request, status, error) {
                    console.log(status, error);
                }
            });
    */

    /*=================================================================================================================*/

    //modal form
    $(document).on('click', '#editUserBtn', function () {

        $("#updateUserId").val($(this).closest("tr").find("#tableId").text());
        $("#updateUserId").prop("disabled", true);

        $("#updateUserName").val($(this).closest("tr").find("#tableName").text());

        //$("#updateUserPass").hide();
        $("#updateUserPass").val($(this).closest("tr").find("#tablePass").text());
        $("#updateUserPass").prop("disabled", true);

        let role = $(this).closest("tr").find("#tableRole").text();
        let admin = "ROLE_ADMIN";
        if (role === admin) {
            $('#updateUserRole option:contains("ROLE_ADMIN")').prop("selected", true);
        } else {
            $('#updateUserRole option:contains("ROLE_USER")').prop("selected", true);
        }
    });

    //addForm
    $("#addFormUser").click(function (event) {
        event.preventDefault();
        addForm();
        $(':input', '#addForm').val('');
    });

    $("#resetTable").click(function () {
        getTable();
    });

    function addForm() {

        let user = {
            'userName': $("#addName").val(),
            'password': $("#addPassword").val(),
            'roles': $("#addRole").val()
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
            async: true,
            dataType: 'JSON',
        });
    }

    //updateForm
    $("#updateFormUser").click(function (event) {
        event.preventDefault();
        updateForm();
        $("#editUser").modal('toggle');
        getTable();
    });

    function updateForm() {
        let user = {
            'id': $("#updateUserId").val(),
            'userName': $("#updateUserName").val(),
            'password': $("#updateUserPass").val(),
            'name': $("#updateUserRole").val(),
            'role_admin': $("#updateAdminRole").val()
        };

        $.ajax({

            type: 'Put',
            url: "/admin/rest/update",

            contentType: 'application/json;',
            data: JSON.stringify(user),

            success: function () {
                getTable();
            }
        });
    }

    //deleteForm
    $("#deleteUser").click(function (event) {
        event.preventDefault();
        deleteUser();
        getTable();
    });

    function deleteUser() {
        let id = $("#updateUserId").val();

        $.ajax({
            type: 'DELETE',
            url: "/admin/rest/delete",

            contentType: 'application/json;',
            data: JSON.stringify(id),
            success: function () {
                getTable();
            }
        });
    }

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
                for (let i = 0; i < listUsers.length; i++) {
                    let htmlRole = listUsers[i].roles[0].name;

                    htmlTable += `<tr id="list">
                        <td id="tableId" class="text-center">${listUsers[i].id}</td>
                        <td id="tableRole"> ${htmlRole} </td>
                        <td id="tableName"> ${listUsers[i].userName}  </td>
                        <td id="tablePass" style="max-width: 100px" class="text-truncate">  ${listUsers[i].password}  </td>
                        <td><button id="editUserBtn"  class="btn btn-primary" type="button" data-toggle="modal" data-target="#editUser">Edit</button></td>
                        </tr><br>`;
                }
                $("#list").remove();
                $("#tableUser thead").after(htmlTable);
            }
        })
    }
});