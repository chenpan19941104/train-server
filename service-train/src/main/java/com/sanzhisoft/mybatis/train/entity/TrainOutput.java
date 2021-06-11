package com.sanzhisoft.mybatis.train.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
@EqualsAndHashCode(callSuper = true)
@TableName("TrainOutput")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("车次出行信息表")
public class TrainOutput extends Model<TrainOutput> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    @ApiModelProperty("编号")
    private Integer id;

    @TableField("TrainName")
    @ApiModelProperty("车次名称")
    private String trainName;

    @TableField("LoadStation")
    @ApiModelProperty("装载车站")
    private String loadStation;

    @TableField("EndLoadTime")
    @ApiModelProperty("装载的结束时间")
    private String endLoadTime;

    @TableField("DumpStation")
    @ApiModelProperty("卸载车站")
    private String dumpStation;

    @TableField("EndDumpTime")
    @ApiModelProperty("卸载的结束时间")
    private String endDumpTime;

    @Override
    public String toString() {
        return "TrainOutput{" +
                "id=" + id +
                ", TrainName=" + trainName +
                ", LoadStation=" + loadStation +
                ", EndLoadTime=" + endLoadTime +
                ", DumpStation=" + dumpStation +
                ", EndDumpTime=" + endDumpTime +
                "}";
    }
}
