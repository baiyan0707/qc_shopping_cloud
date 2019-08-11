package com.qingcheng.user.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.user.User;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "user接口", description = "提供user查询及管理", tags = {"user接口"})
public interface UserControllerApi{


	@ApiOperation("查询所有")
     List<User> findAll();

	@ApiOperation("根据id查询")
     User findUserById(String username) ;
	
	@ApiOperation("添加")
     ResponseResult addUser(User user) ;
	
	@ApiOperation("修改")
     ResponseResult updateUser(User user);

	@ApiOperation("删除")
     ResponseResult deleteUser(String username) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<User> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<User> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<User> findPageList(Integer page, Integer size, Map searchMap);

}
