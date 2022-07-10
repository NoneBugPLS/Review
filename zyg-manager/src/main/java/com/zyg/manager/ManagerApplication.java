package com.zyg.manager;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCasClient
public class ManagerApplication {
    public static void main(String[] args) {
            SpringApplication.run(ManagerApplication.class);
    }
}
