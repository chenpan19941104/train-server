package com.sanzhisoft.mybatis.train.service;

import com.sanzhisoft.mybatis.train.entity.ViewPpTimezone;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
public interface IViewPpTimezoneService extends IService<ViewPpTimezone> {

    /**
     * 查询员工信息统计
     * @return Map
     */
    Map<String,Integer> getTimeZone();
}
