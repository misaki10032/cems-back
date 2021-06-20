package com.cems.service.impl;


import com.cems.mapper.EntrustMapper;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.service.ComEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ComEntrustServiceImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Service
public class ComEntrustServiceImpl implements ComEntrustService {
    @Autowired
    EntrustMapper entrustMapper;

    @Override
    public int upQuitEtrustEntMoney(Map<String, Object> map) {
        return entrustMapper.upQuitEtrustEntMoney(map);
    }

    @Override
    public void delLeisureEntrustById2(Integer id) {
        entrustMapper.delLeisureEntrustById2(id);
    }

    @Override
    public String handleDeleteById(Integer id) {
        return entrustMapper.handleDeleteById(id);
    }

    @Override
    public boolean judgeTypeRep(String entrustType) {
        System.out.println("entrustTypeentrustType+" + entrustType);
//        redisUtil.delete("cems_AllEtrustType");
        List<ComEntrustType> allEtrustType = entrustMapper.getEntTypes();
        LinkedList<String> list = new LinkedList<>();
        System.err.println(list.toString());
        for (ComEntrustType type : allEtrustType) {
            System.err.println(type.getEntType().equals(entrustType));
            list.addLast(type.getEntType());
        }
        if (list.contains(entrustType)) {
            System.err.println(entrustType + "有重复的");
            return false;
        } else {
//            entrustMapper.addEntrustType(entrustType);
            System.err.println(entrustType + "无重复");
            return true;
        }
    }

    @Override
    public boolean addEntrustType(String entrustType) {

        List<ComEntrustType> allEtrustType = entrustMapper.getEntTypes();
        System.err.println(allEtrustType);
        System.out.println(entrustType);
        LinkedList<String> list = new LinkedList<>();
        for (ComEntrustType type : allEtrustType) {
            System.err.println(type.getEntType().equals(entrustType));
            list.addLast(type.getEntType());
        }
        if (list.contains(entrustType)) {
            System.out.println(entrustType + "插入失败");
            return false;
        } else {

            entrustMapper.addEntrustType(entrustType);
            return true;
        }
    }

    @Override
    public List<ComEntrust> getEntrusts() {
        return entrustMapper.getEntrusts();
    }

    @Override
    public List<ComEntrustType> getEntTypes() {
        return entrustMapper.getEntTypes();
    }

    @Override
    public void updataEntState(int id, String status) {
        entrustMapper.updataEntState(id, status);
    }
}
