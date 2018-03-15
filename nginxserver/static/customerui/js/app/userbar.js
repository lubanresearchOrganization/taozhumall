var userbar = (function (jQuery,lajaxComponent){

       var userbar = {};
　　　　userbar.init = function () {

                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/users/authentication",function(result, textStatus, jqXHR){
                console.info(result);
                });
　　　　};

　　　　return userbar;

　　})(jQuery,lbajax);

$(document).ready(function(){
  userbar.init();
});
