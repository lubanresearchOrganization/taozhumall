package com.lubanresearch.lubanmall.merchantservice.domain.commandhandler;

import com.lubanresearch.lubanmall.merchantservice.domain.Shop;
import com.lubanresearch.lubanmall.merchantservice.domain.ShopRepository;
import com.lubanresearch.lubanmall.merchantservice.domain.command.UpdateShopCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class UpdateShopCommandHandler {

    @Autowired
    private ShopRepository shopRepository;

    public Shop handler(UpdateShopCommand command){

        Shop shop = new Shop();
        shop.setId(System.currentTimeMillis());
        shop.setName(command.getName());
        shop.setImgUrl(command.getImgUrl());
        shop.setDiscription(command.getDiscription());
        shop.setCreateTime(new Date());
        shop.setId(command.getId());
        shopRepository.update(shop);
        return shopRepository.get(shop.getId());
    }
}
