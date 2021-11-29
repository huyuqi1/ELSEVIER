package com.example.demo.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.demo.model.ElementInfo;
import com.example.demo.model.SimpleElement;
import com.example.demo.service.ApiService;
import com.example.demo.service.ResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ApiController
 *
 * @author YUQI
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;


    @RequestMapping("/showperiodic")
    public Map<String, Object> showperiodic() {
        List<SimpleElement> list = apiService.queryAll();
        if (Objects.isNull(list) || list.size() < 1) {
            throw new ResultNotFoundException();
        } else {
            Map<String, Object> res = MapUtil.newHashMap(3);
            res.put("code", 200);
            res.put("msg", "success");
            res.put("data", list);
            return res;
        }
    }

    @RequestMapping("/element")
    public Map<String, Object> element(Integer atomicNumber) {
        if (ObjectUtil.isEmpty(atomicNumber) || apiService.queryByAtomicNumber(atomicNumber) == null) {
            throw new ResultNotFoundException();
        } else {
            Map<String, Object> res = MapUtil.newHashMap(3);
            res.put("code", 200);
            res.put("msg", "success");
            res.put("data", apiService.queryByAtomicNumber(atomicNumber));
            return res;
        }
    }

    @RequestMapping("/elementlist")
    public Map<String, Object> elementlist(Integer group, Integer period) {
        List<SimpleElement> list = apiService.queryByGroupAndPeriod(group, period);
        if (Objects.isNull(list) || list.size() < 1) {
            throw new ResultNotFoundException();
        } else {
            Map<String, Object> res = MapUtil.newHashMap(3);
            res.put("code", 200);
            res.put("msg", "success");
            res.put("data", list);
            return res;
        }
    }

}
