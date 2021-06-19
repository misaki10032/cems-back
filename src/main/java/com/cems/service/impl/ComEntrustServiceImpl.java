package com.cems.service.impl;



import com.cems.mapper.EntrustMapper;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.service.ComEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public int upQuitEtrustEntMoney(Map<String, Object> map) {
        return entrustMapper.upQuitEtrustEntMoney(map);
    }

    @Override
    public void delLeisureEntrustById2(Integer id) {
        entrustMapper.delLeisureEntrustById2(id);
    }

    @Override
    public List<ComEntrust> getEntrusts() {
        return entrustMapper.getEntrusts();
    }

    @Override
    public List<ComEntrustType> getEntTypes() {
        return entrustMapper.getEntTypes();
    }

    @Override
    public void updataEntState(int id, String status) {
        entrustMapper.updataEntState(id,status);
    }
}
