package com.zyg.user;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@SpringBootApplication
@EnableFeignClients
@EnableCasClient
public class ZygUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygUserApplication.class);
    }
}
