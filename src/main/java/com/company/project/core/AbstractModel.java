package com.company.project.core;

import java.util.Date;

import javax.persistence.Column;

public class AbstractModel extends IdModel{
	
	public AbstractModel() {
	}
	
	private Boolean deleted;
	
	@Column(name = "createTime")
	private Date createtime;

	@Column(name = "updateTime")
	private Date updatetime;
	
	@Column(name = "lastUpdateBy")
	private String lastUpdateBy;


	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

}
