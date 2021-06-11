package com.sanzhisoft.mybatis.train.service.impl;

import com.sanzhisoft.mybatis.train.dto.TrainOutPutCountRes;
import com.sanzhisoft.mybatis.train.entity.TrainOutput;
import com.sanzhisoft.mybatis.train.mapper.TrainOutputMapper;
import com.sanzhisoft.mybatis.train.service.ITrainOutputService;
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
public class TrainOutputServiceImpl extends ServiceImpl<TrainOutputMapper, TrainOutput> implements ITrainOutputService {

    @Override
    public List<TrainOutPutCountRes> getTrainOutPutCountBuTrainName(String trainName) {
        return null;
    }
}
