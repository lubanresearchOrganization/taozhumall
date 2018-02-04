package com.lubanresearch.lubanmall.cartapi;

import java.util.List;

/**
 * Created by hilbertcao on 2018/2/4.
 */
public class ConfirmDTO {

    /**
     * 拆单时候给每个单的一个备注
     */
    private List<GroupExtraDTO> extras;

    public List<GroupExtraDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<GroupExtraDTO> extras) {
        this.extras = extras;
    }
}
