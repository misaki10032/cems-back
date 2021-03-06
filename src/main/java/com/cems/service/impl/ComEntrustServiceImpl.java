package com.cems.service.impl;


import com.cems.mapper.EntrustMapper;
import com.cems.mapper.UserMapper;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.uni.UniAddEntrust;
import com.cems.pojo.uni.UniEntrust;
import com.cems.pojo.uni.UniPage;
import com.cems.service.ComEntrustService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @Autowired
    UserMapper userMapper;

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
            System.err.println(entrustType + "无重复");
            return true;
        }
    }

    @Override
    public List<String> getEntrustName() {
        return entrustMapper.getEntrustName();
    }

    @Override
    public List<String> getEntPlan() {
        return entrustMapper.getEntPlan();
    }

    @Override
    public List<Integer> getEntPlanNums() {
        return entrustMapper.getEntPlanNums();
    }

    @Override
    public List<String> getExistEnt() {
        return entrustMapper.getExistEnt();
    }

    @Override
    public List<Integer> getExistEntNums() {
        return entrustMapper.getExistEntNums();
    }

    @Override
    public UniEntrust getEntById(int id) {
        UniEntrust entrust = new UniEntrust();
        ComEntrust oldEnt = entrustMapper.getEntById(id);
        entrust.setId(oldEnt.getId())
                .setEntData(oldEnt.getEntData())
                .setEntState(oldEnt.getEntState())
                .setEntPlan(oldEnt.getEntPlan())
                .setGmtCreate(oldEnt.getGmtCreate())
                .setGmtEnd(oldEnt.getGmtEnd())
                .setGmtMoney(Integer.parseInt(oldEnt.getEntMoney()));
        entrust.setEntConsignor(userMapper.selOneUser(oldEnt.getEntConsignor()).getUserPname());
        if (oldEnt.getEntAgent() != -1) {
            entrust.setEntAgent(userMapper.selOneUser(oldEnt.getEntAgent()).getUserPname());
        } else {
            entrust.setEntAgent("还没有被接取");
        }
        entrust.setEntType(entrustMapper.getEntTypeById(Integer.parseInt(oldEnt.getEntTypeId())).getEntType());
        return entrust;
    }

    @Override
    public void getThisTask(int taskId, int userId) {
        entrustMapper.getThisEntrust(taskId, userId);
    }

    @Override
    public List<ComEntrust> getEntByPlan(String plan) {
        List<ComEntrust> entByPlan = entrustMapper.getEntByPlan(plan);
        for (ComEntrust comEntrust : entByPlan) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entByPlan;
    }

    @Override
    public List<ComEntrust> getUserEntByPlan(String plan, int id) {
        List<ComEntrust> entByPlan = entrustMapper.getUserEntByPlan(plan, id);
        for (ComEntrust comEntrust : entByPlan) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entByPlan;
    }
    @Override
    public List<ComEntrust> getPrincipalEntByPlan(String plan, int id) {
        List<ComEntrust> entByPlan = entrustMapper.getPrincipalEntByPlan(plan, id);
        for (ComEntrust comEntrust : entByPlan) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entByPlan;
    }
    @Override
    public List<ComEntrust> getEntByText(String text, String plan) {
        ComEntrustType typeByName = entrustMapper.getTypeByName(text);
        String type = null;
        if (typeByName != null) {
            type = String.valueOf(typeByName.getId());
        }
        if (plan.equals("全部")) {
            plan = null;
        }
        List<ComEntrust> entList = entrustMapper.getEntByText("%" + text + "%", type, plan);
        for (ComEntrust comEntrust : entList) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entList;
    }

    @Override
    public List<ComEntrust> getUserEntByText(String text, String plan, int id) {
        ComEntrustType typeByName = entrustMapper.getTypeByName(text);
        String type = null;
        if (typeByName != null) {
            type = String.valueOf(typeByName.getId());
        }
        if (plan.equals("全部")) {
            plan = null;
        }
        List<ComEntrust> entList = entrustMapper.getUserEntByText("%" + text + "%", type, plan, id);
        for (ComEntrust comEntrust : entList) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entList;
    }

    @Override
    public List<ComEntrust> getPrincipalEntByText(String text, String plan, int id) {
        ComEntrustType typeByName = entrustMapper.getTypeByName(text);
        String type = null;
        if (typeByName != null) {
            type = String.valueOf(typeByName.getId());
        }
        if (plan.equals("全部")) {
            plan = null;
        }
        List<ComEntrust> entList = entrustMapper.getPrincipalEntByText("%" + text + "%", type, plan, id);
        for (ComEntrust comEntrust : entList) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entList;
    }

    @Override
    public List<String> getAllEntrustType() {
        return entrustMapper.getAllEntrustType();
    }

    @Override
    public List<String> getAllEntrustTypeId() {
        return entrustMapper.getAllEntrustTypeId();
    }

    @Override
    public void addEntrust(UniAddEntrust entrust) {
        entrust.setMoney(String.valueOf(Integer.parseInt(entrust.getMoney())));
        entrustMapper.addEntrust(entrust);
    }

    @Override
    public String getEntIdByName(String name) {
        return entrustMapper.getTypeByName(name).getEntType();
    }

    @Override
    public int getTypeById(int id) {
        return entrustMapper.getTypeById(id).getId();
    }

    @Override
    public List<ComEntrust> getUserEntrustsOk(int id) {
        List<ComEntrust> userEntrustsOk = entrustMapper.getUserEntrustsOk(id);
        for (ComEntrust comEntrust : userEntrustsOk) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return userEntrustsOk;
    }

    @Override
    public List<ComEntrust> getPrincipalEntrusts(int id) {
        List<ComEntrust> userEntrustsOk = entrustMapper.getPrincipalEntrusts(id);
        for (ComEntrust comEntrust : userEntrustsOk) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return userEntrustsOk;
    }

    @Override
    public List<Integer> getTypeNums() {
        return entrustMapper.getTypeNums();
    }

    @Override
    public boolean addEntrustType(String entrustType) {
        List<ComEntrustType> allEtrustType = entrustMapper.getEntTypes();
        LinkedList<String> list = new LinkedList<>();
        for (ComEntrustType type : allEtrustType) {
            list.addLast(type.getEntType());
        }
        if (list.contains(entrustType)) {
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
    public List<ComEntrust> getEntrustsOk() {
        List<ComEntrust> entrustsOk = entrustMapper.getEntrustsOk();
        for (ComEntrust comEntrust : entrustsOk) {
            String entTypeId = comEntrust.getEntTypeId();
            comEntrust.setEntTypeId(entrustMapper.getTypeById(Integer.parseInt(entTypeId)).getEntType());
        }
        return entrustsOk;
    }

    @Override
    public List<ComEntrustType> getEntTypes() {
        return entrustMapper.getEntTypes();
    }

    @Override
    public void updataEntState(int id, String status) {
        entrustMapper.updataEntState(id, status);
    }

    @Override
    public void updateEntPlan(Integer id, String entPlan) {
        entrustMapper.updateEntPlan(id,entPlan);
    }

    @Override
    public ComEntrust getEntUserId(Integer id) {
        return entrustMapper.getEntById(id);
    }
}
