package com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db;

import com.lubanresearch.lubanmall.orderservice.domain.bean.Order;
import com.lubanresearch.lubanmall.orderservice.domain.query.condition.OrderQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderQueryCondition example);

    int deleteByExample(OrderQueryCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderQueryCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<Order> selectByExampleSelective(@Param("example") OrderQueryCondition example, @Param("selective") Order.Column ... selective);

    Order selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Order selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Order.Column ... selective);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderQueryCondition example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderQueryCondition example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertSelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExample(@Param("record") Order record, @Param("example") OrderQueryCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExampleSelective(@Param("record") Order record, @Param("example") OrderQueryCondition example);
}