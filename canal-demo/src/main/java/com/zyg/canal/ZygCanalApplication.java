package com.zyg.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@SpringBootApplication
@EnableCanalClient
public class ZygCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZygCanalApplication.class);
    }
}
