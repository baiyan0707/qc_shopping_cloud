package com.qingcheng.system.service;

import com.qingcheng.system.manage_good.dao.MenuMapper;
import com.qingcheng.framework.domain.system.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.ExceptionCast;
import com.qingcheng.framework.exception.model.response.GoodsCode;
import com.qingcheng.framework.exception.model.response.QueryResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.UUID;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Menu> findAll(){
        return menuMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Menu findById(String id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param menu
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Menu menu) {
        if (menu == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		menuMapper.insert(menu);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param menu
     */
	@Transactional
    public ResponseResult update(Menu menu) {
        if (menu==null||menu.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = menuMapper.updateByPrimaryKeySelective(menu);
        if (count != 1) {
            ExceptionCast.cast(GoodsCode.UPDATE_ERROR);
        }
        return ResponseResult.SUCCESS();
    }

    /**
     * @param id 所有品牌id集合
     * @return 根据所有品牌id 删除对应的品牌信息 并返回操作结果
     */
    @Transactional
    public ResponseResult deleteMenu(String id) {
        int result = menuMapper.deleteByPrimaryKey(id);
        if(result > 0){
            return new ResponseResult(GoodsCode.SUCCESS);
        }
        return new ResponseResult(GoodsCode.DELETE_ERROR);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public QueryBrandResult<Menu> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Menu> menu = (Page<Menu>) menuMapper.selectAll();
        QueryResult<Menu> queryResult = new QueryResult<>();
        List<Menu> result = menu.getResult();
        long total = menu.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Menu> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return menuMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 菜单ID
            if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 菜单名称
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 图标
            if (StringUtils.isNotEmpty((String) searchMap.get("icon"))) {
                criteria.andLike("icon","%"+searchMap.get("icon")+"%");
           	}
            // URL
            if (StringUtils.isNotEmpty((String) searchMap.get("url"))) {
                criteria.andLike("url","%"+searchMap.get("url")+"%");
           	}
            // 上级菜单ID
            if (StringUtils.isNotEmpty((String) searchMap.get("parent_id"))) {
                criteria.andLike("parent_id","%"+searchMap.get("parent_id")+"%");
           	}


        }
        return example;
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    public QueryBrandResult<Menu> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Menu> brands = (Page<Menu>) menuMapper.selectByExample(example);
        QueryResult<Menu> queryResult = new QueryResult<>();
        List<Menu> result = brands.getResult();
        long total = brands.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }
	
	/**
     * 将图片上传到oss服务器
     * @param file
     * @param folder
     * @return
     */
    public String ossUpdate(MultipartFile file, String folder){
        String bucketName = "qingcheng0707";
        String fileName = folder + "/" + UUID.randomUUID() + file.getOriginalFilename();

        try {
            ossClient.putObject(bucketName,fileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://" + bucketName + "oss-ap-northeast-1.aliyuncs.com/" + fileName;
    }
}
