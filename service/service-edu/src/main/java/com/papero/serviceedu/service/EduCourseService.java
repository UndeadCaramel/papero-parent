package com.papero.serviceedu.service;

import com.papero.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.papero.serviceedu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author paper
 * @since 2021-08-29
 */
public interface EduCourseService extends IService<EduCourse> {
    public String saveCourseInfo(CourseInfoVo courseInfoVo);
}
