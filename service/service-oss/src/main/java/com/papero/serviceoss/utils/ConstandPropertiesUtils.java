package com.papero.serviceoss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstandPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;

    // 定义公开静态常量1
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        ACCESS_KEY_ID=accessKeyId;
        ACCESS_KEY_SECRET=accessKeySecret;
        BUCKET_NAME=bucketName;
    }
}
