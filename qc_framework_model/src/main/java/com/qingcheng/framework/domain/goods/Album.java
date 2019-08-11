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
 * album实体类
 * @author 
 *
 */
@Table(name="tb_album")@Data
@NoArgsConstructor
@Entity
@ToString
public class Album implements Serializable{

	@Id
	@ApiModelProperty("编号")
	private Long id;//编号


	@ApiModelProperty("相册名称")
	private String title;//相册名称
	@ApiModelProperty("相册封面")
	private String image;//相册封面
	@ApiModelProperty("图片列表")
	private String imageItems;//图片列表


}
