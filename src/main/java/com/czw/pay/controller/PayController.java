package com.czw.pay.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.czw.alipay.service.AlipayService;
import com.czw.alipay.types.ClientType;
import com.czw.common.tools.Tools;
import com.czw.pay.entity.JpayOrder;
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
		
	@RequestMapping("/alipay")
    public void alipay(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(required=false,defaultValue = "") String outTradeNo,
    		@RequestParam(required=false,defaultValue = "0") int clientType) {
		AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
		alipayTradeWapPayModel.setOutTradeNo(outTradeNo);
		String form = "";
		if(clientType == ClientType.WAP.ordinal()){
			form = alipayService.payJump(alipayTradeWapPayModel,ClientType.WAP);
		}else if(clientType == ClientType.PC.ordinal()){
			form = alipayService.payJump(alipayTradeWapPayModel,ClientType.PC);
		}
		response.setContentType("text/html;charset=" + "utf-8");
		try {
			response.getWriter().write(form);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	@RequestMapping("/wxpay")
	public void wxpay(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(required=false,defaultValue = "") String outTradeNo,
    		@RequestParam(required=false,defaultValue = "0") int clientType){
		
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Object test(HttpServletRequest request, 
    		HttpServletResponse response,Model model){
		//根据返回标识处理相应订单返给前台状态页面
		JSONObject json = new JSONObject();
		JpayOrder o = new JpayOrder();
		o.setBuyerNick("lalalal");
		o.setOrderNo("12312312312");
		jpayOrderService.save(o);
		json.put("retcode", "1");
		json.put("msg", "success");
		logger.info(Tools.logString("111",json.toString()));
		return json;
	}
	
	/**
	 * 同步返回地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/payreturn")
	@ResponseBody
	public Object payReturn(HttpServletRequest request, 
    		HttpServletResponse response,Model model){
		//根据返回标识处理相应订单返给前台状态页面
		JSONObject json = new JSONObject();
		json.put("retcode", "1");
		json.put("msg", "success");
		logger.info(Tools.logString("111",json.toString()));
		return json;
	}
	
	/**
	 * 后台异步通知地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/paynotify")
	@ResponseBody
	public Object payNotify(HttpServletRequest request, 
    		HttpServletResponse response,Model model){
		//可以保证准确率处理订单支付成功逻辑
		JSONObject json = new JSONObject();
		json.put("retcode", "1");
		json.put("msg", "success");
		return json;
	}
	/*@RequestMapping("/test")
	public String test(Model model){
		model.addAttribute("time", new Date());
        model.addAttribute("message", "woshi");
		return "test";
	}*/
}
