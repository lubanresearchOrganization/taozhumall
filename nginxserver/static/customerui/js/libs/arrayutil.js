/**
 * 数组工具类
 */

var arrayutil = (function (){

    var arrayutil = {};
　　 /**整个数组通过一个数组mapping**/
    arrayutil.mapFunction = function(array,f){

        if(array == null){

            return array;
        }

        if(array.length == 0){

            return array;
        }

        return f(result);
    };

    /**数组的每一项通过数组mapping**/
    arrayutil.itemMapFunction = function(array,f){

        if(array == null){

            return array;
        }

        if(array.length == 0){

            return array;
        }

        var result = [];
        for(var i = 0; i < array.length; i++){

            result.push(f(array[i]));

        }

        return result;
    };

    /**fold逐项迭代计算**/
    arrayutil.fold = function(array,f,init){

        if(array == null){

            return array;
        }

        if(array.length == 0){

            return array;
        }

        var result = init;
        for(var i = 0; i < array.length; i++){

            result = f(result,array[i]);

        }

        return result;
    };

    return arrayutil;
　　})();
