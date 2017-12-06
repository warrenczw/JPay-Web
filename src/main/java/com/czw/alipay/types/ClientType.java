package com.czw.alipay.types;
/**
 * 创建者:崔志伟
 * emai：cuizhiwei4930@sina.com
 * 日期：2017年11月28日
 */
public enum ClientType {
	WAP("移动端"),
	PC("PC"),
	APP("APP");
	private String info;
	private ClientType(String info){
		this.info=info;
	}
	public String getInfo(){
		return info;
	}

}
