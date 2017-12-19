package com.lubanresearch.lubanmall.catagoryservice.infrastructure.persistence.db.mapper;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.query.condition.CatagoryQueryCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatagoryMapper {
    long countByExample(CatagoryQueryCondition example);

    int deleteByExample(CatagoryQueryCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CatagoryQueryCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<Category> selectByExampleSelective(@Param("example") CatagoryQueryCondition example, @Param("selective") Category.Column ... selective);

    Category selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Category selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Category.Column ... selective);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CatagoryQueryCondition example);

    int updateByExample(@Param("record") Category record, @Param("example") CatagoryQueryCondition example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertSelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExample(@Param("record") Category record, @Param("example") CatagoryQueryCondition example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_category
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertByExampleSelective(@Param("record") Category record, @Param("example") CatagoryQueryCondition example);
}