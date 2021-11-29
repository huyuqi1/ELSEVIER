package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * SimpleElement
 *
 * @author YUQI
 */
@Getter
@Setter
public class SimpleElement {

    private String name;

    @JSONField(name = "atomic_number")
    private String atomicNumber;


}
