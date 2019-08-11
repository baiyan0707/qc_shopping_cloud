package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.OrderLog;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.OrderLogControllerApi;
import com.qingcheng.order.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderLog")
public class OrderLogController implements OrderLogControllerApi {


    @Autowired
    private OrderLogService orderLogService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<OrderLog> findAll(){
        return orderLogService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public OrderLog findOrderLogById(@PathVariable("id") String id) {
        return orderLogService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addOrderLog(@RequestBody OrderLog orderLog) {
        return orderLogService.add(orderLog);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateOrderLog(@RequestBody OrderLog orderLog) {
        return orderLogService.update(orderLog);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteOrderLog(@PathVariable("id") String id) {
        return orderLogService.deleteOrderLog(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<OrderLog> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return orderLogService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<OrderLog> findList(@RequestBody Map searchMap) {
        return orderLogService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<OrderLog> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return orderLogService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return orderLogService.ossUpdate(file,folder);
    }

}
