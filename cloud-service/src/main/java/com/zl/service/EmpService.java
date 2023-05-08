package com.zl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zl.entity.Dept;
import com.zl.entity.Emp;
import com.zl.vo.SearchVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
public interface EmpService extends IService<Emp> {

    IPage<Emp> findPage(SearchVo searchVo);
}
