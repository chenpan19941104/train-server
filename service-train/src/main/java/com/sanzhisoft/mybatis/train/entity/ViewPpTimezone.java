package com.sanzhisoft.mybatis.train.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author syetem
 * @since 2021-06-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("区域信息")
public class ViewPpTimezone extends Model<ViewPpTimezone> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("区域id")
    private Integer zoneid;

    @ApiModelProperty("区域名")
    private String zonename;

    @Override
    public String toString() {
        return "ViewPpTimezone{" +
        "zoneid=" + zoneid +
        ", zonename=" + zonename +
        "}";
    }
}
