package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.ReturnOrder;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.ReturnOrderControllerApi;
import com.qingcheng.order.service.ReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/returnOrder")
public class ReturnOrderController implements ReturnOrderControllerApi {


    @Autowired
    private ReturnOrderService returnOrderService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<ReturnOrder> findAll(){
        return returnOrderService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public ReturnOrder findReturnOrderById(@PathVariable("id") Long id) {
        return returnOrderService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addReturnOrder(@RequestBody ReturnOrder returnOrder) {
        return returnOrderService.add(returnOrder);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateReturnOrder(@RequestBody ReturnOrder returnOrder) {
        return returnOrderService.update(returnOrder);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteReturnOrder(@PathVariable("id") Long id) {
        return returnOrderService.deleteReturnOrder(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<ReturnOrder> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return returnOrderService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<ReturnOrder> findList(@RequestBody Map searchMap) {
        return returnOrderService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<ReturnOrder> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return returnOrderService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return returnOrderService.ossUpdate(file,folder);
    }

}
