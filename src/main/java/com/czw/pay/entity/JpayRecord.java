package com.czw.pay.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.czw.common.entity.BaseEntity;
import com.czw.pay.type.PaymentChannelType;
import com.czw.pay.type.PlatType;

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
@Table(name = "jpay_record")
public class JpayRecord extends BaseEntity {
	private static final long serialVersionUID = -5605386336171394689L;

	private String orderNo;// 订单编号
	private String itemNo;// 商品编号
	private BigDecimal totalAmount;// 支付总金额
	private String tradeNo;// 外部交易流水号
	private Long userId;// 用户id
	private String outUserNo;// 外部用户标识
	@Enumerated(EnumType.ORDINAL)
	private PaymentChannelType paymentChannel;// 支付渠道平台
	@Enumerated(EnumType.ORDINAL)
	private PlatType plat;// 平台

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOutUserNo() {
		return outUserNo;
	}

	public void setOutUserNo(String outUserNo) {
		this.outUserNo = outUserNo;
	}

	public PaymentChannelType getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(PaymentChannelType paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public PlatType getPlat() {
		return plat;
	}

	public void setPlat(PlatType plat) {
		this.plat = plat;
	}

}
