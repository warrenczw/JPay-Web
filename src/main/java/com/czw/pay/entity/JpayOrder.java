package com.czw.pay.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.czw.common.entity.BaseEntity;
import com.czw.pay.type.PayStatusType;
import com.czw.pay.type.PaymentChannelType;
import com.czw.pay.type.PaymentType;
import com.czw.pay.type.PlatType;

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
@Table(name = "jpay_order")
public class JpayOrder extends BaseEntity {

	private static final long serialVersionUID = -8868566083752169571L;
	@Enumerated(EnumType.ORDINAL)
	private PlatType plat;// 平台
	private String orderNo;// 订单编号
	private BigDecimal payment;// 实付金额
	@Enumerated(EnumType.ORDINAL)
	private PaymentType paymentType;// 支付类型
	@Enumerated(EnumType.ORDINAL)
	private PaymentChannelType paymentChannel;// 支付渠道
	private BigDecimal postFee;// 邮费
	@Enumerated(EnumType.ORDINAL)
	private PayStatusType payStatus;// 付款状态
	private Date endTime;// 交易完成时间
	private Date closeTime;// 交易关闭时间
	private String shippingName;// 物流名称
	private String shippingCode;// 物流单号
	private Long userId;// 用户id
	private String buyerMessage;// 买家留言
	private String buyerNick;// 买家昵称
	private int buyerRate;// 买家是否评价

	
	public PlatType getPlat() {
		return plat;
	}

	public void setPlat(PlatType plat) {
		this.plat = plat;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentChannelType getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(PaymentChannelType paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public PayStatusType getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(PayStatusType payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public int getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(int buyerRate) {
		this.buyerRate = buyerRate;
	}

}
