var memberregister = (function ($){

       var memberregister = {};
　　　　memberregister.init = function () {
　　　　　　
         $("#submitBtn").on("click",function(){

              var name = $("#nameInput").val();
              var password = $("#passwordInput").val();
              var repassword = $("#repasswordInput").val();
              if(!name){
                alert("请输入用户名!");
                return false;
              }
              if(!password){
                alert("请输入密码!");
                return false;
              }
              if(!repassword){
                alert("请确认密码!");
                return false;
              }
              if(repassword != repassword){
                alert("两次密码不一致，请重新确认!");
                return false;
              }

              var formData = {
                  "name":name,
                  "password":password
              };

               $.ajax({
                      type : "post",
                      url : "/register",
                      data : JSON.stringify(formData),
                      contentType : "application/json; charset=utf-8",
                      dataType : "json",
                      error : function(result){
                         alert(result.message);
                      },
                      success : function(result){
                                 alert("注册成功!");
                                 window.location.href = "http://www.taozhumall.com";
                                }
                     });


         });
　　　　};

　　　　return memberregister;

　　})(jQuery);

$(document).ready(function(){
  memberregister.init();
});
