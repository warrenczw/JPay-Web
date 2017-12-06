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
 * 创建日期：2017年12月4日 
 * www.cuizhiwei.com
 */
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Proxy(lazy = false)
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "jpay_order_shipping")
public class JpayOrderShipping extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617167943707610731L;

	private Long orderId;// 订单id
	private String orderNo;// 订单号
	private String receiverName;// 收货人姓名
	private String receiverPhone;// 收货人电话
	private String receiverMobile;// 收货人手机号码
	private String province;// 省份
	private String provinceCode;// 省代码
	private String city;// 城市
	private String cityCode;// 城市代码
	private String address;// 详细地址
	private String postcode;// 邮编

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
