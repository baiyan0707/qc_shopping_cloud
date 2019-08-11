package com.qingcheng.system.config.controller.api;

import com.qingcheng.framework.domain.config.FreightTemplate;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "freightTemplate接口", description = "提供freightTemplate查询及管理", tags = {"freightTemplate接口"})
public interface FreightTemplateControllerApi{


	@ApiOperation("查询所有")
     List<FreightTemplate> findAll();

	@ApiOperation("根据id查询")
     FreightTemplate findFreightTemplateById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addFreightTemplate(FreightTemplate freightTemplate) ;
	
	@ApiOperation("修改")
     ResponseResult updateFreightTemplate(FreightTemplate freightTemplate);

	@ApiOperation("删除")
     ResponseResult deleteFreightTemplate(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<FreightTemplate> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<FreightTemplate> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<FreightTemplate> findPageList(Integer page, Integer size, Map searchMap);

}
