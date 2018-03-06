var addcomment = (function ($,urlutil,config,lajaxComponent){
       var addcomment = {};
       var productid;
       var shopid;
       addcomment.bind = function(){

          $(document).on('click','#addCommentBtn',function(){

             var data = {};
             var content = $("#contentInput").val();
             var score = $("#scoreInput").val();
             if(content){
                data.content = content;
             }
             data.score = score;
             data.productId = productid;
             console.info(data);


             lajaxComponent.postJsonReturnJson(config.baseUrl+"/v/0.1/comments/",
                        data,
                        function(result){

                        console.info(result);
                        alert("评论成功");
                        window.location.href = "./orderlist.html";
                        }
                        );


          });
       };
　　　　addcomment.init = function () {
　　　　　　
           productid = urlutil.getParameter("productId");
           shopid = urlutil.getParameter("shopId");

           lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/shops/"+shopid,
                           function(result){
                             $("#shopInfo").html($("#shopTemplate").tmpl(result));

                           });
           lajaxComponent.getNoParamReturnJson(config.baseUrl+"/v/0.1/products/"+productid,
                                      function(result){
                                         $("#productInfo").html($("#productTemplate").tmpl(result));
                                         $("#productImageInfo").html($("#productImageTemplate").tmpl(result));

                                      });
       }
       return addcomment;
　　})(jQuery,urlutil,config,lbajax);

$(document).ready(function(){
  addcomment.init();
  addcomment.bind();
});
