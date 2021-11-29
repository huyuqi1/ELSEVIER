package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * ElementInfo
 *
 * @author YUQI
 */
@Getter
@Setter
@Builder
public class ElementInfo {

    @JSONField(name = "atomic_number")
    private Integer atomicNumber;

    private String name;

    @JSONField(name = "alternative_name")
    private String alternativeName;

    private String symbol;

    private String appearance;

    private List<String> discoverers;

    @JSONField(name = "discovery_year")
    private String discoveryYear;

    private Integer group;

    private Integer period;

    public void setAlternativeName(String str) {
        this.alternativeName = Objects.equals("n/a", str) ? "none" : str;
    }

    public void setGroup(Integer group) {
        this.group = group < 1 ? 0 : group;
    }


}
