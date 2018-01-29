

var lbmap = (function (){

       var current = {};
       //键集
           current.keySet = [];
           //值集
           current.entitySet = [];

           //加入映射对
           current.put = function(key,value){

               if(current.keySet.length==0){
                   current.keySet.push(key);
                   current.entitySet.push(value);
                   return;
               }

               var find = false;
               for(var i = 0; i < current.keySet.length; i++){

                   if(current.keySet[i]==key){

                       current.entitySet[i] = value;
                       find = true;
                   }
               }

               if(!find){

                   current.keySet.push(key);
                   current.entitySet.push(value);
               }

           };

           //通过key获取value
           current.get = function(key){

               if(typeof(key) == "undefined")
               {
                   return null;
               }

               for(var i = 0; i < current.keySet.length; i++){

                   if(current.keySet[i]==key){

                       return current.entitySet[i];
                   }

               }
           };

           //获取实体集
           current.getEntitySet = function(){

               return current.entitySet;
           };

           //获取键集
           current.getKeySet = function(){

               return current.keySet;
           };

           //定义一个自反函数
           current.selfNegative = function(o){

               return o;
           };

           //转对象但是对key,value有处理函数
           current.toObjectWithFunction = function(kf,vf){

               var json = {};

               if(current.keySet == null){

                   return json;
               }

               if(current.entitySet.length==0 || current.keySet.length ==0){

                   return json;
               }

               for(var i = 0; i < current.keySet.length; i++){

                   var key = kf(current.keySet[i]);
                   var value = vf(current.entitySet[i]);
                   //通过传入的函数处理里面的k、v
                   json[key] = value;
               }

               return json;
           };

           //转对象
           current.toObject = function(){

               //默认传两个自反函数
               return current.toObjectWithFunction(current.selfNegative,current.selfNegative);
           };


           //转对象但是对value有处理函数
           current.toObjectWithValueFunction = function(vf){

               //对key还是用自反函数
               return current.toObjectWithFunction(current.selfNegative,vf);
           };

           //转对象但是对key有处理函数
           current.toObjectWithKeyFunction = function(kf){

               //对value还是用自反函数
               return current.toObjectWithFunction(kf,current.selfNegative);
           };
　　　　return current;

　　})();
