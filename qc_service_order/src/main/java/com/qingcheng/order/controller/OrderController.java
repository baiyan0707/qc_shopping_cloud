package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.Order;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.OrderControllerApi;
import com.qingcheng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController implements OrderControllerApi {


    @Autowired
    private OrderService orderService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Order findOrderById(@PathVariable("id") String id) {
        return orderService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addOrder(@RequestBody Order order) {
        return orderService.add(order);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteOrder(@PathVariable("id") String id) {
        return orderService.deleteOrder(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Order> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return orderService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map searchMap) {
        return orderService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Order> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return orderService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return orderService.ossUpdate(file,folder);
    }

}
