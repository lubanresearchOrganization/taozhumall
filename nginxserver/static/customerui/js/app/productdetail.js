var productdetail = (function (jQuery,urlutil,lajaxComponent){

     var productdetail = {};
     productdetail.init = function () {
       var productId = urlutil.getParameter("id");
       var productDetail = {};
        lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/products/"+productId,
        function(result){

            result.commentsTotal = productDetail.commentsTotal;
            productDetail = result;
            $("#productImage").html($("#productImageTemplate").tmpl(result));
            $("#productDetail").html($("#productDetailTemplate").tmpl(result));

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



           var condition = {
           "page":page,
           "size":size,
           "productId":productId
           };
            lajaxComponent.getTextReturnJson(
                              config.baseUrl+"/v/0.1/comments/",condition,function(result){
                              productDetail.commentsTotal = result.total;
                              $("#productDetail").html($("#productDetailTemplate").tmpl(productDetail));
                              $("#commentTitle").html($("#commentTitleTemplate").tmpl(productDetail));
                              $("#rows").html($("#rowTemplate").tmpl(result.items));


                              if(result.pageCount>0){
                                  var options = {
                                                      totalPages: result.pageCount,
                                                      bootstrapMajorVersion: 3,
                                                      itemContainerClass: function (type, page, current) {
                                                           return (page === current) ? "page-item active" : "page-item";
                                                      },
                                                         itemContentClass:"page-link",
                                                      pageUrl: function(type, page, current){

                                                         return "./productdetail.html?id="+productId+"&page="+page+"&size="+size;

                                                         }
                                                      }
                                  options.currentPage = result.pageIndex+1;
                                  $('#pageBar').bootstrapPaginator(options);

                              }

                              });
        });
        $("#addCartItemBtn").click(function(){

           lajaxComponent.postJsonReturnJson(config.baseUrl+"/v/0.1/carts/commands/addCartItem",
           {"productId":productId,
           "num":$("#productNumInput").val()
           },
           function(result){


           alert("添加成功");
           }
           );
        });
        $("#directBuyBtn").click(function(){
         var items = new Array();
         items[0] = {
         "productId":productId,
         "num":$("#productNumInput").val()
         };
         window.location.href = "./confirmdeal.html?items="+JSON.stringify(items)+"&type=directbuy";
        });

     };

     return productdetail;

})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  productdetail.init();
});
