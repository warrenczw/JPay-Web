package com.czw.pay.type;
/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
public enum PaymentType {
	
	PAYONLINE("在线支付"),
	PAYRECEIVE("货到付款");
	
	private String info;
	private PaymentType(String info){
		this.info=info;
	}
	public String getInfo(){
		return info;
	}
}
