package com.qingcheng.user.controller;


import com.qingcheng.framework.domain.user.Address;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.user.controller.api.AddressControllerApi;
import com.qingcheng.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController implements AddressControllerApi {


    @Autowired
    private AddressService addressService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Address> findAll(){
        return addressService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Address findAddressById(@PathVariable("id") Integer id) {
        return addressService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addAddress(@RequestBody Address address) {
        return addressService.add(address);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateAddress(@RequestBody Address address) {
        return addressService.update(address);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteAddress(@PathVariable("id") Integer id) {
        return addressService.deleteAddress(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Address> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return addressService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Address> findList(@RequestBody Map searchMap) {
        return addressService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Address> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return addressService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return addressService.ossUpdate(file,folder);
    }

}
