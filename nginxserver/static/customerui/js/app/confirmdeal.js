var confirmdeal = (function (jQuery,urlutil,lajaxComponent,math,lbmap){

     var confirmdeal = {};
     confirmdeal.init = function () {
     var type = urlutil.getParameter("type");


        if(type=="directbuy"){
           var items = JSON.parse(urlutil.getParameter("items"));
             for (x in items){
                var item = items[x];
                lbmap.put(item.productId,item.num);
             }
           var item = items[0];

           lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/shopGroupedProducts/?productIds="+item.productId,
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

                       $("#formContent").html($("#shopProductsTemplate").tmpl(result));
                       $("#formCommit").html($("#formCommitTemplate").tmpl(allTotal));

                   });
        }
        if(type=="settle"){
           var productIds = urlutil.getParameter("productIds");
                      lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/carts/?productIds="+productIds,
                              function(result){
                              for (x in result){
                                              var item = result[x];
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

                                                     $("#formContent").html($("#shopProductsTemplate").tmpl(result));
                                                     $("#formCommit").html($("#formCommitTemplate").tmpl(allTotal));

                                                 });
                      });
        }
      };
     return confirmdeal;

})(jQuery,urlutil,lbajax,math,lbmap);

$(document).ready(function(){
  confirmdeal.init();
});