var urlutil = (function (){

       var urlutil = {};
　　　　urlutil.getParameter = function (name) {

                var url_string = window.location.href
                var url = new URL(url_string);
                var value = url.searchParams.get(name);
                console.log(value);
                return value;
　　　　};

　　　　return urlutil;

　　})();

