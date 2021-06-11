package com.sanzhisoft.mybatis.train.service;

import com.sanzhisoft.mybatis.train.entity.CarmeraInfo;
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
public interface ICarmeraInfoService extends IService<CarmeraInfo> {

    /**
     *  根据id获取关联车次的摄像头的url
     * @param id id
     */
    List<String> getCarmeraUrlWithLinkTarin(Integer id);
}
