package com.lubanresearch.lubanmall.merchantservice.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Product {
    private Long id;

    private String name;

    private String imgUrl;

    private BigDecimal unitPrice;

    private Long shopId;

    private Long categoryId;

    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lb_product
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Product.Builder builder() {
        return new Product.Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public Product selective(Column ... columns) {
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
     * This class corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private Product obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new Product();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.id
         *
         * @param id the value for lb_product.id
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
         * This method sets the value of the database column lb_product.name
         *
         * @param name the value for lb_product.name
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.img_url
         *
         * @param imgUrl the value for lb_product.img_url
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder imgUrl(String imgUrl) {
            obj.setImgUrl(imgUrl);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.unit_price
         *
         * @param unitPrice the value for lb_product.unit_price
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder unitPrice(BigDecimal unitPrice) {
            obj.setUnitPrice(unitPrice);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.shop_id
         *
         * @param shopId the value for lb_product.shop_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder shopId(Long shopId) {
            obj.setShopId(shopId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.category_id
         *
         * @param categoryId the value for lb_product.category_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder categoryId(Long categoryId) {
            obj.setCategoryId(categoryId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column lb_product.create_time
         *
         * @param createTime the value for lb_product.create_time
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
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Product build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table lb_product
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        name("name"),
        imgUrl("img_url"),
        unitPrice("unit_price"),
        shopId("shop_id"),
        categoryId("category_id"),
        createTime("create_time");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lb_product
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}