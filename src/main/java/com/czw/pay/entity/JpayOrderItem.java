package com.czw.pay.entity;

import java.math.BigDecimal;

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
@Table(name = "jpay_order_item")
public class JpayOrderItem extends BaseEntity {

	private static final long serialVersionUID = -8671321864818831509L;

	private String itemNo;// 商品编号
	private Long orderId;// 订单id
	private String orderNo;// 订单号
	private int num;// 商品购买数量
	private String title;// 商品标题
	private BigDecimal price;// 商品单价
	private BigDecimal totalFee;// 商品总金额
	private String picUrl;// 商品图片地址
	private String detail;// 商品详情

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
