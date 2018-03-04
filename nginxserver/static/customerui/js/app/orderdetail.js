
var orderdetail = (function ($,urlutil,lajaxComponent){

       var orderdetail = {};
　　　　orderdetail.init = function () {
            var id = urlutil.getParameter("id");
            lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/orders/"+id,
                function(result){


                });
       }

　　})(jQuery,urlutil,lbajax);

$(document).ready(function(){
  shoplist.init();
});
