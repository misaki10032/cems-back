package com.cems.service.impl;

import com.cems.mapper.SysNoticeMapper;
import com.cems.pojo.SysNotice;
import com.cems.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    SysNoticeMapper sysNoticeMapper;

    @Override
    public List<SysNotice> selAllNotice() {
        return sysNoticeMapper.selAllNotice();
    }

    @Override
    public int delOneNotice(int id) {
        return sysNoticeMapper.delOneNotice(id);
    }

    @Override
    public List<SysNotice> selSomeNot() {
        return sysNoticeMapper.selSomeNot();
    }

    @Override
    public int insNotice(Map<String, Object> map) {
        return sysNoticeMapper.insNotice(map);
    }
}
