package com.cems.controller.uniApp.Info;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.uni.UniEntrust;
import com.cems.pojo.uni.UniPage;
import com.cems.service.ComEntrustService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EntrustController
 * @Author 陈新予(blank)
 * @Date 2021/6/24
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class Uni_EntrustController {
    @Autowired
    ComEntrustService entrustService;
    @GetMapping("getEnts")
    public Map<String, Object> getEnts(String pageIndex, String pageSize) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getEntrustsOk());
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("toEntrustInfo")
    public Map<String, Object> getEntInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            UniEntrust entById = entrustService.getEntById(id);
            map.put("code", "200");
            map.put("entrust", entById);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "委托状态异常!");
        }
        return map;
    }

    @GetMapping("toTaskDone")
    public Map<String, Object> toTaskDone(String taskId, String userId) {
        System.err.println(taskId);
        System.err.println(userId);
        Map<String, Object> map = new HashMap<>();
        try {
            entrustService.getThisTask(Integer.parseInt(taskId), Integer.parseInt(userId));
            map.put("code", "200");
        } catch (Exception e) {
            map.put("code", "500");
        }
        return map;
    }

    @GetMapping("getEntsByPlan")
    public Map<String, Object> getEntsbyPlan(UniPage page) {
        try {
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getEntByPlan(page.getEntPlan()));
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getEntsByText")
    public Map<String, Object> getEntsbyText(UniPage page) {
        try {
            System.err.println(page);
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getEntByText(page.getText(), page.getEntPlan()));
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getUserEnts")
    public Map<String, Object> getEnts(String pageIndex, String pageSize, String id) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getUserEntrustsOk(Integer.parseInt(id)));
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getUserEntsByPlan")
    public Map<String, Object> getUserEntsbyPlan(UniPage page) {
        try {
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getUserEntByPlan(page.getEntPlan(), Integer.parseInt(page.getId())));
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getUserEntsByText")
    public Map<String, Object> getUserEntsbyText(UniPage page) {
        try {
            System.err.println(page);
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getUserEntByText(page.getText(), page.getEntPlan(), Integer.parseInt(page.getId())));
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

}
