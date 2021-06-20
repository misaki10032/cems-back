package com.cems.service;


import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ComEntrustService
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
public interface ComEntrustService {

    List<ComEntrust> getEntrusts();

    List<ComEntrustType> getEntTypes();


    void updataEntState(int id, String status);

    int upQuitEtrustEntMoney(Map<String, Object> map);



    void delLeisureEntrustById2(Integer id);

    String handleDeleteById(Integer id);


    boolean judgeTypeRep(String entrustType);

    boolean addEntrustType(String entrustType);

    //图形查去数据
    List<String> getEntrustName();
    //图形查去数据
    List<Integer> getTypeNums();

    List<String> getEntPlan();

    List<Integer> getEntPlanNums();

    List<String> getExistEnt();

    List<Integer> getExistEntNums();
}
