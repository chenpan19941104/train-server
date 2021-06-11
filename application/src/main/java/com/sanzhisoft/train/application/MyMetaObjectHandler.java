package com.sanzhisoft.train.application;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.SneakyThrows;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName MetaObjectHandler
 * @Description TODO
 * @Author Dxz
 * @Date 2020/12/28 16:50
 * @Version 1.0
 */
@Component
@MapperScan("com.sanzhisoft.mybatis.train.entity")
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Autowired
    HttpServletRequest request;

    @SneakyThrows
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    @SneakyThrows
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.0(推荐)
        //即使前端传updateTime,也会插入最新的updateTime时间
        setFieldValByName("updateTime",LocalDateTime.now(), metaObject);
        //如果前端传updateTime,会插入前端传的updateTime
//      this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
