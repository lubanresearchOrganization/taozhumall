var shopdetail = (function ($) {

    var mod = {};

    mod.getQueryString = function () {

        var LocString = String(window.document.location.href);
        var rs = new RegExp("(^|)" + name + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
        if (tmp = rs) {
            return decodeURI(tmp[2]);
        }
        // parameter cannot be found
        return "";

    }


    mod.init = function () {

        var id = shopdetail.getQueryString("id");

        $.get(config.shopUrl + "/v/0.1/shops/"+id, function(result){


            $('#shopName').text(result["name"]);
            $("#shopPic").attr('src',result["imgUrl"]);
            $("#desc").text(result["discription"]);
            $("#accountName").text(result["accountName"]);
            $("#mobile").text(result["mobile"]);


        });



    };

    return mod;

})(jQuery, config);



$(document).ready(function () {
    shopdetail.init();
});
