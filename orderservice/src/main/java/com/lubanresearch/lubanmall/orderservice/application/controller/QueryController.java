package com.lubanresearch.lubanmall.orderservice.application.controller;

import com.lubanmall.merchantserviceapi.bean.ProductDTO;
import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanmall.orderserviceapi.bean.OrderDTO;
import com.lubanmall.orderserviceapi.bean.OrderItemDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.orderservice.domain.bean.Order;
import com.lubanresearch.lubanmall.orderservice.domain.bean.OrderItem;
import com.lubanresearch.lubanmall.orderservice.domain.query.condition.OrderItemQueryCondition;
import com.lubanresearch.lubanmall.orderservice.domain.query.condition.OrderQueryCondition;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderItemMapper;
import com.lubanresearch.lubanmall.orderservice.infrastructure.persistence.db.OrderMapper;
import com.lubanresearch.lubanmall.orderservice.infrastructure.remote.MerchantService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hilbertcao on 2018/2/4.
 */
@Controller
@RequestMapping("/v/0.1")
public class QueryController {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private MerchantService merchantService;


    @RequestMapping("/orders/{id}")
    public
    @ResponseBody
    OrderDTO getOrder(
            @PathVariable(value = "id", required = false) Long id
    ) {

        Order order = orderMapper.selectByPrimaryKey(id);
        if (order == null) {

            return null;
        }

        OrderItemQueryCondition.Criteria criteria = new OrderItemQueryCondition().createCriteria();

        criteria.andOrderIdEqualTo(id);

        List<OrderItem> allItems = orderItemMapper.selectByExample(
                criteria.example());

        Map<Long, List<OrderItem>> orderOrderItemsMap = allItems.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));

        ShopDTO shop = merchantService.getShop(order.getShopId());
        Map<Long, ProductDTO> productMap = merchantService.getProducts(allItems.stream().map(OrderItem::getProductId).collect(Collectors.toList()
        )).getItems().stream().collect(Collectors.toMap(ProductDTO::getId, Function.identity(),(p1,p2)->p1));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setShopId(order.getShopId());
        orderDTO.setRemark(order.getRemark());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setCreateTime(order.getCreateTime());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setShopImgUrl(shop.getImgUrl());
        orderDTO.setShopName(shop.getName());

        orderDTO.setOrderItemList(
                Optional.ofNullable(orderOrderItemsMap.get(order.getId())).orElse(new ArrayList<>()).stream()
                        .map(orderItem -> {
                            OrderItemDTO orderItemDTO = new OrderItemDTO();
                            orderItemDTO.setProductNum(orderItem.getProductNum());
                            orderItemDTO.setProductId(orderItem.getProductId());
                            orderItemDTO.setUnitPrice(orderItem.getUnitPrice());

                            ProductDTO product = Optional.ofNullable(productMap.get(orderItem.getProductId())).orElse(new ProductDTO());
                            orderItemDTO.setProductImageUrl(product.getImgUrl());
                            orderItemDTO.setProductName(product.getName());
                            return orderItemDTO;
                        }).collect(Collectors.toList())
        );

        return orderDTO;

    }

    @RequestMapping("/orders/")
    public
    @ResponseBody
    Pagination<OrderDTO> getOrders(
            @RequestParam(value = "dealId", required = false) Long dealId,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "shopId", required = false) Long shopId,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ) {


        OrderQueryCondition.Criteria orderCriteria = new OrderQueryCondition().createCriteria();
        orderCriteria.andIf(shopId != null, new OrderQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public OrderQueryCondition.Criteria add(OrderQueryCondition.Criteria add) {
                return add.andShopIdEqualTo(shopId);
            }
        }).andIf(dealId != null, new OrderQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public OrderQueryCondition.Criteria add(OrderQueryCondition.Criteria add) {
                return add.andDealIdEqualTo(dealId);
            }
        }).andIf(id != null, new OrderQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public OrderQueryCondition.Criteria add(OrderQueryCondition.Criteria add) {
                return add.andIdEqualTo(id);
            }
        }).andIf(customerId != null, new OrderQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public OrderQueryCondition.Criteria add(OrderQueryCondition.Criteria add) {
                return add.andCustomerIdEqualTo(customerId);
            }
        }).andIf(status != null, new OrderQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public OrderQueryCondition.Criteria add(OrderQueryCondition.Criteria add) {
                return add.andStatusEqualTo(status);
            }
        })
        ;

        List<Order> orderList = orderMapper.selectByExample(
                orderCriteria.example().page(page, size).orderBy("create_time desc"));
        if (CollectionUtils.isEmpty(orderList)) {

            Pagination<OrderDTO> pagination = new Pagination<>();
            pagination.setItems(new ArrayList<>());
            pagination.setTotal(0);
            pagination.setPageCount(0);
            pagination.setPageIndex(0);
            return pagination;
        }

        List<OrderItem> allItems = getOrderItems(orderList.stream().map(Order::getId).collect(Collectors.toList()));

        Map<Long, List<OrderItem>> orderOrderItemsMap = allItems.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));

        Map<Long, ShopDTO> shopMap = getShopMap(orderList.stream().map(Order::getShopId).collect(Collectors.toList()));
        Map<Long, ProductDTO> productMap = getProductMap(orderList.stream().map(Order::getShopId).collect(Collectors.toList()));
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> {

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setShopId(order.getShopId());
            orderDTO.setRemark(order.getRemark());
            orderDTO.setCustomerId(order.getCustomerId());
            orderDTO.setTotalAmount(order.getTotalAmount());
            orderDTO.setCreateTime(order.getCreateTime());
            orderDTO.setStatus(order.getStatus());
            ShopDTO shop = Optional.ofNullable(shopMap.get(orderDTO.getShopId())).orElse(new ShopDTO());
            orderDTO.setShopImgUrl(shop.getImgUrl());
            orderDTO.setShopName(shop.getName());

            orderDTO.setOrderItemList(
                    Optional.ofNullable(orderOrderItemsMap.get(order.getId())).orElse(new ArrayList<>()).stream()
                            .map(orderItem -> {
                                OrderItemDTO orderItemDTO = new OrderItemDTO();
                                orderItemDTO.setProductNum(orderItem.getProductNum());
                                orderItemDTO.setProductId(orderItem.getProductId());
                                orderItemDTO.setUnitPrice(orderItem.getUnitPrice());

                                ProductDTO product = Optional.ofNullable(productMap.get(orderItem.getProductId())).orElse(new ProductDTO());
                                orderItemDTO.setProductImageUrl(product.getImgUrl());
                                orderItemDTO.setProductName(product.getName());
                                return orderItemDTO;
                            }).collect(Collectors.toList())
            );

            return orderDTO;

        }).collect(Collectors.toList());


        Long total = orderMapper.countByExample(orderCriteria.example());

        Pagination<OrderDTO> pagination = new Pagination<>();
        pagination.setItems(orderDTOList);
        pagination.setTotal(total.intValue());
        pagination.setPageCount((pagination.getTotal() % size == 0) ? (pagination.getTotal() / size) : (pagination.getTotal() / size + 1));
        pagination.setPageIndex(page);

        return pagination;


    }

    private Map<Long, ProductDTO> getProductMap(List<Long> ids) {

        return Optional.ofNullable(merchantService.getProducts(ids).getItems()).orElse(new ArrayList<>()).stream().collect(Collectors
                .toMap(ProductDTO::getId, Function.identity()));
    }

    private List<OrderItem> getOrderItems(List<Long> orderIds) {

        OrderItemQueryCondition.Criteria criteria = new OrderItemQueryCondition().createCriteria();

        criteria.andOrderIdIn(orderIds);

        return orderItemMapper.selectByExample(
                criteria.example());
    }

    private Map<Long, ShopDTO> getShopMap(List<Long> ids) {

        return Optional.ofNullable(merchantService.getShops(ids).getItems()).orElse(new ArrayList<>()).stream().collect(Collectors
                .toMap(ShopDTO::getId, Function.identity()));
    }
}
