var customerlogin = (function ($){

       var mod = {};
　　　　mod.init = function () {
　　　　　　
         $("#submitBtn").on("click",function(){
              $('#loginForm').submit();
              var formData = {
                  "name":$("#nameInput").val(),
                  "password":$("#passwordInput").val()
              };
              $.post("usercente.taozhumall.com/ajaxLogin", formData,
                 function(result){
                   if(result.code==200){
                   alert("登录成功!");
                   window.location.href = result.data;
                   }else{
                     alert(result.message);
                   }
                 }, "json");
         });
　　　　};

　　　　return mod;

　　})(jQuery);

$(document).ready(function(){
  customerlogin.init();
});
