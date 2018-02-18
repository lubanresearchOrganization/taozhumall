package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ShopGroupedProductDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/18.
 */
@FeignClient(name = "merchantservice")
public interface ShopGroupedProductService {

    @RequestMapping(value = "/v/0.1/shopGroupedProducts/", method = RequestMethod.GET)
    @ResponseBody
    List<ShopGroupedProductDTO> findShopGroupProducts(
            @RequestParam(value = "productIds") List<Long> productIds
    );

}
