var productdetail = (function (jQuery,urlutil,lajaxComponent){

     var productdetail = {};
     productdetail.init = function () {
       var productId = urlutil.getParameter("id");
        lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/products/"+productId,
        function(result){


            $("#productImage").html($("#productImageTemplate").tmpl(result));
            $("#productDetail").html($("#productDetailTemplate").tmpl(result));
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
