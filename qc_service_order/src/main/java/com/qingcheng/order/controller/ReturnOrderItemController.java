package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.ReturnOrderItem;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.ReturnOrderItemControllerApi;
import com.qingcheng.order.service.ReturnOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/returnOrderItem")
public class ReturnOrderItemController implements ReturnOrderItemControllerApi {


    @Autowired
    private ReturnOrderItemService returnOrderItemService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<ReturnOrderItem> findAll(){
        return returnOrderItemService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public ReturnOrderItem findReturnOrderItemById(@PathVariable("id") Long id) {
        return returnOrderItemService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addReturnOrderItem(@RequestBody ReturnOrderItem returnOrderItem) {
        return returnOrderItemService.add(returnOrderItem);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateReturnOrderItem(@RequestBody ReturnOrderItem returnOrderItem) {
        return returnOrderItemService.update(returnOrderItem);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteReturnOrderItem(@PathVariable("id") Long id) {
        return returnOrderItemService.deleteReturnOrderItem(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<ReturnOrderItem> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return returnOrderItemService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<ReturnOrderItem> findList(@RequestBody Map searchMap) {
        return returnOrderItemService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<ReturnOrderItem> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return returnOrderItemService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return returnOrderItemService.ossUpdate(file,folder);
    }

}
