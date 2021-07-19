package com.papero.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.papero.serviceedu.entity.EduSubject;
import com.papero.serviceedu.entity.excel.SubjectData;
import com.papero.serviceedu.entity.vo.SubjectTree;
import com.papero.serviceedu.listener.SubjectExcelListener;
import com.papero.serviceedu.mapper.EduSubjectMapper;
import com.papero.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public EduSubjectServiceImpl() {
        super();
    }

    @Override
    public List<SubjectTree> querySubjectTree() {
        List<SubjectTree> list=new ArrayList<SubjectTree>();
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<EduSubject>();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjects=this.list(queryWrapper);
        if(ObjectUtils.isNotEmpty(oneSubjects)&&oneSubjects.size()>0) {
            list=recursionTree(oneSubjects);
        }
        return list;
    }

    private List<SubjectTree> recursionTree(List<EduSubject> subjectlist){
        List<SubjectTree> list=new ArrayList<SubjectTree>();
        for (EduSubject subject : subjectlist) {
            SubjectTree temp = new SubjectTree();
            BeanUtils.copyProperties(subject,temp);
            QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<EduSubject>();
            queryWrapper.eq("parent_id",subject.getId());
            List<EduSubject> nextSubjects = this.list(queryWrapper);
            if (ObjectUtils.isNotEmpty(nextSubjects) && nextSubjects.size() > 0) {
                temp.setChildren(recursionTree(nextSubjects));
            }
            list.add(temp);
        }
        return list;
    }
}
