package com.lubanresearch.lubanmall.shopui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hilbertcao on 2018/1/4.
 */
@FeignClient(name = "merchantService")
public interface MerchantService {


    @RequestMapping(value = "/v/0.1/shops/",method = RequestMethod.POST)
    ShopDTO addShop(@RequestBody ShopDTO dto)throws ServiceException;
}
