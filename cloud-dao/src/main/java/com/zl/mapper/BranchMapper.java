package com.zl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.entity.Branch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Mapper
public interface BranchMapper extends BaseMapper<Branch> {
    @Select("select * from branch where did=#{id}")
    public List<Branch> findBranchListById(String id);
}
