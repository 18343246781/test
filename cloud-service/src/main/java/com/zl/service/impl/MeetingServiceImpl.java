package com.zl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.entity.Emp;
import com.zl.entity.Meeting;
import com.zl.entity.MeetingMake;
import com.zl.mapper.MeetingMakeMapper;
import com.zl.mapper.MeetingMapper;
import com.zl.service.MeetingMakeService;
import com.zl.service.MeetingService;
import com.zl.utils.PatternUtil;
import com.zl.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {
    @Resource
    private MeetingMakeService meetingMakeService;
    @Resource
    private MeetingMakeMapper meetingMakeMapper;
    @Override
    public IPage<Meeting> findPage(SearchVo searchVo) throws ParseException {
        Page<Meeting> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<Meeting> wrapper = new QueryWrapper<>();
        wrapper
                .like(StringUtils.isNotEmpty(searchVo.getValueOne()),"name",searchVo.getValueOne());
        Page<Meeting> meetingPage = baseMapper.selectPage(page, wrapper);
        for (Meeting thisMeeting : meetingPage.getRecords()) {
            MeetingMake meetingMake = new MeetingMake();
            meetingMake.setMeetingDate(PatternUtil.getNowDate());
            meetingMake.setStartTime(PatternUtil.getNowTime());
            meetingMake.setEndTime(PatternUtil.getNowTime());
            boolean overlap = meetingMakeService.isOverlap(meetingMake, meetingMakeMapper.findMeetingMakeByBid(thisMeeting.getId()));
            if (overlap){
                thisMeeting.setStatus("占用");
            }else {
                thisMeeting.setStatus("空闲");
            }
        }
        return meetingPage;
    }
}
