package com.czw.pay.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.czw.pay.entity.JpayOrder;
import com.czw.pay.entity.JpayOrderItem;
import com.czw.pay.service.JpayOrderItemService;
import com.czw.pay.service.JpayOrderService;
import com.czw.pay.tools.OrderTools;
import com.czw.pay.type.PayStatusType;
import com.czw.pay.type.PlatType;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
@Controller
@PropertySource(value = {"classpath:/common.properties"},encoding="utf-8") 
@RequestMapping("/api/order")
public class OrderController {
	
	private static Logger logger = Logger.getLogger(OrderController.class);
	
	@Resource(name="jpayOrderService")
	private JpayOrderService jpayOrderService;
	
	@Resource(name="jpayOrderItemService")
	private JpayOrderItemService jpayOrderItemService;
	
	
	/**
	 * 生成订单
	 * @param request
	 * @param response
	 * @param itemName
	 * @param price
	 * @param itemNo
	 * @param num
	 * @param uid
	 * @param plat
	 * @param model
	 * @return
	 */
	@RequestMapping("/createOrder")
	@ResponseBody
	public Object createOrder(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="itemName",required=false,defaultValue = "") String itemName,
    		@RequestParam(value="price",required=false,defaultValue = "0") BigDecimal price,
    		@RequestParam(value="itemNo",required=false,defaultValue = "0") String itemNo,
    		@RequestParam(value="num",required=false,defaultValue = "1") int num,
    		@RequestParam(value="uid",required=false,defaultValue = "0") Long uid,
    		@RequestParam(value="plat",required=false,defaultValue = "0") int plat){
		JSONObject result = new JSONObject();
		try {
			JpayOrder order = new JpayOrder();
			
			String orderno = OrderTools.createOrderNo(PlatType.values()[plat], uid);
			order.setOrderNo(orderno);
			BigDecimal numbd = new BigDecimal(Long.toString(num));
			BigDecimal mutiresult = numbd.multiply(price);
			order.setPayment(mutiresult);
			order.setPayStatus(PayStatusType.UNPAY);
			order.setUserId(uid);
			jpayOrderService.save(order);
			JpayOrderItem item = new JpayOrderItem();
			item.setItemNo(itemNo);
			item.setOrderId(order.getId());
			item.setOrderNo(orderno);
			item.setNum(num);
			item.setTitle(itemName);
			item.setPrice(price);
			item.setTotalFee(mutiresult);
			jpayOrderItemService.save(item);
			result.put("retcode", 1);
			result.put("orderno", orderno);
			result.put("msg", "下单成功");
		} catch (Exception e) {
			result.put("retcode", 0);
			result.put("msg", "下单失败");
		}
		return result;
	}
	
	
	
}
