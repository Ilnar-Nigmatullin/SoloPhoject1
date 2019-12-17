$(document).ready(function () {
    getTable();

    $("#deleteUser").click(function (event) {
        event.preventDefault();
        deleteUserById();
        getTable();
    });

    function deleteUserById() {
        let id = $("#updateUserId").val();

        $.ajax({
            type: 'DELETE',
            url: "/admin/rest/delete/" + id,

            contentType: 'application/json;',
            data: JSON.stringify(id),
            success: function () {
                getTable();
            }
        });
    }
});