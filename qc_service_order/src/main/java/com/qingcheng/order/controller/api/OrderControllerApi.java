package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.Order;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "order接口", description = "提供order查询及管理", tags = {"order接口"})
public interface OrderControllerApi{


	@ApiOperation("查询所有")
    List<Order> findAll();

	@ApiOperation("根据id查询")
     Order findOrderById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addOrder(Order order) ;
	
	@ApiOperation("修改")
     ResponseResult updateOrder(Order order);

	@ApiOperation("删除")
     ResponseResult deleteOrder(String id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Order> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Order> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Order> findPageList(Integer page, Integer size, Map searchMap);

}
