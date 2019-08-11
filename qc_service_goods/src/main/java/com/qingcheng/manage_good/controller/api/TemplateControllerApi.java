package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Template;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "template接口", description = "提供template查询及管理", tags = {"template接口"})
public interface TemplateControllerApi{


	@ApiOperation("查询所有")
     List<Template> findAll();

	@ApiOperation("根据id查询")
     Template findTemplateById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addTemplate(Template template) ;
	
	@ApiOperation("修改")
     ResponseResult updateTemplate(Template template);

	@ApiOperation("删除")
     ResponseResult deleteTemplate(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Template> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Template> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Template> findPageList(Integer page, Integer size, Map searchMap);

}
