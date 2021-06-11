package com.sanzhisoft.mybatis.train.service.impl;

import com.sanzhisoft.mybatis.train.entity.CarmeraInfo;
import com.sanzhisoft.mybatis.train.mapper.CarmeraInfoMapper;
import com.sanzhisoft.mybatis.train.service.ICarmeraInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
@Service
public class CarmeraInfoServiceImpl extends ServiceImpl<CarmeraInfoMapper, CarmeraInfo> implements ICarmeraInfoService {

    @Override
    public List<String> getCarmeraUrlWithLinkTarin(Integer id) {
        return null;
    }
}
