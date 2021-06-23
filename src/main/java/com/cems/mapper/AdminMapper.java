package com.cems.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfo;
import com.cems.pojo.SysShenSu;
import com.cems.pojo.to.LevelUpDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    @Select("select * from sys_admin where admin_num = #{num}")
    SysAdmin getAdminNum(String num);

    /**管理员申诉账号密码验证*/
    SysAdmin gljudgeAP(Map<String,Object> map);
    /**管理员注册*/
    int registerSys(Map<String,Object> map);
    /**添加邮箱*/
    int insertEmail(Map<String, Object> map);

    /**
     * 管理员注册差重复
     */
    List<SysAdmin> selOneSysByAcc(String acc);

    /**
     * 管理员注册差重复(手机号,邮箱)
     */
    List<SysAdminInfo> selOneSysByEP(Map<String, Object> map);

    List<SysAdminInfo> selOneSysByEP2(Map<String, Object> map);

    /**
     * 管理员申诉
     */
    int insApple(Map<String, Object> map);

    /**
     * 管理员申诉查询
     */
    List<SysShenSu> selAllAppeal();

    /**
     * 管理员申诉成功
     */
    int shensuOK(String adminNum);

    /**
     * 管理员申诉成功,删除申诉
     */
    int delSS(Integer id);

    /**
     * 根据账号查询管理员
     */
    SysAdmin selOneSysZC(String acc);

    void addLevelUp(LevelUpDTO level);
}

