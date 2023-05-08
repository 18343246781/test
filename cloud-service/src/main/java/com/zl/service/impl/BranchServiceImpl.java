package com.zl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.entity.Branch;
import com.zl.mapper.BranchMapper;
import com.zl.service.BranchService;
import com.zl.service.DeptService;
import com.zl.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Service
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch> implements BranchService {
    @Resource
    private DeptService deptService;

    @Override
    public IPage<Branch> findPage(SearchVo searchVo) {
        Page<Branch> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<Branch> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(searchVo.getValueOne()),"deptname",searchVo.getValueOne());
        Page<Branch> branchPage = baseMapper.selectPage(page, wrapper);
        for (Branch thisBranch : branchPage.getRecords()) {
            thisBranch.setDept(deptService.getById(thisBranch.getDid()));
        }
        return branchPage;
    }
}
