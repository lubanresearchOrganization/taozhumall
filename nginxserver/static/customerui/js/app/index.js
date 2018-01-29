var index = (function ($,converter,config,lajaxComponent){
       var index = {};
　　　　index.init = function () {
　　　　　　

          //选择店铺和商品跳到不同的页面
          $("#searchBtn").click(function(){

              var type = $("#typeInput").val();
              var key = $("#keyInput").val();
              if(key==''){
              $("#keyInput").focus();
              }else{
               if(1==type){
                            window.location.href = "./productlist.html?key="+key;
                            }else if(2==type){
                            window.location.href = "./shoplist.html?key="+key;
                            }
              }


          });
          //初始化类目
          lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/categorys/",function(result){
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

            $('#catagoryTree').on('nodeSelected', function(event, node) {
               window.location.href = "./productlist.html?category="+node.id;
            });

            });

　　　　};

　　　　return index;

　　})(jQuery,converter,config,lajax);

$(document).ready(function(){
  index.init();
});
