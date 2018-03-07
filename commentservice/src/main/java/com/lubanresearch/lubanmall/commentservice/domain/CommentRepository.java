package com.lubanresearch.lubanmall.commentservice.domain;

import com.lubanresearch.lubanmall.commentservice.infrastructure.persistence.db.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Repository
public class CommentRepository {

    @Autowired
    private CommentMapper commentMapper;




    public void addComment(Comment comment) {

        commentMapper.insertSelective(comment);

    }
    public Comment get(Long id){

        return commentMapper.selectByPrimaryKey(id);
    }

    public void remove(Long id){

        commentMapper.deleteByPrimaryKey(id);
    }
}
