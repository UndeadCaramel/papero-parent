package com.papero.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {
        //写操作
        String filename="F:\\Learn\\write.xlsx";
//        List<DemoData> list=getData();
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(list);
//        读操作
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("Lucy_"+i);
            list.add(data);
        }
        return list;
    }
}
