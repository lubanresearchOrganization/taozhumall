var addshop = (function ($, lajaxComponent, formComponent) {

    var mod = {};


    mod.init = function () {

        $("#submitButtom").on("click", function () {


            if ($("#shopName").val() == '') {

                alert("店铺名称不能为空");

                return false;
            }

            if ($("#imageUrl").val() == '') {

                alert("请选择图片");

                return false;
            }


            if ($("#accountName").val() == '') {

                alert("请输入账号名称");

                return false;

            }

            if ($("#accountPhone").val() == '') {

                alert("请输入手机号码");

                return false;

            }


            var formArray = $("#shopform").serializeArray();
            var data = formComponent.formtoarray(formArray);
            data.imgUrl = "http://img.zcool.cn/community/01b5f75715286632f8758c9b40f665.jpg";
            lajaxComponent.postJsonReturnJson(config.baseUrl + "/v/0.1/shops/", data, function (result) {

                $(window).attr('location', 'shopmanager.html');


            });

        });
    };

    return mod;

})(jQuery, lbajax, formutil);


$(document).ready(function () {
    addshop.init();
});
