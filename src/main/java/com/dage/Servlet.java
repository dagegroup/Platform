package com.dage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @className:Servlet
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2019-01-16 10:24
 */
public class Servlet extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootMain.class);
    }
}
