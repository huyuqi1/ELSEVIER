package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.ElementInfo;
import com.example.demo.model.SimpleElement;
import com.example.demo.dao.ApiDao;
import com.example.demo.service.ApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ApiServiceImpl
 *
 * @author YUQI
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    private ApiDao apiDao;

    @Override
    public List<SimpleElement> queryAll() {
        JSONArray db = apiDao.getData();
        return db.stream().map(i -> JSONObject.parseObject(JSONObject.toJSONString(i), SimpleElement.class)).collect(Collectors.toList());
    }


    @Override
    public List<SimpleElement> queryByGroupAndPeriod(Integer group, Integer period) {
        JSONArray db = apiDao.getData();
        List<SimpleElement> res = new ArrayList<>();
        for (int i = 0; i < db.size(); i++) {
            JSONObject item = db.getJSONObject(i);
            if (Objects.nonNull(group)) {
                Integer s = getGroupNo(item.getString("group_block"));
                if (!Objects.equals(group, s)) {
                    continue;
                }
            }
            if (Objects.nonNull(period) && !Objects.equals(period, item.getInteger("period"))) {
                continue;
            }
            res.add(JSONObject.parseObject(JSONObject.toJSONString(item), SimpleElement.class));
        }
        return res;
    }

    @Override
    public ElementInfo queryByAtomicNumber(Integer atomicNumber) {
        JSONArray db = apiDao.getData();
        for (int i = 0; i < db.size(); i++) {
            JSONObject item = db.getJSONObject(i);
            if (Objects.nonNull(atomicNumber) && Objects.equals(atomicNumber, item.getInteger("atomic_number"))) {
                ElementInfo info = ElementInfo.builder()
                        .atomicNumber(item.getInteger("atomic_number"))
                        .name(item.getString("name"))
                        .symbol(item.getString("symbol"))
                        .appearance(item.getString("appearance"))
                        .discoveryYear(getDiscoveryYear(item.getString("discovery_and_first_isolation")))
                        .period(item.getInteger("period"))
                        .build();
                info.setAlternativeName(item.getString("alternative_name"));
                info.setGroup(getGroupNo(item.getString("group_block")));
                if (!Objects.equals("n/a", item.getString("discovery"))) {
                    List<String> arr = new ArrayList<>();
                    arr.add(item.getString("discovery"));
                    info.setDiscoverers(arr);
                }
                return info;
            }
        }
        return null;
    }

}
