var addshop = (function ($) {

    var mod = {};

    mod.arrayToJson = function (formArray) {

        var dataArray = {};
        $.each(formArray, function () {
            if (dataArray[this.name]) {
                if (!dataArray[this.name].push) {
                    dataArray[this.name] = [dataArray[this.name]];
                }
                dataArray[this.name].push(this.value || '');
            } else {
                dataArray[this.name] = this.value || '';
            }
        });
        return JSON.stringify(dataArray);

    }


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


            if($("#accountName").val() == ''){

                alert("请输入账号名称");

                return false;

            }

            if($("#accountPhone").val() == ''){

                alert("请输入手机号码");

                return false;

            }



            var formArray = $("#shopform").serializeArray();


            $.ajax({
                url: config.baseUrl + "/v/0.1/shops/",
                type: 'POST',
                contentType: "application/json",
                data: addshop.arrayToJson(formArray),
                success: function (jsonResult) {
                    $(window).attr('location','shopmanager.html');
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }


            });
        });
    };

    return mod;

})(jQuery, config);




$(document).ready(function () {
    addshop.init();
});
