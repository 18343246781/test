package com.zl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zl.entity.MeetingMake;
import com.zl.vo.SearchVo;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
public interface MeetingMakeService extends IService<MeetingMake> {
    public boolean isOverlap(MeetingMake meetingMake,List<MeetingMake> list) throws ParseException;
    public String checkAdd(MeetingMake meetingMake) throws ParseException;

    IPage<MeetingMake> findPage(SearchVo searchVo);
    IPage<MeetingMake> findPageByEmp(SearchVo searchVo);
}
