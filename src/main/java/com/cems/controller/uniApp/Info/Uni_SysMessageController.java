package com.cems.controller.uniApp.Info;

import com.cems.pojo.ComEntrust;
import com.cems.pojo.Message;
import com.cems.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("uniApp")
public class Uni_SysMessageController {
    @Autowired
    UserService userService;

    @GetMapping("getAllSysMessage")
    public Map<String, Object> getAllSysMessage(String pageIndex, String pageSize, String id) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            List<Message> messages = userService.selAllMessageById(Integer.parseInt(id));
            for (Message message:messages) {
                message.setCheck(false);
                message.setDel(true);
            }
            System.out.println(messages);
            PageInfo<Message> messList = new PageInfo<>(messages);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", messList.getList());
            map.put("total", messList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }
    @GetMapping("delOneSysMessage")
    public String delOneSysMessage(String mid) {
        try {
            System.out.println("mid==="+mid+"删除了");
            userService.delMessage(Integer.valueOf(mid));
            return "";
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }

    }

    @GetMapping("delSysMessage")
    public Map<String, Object> delSysMessage(String pageIndex, String pageSize, String id,String mid) {
        try {
            System.out.println("mid====="+mid);
            String[] split = mid.split(",");
            System.out.println(Arrays.toString(split));
            for (String s: split) {
                userService.delMessage(Integer.valueOf(s));
            }
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            List<Message> messages = userService.selAllMessageById(Integer.parseInt(id));
            for (Message message:messages) {
                message.setCheck(false);
                message.setDel(true);
            }
            PageInfo<Message> messList = new PageInfo<>(messages);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", messList.getList());
            map.put("total", messList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }

    }

    @GetMapping("delSysMessageByUid")
    public Map<String, Object> delSysMessageByUid(String pageIndex, String pageSize, String id) {
        try {
            System.out.println("清空成功");
            userService.delMessageByUid(Integer.valueOf(id));
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            List<Message> messages = userService.selAllMessageById(Integer.parseInt(id));
            for (Message message:messages) {
                message.setCheck(false);
                message.setDel(true);
            }
            PageInfo<Message> messList = new PageInfo<>(messages);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", messList.getList());
            map.put("total", messList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }

    }

}
