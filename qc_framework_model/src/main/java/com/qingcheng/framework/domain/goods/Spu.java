package com.qingcheng.framework.domain.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * spu实体类
 * @author 
 *
 */
@Table(name="tb_spu")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Spu implements Serializable{

	@Id
	@ApiModelProperty("主键")
	private String id;//主键


	@ApiModelProperty("货号")
	private String sn;//货号
	@ApiModelProperty("SPU名")
	private String name;//SPU名
	@ApiModelProperty("副标题")
	private String caption;//副标题
	@ApiModelProperty("品牌ID")
	private Integer brandId;//品牌ID
	@ApiModelProperty("一级分类")
	private Integer category1Id;//一级分类
	@ApiModelProperty("二级分类")
	private Integer category2Id;//二级分类
	@ApiModelProperty("三级分类")
	private Integer category3Id;//三级分类
	@ApiModelProperty("模板ID")
	private Integer templateId;//模板ID
	@ApiModelProperty("运费模板id")
	private Integer freightId;//运费模板id
	@ApiModelProperty("图片")
	private String image;//图片
	@ApiModelProperty("图片列表")
	private String images;//图片列表
	@ApiModelProperty("售后服务")
	private String saleService;//售后服务
	@ApiModelProperty("介绍")
	private String introduction;//介绍
	@ApiModelProperty("规格列表")
	private String specItems;//规格列表
	@ApiModelProperty("参数列表")
	private String paraItems;//参数列表
	@ApiModelProperty("销量")
	private Integer saleNum;//销量
	@ApiModelProperty("评论数")
	private Integer commentNum;//评论数
	@ApiModelProperty("是否上架")
	private String isMarketable;//是否上架
	@ApiModelProperty("是否启用规格")
	private String isEnableSpec;//是否启用规格
	@ApiModelProperty("是否删除")
	private String isDelete;//是否删除
	@ApiModelProperty("审核状态")
	private String status;//审核状态


}
