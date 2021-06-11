package com.sanzhisoft.train.application;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sanzhisoft.base.mybatis.spring.BaseConfiguration;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@Import({BaseConfiguration.class, JedisPool.class})
public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    //swagger配置
    @Value("${swagger.domain}")
    String swaggerDomain;
    @Value("${server.port}")
    String swaggerPort;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }


    /**
     * Mas的短信定时任务暂时先注释
     */
//    @Bean
//    public SmsMasSchedule SmsMasSchedule() {
//        return new SmsMasSchedule();
//    }

    /*
    跨域配置,可以使用相关域名代替* ,以提高安全性
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    /*
    编码过滤器
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        return characterEncodingFilter;
    }

    // EventBus 相关配置  开始

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBusRegisterEvents(eventBus);
        return eventBus;
    }

    @Bean
    public AsyncEventBus asyncEventBus() {
        Executor executor = Executors.newFixedThreadPool(3);
        AsyncEventBus asyncEventBus = new AsyncEventBus(executor);
        eventBusRegisterEvents(asyncEventBus);
        return asyncEventBus;
    }

    private void eventBusRegisterEvents(EventBus eventBus) {
        //在这里注册所有的事件.注册完就可以用同步或者异步发送事件.
        //或者在Subscriber的添加构造完毕后，注册.
        eventBus.register(this);//处理 deadEvent
    }

    @Subscribe
    public void onDeadEvent(DeadEvent event) {
        log.error("EventBus缺少register,接收到DeadEvent: 发布者:" + event.getSource() + " 事件:"
                + JSON.toJSONString(event.getEvent()));
    }
    // EventBus 相关配置  结束

    @PostConstruct
    public void configSwagger2() {
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setBasePath("");
        swaggerConfig.setDescription("swagger自动生成接口");
        swaggerConfig.setHost(swaggerDomain + ":" + swaggerPort + "/api");
        swaggerConfig.setSchemes(new String[]{"http"});

        Info info = new Info();
        info.setTitle("swagger-jaxrs自动生成接口");
        info.setDescription("服务接口规范");
        info.setVersion("1.0.0");

        Contact contact = new Contact();
        contact.setName("三智科技");
        contact.setUrl("http://www.sanzhisoft.com/");
        info.setContact(contact);
        info.setVendorExtension("token", "1111");
        swaggerConfig.setInfo(info);
        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);
    }

}
