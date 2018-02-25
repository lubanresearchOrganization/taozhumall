var cart = (function ($,urlutil,lajaxComponent,math,lbmap,arrayutil){

     var cart = {};
     cart.bind = function(){

             $(document).on('click','.itemDeleteBtn',function(){
                            var itemId = $(this).attr("itemid");
                            var params = {"productId":itemId};

                            lajaxComponent.postTextReturnJson(
                            config.baseUrl+"/v/0.1/carts/commands/removeCartItem",params,function(result){
                                                            //window.location.href = "./cart.html";
                                  cart.init();
                            });

                            });
              $(document).on('change','.itemNumInput',function(){
                                          var itemId = $(this).attr("itemid");
                                          var params = {
                                          "productId":itemId,
                                          "num":$(this).val()
                                          };

                                          lajaxComponent.postJsonReturnJson(
                                          config.baseUrl+"/v/0.1/carts/commands/changeNum",params,function(result){
                                                                          //window.location.href = "./cart.html";
                                                cart.init();
                                          });

                                          });

               $(document).on('click','.selectAll',function(){

                      var value = $(this).prop("checked");
                      $(".selectAll").prop("checked",value);
                      $(".selectShop").prop("checked",value);
                      $(".selectItem").prop("checked",value);
               });

               $(document).on('click','.selectShop',function(){

                      var value = $(this).prop("checked");
                      var shopid = $(this).attr("shopid");
                      $(".selectItem[shopid="+shopid+"]").prop("checked",value);
               });
               $(document).on('click','#confirmdealBtn',function(){

                                     var items = [];

                                     $.each($('input[name=selectedItemsInput]:checked'),function(){
                                                     var itemid = $(this).attr("itemid");
                                                     items.push(itemid);
                                                 });
                                     var productIds = items.join(",");
                                     if(items.length==0){
                                        alert("你可能没有选好要结算的商品!")
                                     }else{
                                      window.location.href = "./confirmdeal.html?productIds="+productIds+"&type=settle";
                                     }

                              });

     };
     cart.init = function () {
         var data = {};
         $("#shopContent").html("");
         $("#shopCommit").html("");
         lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/carts/",
            function(rdata){

                data.productNum = rdata.length;
                if(data.productNum == 0){
                   return;
                }
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
                                        $("#shopTemplate").tmpl(result).appendTo("#shopContent");
                                        $("#shopCommit").html($("#shopCommitTemplate").tmpl(data));


                                   });

            });
     };
     return cart;

})(jQuery,urlutil,lbajax,math,lbmap,arrayutil);

$(document).ready(function(){

  cart.bind();
  cart.init();

});