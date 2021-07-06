package com.papero.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.papero.serviceedu.entity.EduSubject;
import com.papero.serviceedu.entity.excel.SubjectData;
import com.papero.serviceedu.listener.SubjectExcelListener;
import com.papero.serviceedu.mapper.EduSubjectMapper;
import com.papero.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author paper
 * @since 2021-07-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void saveSubjects(MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(), SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
