package com.lubanresearch.lubanmall.demoservice.service.impl;

import com.lubanresearch.lubanmall.demoservice.domain.repository.DemoRepository;
import com.lubanresearch.lubanmall.demoservice.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hilbert.cao
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;
}
