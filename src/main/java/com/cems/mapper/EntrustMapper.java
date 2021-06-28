package com.cems.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.uni.UniAddEntrust;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName EntrustMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Component
@Mapper
public interface EntrustMapper extends BaseMapper<ComEntrust> {

    List<ComEntrust> getEntrusts();

    List<ComEntrust> getEntrustsOk();

    List<ComEntrustType> getEntTypes();

    void updataEntState(@Param("id") int id, @Param("status") String status);

    int upQuitEtrustEntMoney(Map<String, Object> map);

    void delLeisureEntrustById2(Integer id);

    String handleDeleteById(Integer id);

    void addEntrustType(String entrustType);

    List<String> getEntrustName();

    List<Integer> getTypeNums();

    List<String> getEntPlan();

    List<Integer> getEntPlanNums();

    List<String> getExistEnt();

    List<Integer> getExistEntNums();

    ComEntrust getEntById(int id);

    ComEntrustType getEntTypeById(int id);

    void getThisEntrust(@Param("taskId") int taskId, @Param("userId") int userId);

    ComEntrustType getTypeById(int id);

    ComEntrustType getTypeByName(String text);

    List<ComEntrust> getEntByText(@Param("text") String text, @Param("textType") String textType, @Param("entPlan") String entPlan);

    List<ComEntrust> getEntByPlan(String entPlan);

    List<ComEntrust> getUserEntrustsOk(int id);

    List<ComEntrust> getUserEntByText(@Param("text") String text, @Param("textType") String textType, @Param("entPlan") String entPlan, @Param("id") int id);

    List<ComEntrust> getUserEntByPlan(@Param("entPlan") String entPlan, @Param("id") int id);

    List<String> getAllEntrustType();

    List<String> getAllEntrustTypeId();

    void addEntrust(UniAddEntrust entrust);

}
