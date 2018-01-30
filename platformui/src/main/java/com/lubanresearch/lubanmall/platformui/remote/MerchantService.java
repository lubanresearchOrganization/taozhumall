package com.lubanresearch.lubanmall.platformui.remote;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "merchantservice")
public interface MerchantService {


    @RequestMapping(value = "/v/0.1/shops/", method = RequestMethod.POST)
    ShopDTO addShop(@RequestBody ShopDTO dto) throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.DELETE)
    void deleteShop(@PathVariable(value = "id") Long id) throws ServiceException;

    @RequestMapping(value = "/v/0.1/shops/", method = RequestMethod.GET)
    Pagination<ShopDTO> findShops(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size
    );



    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.GET)
    @ResponseBody
    ShopDTO getShop(@PathVariable("id") Long id) throws ServiceException;

}
