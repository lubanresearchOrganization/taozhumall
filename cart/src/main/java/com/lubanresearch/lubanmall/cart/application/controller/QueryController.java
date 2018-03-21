package com.lubanresearch.lubanmall.cart.application.controller;

import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.entity.CartItemEntity;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.mapper.CartItemEntityMapper;
import com.lubanresearch.lubanmall.cart.infrastructure.persistence.db.query.condition.CartItemEntityQueryCondition;
import com.lubanresearch.lubanmall.cart.infrastructure.remote.MerchantService;
import com.lubanresearch.lubanmall.cartapi.CartItemDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1/carts/{customerId}")
public class QueryController {

    @Autowired
    private CartItemEntityMapper cartItemEntityMapper;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping("/")
    public @ResponseBody List<CartItemEntity> getCustomerCart(@PathVariable("customerId") Long customerId,
                                                 @RequestParam(value = "productIds",required = false) List<Long> productIds
    ){

        CartItemEntityQueryCondition queryCondition = new CartItemEntityQueryCondition();
        queryCondition.createCriteria().andCustomerIdEqualTo(customerId).andIf(CollectionUtils.isNotEmpty(productIds),
                new CartItemEntityQueryCondition.Criteria.ICriteriaAdd() {
                    @Override
                    public CartItemEntityQueryCondition.Criteria add(CartItemEntityQueryCondition.Criteria add) {
                        return add.andProductIdIn(productIds);
                    }
                });
        return cartItemEntityMapper.selectByExample(queryCondition);
    }
    /*@RequestMapping("/")
    public @ResponseBody CartDTO getCustomerCart(@PathVariable("customerId") Long customerId,
                                                 @RequestParam(value = "productIds",required = false) List<Long> productIds
                                                 ){

        CartItemEntityQueryCondition queryCondition = new CartItemEntityQueryCondition();
        queryCondition.createCriteria().andCustomerIdEqualTo(customerId).andIf(CollectionUtils.isNotEmpty(productIds),
                new CartItemEntityQueryCondition.Criteria.ICriteriaAdd() {
                    @Override
                    public CartItemEntityQueryCondition.Criteria add(CartItemEntityQueryCondition.Criteria add) {
                        return add.andProductIdIn(productIds);
                    }
                });
        List<CartItemEntity> cartItems = cartItemEntityMapper.selectByExample(queryCondition);
        Map<Long,CartItemEntity> cartItemEntityMap = cartItems.stream().collect(Collectors.toMap(
                CartItemEntity::getProductId, Function.identity(),(p1,p2)->p1
        ));

        List<ProductDTO> products = cartItems.stream().map(cartItemEntity -> {return merchantService.getProduct(cartItemEntity.getProductId());})
                .collect(Collectors.toList());

        Map<Long,List<ProductDTO>> shopProducts =  products.stream().collect(Collectors.groupingBy(ProductDTO::getShopId));

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerId(customerId);
        cartDTO.setGroups(shopProducts.keySet().stream().map(shopId->{
            GroupDTO groupDTO = new GroupDTO();
            ShopDTO shopDTO =  merchantService.getShop(shopId);
            groupDTO.setId(shopId);
            groupDTO.setName(shopDTO.getName());
            groupDTO.setCartItems(shopProducts.get(shopId).stream().map(productDTO -> {
                CartItemDTO cartItem = new CartItemDTO();
                CartItemEntity cartItemEntity =  cartItemEntityMap.get(productDTO.getId());
                cartItem.setId(cartItemEntity.getId());
                cartItem.setCustomerId(cartItemEntity.getCustomerId());
                cartItem.setProductNum(cartItemEntity.getProductNum());
                cartItem.setCreateTime(cartItemEntity.getCreateTime());
                cartItem.setProductPrice(cartItemEntity.getProductPrice());
                return cartItem;
            }).collect(Collectors.toList()));
            groupDTO.setAmount(groupDTO.getCartItems().stream().map(cartItemDTO -> {
                return cartItemDTO.getProductPrice().multiply(BigDecimal.valueOf(cartItemDTO.getProductNum()));
            }).reduce((sum,itemTotal)->{return sum.add(itemTotal);}).get());
            return groupDTO;
        }).collect(Collectors.toList()));

        cartDTO.setAmount(cartDTO.getGroups().stream().map(GroupDTO::getAmount).reduce((sum,itemTotal)->{return sum.add(itemTotal);}).get());
        return cartDTO;
    }*/


}
