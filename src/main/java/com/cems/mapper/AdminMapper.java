package com.cems.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.SysAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName AdminMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
@Mapper
@Component
public interface AdminMapper extends BaseMapper<SysAdmin> {

    List<SysAdmin> getAdmins();

}

