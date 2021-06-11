package com.sanzhisoft.mybatis.train.web;

import com.sanzhisoft.base.mybatis.jersey.IResponse;
import com.sanzhisoft.base.mybatis.jersey.auth.ISecurityContext;
import com.sanzhisoft.mybatis.train.constants.ApiConstants;
import com.sanzhisoft.mybatis.train.entity.TrainInfo;
import com.sanzhisoft.mybatis.train.service.ITrainInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author cp
 * @since 2021-06-11 15:10
 */
@Path("train/info")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Api(value = ApiConstants.TRAIN_INFO, tags = {ApiConstants.TRAIN_INFO})
public class TrainInfoApi implements ISecurityContext, IResponse {
    @Autowired
    private ITrainInfoService trainInfoService;
    @GET
    @Path("all")
    @ApiOperation(value = "获得所有的车次信息", tags = {ApiConstants.ADMIN,ApiConstants.TRAIN_INFO}, response = TrainInfo.class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return ok(trainInfoService.getAll());
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "根据id获取车次信息", tags = {ApiConstants.ADMIN,ApiConstants.TRAIN_INFO}, response = TrainInfo.class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTrainInfoByID(@ApiParam(value = "id", required = true) @PathParam("id") Integer id) {
        return ok(trainInfoService.getTrainInfoByID(id));
    }
}
