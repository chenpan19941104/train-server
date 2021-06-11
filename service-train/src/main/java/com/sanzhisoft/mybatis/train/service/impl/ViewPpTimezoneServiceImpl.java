package com.sanzhisoft.mybatis.train.service.impl;

import com.sanzhisoft.mybatis.train.entity.ViewPpTimezone;
import com.sanzhisoft.mybatis.train.mapper.ViewPpTimezoneMapper;
import com.sanzhisoft.mybatis.train.service.IViewPpTimezoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
@Service
public class ViewPpTimezoneServiceImpl extends ServiceImpl<ViewPpTimezoneMapper, ViewPpTimezone> implements IViewPpTimezoneService {

    @Override
    public Map<String, Integer> getTimeZone() {
        return null;
    }
}
