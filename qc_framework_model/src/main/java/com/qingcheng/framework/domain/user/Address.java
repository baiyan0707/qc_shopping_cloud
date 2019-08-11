package com.qingcheng.framework.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * address实体类
 * @author Bai
 *
 */
@Table(name="tb_address")
@Data
public class Address implements Serializable{

	@Id
	@ApiModelProperty("id")
	private Integer id;//id


	@ApiModelProperty("用户名")
	private String username;//用户名
	@ApiModelProperty("省")
	private String provinceid;//省
	@ApiModelProperty("市")
	private String cityid;//市
	@ApiModelProperty("县/区")
	private String areaid;//县/区
	@ApiModelProperty("电话")
	private String phone;//电话
	@ApiModelProperty("详细地址")
	private String address;//详细地址
	@ApiModelProperty("联系人")
	private String contact;//联系人
	@ApiModelProperty("是否是默认 1默认 0否")
	private String isDefault;//是否是默认 1默认 0否
	@ApiModelProperty("别名")
	private String alias;//别名


}
