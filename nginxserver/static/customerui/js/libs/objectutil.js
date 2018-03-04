var objectutil = (function (){

       var objectutil = {};
　　　　objectutil.simplecopy = function (source,target) {

                if(source&&target){

                     for(var key in source) {
                         target[key] = source[key];
                     }
                }
　　　　};
      return objectutil;
　　})();
