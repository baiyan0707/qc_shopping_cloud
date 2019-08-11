package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.ReturnOrder;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "returnOrder接口", description = "提供returnOrder查询及管理", tags = {"returnOrder接口"})
public interface ReturnOrderControllerApi{


	@ApiOperation("查询所有")
     List<ReturnOrder> findAll();

	@ApiOperation("根据id查询")
     ReturnOrder findReturnOrderById(Long id) ;
	
	@ApiOperation("添加")
     ResponseResult addReturnOrder(ReturnOrder returnOrder) ;
	
	@ApiOperation("修改")
     ResponseResult updateReturnOrder(ReturnOrder returnOrder);

	@ApiOperation("删除")
     ResponseResult deleteReturnOrder(Long id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<ReturnOrder> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<ReturnOrder> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<ReturnOrder> findPageList(Integer page, Integer size, Map searchMap);

}
