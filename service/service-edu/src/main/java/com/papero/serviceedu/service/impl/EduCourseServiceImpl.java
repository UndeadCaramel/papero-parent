package com.papero.serviceedu.service.impl;

import com.papero.servicebase.exceptionhandler.CustomException;
import com.papero.serviceedu.entity.EduCourse;
import com.papero.serviceedu.entity.EduCourseDescription;
import com.papero.serviceedu.entity.vo.CourseInfoVo;
import com.papero.serviceedu.mapper.EduCourseMapper;
import com.papero.serviceedu.service.EduCourseDescriptionService;
import com.papero.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author paper
 * @since 2021-08-29
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,courseInfoVo);
        int insert=baseMapper.insert(eduCourse);
        if(insert<=0){
            throw new CustomException(20001,"添加课程信息失败");
        }
        String cid=eduCourse.getId();
        EduCourseDescription  courseDescription=new EduCourseDescription();
        courseDescription.setId(cid);
        courseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(courseDescription);

        return cid;

    }
}
