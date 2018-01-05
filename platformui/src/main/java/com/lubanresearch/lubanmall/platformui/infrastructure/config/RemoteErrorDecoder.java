package com.lubanresearch.lubanmall.platformui.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import feign.Response;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by hilbertcao on 2018/1/5.
 */


@Configuration
public class RemoteErrorDecoder implements feign.codec.ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            com.lubanresearch.lubanmall.common.bean.Response respo = new ObjectMapper().readValue(response.body().asReader(), com.lubanresearch.lubanmall.common.bean.Response.class);
            return new ServiceException(respo.getCode(), respo.getMessage());
        } catch (IOException e) {
            return new ServiceException(500);
        }
    }
}
