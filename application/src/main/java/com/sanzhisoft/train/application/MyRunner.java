package com.sanzhisoft.train.application;

import com.sanzhisoft.base.mybatis.exception.AppBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    //swagger相关配置
    @Value("${swagger.rootUrl}")
    String rootUrl;
    @Value("${server.port}")
    int port;
    @Value("${swagger.openUrl}")
    boolean openUrl;
    @Value("${swagger.googleExecute}")
    String googleExecute;

    @Value("${common.env}")
    String env;


    @Override
    public void run(String... args) throws Exception {
        try {
            logger.info("加载" + env + "配置文件完成！");
            logger.info("服务启动成功！");
            openUrl();
        } catch (AppBusinessException ex) {
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }


    private void openUrl() {
        if (!openUrl) return;
        try {
            String url = rootUrl + ":" + port + "/swagger/index.html";
            File file = new File(googleExecute);
            if (file.exists()) {
                String cmd = googleExecute + " " + url;
                Runtime run = Runtime.getRuntime();
                run.exec(cmd);
            } else {
                Runtime.getRuntime().exec("cmd /c start " + url);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
