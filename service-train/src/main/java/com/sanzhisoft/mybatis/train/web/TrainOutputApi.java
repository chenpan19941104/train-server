package com.sanzhisoft.mybatis.train.web;

import com.sanzhisoft.base.mybatis.jersey.IResponse;
import com.sanzhisoft.base.mybatis.jersey.auth.ISecurityContext;
import com.sanzhisoft.mybatis.train.constants.ApiConstants;
import com.sanzhisoft.mybatis.train.dto.TrainOutPutCountRes;
import com.sanzhisoft.mybatis.train.entity.TrainInfo;
import com.sanzhisoft.mybatis.train.service.ITrainOutputService;
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
 * @since  2021-06-11 15:10
 */
@Path("trainoutput")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Api(value = ApiConstants.TRAIN_OUTPUT, tags = {ApiConstants.TRAIN_OUTPUT})
public class TrainOutputApi implements ISecurityContext, IResponse {
    @Autowired
    private ITrainOutputService trainOutputService;

    @GET
    @Path("{trainName}")
    @ApiOperation(value = "根据车名获取7天白夜班的次数统计", tags = {ApiConstants.ADMIN,ApiConstants.TRAIN_OUTPUT}, response = TrainOutPutCountRes.class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTrainOutPutCountBuTrainName(@ApiParam(value = "trainName", required = true) @PathParam("trainName") String trainName) {
        return ok(trainOutputService.getTrainOutPutCountBuTrainName(trainName));
    }
}
