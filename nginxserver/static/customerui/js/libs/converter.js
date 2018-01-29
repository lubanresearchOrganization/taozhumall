var converter = (function (){

       var converter = {};
　　　　converter.listToTree = function (data, options) {
        options = options || {};
        var ID_KEY = options.idKey || 'id';
        var PARENT_KEY = options.parentKey || 'parent';
        var CHILDREN_KEY = options.childrenKey || 'children';
        var NAME_FROM_KEY = options.nameFromKey || 'name';
        var NAME_TO_KEY = options.nameToKey || 'name';

        var tree = [], childrenOf = {};
        var item, id, parentId;

        for(var i = 0, length = data.length; i < length; i++) {
          item = data[i];
          id = item[ID_KEY];
          parentId = item[PARENT_KEY] || 0;
          item[NAME_TO_KEY] = item[NAME_FROM_KEY];
          // every item may have children
          childrenOf[id] = childrenOf[id] || [];
          // init its children
          item[CHILDREN_KEY] = childrenOf[id];
          if (parentId != 0) {
            // init its parent's children object
            childrenOf[parentId] = childrenOf[parentId] || [];
            // push it into its parent's children object
            childrenOf[parentId].push(item);
          } else {
            tree.push(item);
          }
        };

     return tree;
     }

    return converter;
　　})();
