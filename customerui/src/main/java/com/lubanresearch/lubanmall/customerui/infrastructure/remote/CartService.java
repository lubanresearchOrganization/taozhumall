package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanresearch.lubanmall.cartapi.*;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "cart",url = "http://cart.taozhumall.com")
public interface CartService {


    @RequestMapping("/v/0.1/carts/{customerId}/")
    @ResponseBody
    List<CartItemDTO> getCustomerCart(@PathVariable("customerId") Long customerId,
                            @RequestParam(value = "productIds",required = false) List<Long> productIds)throws ServiceException;


    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/addCartItem",method = RequestMethod.POST)
    boolean addCartItem(@PathVariable("customerId") Long customerId,@RequestBody AddCartItemDTO addCartItemDTO)throws ServiceException;

    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/removeCartItem",method = RequestMethod.DELETE)
    boolean removeCartItem(@PathVariable("customerId") Long customerId,
                        @RequestParam("productId") Long productId)throws ServiceException;

    /**
     * 结算
     * 将相关的购物车项状态改成待确认状态
     * @param settleDTO
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/settle",method = RequestMethod.POST)
    boolean settle(@PathVariable("customerId") Long customerId,@RequestBody SettleDTO settleDTO)throws ServiceException;

    /**
     * 修改购物车项的数量
     * @param changeNumDTO
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/changeNum",method = RequestMethod.POST)
    boolean changeNum(@PathVariable("customerId") Long customerId,@RequestBody ChangeNumDTO changeNumDTO)throws ServiceException;
    /**
     * 确认购物车，创建订单，将相关的购物车项状态设置为已确认
     */
    @RequestMapping(value="/v/0.1/carts/{customerId}/commands/confirm",method = RequestMethod.POST)
    boolean confirm(@PathVariable("customerId") Long customerId,@RequestBody ConfirmDTO confirmDTO)throws ServiceException;
}
