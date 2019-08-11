package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.ReturnOrderItem;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "returnOrderItem接口", description = "提供returnOrderItem查询及管理", tags = {"returnOrderItem接口"})
public interface ReturnOrderItemControllerApi{


	@ApiOperation("查询所有")
     List<ReturnOrderItem> findAll();

	@ApiOperation("根据id查询")
     ReturnOrderItem findReturnOrderItemById(Long id) ;
	
	@ApiOperation("添加")
     ResponseResult addReturnOrderItem(ReturnOrderItem returnOrderItem) ;
	
	@ApiOperation("修改")
     ResponseResult updateReturnOrderItem(ReturnOrderItem returnOrderItem);

	@ApiOperation("删除")
     ResponseResult deleteReturnOrderItem(Long id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<ReturnOrderItem> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<ReturnOrderItem> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<ReturnOrderItem> findPageList(Integer page, Integer size, Map searchMap);

}
