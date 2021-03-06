package com.cems.controller.uniApp.Info;

import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.uni.UniAddEntrust;
import com.cems.pojo.uni.UniEntrust;
import com.cems.pojo.uni.UniPage;
import com.cems.pojo.uni.UniUpUserSole;
import com.cems.service.ComEntrustService;
import com.cems.service.ComUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    ComUserService userService;

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
        Map<String, Object> map = new ConcurrentHashMap<>();
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
        Map<String, Object> map = new ConcurrentHashMap<>();
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
            Map<String, Object> map = new ConcurrentHashMap<>();
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
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getPrincipalEntrusts")
    public Map<String, Object> getPrincipalEntrusts(String pageIndex, String pageSize, String id) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getPrincipalEntrusts(Integer.parseInt(id)));
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getPrincipalEntbyPlan")
    public Map<String, Object> getPrincipalEntbyPlan(UniPage page) {
        try {
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getPrincipalEntByPlan(page.getEntPlan(), Integer.parseInt(page.getId())));
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getPrincipalEntbyText")
    public Map<String, Object> getPrincipalEntbyText(UniPage page) {
        try {
            System.err.println(page);
            PageHelper.startPage(Integer.parseInt(page.getPageIndex()), Integer.parseInt(page.getPageSize()));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getPrincipalEntByText(page.getText(), page.getEntPlan(), Integer.parseInt(page.getId())));
            Map<String, Object> map = new ConcurrentHashMap<>();
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
            Map<String, Object> map = new ConcurrentHashMap<>();
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
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("getAllEntrustType")
    public Map<String, Object> getAllEntrustType() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            List<String> allEntrustType = entrustService.getAllEntrustType();
            List<String> allEntrustTypeId = entrustService.getAllEntrustTypeId();
            map.put("data", allEntrustType);
            map.put("dataId", allEntrustTypeId);
            map.put("code", 200);
        } catch (Exception e) {
            map.put("msg", "服务器故障!");
            map.put("code", 500);
        }
        return map;
    }

    @GetMapping("addEntrust")
    public Map<String, Object> addEntrust(UniAddEntrust entrust) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            ComUser userById = userService.getUserById(Integer.parseInt(entrust.getId()));
            if (userById.getUserMoney() < Integer.parseInt(entrust.getMoney())) {
                map.put("msg", "余额不足!");
                map.put("code", 501);
                return map;
            }
            Integer upMoney = userById.getUserMoney() - Integer.parseInt(entrust.getMoney());
            userService.updataUserMoney(Integer.valueOf(entrust.getId()), upMoney);
            if (userById.getUserRole().equals("agent")) {
                map.put("msg", "权限不足!");
                map.put("code", 502);
                return map;
            }
            entrustService.addEntrust(entrust);
            userById.setUserMoney(upMoney);
            map.put("msg", "添加成功!");
            map.put("loginUser", userById);
            map.put("code", 200);
        } catch (Exception e) {
            map.put("msg", "服务器故障!");
            map.put("code", 500);
        }
        return map;
    }

    @GetMapping("updateEntPlan")
    public Map<String, Object> updateEntPlan(Integer id) {
        System.err.println(id);
        Map<String, Object> map = new ConcurrentHashMap<>();
        String EntPlan = "已完成";
        ComEntrust entById = entrustService.getEntUserId(id);

        System.err.println(entById);
        ComUser userById = userService.getUserById(entById.getEntAgent());
        Integer allMoney = Integer.parseInt(entById.getEntMoney()) + userById.getUserMoney();
        try {
            entrustService.updateEntPlan(id, EntPlan);
            userService.addUserMoney(userById.getUserId(), allMoney);
            map.put("msg", "确认成功");
            map.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "服务器故障!");
            map.put("code", 500);
        }
        System.err.println(map);
        return map;
    }

}
