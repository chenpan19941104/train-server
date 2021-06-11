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
@TableName("CARMERA_INFO")
@ApiModel("摄像头信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarmeraInfo extends Model<CarmeraInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    @ApiModelProperty("编号")
    private Integer id;

    @TableField("LINK_TRAIN")
    @ApiModelProperty("关联的车次")
    private Integer linkTrain;

    @TableField("NAME")
    @ApiModelProperty("姓名")
    private String name;

    @TableField("IP")
    @ApiModelProperty("ip")
    private String ip;

    @TableField("PORT")
    @ApiModelProperty("端口号")
    private Integer port;

    @TableField("USER_NAME")
    @ApiModelProperty("用户名")
    private String userName;

    @TableField("PASSWORD")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 0：主码流 1：子码流
     */
    @TableField("TYPE")
    @ApiModelProperty("信息类型")
    private Integer type;

    @Override
    public String toString() {
        return "CarmeraInfo{" +
                "id=" + id +
                ", linkTrain=" + linkTrain +
                ", name=" + name +
                ", ip=" + ip +
                ", port=" + port +
                ", userName=" + userName +
                ", password=" + password +
                ", type=" + type +
                "}";
    }
}
