var productdetail = (function (jQuery,urlutil,lbajax){

     var productdetail = {};
     productdetail.init = function () {
       var productId = urlutil.getParameter("productId");

       alert(productId);
     };

     return productdetail;

})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  productdetail.init();
});
