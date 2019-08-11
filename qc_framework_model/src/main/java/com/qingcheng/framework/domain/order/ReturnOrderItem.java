package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * returnOrderItem实体类
 * @author Bai
 *
 */
@Table(name="tb_return_order_item")
@Data
public class ReturnOrderItem implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Long id;//ID


	@ApiModelProperty("分类ID")
	private Long categoryId;//分类ID
	@ApiModelProperty("SPU_ID")
	private Long spuId;//SPU_ID
	@ApiModelProperty("SKU_ID")
	private Long skuId;//SKU_ID
	@ApiModelProperty("订单ID")
	private Long orderId;//订单ID
	@ApiModelProperty("订单明细ID")
	private Long orderItemId;//订单明细ID
	@ApiModelProperty("退货订单ID")
	private Long returnOrderId;//退货订单ID
	@ApiModelProperty("标题")
	private String title;//标题
	@ApiModelProperty("单价")
	private Integer price;//单价
	@ApiModelProperty("数量")
	private Integer num;//数量
	@ApiModelProperty("总金额")
	private Integer money;//总金额
	@ApiModelProperty("支付金额")
	private Integer payMoney;//支付金额
	@ApiModelProperty("图片地址")
	private String image;//图片地址
	@ApiModelProperty("重量")
	private Integer weight;//重量


}
