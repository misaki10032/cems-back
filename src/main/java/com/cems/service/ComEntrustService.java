package com.cems.service;


import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
