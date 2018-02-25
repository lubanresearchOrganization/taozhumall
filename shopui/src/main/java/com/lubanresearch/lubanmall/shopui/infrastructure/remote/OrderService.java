package com.lubanresearch.lubanmall.shopui.infrastructure.remote;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "orderService")
public interface OrderService {

    @RequestMapping("/v/0.1/orders/")
    @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam("shopId") Long shopId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("status") Long status
    ) throws ServiceException;

    @RequestMapping("/v/0.1/orders/{orderId}")
    @ResponseBody
    OrderDTO getOrder(
            @PathVariable("orderId") Long orderId
    ) throws ServiceException;

    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    void confirmReceive() throws ServiceException;


    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    void changeTotal(@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) throws ServiceException;
}
