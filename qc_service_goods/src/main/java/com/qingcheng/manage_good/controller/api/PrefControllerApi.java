package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Pref;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "pref接口", description = "提供pref查询及管理", tags = {"pref接口"})
public interface PrefControllerApi{


	@ApiOperation("查询所有")
     List<Pref> findAll();

	@ApiOperation("根据id查询")
     Pref findPrefById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addPref(Pref pref) ;
	
	@ApiOperation("修改")
     ResponseResult updatePref(Pref pref);

	@ApiOperation("删除")
     ResponseResult deletePref(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Pref> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Pref> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Pref> findPageList(Integer page, Integer size, Map searchMap);

}
