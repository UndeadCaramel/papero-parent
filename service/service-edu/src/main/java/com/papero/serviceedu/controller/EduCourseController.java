package com.papero.serviceedu.controller;


import com.papero.commonutils.R;
import com.papero.serviceedu.entity.EduCourse;
import com.papero.serviceedu.entity.vo.CourseInfoVo;
import com.papero.serviceedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author paper
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

     @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
         String id=eduCourseService.saveCourseInfo(courseInfoVo);
         return R.ok().data("courseId",id);
     }

}

