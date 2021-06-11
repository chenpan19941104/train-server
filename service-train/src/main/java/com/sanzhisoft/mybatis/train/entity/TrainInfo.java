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
@TableName("TRAIN_INFO")
@ApiModel("车次信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainInfo extends Model<TrainInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("NAME")
    @ApiModelProperty("车次名")
    private String name;

    @TableField("TYPE")
    @ApiModelProperty("车次类型")
    private Integer type;

    @Override
    public String toString() {
        return "TrainInfo{" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                "}";
    }
}
