var shopdetail = (function ($,urlutil,lajaxComponent) {

    var mod = {};


    mod.init = function () {

        var id = urlutil.getParameter("id");

        lajaxComponent.getNoParamReturnJson(config.baseUrl + "/v/0.1/shops/"+id,function (result) {

            $('#shopName').text(result["name"]);
            $("#shopPic").attr('src',result["imgUrl"]);
            $("#desc").text(result["discription"]);
            $("#accountName").text(result["accountName"]);
            $("#mobile").text(result["mobile"]);
        });


    };

    return mod;

})(jQuery,urlutil,lbajax);



$(document).ready(function () {
    shopdetail.init();
});
