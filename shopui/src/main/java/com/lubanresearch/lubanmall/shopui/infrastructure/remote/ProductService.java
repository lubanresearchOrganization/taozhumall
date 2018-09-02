package com.lubanresearch.lubanmall.shopui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "productservice")
public interface ProductService {

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    ProductDTO addProduct(@RequestBody ProductDTO dto) throws ServiceException;

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    void removeProduct(@PathVariable("id") Long id) throws ServiceException;

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    ProductDTO updateProduct(@PathVariable("id") Long id,@RequestBody ProductDTO dto) throws ServiceException;

}
