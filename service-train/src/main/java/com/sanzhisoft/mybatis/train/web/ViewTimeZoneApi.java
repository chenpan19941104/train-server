package com.sanzhisoft.mybatis.train.web;

import com.sanzhisoft.base.mybatis.jersey.IResponse;
import com.sanzhisoft.base.mybatis.jersey.auth.ISecurityContext;
import com.sanzhisoft.mybatis.train.constants.ApiConstants;
import com.sanzhisoft.mybatis.train.entity.ViewPpTimezone;
import com.sanzhisoft.mybatis.train.service.IViewPpTimezoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * @author cp
 * @since  2021-06-11 15:10
 */
@Path("timezone")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Api(value = ApiConstants.VIEW_TIME_ZONE, tags = {ApiConstants.VIEW_TIME_ZONE})
public class ViewTimeZoneApi implements ISecurityContext, IResponse {
    @Autowired
    private IViewPpTimezoneService viewPpTimezoneService;

    @GET
    @Path("")
    @ApiOperation(value = "获取区域信息", tags = {ApiConstants.ADMIN,ApiConstants.VIEW_TIME_ZONE}, response = Map.class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTimeZone() {
        return ok(viewPpTimezoneService.getTimeZone());
    }
}
