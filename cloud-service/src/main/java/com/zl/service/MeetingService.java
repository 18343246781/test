package com.zl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zl.entity.Meeting;
import com.zl.vo.SearchVo;

import java.text.ParseException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
public interface MeetingService extends IService<Meeting> {

    IPage<Meeting> findPage(SearchVo searchVo) throws ParseException;
}
