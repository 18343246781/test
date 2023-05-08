package com.zl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.entity.Emp;
import com.zl.entity.Meeting;
import com.zl.entity.MeetingMake;
import com.zl.mapper.EmpMapper;
import com.zl.service.MeetingMakeService;
import com.zl.sys.controller.config.jwt.JwtConfig;
import com.zl.vo.Result;
import com.zl.vo.ResultMessage;
import com.zl.vo.SearchVo;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/meeting-make")
public class MeetingMakeController {
    @Resource
    private MeetingMakeService meetingMakeService;
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private EmpMapper empMapper;

    //添加
    @PostMapping("/save")
    public Result saveTown(@RequestBody MeetingMake meetingMake, HttpServletRequest request) throws ParseException {
        String message = meetingMakeService.checkAdd(meetingMake);
        if (message!=null){
            return Result.error().message(message);
        }else {
            String authorization = request.getHeader("Authorization");
            Claims claim = jwtConfig.getTokenClaim(authorization);
            Integer uid = Integer.parseInt(claim.getSubject());
            Emp dbEmp = empMapper.findEmpByUid(uid);
            meetingMake.setUid(dbEmp.getId());
            meetingMake.setStatus("ACTIVE");
            return meetingMakeService.save(meetingMake)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
        }
    }

    //分页列表查询
    @PostMapping("/findPage")
    public IPage<MeetingMake> findPage(@RequestBody SearchVo searchVo) throws ParseException {
        return meetingMakeService.findPage(searchVo);
    }

    //分页列表查询
    @PostMapping("/findPageByEmp")
    public IPage<MeetingMake> findPageByEmp(@RequestBody SearchVo searchVo, HttpServletRequest request) throws ParseException {
        String authorization = request.getHeader("Authorization");
        Claims claim = jwtConfig.getTokenClaim(authorization);
        Integer uid = Integer.parseInt(claim.getSubject());
        Emp dbEmp = empMapper.findEmpByUid(uid);
        searchVo.setValueTwo(dbEmp.getId()+"");
        return meetingMakeService.findPageByEmp(searchVo);
    }

    //取消会议
    @GetMapping("/cancelMeeting/{mid}")
    public Result cancelMeeting(@PathVariable("mid") String mid){
        MeetingMake meetingMake = meetingMakeService.getById(mid);
        meetingMake.setStatus("INACTION");
        meetingMakeService.updateById(meetingMake);
        return Result.ok().message(ResultMessage.SUCCESSMSG);
    }

}

