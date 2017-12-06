package com.czw.pay.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.czw.common.entity.BaseEntity;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Proxy(lazy = false)
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "jpay_user")
public class JpayUser extends BaseEntity {

	private static final long serialVersionUID = 2000421494465832857L;

	private String nickname;// 用户昵称
	private int sex;// 性别

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

}
