package com.czw.pay.controller.page;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.czw.common.tools.DateTools;
import com.czw.common.tools.Tools;
import com.czw.pay.entity.JpayOrderItem;
import com.czw.pay.service.JpayOrderItemService;
import com.czw.pay.service.JpayOrderService;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月7日
 * www.cuizhiwei.com
 */
@Controller
@RequestMapping("/p")
public class PayPage {
	
	@Resource(name="jpayOrderService")
	private JpayOrderService jpayOrderService;
	
	@Resource(name="jpayOrderItemService")
	private JpayOrderItemService jpayOrderItemService;

	@RequestMapping("/item")
	public String item(HttpServletRequest request, 
    		HttpServletResponse response,
    		Model model){
		return "item";
	}
	
	@RequestMapping("/order")
	public String order(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="orderNo",required=false) String orderNo,
    		Model model){
		if(Tools.stringIsNotNull(orderNo)){
			List<JpayOrderItem> itemList = jpayOrderItemService.findList("orderNo", orderNo);
			//默认一种商品一个订单号
			if(itemList!=null && itemList.size()>0){
				JpayOrderItem item = itemList.get(0);
				model.addAttribute("itemName", item.getTitle());
				model.addAttribute("num", item.getNum());
				model.addAttribute("itemNo", item.getItemNo());
				model.addAttribute("totalFee", item.getTotalFee());
				model.addAttribute("createTime", DateTools.formatDateTime(item.getCreateTime()));
			}
		}
		model.addAttribute("orderNo", orderNo);
		return "order";
	}
	
	@RequestMapping("/tradefinish")
	public String tradefinish(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="orderNo",required=false) String orderNo,
    		Model model){
			return "tradefinish";
	}
	
	@RequestMapping("/process")
	public String process(HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="orderNo",required=false) String orderNo,
    		Model model){
			return "process";
	}
	
}
