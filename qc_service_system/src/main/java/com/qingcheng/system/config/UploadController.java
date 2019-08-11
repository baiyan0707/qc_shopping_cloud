package com.qingcheng.system.config;


import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UploadController {

    @Value("${aliyun-oss.endpoint}")
    String endpoint;

    @Value("${aliyun-oss.accessKeyId}")
    String access_id;

    @Value("${aliyun-oss.accessKeySecret}")
    String access_key;

    @Bean
    public OSSClient getOssClient(){
        return new OSSClient(endpoint,access_id,access_key);
    }
}
