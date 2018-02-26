package com.lubanresearch.lubanmall.orderservice.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hilbertcao on 2018/1/4.
 */
@FeignClient(name = "merchantservice",url = "http://merchantservice.taozhumall.com")
public interface MerchantService {

    @RequestMapping(value = "/v/0.1/products/{id}", method = RequestMethod.GET)
    ProductDTO getProduct(@PathVariable("id") Long id);

}
