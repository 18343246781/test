package com.zl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.entity.Dept;
import com.zl.entity.Emp;
import com.zl.mapper.EmpMapper;
import com.zl.service.EmpService;
import com.zl.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Override
    public IPage<Emp> findPage(SearchVo searchVo) {
        Page<Emp> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper
                .like(StringUtils.isNotEmpty(searchVo.getValueOne()),"name",searchVo.getValueOne())
                .like(StringUtils.isNotEmpty(searchVo.getValueTwo()),"natives",searchVo.getValueTwo())
                .like(StringUtils.isNotEmpty(searchVo.getValueThree()),"education",searchVo.getValueThree())
                .like(StringUtils.isNotEmpty(searchVo.getValueFour()),"nation",searchVo.getValueFour())
        ;
        return baseMapper.selectPage(page, wrapper);
    }
}
