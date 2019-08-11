package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Spec;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "spec接口", description = "提供spec查询及管理", tags = {"spec接口"})
public interface SpecControllerApi{


	@ApiOperation("查询所有")
     List<Spec> findAll();

	@ApiOperation("根据id查询")
     Spec findSpecById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addSpec(Spec spec) ;
	
	@ApiOperation("修改")
     ResponseResult updateSpec(Spec spec);

	@ApiOperation("删除")
     ResponseResult deleteSpec(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Spec> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Spec> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Spec> findPageList(Integer page, Integer size, Map searchMap);

}
