$(document).ready(function () {
    getTable();

    $("#updateFormUser").click(function (event) {
        event.preventDefault();
        updateForm();
        $("#editUser").modal('toggle');
        getTable();
    });

    function updateForm() {

        var updateRole = [];
        var userId = $("#updateUserId").val();
        var userName = $("#updateUserName").val();
        var password = $("#updateUserPass").val();

        if ($('#updateUserRole').is(':checked') && $('#updateAdminRole').is(':checked')) {
            updateRole.push({"id": 1, "name": "ROLE_USER"}, {"id": 2, "name": "ROLE_ADMIN"})
        } else if ($('#updateUserRole').is(':checked')) {
            updateRole.push({"id": 1, "name": "ROLE_USER"})
        } else if ($('#updateAdminRole').is(':checked')) {
            updateRole.push({"id": 2, "name": "ROLE_ADMIN"})
        } else {
            updateRole.push({"id": 1, "name": "ROLE_USER"})
        }

        let user = {
            id: userId,
            userName: userName,
            password: password,
            roles: updateRole
        };


        $.ajax({
            type: 'PUT',
            url: "/admin/rest/update/" + userId,

            contentType: 'application/json;',
            data: JSON.stringify(user),

            success: function () {
                getTable();
            }
        });
    }
});