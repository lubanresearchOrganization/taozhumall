package com.lubanresearch.lubanmall.customerui.infrastructure.remote;

import com.lubanmall.commentserviceapi.CommentDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@FeignClient(name = "commentservice")
public interface CommentService {


    @RequestMapping(value = "/v/0.1/comments/",method = RequestMethod.POST)
    @ResponseBody
    CommentDTO addComment(@RequestBody CommentDTO dto)throws ServiceException;


    @RequestMapping(value = "/v/0.1/comments/", method = RequestMethod.GET)
    @ResponseBody
    Pagination<CommentDTO> findComments(
            @RequestParam("productId") Long productId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    )throws ServiceException;
}
