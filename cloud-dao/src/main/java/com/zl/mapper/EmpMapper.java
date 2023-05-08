package com.zl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    @Select("select * from emp where uid=#{id}")
    public Emp findEmpByUid(Integer id);
}
