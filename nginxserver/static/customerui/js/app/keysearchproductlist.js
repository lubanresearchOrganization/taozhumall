
var keysearchproductlist = (function ($,urlutil,lajaxComponent,searchbar){

       var keysearchproductlist = {};
　　　　keysearchproductlist.init = function () {

           searchbar.init();
           var key = urlutil.getParameter("key");
           var page = urlutil.getParameter("page");
                      var size = urlutil.getParameter("size");
                      if(!page){
                      page = 0;
                      }else{
                      page = page-1;
                      }
                      if(!size){
                      size = 12;
                      }



           var condition = {
           "type":"keySearch",
           "page":page,
           "size":size
           };
           if(key){
            condition.key = key;
           }


            //初始化类目
                  lajaxComponent.getTextReturnJson(
                  config.baseUrl+"/v/0.1/products/",condition,function(result){

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
                                                "size":size
                                          };
                                         if(key){
                                             params.key = key;
                                         }
                                         return "./keysearchproductlist.html?"+urlutil.concatParam(params);
                               }
                       };
                       $('#pageBar').bootstrapPaginator(options);
                  }

                  });
　　　　};

　　　　return keysearchproductlist;

　　})(jQuery,urlutil,lbajax,searchbar);

$(document).ready(function(){
  keysearchproductlist.init();
});
