var searchbar = (function (){

       var searchbar = {};
　　　　searchbar.init = function () {

                //选择店铺和商品跳到不同的页面
                          $("#searchBtn").click(function(){

                              var type = $("#searchTypeInput").val();
                              var key = $("#searchKeyInput").val();
                              if(key==''){
                              $("#searchKeyInput").focus();
                              }else{
                               if(1==type){
                                            window.location.href = "./keysearchproductlist.html?key="+key;
                                  }else if(2==type){
                                            window.location.href = "./shoplist.html?key="+key;
                                  }
                              }


                          });
　　　　};

　　　　return searchbar;

　　})();

