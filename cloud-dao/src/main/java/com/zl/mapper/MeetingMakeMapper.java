package com.zl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.entity.MeetingMake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
@Mapper
public interface MeetingMakeMapper extends BaseMapper<MeetingMake> {
    @Select("select * from meeting_make where mid=#{mid} and status='ACTIVE'")
    public List<MeetingMake> findMeetingMakeByBid(Integer mid);
}
