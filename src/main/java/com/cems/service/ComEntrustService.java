package com.cems.service;


import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.uni.UniAddEntrust;
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

    void getThisTask(int taskId, int userId);

    List<ComEntrust> getEntByPlan(String plan);

    List<ComEntrust> getEntByText(String text, String plan);

    String getEntIdByName(String name);

    int getTypeById(int id);

    List<ComEntrust> getUserEntrustsOk(int id);

    List<ComEntrust> getPrincipalEntrusts(int id);

    List<ComEntrust> getUserEntByPlan(String plan, int id);

    List<ComEntrust> getPrincipalEntByPlan(String plan, int id);

    List<ComEntrust> getUserEntByText(String text, String plan, int id);

    List<ComEntrust> getPrincipalEntByText(String text, String plan, int id);

    List<String> getAllEntrustType();

    List<String> getAllEntrustTypeId();

    void addEntrust(UniAddEntrust entrust);
}
