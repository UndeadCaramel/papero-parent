package com.papero.serviceoss.controller;


import com.papero.commonutils.R;
import com.papero.serviceoss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss")
@Api(value="阿里云oss")
public class OssController  {

    @Autowired
    private OssService ossService;

    // 上传头像
    @ApiOperation(value="阿里云oss头像上传")
    @PostMapping("uploadAvatar")
    public R uploadFileAvatar(@RequestPart MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
