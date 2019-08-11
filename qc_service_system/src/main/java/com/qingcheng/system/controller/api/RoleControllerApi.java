package com.qingcheng.system.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.system.Role;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "role接口", description = "提供role查询及管理", tags = {"role接口"})
public interface RoleControllerApi{


	@ApiOperation("查询所有")
     List<Role> findAll();

	@ApiOperation("根据id查询")
     Role findRoleById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addRole(Role role) ;
	
	@ApiOperation("修改")
     ResponseResult updateRole(Role role);

	@ApiOperation("删除")
     ResponseResult deleteRole(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Role> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Role> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Role> findPageList(Integer page, Integer size, Map searchMap);

}
