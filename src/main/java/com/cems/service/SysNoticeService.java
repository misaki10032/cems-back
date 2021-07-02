package com.cems.service;

import com.cems.pojo.SysNotice;

import java.util.List;

public interface SysNoticeService {

    List<SysNotice> selAllNotice();
    int delOneNotice(int id);
    List<SysNotice> selSomeNot();
}
