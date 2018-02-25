package com.lubanresearch.lubanmall.platformui.remote;

import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "orderservice")
public interface OrderService {


    @RequestMapping("/v/0.1/orders/")
    @ResponseBody
    Pagination<OrderDTO> getOrders( @RequestParam(value = "dealId") Long dealId,
                                    @RequestParam(value = "shopId") Long shopId,
                                    @RequestParam(value = "customerId") Long customerId,
                                    @RequestParam(value = "status") Byte status,
                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size) throws ServiceException;
}
