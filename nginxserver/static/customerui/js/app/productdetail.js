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
     };

     return productdetail;

})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  productdetail.init();
});
