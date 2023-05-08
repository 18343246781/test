package com.zl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.entity.Branch;
import com.zl.entity.Dept;
import com.zl.service.BranchService;
import com.zl.service.DeptService;
import com.zl.vo.Result;
import com.zl.vo.ResultMessage;
import com.zl.vo.SearchVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/branch")
public class BranchController {
    @Resource
    private BranchService branchService;

    //分页列表查询
    @PostMapping("/findPage")
    public IPage<Branch> findPage(@RequestBody SearchVo searchVo){
        return branchService.findPage(searchVo);
    }

    //添加
    @PostMapping("/save")
    public Result saveTown(@RequestBody Branch branch){
        return branchService.save(branch)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //通过id查询
    @GetMapping("/findById/{id}")
    public Branch findById(@PathVariable("id") String id){
        return branchService.getById(id);
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Branch branch){
        return branchService.updateById(branch)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //删除
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return branchService.removeById(id)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //查询全部
    @GetMapping("/all")
    public List<Branch> findAll(){
        return branchService.list();
    }
}

