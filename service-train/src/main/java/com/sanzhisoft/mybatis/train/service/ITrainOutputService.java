package com.sanzhisoft.mybatis.train.service;

import com.sanzhisoft.mybatis.train.dto.TrainOutPutCountRes;
import com.sanzhisoft.mybatis.train.entity.TrainOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
public interface ITrainOutputService extends IService<TrainOutput> {

    /**
     * 根据车名获取7天白夜班的数据统计
     * @param trainName 车次名称
     */
    List<TrainOutPutCountRes> getTrainOutPutCountBuTrainName(String trainName);
}
