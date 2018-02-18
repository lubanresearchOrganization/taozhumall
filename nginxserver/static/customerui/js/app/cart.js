var cart = (function (jQuery,urlutil,lajaxComponent,math,lbmap,arrayutil){

     var cart = {};
     cart.init = function () {
         var data = {};
         lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/carts/",
            function(rdata){

                data.productNum = rdata.length;
                var productIds = arrayutil.itemMapFunction(rdata,function(a){return a.productId;}).join(",");
                 for (x in rdata){
                        var item = rdata[x];
                        lbmap.put(item.productId,item.productNum);
                     }
                lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/shopGroupedProducts/?productIds="+productIds,
                                   function(result){


                                       var allTotal = 0;
                                       for (i in result){
                                               var group = result[i];
                                               var products  = group.products||[];
                                               var shop = group.shop||{};
                                               var shopTotal = 0;
                                               for (j in products){
                                                   var product = products[j];
                                                   product.num = lbmap.get(product.id);
                                                   product.total = math.multiply(product.num,product.unitPrice);
                                                   shopTotal = product.total+shopTotal;
                                               }
                                               shop.total = shopTotal;
                                               allTotal = allTotal + shopTotal;
                                       }
                                       data.allTotal = allTotal;
                                        $("#shopTemplate").tmpl(result).appendTo("#shopContent")
                                        $("#shopCommit").html($("#shopCommitTemplate").tmpl(data));


                                   });

            });
     };
     return cart;

})(jQuery,urlutil,lbajax,math,lbmap,arrayutil);

$(document).ready(function(){
  cart.init();
});