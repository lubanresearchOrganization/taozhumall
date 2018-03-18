var userbar = (function (jQuery,lajaxComponent){

       var userbar = {};
       userbar.bind = function(){

       $(document).on('click','#logoutBtn',function(){
       lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/users/logout",function(result, textStatus, jqXHR){
                       console.info(result);
                       window.location.href="./index.html";

       });
       });

       };
　　　　userbar.init = function () {

                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/users/authentication",function(result, textStatus, jqXHR){
                console.info(result);
                $("#userbarContent").html($("#userbarTemplate").tmpl(result));
                });
　　　　};

　　　　return userbar;

　　})(jQuery,lbajax);

$(document).ready(function(){
  userbar.bind();
  userbar.init();
});
