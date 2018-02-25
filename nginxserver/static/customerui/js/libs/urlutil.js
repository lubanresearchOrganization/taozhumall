var urlutil = (function (){

       var urlutil = {};
　　　　urlutil.getParameter = function (name) {

                var url_string = window.location.href
                var url = new URL(url_string);
                var value = url.searchParams.get(name);
                console.log(value);
                return value;
　　　　};

　　　　
      urlutil.concatParam = function(param){
                                var paramStr="";
                                if(param.length==0){
                                return "";
                                }
                                for(var field in param){

                                    paramStr+="&"+field+"="+encodeURIComponent(param[field]);

                                }
                                return paramStr.substr(1);
                            };
      return urlutil;
　　})();

