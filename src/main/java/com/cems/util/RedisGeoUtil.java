package com.cems.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisGeoUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加经纬度信息
     * <p>
     * redis 命令：geoadd key 116.405285 39.904989 "北京"
     */
    public Long geoAdd(String key, Point point, String member) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForGeo().remove(key, member);
        }
        return redisTemplate.opsForGeo().add(key, point, member);
    }

    /**
     * 查找指定key的经纬度信息，可以指定多个member，批量返回
     * <p>
     * redis命令：geopos key 北京
     */
    public List<Point> geoGet(String key, String... members) {
        return redisTemplate.opsForGeo().position(key, members);
    }

    /**
     * 返回两个地方的距离，可以指定单位，比如米m，千米km，英里mi，英尺ft
     * <p>
     * redis命令：geodist key 北京 上海
     */
    public Distance geoDist(String key, String member1, String member2, Metric metric) {
        return redisTemplate.opsForGeo().distance(key, member1, member2, metric);
    }

    /**
     * 根据给定的经纬度，返回半径不超过指定距离的元素
     * <p>
     * redis命令：georadius key 116.405285 39.904989 100 km WITHDIST WITHCOORD ASC
     * COUNT 5
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByXY(String key, Circle circle, long count) {
        // includeDistance 包含距离
        // includeCoordinates 包含经纬度
        // sortAscending 正序排序
        // limit 限定返回的记录数
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        return redisTemplate.opsForGeo().radius(key, circle, args);
    }

    /**
     * 根据指定的地点查询半径在指定范围内的位置
     * <p>
     * redis命令：georadiusbymember key 北京 100 km WITHDIST WITHCOORD ASC COUNT 5
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByPlace(String key, String member, Distance distance,
                                                                        long count) {
        // includeDistance 包含距离
        // includeCoordinates 包含经纬度
        // sortAscending 正序排序
        // limit 限定返回的记录数
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        return redisTemplate.opsForGeo().radius(key, member, distance, args);
    }

    /**
     * 返回的是geohash值
     * <p>
     * redis命令：geohash key 北京
     */
    public List<String> geoHash(String key, String member) {
        return redisTemplate.opsForGeo().hash(key, member);
    }

}