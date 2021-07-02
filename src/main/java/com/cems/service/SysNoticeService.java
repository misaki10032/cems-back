package com.cems.service;

import com.cems.pojo.SysNotice;

import java.util.List;
import java.util.Map;

public interface SysNoticeService {

    List<SysNotice> selAllNotice();
    int delOneNotice(int id);
    List<SysNotice> selSomeNot();
    int insNotice(Map<String,Object> map);
}
