package com.cems.controller.uniApp.Info;

import com.cems.pojo.to.ComUser;
import com.cems.pojo.uni.UniNearPeople;
import com.cems.service.UserService;
import com.cems.util.RedisGeoUtil;
import com.cems.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Uni_NearPeoController
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class Uni_NearPeoController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisGeoUtil redisGeoUtil;
    @Autowired
    UserService userService;

    @GetMapping("setUserPosition")
    public Map<String, Object> setUserJW(String userId, String longitude, String latitude, String userInfo) {
        Map<String, Object> map = new HashMap<>();
        try {
            String redisKey = "cems_JW";
            redisGeoUtil.geoAdd(redisKey, new Point(Double.parseDouble(longitude), Double.parseDouble(latitude)), userId);
            redisUtil.set("cems_jw_info_" + userId, userInfo);
            redisUtil.expire("cems_jw_info_" + userId,24*60*30, TimeUnit.MINUTES);
            List<Point> points = redisGeoUtil.geoGet(redisKey, String.valueOf(userId));
            System.err.println("添加位置坐标点:" + userId + points);
            redisUtil.expire("cems_JW",24*60*30, TimeUnit.MINUTES);
            map.put("code", "200");
            map.put("msg", "成功获取位置信息!");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器异常!");
        }
        return map;
    }

    @GetMapping("getOtherPeople")
    public Map<String, Object> getOtherPeople(String userId) {
        System.err.println(userId);
        //redis中key的名字
        String redisKey = "cems_JW";
        Map<String, Object> map = new HashMap<>();
        //存储为list
        List<UniNearPeople> otherPeople = new ArrayList<>();
        //查询10KM以内的人
        try {
            //获取自己的位置
            String oinfo = String.valueOf(redisUtil.get("cems_jw_info_" + userId));
            System.out.println(oinfo);
            if (oinfo == null || oinfo.equals("null")) {
                map.put("code", "501");
                map.put("msg", "获取不到位置信息!");
                return map;
            }
            GeoResults<RedisGeoCommands.GeoLocation<String>> points = redisGeoUtil.nearByPlace(redisKey, userId, new Distance(100, Metrics.KILOMETERS), 100);
            //遍历取得附近的人的id
            for (GeoResult<RedisGeoCommands.GeoLocation<String>> point : points) {
                String otherId = point.getContent().getName();
                UniNearPeople other = new UniNearPeople();
                if (otherId.equals(userId)) {
                    continue;
                }
                ComUser comUser = userService.selOneUser(Integer.parseInt(otherId));
                System.err.println(point.getDistance().getValue() * 1000 + "---->user" + point.getContent().getName());
                other.setUserDistance(String.valueOf(point.getDistance().getValue() * 1000))
                        .setUserId(String.valueOf(comUser.getUserId()))
                        .setUserName(comUser.getUserPname())
                        .setUserSex(comUser.getUserSex());
                otherPeople.add(other);
            }
            if (otherPeople.size() == 0) {
                map.put("code", "201");
                map.put("userMsg", "你附近没有其他人!");
            } else {
                map.put("code", "200");
                map.put("userMsg", "获取成功!");
            }
            map.put("list", otherPeople);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "系统未知故障!");
        }
        return map;
    }

}
