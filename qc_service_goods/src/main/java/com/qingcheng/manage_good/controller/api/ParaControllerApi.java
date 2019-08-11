package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Para;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "para接口", description = "提供para查询及管理", tags = {"para接口"})
public interface ParaControllerApi{


	@ApiOperation("查询所有")
     List<Para> findAll();

	@ApiOperation("根据id查询")
     Para findParaById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addPara(Para para) ;
	
	@ApiOperation("修改")
     ResponseResult updatePara(Para para);

	@ApiOperation("删除")
     ResponseResult deletePara(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Para> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Para> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Para> findPageList(Integer page, Integer size, Map searchMap);

}
