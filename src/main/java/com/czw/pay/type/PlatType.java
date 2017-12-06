package com.czw.pay.type;
/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
public enum PlatType {
	
	GYPAY("gypay");

	private final String info;

	private PlatType(String info)
	{
		this.info = info;
	}

	public String getInfo()
	{
		return info;
	}

}
