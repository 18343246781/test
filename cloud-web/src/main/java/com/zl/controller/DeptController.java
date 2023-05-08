package com.zl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.entity.Dept;
import com.zl.service.DeptService;
import com.zl.vo.DeptChildVo;
import com.zl.vo.Result;
import com.zl.vo.ResultMessage;
import com.zl.vo.SearchVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * `dept` 前端控制器
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    //分页列表查询
    @PostMapping("/findPage")
    public IPage<Dept> findPage(@RequestBody SearchVo searchVo){
        return deptService.findPage(searchVo);
    }

    //添加
    @PostMapping("/save")
    public Result saveTown(@RequestBody Dept dept){
        return deptService.save(dept)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //通过id查询
    @GetMapping("/findById/{id}")
    public Dept findById(@PathVariable("id") String id){
        return deptService.getById(id);
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //删除
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return deptService.removeById(id)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //查询全部
    @GetMapping("/all")
    public List<Dept> all(){
        return deptService.list();
    }

    @GetMapping("/findAll")
    public List<DeptChildVo> findAll(){
        return deptService.findDeptList();
    }
}

