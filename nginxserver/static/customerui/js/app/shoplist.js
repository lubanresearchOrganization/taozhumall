
var shoplist = (function ($,urlutil){

       var shoplist = {};
　　　　shoplist.init = function () {

           var key = urlutil.getParameter("key");
           console.log(key);
　　　　};

　　　　return shoplist;

　　})(jQuery,urlutil);

$(document).ready(function(){
  shoplist.init();
});
