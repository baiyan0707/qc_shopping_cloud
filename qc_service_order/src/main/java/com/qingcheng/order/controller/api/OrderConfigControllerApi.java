package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.OrderConfig;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "orderConfig接口", description = "提供orderConfig查询及管理", tags = {"orderConfig接口"})
public interface OrderConfigControllerApi{


	@ApiOperation("查询所有")
     List<OrderConfig> findAll();

	@ApiOperation("根据id查询")
     OrderConfig findOrderConfigById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addOrderConfig(OrderConfig orderConfig) ;
	
	@ApiOperation("修改")
     ResponseResult updateOrderConfig(OrderConfig orderConfig);

	@ApiOperation("删除")
     ResponseResult deleteOrderConfig(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<OrderConfig> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<OrderConfig> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<OrderConfig> findPageList(Integer page, Integer size, Map searchMap);

}
