var categorymanager = (function ($,converter){

    var categorymanager = {};
    categorymanager.init = function () {


        $.get({
            url:config.categoryUrl+"/v/0.1/categorys/",
            type:'GET',
            dataType:'JSON',
            success:function(result){


                console.log(result);

                var tree = converter.listToTree(result,{
                    idKey:"id",
                    parentKey:"parentId",
                    childrenKey:"nodes",
                    nameFromKey:"name",
                    nameToKey:"text"
                });

                console.log(tree);
                $('#treeDemo').treeview(
                    {
                        data:tree,
                        levels:3
                    });

            }
        });

    };

    return categorymanager;

})(jQuery,converter,config);


$(document).ready(function(){
    categorymanager.init();
});
