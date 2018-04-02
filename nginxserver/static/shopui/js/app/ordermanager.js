var ordermanager = (function ($, urlutil, lajaxComponent, objectutil) {


    var index = {};
    var currentorderid;


    index.bind = function () {

        $(document).on('click', '#searchOrdersBtn', function () {

            $("#rows").html("");
            $('#pageBar').html("");
            urlparams = {};
            page = 0;
            var condition = {
                "page": page,
                "size": size
            };
            if ($("#statusInput").val()) {

                urlparams.status = $("#statusInput").val();
            }
            if ($("#orderNOInput").val()) {

                urlparams.id = $("#orderNOInput").val();
            }
            objectutil.simplecopy(urlparams, condition);


            lajaxComponent.getTextReturnJson(config.baseUrl + "/v/0.1/orders/", condition, function (result) {


                if (result.pageCount > 0) {


                    $("#rows").html($("#resultTemplate").tmpl(result.items));
                    var options = {
                        currentPage: result.pageIndex + 1,
                        totalPages: result.pageCount,
                        bootstrapMajorVersion: 3,
                        itemContainerClass: function (type, page, current) {
                                return (page === current) ? "page-item active" : "page-item";
                            },
                            itemContentClass: "page-link",
                        pageUrl: function (type, page, current) {

                            var params = {
                                "page": page,
                                "size": size,
                                "params": JSON.stringify(urlparams)
                            };
                            return "./ordermanager.html?" + urlutil.concatParam(params);

                        }
                    }

                    $('#pageBar').bootstrapPaginator(options);
                }
            });
        });

        $(document).on('click', '.deliverBtn', function () {
            var orderid = $(this).attr("orderid");
            console.info(orderid);
            lajaxComponent.postNoParamReturnJson(config.baseUrl + "/v/0.1/orders/" + orderid + "/commands/deliver", function (result) {
                page = 0;
                alert("发货成功!");
                $("#searchOrdersBtn").click();

            });
        });

        $(document).on('click', '.changeTotalBtn', function () {

            currentorderid = $(this).attr("orderid");
            $('#modifyTotalPanel').modal('show');
        });

        $(document).on('click', '#changeTotalSubmitBtn', function () {
            console.info(currentorderid);
            var total = $("#newTotal").val();
            if (!total) {
                alert("请输入新的价格");
                $("#newTotal").focus();
                return;
            }
            var orderid = currentorderid;
            var data = {
                "orderId": orderid,
                "total": total
            };

            lajaxComponent.postJsonReturnJson(config.baseUrl + "/v/0.1/orders/" + orderid + "/commands/changeTotal",
                data,
                function (result) {
                console.info(result);
                    page = 0;
                    alert("修改价格成功!");
                    $("#searchOrdersBtn").click();
                    $('#modifyTotalPanel').modal('hide');
                });

        });
    };

    index.init = function () {


        page = urlutil.getParameter("page");
        size = urlutil.getParameter("size");
        urlparams = JSON.parse(urlutil.getParameter("params")) || {};
        if (!page) {
            page = 0;
        } else {
            page = page - 1;
        }
        if (!size) {
            size = 6;
        }
        var condition = {
            "page": page,
            "size": size
        };
        if (urlparams.status) {

            $("#statusInput").val(urlparams.status);
        }
        if (urlparams.id) {

            $("#orderNOInput").val(urlparams.id);
        }

        objectutil.simplecopy(urlparams, condition);


        //初始化订单
        lajaxComponent.getTextReturnJson(config.baseUrl + "/v/0.1/orders/", condition, function (result) {

            console.info(result);
            $("#rows").html($("#resultTemplate").tmpl(result.items));
            if (result.pageCount > 0) {
                var options = {
                    currentPage: result.pageIndex + 1,
                    totalPages: result.pageCount,
                    bootstrapMajorVersion: 3,
                    itemContainerClass: function (type, page, current) {
                            return (page === current) ? "page-item active" : "page-item";
                        },
                        itemContentClass: "page-link",
                    pageUrl: function (type, page, current) {

                        var params = {
                            "page": page,
                            "size": size,
                            "params": JSON.stringify(urlparams)
                        };
                        return "./ordermanager.html?" + urlutil.concatParam(params);

                    }
                }

                $('#pageBar').bootstrapPaginator(options);
            }
        });


    };

    return index;

})(jQuery, urlutil, lbajax, objectutil);

$(document).ready(function () {
    ordermanager.bind();
    ordermanager.init();
});