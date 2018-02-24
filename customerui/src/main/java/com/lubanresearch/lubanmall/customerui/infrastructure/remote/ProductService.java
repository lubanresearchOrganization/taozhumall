package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "merchantservice")
public interface ProductService {

    @RequestMapping(value = "/v/0.1/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    ProductDTO getProduct(@PathVariable("id") Long id)throws ServiceException;

    @RequestMapping(value = "/v/0.1/search/products/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<ProductDTO> findProducts(
            @RequestParam("categoryId")Long categoryId,
            @RequestParam("shopId")Long shopId,
            @RequestParam(value = "recursive",defaultValue = "false")boolean recursive,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    )throws ServiceException;
}
