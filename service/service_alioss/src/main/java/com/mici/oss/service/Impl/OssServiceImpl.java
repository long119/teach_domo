package com.mici.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mici.oss.service.OssService;
import com.mici.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String assessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        System.out.println("endpoint"+endpoint);
        System.out.println("keyId"+accessKeyId);
        System.out.println("keySecret"+assessKeySecret);
        System.out.println("bucketName"+bucketName);

        OSS oss = null;
        String url = null;
        try {
            //创建oss实例
            oss = new OSSClientBuilder().build(endpoint, accessKeyId, assessKeySecret);
            //上传文件流
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName = dataPath + "/" + uuid + fileName;

            oss.putObject(bucketName,fileName,inputStream);

            url = "https://"+bucketName+"."+endpoint+"/"+fileName;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            oss.shutdown();
        }

        return url;
    }
}
