package com.zl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.entity.Branch;
import com.zl.entity.Dept;
import com.zl.mapper.BranchMapper;
import com.zl.mapper.DeptMapper;
import com.zl.service.BranchService;
import com.zl.service.DeptService;
import com.zl.vo.BranchVo;
import com.zl.vo.DeptChildVo;
import com.zl.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * `dept` 服务实现类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Resource
    private BranchMapper branchMapper;

    @Override
    public IPage<Dept> findPage(SearchVo searchVo) {
        Page<Dept> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(searchVo.getValueOne()),"deptname",searchVo.getValueOne());
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public List<DeptChildVo> findDeptList() {
        ArrayList<DeptChildVo> list = new ArrayList<>();
        List<Dept> deptList = baseMapper.selectList(null);
        if (!deptList.isEmpty()){
            for (Dept thisDept : deptList) {
                DeptChildVo vo = new DeptChildVo();
                vo.setValue(thisDept.getId());
                vo.setLabel(thisDept.getDeptname());
                list.add(vo);
            }
        }
        if (!list.isEmpty()){
            for (DeptChildVo thisVo : list) {
                ArrayList<BranchVo> childList = new ArrayList<>();
                List<Branch> branchList = branchMapper.findBranchListById(thisVo.getValue() + "");
                if (!branchList.isEmpty()){
                    for (Branch thisBranch : branchList) {
                        BranchVo vo = new BranchVo();
                        vo.setValue(thisBranch.getId());
                        vo.setLabel(thisBranch.getDeptname()+" / "+thisBranch.getPostname());
                        childList.add(vo);
                    }
                }
                thisVo.setChildren(childList);
            }
        }
        return list;
    }
}
