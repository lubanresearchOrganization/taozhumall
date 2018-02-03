
var productlist = (function ($,urlutil,lajaxComponent){

       var productlist = {};
　　　　productlist.init = function () {

           var category = urlutil.getParameter("category");
           var key = urlutil.getParameter("key");
           var page = urlutil.getParameter("page");
                      var size = urlutil.getParameter("size");
                      if(!page){
                      page = 0;
                      }else{
                      page = page-1;
                      }
                      if(!size){
                      size = 12;
                      }
            //初始化类目
                                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/products/?page="+page+"&size="+size,function(result){

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

                                                                 return "./productlist.html?page="+page+"&size="+size;

                                                         }
                                }

                              $('#pageBar').bootstrapPaginator(options);
                                  });
　　　　};

　　　　return productlist;

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  productlist.init();
});
