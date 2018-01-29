
var lajax = (function ($){

       var lajax = {};
　　　　lajax.disableNode = function(node) {
                                if (node) {
                                    node.attr('disabled', "disabled");
                                }
                            };
       lajax.errorCallBack = function(XMLHttpRequest, textStatus, errorThrown) {
                                     var statusCode = XMLHttpRequest.status;
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
                                                 url : url,
                                                 data : data,
                                                 dataType : "text/plain",
                                                 success : sf,
                                                 error : lajax.errorCallBack,
                                                 complete : lajax.complete(node)
                                             });
                                         };
         lajax.postJsonReturnJson = function(url, ldata, sf, node) {
                                            lajax.disableNode(node);
                                            $.ajax({
                                                type : "post",
                                                url : url,
                                                data : JSON.stringify(ldata),
                                                contentType : "application/json; charset=utf-8",
                                                dataType : "json",
                                                success : sf,
                                                error : lajax.errorCallBack,
                                                complete : lajax.complete(node)
                                            });
                                        };
         lajax.postNoParamReturnJson = function(url, sf, node) {
                                               lajax.disableNode(node);
                                               $.ajax({
                                                   type : "post",
                                                   url : url,
                                                   contentType : "application/json; charset=utf-8",
                                                   dataType : "json",
                                                   success : sf,
                                                   error : lajax.errorCallBack,
                                                   complete : lajax.complete(node)
                                               });
                                           };
         lajax.getTextReturnJson = function(url, sf, node) {
                                                        lajax.disableNode(node);
                                                        $.ajax({
                                                            type : "get",
                                                            url : url,
                                                            data : data,
                                                            dataType : "json",
                                                            success : sf,
                                                            error : lajax.errorCallBack,
                                                            complete : lajax.complete(node)
                                                        });
                                                    };

         lajax.getNoParamReturnJson = function(url, sf, node) {
                                                                 lajax.disableNode(node);
                                                                 $.ajax({
                                                                     type : "get",
                                                                     url : url,
                                                                     dataType : "json",
                                                                     success : sf,
                                                                     error : lajax.errorCallBack,
                                                                     complete : lajax.complete(node)
                                                                 });
                                                             };
　　　　return lajax;

　　})(jQuery);