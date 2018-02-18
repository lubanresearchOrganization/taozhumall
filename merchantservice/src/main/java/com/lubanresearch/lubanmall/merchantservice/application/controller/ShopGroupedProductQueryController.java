package com.lubanresearch.lubanmall.merchantservice.application.controller;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanmall.merchantserviceapi.bean.ShopGroupedProductDTO;
import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.merchantservice.domain.Product;
import com.lubanresearch.lubanmall.merchantservice.domain.Shop;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ProductMapper;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper.ShopMapper;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.query.condition.ProductQueryCondition;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.query.condition.ShopQueryCondition;
import com.lubanresearch.lubanmall.merchantservice.infrastructure.remote.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2018/2/18.
 */

@RequestMapping("/v/0.1/shopGroupedProducts")
@Controller
public class ShopGroupedProductQueryController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<ShopGroupedProductDTO> findShopGroupProducts(
            @RequestParam(value = "productIds") List<Long> productIds
    ){
        List<Product> products = getProducts(productIds);
        Map<Long,List<ProductDTO>> shopProductsMap = products.stream().map(
                product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setName(product.getName());
                    productDTO.setId(product.getId());
                    productDTO.setImgUrl(product.getImgUrl());
                    productDTO.setCreateTime(product.getCreateTime());
                    productDTO.setCategoryId(product.getCategoryId());
                    productDTO.setShopId(product.getShopId());
                    productDTO.setUnitPrice(product.getUnitPrice());
                    return  productDTO;
                }
        ).collect(Collectors.groupingBy(ProductDTO::getShopId));

        List<Shop> shops = getShops(products.stream().map(Product::getShopId).distinct().collect(Collectors.toList()));
        Map<Long,UserDTO> accountMap = userService.getUsers(shops.stream().map(Shop::getAccountId).distinct().collect(Collectors.toList())).stream().collect(
                Collectors.toMap(UserDTO::getId,Function.identity())
        );
        Map<Long,ShopDTO> shopDTOMap = shops.stream()
                .map(shop -> {

                    ShopDTO shopDTO = new ShopDTO();
                    shopDTO.setAccountId(shop.getAccountId());
                    shopDTO.setName(shop.getName());
                    shopDTO.setId(shop.getId());
                    shopDTO.setCreateTime(shop.getCreateTime());
                    shopDTO.setDiscription(shop.getDiscription());
                    shopDTO.setImgUrl(shop.getImgUrl());
                    UserDTO userDTO = accountMap.get(shop.getAccountId());
                    if(userDTO!=null){
                        shopDTO.setMobile(userDTO.getMobile());
                        shopDTO.setAccountName(userDTO.getName());
                    }
                    return shopDTO;
                }).collect(Collectors.toMap(ShopDTO::getId, Function.identity()));
        return shopProductsMap.keySet().stream().map(shopId->{
            ShopGroupedProductDTO shopGroupedProductDTO = new ShopGroupedProductDTO();
            shopGroupedProductDTO.setShop(shopDTOMap.get(shopId));
            shopGroupedProductDTO.setProducts(shopProductsMap.get(shopId));
            return shopGroupedProductDTO;
        }).collect(Collectors.toList());
    }

    private List<Product> getProducts(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){

            return new ArrayList<>();
        }
        ProductQueryCondition condition = new ProductQueryCondition();
        condition.createCriteria().andIdIn(ids);
        return productMapper.selectByExample(condition);
    }

    private List<Shop> getShops(List<Long> ids) {

        if(CollectionUtils.isEmpty(ids)){

            return new ArrayList<>();
        }
        ShopQueryCondition condition = new ShopQueryCondition();
        condition.createCriteria().andIdIn(ids);
        return shopMapper.selectByExample(condition);
    }

}
