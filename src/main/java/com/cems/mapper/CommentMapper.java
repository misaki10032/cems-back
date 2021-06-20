package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.ForumComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName CommentMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Mapper
@Component
public interface CommentMapper extends BaseMapper<ForumComment> {

    List<ForumComment> getComments();

    List<ForumComment> getCommentLike(String text);
}
