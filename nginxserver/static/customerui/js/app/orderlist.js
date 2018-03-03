
var orderlist = (function ($,urlutil,lajaxComponent){

       var orderlist = {};
　　　　orderlist.init = function () {

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
           var condition = {
               "page":page,
               "size":size
           };

           //初始化类目
                     lajaxComponent.getTextReturnJson(config.baseUrl+"/v/0.1/orders/",condition,function(result){

                     $("#rows").html($("#resultTemplate").tmpl(result.items));
                     if(result.pageCount>0){
                       var options = {
                       currentPage: result.pageIndex+1,
                       totalPages: result.pageCount,
                       bootstrapMajorVersion: 3,
                       itemContainerClass: function (type, page, current) {
                              return (page === current) ? "page-item active" : "page-item";
                        },
                       itemContentClass:"page-link",
                       pageUrl: function(type, page, current){

                                var params = {
                                     "page":page,
                                     "size":size
                                };
                               return "./orderlist.html?"+urlutil.concatParam(params);

                       }
                     }

                   $('#pageBar').bootstrapPaginator(options);
                   }
                   });

　　　　};

　　　　return orderlist;

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  orderlist.init();
});
