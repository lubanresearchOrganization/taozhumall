var categorymanager = (function ($) {

    var categorymanager = {};
    categorymanager.init = function () {


        $.get({
            url: config.baseUrl + "/v/0.1/categorys/?parentId=0",
            type: 'GET',
            dataType: 'JSON',
            success: function (result) {


                categorymanager.addCategoryList(result);

            }
        });


        $("#submitButtom").on("click", function () {


            if ($("#categoryName").val() == '') {

                alert("类目名称不能为空");

                return false;
            }


            var formArray = $("#shopform").serializeArray();


            $.ajax({
                url: config.baseUrl + "/v/0.1/categorys/",
                type: 'POST',
                contentType: "application/json",
                data: categorymanager.arrayToJson(formArray),
                success: function (jsonResult) {
                    $(window).attr('location', 'catagorymanager.html');
                }


            });
        });

    };


    categorymanager.addCategoryList = function (result) {

        for (var i = 0; i < result.length; i++) {

            var category = result[i];

            $("#parent_category_ul").append(" <li  onclick =' categorymanager.liclick(" + JSON.stringify(category) + ") '>" + category.name + "</li>");

        }

    }


    categorymanager.liclick = function (category) {

        $("#top_catagory").text(category.name);
        $("#parentId").val(category.id);

    }


    categorymanager.arrayToJson = function (formArray) {

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


    return categorymanager;

})(jQuery, config);


$(document).ready(function () {
    categorymanager.init();
});
