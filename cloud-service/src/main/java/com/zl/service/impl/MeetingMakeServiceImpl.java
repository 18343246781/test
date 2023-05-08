package com.zl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.entity.Meeting;
import com.zl.entity.MeetingMake;
import com.zl.mapper.MeetingMakeMapper;
import com.zl.service.EmpService;
import com.zl.service.MeetingMakeService;
import com.zl.service.MeetingService;
import com.zl.utils.PatternUtil;
import com.zl.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
@Service
public class MeetingMakeServiceImpl extends ServiceImpl<MeetingMakeMapper, MeetingMake> implements MeetingMakeService {
    @Resource
    private MeetingService meetingService;
    @Resource
    private EmpService empService;
    @Override
    public boolean isOverlap(MeetingMake meetingMake, List<MeetingMake> list) throws ParseException {
        if (!list.isEmpty()){
            for (MeetingMake thisMeeting : list) {
                Boolean flag = checkTimesHasOverlap(
                        formatDate(thisMeeting.getMeetingDate()+" "+thisMeeting.getStartTime()),
                        formatDate(thisMeeting.getMeetingDate()+" "+thisMeeting.getEndTime()),
                        formatDate(meetingMake.getMeetingDate()+" "+meetingMake.getStartTime()),
                        formatDate(meetingMake.getMeetingDate()+" "+meetingMake.getEndTime()));
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String checkAdd(MeetingMake meetingMake) throws ParseException {
        boolean overlap = isOverlap(meetingMake, baseMapper.findMeetingMakeByBid(meetingMake.getMid()));
        if (overlap){
            return "会议室当前时间已被预订";
        }
        return null;
    }

    @Override
    public IPage<MeetingMake> findPage(SearchVo searchVo) {
        Page<MeetingMake> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<MeetingMake> wrapper = new QueryWrapper<>();
        wrapper
                .eq(StringUtils.isNotEmpty(searchVo.getValueOne()),"mid",searchVo.getValueOne());
        Page<MeetingMake> meetingPage = baseMapper.selectPage(page, wrapper);
        for (MeetingMake thisMeeting : meetingPage.getRecords()) {
            thisMeeting.setMeetingMake(meetingService.getById(thisMeeting.getMid()));
            thisMeeting.setEmp(empService.getById(thisMeeting.getUid()));
        }
        return meetingPage;
    }

    @Override
    public IPage<MeetingMake> findPageByEmp(SearchVo searchVo) {
        Page<MeetingMake> page = new Page<>(searchVo.getPageNumber(),searchVo.getPageCount());
        QueryWrapper<MeetingMake> wrapper = new QueryWrapper<>();
        wrapper
                .eq(StringUtils.isNotEmpty(searchVo.getValueOne()),"mid",searchVo.getValueOne())
                .eq("uid",searchVo.getValueTwo());
        Page<MeetingMake> meetingPage = baseMapper.selectPage(page, wrapper);
        for (MeetingMake thisMeeting : meetingPage.getRecords()) {
            thisMeeting.setMeetingMake(meetingService.getById(thisMeeting.getMid()));
            thisMeeting.setEmp(empService.getById(thisMeeting.getUid()));
        }
        return meetingPage;
    }

    //字符传转date
    public static Date formatDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(date);
    }

    public static Boolean checkTimesHasOverlap(Date dynaStartTime, Date dynaEndTime, Date fixedStartTime, Date fixedEndTime) {
        return !(dynaEndTime.getTime() < fixedStartTime.getTime() || dynaStartTime.getTime() > fixedEndTime.getTime());
    }
}
