package com.papero.serviceedu.controller;


import com.papero.commonutils.R;
import com.papero.serviceedu.service.EduSubjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author paper
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping(value="addSubject", headers = "content-type=multipart/form-data")
    public R addSubject(@RequestPart MultipartFile multipartFile){
        eduSubjectService.saveSubjects(multipartFile);
        return R.ok();
    }

}

