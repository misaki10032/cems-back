package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.SysAdminInfo;
import com.cems.pojo.SysShenSu;
import com.cems.pojo.to.PageTo;
import com.cems.service.SysAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("web")
public class SysAdminController {
    @Autowired
    SysAdminService sysAdminService;

    @PostMapping("getAllAppeal/{pageNum}/{pageSize}")
    public String getAllAppeal(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysShenSu> entList = new PageInfo(sysAdminService.selAllAppeal());
            List<SysShenSu> shensuList = entList.getList();
            map.put("data", shensuList);
            map.put("code", "200");
            map.put("total", entList.getTotal());
            System.out.println(entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("ShenSuOk/{id}/{adminNum}")
    public String ShenSuOk(@PathVariable Integer id, @PathVariable String adminNum) {
        sysAdminService.shensuOK(adminNum);
        sysAdminService.delSS(id);
        return "ok";
    }

    @PostMapping("AdminByNum/{num}")
    public SysAdminInfo AdminByNum(@PathVariable String num) {
        System.err.println(num);
        return sysAdminService.getAdminInfo(num);
    }

    @PostMapping("updateAdminByNum")
    public String updateAdminByNum(@RequestBody SysAdminInfo sysAdminInfo) {
        try {
            sysAdminService.updateAdminInfo(sysAdminInfo);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
    }


}
