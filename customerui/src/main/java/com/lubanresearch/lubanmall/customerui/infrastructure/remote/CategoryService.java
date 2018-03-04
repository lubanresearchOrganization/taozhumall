package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hilbertcao on 2018/1/22.
 */
@FeignClient(name = "catagoryservice")
public interface CategoryService {


    @RequestMapping(value = "/v/0.1/categorys/", method = RequestMethod.GET)
    @ResponseBody
    List<CategoryDTO> getCategorys(@RequestParam(value = "parentId", required = false) Long parentId,
                                   @RequestParam(value = "recursive", defaultValue = "false") boolean recursive) throws ServiceException;
}