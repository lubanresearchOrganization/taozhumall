var categorymanager = (function ($, lajaxComponent,formComponent) {

    var categorymanager = {};
    categorymanager.init = function () {

        lajaxComponent.getNoParamReturnJson(config.baseUrl + "/v/0.1/categorys/?parentId=0", function (result) {


            console.log(result);

            categorymanager.addCategoryList(result);
        });


        $("#submitButtom").on("click", function () {


            if ($("#categoryName").val() == '') {

                alert("类目名称不能为空");

                return false;
            }


            var formArray = $("#shopform").serializeArray();



            lajaxComponent.postJsonReturnJson(config.baseUrl + "/v/0.1/categorys/", formComponent.formtoarray(formArray),function (result) {

                $(window).attr('location', 'catagorymanager.html');


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



    return categorymanager;

})(jQuery, lbajax,formutil);


$(document).ready(function () {
    categorymanager.init();
});
