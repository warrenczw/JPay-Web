package com.czw.common.type;
/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
public enum StatusType {
	DEMAND("申请"),
	VALID("正常"),
	INVALID("无效"),
	LOCK("锁定"),
	DELETE("被删除");

	private final String info;

	private StatusType(String info)
	{
		this.info = info;
	}

	public String getInfo()
	{
		return info;
	}

	
}
