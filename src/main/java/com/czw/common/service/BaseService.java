package com.czw.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.czw.common.dao.BaseDao;
import com.czw.common.entity.BaseEntity;

/**
 * @author 崔志伟
 * 联系方式：493067123@qq.com
 * 创建日期：2017年12月5日
 * www.cuizhiwei.com
 */
@Transactional
public abstract class BaseService <T extends BaseEntity>{
	protected BaseDao<T> baseDao;
	public BaseDao<T> getBaseDao()
	{
		return baseDao;
	}
	public abstract void setBaseDao(BaseDao<T> baseDao);
	
	public SessionFactory getSessionFactory()
	{
		return getBaseDao().getSessionFactory();
	}
	
	/**
	 * 新增对象.
	 */
	public void save(T entity)
	{
		getBaseDao().save(entity);
	}
	
	/**
     * 新增或修改对象.
     */
    public void saveOrUpdate(T entity)
    {
        getBaseDao().saveOrUpdate(entity);
    }
    /**
	 * 修改对象.
	 */
	public void update(T entity)
	{
		getBaseDao().update(entity);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(T entity)
	{
		getBaseDao().delete(entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(Long id)
	{
		getBaseDao().delete(id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(Set<Long> ids)
	{
		for(long id : ids){
			getBaseDao().delete(id);
		}
	}
	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public T find(Long id)
	{
		T entity = null;
		if (id != null && id > 0)
		{
			entity = getBaseDao().find(id);
		}
		return entity;
	}
	
	/**
	 * 通过限定一系列条件进行唯一查询
	 */
	@Transactional(readOnly = true)
	public T find(Map<String, Object> query)
	{
		T entity = null;
		if (query != null && query.size() > 0)
		{
			entity = getBaseDao().find(query);
		}
		return entity;
	}
	
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public T find(String fieldName, Object fieldValue)
	{
		return getBaseDao().find(fieldName, fieldValue);
	}
	
	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(String fieldName, Object fieldValue)
	{
		return getBaseDao().findList(fieldName, fieldValue);
	}
	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListByIds(List<Long> idList)
	{
		List<T> listEntities = new ArrayList<T>(); 
		//此处进行了更改，将返回结果按输入参数的id顺序，进行了重新排序
		List<T> listTemp = getBaseDao().findListByIds(idList);
		if(listTemp==null)
		{
			return null;
		}else
		{
			Map<Long, T> map = new HashMap<Long, T>();
			for(T t : listTemp)
			{
				if (t != null && t instanceof BaseEntity)
				{
					map.put(((BaseEntity) t).getId(), t);
				}
			}
			for(Long id : idList)
			{
				if(id!=null)
				{
					T t = map.get(id);
					if(t!=null)
					{
						listEntities.add(t);
					}
				}
			}
			
			return listEntities;
		}
	}
	
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Object... values)
	{
		return (X)getBaseDao().find(hql, values);
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Map<String, ?> values)
	{
		return (X)getBaseDao().find(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Object... values)
	{
		return getBaseDao().findList(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		return getBaseDao().findList(hql, values);
	}
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Map<String, ?> values, boolean cacheable)
	{
		return getBaseDao().findList(hql, values, cacheable);
	}
	
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Map<String, ?> values, int startPos, int pageSize)
	{
		return getBaseDao().findList(hql, values, startPos, pageSize);
	}
	
	
	/**
	 * 本地SQL进行修改/删除操作.
	 * @return 更新记录数.
	 */
	@Transactional(readOnly = true)
	public List find(String sql)
	{
		return getBaseDao().getSql(sql);
	}
}
