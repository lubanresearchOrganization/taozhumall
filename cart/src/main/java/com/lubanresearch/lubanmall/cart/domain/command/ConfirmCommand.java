package com.lubanresearch.lubanmall.cart.domain.command;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by hilbertcao on 2018/2/8.
 */
public class ConfirmCommand {

    public ConfirmCommand(Long customerId, List<Long> productIds, Map<Long, String> remarkMap) {
        this.customerId = customerId;
        this.productIds = productIds;
        this.remarkMap = remarkMap;
    }

    @NotNull
    private Long customerId;

    @NotNull
    private List<Long> productIds;

    /**
     * 拆单时候给每个单的一个备注
     */
    @NotNull
    private Map<Long,String> remarkMap;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Map<Long, String> getRemarkMap() {
        return remarkMap;
    }

    public void setRemarkMap(Map<Long, String> remarkMap) {
        this.remarkMap = remarkMap;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
