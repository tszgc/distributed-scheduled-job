package org.nina.dsj.manage;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述：定时任务管理器主启动类
 * 作者：zgc
 * 时间：2022/6/24 15:56
 */
@RetrofitScan(basePackages = "org.nina")
@MapperScan(basePackages = "org.nina.dsj.mapper")
@ComponentScan(basePackages = {"org.nina"})
@SpringBootApplication
public class DsjApp {

    public static void main(String[] args) {
        SpringApplication.run(DsjApp.class, args);
    }

}
