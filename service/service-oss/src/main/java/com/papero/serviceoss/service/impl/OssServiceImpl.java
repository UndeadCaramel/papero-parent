package com.papero.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.papero.serviceoss.service.OssService;
import com.papero.serviceoss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        OSS ossClient =null;
        try {
            // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = ConstandPropertiesUtils.END_POINT;
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
            String bucketName = ConstandPropertiesUtils.BUCKET_NAME;

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();

            String uuid= UUID.randomUUID().toString().replace("-","");
            String fileName=uuid+file.getOriginalFilename();

            String datePath=new DateTime().toString("yyyy/MM/dd");
            fileName=datePath+fileName;


            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName,fileName, inputStream);

            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            if(ossClient!=null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
