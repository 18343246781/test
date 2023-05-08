package com.zl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author XW.Fan
 * @since 2023-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Emp对象", description="")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "一级部门id")
    private Integer did;

    @ApiModelProperty(value = "部门id")
    private Integer bid;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "婚姻")
    private String marriage;

    @ApiModelProperty(value = "籍贯")
    private String natives;

    @ApiModelProperty(value = "身高")
    private String cm;

    @ApiModelProperty(value = "体重")
    private String kg;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "政治面貌")
    private String face;

    @ApiModelProperty(value = "健康状态")
    private String healthy;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "邮编")
    private String postalCode;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "保险")
    private String insure;

    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "报到日期")
    private String checkDate;

    @ApiModelProperty(value = "在职状态")
    private String jobStatus;

    @ApiModelProperty(value = "转正日期")
    private String becomeDate;

    @ApiModelProperty(value = "试用期工资")
    private Integer trialSalary;

    @ApiModelProperty(value = "转正后工资")
    private Integer becomeSalaty;

    @ApiModelProperty(value = "银行卡号")
    private Integer bankNum;

    @ApiModelProperty(value = "试用类型")
    private String trialCate;

    @ApiModelProperty(value = "劳动合同")
    private String contract;

    @ApiModelProperty(value = "试用期（月）")
    private Integer trialNum;

    @ApiModelProperty(value = "签订合同日期")
    private String beginDate;

    @ApiModelProperty(value = "合同结束日期")
    private String endDate;

    @ApiModelProperty(value = "薪制")
    private String salarySystem;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "毕业学校")
    private String graduation;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "申请离职日期")
    private String applyResignation;

    @ApiModelProperty(value = "离职日期")
    private String resignation;

    @ApiModelProperty(value = "离职申请")
    private String resignationRemark;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "账号id")
    private Integer uid;

    @ApiModelProperty(value = "工资类型id")
    private Integer sid;

    @TableField(exist = false)
    private List<String> deptIdList=new ArrayList<>();
}
