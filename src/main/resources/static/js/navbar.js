$.fn.extend({
    toggleText: function(a, b){
        return this.text(this.text() == b ? a : b);
    }
});

$( document ).ready(function() {

    $('.navTrigger').click(function () {
        $(this).toggleClass('active');
        console.log("Clicked menu");
        $("#mainListDiv").toggleClass("show_list");
        $('#m-calc').toggleText('Mortgage Calculator', 'Mortgage Calc');
        $("#mainListDiv").fadeIn();

    });

});
