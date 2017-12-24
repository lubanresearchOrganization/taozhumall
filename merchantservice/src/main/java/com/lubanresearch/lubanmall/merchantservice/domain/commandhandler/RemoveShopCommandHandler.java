package com.lubanresearch.lubanmall.merchantservice.domain.commandhandler;

import com.lubanresearch.lubanmall.merchantservice.domain.ShopRepository;
import com.lubanresearch.lubanmall.merchantservice.domain.command.RemoveShopCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class RemoveShopCommandHandler {

    @Autowired
    private ShopRepository shopRepository;

    public void handler(RemoveShopCommand command){

        shopRepository.remove(command.getId());
    }
}
