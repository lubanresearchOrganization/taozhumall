


var shopmanager = (function ($) {

    var dttable;

    var index = {};

    index.del =  function(data) {


        var msg = "您真的确定要删除吗？\n\n请确认！";

        if (confirm(msg)==true){
            $.ajax({
                url: config.shopUrl + "/v/0.1/shops/"+data,
                type : "delete",
                data : {

                },
                contentType: "application/json",
                success : function(result){

                    console.log(result)

                    if ($('#shop').hasClass('dataTable')) {
                        dttable = $('#shop').dataTable();
                        dttable.fnClearTable();
                        dttable.fnDestroy();
                    }
                    shopmanager.init();

                },
                error : function(result){

                    console.log("fuail"+result)
                }
            })
        }





    }



    index.init = function () {

        $.get({
            url: config.baseUrl + "/v/0.1/shops/",
            type: 'GET',
            dataType: 'JSON',
            success: function (data) {

                console.log(data);
                dttable =   $('#shop').DataTable({
                    data: data.items,

                    "paging" : true,
                    "lengthChange" : true,
                    "searching" : false,
                    "ordering" : false,
                    "info" : true,
                    "autoWidth" : false,
                    "bStateSave" : true,
                    "bFilter" : true,
                    "iDisplayLength": 10,
                    "iDisplayStart": 0,

                    "oLanguage" : {
                        "sProcessing" : "正在加载中......",
                        "sLengthMenu" : "每页显示 _MENU_ 条记录",
                        "sZeroRecords" : "正在加载中......",
                        "sEmptyTable" : "表中无数据存在！",
                        "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                        "sInfoEmpty" : "显示0到0条记录",
                        "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                        "sSearch" : "搜索",
                        "oPaginate" : {
                            "sFirst" : "首页",
                            "sPrevious" : "上一页",
                            "sNext" : "下一页",
                            "sLast" : "末页"
                        }
                    },

                    columns: [
                        {data: 'imgUrl'},
                        {data: 'name'},
                        {data: 'createTime'},
                        {data: 'id'}
                    ]
                    , "columnDefs": [


                        {
                            "render": function (data, type,
                                                row) {
                                //data为当前列的数据
                                //type为当前列数据类型
                                //row为当前行数据
                                var str = "<img src='"+data+"' class='img-thumbnail' width='100' height='100'/>";
                                return str;
                                //此处return可自己定义，博主此处举例为超链接，传参须注意，若传字符串需加上转义字符，否则会报错ReferenceError: XXX is not defined at HTMLAnchorElement.onclick
                            },
                            //此处target负数表示从右向左的顺序
                            //-1为右侧第一列
                            "targets": 0
                        },
                        {
                            "render": function (data, type,
                                                row) {

                               var date =  new Date(data).toLocaleDateString();

                                return date;
                                //此处return可自己定义，博主此处举例为超链接，传参须注意，若传字符串需加上转义字符，否则会报错ReferenceError: XXX is not defined at HTMLAnchorElement.onclick
                            },
                            //此处target负数表示从右向左的顺序
                            //-1为右侧第一列
                            "targets": 2
                        },

                        {
                            "render": function (data, type,
                                                row) {
                                var str = "<a href='./shopdetail.html?id="+data+"' class='btn btn-info'>明细</a><a class='btn btn-danger' onclick='shopmanager.del("+data+")'>删除</a>";
                                return str;
                                //此处return可自己定义，博主此处举例为超链接，传参须注意，若传字符串需加上转义字符，否则会报错ReferenceError: XXX is not defined at HTMLAnchorElement.onclick
                            },
                            //此处target负数表示从右向左的顺序
                            //-1为右侧第一列
                            "targets": -1
                        }
                    ]


                })

            }
        });

    };

    return index;

})(jQuery, config);

$(document).ready(function () {
    shopmanager.init();
});
