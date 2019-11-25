(function($) {

    $(".toggle-password").click(function() {

        $(this).toggleClass("zmdi-eye zmdi-eye-off");
        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
          input.attr("type", "text");
        } else {
          input.attr("type", "password");
        }
    });




})(jQuery);

$(document).ready(function () {

    $('#signup-form').on("submit", function (e) {
        e.preventDefault();
        let login = $('#login').val();
        let password = $('#password').val();
        let email = $('#email').val();

        $.ajax({
            type: "POST",
            url: "registration",
            data: {"login": login, "password": password, "email": email},
            success: function (data) {
                console.log(data);
                if (data.success) {
                    alert("Successfully registered");
                    $(location).attr('href', data.url)
                } else {
                    let textNode = document.createTextNode(data.message);
                    $('.register-message').append(textNode);
                }

            }
        })
    })
})