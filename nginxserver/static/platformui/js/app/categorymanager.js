var categorymanager = (function ($, lajaxComponent, config,formComponent) {

    var categorymanager = {};
    var zTreeObj;
    var currentParentId;
    var currentId;


    categorymanager.bind = function(){

    $(document).on('click','#addRoot',function(){
       $('#addRootCategoryPanel').modal('show');
       $("#addRootForm").clearForm();
    });

    $(document).on('click','#addSubCategoryBtn',function(){

           var formArray = $("#addForm").serializeArray();
           var data = formComponent.formtoarray(formArray);
           data.parentId = currentParentId;
           lajaxComponent.postJsonReturnJson(config.baseUrl + "/v/0.1/categorys/", data,function (result) {

                 categorymanager.init();
                 $('#addCategoryPanel').modal('hide');
            });
    });

    $(document).on('click','#editCategoryBtn',function(){
           var formArray = $("#editForm").serializeArray();
           var data = formComponent.formtoarray(formArray);
           console.info(JSON.stringify(data));
    });

    $(document).on('click','#addRootCategoryBtn',function(){
           var formArray = $("#addRootForm").serializeArray();
           var data = formComponent.formtoarray(formArray);
           data.parentId = 0;

           lajaxComponent.postJsonReturnJson(config.baseUrl + "/v/0.1/categorys/", data,function (result) {

                 categorymanager.init();
                 $('#addRootCategoryPanel').modal('hide');
            });

    });


    };
    categorymanager.init = function () {



        var setting = {

            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId",
                    rootPId: ""
                }
            }

            , callback: {
                onRightClick: categorymanager.rightClick

            }

        };
        var zNodes;



        lajaxComponent.getNoParamReturnJson(config.baseUrl + "/v/0.1/categorys/",function (result) {

            zTreeObj = $.fn.zTree.init($("#tree"), setting, result);
        });


    };

    categorymanager.rightClick = function(event, treeId, treeNode){
         zTreeObj.selectNode(treeNode);
           if(treeNode) {
       //弹出菜单
               $("#menu").popupSmallMenu({
                   event : event,
                   onClickItem  : function(type) {
                      currentId = treeNode.id;
                      if(type == 'edit'){
                         categorymanager.showEditPanel(treeNode);
                      }
                      if(type == 'addSubCategory'){
                         currentParentId = treeNode.id;
                         categorymanager.showAddPanel(treeNode.id);
                      }
                      if(type == 'delete'){
                         categorymanager.delete(treeNode.id);
                      }
                   }
           });

           }
    }

    categorymanager.delete = function(id){

        alert("删除"+id);
    }
    categorymanager.showEditPanel = function(treeNode){
                console.info(treeNode.id);
                $('#categoryName').val(treeNode.name);
                $("#editForm").clearForm();
                $('#editPanel').modal('show');
        }
    categorymanager.showAddPanel = function(id){
            console.info(id);
            $("#addForm").clearForm();
            $('#addCategoryPanel').modal('show');
    }

    return categorymanager;

})(jQuery, lbajax, config,formutil);


$(document).ready(function () {
    categorymanager.init();
    categorymanager.bind();
});
