package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.ReturnCause;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "returnCause接口", description = "提供returnCause查询及管理", tags = {"returnCause接口"})
public interface ReturnCauseControllerApi{


	@ApiOperation("查询所有")
     List<ReturnCause> findAll();

	@ApiOperation("根据id查询")
     ReturnCause findReturnCauseById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addReturnCause(ReturnCause returnCause) ;
	
	@ApiOperation("修改")
     ResponseResult updateReturnCause(ReturnCause returnCause);

	@ApiOperation("删除")
     ResponseResult deleteReturnCause(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<ReturnCause> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<ReturnCause> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<ReturnCause> findPageList(Integer page, Integer size, Map searchMap);

}
