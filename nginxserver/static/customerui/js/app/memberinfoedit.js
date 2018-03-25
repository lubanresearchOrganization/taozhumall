var memberinfoedit = (function ($,config,lajaxComponent){
       var memberinfoedit = {};
       memberinfoedit.bind = function(){

                  $(document).on('click','#accountEditBtn',function(){

                      var name = $("#accountNameEdit").val();
                      var mobile = $("#accountMobileEdit").val();
                      if(!name){

                          alert("请输入账户名!");
                          return;
                      }
                      var data = {
                          "name":name,
                          "mobile":mobile
                      };
                      console.info(data);

                      lajaxComponent.putJsonReturnJson(config.baseUrl+"/v/0.1/users/",
                                 data,
                                 function(result){
                                 alert("更新账户信息成功");
                                 }
                                 );


                  });


                  $(document).on('click','#changePasswordBtn',function(){


                      var oldPassword = $("#oldPassword").val();
                      var newPassWord = $("#newPassWord").val();
                      var reNewPassWord = $("#reNewPassWord").val();
                      if(!oldPassword){

                           alert("请输入旧密码!");
                           return;
                      }
                      if(!newPassWord){

                           alert("请输入新密码!");
                           return;
                      }
                      if(!reNewPassWord){

                           alert("请确认新密码!");
                           return;

                      }

                      if(reNewPassWord!=newPassWord){

                           alert("两次输入的新密码不一样!");
                           return;

                       }

                       var data = {
                           "oldPassword":oldPassword,
                           "newPassword":newPassWord
                       };
                       console.info(data);

                       lajaxComponent.postJsonReturnJson(config.baseUrl+"/v/0.1/users/commands/changePassword",
                                                        data,
                                                        function(result){
                                                        alert("更新密码成功");
                                                        }
                                                        );
                  });

       };
　　　　memberinfoedit.init = function () {
　　　　　　
                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/users/authentication",function(result, textStatus, jqXHR){
                console.info(result);
                $("#userInfo").html($("#userInfoTemplate").tmpl(result));
                $("#accountNameEdit").val(result.name);
                $("#accountMobileEdit").val(result.mobile);
                });


　　　　};

　　　　return memberinfoedit;

　　})(jQuery,config,lbajax);

$(document).ready(function(){
  memberinfoedit.bind();
  memberinfoedit.init();
});
