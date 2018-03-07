package com.lubanresearch.lubanmall.commentservice.application.controller;

import com.lubanresearch.lubanmall.commentservice.domain.Comment;
import com.lubanresearch.lubanmall.commentservice.infrastructure.persistence.db.mapper.CommentMapper;
import com.lubanresearch.lubanmall.commentservice.infrastructure.persistence.db.query.condition.CommentQueryCondition;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hilbertcao on 2017/12/25.
 */

@Controller
@RequestMapping("/v/0.1/comments")
public class QueryContorller {

    @Autowired
    private CommentMapper commentMapper;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<Comment> findComments(
            @RequestParam("productId") Long productId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        CommentQueryCondition condition = new CommentQueryCondition();
        condition.createCriteria().andProductIdEqualTo(productId);
        condition.limit(page*size,size);
        condition.orderBy("create_time desc");

        Pagination<Comment> pagination = new Pagination<>();
        pagination.setItems(commentMapper.selectByExample(condition));
        pagination.setTotal((int) commentMapper.countByExample(condition));
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);
        return pagination;
    }
}
