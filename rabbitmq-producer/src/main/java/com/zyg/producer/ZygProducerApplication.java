package com.zyg.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZygProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygProducerApplication.class);
    }
}
