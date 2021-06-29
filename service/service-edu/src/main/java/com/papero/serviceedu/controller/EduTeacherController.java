package com.papero.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.papero.commonutils.R;
import com.papero.serviceedu.entity.EduTeacher;
import com.papero.serviceedu.entity.vo.TeacherQuery;
import com.papero.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author paper
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
@Api(value  = "讲师接口",tags = "用户操作接口")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //1 查询讲师表所有数据
    //rest风格
    @GetMapping("findAll")
    @ApiOperation(value="获取全部讲师信息",httpMethod = "GET")
    public R query(){
        List<EduTeacher> list=eduTeacherService.list();
        return R.ok().data("list",list);
    }

//2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean flag=eduTeacherService.removeById(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

//3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页") @PathVariable Long current,@ApiParam(name = "limit",value = "每页条数")@PathVariable Long limit){
        Page<EduTeacher> page=new Page<EduTeacher>(current,limit);
        eduTeacherService.page(page);
        long total=page.getTotal();
        List<EduTeacher> records=page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
//4 条件查询带分页的方法
    @ApiOperation(value = "条件查询讲师带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页") @PathVariable Long current, @ApiParam(name = "limit",value = "每页条数")@PathVariable Long limit, @RequestBody TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper();
        if(!ObjectUtils.isEmpty(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());
        }
        if(!ObjectUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if(!ObjectUtils.isEmpty(teacherQuery.getBegin())) {
            wrapper.gt("gmt_create", teacherQuery.getBegin());
        }
        if(!ObjectUtils.isEmpty(teacherQuery.getEnd())) {
            wrapper.le("gmt_create", teacherQuery.getEnd());
        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page,wrapper);
        long total=page.getTotal();
        List<EduTeacher> records=page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
//添加讲师接口的方法
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "eduTeacher",value = "讲师对象",required = true)@RequestBody(required = true) EduTeacher eduTeacher){
        Boolean save=eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }
//根据讲师id进行查询
    @ApiOperation(value = "通过id获取讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable(required = true) String id){
        EduTeacher eduTeacher=eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
//讲师修改功能
    @ApiOperation(value = "编辑讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@ApiParam(name = "eduTeacher",value = "讲师对象",required = true)@RequestBody(required = true) EduTeacher eduTeacher){
        Boolean flag=eduTeacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}

