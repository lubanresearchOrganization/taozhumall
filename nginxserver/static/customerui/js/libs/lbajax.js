
var lbajax = (function ($){

       var lajax = {};
　　　　lajax.disableNode = function(node) {
                                if (node) {
                                    node.attr('disabled', "disabled");
                                }
                            };
       lajax.beforeSend = function(request) {

                              var url = new URL(window.location.href);
                              var st = url.searchParams.get("st");
                              if(st){
                              request.setRequestHeader("st",st);
                              }
                              request.setRequestHeader("request-client","non-page");
                            };

       lajax.errorCallBack = function(xhr, textStatus, errorThrown) {
                                     console.log(xhr.getAllResponseHeaders());
                                     var statusCode = xhr.status;
                                     if (statusCode == 901) {
                                         alert("服务已超时，需重新加载页面。");
                                         window.location.reload();
                                     }
                                     if (statusCode >= 500 && statusCode <= 600 ) {
                                         alert("服务器异常，请联系管理员。状态编码：" + statusCode);
                                     }
                                     if (statusCode >= 400 && statusCode <500) {
                                         alert("请求参数错误，请联系管理员。状态编码：" + statusCode);
                                     }
                                     console.log("状态编码：" + statusCode);
                                 };
       lajax.wrapperSuccess = function(sf){

              var result =  function(data, textStatus, jqXHR){
                              if(data.redirect){
                                 window.location.href = data.redirect+config.indexUrl;
                              }
                             (sf)(data,textStatus,jqXHR);
              };
              return result;
       }
       lajax.complete = function(node) {
                                var callback = function(XMLHttpRequest, textStatus) {

                                    if (node) {
                                        node.removeAttr('disabled');
                                    }
                                };
                                return callback;
                            };
       lajax.post = function(url, data, sf, type, node) {
                            lajax.disableNode(node);
                            $.ajax({
                                type : "post",
                                beforeSend: lajax.beforeSend,
                                url : url,
                                data : data,
                                dataType : type,
                                success : sf,
                                error : lajax.errorCallBack,
                                complete : lajax.complete(node)
                            });
                        };


         lajax.postTextReturnJson =  function(url, data, sf, node) {
                                                lajax.disableNode(node);
                                                $.ajax({
                                                    type : "post",
                                                    beforeSend: lajax.beforeSend,
                                                    url : url,
                                                    data : data,
                                                    dataType : "json",
                                                    success : sf,
                                                    error : lajax.errorCallBack,
                                                    complete : lajax.complete(node)
                                                });
                                            };
         // 传的参数是text格式，返回的是text格式
         lajax.postTextReturnText  = function(url, data, sf, node) {
                                             lajax.disableNode(node);
                                             $.ajax({
                                                 type : "post",
                                                 beforeSend: lajax.beforeSend,
                                                 url : url,
                                                 data : data,
                                                 dataType : "text/plain",
                                                 success : lajax.wrapperSuccess(sf),
                                                 error : lajax.errorCallBack,
                                                 complete : lajax.complete(node)
                                             });
                                         };
         lajax.postJsonReturnJson = function(url, ldata, sf, node) {
                                            lajax.disableNode(node);
                                            $.ajax({
                                                type : "post",
                                                beforeSend: lajax.beforeSend,
                                                url : url,
                                                data : JSON.stringify(ldata),
                                                contentType : "application/json; charset=utf-8",
                                                dataType : "json",
                                                success : lajax.wrapperSuccess(sf),
                                                error : lajax.errorCallBack,
                                                complete : lajax.complete(node)
                                            });
                                        };
         lajax.postNoParamReturnJson = function(url, sf, node) {
                                               lajax.disableNode(node);
                                               $.ajax({
                                                   type : "post",
                                                   beforeSend: lajax.beforeSend,
                                                   url : url,
                                                   contentType : "application/json; charset=utf-8",
                                                   dataType : "json",
                                                   success : lajax.wrapperSuccess(sf),
                                                   error : lajax.errorCallBack,
                                                   complete : lajax.complete(node)
                                               });
                                           };
         lajax.getNoParamReturnText = function(url, sf, node) {
                                                        lajax.disableNode(node);
                                                        $.ajax({
                                                            type : "get",
                                                            beforeSend: lajax.beforeSend,
                                                            url : url,
                                                            dataType : "text/plain",
                                                            success : lajax.wrapperSuccess(sf),
                                                            error : lajax.errorCallBack,
                                                            complete : lajax.complete(node)
                                                        });
                                                    };
         lajax.getTextReturnJson = function(url,data, sf, node) {
                                                        lajax.disableNode(node);
                                                        $.ajax({
                                                            type : "get",
                                                            beforeSend: lajax.beforeSend,
                                                            url : url,
                                                            data : data,
                                                            dataType : "json",
                                                            success : lajax.wrapperSuccess(sf),
                                                            error : lajax.errorCallBack,
                                                            complete : lajax.complete(node)
                                                        });
                                                    };

         lajax.getNoParamReturnJson = function(url, sf, node) {
                                                                 lajax.disableNode(node);
                                                                 $.ajax({
                                                                     type : "get",
                                                                     beforeSend: lajax.beforeSend,
                                                                     url : url,
                                                                     dataType : "json",
                                                                     success : lajax.wrapperSuccess(sf),
                                                                     error : lajax.errorCallBack,
                                                                     complete : lajax.complete(node)
                                                                 });
                                                             };
　　　　return lajax;

　　})(jQuery);