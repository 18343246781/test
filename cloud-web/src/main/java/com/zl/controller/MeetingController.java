package com.zl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.entity.Dept;
import com.zl.entity.Meeting;
import com.zl.service.MeetingService;
import com.zl.vo.Result;
import com.zl.vo.ResultMessage;
import com.zl.vo.SearchVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XW.Fan
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Resource
    private MeetingService meetingService;

    //分页列表查询
    @PostMapping("/findPage")
    public IPage<Meeting> findPage(@RequestBody SearchVo searchVo) throws ParseException {
        return meetingService.findPage(searchVo);
    }

    //添加
    @PostMapping("/save")
    public Result saveTown(@RequestBody Meeting meeting){
        QueryWrapper<Meeting> wrapper = new QueryWrapper<>();
        wrapper.eq("name",meeting.getName());
        List<Meeting> list = meetingService.list(wrapper);
        if (!list.isEmpty()){
            return Result.error().message("该会议名称已存在");
        }
        return meetingService.save(meeting)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //通过id查询
    @GetMapping("/findById/{id}")
    public Meeting findById(@PathVariable("id") String id){
        return meetingService.getById(id);
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Meeting meeting){
        return meetingService.updateById(meeting)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //删除
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return meetingService.removeById(id)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //查询全部
    @GetMapping("/all")
    public List<Meeting> all(){
        return meetingService.list();
    }
}

