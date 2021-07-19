package com.papero.serviceedu.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectQuery {

    @ApiModelProperty(value = "课程分类名称,模糊查询")
    private String title;
}
