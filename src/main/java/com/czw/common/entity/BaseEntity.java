package com.czw.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.czw.common.type.StatusType;
/**
 * 创建者:崔志伟
 * emai：cuizhiwei4930@sina.com
 * 日期：2017年11月27日
 */
@MappedSuperclass
public class BaseEntity implements Cloneable,Serializable{

	/**
	 * 
	 * 创建者:czw
	 */
	private static final long serialVersionUID = -3937609607417122975L;
	
	@Override  
    public Object clone() throws CloneNotSupportedException {  
        return super.clone();  
    }
	
	//主键自动生成策略：数据库自动增长
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	//插入时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,updatable=false)
	protected Date createTime;
	
	//最后一次修改时间
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateTime;
	
	@Enumerated(EnumType.ORDINAL)
	protected StatusType status = StatusType.VALID;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		if(createTime == null){
			createTime = new Date();
		}
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		if(updateTime == null ){
			updateTime = new Date();
		}
		this.updateTime = updateTime;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}
	
	
}
