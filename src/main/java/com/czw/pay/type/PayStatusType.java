package com.czw.pay.type;
/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
public enum PayStatusType {
	
	UNPAY("未付款"),
	HASPAY("已付款"),
	UNPOST("未发货"),
	HASPOST("已发货"),
	FINISH("交易成功"),
	CLOSE("交易关闭");
	
	private String info;
	private PayStatusType(String info){
		this.info=info;
	}
	public String getInfo(){
		return info;
	}
}
