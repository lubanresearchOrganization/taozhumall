var ordermanager = (function ($, lajaxComponent) {

    var dttable;

    var index = {};



    index.init = function () {


        lajaxComponent.getNoParamReturnJson(config.baseUrl + "/v/0.1/orders/?dealId=13301967441401&shopId=1&customerId=1&status=1", function (data) {

            console.log(data);
            dttable = $('#order').DataTable({
                data: data.items,

                "paging": true,
                "lengthChange": true,
                "searching": false,
                "ordering": false,
                "info": true,
                "autoWidth": false,
                "bStateSave": true,
                "bFilter": true,
                "iDisplayLength": 10,
                "iDisplayStart": 0,

                "oLanguage": {
                    "sProcessing": "正在加载中......",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "正在加载中......",
                    "sEmptyTable": "表中无数据存在！",
                    "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                    "sInfoEmpty": "显示0到0条记录",
                    "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                    "sSearch": "搜索",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "末页"
                    }
                },

                columns: [
                    {data: 'id'},
                    {data: 'totalAmount'},
                    {data: 'customerName'},
                    {data: 'createTime'},
                    {data: 'status'},
                    {data: 'id'}
                ]
                , "columnDefs": [


                    {
                        "render": function (data, type,
                                            row) {
                            var str = "<a href='./shopdetail.html?id=" + data + "' class='btn btn-info'>明细</a><a class='btn btn-danger' onclick='shopmanager.del(" + data + ")'>发货</a>";
                            return str;
                            //此处return可自己定义，博主此处举例为超链接，传参须注意，若传字符串需加上转义字符，否则会报错ReferenceError: XXX is not defined at HTMLAnchorElement.onclick
                        },
                        //此处target负数表示从右向左的顺序
                        //-1为右侧第一列
                        "targets": -1
                    }
                ]


            })

        });


    };

    return index;

})(jQuery, lbajax);

$(document).ready(function () {
    ordermanager.init();
});
