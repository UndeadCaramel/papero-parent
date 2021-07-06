package com.papero.serviceedu.service;

import com.papero.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author paper
 * @since 2021-07-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubjects(MultipartFile multipartFile);

}
