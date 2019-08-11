package com.qingcheng.order.controller.api;



import com.qingcheng.framework.domain.order.OrderLog;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "orderLog接口", description = "提供orderLog查询及管理", tags = {"orderLog接口"})
public interface OrderLogControllerApi{


	@ApiOperation("查询所有")
     List<OrderLog> findAll();

	@ApiOperation("根据id查询")
     OrderLog findOrderLogById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addOrderLog(OrderLog orderLog) ;
	
	@ApiOperation("修改")
     ResponseResult updateOrderLog(OrderLog orderLog);

	@ApiOperation("删除")
     ResponseResult deleteOrderLog(String id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<OrderLog> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<OrderLog> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<OrderLog> findPageList(Integer page, Integer size, Map searchMap);

}
