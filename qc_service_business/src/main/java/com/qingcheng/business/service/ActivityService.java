package com.qingcheng.business.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.business.dao.ActivityMapper;
import com.qingcheng.framework.domain.business.Activity;
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
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Activity> findAll(){
        return activityMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Activity findById(Integer id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  activityMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param activity
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Activity activity) {
        if (activity == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		activityMapper.insert(activity);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param activity
     */
	@Transactional
    public ResponseResult update(Activity activity) {
        if (activity==null||activity.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = activityMapper.updateByPrimaryKeySelective(activity);
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
    public ResponseResult deleteActivity(Integer id) {
        int result = activityMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Activity> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Activity> activity = (Page<Activity>) activityMapper.selectAll();
        QueryResult<Activity> queryResult = new QueryResult<>();
        List<Activity> result = activity.getResult();
        long total = activity.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Activity> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return activityMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 活动标题
            if (StringUtils.isNotEmpty((String) searchMap.get("title"))) {
                criteria.andLike("title","%"+searchMap.get("title")+"%");
           	}
            // 状态
            if (StringUtils.isNotEmpty((String) searchMap.get("status"))) {
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}
            // 活动内容
            if (StringUtils.isNotEmpty((String) searchMap.get("content"))) {
                criteria.andLike("content","%"+searchMap.get("content")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
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
    public QueryBrandResult<Activity> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Activity> brands = (Page<Activity>) activityMapper.selectByExample(example);
        QueryResult<Activity> queryResult = new QueryResult<>();
        List<Activity> result = brands.getResult();
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
