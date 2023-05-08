package com.zl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.entity.Branch;
import com.zl.entity.Dept;
import com.zl.entity.Emp;
import com.zl.entity.User;
import com.zl.service.BranchService;
import com.zl.service.DeptService;
import com.zl.service.EmpService;
import com.zl.service.UserService;
import com.zl.sys.entity.UserRole;
import com.zl.sys.service.UserRoleService;
import com.zl.vo.Result;
import com.zl.vo.ResultMessage;
import com.zl.vo.SearchVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RequestMapping("/emp")
public class EmpController {
    @Resource
    private EmpService empService;
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private BranchService branchService;

    //分页列表查询
    @PostMapping("/findPage")
    public IPage<Emp> findPage(@RequestBody SearchVo searchVo){
        return empService.findPage(searchVo);
    }

    //添加
    @PostMapping("/save")
    public Result saveTown(@RequestBody Emp emp){
        //一级部门id
        emp.setDid(Integer.parseInt(emp.getDeptIdList().get(0)));
        //二级部门id
        emp.setBid(Integer.parseInt(emp.getDeptIdList().get(1)));

        //岗位
        Branch dbBranch = branchService.getById(emp.getBid());
        emp.setPost(dbBranch.getPostname());

        if (StringUtils.isNotEmpty(emp.getBirthday())){
            emp.setBirthday(emp.getBirthday().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getCheckDate())){
            emp.setCheckDate(emp.getCheckDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getBecomeDate())){
            emp.setBecomeDate(emp.getBecomeDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getBeginDate())){
            emp.setBeginDate(emp.getBeginDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getEndDate())){
            emp.setEndDate(emp.getEndDate().split("T")[0]);
        }
        boolean save = empService.save(emp);

        User user = new User();
        user.setName(emp.getId()+"");
        user.setPassword(DigestUtils.md5Hex("123123"));
        user.setStatus("ACTIVE");
        userService.save(user);

        emp.setUid(user.getId());
        empService.updateById(emp);

        UserRole userRole = new UserRole();
        userRole.setUid(user.getId());
        userRole.setRoleId(3);
        userRoleService.save(userRole);

        return save?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //通过id查询
    @GetMapping("/findById/{id}")
    public Emp findById(@PathVariable("id") String id){
        return empService.getById(id);
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Emp emp){
        //一级部门id
        emp.setDid(Integer.parseInt(emp.getDeptIdList().get(0)));
        //二级部门id
        emp.setBid(Integer.parseInt(emp.getDeptIdList().get(1)));

        //岗位
        Branch dbBranch = branchService.getById(emp.getBid());
        emp.setPost(dbBranch.getPostname());

        if (StringUtils.isNotEmpty(emp.getBirthday())){
            emp.setBirthday(emp.getBirthday().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getCheckDate())){
            emp.setCheckDate(emp.getCheckDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getBecomeDate())){
            emp.setBecomeDate(emp.getBecomeDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getBeginDate())){
            emp.setBeginDate(emp.getBeginDate().split("T")[0]);
        }
        if (StringUtils.isNotEmpty(emp.getEndDate())){
            emp.setEndDate(emp.getEndDate().split("T")[0]);
        }
        return empService.updateById(emp)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //删除
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return empService.removeById(id)?Result.ok().message(ResultMessage.SUCCESSMSG):Result.error().message(ResultMessage.ERRORMSG);
    }

    //查询全部
    @GetMapping("/all")
    public List<Emp> findAll(){
        return empService.list();
    }
}

