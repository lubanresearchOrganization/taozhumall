
var shoplist = (function ($,urlutil,lajaxComponent){

       var shoplist = {};
　　　　shoplist.init = function () {

           var key = urlutil.getParameter("key");
           console.log(key);

           //初始化类目
                     lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/shops/?size=12",function(result){

                     $("#rows").html($("#resultTemplate").tmpl(result.items));
                       var options = {
                       currentPage: result.pageIndex+1,
                       totalPages: result.pageCount
                     }

                   $('#pageBar').bootstrapPaginator(options);
                       });
　　　　};

　　　　return shoplist;

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  shoplist.init();
});
