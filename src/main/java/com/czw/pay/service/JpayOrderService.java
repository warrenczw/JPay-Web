package com.czw.pay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czw.common.dao.BaseDao;
import com.czw.common.service.BaseService;
import com.czw.common.type.StatusType;
import com.czw.pay.entity.JpayOrder;
import com.czw.pay.type.PayStatusType;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月4日
 * www.cuizhiwei.com
 */
@Service
@Transactional
public class JpayOrderService extends BaseService<JpayOrder>{
	
	@Resource(name="jpayOrderDao")
	@Override
	public void setBaseDao(BaseDao<JpayOrder> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 根据订单号查询订单信息
	 * @param orderNo
	 * @return
	 */
	public JpayOrder findByOrderNo(String orderNo){
		String hql="from JpayOrder where orderNo=:orderNo and status=:status";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("orderNo", orderNo);
		values.put("status", StatusType.VALID);
		List<JpayOrder> orderList = findList(hql, values);
		if(orderList!=null && orderList.size()>0){
			return orderList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 订单是否支付成功
	 * @param orderNo
	 * @return
	 */
	public Boolean isPaid(String orderNo){
		String hql="from JpayOrder where orderNo=:orderNo and status=:status";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("orderNo", orderNo);
		values.put("status", StatusType.VALID);
		List<JpayOrder> list = this.findList(hql, values);
		boolean result = false;
		if(list!=null && list.size()>0){
			JpayOrder jo= list.get(0);
			if(jo.getPayStatus() == PayStatusType.HASPAY){
				result = true;
			}
		}
		return result;
	}


}
