var index = (function ($,converter){

       var index = {};
　　　　index.init = function () {
　　　　　　
          $.get({
           url:config.baseUrl+"/v/0.1/categorys/",
            type:'GET',
            dataType:'JSON',
            success:function(result){
              var tree = converter.listToTree(result,{
              idKey:"id",
              parentKey:"parentId",
              childrenKey:"nodes",
              nameFromKey:"name",
              nameToKey:"text"
              });

              $('#catagoryTree').treeview(
              {
               data:tree,
               levels:3
              });

             }
             });

　　　　};

　　　　return index;

　　})(jQuery,converter,config);

$(document).ready(function(){
  index.init();
});
