package com.qingcheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.qingcheng.framework.domain.goods") //扫描实体类
@ComponentScan(basePackages={"com.qingcheng.manage_good"})
@ComponentScan(basePackages={"com.qingcheng.framework"}) //扫描common下的所有类
@MapperScan(basePackages = {"com.qingcheng.manage_good.dao"}) //扫描通用mapper
public class ManagerGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerGoodsApplication.class,args);
    }

    @Bean
    @LoadBalanced //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
