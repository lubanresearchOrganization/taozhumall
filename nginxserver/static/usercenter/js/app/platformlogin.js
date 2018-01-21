var platformlogin = (function ($){

       var mod = {};
　　　　mod.init = function () {
　　　　　　
         $("#submitBtn").on("click",function(){
              $('#loginForm').submit();
         });
　　　　};

　　　　return mod;

　　})(jQuery);

$(document).ready(function(){
  platformlogin.init();
});
