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
 * sku实体类
 * @author 
 *
 */
@Table(name="tb_sku")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Sku implements Serializable{

	@Id
	@ApiModelProperty("商品id")
	private String id;//商品id


	@ApiModelProperty("商品条码")
	private String sn;//商品条码
	@ApiModelProperty("SKU名称")
	private String name;//SKU名称
	@ApiModelProperty("价格（分）")
	private Integer price;//价格（分）
	@ApiModelProperty("库存数量")
	private Integer num;//库存数量
	@ApiModelProperty("库存预警数量")
	private Integer alertNum;//库存预警数量
	@ApiModelProperty("商品图片")
	private String image;//商品图片
	@ApiModelProperty("商品图片列表")
	private String images;//商品图片列表
	@ApiModelProperty("重量（克）")
	private Integer weight;//重量（克）
	@ApiModelProperty("创建时间")
	private java.util.Date createTime;//创建时间
	@ApiModelProperty("更新时间")
	private java.util.Date updateTime;//更新时间
	@ApiModelProperty("SPUID")
	private String spuId;//SPUID
	@ApiModelProperty("类目ID")
	private Integer categoryId;//类目ID
	@ApiModelProperty("类目名称")
	private String categoryName;//类目名称
	@ApiModelProperty("品牌名称")
	private String brandName;//品牌名称
	@ApiModelProperty("规格")
	private String spec;//规格
	@ApiModelProperty("销量")
	private Integer saleNum;//销量
	@ApiModelProperty("评论数")
	private Integer commentNum;//评论数
	@ApiModelProperty("商品状态 1-正常，2-下架，3-删除")
	private String status;//商品状态 1-正常，2-下架，3-删除


}
