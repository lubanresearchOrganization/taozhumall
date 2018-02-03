package com.lubanresearch.lubanmall.platformui.remote;

import com.lubanmall.catagoryserviceapi.bean.CategoryDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "catagoryservice")
public interface CategoryService {

    @RequestMapping(value = "/v/0.1/categorys/", method = RequestMethod.GET)
    @ResponseBody
    List<CategoryDTO> getCategorys(@RequestParam(value = "parentId", required = false) Long parentId);


    @RequestMapping(value = "/v/0.1/categorys/", method = RequestMethod.POST)
    @ResponseBody
    CategoryDTO addCategory(@RequestBody CategoryDTO category);

}
