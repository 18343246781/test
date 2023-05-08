package com.zl.vo;

import com.zl.entity.Branch;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeptChildVo {
    private Integer value;
    private String label;

    private List<BranchVo> children=new ArrayList<>();
}
