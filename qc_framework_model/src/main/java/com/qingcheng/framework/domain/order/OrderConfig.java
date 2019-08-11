package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * orderConfig实体类
 * @author Bai
 *
 */
@Table(name="tb_order_config")
@Data
public class OrderConfig implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("正常订单超时时间（分）")
	private Integer orderTimeout;//正常订单超时时间（分）
	@ApiModelProperty("秒杀订单超时时间（分）")
	private Integer seckillTimeout;//秒杀订单超时时间（分）
	@ApiModelProperty("自动收货（天）")
	private Integer takeTimeout;//自动收货（天）
	@ApiModelProperty("售后期限")
	private Integer serviceTimeout;//售后期限
	@ApiModelProperty("自动五星好评")
	private Integer commentTimeout;//自动五星好评


}
