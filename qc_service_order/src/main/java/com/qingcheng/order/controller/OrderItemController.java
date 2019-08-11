package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.OrderItem;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.OrderItemControllerApi;
import com.qingcheng.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController implements OrderItemControllerApi {


    @Autowired
    private OrderItemService orderItemService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<OrderItem> findAll(){
        return orderItemService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public OrderItem findOrderItemById(@PathVariable("id") String id) {
        return orderItemService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.add(orderItem);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.update(orderItem);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteOrderItem(@PathVariable("id") String id) {
        return orderItemService.deleteOrderItem(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<OrderItem> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return orderItemService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<OrderItem> findList(@RequestBody Map searchMap) {
        return orderItemService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<OrderItem> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return orderItemService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return orderItemService.ossUpdate(file,folder);
    }

}
