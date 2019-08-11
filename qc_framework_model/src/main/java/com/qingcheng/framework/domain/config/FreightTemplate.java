package com.qingcheng.framework.domain.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * freightTemplate实体类
 * @author Bai
 *
 */
@Table(name="tb_freight_template")
@Data
public class FreightTemplate implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("模板名称")
	private String name;//模板名称
	@ApiModelProperty("计费方式")
	private String type;//计费方式


}
