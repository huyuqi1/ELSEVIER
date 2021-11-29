package com.example.demo.dao;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;

/**
 * ApiDao
 *
 * @author YUQI
 */
@Repository
public class ApiDao {

    /**
     * get data from json
     *
     * @return
     */
    public JSONArray getData() {
        String str = FileUtil.readString("classpath:periodic_table.json", StandardCharsets.UTF_8);
        return JSONArray.parseArray(str);
    }

}
