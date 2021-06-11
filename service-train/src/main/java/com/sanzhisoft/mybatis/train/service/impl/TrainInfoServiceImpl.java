package com.sanzhisoft.mybatis.train.service.impl;

import com.sanzhisoft.mybatis.train.entity.TrainInfo;
import com.sanzhisoft.mybatis.train.mapper.TrainInfoMapper;
import com.sanzhisoft.mybatis.train.service.ITrainInfoService;
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
public class TrainInfoServiceImpl extends ServiceImpl<TrainInfoMapper, TrainInfo> implements ITrainInfoService {

    @Override
    public List<TrainInfo> getAll() {
        return null;
    }

    @Override
    public TrainInfo getTrainInfoByID(Integer id) {
        return null;
    }
}
