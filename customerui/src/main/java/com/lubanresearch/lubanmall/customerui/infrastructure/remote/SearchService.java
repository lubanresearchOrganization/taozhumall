package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/1/30.
 */
@FeignClient(name = "searchservice")
public interface SearchService {

    @RequestMapping(value = "/v/0.1/search/products/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<ProductDTO> findProducts(
            @RequestParam("key") String  key,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    )throws ServiceException;

    @RequestMapping(value = "/v/0.1/search/shops/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<ShopDTO> findShops(
            @RequestParam("key") String  key,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    )throws ServiceException;
}
