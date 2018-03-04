
var orderlist = (function ($,urlutil,lajaxComponent,objectutil){

       var orderlist = {};
       var urlparams;
       var page;
       var size;
       orderlist.bind = function(){

           $(document).on('click','#searchOrdersBtn',function(){

              $("#rows").html("");
              $('#pageBar').html("");
              urlparams = {};
              var condition = {
                               "page":page,
                               "size":size
                           };
              if($("#statusInput").val()){

                   urlparams.status = $("#statusInput").val();
               }
               if($("#orderNOInput").val()){

                   urlparams.id = $("#orderNOInput").val();
               }
              objectutil.simplecopy(urlparams,condition);
               lajaxComponent.getTextReturnJson(config.baseUrl+"/v/0.1/orders/",condition,function(result){


                                   if(result.pageCount>0){


                                     $("#rows").html($("#resultTemplate").tmpl(result.items));
                                     var options = {
                                     currentPage: result.pageIndex+1,
                                     totalPages: result.pageCount,
                                     bootstrapMajorVersion: 3,
                                     itemContainerClass: function (type, page, current) {
                                            return (page === current) ? "page-item active" : "page-item";
                                      },
                                     itemContentClass:"page-link",
                                     pageUrl: function(type, page, current){

                                              var params = {
                                                   "page":page,
                                                   "size":size,
                                                   "params":JSON.stringify(urlparams)
                                              };
                                             return "./orderlist.html?"+urlutil.concatParam(params);

                                     }
                                   }

                                 $('#pageBar').bootstrapPaginator(options);
                                 }
                          });
           });
       }
　　　　orderlist.init = function () {

           page = urlutil.getParameter("page");
           size = urlutil.getParameter("size");
           urlparams = JSON.parse(urlutil.getParameter("params"))||{};
           if(!page){
           page = 0;
           }else{
           page = page-1;
           }
           if(!size){
           size = 6;
           }
           var condition = {
               "page":page,
               "size":size
           };
           if(urlparams.status){

               $("#statusInput").val(urlparams.status);
           }
           if(urlparams.id){

               $("#orderNOInput").val(urlparams.id);
           }

           objectutil.simplecopy(urlparams,condition);
           //初始化类目
           lajaxComponent.getTextReturnJson(config.baseUrl+"/v/0.1/orders/",condition,function(result){

                     $("#rows").html($("#resultTemplate").tmpl(result.items));
                     if(result.pageCount>0){
                       var options = {
                       currentPage: result.pageIndex+1,
                       totalPages: result.pageCount,
                       bootstrapMajorVersion: 3,
                       itemContainerClass: function (type, page, current) {
                              return (page === current) ? "page-item active" : "page-item";
                        },
                       itemContentClass:"page-link",
                       pageUrl: function(type, page, current){

                                var params = {
                                     "page":page,
                                     "size":size,
                                     "params":JSON.stringify(urlparams)
                                };
                               return "./orderlist.html?"+urlutil.concatParam(params);

                       }
                     }

                   $('#pageBar').bootstrapPaginator(options);
                   }
            });

　　　　};

　　　　return orderlist;

　　})(jQuery,urlutil,lbajax,objectutil);

$(document).ready(function(){

  orderlist.bind();
  orderlist.init();
});
