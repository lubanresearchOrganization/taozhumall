var categorymanager = (function ($, converter) {

    var categorymanager = {};
    categorymanager.init = function () {


        var zTreeObj;
        var setting = {

            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId",
                    rootPId: ""
                }
            }

            , callback: {
                onClick: categorymanager.ztreeClick
            }

        };
        var zNodes;


        $.get({
            url: config.categoryUrl + "/v/0.1/categorys/",
            type: 'GET',
            dataType: 'JSON',
            success: function (result) {



                zNodes = categorymanager.addParentFlagToTreeData(result);

                zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
            }
        });

    };

    categorymanager.ztreeClick = function (event, treeId, treeNode) {

        alert(treeNode.id);

    }

    categorymanager.addParentFlagToTreeData = function (result) {

        for (var i = 0; i < result.length; i++) {

            var category = result[i];


            if (category.parentId == 0) {

                category["isParent"] = true;

            }

        }

        return result;

    }


    return categorymanager;

})(jQuery, converter, config);


$(document).ready(function () {
    categorymanager.init();
});
