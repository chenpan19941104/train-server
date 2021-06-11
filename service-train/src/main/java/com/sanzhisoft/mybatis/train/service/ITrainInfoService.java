package com.sanzhisoft.mybatis.train.service;

import com.sanzhisoft.mybatis.train.entity.TrainInfo;
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
public interface ITrainInfoService extends IService<TrainInfo> {

    /**
     * 获取所有的车次信息
     */
    List<TrainInfo> getAll();

    /**
     * 通过id获取车次信息
     * @param id id
     */
    TrainInfo getTrainInfoByID(Integer id);
}
