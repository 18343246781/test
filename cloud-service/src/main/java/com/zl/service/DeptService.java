package com.zl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zl.entity.Dept;
import com.zl.vo.DeptChildVo;
import com.zl.vo.SearchVo;

import java.util.List;

/**
 * <p>
 * `dept` 服务类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
public interface DeptService extends IService<Dept> {

    IPage<Dept> findPage(SearchVo searchVo);

    public List<DeptChildVo> findDeptList();
}
