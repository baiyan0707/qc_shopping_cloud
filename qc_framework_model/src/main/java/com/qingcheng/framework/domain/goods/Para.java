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
 * para实体类
 * @author 
 *
 */
@Table(name="tb_para")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Para implements Serializable{

	@Id
	@ApiModelProperty("id")
	private Integer id;//id


	@ApiModelProperty("名称")
	private String name;//名称
	@ApiModelProperty("选项")
	private String options;//选项
	@ApiModelProperty("排序")
	private Integer seq;//排序
	@ApiModelProperty("模板ID")
	private Integer templateId;//模板ID


}
