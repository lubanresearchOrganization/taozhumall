var index = (function ($,converter,config,lajaxComponent,searchbar){
       var index = {};
　　　　index.init = function () {
　　　　　　

          searchbar.init();
          //初始化类目
          lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/categorys/",function(result){
            var tree = converter.listToTree(result,{
            idKey:"id",
            parentKey:"parentId",
            childrenKey:"nodes",
            nameFromKey:"name",
            nameToKey:"text"
            });

            $('#categoryTree').treeview(
            {
             data:tree,
             levels:3
            });

            $('#categoryTree').on('nodeSelected', function(event, node) {
               window.location.href = "./productlist.html?recursive=true&category="+node.id;
            });

            });

　　　　};

　　　　return index;

　　})(jQuery,converter,config,lbajax,searchbar);

$(document).ready(function(){
  index.init();
});
