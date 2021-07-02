package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.CommentReply;
import com.cems.pojo.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    List<SysNotice> selAllNotice();
    int delOneNotice(int id);
    List<SysNotice> selSomeNot();
}
