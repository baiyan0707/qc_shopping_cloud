package com.qingcheng.framework.domain.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * menu实体类
 * @author Bai
 *
 */
@Table(name="tb_menu")
@Data
public class Menu implements Serializable{

	@Id
	@ApiModelProperty("菜单ID")
	private String id;//菜单ID


	@ApiModelProperty("菜单名称")
	private String name;//菜单名称
	@ApiModelProperty("图标")
	private String icon;//图标
	@ApiModelProperty("URL")
	private String url;//URL
	@ApiModelProperty("上级菜单ID")
	private String parentId;//上级菜单ID


}
