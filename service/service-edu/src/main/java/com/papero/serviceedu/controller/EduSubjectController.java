package com.papero.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.papero.commonutils.R;
import com.papero.serviceedu.entity.EduSubject;
import com.papero.serviceedu.entity.vo.SubjectQuery;
import com.papero.serviceedu.entity.vo.SubjectTree;
import com.papero.serviceedu.service.EduSubjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author paper
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping(value="addSubject", headers = "content-type=multipart/form-data")
    public R addSubject(@RequestPart("file") MultipartFile multipartFile){
        eduSubjectService.saveSubjects(multipartFile);
        return R.ok();
    }

    @GetMapping(value="querySubject")
    public R querySubject(){
        List<SubjectTree> list=eduSubjectService.querySubjectTree();
        return R.ok().data("list",list);
    }

}

