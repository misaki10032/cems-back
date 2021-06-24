package com.cems.controller.web.echarts;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/20  16:42
 */

import com.alibaba.fastjson.JSON;
import com.cems.pojo.EntPlan;
import com.cems.pojo.EntrusType;
import com.cems.service.ComEntrustService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("web")
public class EchartsController {
    @Autowired
    ComEntrustService comEntrustService;

    //委托类型  柱状形图
    @PostMapping("entrustTypeEchart")
    public Map<Object, Object> entrustTypeEchart() {
        Map<Object, Object> EntrustTypeMap = new HashMap<>();
        List<String> entrusts = comEntrustService.getEntrustName();
        List<Integer> listCout = comEntrustService.getTypeNums();
        EntrustTypeMap.put("X", entrusts);
        EntrustTypeMap.put("Y", listCout);
        return EntrustTypeMap;//跟templates文件夹下的test.html名字一样，返回这个界面
    }

    //委托类型  扇形状形图
    @PostMapping("entrustTypeEchartSector")
    public String entrustTypeEchartSector() throws JsonProcessingException {
        List<String> entrusts = comEntrustService.getEntrustName();
        List<Integer> listCout = comEntrustService.getTypeNums();
        List<EntrusType> list = new ArrayList<EntrusType>();
        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts.size(); i++) {
            EntrusType entrusType = new EntrusType();
            entrusType.setValue(listCout.get(i));
            entrusType.setName(entrusts.get(i));
            list.add(entrusType);
        }
        //将我们的对象解析成为json格式
        return JSON.toJSONString(list);
    }

    //获取委托数据图像数据
    @GetMapping("entrustEchart")
    @ResponseBody
    public Map entrustEchart() {
        //委托计划进度的数据
        List<String> entrusts = comEntrustService.getEntPlan();
        List<Integer> listCout = comEntrustService.getEntPlanNums();
        //创建一个jackson的对象映射器，用来解析数据
        List<EntrusType> list = new ArrayList();
        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts.size(); i++) {
            EntrusType entrusType = new EntrusType();
            entrusType.setValue(listCout.get(i));
            entrusType.setName(entrusts.get(i));
            list.add(entrusType);
        }
        List<String> entrusts1 = comEntrustService.getExistEnt();
        List<Integer> listCout1 = comEntrustService.getExistEntNums();
        List<EntPlan> list1 = new ArrayList<EntPlan>();
        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts1.size(); i++) {
            EntPlan entPlan = new EntPlan();
            entPlan.setValue(listCout1.get(i));
            entPlan.setName(entrusts1.get(i));
            list1.add(entPlan);
        }
        Map<Object, Object> EntrustTypeMap = new HashMap<>();
        EntrustTypeMap.put("EntrusType", list);
        EntrustTypeMap.put("EntPlan", list1);
        EntrustTypeMap.put("EntPlanName", entrusts);
        return EntrustTypeMap;
    }
}
