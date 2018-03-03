package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.orderserviceapi.bean.ChangeOrderTotalDTO;
import com.lubanmall.orderserviceapi.bean.CreateDealDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "orderservice")
public interface OrderService {


    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/confirmReceive", method = RequestMethod.POST)
    @ResponseBody void confirmReceive() ;
    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/pay", method = RequestMethod.POST)
    @ResponseBody void pay() ;
    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/cancel", method = RequestMethod.POST)
    @ResponseBody
    void cancel() ;

    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/deliver", method = RequestMethod.POST)
    @ResponseBody
    void deliver() ;

    @RequestMapping(value = "/v/0.1/orders/{orderId}/commands/changeTotal", method = RequestMethod.POST)
    @ResponseBody
    void changeTotal(@RequestBody ChangeOrderTotalDTO changeOrderTotalDTO) ;


    @RequestMapping("/v/0.1/orders/")
    @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam("id")Long id,
            @RequestParam("shopId") Long shopId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("status") Long status,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size);

    @RequestMapping("/orders/{orderId}")
    @ResponseBody OrderDTO getOrder(
            @RequestParam("orderId") Long orderId
    );

    @RequestMapping(value = "/v/0.1/deals/", method = RequestMethod.POST)
    @ResponseBody
    boolean createDeal(@RequestBody CreateDealDTO createDealDTO) ;
}
