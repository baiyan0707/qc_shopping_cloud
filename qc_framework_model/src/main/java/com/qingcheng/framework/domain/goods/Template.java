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
 * template实体类
 * @author 
 *
 */
@Table(name="tb_template")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Template implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("模板名称")
	private String name;//模板名称
	@ApiModelProperty("规格数量")
	private Integer specNum;//规格数量
	@ApiModelProperty("参数数量")
	private Integer paraNum;//参数数量


}
