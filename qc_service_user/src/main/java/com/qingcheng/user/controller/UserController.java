package com.qingcheng.user.controller;

import com.qingcheng.framework.domain.user.User;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.user.controller.api.UserControllerApi;
import com.qingcheng.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {


    @Autowired
    private UserService userService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

	
    @Override
    @GetMapping("/get/{username}")
    public User findUserById(@PathVariable("username") String username) {
        return userService.findById(username);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody User user) {
        return userService.add(user);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateUser(@RequestBody User user) {
        return userService.update(user);
    }
	

    @Override
    @DeleteMapping("/delete/{username}")
    public ResponseResult deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<User> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return userService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<User> findList(@RequestBody Map searchMap) {
        return userService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<User> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return userService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return userService.ossUpdate(file,folder);
    }

}
