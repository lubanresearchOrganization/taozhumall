package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "merchantservice")
public interface ProductService {

    @RequestMapping(value = "/v/0.1/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    ProductDTO getProduct(@PathVariable("id") Long id)throws ServiceException;

}
