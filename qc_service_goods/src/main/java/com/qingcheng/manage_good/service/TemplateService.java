package com.qingcheng.manage_good.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.goods.Template;
import com.qingcheng.manage_good.dao.TemplateMapper;
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
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Template> findAll(){
        return templateMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Template findById(Integer id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param template
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Template template) {
        if (template == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		templateMapper.insert(template);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param template
     */
	@Transactional
    public ResponseResult update(Template template) {
        if (template==null||template.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = templateMapper.updateByPrimaryKeySelective(template);
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
    public ResponseResult deleteTemplate(Integer id) {
        int result = templateMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Template> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Template> template = (Page<Template>) templateMapper.selectAll();
        QueryResult<Template> queryResult = new QueryResult<>();
        List<Template> result = template.getResult();
        long total = template.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Template> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return templateMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 模板名称
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 规格数量
            if(searchMap.get("specNum")!=null ){
                criteria.andEqualTo("specNum",searchMap.get("specNum"));
            }
            // 参数数量
            if(searchMap.get("paraNum")!=null ){
                criteria.andEqualTo("paraNum",searchMap.get("paraNum"));
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
    public QueryBrandResult<Template> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Template> brands = (Page<Template>) templateMapper.selectByExample(example);
        QueryResult<Template> queryResult = new QueryResult<>();
        List<Template> result = brands.getResult();
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
