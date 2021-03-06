package com.lubanresearch.lubanmall.commentservice.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Comment {
    private Long id;

    private Long productId;

    private String content;

    private Byte score;

    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Comment.Builder builder() {
        return new Comment.Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public Comment selective(Column ... columns) {
        this.selectiveColumns.clear();
        if (columns != null) {
            for (Column column : columns) {
                this.selectiveColumns.put(column.value(), true);
            }
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private Comment obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new Comment();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_comment.id
         *
         * @param id the value for lb_comment.id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_comment.product_id
         *
         * @param productId the value for lb_comment.product_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder productId(Long productId) {
            obj.setProductId(productId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_comment.content
         *
         * @param content the value for lb_comment.content
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder content(String content) {
            obj.setContent(content);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_comment.score
         *
         * @param score the value for lb_comment.score
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder score(Byte score) {
            obj.setScore(score);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_comment.create_time
         *
         * @param createTime the value for lb_comment.create_time
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Comment build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table lb_comment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        productId("product_id"),
        content("content"),
        score("score"),
        createTime("create_time");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_comment
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}