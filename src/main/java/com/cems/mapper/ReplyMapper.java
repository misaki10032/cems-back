package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.CommentReply;
import com.cems.pojo.Message;
import com.cems.pojo.uni.UniReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName ReplyMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Mapper
@Component
public interface ReplyMapper extends BaseMapper<CommentReply> {

    List<CommentReply> getReplyByCommId(int id);

    List<CommentReply> getReplyLike(@Param("id") int id, @Param("text") String text);

    int addReply(UniReply reply);
}
