package com.qingcheng.manage_good.controller;

import com.qingcheng.framework.domain.goods.Album;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.AlbumControllerApi;
import com.qingcheng.manage_good.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController implements AlbumControllerApi {


    @Autowired
    private AlbumService albumService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Album> findAll(){
        return albumService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Album findAlbumById(@PathVariable("id") Long id) {
        return albumService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addAlbum(@RequestBody Album album) {
        return albumService.add(album);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateAlbum(@RequestBody Album album) {
        return albumService.update(album);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteAlbum(@PathVariable("id") Long id) {
        return albumService.deleteAlbum(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Album> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return albumService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map searchMap) {
        return albumService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Album> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return albumService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return albumService.ossUpdate(file,folder);
    }

}
