package com.lubanresearch.lubanmall.shopui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/1/4.
 */
@FeignClient(name = "merchantService")
public interface MerchantService {

    @RequestMapping(value = "/v/0.1/products/",method = RequestMethod.POST)
    @ResponseBody
    ProductDTO addProduct(@RequestBody ProductDTO dto) throws ServiceException;

    @RequestMapping(value = "/v/0.1/products/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    void removeProduct(@PathVariable("id") Long id) throws ServiceException;

    @RequestMapping(value = "/v/0.1/products/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    ProductDTO updateProduct(@PathVariable("id") Long id,@RequestBody ProductDTO dto)throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.GET)
    @ResponseBody
    ShopDTO get(@PathVariable("id") String id) throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/",method = RequestMethod.POST)
    @ResponseBody
    ShopDTO addShop(@RequestBody ShopDTO dto) throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    ShopDTO updateShop(@PathVariable("id") Long id,@RequestBody ShopDTO dto) throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    void removeShop(@PathVariable("id") Long id) throws ServiceException;

}
