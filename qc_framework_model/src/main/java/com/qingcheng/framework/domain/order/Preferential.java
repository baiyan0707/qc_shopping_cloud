package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * preferential实体类
 * @author Bai
 *
 */
@Table(name="tb_preferential")
@Data
public class Preferential implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("消费金额")
	private Integer buyMoney;//消费金额
	@ApiModelProperty("优惠金额")
	private Integer preMoney;//优惠金额
	@ApiModelProperty("品类ID")
	private Integer categoryId;//品类ID
	@ApiModelProperty("活动开始日期")
	private java.util.Date startTime;//活动开始日期
	@ApiModelProperty("活动截至日期")
	private java.util.Date endTime;//活动截至日期
	@ApiModelProperty("状态")
	private String state;//状态
	@ApiModelProperty("类型1不翻倍 2翻倍")
	private String type;//类型1不翻倍 2翻倍


}
