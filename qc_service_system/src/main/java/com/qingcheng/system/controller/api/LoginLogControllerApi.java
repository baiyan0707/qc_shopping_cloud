package com.qingcheng.system.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.system.LoginLog;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "loginLog接口", description = "提供loginLog查询及管理", tags = {"loginLog接口"})
public interface LoginLogControllerApi{


	@ApiOperation("查询所有")
     List<LoginLog> findAll();

	@ApiOperation("根据id查询")
     LoginLog findLoginLogById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addLoginLog(LoginLog loginLog) ;
	
	@ApiOperation("修改")
     ResponseResult updateLoginLog(LoginLog loginLog);

	@ApiOperation("删除")
     ResponseResult deleteLoginLog(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<LoginLog> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<LoginLog> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<LoginLog> findPageList(Integer page, Integer size, Map searchMap);

}
