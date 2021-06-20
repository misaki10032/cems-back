package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.to.FormArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName ArticleMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Mapper
@Component
public interface ArticleMapper extends BaseMapper<ForumArticle> {

    List<ForumArticle> getArticles();

    List<ForumArticle> getArticleLike(@Param("title") String title, @Param("status") String status);

    void killArticle(@Param("id") int id, @Param("status") String status);

}
