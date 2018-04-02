var categorymanager = (function ($, lajaxComponent) {

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



        lajaxComponent.getNoParamReturnJson(config.baseUrl + "/v/0.1/categorys/",function (result) {

            zNodes = categorymanager.addParentFlagToTreeData(result);

            zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
        });


    };

    categorymanager.ztreeClick = function (event, treeId, treeNode) {

        console.info(treeNode.id);
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

})(jQuery, lbajax, config);


$(document).ready(function () {
    categorymanager.init();
});
