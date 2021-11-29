package com.example.demo.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.model.ElementInfo;
import com.example.demo.model.SimpleElement;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ApiService
 *
 * @author YUQI
 */
public interface ApiService {

    /**
     *
     * get all data
     * @return {@link JSONArray}
     */
    List<SimpleElement> queryAll();

    /**
     * get perid
     *
     * @param group
     * @param period
     * @return {@link JSONArray}
     */
    List<SimpleElement> queryByGroupAndPeriod(Integer group, Integer period);

    /**
     * Search details by atomic number
     *
     * @param atomicNumber
     * @return {@link JSONArray}
     */

    ElementInfo queryByAtomicNumber(Integer atomicNumber);

    /**
     * get group num in block
     */
    default Integer getGroupNo(String groupBlock) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(groupBlock);
        String res = m.replaceAll("").trim();
        if (StrUtil.isBlank(res)) {
            return -1;
        }
        return Integer.parseInt(res);
    }

    /**
     * get year
     */
    default String getDiscoveryYear(String str) {
        if (Objects.equals("n/a", str)) {
            return "unknown";
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
