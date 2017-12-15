package com.czw.pay.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.czw.alipay.service.AlipayService;
import com.czw.alipay.types.ClientType;
import com.czw.common.tools.Tools;
import com.czw.pay.entity.JpayOrderItem;
import com.czw.pay.service.JpayOrderItemService;
import com.czw.pay.service.JpayOrderService;

/**
 * 创建者:崔志伟
 * emai：cuizhiwei4930@sina.com
 * 日期：2017年11月27日下午11:17:31
 */
@Controller
@PropertySource(value = {"classpath:/common.properties"},encoding="utf-8") 
@RequestMapping("/api/pay")
public class PayController {
	
	private static Logger logger = Logger.getLogger(PayController.class);
	
	@Autowired
	private AlipayService alipayService;
	
	@Resource(name="jpayOrderService")
	private JpayOrderService jpayOrderService;
	
	@Resource(name="jpayOrderItemService")
	private JpayOrderItemService jpayOrderItemService;
		
	/**
	 * 支付宝支付
	 * @param request
	 * @param response
	 * @param orderNo
	 * @param clientType
	 */
	@RequestMapping("/alipay")
	public void alipay(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="orderNo",required=false,defaultValue = "0") String orderNo,
    		@RequestParam(value="clientType",required=false,defaultValue = "0") int clientType){
		AlipayTradeWapPayModel atpm = new AlipayTradeWapPayModel();
		List<JpayOrderItem> itemList = jpayOrderItemService.findList("orderNo", orderNo);
		String resultform = "";
		//默认一种商品一个订单号
		if(itemList!=null && itemList.size()>0){
			JpayOrderItem item = itemList.get(0);
			atpm.setOutTradeNo(orderNo);
			atpm.setTotalAmount(Tools.toString(item.getTotalFee()));
			atpm.setSubject(item.getTitle());
			ClientType ct = ClientType.values()[clientType];
			resultform = alipayService.payJump(atpm, ct);
			//logger.info(Tools.logString(resultform));
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().print(resultform);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/wxpay")
	public void wxpay(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(required=false,defaultValue = "") String outTradeNo,
    		@RequestParam(required=false,defaultValue = "0") int clientType){
		
	}
	
	

}
