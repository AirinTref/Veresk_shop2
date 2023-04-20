package com.example.veresk_shop.config;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.context.annotation.ComponentScan;



@Configuration
//@ComponentScan("com.example.veresk_shop.repositories")
public class Config implements WebMvcConfigurer {

    @Value("${upload.path}")
//=путь
    private  String uploadPath;

//метод, который позволит раздавать статические ресурсы

    @Override
    public  void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("file:///" +uploadPath +"/");
    }
}
