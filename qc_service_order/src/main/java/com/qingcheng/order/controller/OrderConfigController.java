package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.OrderConfig;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.OrderConfigControllerApi;

import com.qingcheng.order.service.OrderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderConfig")
public class OrderConfigController implements OrderConfigControllerApi {


    @Autowired
    private OrderConfigService orderConfigService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<OrderConfig> findAll(){
        return orderConfigService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public OrderConfig findOrderConfigById(@PathVariable("id") Integer id) {
        return orderConfigService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addOrderConfig(@RequestBody OrderConfig orderConfig) {
        return orderConfigService.add(orderConfig);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateOrderConfig(@RequestBody OrderConfig orderConfig) {
        return orderConfigService.update(orderConfig);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteOrderConfig(@PathVariable("id") Integer id) {
        return orderConfigService.deleteOrderConfig(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<OrderConfig> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return orderConfigService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<OrderConfig> findList(@RequestBody Map searchMap) {
        return orderConfigService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<OrderConfig> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return orderConfigService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return orderConfigService.ossUpdate(file,folder);
    }

}
