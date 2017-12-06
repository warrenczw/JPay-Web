package com.czw.pay.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czw.common.dao.BaseDao;
import com.czw.common.service.BaseService;
import com.czw.pay.entity.JpayRecord;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月6日
 * www.cuizhiwei.com
 */
@Service
@Transactional
public class JpayRecordService extends BaseService<JpayRecord>{

	@Resource(name="jpayRecordDao")
	@Override
	public void setBaseDao(BaseDao<JpayRecord> baseDao) {
		this.baseDao = baseDao;
	}
	
}
