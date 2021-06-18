package com.cems.service.impl;



import com.cems.mapper.EntrustMapper;
import com.cems.pojo.ComEntrust;
import com.cems.service.ComEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ComEntrustServiceImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Service
public class ComEntrustServiceImpl implements ComEntrustService {
    @Autowired
    EntrustMapper entrustMapper;
    @Override
    public List<ComEntrust> getEntrusts() {
        return entrustMapper.getEntrusts();
    }
}
