package com.cems.service;


import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.uni.UniEntrust;

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

    List<ComEntrust> getEntrustsOk();

    List<ComEntrustType> getEntTypes();

    void updataEntState(int id, String status);

    int upQuitEtrustEntMoney(Map<String, Object> map);

    void delLeisureEntrustById2(Integer id);

    String handleDeleteById(Integer id);

    boolean judgeTypeRep(String entrustType);

    boolean addEntrustType(String entrustType);

    List<String> getEntrustName();

    List<Integer> getTypeNums();

    List<String> getEntPlan();

    List<Integer> getEntPlanNums();

    List<String> getExistEnt();

    List<Integer> getExistEntNums();

    UniEntrust getEntById(int id);

}
