package com.papero.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.papero.servicebase.exceptionhandler.CustomException;
import com.papero.serviceedu.entity.EduSubject;
import com.papero.serviceedu.entity.excel.SubjectData;
import com.papero.serviceedu.service.EduSubjectService;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;
    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap.get(0));
        System.out.println(headMap.get(1));
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new CustomException(2001,"Excel内容为空");
        }
        EduSubject oneSubject=existOneSubject(subjectData);
        if(oneSubject==null){
            oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }
        String pid=oneSubject.getId();
        EduSubject twoSubject=existTwoSubject(subjectData,pid);
        if(twoSubject==null){
            twoSubject=new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(twoSubject);
        }
    }

    private EduSubject existOneSubject(SubjectData subjectData){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("title",subjectData.getOneSubjectName());
        wrapper.eq("parent_id","0");
        return eduSubjectService.getOne(wrapper);
    }

    private EduSubject existTwoSubject(SubjectData subjectData,String pid){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("title",subjectData.getTwoSubjectName());
        wrapper.eq("parent_id",pid);
        return eduSubjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
