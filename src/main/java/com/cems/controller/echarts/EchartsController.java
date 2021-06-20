package com.cems.controller.echarts;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/20  16:42
 */

import com.cems.pojo.EntPlan;
import com.cems.pojo.EntrusType;
import com.cems.service.ComEntrustService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public Map entrustTypeEchart() {
        System.out.println("dsadasdastoEntrustTypeEcharttoEntrustTypeEchart");
        Map EntrustTypeMap = new HashMap<>();
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
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        List<EntrusType> list = new ArrayList<EntrusType>();
//        List<>
        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts.size(); i++) {
            EntrusType entrusType = new EntrusType();
            entrusType.setValue(listCout.get(i));
            entrusType.setName(entrusts.get(i));

            list.add(entrusType);
        }
        //将我们的对象解析成为json格式
        String str = mapper.writeValueAsString(list);
        System.err.println(str);
        return str;  //跟templates文件夹下的test.html名字一样，返回这个界面
    }




    //获取委托数据图像数据
    @GetMapping("entrustEchart")
    @ResponseBody
    public Map entrustEchart() throws JsonProcessingException {


        //委托计划进度的数据
        List<String> entrusts = comEntrustService.getEntPlan();
        List<Integer> listCout = comEntrustService.getEntPlanNums();
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        List<EntrusType> list = new ArrayList<EntrusType>();

        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts.size(); i++) {
            EntrusType entrusType = new EntrusType();
            entrusType.setValue(listCout.get(i));
            entrusType.setName(entrusts.get(i));
            list.add(entrusType);
        }
        //将我们的对象解析成为json格式
        String str = mapper.writeValueAsString(list);

        //委托类型中 存在真正委托的查询
        List<String> entrusts1 = comEntrustService.getExistEnt();
        List<Integer> listCout1 = comEntrustService.getExistEntNums();
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper1 = new ObjectMapper();
        List<EntPlan> list1 = new ArrayList<EntPlan>();

        //利用字符拼接所需要的数据
        for (int i = 0; i < entrusts1.size(); i++) {
            EntPlan entPlan = new EntPlan();
            entPlan.setValue(listCout1.get(i));
            entPlan.setName(entrusts1.get(i));
            list1.add(entPlan);
        }
        //将我们的对象解析成为json格式
        Map EntrustTypeMap = new HashMap<>();

        EntrustTypeMap.put("EntrusType", list);
        EntrustTypeMap.put("EntPlan", list1);
        //委托计划进度的数据
        EntrustTypeMap.put("EntPlanName", entrusts);
        return EntrustTypeMap;  //跟templates文件夹下的test.html名字一样，返回这个界面
    }


}
