$(document).ready(function(){
        $('input').blur(function() {
            if (($('#psw1').val() == $('#psw2').val()) && $('#psw1').val() != '' && $('#psw2').val() != '') {
                $('.psw-matcher').show();
                return false;
            }
            else if (($('#psw1').val() != $('#psw2').val()) || ($('#psw1').val() != '' || $('#psw2').val() != '')) {
                $('.psw-matcher').hide();
                return false;
            }
            else { return true; }
        });

        $("#register-button").on("click", function(event) {
            event.preventDefault ? event.preventDefault() : event.returnValue = false;

            if ($('#psw1').val() == $('#psw2').val()) {
                $("#form").submit();
            }
            else {
                alert("The passwords don't match.");
            }
        });

})