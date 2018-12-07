package com.dage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className:SpringBootMain
 * @discription:springboot启动类
 * @author:ProMonkey-K
 * @creatTime:2018-11-21 09:31
 */
@SpringBootApplication
@MapperScan("com.dage.dao") //扫描dao层接口
public class SpringBootMain {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication.run(SpringBootMain.class);
    }
}
