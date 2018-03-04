package com.lubanresearch.lubanmall.orderservice.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hilbertcao on 2018/1/4.
 */
@FeignClient(name = "merchantservice")
public interface MerchantService {

    @RequestMapping(value = "/v/0.1/products/{id}", method = RequestMethod.GET)
    ProductDTO getProduct(@PathVariable("id") Long id);

    @RequestMapping(value = "/v/0.1/shops/", method = RequestMethod.GET)
    Pagination<ShopDTO> getShops(@RequestParam(value = "ids") List<Long> ids);

    @RequestMapping(value = "/v/0.1/products/", method = RequestMethod.GET)
    Pagination<ProductDTO> getProducts(@RequestParam(value = "ids") List<Long> ids);

    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.GET)
    ShopDTO getShop(@PathVariable("id") Long id);
}
