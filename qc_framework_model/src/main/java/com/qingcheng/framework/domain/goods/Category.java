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
 * category实体类
 * @author 
 *
 */
@Table(name="tb_category")
@Data
@NoArgsConstructor
@Entity
@ToString
public class Category implements Serializable{

	@Id
	@ApiModelProperty("分类ID")
	private Integer id;//分类ID


	@ApiModelProperty("分类名称")
	private String name;//分类名称
	@ApiModelProperty("商品数量")
	private Integer goodsNum;//商品数量
	@ApiModelProperty("是否显示")
	private String isShow;//是否显示
	@ApiModelProperty("是否导航")
	private String isMenu;//是否导航
	@ApiModelProperty("排序")
	private Integer seq;//排序
	@ApiModelProperty("上级ID")
	private Integer parentId;//上级ID
	@ApiModelProperty("模板ID")
	private Integer templateId;//模板ID


}
