package com.sanzhisoft.mybatis.train.web;

import com.sanzhisoft.base.mybatis.jersey.IResponse;
import com.sanzhisoft.base.mybatis.jersey.auth.ISecurityContext;
import com.sanzhisoft.mybatis.train.constants.ApiConstants;
import com.sanzhisoft.mybatis.train.entity.TrainInfo;
import com.sanzhisoft.mybatis.train.service.ICarmeraInfoService;
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
 * @since 2021-06-11 15:09
 */
@Path("carmera/info")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Api(value = ApiConstants.CARMERA_INFO, tags = {ApiConstants.CARMERA_INFO})
public class CarmeraInfoApi implements ISecurityContext, IResponse {
    @Autowired
    private ICarmeraInfoService carmeraInfoService;

    @GET
    @Path("{id}")
    @ApiOperation(value = "根据id获取摄像头的url", tags = {ApiConstants.ADMIN,ApiConstants.CARMERA_INFO}, response = String.class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCarmeraUrlWithLinkTarin(@ApiParam(value = "id", required = true) @PathParam("id") Integer id) {
        return ok(carmeraInfoService.getCarmeraUrlWithLinkTarin(id));
    }
}
