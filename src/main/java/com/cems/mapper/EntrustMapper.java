package com.cems.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName EntrustMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Component
@Mapper
public interface EntrustMapper extends BaseMapper<ComEntrust> {

    @Select("select * from com_entrust")
    List<ComEntrust> getEntrusts();

    List<ComEntrustType> getEntTypes();

    void updataEntState(@Param("id") int id, @Param("status") String status);

}
