$(document).on('click', '#editUserBtn', function () {

    $("#updateUserId").val($(this).closest("tr").find("#tableId").text());
    $("#updateUserId").prop("disabled", true);
    $("#updateUserName").val($(this).closest("tr").find("#tableName").text());
    $("#updateUserPass").val($(this).closest("tr").find("#tablePass").text());

    let role = $(this).closest("tr").find("#tableRole").text();
    let admin = "ROLE_ADMIN ";
    if (role === admin) {
        $('#updateUserRole input:contains("ROLE_ADMIN")').prop("checked");
    } else {
        $('#updateUserRole input:contains("ROLE_USER")').prop("checked", true);
    }
});