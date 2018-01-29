
var productlist = (function ($,urlutil){

       var productlist = {};
　　　　productlist.init = function () {

           var category = urlutil.getParameter("category");
           var key = urlutil.getParameter("key");
           console.log(category+";"+key);
　　　　};

　　　　return productlist;

　　})(jQuery,urlutil);

$(document).ready(function(){
  productlist.init();
});
