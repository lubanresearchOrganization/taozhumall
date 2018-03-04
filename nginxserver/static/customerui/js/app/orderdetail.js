
var orderdetail = (function ($,urlutil,lajaxComponent,dateutil,arrayutil,math){

       var orderdetail = {};
　　　　orderdetail.init = function () {
            var id = urlutil.getParameter("id");
            lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/orders/"+id,
                function(result){

                  console.info(result);
                  result.createTimeStr = dateutil.format(new Date(result.createTime),"yyyy-MM-dd hh:mm:ss");
                  result.orderItemList = arrayutil.itemMapFunction(result.orderItemList,function(data){
                  data.total = math.multiply(data.productNum,data.unitPrice);
                  return data;
                  });
                  $("#detail").html($("#detailTemplate").tmpl(result));
                });
       }

      return orderdetail;
　　})(jQuery,urlutil,lbajax,dateutil,arrayutil,math);

$(document).ready(function(){
  orderdetail.init();
});
