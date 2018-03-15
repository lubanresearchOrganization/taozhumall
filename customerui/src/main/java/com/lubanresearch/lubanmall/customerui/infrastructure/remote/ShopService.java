package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/3/6.
 */
@FeignClient(name = "merchantservice",url = "http://merchantservice.taozhumall.com")
public interface ShopService {

    @RequestMapping(value = "/v/0.1/shops/{id}", method = RequestMethod.GET)
    @ResponseBody
    ShopDTO getShop(@PathVariable("id") Long id);

}
