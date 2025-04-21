package com.ccll.common.config;

import com.ccll.common.interceptor.JwtTokenUserInterceptor;
import com.ccll.common.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册自定义拦截器");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/course/pageComments")
                .excludePathPatterns("/user/course/pageCourse")
                .excludePathPatterns("/user/course/detail")
                .excludePathPatterns("/user/register");
    }





    /**
     * 通过knife4j生成接口文档
     * @return
     */
    /*@Bean
    public Docket docket01() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("苍穹外卖项目管理端接口文档")
                .version("2.0")
                .description("苍穹外卖项目管理端接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docket02() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("苍穹外卖项目用户端接口文档")
                .version("2.0")
                .description("苍穹外卖项目用户端接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sky.controller.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }*/

    /**
     * 设置静态资源映射
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始静态资源映射");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("开始拓展消息转换器...");

        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，可以将Java对象转换为json字符串
        converter.setObjectMapper(new JacksonObjectMapper());

        //将设置的转换器放入Spring MVC框架的容器中，并设置优先级为0
        converters.add(0,converter);
    }
}
