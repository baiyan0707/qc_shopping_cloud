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
 * pref实体类
 * @author 
 *
 */
@Table(name="tb_pref")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Pref implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("分类ID")
	private Integer cateId;//分类ID
	@ApiModelProperty("消费金额")
	private Integer buyMoney;//消费金额
	@ApiModelProperty("优惠金额")
	private Integer preMoney;//优惠金额
	@ApiModelProperty("活动开始日期")
	private java.util.Date startTime;//活动开始日期
	@ApiModelProperty("活动截至日期")
	private java.util.Date endTime;//活动截至日期
	@ApiModelProperty("类型")
	private String type;//类型
	@ApiModelProperty("状态")
	private String state;//状态


}
