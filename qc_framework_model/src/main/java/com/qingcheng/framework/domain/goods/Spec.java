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
 * spec实体类
 * @author 
 *
 */
@Table(name="tb_spec")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Spec implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("名称")
	private String name;//名称
	@ApiModelProperty("规格选项")
	private String options;//规格选项
	@ApiModelProperty("排序")
	private Integer seq;//排序
	@ApiModelProperty("模板ID")
	private Integer templateId;//模板ID


}
