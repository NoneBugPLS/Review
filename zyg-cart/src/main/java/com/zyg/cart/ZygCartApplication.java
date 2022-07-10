package com.zyg.cart;

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
@EnableCasClient
@EnableFeignClients
public class ZygCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygCartApplication.class);
    }
}
