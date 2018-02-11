package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanresearch.lubanmall.cartapi.*;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "cart")
public interface CartService {


    @RequestMapping("/v/0.1/carts/{customerId}/")
    @ResponseBody
    CartDTO getCustomerCart(@PathVariable("customerId") Long customerId,
                            @RequestParam(value = "productIds",required = false) List<Long> productIds)throws ServiceException;


    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/addCartItem",method = RequestMethod.POST)
    void addCartItem(@RequestBody AddCartItemDTO addCartItemDTO)throws ServiceException;

    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/removeCartItem",method = RequestMethod.POST)
    void removeCartItem(@RequestBody CartItemDTO cartItemDTO)throws ServiceException;

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/settle",method = RequestMethod.POST)
    void settle(@RequestBody SettleDTO settleDTO)throws ServiceException;

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/changeNum",method = RequestMethod.POST)
    void changeNum(@RequestBody ChangeNumDTO changeNumDTO)throws ServiceException;
    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/confirm",method = RequestMethod.POST)
    void confirm(@RequestBody ConfirmDTO confirmDTO)throws ServiceException;
}
