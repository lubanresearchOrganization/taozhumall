package com.lubanresearch.lubanmall.cart.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hilbertcao on 2018/2/12.
 */
@FeignClient(name = "merchantservice")
public interface MerchantService {

    @RequestMapping(value = "/v/0.1/products/{id}", method = RequestMethod.GET)
    ProductDTO getProduct(@PathVariable("id") Long id);


    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.GET)
    ShopDTO getShop(@PathVariable("id") Long id);

}
