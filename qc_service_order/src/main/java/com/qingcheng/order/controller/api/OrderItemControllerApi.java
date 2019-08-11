package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.OrderItem;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "orderItem接口", description = "提供orderItem查询及管理", tags = {"orderItem接口"})
public interface OrderItemControllerApi{


	@ApiOperation("查询所有")
     List<OrderItem> findAll();

	@ApiOperation("根据id查询")
     OrderItem findOrderItemById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addOrderItem(OrderItem orderItem) ;
	
	@ApiOperation("修改")
     ResponseResult updateOrderItem(OrderItem orderItem);

	@ApiOperation("删除")
     ResponseResult deleteOrderItem(String id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<OrderItem> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<OrderItem> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<OrderItem> findPageList(Integer page, Integer size, Map searchMap);

}
