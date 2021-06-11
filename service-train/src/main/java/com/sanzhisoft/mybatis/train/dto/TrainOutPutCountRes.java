package com.sanzhisoft.mybatis.train.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cp
 * @since 2021-06-11 15:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("列车的7天内轮班次数的res")
public class TrainOutPutCountRes {
    @ApiModelProperty("卸载结束时间")
    private String endDumpTime;
    @ApiModelProperty("次数")
    private Integer count;
    @ApiModelProperty("类型")
    private String type;
}
