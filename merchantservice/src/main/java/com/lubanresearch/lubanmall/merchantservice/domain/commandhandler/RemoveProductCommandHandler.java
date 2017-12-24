package com.lubanresearch.lubanmall.merchantservice.domain.commandhandler;

import com.lubanresearch.lubanmall.merchantservice.domain.ProductRepository;
import com.lubanresearch.lubanmall.merchantservice.domain.command.RemoveProductCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class RemoveProductCommandHandler {

    @Autowired
    private ProductRepository productRepository;
    @CommandHandler
    public void handler(RemoveProductCommand command){

        productRepository.remove(command.getId());
    }
}
