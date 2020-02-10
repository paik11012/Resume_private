package com.ssafy.web9to6;

import com.ssafy.web9to6.controller.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@EnableJpaAuditing
@SpringBootApplication
public class Application implements WebMvcConfigurer {
//public class Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/resume/**")
                .addPathPatterns("/interview/**")
                .addPathPatterns("/careers/**")
                .addPathPatterns("/edu/**")
                .addPathPatterns("/exp/**")
                .addPathPatterns("/lic/**")
                .addPathPatterns("/awards/**")
//                .addPathPatterns("/")
               // .addPathPatterns("/hello")
         //       .addPathPatterns("/interview")
       //         .addPathPatterns("/resume")
                .excludePathPatterns(Arrays.asList("/users/**"));
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(("/**"))
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .exposedHeaders("jwt-auth-token");
    }
}
