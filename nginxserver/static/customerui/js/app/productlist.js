
var productlist = (function ($,urlutil,lajaxComponent){

       var productlist = {};
　　　　productlist.init = function () {

           var category = urlutil.getParameter("category");
           var key = urlutil.getParameter("key");
            //初始化类目
                                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/products/?size=12&page=0",function(result){

                                $("#rows").html($("#resultTemplate").tmpl(result.items));
                                  var options = {
                                  currentPage: result.pageIndex+1,
                                  totalPages: result.pageCount
                                }

                              $('#pageBar').bootstrapPaginator(options);
                                  });
　　　　};

　　　　return productlist;

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  productlist.init();
});
