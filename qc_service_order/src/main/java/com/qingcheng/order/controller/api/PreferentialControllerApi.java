package com.qingcheng.order.controller.api;


import com.qingcheng.framework.domain.order.Preferential;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "preferential接口", description = "提供preferential查询及管理", tags = {"preferential接口"})
public interface PreferentialControllerApi{


	@ApiOperation("查询所有")
     List<Preferential> findAll();

	@ApiOperation("根据id查询")
     Preferential findPreferentialById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addPreferential(Preferential preferential) ;
	
	@ApiOperation("修改")
     ResponseResult updatePreferential(Preferential preferential);

	@ApiOperation("删除")
     ResponseResult deletePreferential(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Preferential> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Preferential> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Preferential> findPageList(Integer page, Integer size, Map searchMap);

}
