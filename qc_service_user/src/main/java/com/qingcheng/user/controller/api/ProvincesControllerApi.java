package com.qingcheng.user.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.user.Provinces;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "provinces接口", description = "提供provinces查询及管理", tags = {"provinces接口"})
public interface ProvincesControllerApi{


	@ApiOperation("查询所有")
     List<Provinces> findAll();

	@ApiOperation("根据id查询")
     Provinces findProvincesById(String provinceid) ;
	
	@ApiOperation("添加")
     ResponseResult addProvinces(Provinces provinces) ;
	
	@ApiOperation("修改")
     ResponseResult updateProvinces(Provinces provinces);

	@ApiOperation("删除")
     ResponseResult deleteProvinces(String provinceid) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Provinces> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Provinces> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Provinces> findPageList(Integer page, Integer size, Map searchMap);

}
