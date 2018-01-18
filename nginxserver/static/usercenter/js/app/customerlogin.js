var customerlogin = (function ($){

       var mod = {};
　　　　mod.init = function () {
　　　　　　
         $("#submitBtn").on("click",function(){
              alert($("#submitBtn").val());
         });
　　　　};

　　　　return mod;

　　})(jQuery);

$(document).ready(function(){
  customerlogin.init();
});
