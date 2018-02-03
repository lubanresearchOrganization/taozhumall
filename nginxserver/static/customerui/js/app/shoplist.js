
var shoplist = (function ($,urlutil,lajaxComponent){

       var shoplist = {};
　　　　shoplist.init = function () {

           var key = urlutil.getParameter("key");
           var page = urlutil.getParameter("page");
           var size = urlutil.getParameter("size");
           if(!page){
           page = 0;
           }else{
           page = page-1;
           }
           if(!size){
           size = 6;
           }
           console.log(key);

           //初始化类目
                     lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/shops/?page="+page+"&size="+size,function(result){

                     $("#rows").html($("#resultTemplate").tmpl(result.items));
                       var options = {
                       currentPage: result.pageIndex+1,
                       totalPages: result.pageCount,
                       bootstrapMajorVersion: 3,
                       itemContainerClass: function (type, page, current) {
                              return (page === current) ? "page-item active" : "page-item";
                        },
                       itemContentClass:"page-link",
                       pageUrl: function(type, page, current){

                               return "./shoplist.html?page="+page+"&size="+size;

                       }
                     }

                   $('#pageBar').bootstrapPaginator(options);
                       });
　　　　};

　　　　return shoplist;

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  shoplist.init();
});
