package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * orderItem实体类
 * @author Bai
 *
 */
@Table(name="tb_order_item")
@Data
public class OrderItem implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private String id;//ID


	@ApiModelProperty("1级分类")
	private Integer categoryId1;//1级分类
	@ApiModelProperty("2级分类")
	private Integer categoryId2;//2级分类
	@ApiModelProperty("3级分类")
	private Integer categoryId3;//3级分类
	@ApiModelProperty("SPU_ID")
	private String spuId;//SPU_ID
	@ApiModelProperty("SKU_ID")
	private String skuId;//SKU_ID
	@ApiModelProperty("订单ID")
	private String orderId;//订单ID
	@ApiModelProperty("商品名称")
	private String name;//商品名称
	@ApiModelProperty("单价")
	private Integer price;//单价
	@ApiModelProperty("数量")
	private Integer num;//数量
	@ApiModelProperty("总金额")
	private Integer money;//总金额
	@ApiModelProperty("实付金额")
	private Integer payMoney;//实付金额
	@ApiModelProperty("图片地址")
	private String image;//图片地址
	@ApiModelProperty("重量")
	private Integer weight;//重量
	@ApiModelProperty("运费")
	private Integer postFee;//运费
	@ApiModelProperty("是否退货")
	private String isReturn;//是否退货


}
