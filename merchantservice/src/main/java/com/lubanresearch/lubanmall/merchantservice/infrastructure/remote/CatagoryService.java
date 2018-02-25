package com.lubanresearch.lubanmall.merchantservice.infrastructure.remote;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hilbertcao on 2018/1/30.
 */
@FeignClient(name = "catagoryservice",url = "http://catagoryservice.taozhumall.com")
public interface CatagoryService {

    @RequestMapping(value = "/v/0.1/categorys/", method = RequestMethod.GET)
    @ResponseBody
    List<CategoryDTO> getCategorys(@RequestParam(value = "parentId", required = false) Long parentId,
                                   @RequestParam(value = "recursive", defaultValue = "false") boolean recursive) throws ServiceException;
}
