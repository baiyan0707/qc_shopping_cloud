package com.qingcheng.system.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.system.Admin;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "admin接口", description = "提供admin查询及管理", tags = {"admin接口"})
public interface AdminControllerApi{


	@ApiOperation("查询所有")
     List<Admin> findAll();

	@ApiOperation("根据id查询")
     Admin findAdminById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addAdmin(Admin admin) ;
	
	@ApiOperation("修改")
     ResponseResult updateAdmin(Admin admin);

	@ApiOperation("删除")
     ResponseResult deleteAdmin(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Admin> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Admin> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Admin> findPageList(Integer page, Integer size, Map searchMap);

}
